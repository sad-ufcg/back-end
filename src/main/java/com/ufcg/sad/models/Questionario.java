package com.ufcg.sad.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Questionario implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "questionario_id")
	private Long id;
	
	@Column
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Questao> questoes;

	public Questionario(Long id, String name, Set<Questao> questoes) {
		this.id = id;
		this.name = name;
		this.questoes = questoes;
	}
	
	public Questionario() { 
		this.questoes = new HashSet<Questao>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(Set<Questao> questoes) {
		this.questoes = questoes;
	}
	
	
}
