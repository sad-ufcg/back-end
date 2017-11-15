package com.ufcg.sad.models.questao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

import com.ufcg.sad.models.professor.Professor;

/**
 * Entidade que representa uma questão aberta.
 * 
 * @author Marianne Linhares
 */
@Entity
public class QuestaoAberta extends Questao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Método para construir uma instância do tipo questão
	 * @param id
	 * @param enunciado
	 * @param autor
	 * @param dataDeCriacao
	 * @param comentario
	 */
	public QuestaoAberta(Long id, String enunciado, Professor autor, Date dataCriacao, String comentario) {
		super(id, enunciado, autor, dataCriacao, comentario);
	}
	
	/**
     * Construtor padrão para o Hibernate.
     */
	public QuestaoAberta() { }
}
