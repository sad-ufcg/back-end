package com.github.sadufcg.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Long id;

	@Column
	@NotNull
	private String name;

	@Column
	@NotNull
	private int number;

    @ManyToOne
    @JoinColumn(name = "siape")
	private Teacher teacher;

	@Column
	@NotNull
	private String semester;

	public Course() {}

	public Course(String name, int number, String semester, Teacher teacher) {
		this.name = name;
		this.number = number;
		this.semester = semester;
		this.teacher = teacher;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
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

	public static String getId(String name, int number, String semester, Teacher teacher) {
		return name + "__" + number + "__" + semester + "__" + teacher.getSiape();
	}

	@Override
	public String toString() {
		return name + " - " + number + " Professor: " + teacher.getName() + " - " + semester;
	}

	

}
