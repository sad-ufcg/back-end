package com.github.sadufcg.pojo;

import javax.persistence.*;
import java.util.Set;
import javax.persistence.CascadeType;

@Entity
@Table
public class Course {

	@Id
	@Column
	private String id;

	@Column
	private String name;

	@Column
	private int courseNumber;

	@Column
	private Teacher teacher;

	@Column
	private String semester;

	@Column
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<CourseStudent> courseStudent;

	public Course() {}

	public Course(String id, String name, int courseNumber, Teacher teacher,
                  String semester, Set<CourseStudent> courseStudent) {
	    this.id = id;
	    this.name = name;
	    this.courseNumber = courseNumber;
	    this.teacher = teacher;
	    this.semester = semester;
	    this.courseStudent = courseStudent;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public String getId() { return this.id; }

    public void setId(String id) { this.id = id; }

    public int getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(int courseNumber) {
		this.courseNumber = courseNumber;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public Set<CourseStudent> getCourseStudent() {
		return courseStudent;
	}

	public void setCourseStudent(Set<CourseStudent> courseStudent) {
		this.courseStudent = courseStudent;
	}
}
