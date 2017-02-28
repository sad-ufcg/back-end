package com.github.sadufcg.repositories;

import com.github.sadufcg.pojo.Course;
import java.util.List;

/**
 * This repository provides operations for (@link Course) objects.
 *
 * @author Antunes Dantas
 */
public interface CourseRepository extends BaseRepository<Course, String> {

    Course findOne(String id);

    Course save(Course course);

    List<Course> findAll();

    void delete(Course course);

}
