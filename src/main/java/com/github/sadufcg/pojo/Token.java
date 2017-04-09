package com.github.sadufcg.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
public class Token {

    @Column
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @OneToOne
    private CourseStudent courseStudent;

    public Token() {

    }

    public String getId() { return this.id; }

    public void setId(String id) { this.id = id; }

	public CourseStudent getCourseStudent() {
		return courseStudent;
	}

	public void setCourseStudent(CourseStudent courseStudent) {
		this.courseStudent = courseStudent;
	}

}