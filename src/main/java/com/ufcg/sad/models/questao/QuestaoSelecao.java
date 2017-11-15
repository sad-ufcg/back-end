package com.ufcg.sad.models.questao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;

import com.ufcg.sad.models.opcao.Opcao;
import com.ufcg.sad.models.professor.Professor;

/**
 * Entidade que representa uma questão múltipla escolha.
 * 
 * @author Marianne Linhares
 */
@Entity
@Inheritance
public class QuestaoSelecao extends Questao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@OneToMany(fetch = FetchType.LAZY,
               mappedBy = "questao_id")
	private List<Opcao> opcoesSelecionadas;

	/**
	 * Método para construir uma instância do tipo questão
	 * @param id
	 * @param enunciado
	 * @param autor
	 * @param dataDeCriacao
	 * @param comentario
	 * @param opcoesSelecionadas
	 */
	public QuestaoSelecao(Long id, String enunciado, Professor autor, Date dataCriacao, String comentario, List<Opcao> opcoesSelecionadas) {
		super(id, enunciado, autor, dataCriacao, comentario);
		this.opcoesSelecionadas = opcoesSelecionadas;
	}
	
	/**
     * Construtor padrão para o Hibernate.
     */
	public QuestaoSelecao() { }
	
	public List<Opcao> getOpcoesSelecionadas() {
		return opcoesSelecionadas;
	}
	
	public void setOpcoesSelecionadas(List<Opcao> opcoesSelecionadas) {
		this.opcoesSelecionadas = opcoesSelecionadas;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(super.equals(obj) && getClass() == obj.getClass()) {
			QuestaoSelecao outraQuestao = (QuestaoSelecao) obj;
			
		    List<Opcao> opcoes = new ArrayList<Opcao>(this.opcoesSelecionadas); 
		    List<Opcao> outrasOpcoes = new ArrayList<Opcao>(outraQuestao.getOpcoesSelecionadas());   

		    return opcoes.containsAll(outrasOpcoes) && outrasOpcoes.containsAll(opcoes);
		}
		else {
			return false;
		}
	}
}
