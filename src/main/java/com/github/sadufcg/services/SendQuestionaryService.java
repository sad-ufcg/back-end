package com.github.sadufcg.services;

import com.github.sadufcg.pojo.Course;

/**
 * Created by Antunes Dantas on 20/03/17.
 */
public interface SendQuestionaryService {

    void sendQuestionnaire(Course c);

    void sendQuestionnaireForAllCourses();

}
