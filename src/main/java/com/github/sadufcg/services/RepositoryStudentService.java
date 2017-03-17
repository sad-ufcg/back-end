package com.github.sadufcg.services;

import com.github.sadufcg.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.sadufcg.pojo.Student;
import java.util.List;

/**
 * Created by antunesdantas on 28/02/17.
 */
@Service
final class RepositoryStudentService implements StudentService {

    private StudentRepository repository;

    @Autowired
    void RespositoryStudentService(StudentRepository repository) { this.repository = repository; }

    @Transactional
    public Student create(Student newStudentEntry) {
        Student created = repository.save(newStudentEntry);
        return created;
    }

    @Transactional(readOnly = true)
    @Override
    public Student findById(Long id) {
        Student studentEntry = findCourseEntryById(id);
        return studentEntry;
    }

    private Student findCourseEntryById(Long id) {
        Student entry = repository.findOne(id);
        return entry;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Student> findAll() {
        List<Student> studentList = repository.findAll();
        return studentList;
    }

    @Transactional
    @Override
    public Student update(Student updatedStudent) {
        Student updated = repository.save(updatedStudent);
        return updated;
    }

    @Transactional
    public void delete(Long id) {
        Student studentEntry = findCourseEntryById(id);
        repository.delete(studentEntry);
    }

    @Transactional
    public Student findByEmail(String emailAdress) {
        return repository.findByEmail(emailAdress);
    }

}
