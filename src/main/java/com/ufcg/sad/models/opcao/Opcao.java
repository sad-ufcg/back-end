package com.ufcg.sad.models.opcao;

import static com.ufcg.sad.models.util.Utils.TAMANHO_MAX_STRING;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.ufcg.sad.models.questao.Questao;

public class Opcao {
	
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    @NotNull
    @Length(max = TAMANHO_MAX_STRING)
    private String nome;

	@Column
    @NotNull
    @Length(max = TAMANHO_MAX_STRING)
    private String descricao;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Questao questao;
    
    /**
     * Contrutor padrão para o Hibernate.
     */
    public Opcao() {}

    /**
     * Construtor para a classe Disciplina
     * @param id Id da disciplina.
     * @param nome Nome da disciplina.
     * @param turma Número da turma.
     * @param professor Professor responsável pela disciplina.
     * @param semestre Semestre a qual a disciplina foi ministrada.
     * @param alunos Conjunto de alunos que cursaram a disciplina.
     */
    public Opcao(Long id, String nome, String descricao, Questao questao) {
        this.id = id;
        this.nome = nome;
        this.questao = questao;
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

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((questao == null) ? 0 : questao.hashCode());
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
		Opcao other = (Opcao) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (questao == null) {
			if (other.questao != null)
				return false;
		} else if (!questao.equals(other.questao))
			return false;
		return true;
	}

}
