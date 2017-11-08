package com.ufcg.sad.models.questionario;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Entidade que representa uma questão
 * 
 * @author Lucas Silva
 */
@Entity
public class Questao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	@NotNull
	private String enunciado;
	
	@Column
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoResposta tipoResposta;
	
	@Column
	@NotNull
	private String comentario;
	
	/**
	 * Método para construir uma instância do tipo questão
	 * @param id
	 * @param enunciado
	 * @param tipoResposta (enum)
	 * @param comentario
	 */
	public Questao(Long id, String enunciado, TipoResposta tipoResposta, String comentario) {
		this.id = id;
		this.enunciado = enunciado;
		this.tipoResposta = tipoResposta;
		this.comentario = comentario;
	}
	
	/**
	 * Método para construir uma instância do tipo questão
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
	
	public TipoResposta getTipoResposta() {
		return tipoResposta;
	}
	
	public void setTipoResposta(TipoResposta tipoResposta) {
		this.tipoResposta = tipoResposta;
	}
	
	public String getComentario() {
		return comentario;
	}
	
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	@Override
	public int hashCode() {
		final int primo = 31;
		int resultado = 1;
		resultado = primo * resultado + ((comentario == null) ? 0 : comentario.hashCode());
		resultado = primo * resultado + ((enunciado == null) ? 0 : enunciado.hashCode());
		resultado = primo * resultado + ((id == null) ? 0 : id.hashCode());
		resultado = primo * resultado + ((tipoResposta == null) ? 0 : tipoResposta.hashCode());
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
		if (tipoResposta != outraQuestao.tipoResposta)
			return false;
		return true;
	}
}
