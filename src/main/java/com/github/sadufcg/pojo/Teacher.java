package com.github.sadufcg.pojo;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Teacher {

	@Id
	@Column
	private String siape;

	@Column
	private String name;

	@OneToMany(mappedBy = "teacher")
	private Set<Course> courses;

	public Teacher() {
	}

	public Teacher(String siape, String name) {
		this.siape = siape;
		this.name = name;
	}

	public String getSiape() {
		return siape;
	}

	public void setSiape(String siape) {
		this.siape = siape;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Course> getCourses() {
		return courses;
	}
	
}
