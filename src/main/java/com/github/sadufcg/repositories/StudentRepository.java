package com.github.sadufcg.repositories;

import com.github.sadufcg.pojo.Student;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by antunesdantas on 28/02/17.
 */
public interface StudentRepository extends BaseRepository<Student, Long> {

    Student findOne(Long id);

    Student save(Student student);

    List<Student> findAll();

    void delete(Student student);

    @Query("select s from Student s where s.email = ?1")
    Student findByEmail(String emailAdress);

}
