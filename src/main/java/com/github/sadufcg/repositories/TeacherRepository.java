package com.github.sadufcg.repositories;

import com.github.sadufcg.pojo.Teacher;
import java.util.List;

/**
 * This repository provides operations for {@link Teacher} objects.
 *
 * @author Antunes Dantas.
 */
public interface TeacherRepository extends BaseRepository<Teacher, String> {

    Teacher findOne(String id);

    List<Teacher> findAll();

    Teacher save(Teacher teacher);

    void delete(Teacher teacher);

}
