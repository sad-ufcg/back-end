package com.ufcg.sad.models.token;


import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @Column
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Long idQuestionarioAplicado;

    @Column
    @NotNull
	private Long idAluno;

    public Token(Long idQuestionarioAplicado, Long idAluno) {
        this.id = UUID.randomUUID().toString();
        this.idQuestionarioAplicado = idQuestionarioAplicado;
        this.idAluno = idAluno;
    }

    public Token(Long idQuestionarioAplicado) {
        this.id = UUID.randomUUID().toString();
        this.idQuestionarioAplicado = idQuestionarioAplicado;
    }

    public Token(){
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getIdQuestionarioAplicado() {
        return idQuestionarioAplicado;
    }

    public void setIdQuestionarioAplicado(Long idQuestionarioAplicado) {
        this.idQuestionarioAplicado = idQuestionarioAplicado;
    }

	public Long getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idQuestionarioAplicado == null) ? 0 : idQuestionarioAplicado.hashCode());
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
		if (idQuestionarioAplicado == null) {
			if (other.idQuestionarioAplicado != null)
				return false;
		} else if (!idQuestionarioAplicado.equals(other.idQuestionarioAplicado))
			return false;
		return true;
	}
}
