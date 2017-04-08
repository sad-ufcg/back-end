package com.github.sadufcg.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.sadufcg.services.CsvClassCreator;

/**
 * Created by Antunes Dantas on 17/03/17.
 */

@RestController
@RequestMapping("/create/upload")
public class CreateCourseREST {

    private final CsvClassCreator service;

    @Autowired
    public CreateCourseREST(CsvClassCreator service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    void createCourse(@RequestParam("file") MultipartFile file) throws Exception {
    	service.createCourse(file);
    }

}
