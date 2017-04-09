package com.github.sadufcg.repositories;

import java.util.List;

import com.github.sadufcg.pojo.Course;
import com.github.sadufcg.pojo.CourseStudent;
import com.github.sadufcg.pojo.Student;

/**
 * This repository provides operations for (@link Course) objects.
 *
 * @author Antunes Dantas
 */
public interface CourseStudentRepository extends BaseRepository<CourseStudent, Long> {

    List<CourseStudent> findBy(Course course);

    List<CourseStudent> findBy(Student student);
    
    CourseStudent save(CourseStudent course);

    void delete(CourseStudent course);

}
