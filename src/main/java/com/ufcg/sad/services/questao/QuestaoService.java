package com.ufcg.sad.services.questao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.sad.exceptions.EntidadeNotFoundException;
import com.ufcg.sad.models.questao.Questao;
import com.ufcg.sad.models.questao.TipoQuestao;
import com.ufcg.sad.repositories.questionario.QuestaoRepository;

/**
 * Serviço para a entidade Questão.
 * 
 * @author Marianne Linhares
 */
/*
TODO: precisa criar uma interface QuestaoService
*/
@Service
public class QuestaoService {

	@Autowired
	private QuestaoRepository questaoRepository;

	/**
	 * Construtor para o tipo QuestaoService.
	 */
	public QuestaoService() {}
	
	/**
	 * Método auxiliar para validar uma questão.
	 * @param questao
	 * @return boolean
	 */
	public boolean validaQuestao(Questao questao) {
		
		if(questao.getId() != null) {
			return false;
		}
		
		if(questao.getEnunciado() == null || questao.getEnunciado().isEmpty()) {
			return false;
		}

		if(questao.getTipoQuestao() == null) {
			return false;
		}
		
		boolean tipoValido = false;
		for(TipoQuestao questaoValida : TipoQuestao.values()) {
			if(questao.getTipoQuestao().equals(questaoValida)) {
		       tipoValido = true;
		       break;
		   }
		}
		return tipoValido;
	}
	
	/**
	 * Método para buscar uma questão a partir de um id.
	 * @param id
	 * @return questao
	 */
	public Questao getQuestao(Long id) throws EntidadeNotFoundException {
		
		Questao questao = questaoRepository.findOne(id);
		
		if(questao != null) {
			return questao;
		}
		else {
			throw new EntidadeNotFoundException();
		}
	}
	
	/**
	 * Método para buscar todas as questões.
	 * @return Lista contendo questoes
	 */
	public List<Questao> getTodasQuestoes() {
		List<Questao> questoes = new ArrayList<>();

		for (Questao questao : questaoRepository.findAll()) {
			questoes.add(questao);
		}

		return questoes;
	}
	
	/**
	 * Método para criar uma questão.
	 * @param questao
	 */
	public void criaQuestao(Questao questao) {
		questaoRepository.save(questao);
	}
	
}