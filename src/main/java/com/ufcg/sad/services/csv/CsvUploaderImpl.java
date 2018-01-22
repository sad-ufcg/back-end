package com.ufcg.sad.services.csv;

import com.ufcg.sad.exceptions.EntidadeInvalidaException;
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
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

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
    public void cadastrarDisciplina(MultipartFile csv) throws EntidadeInvalidaException, Exception {
        Scanner leitor = new Scanner(new ByteArrayInputStream(csv.getBytes()));

        String dadosDaDisciplina = leitor.nextLine();
        String dadosDoProfessor = leitor.nextLine();

        Set<Aluno> alunos = new HashSet<>();
        while (leitor.hasNext()) {
            alunos.add(criaAluno(leitor.nextLine()));
        }

        Disciplina disciplina = criaDisciplina(dadosDaDisciplina);
        Professor professor = criaProfessor(dadosDoProfessor);

        disciplina.setProfessorId(professor.getId());
        professor.addDisciplina(disciplina);
        
        for(Aluno aluno: alunos) {
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
     * @throws EntidadeInvalidaException 
     * @throws EntidadeNotFoundException.
     */
    private Disciplina criaDisciplina(String entrada) throws EntidadeNotFoundException, EntidadeInvalidaException {
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
     * @throws EntidadeNotFoundException 
     * @throws EntidadeInvalidaException 
     */
    private Professor criaProfessor(String entrada) throws EntidadeInvalidaException, EntidadeNotFoundException {
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
     * @throws EntidadeInvalidaException 
     * @throws EntidadeNotFoundException 
     */
    private Aluno criaAluno(String entrada) throws EntidadeInvalidaException, EntidadeNotFoundException {
        String[] dadosAluno = entrada.split(LINE_SEPARATOR);
        Aluno aluno;
        try {
        	aluno = alunoService.procurarPorEmail(dadosAluno[2]);
        } catch(EntidadeNotFoundException entidadeNotFoundException) {
            aluno = new Aluno();
            aluno.setNome(dadosAluno[0]);
            aluno.setSobrenome(dadosAluno[1]);
            aluno.setEmail(dadosAluno[2]);
            try {
            	aluno = alunoService.criarAluno(aluno);
            } catch(Exception e) {
            	throw new EntidadeInvalidaException("Algum dos alunos apresenta dados inválidos.");
            }
        }
        return aluno;
    }

}
