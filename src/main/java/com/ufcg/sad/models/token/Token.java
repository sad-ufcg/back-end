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

}
