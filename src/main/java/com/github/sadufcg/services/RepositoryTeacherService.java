package com.github.sadufcg.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.sadufcg.pojo.Teacher;
import com.github.sadufcg.repositories.TeacherRepository;

/**
 * Implements the interface {@link TeacherService}
 *
 * @author Antunes Dantas
 */

@Service
public class RepositoryTeacherService implements TeacherService {

    private final TeacherRepository repository;

    @Autowired
    public RepositoryTeacherService(TeacherRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Teacher create(Teacher newTeacher) {
        return repository.save(newTeacher);
    }

    @Transactional
    public Teacher findById(String siape) {
        return repository.findOne(siape);
    }

    @Transactional
    public List<Teacher> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Teacher update(Teacher updatedTeacher) {
        return repository.save(updatedTeacher);
    }

    @Transactional
    public void delete(Teacher teacher) {
        repository.delete(teacher);
    }

    @Transactional
    public void delete(String siape) {
        Teacher teacher = repository.findOne(siape);
        repository.delete(teacher);
    }

}
