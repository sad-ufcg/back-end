package com.github.sadufcg.pojo;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Token {

    @Column
    @Id
    private String id;

    @OneToOne
    private Course course;

    public Token() {
    	this.id = UUID.randomUUID().toString();
    }

    public Token(Course course) {
    	this();
    	this.course = course;
    }
    
    public String getId() { return this.id; }

    public void setId(String id) { this.id = id; }

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}