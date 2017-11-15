package com.ufcg.sad.models.questionario;

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

/**
 * Entidade que representa um questionário
 * 
 * @author Lucas Silva
 */
@Entity
public class Questionario implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "questionario_id")
	private Long id;
	
	@Column
	private String nome;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Questao> questoes;

	/**
	 * Método para construir uma instância do tipo questionário
	 * @param id
	 * @param nome
	 * @param questoes
	 */
	public Questionario(Long id, String nome, Set<Questao> questoes) {
		this.id = id;
		this.nome = nome;
		this.questoes = questoes;
	}
	
	/**
	 * Método para construir uma instância do tipo questionário
	 */
	public Questionario() { 
		this.questoes = new HashSet<Questao>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(Set<Questao> questoes) {
		this.questoes = questoes;
	}

	@Override
	public int hashCode() {
		final int primo = 31;
		int resultado = 1;
		resultado = primo * resultado + ((id == null) ? 0 : id.hashCode());
		resultado = primo * resultado + ((nome == null) ? 0 : nome.hashCode());
		resultado = primo * resultado + ((questoes == null) ? 0 : questoes.hashCode());
		return resultado;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Questionario outroQuestionario = (Questionario) obj;
		if (id == null) {
			if (outroQuestionario.id != null)
				return false;
		} else if (!id.equals(outroQuestionario.id))
			return false;
		if (nome == null) {
			if (outroQuestionario.nome != null)
				return false;
		} else if (!nome.equals(outroQuestionario.nome))
			return false;
		if (questoes == null) {
			if (outroQuestionario.questoes != null)
				return false;
		} else if (!questoes.equals(outroQuestionario.questoes))
			return false;
		return true;
	}
}
