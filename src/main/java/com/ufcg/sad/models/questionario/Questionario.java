package com.ufcg.sad.models.questionario;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

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
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "questionario_id")
	private Long id;
	
	@Column
	private String nome;
	
	@Column
	private String descricao;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Questao> questoes;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private Professor autor;
	
	@Column
	private Date dataCriacao;
	
	@Column
	private Date dataUltimaEdicao;

	@OneToMany(fetch = FetchType.LAZY,
				mappedBy = "questionario")
	private Set<QuestionarioAplicado> questionariosAplicados;

	/**
	 * Método para construir uma instância do tipo questionário
	 * @param id
	 * @param nome
	 * @param  descricao
	 * @param questoes
	 * @param autor
	 * @param dataCriacao
	 * @param dataUltimaEdicao
	 * @param questionariosAplicados
	 */
	public Questionario(Long id, String nome, String descricao, Set<Questao> questoes, Professor autor, Date dataCriacao, Date dataUltimaEdicao, Set<QuestionarioAplicado> questionariosAplicados) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.questoes = questoes;
		this.autor = autor;
		this.dataCriacao = dataCriacao;
		this.dataUltimaEdicao = dataUltimaEdicao;
		this.questionariosAplicados = questionariosAplicados;
	}
	
	/**
	 * Método para construir uma instância do tipo questionário
	 */
	public Questionario() { 
		this.questoes = new HashSet<Questao>();
		this.questionariosAplicados = new HashSet<QuestionarioAplicado>();
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
	
	public Set<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(Set<Questao> questoes) {
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

	public Set<QuestionarioAplicado> getQuestionariosAplicados() {
		return questionariosAplicados;
	}

	public void setQuestionariosAplicados(Set<QuestionarioAplicado> questionariosAplicados) {
		this.questionariosAplicados = questionariosAplicados;
	}

	@Override
	public int hashCode() {
		final int primo = 31;
		int resultado = 1;
		resultado = primo * resultado + ((id == null) ? 0 : id.hashCode());
		resultado = primo * resultado + ((nome == null) ? 0 : nome.hashCode());
		resultado = primo * resultado + ((questoes == null) ? 0 : questoes.hashCode());
		resultado = primo * resultado + ((questionariosAplicados == null) ? 0 : questionariosAplicados.hashCode());
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
		if (questionariosAplicados == null) {
			if (outroQuestionario.questionariosAplicados != null)
				return false;
		} else if (!questionariosAplicados.equals(outroQuestionario.questionariosAplicados))
			return false;

		return true;
	}

	
}
