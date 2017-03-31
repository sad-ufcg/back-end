package com.github.sadufcg.pojo;

import javax.persistence.*;
import java.util.List;

/**
 * @author Antunes Dantas on 31/03/17.
 */
@Table
@Entity
public class Questionnaire {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private List<Long> questionnaire;

    public Questionnaire() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(List<Long> questionnaire) {
        this.questionnaire = questionnaire;
    }

    public void addQuestion(Long questionId) {
        this.questionnaire.add(questionId);
    }

    public void removeQuestion(Long questionId) {
        this.questionnaire.remove(questionId);
    }
}
