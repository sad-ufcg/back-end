package com.ufcg.sad.models.util;

import java.util.Date;
import java.util.List;

import com.ufcg.sad.models.aluno.Aluno;
import com.ufcg.sad.models.disciplina.Disciplina;
import com.ufcg.sad.models.matricula.Matricula;
import com.ufcg.sad.models.opcao.Opcao;
import com.ufcg.sad.models.professor.Professor;
import com.ufcg.sad.models.questao.Questao;
import com.ufcg.sad.models.questao.TipoQuestao;

public final class Utils {

    public static final int TAMANHO_MAX_STRING = 255;


    /**
     * Cria uma matricula default. Serve unicamente para testes!
     * @param nomeAluno nome do aluno
     * @param nomeDisciplina nome da disciplina
     * @return uma matricula default
     */
    public static Matricula createMatriculaTest(String nomeAluno, String nomeDisciplina) {
        Aluno aluno = new Aluno();
        aluno.setNome(nomeAluno);
        aluno.setEmail("algumemail@ccc.ufcg.edu.br");

        Disciplina disciplina = new Disciplina();
        disciplina.setNome(nomeDisciplina);
        disciplina.setTurma(2);
        disciplina.setSemestre("2017.2");
        Matricula matricula = new Matricula(aluno, disciplina);

        return matricula;
    }
  
    
    public static Questao createQuestaoTest(String enunciado, Professor autor, Date dataCriacao, String comentario, List<Opcao> opcoes, TipoQuestao tipo) {
    	Questao questao = new Questao();
    	
    	questao.setEnunciado(enunciado);
    	questao.setAutor(autor);
    	questao.setDataCriacao(dataCriacao);
    	questao.setDataUltimaEdicao(dataCriacao);
    	questao.setComentario(comentario);
    	questao.setOpcoes(opcoes);
    	questao.setTipoQuestao(tipo);
    	
    	return questao;
    }
    
}