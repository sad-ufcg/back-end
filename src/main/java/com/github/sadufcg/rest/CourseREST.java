package com.github.sadufcg.rest;

import javax.validation.Valid;
import com.github.sadufcg.pojo.Course;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.github.sadufcg.services.CourseService;
import java.util.List;

/**
 * This controller provides the public API that is used to perform operations
 * for {@link Course}.
 *
 * @author Antunes Dantas
 */
@RestController
@RequestMapping("/course")
public class CourseREST {

    private final CourseService courseService;

    public CourseREST (CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * Create a new course.
     *
     * @param newCourse
     *            The information of the created course.
     * @return The information of the created course.
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    Course create(@RequestBody @Valid Course newCourse) {
        Course created = courseService.create(newCourse);
        return created;
    }

    /**
     * Finds a single course.
     *
     * @param id
     *            The id of the requested course.
     * @return The information of the requested course.
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    Course findById(@PathVariable("id") String id) {
        Course course = courseService.findById(id);
        return course;
    }

    /**
     * Finds all the course entries
     *
     * @return A list with all the courses
     */
    @RequestMapping(method = RequestMethod.GET)
    List<Course> findAll() {
        List<Course> courseEntries = courseService.findAll();
        return courseEntries;
    }

    /**
     * Updates the information of an existing course.
     *
     * @param updatedCourse
     *            The new information of the updated course.
     * @return The updated information of the updated course.
     */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    Course update(@PathVariable("id") String id, @RequestBody @Valid Course updatedCourse) {
        if (!id.equals(updatedCourse.getId())) {
            return null;
        } else {
            Course updated = courseService.update(updatedCourse);
            return updated;
        }
    }

    /**
     * Deletes a course entry.
     *
     * @param id
     *            of the course.
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    void delete(@PathVariable("id") String id) {
        courseService.delete(id);
    }

}
