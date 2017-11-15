package com.ufcg.sad.models.questao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.validation.constraints.NotNull;

import com.ufcg.sad.models.professor.Professor;

/**
 * Entidade que representa uma questão genérica.
 * 
 * @author Lucas Silva, Marianne Linhares
 */
@Entity
@Inheritance
public class Questao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	@NotNull
	private String enunciado;
	
	@Column
	private String comentario;
	
	@Column
	@NotNull
	private Professor autor;
	
	@Column
	@NotNull
	private Date dataCriacao;
	
	@Column
	@NotNull
	private Date dataUltimaEdicao;
	

	/**
	 * Método para construir uma instância do tipo questão
	 * @param id
	 * @param enunciado
	 * @param autor
	 * @param dataDeCriacao
	 * @param comentario
	 */
	public Questao(Long id, String enunciado, Professor autor, Date dataCriacao, String comentario) {
		this.id = id;
		this.enunciado = enunciado;
		this.autor = autor;
		this.dataCriacao = dataCriacao;
		this.comentario = comentario;
		// A data da última edição é inicialmente a data de criação.
		this.dataUltimaEdicao = dataCriacao;
	}


	public Questao(String enunciado, TipoResposta tipoResposta, String comentario) {
		this.enunciado = enunciado;
		this.tipoResposta = tipoResposta;
		this.comentario = comentario;
	}


	/**
     * Construtor padrão para o Hibernate.
     */
	public Questao() { }
	
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
	
	public String getComentario() {
		return comentario;
	}
	
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	public Professor getAutor() {
		return autor;
	}
	
	public Date getDataCriacao() {
		return dataCriacao;
	}
	
	public Date getDataUltimaEdicao() {
		return dataUltimaEdicao;
	}
	
	public void setDataUltimaEdicao(Date dataEdicao) {
		this.dataUltimaEdicao = dataEdicao;
	}
	
	@Override
	public int hashCode() {
		final int primo = 31;
		int resultado = 1;
		resultado = primo * resultado + ((comentario == null) ? 0 : comentario.hashCode());
		resultado = primo * resultado + ((enunciado == null) ? 0 : enunciado.hashCode());
		resultado = primo * resultado + ((id == null) ? 0 : id.hashCode());
		return resultado;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Questao))
			return false;
		Questao outraQuestao = (Questao) obj;
		if (comentario == null) {
			if (outraQuestao.comentario != null)
				return false;
		} else if (!comentario.equals(outraQuestao.comentario))
			return false;
		if (enunciado == null) {
			if (outraQuestao.enunciado != null)
				return false;
		} else if (!enunciado.equals(outraQuestao.enunciado))
			return false;
		if (id == null) {
			if (outraQuestao.id != null)
				return false;
		} else if (!id.equals(outraQuestao.id))
			return false;
		return true;
	}
}
