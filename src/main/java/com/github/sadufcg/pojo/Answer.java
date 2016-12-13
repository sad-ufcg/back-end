package com.github.sadufcg.pojo;

import javax.persistence.*;

@Entity
@Table
public class Answer {
	
	@Id
	@Column
	private Long id;

	@Column
	private Long question;
	
	@Column
	private String answerText;
	
	@Column
	private int choiceNumber;
	
	public Answer() {}
	
	public Answer(Long id, Long question, String answerText, int choiceNumber) {
		this.id = id;
		this.question = question;
		this.answerText = answerText;
		this.choiceNumber = choiceNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getQuestion() {
		return question;
	}

	public void setQuestion(Long question) {
		this.question = question;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public int getChoiceNumber() {
		return choiceNumber;
	}

	public void setChoiceNumber(int choiceNumber) {
		this.choiceNumber = choiceNumber;
	}
}
