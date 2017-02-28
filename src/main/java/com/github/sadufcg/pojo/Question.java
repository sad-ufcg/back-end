package com.github.sadufcg.pojo;

import javax.persistence.*;

@Entity
@Table
public class Question {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column
	private Long id;

	@Column
	private String enunciado;

	@Column
	private AnswerType tipoResposta;

	@Column
	private Boolean comentario;

	public Question() {}
	
	public Question(Long id, String enunciado, AnswerType tipoResposta,
			Boolean comentario) {
		this.id = id;
		this.enunciado = enunciado;
		this.tipoResposta = tipoResposta;
		this.comentario = comentario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public AnswerType getTipoResposta() {
		return tipoResposta;
	}

	public void setTipoResposta(AnswerType tipoResposta) {
		this.tipoResposta = tipoResposta;
	}

	public Boolean getComentario() {
		return comentario;
	}

	public void setComentario(Boolean comentario) {
		this.comentario = comentario;
	}
}
