package com.github.sadufcg.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * This service provides an auto registration of courses on the system.
 *
 * @author Antunes Dantas
 */
public interface CsvClassCreator {

    void createCourse(MultipartFile file);

}
