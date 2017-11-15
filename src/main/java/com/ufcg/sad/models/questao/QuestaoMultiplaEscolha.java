package com.ufcg.sad.models.questao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.validation.constraints.NotNull;

import com.ufcg.sad.models.opcao.Opcao;
import com.ufcg.sad.models.professor.Professor;

/**
 * Entidade que representa uma questão múltipla escolha.
 * 
 * @author Marianne Linhares
 */
@Entity
@Inheritance
public class QuestaoMultiplaEscolha extends Questao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column
	@NotNull
	private Opcao opcaoSelecionada;

	/**
	 * Método para construir uma instância do tipo questão
	 * @param id
	 * @param enunciado
	 * @param autor
	 * @param dataDeCriacao
	 * @param comentario
	 * @param opcaoSelecionada
	 */
	public QuestaoMultiplaEscolha(Long id, String enunciado, Professor autor, Date dataCriacao, String comentario, Opcao opcao) {
		super(id, enunciado, autor, dataCriacao, comentario);
		this.opcaoSelecionada = opcao;
	}
	
	/**
     * Construtor padrão para o Hibernate.
     */
	public QuestaoMultiplaEscolha() { }
	
	public Opcao getOpcaoSelecionada() {
		return opcaoSelecionada;
	}
	
	public void setOpcaoSelecionada(Opcao opcaoSelecionada) {
		this.opcaoSelecionada = opcaoSelecionada;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(super.equals(obj) && getClass() == obj.getClass()) {
			QuestaoMultiplaEscolha outraQuestao = (QuestaoMultiplaEscolha) obj;
			return (opcaoSelecionada.equals(outraQuestao.getOpcaoSelecionada()));
		}
		else {
			return false;
		}
	}
}
