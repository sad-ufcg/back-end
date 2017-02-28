package com.github.sadufcg.services;

import com.github.sadufcg.pojo.Student;

import java.util.List;

/**
 * This service provides operations for {@link Student} objects.
 *
 * @author Antunes Dantas
 */
public interface StudentService {

    /**
     * Creates a new student entry.
     *
     * @param newStudent
     *            The information of a new student to create.
     * @return The student created.
     */
    Student create(Student newStudent);

    /**
     * Finds a student by using the id.
     *
     * @param id
     *            The id of the wanted student.
     * @return The information of the requested student.
     */
    Student findById(Long id);

    /**
     * Updates the information of an existing student.
     *
     * @param updatedStudent
     *            The new information of an existing student.
     * @return The information of the updated student.
     */
    Student update(Student updatedStudent);

    /**
     * Find all students
     * @return A list with all the students
     */
    List<Student> findAll();

    /**
     * Deletes a student entry from the database.
     *
     * @param id of the student which will be deleted
     */
    void delete(Long id);

}
