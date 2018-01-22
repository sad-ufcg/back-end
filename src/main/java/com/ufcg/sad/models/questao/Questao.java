package com.ufcg.sad.models.questao;

import static com.ufcg.sad.models.util.Utils.TAMANHO_MAX_STRING;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ufcg.sad.models.opcao.Opcao;
import com.ufcg.sad.models.professor.Professor;

/**
 * Entidade que representa uma questão genérica.
 * 
 * @author Lucas Silva, Marianne Linhares
 */
@Entity
@Table
public class Questao implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int MIN_ESCOLHA_SIMPLES = 1;
	public static final int MAX_ESCOLHA_SIMPLES = 5;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@NotNull
    @Length(max = TAMANHO_MAX_STRING)
	private String enunciado;
	
	@Column
    @Length(max = TAMANHO_MAX_STRING)
	private String comentario;
	
	@OneToOne(cascade = CascadeType.REFRESH)
	private Professor autor;
	
	@Column
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataCriacao;
	
	@Column
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataUltimaEdicao;
	
	@Column
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoQuestao tipoQuestao;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Opcao> opcoes;

	/**
	 * Método para construir uma instância do tipo questão
	 * @param id
	 * @param enunciado
	 * @param autor
	 * @param dataCriacao
	 * @param comentario
	 * @param resposta
	 */
	public Questao(Long id, String enunciado, Professor autor, Date dataCriacao, Date dataUltimaEdicao, String comentario, List<Opcao> opcoes, TipoQuestao tipoQuestao) {
		this.id = id;
		this.enunciado = enunciado;
		this.autor = autor;
		this.dataCriacao = dataCriacao;
		this.dataUltimaEdicao = dataUltimaEdicao;
		this.comentario = comentario;
		this.opcoes = opcoes;
		this.tipoQuestao = tipoQuestao;
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
	
	public void setAutor(Professor autor) {
		this.autor = autor;
	}
	
	public Date getDataCriacao() {
		return dataCriacao;
	}
	
	public void setDataCriacao(Date data) {
		this.dataCriacao = data;
	}

	public Date getDataUltimaEdicao() {
		return dataUltimaEdicao;
	}
	
	public void setDataUltimaEdicao(Date dataEdicao) {
		this.dataUltimaEdicao = dataEdicao;
	}
	
	public TipoQuestao getTipoQuestao() {
		return tipoQuestao;
	}

	public void setTipoQuestao(TipoQuestao tipoQuestao) {
		this.tipoQuestao = tipoQuestao;
	}

	public List<Opcao> getOpcoes() {
		return opcoes;
	}

	public void setOpcoes(List<Opcao> opcoes) {
		this.opcoes = opcoes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autor == null) ? 0 : autor.hashCode());
		result = prime * result + ((comentario == null) ? 0 : comentario.hashCode());
		result = prime * result + ((dataCriacao == null) ? 0 : dataCriacao.hashCode());
		result = prime * result + ((dataUltimaEdicao == null) ? 0 : dataUltimaEdicao.hashCode());
		result = prime * result + ((enunciado == null) ? 0 : enunciado.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((opcoes == null) ? 0 : opcoes.hashCode());
		result = prime * result + ((tipoQuestao == null) ? 0 : tipoQuestao.hashCode());
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
		Questao other = (Questao) obj;
		if (autor == null) {
			if (other.autor != null)
				return false;
		} else if (!autor.equals(other.autor))
			return false;
		if (comentario == null) {
			if (other.comentario != null)
				return false;
		} else if (!comentario.equals(other.comentario))
			return false;
		if (dataCriacao == null) {
			if (other.dataCriacao != null)
				return false;
		} else if (!dataCriacao.equals(other.dataCriacao))
			return false;
		if (dataUltimaEdicao == null) {
			if (other.dataUltimaEdicao != null)
				return false;
		} else if (!dataUltimaEdicao.equals(other.dataUltimaEdicao))
			return false;
		if (enunciado == null) {
			if (other.enunciado != null)
				return false;
		} else if (!enunciado.equals(other.enunciado))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (opcoes == null) {
			if (other.opcoes != null)
				return false;
		} else if (!opcoes.equals(other.opcoes))
			return false;
		if (tipoQuestao != other.tipoQuestao)
			return false;
		return true;
	}
}
