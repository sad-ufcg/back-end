package com.github.sadufcg.repositories;

import com.github.sadufcg.pojo.Student;

import java.util.List;

/**
 * Created by antunesdantas on 28/02/17.
 */
public interface StudentRepository extends BaseRepository<Student, Long> {

    Student findOne(Long id);

    Student save(Student student);

    List<Student> findAll();

    void delete(Student student);

}
