package com.github.sadufcg.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Antunes Dantas
 */
@Service
public class CsvClassCreatorImpl implements CsvClassCreator {

    CourseService courseService;
    TeacherService teacherService;


    public void createCourse(MultipartFile file) {

    }

}
