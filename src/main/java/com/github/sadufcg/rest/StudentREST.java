package com.github.sadufcg.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.sadufcg.pojo.Student;
import com.github.sadufcg.services.StudentService;

/**
 * This controller provides the public API that is used to perform operations
 * for {@link Student}.
 *
 * @author Antunes Dantas
 */
@RestController
@RequestMapping("/student")
public class StudentREST {

    private final StudentService studentService;

    @Autowired
    StudentREST(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Create a new student.
     *
     * @param newStudent
     *            The information of the created student.
     * @return The information of the created student.
     */
    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    Student create(@RequestBody @Valid Student newStudent) {
        Student created = studentService.create(newStudent);
        return created;
    }

    /**
     * Finds a single student.
     *
     * @param id
     *            The id of the requested student.
     * @return The information of the requested student.
     */
    @CrossOrigin
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    Student findById(@PathVariable("id") Long id) {
        Student question = studentService.findById(id);
        return question;
    }

    /**
     * Finds all the question entries
     *
     * @return A list with all the questions
     */
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    List<Student> findAll() {
        List<Student> studentEntries = studentService.findAll();
        return studentEntries;
    }

    /**
     * Updates the information of an existing student.
     *
     * @param updatedStudent
     *            The new information of the updated student.
     * @return The updated student.
     */
    @CrossOrigin
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    Student update(@PathVariable("id") Long id, @RequestBody @Valid Student updatedStudent) {
        if (id != updatedStudent.getId()) {
            return null;
        } else {
            Student updated = studentService.update(updatedStudent);
            return updated;
        }
    }

    /**
     * Deletes a student entry.
     *
     * @param id
     *            of the student.
     */
    @CrossOrigin
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    void delete(@PathVariable("id") Long id) {
        studentService.delete(id);
    }
}
