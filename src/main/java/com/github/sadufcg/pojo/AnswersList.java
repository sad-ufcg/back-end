package com.github.sadufcg.pojo;

import javax.persistence.*;
import java.util.List;

/**
 * Created by epol-antunes on 31/03/17.
 */

@Table
@Entity
public class AnswersList {

    @Id
    @Column
    private Long id;

    @Column
    private String courseId;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers;

    public AnswersList() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
