package com.github.sadufcg.services;

/**
 * Created by Antunes Dantas on 20/03/17.
 */
public interface SendQuestionaryService {

    void sendQuestionnaire(Long classId);

    void sendQuestionnaireForAllCourses();

}
