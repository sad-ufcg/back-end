package com.github.sadufcg.services;

/**
 * Created by Antunes Dantas on 20/03/17.
 */
public interface SendQuestionaryService {

    void sendQuestionnaire(String classId);

    void sendQuestionnaireForAllCourses();

}
