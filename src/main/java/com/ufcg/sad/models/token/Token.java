package com.ufcg.sad.models.token;

import com.ufcg.sad.models.questionario.QuestionarioAplicado;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entidade de que representa o token de autenticação de um questionário
 *
 * Created by Sampaio on 16/11/17.
 */
@Entity
@Table
public class Token {

    @Id
    @Column
    private String id;

    @OneToOne(mappedBy = "token",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private QuestionarioAplicado questionarioAplicado;

    public Token(QuestionarioAplicado questionarioAplicado) {
        this.id = UUID.randomUUID().toString();
        this.questionarioAplicado = questionarioAplicado;
    }

    public Token(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public QuestionarioAplicado getQuestionarioAplicado() {
        return questionarioAplicado;
    }

    public void setQuestionarioAplicado(QuestionarioAplicado questionarioAplicado) {
        this.questionarioAplicado = questionarioAplicado;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((questionarioAplicado == null) ? 0 : questionarioAplicado.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Token other = (Token) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (questionarioAplicado == null) {
			if (other.questionarioAplicado != null)
				return false;
		} else if (!questionarioAplicado.equals(other.questionarioAplicado))
			return false;
		return true;
	}
}
