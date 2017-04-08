package com.github.sadufcg.services;

import java.util.List;

import com.github.sadufcg.pojo.Course;

/**
 * This service provides operations for {@link Course} objects.
 *
 * @author Antunes Dantas
 */
public interface CourseService {

    /**
     * Creates a new course entry.
     *
     * @param newCourse
     *            The information of a new course to create.
     * @return The course created.
     */
    Course create(Course newCourse);

    /**
     * Finds a course by using the id.
     *
     * @param id
     *            The id of the wanted course.
     * @return The information of the requested course.
     */
    Course findById(String id);

    /**
     * Updates the information of an existing course.
     *
     * @param updatedCourse
     *            The new information of an existing course.
     * @return The information of the updated course.
     */
    Course update(Course updatedCourse);

    /**
     * Find all courses
     * @return A list with all the courses
     */
    List<Course> findAll();

    /**
     * Deletes a course entry from the database.
     *
     * @param id of the course which will be deleted
     */
    void delete(String id);

}
