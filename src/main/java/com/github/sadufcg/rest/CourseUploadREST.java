package com.github.sadufcg.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
@RequestMapping("/upload")
public class CourseUploadREST {

    private final CsvClassCreator courseCreatorService;

    @Autowired
    public CourseUploadREST(CsvClassCreator courseCreatorService) {
        this.courseCreatorService = courseCreatorService;
    }
	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST)
    HttpStatus uploadCourse(@RequestParam("file") MultipartFile file) throws Exception {
		courseCreatorService.createCourse(file);
		return HttpStatus.ACCEPTED;
	}
}