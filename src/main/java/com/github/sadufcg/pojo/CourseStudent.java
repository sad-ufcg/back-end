package com.github.sadufcg.pojo;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Antunes Dantas
 */

@Table
public class CourseStudent {

    private Student student;
    private Course course;

    public CourseStudent() {
        this.student = new Student();
        this.course = new Course();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
