package com.github.sadufcg.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.sadufcg.pojo.Course;
import com.github.sadufcg.repositories.CourseRepository;
import java.util.List;

/**
 * Implements the interface (@link CourseService).
 *
 * @author Antunes Dantas.
 */
@Service
final class RespositoryCourseService implements CourseService{

    private final CourseRepository repository;

    @Autowired
    RespositoryCourseService(CourseRepository repository) { this.repository = repository; }

    @Transactional
    public Course create(Course newCourseEntry) {

        Course created = repository.save(newCourseEntry);
        return created;
    }

    @Transactional(readOnly = true)
    @Override
    public Course findById(String id) {
        Course courseEntry = findCourseEntryById(id);
        return courseEntry;
    }

    private Course findCourseEntryById(String id) {
        Course entry = repository.findOne(id);
        return entry;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Course> findAll() {
        List<Course> courseList = repository.findAll();
        return courseList;
    }

    @Transactional
    @Override
    public Course update(Course updatedCourse) {
        Course updated = repository.save(updatedCourse);
        return updated;
    }

    @Transactional
    public void delete(String id) {
        Course courseEntry = findCourseEntryById(id);
        repository.delete(courseEntry);
    }

}
