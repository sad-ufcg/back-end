package com.github.sadufcg.rest;

import com.github.sadufcg.pojo.Teacher;
import com.github.sadufcg.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * This controller provides the public API that is used to perform operations
 * for {@link Teacher)
 *
 * @author Antunes Dantas
 */

@RestController
@RequestMapping("/teacher")
public class TeacherREST {

    private final TeacherService service;

    @Autowired
    TeacherREST(TeacherService service) {
        this.service = service;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    Teacher create(@RequestBody Teacher newTeacher) {
        return service.create(newTeacher);
    }

    @CrossOrigin
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    Teacher findOne(@PathVariable("id") String id) {
        return service.findById(id);
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
            return service.update(updatedTeacher);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    void delete(@PathVariable("id") String id) {
            Teacher teacher = service.findById(id);
            if (teacher != null) {
                service.delete(teacher);
        }
    }

}
