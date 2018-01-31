package com.ufcg.sad.models.questionario;

import static com.ufcg.sad.models.util.Utils.TAMANHO_MAX_STRING;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import com.ufcg.sad.models.professor.Professor;
import com.ufcg.sad.models.questao.Questao;

/**
 * Entidade que representa um questionário
 * 
 * @author Lucas Silva, Marianne Linhares
 */
@Entity
@Table
public class Questionario implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
    @NotNull
    @Length(max = TAMANHO_MAX_STRING)
	private String nome;
	
	@Column
    @Length(max = TAMANHO_MAX_STRING)
	private String descricao;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Questao> questoes;
	
	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	private Professor autor;
	
	@Column
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataCriacao;
	
	@Column
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataUltimaEdicao;

	/**
	 * Método para construir uma instância do tipo questionário
	 * @param id
	 * @param nome
	 * @param descricao
	 * @param questoes
	 * @param autor
	 * @param dataCriacao
	 * @param dataUltimaEdicao
	 */
	public Questionario(Long id, String nome, String descricao, List<Questao> questoes, Professor autor, Date dataCriacao, Date dataUltimaEdicao) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.questoes = questoes;
		this.autor = autor;
		this.dataCriacao = dataCriacao;
		this.dataUltimaEdicao = dataUltimaEdicao;
	}
	
	/**
	 * Método para construir uma instância do tipo questionário
	 */
	public Questionario() { 
		this.questoes = new ArrayList<Questao>();
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

	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public List<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
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

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataUltimaEdicao() {
		return dataUltimaEdicao;
	}

	public void setDataUltimaEdicao(Date dataUltimaEdicao) {
		this.dataUltimaEdicao = dataUltimaEdicao;
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
