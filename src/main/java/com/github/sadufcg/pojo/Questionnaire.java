package com.github.sadufcg.pojo;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

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

    @ElementCollection
    @CollectionTable(name="Questionnaires", joinColumns=@JoinColumn(name="questionnaire_id"))
    @Column(name="questionnaire")
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
