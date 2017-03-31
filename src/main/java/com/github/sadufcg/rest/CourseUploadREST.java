package com.github.sadufcg.rest;

import com.github.sadufcg.services.CsvClassCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Antunes Dantas on 17/03/17.
 */

@RestController
@CrossOrigin
@RequestMapping("/upload")
public class CourseUploadREST {

    private final CsvClassCreator courseCreatorService;

    @Autowired
    public CourseUploadREST(CsvClassCreator courseCreatorService) {
        this.courseCreatorService = courseCreatorService;
    }

    HttpStatus uploadCourse(@RequestParam("file") MultipartFile file) {

        try {
            courseCreatorService.createCourse(file);
            return HttpStatus.ACCEPTED;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }

}
