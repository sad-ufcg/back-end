package com.github.sadufcg.pojo;

import javax.persistence.*;

@Entity
@Table
public class Teacher {

	@Id
	private String siape;

	@Column
	private String name;

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
}
