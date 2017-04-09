package com.github.sadufcg.repositories;

import java.util.List;

import com.github.sadufcg.pojo.Teacher;

/**
 * This repository provides operations for {@link Teacher} objects.
 *
 * @author Antunes Dantas.
 */
public interface TeacherRepository extends BaseRepository<Teacher, String> {

    Teacher findOne(String siape);

    List<Teacher> findAll();

    Teacher save(Teacher teacher);

    void delete(Teacher teacher);

}
