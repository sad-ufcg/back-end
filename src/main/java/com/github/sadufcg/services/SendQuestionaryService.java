package com.github.sadufcg.services;

import com.github.sadufcg.pojo.Course;
import com.github.sadufcg.pojo.CourseStudent;

/**
 * Created by Antunes Dantas on 20/03/17.
 */
public interface SendQuestionaryService {

	void sendQuestionnaireStudent(CourseStudent cs);
	
    void sendQuestionnaire(Course c);

    void sendQuestionnaireForAllCourses();

}
