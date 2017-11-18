package com.ufcg.sad.services.csv;

import org.springframework.web.multipart.MultipartFile;

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
     *              nome do Aluno, sobrenome do Aluno, email do Aluno
     */
    void cadastrarDisciplina(MultipartFile csv) throws Exception;

}
