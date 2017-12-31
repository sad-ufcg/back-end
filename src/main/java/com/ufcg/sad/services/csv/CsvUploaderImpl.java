package com.ufcg.sad.services.csv;

import com.ufcg.sad.exceptions.EntidadeNotFoundException;
import com.ufcg.sad.models.aluno.Aluno;
import com.ufcg.sad.models.disciplina.Disciplina;
import com.ufcg.sad.models.professor.Professor;
import com.ufcg.sad.services.aluno.AlunoService;
import com.ufcg.sad.services.disciplina.DisciplinaService;
import com.ufcg.sad.services.professor.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

@Service
public class CsvUploaderImpl implements CsvUploader {

    @Autowired
    DisciplinaService disciplinaService;

    @Autowired
    ProfessorService professorService;

    @Autowired
    AlunoService alunoService;

    private final String LINE_SEPARATOR = ",";

    @Override
    public void cadastrarDisciplina(MultipartFile csv) throws Exception {
        Scanner leitor = new Scanner(new ByteArrayInputStream(csv.getBytes()));

        String dadosDaDisciplina = leitor.nextLine();
        String dadosDoProfessor = leitor.nextLine();
        Disciplina disciplina = criaDisciplina(dadosDaDisciplina);
        Professor professor = criaProfessor(dadosDoProfessor);

        disciplina.setProfessorId(professor.getId());
        professor.addDisciplina(disciplina);

        while (leitor.hasNext()) {
            Aluno aluno = criaAluno(leitor.nextLine());
            aluno.adicionarDisciplina(disciplina);

            alunoService.atualizarAluno(aluno);
        }

        professorService.atualizarProfessor(professor);
        disciplinaService.atualizarDisciplina(disciplina);

        leitor.close();
    }

    /**
     * Método auxiliar que cria uma Disciplina a partir dos dados de uma linha do CSV.
     *
     * @param entrada String contendo a linha com os dados do CSV.
     *
     * @return Um objeto Disciplina.
     * @throws EntidadeNotFoundException.
     */
    private Disciplina criaDisciplina(String entrada) throws EntidadeNotFoundException {
        String[] dadosDisciplina = entrada.split(LINE_SEPARATOR);
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(dadosDisciplina[0]);
        disciplina.setTurma(Integer.parseInt(dadosDisciplina[1]));
        disciplina.setSemestre(dadosDisciplina[2]);

        disciplina = disciplinaService.cadastrarDisciplina(disciplina);
        return disciplina;
    }

    /**
     * Método auxiliar que cria um Professor a partir dos dados de uma linha do CSV.
     *
     * @param entrada String contendo a linha com os dados do CSV.
     *
     * @return Um objeto Professor.
     */
    private Professor criaProfessor(String entrada) {
        String[] dadosProfessor = entrada.split(LINE_SEPARATOR);
        Professor professor;

        try {
            professor = professorService.procurarProfessorPeloSiape(dadosProfessor[0]);
        } catch (EntidadeNotFoundException e) {
            professor = new Professor();
            professor.setSiape(dadosProfessor[0]);
            professor.setNome(dadosProfessor[1]);
            professor = professorService.criarProfessor(professor);
        }

        return professor;
    }

    /**
     * Método auxiliar que cria um Aluno a partir dos dados de uma linha do CSV.
     *
     * @param entrada String contendo a linha com os dados do CSV.
     *
     * @return Um objeto Aluno.
     */
    private Aluno criaAluno(String entrada) {
        String[] dadosAluno = entrada.split(LINE_SEPARATOR);
        Aluno aluno = alunoService.procurarPorEmail(dadosAluno[2]);
        if (aluno == null) {
            aluno = new Aluno();
            aluno.setNome(dadosAluno[0]);
            aluno.setSobrenome(dadosAluno[1]);
            aluno.setEmail(dadosAluno[2]);
            aluno = alunoService.criarAluno(aluno);
        }

        return aluno;
    }

}
