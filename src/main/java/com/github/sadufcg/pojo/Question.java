package com.github.sadufcg.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	private String comentario;

    @ManyToOne
    @JoinColumn(name = "questionare_id")
    private Questionnaire questionnaire;
	
	public Question() {}
	
	public Question(Long id, String enunciado, AnswerType tipoResposta,
			String comentario, Questionnaire questionnaire) {
		this.id = id;
		this.enunciado = enunciado;
		this.tipoResposta = tipoResposta;
		this.comentario = comentario;
		this.questionnaire = questionnaire;
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

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}
	
}
