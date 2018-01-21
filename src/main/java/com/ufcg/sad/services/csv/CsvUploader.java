package com.ufcg.sad.services.csv;

import org.springframework.web.multipart.MultipartFile;

import com.ufcg.sad.exceptions.EntidadeInvalidaException;

/**
 * Interface que provê o serviço de cadastro de Disciplinas
 * através de um CSV.
 */
public interface CsvUploader {

    /**
     * Cria uma Disciplina de acordo com os dados do CSV.
     * O CSV está estruturado da seguinte maneira:
     *              nome da Disciplina, turma, semestre
     *              código siape do professor, nome do professor
     *              nome do Aluno1, sobrenome do Aluno1, email do Aluno1
     *              nome do Aluno2, sobrenome do Aluno2, email do Aluno2
     *				...
     *				nome do AlunoN, sobrenome do AlunoN, email do AlunoN
     */
    void cadastrarDisciplina(MultipartFile csv) throws EntidadeInvalidaException, Exception;

}
