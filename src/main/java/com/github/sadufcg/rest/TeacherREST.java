package com.github.sadufcg.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.github.sadufcg.pojo.Teacher;
import com.github.sadufcg.repositories.TeacherRepository;

/**
 * This controller provides the public API that is used to perform operations
 * for {@link Teacher)
 *
 * @author Antunes Dantas
 */

//@RestController
//@RequestMapping("/teacher")
public class TeacherREST {

    private final TeacherRepository service;

    @Autowired
    TeacherREST(TeacherRepository service) {
        this.service = service;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    Teacher create(@RequestBody Teacher newTeacher) {
        return service.save(newTeacher);
    }

    @CrossOrigin
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    Teacher findOne(@PathVariable("id") String id) {
        return service.findOne(id);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    List<Teacher> findAll() {
        return service.findAll();
    }

    @CrossOrigin
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    Teacher update(@PathVariable("id") String id, @RequestBody Teacher updatedTeacher) {
        if (!id.equals(updatedTeacher.getSiape())) {
            return null;
        } else {
            return service.save(updatedTeacher);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    void delete(@PathVariable("id") String id) {
            Teacher teacher = service.findOne(id);
            if (teacher != null) {
                service.delete(teacher);
        }
    }

}
