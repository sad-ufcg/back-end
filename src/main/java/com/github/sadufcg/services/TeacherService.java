package com.github.sadufcg.services;

import com.github.sadufcg.pojo.Teacher;
import java.util.List;

/**
 * This service provides operations for {@link Teacher} objects.
 *
 * @author Antunes Dantas
 */
public interface TeacherService {

    /**
     * Creates a new teacher entry.
     *
     * @param newTeacher
     *            New teacher to create.
     * @return The teacher created.
     */
    Teacher create(Teacher newTeacher);

    /**
     * Finds a teacher entry by it' id.
     *
     * @param siape
     *            the unique code of a teacher.
     * @return The requested object.
     */
    Teacher findById(String siape);

    /**
     * Gets all the teacher's entries.
     *
     * @return A list containing all the teacher's objects.
     */
    List<Teacher> findAll();

    /**
     * Update a teacher entry on the system.
     *
     * @param updatedTeacher
     *             The updated object
     * @return the updated object
     */
    Teacher update(Teacher updatedTeacher);

    /**
     * Deletes a teacher entry
     *
     * @param teacher object that will be deleted
     */
    void delete(Teacher teacher);

}
