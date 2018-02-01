package com.ufcg.sad.services.questionario;

import com.ufcg.sad.exceptions.EntidadeInvalidaException;
import com.ufcg.sad.exceptions.EntidadeNotFoundException;
import com.ufcg.sad.models.aluno.Aluno;
import com.ufcg.sad.models.disciplina.Disciplina;
import com.ufcg.sad.models.matricula.Matricula;
import com.ufcg.sad.models.questionario.QuestionarioAplicado;
import com.ufcg.sad.models.token.Token;
import com.ufcg.sad.services.disciplina.DisciplinaService;
import com.ufcg.sad.services.email.EmailService;
import com.ufcg.sad.services.token.TokenService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Set;

@Service
public class EnviarQuestionarioServiceImpl implements EnviarQuestionarioService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private DisciplinaService disciplinaService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private QuestionarioAplicadoService questionarioAplicadoService;

    private static final Logger logger =
            Logger.getLogger(EnviarQuestionarioService.class);

    @Override
    public void enviarEmail(List<Long> questionariosAplicados) throws EntidadeNotFoundException, EntidadeInvalidaException {
        List<QuestionarioAplicado> questionarios = questionarioAplicadoService.getListaDeQuestionariosAplicados(questionariosAplicados);
        for (QuestionarioAplicado questionarioAplicado : questionarios) {
            enviarEmail(questionarioAplicado);
        }
    }

    @Override
    public void enviarEmail(Long idQuestionarioAplicado) throws EntidadeNotFoundException, EntidadeInvalidaException {
        QuestionarioAplicado questionarioAplicado = questionarioAplicadoService.getQuestionarioAplicado(idQuestionarioAplicado);
        enviarEmail(questionarioAplicado);
    }

    private void enviarEmail(QuestionarioAplicado questionarioAplicado) throws EntidadeNotFoundException, EntidadeInvalidaException {
        Long idDisciplina = questionarioAplicado.getIdDisciplina();
        Disciplina disciplina = disciplinaService.getDisciplina(idDisciplina);

        Set<Matricula> matriculas = disciplina.getMatriculas();
        System.out.println("oi");
        for (Matricula matricula : matriculas) {
        	System.out.println(matricula.getAluno().getEmail());
            enviarEmail(matricula.getAluno(), questionarioAplicado, disciplina);
        }

        questionarioAplicadoService.atualizaQuestionarioAplicado(questionarioAplicado);
    }

    private void enviarEmail(Aluno aluno, QuestionarioAplicado questionarioAplicado, Disciplina disciplina) throws EntidadeNotFoundException, EntidadeInvalidaException {
        Token token = new Token(questionarioAplicado.getId(), aluno.getId());
        token = tokenService.criaToken(token);
        questionarioAplicado.addToken(token);

        try {
            emailService.enviarEmail(aluno.getEmail(), geraCorpoDoEmail(token, disciplina));
        } catch (MessagingException e) {
            StringBuilder stringBuilder = new StringBuilder();

            String erro = stringBuilder.append("Erro ao enviar email. ")
                    .append(questionarioAplicado.getId())
                    .append(",")
                    .append(disciplina.getId())
                    .append(",")
                    .append(aluno.getId())
                    .append(",")
                    .append(aluno.getEmail())
                    .toString();
            System.out.print(erro);
            logger.warn(erro);
        }
    }

    private String geraCorpoDoEmail(Token token, Disciplina disciplina) {
        StringBuilder sb = new StringBuilder();
        String nl = System.lineSeparator();
        sb.append("Olá! A cada semestre realizamos o processo de avaliação docente." + nl + nl);
        sb.append("Sua participação é importante. Cada professor receberá um resumo"
                + " de como os alunos votaram na sua turma bem como comentários que"
                + " forem registrados por você. IMPORTANTE, VOCÊ NÃO SERÁ IDENTIFICADO" + " NESTE PROCESSO!" + nl + nl);
        sb.append("Os resultados da avaliação docente ajudam o departamento a definir alterações nas alocações das"
                + " disciplinas, a identificar as principais áreas a serem trabalhadas e para poder cobrar por"
                + " melhorias perante a universidade." + nl + nl);
        sb.append("Pedimos que você avalie a disciplina: " + disciplina.getNome() + nl + nl);
        sb.append("Para isto, basta acessar o link: https://sad.splab.ufcg.edu.br/app/#/form/"
                + token.getId() + nl);
        sb.append("Importante: você só poderá votar uma única vez e o voto não poderá ser alterado após ser registrado.");
        return sb.toString();
    }
}
