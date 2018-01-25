package com.ufcg.sad.controllers;

import com.ufcg.sad.services.csv.CsvUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller responsável pelo upload de um CSV para cadastro de Disciplina.
 *
 * @author Antunes Dantas
 */
@RestController
@RequestMapping("/disciplinas/csv")
public class CsvUploaderController {

    @Autowired
    CsvUploader csvUploader;

    /**
     * Realiza o upload de um CSV para criação de Disciplinas.
     *
     * @param csv Arquivo CSV contendo os dados de cadastro da Disciplina
     *
     * @return Status HTTP informando o sucesso ou não do cadastramento.
     */
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity<Object> enviarCSV(@RequestParam("csv")MultipartFile[] arquivos) {
        try {
            for(MultipartFile arquivoCSV: arquivos) {
            	csvUploader.cadastrarDisciplina(arquivoCSV);
            }
            return new ResponseEntity<Object>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
