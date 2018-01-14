package com.ufcg.sad.services.questao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.sad.exceptions.EntidadeInvalidaException;
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
public class QuestaoServiceImpl implements QuestaoService {

	@Autowired
	private QuestaoRepository questaoRepository;

	/**
	 * Construtor para o tipo QuestaoService.
	 */
	public QuestaoServiceImpl() {}

	public void validaQuestao(Questao questao) throws EntidadeInvalidaException {
		
		if(questao.getId() != null) {
			throw new EntidadeInvalidaException("Questão não se deve conter ID.");
		}
		
		if(questao.getEnunciado() == null || questao.getEnunciado().isEmpty()) {
			throw new EntidadeInvalidaException("Questão deve conter enunciado.");
		}

		if(questao.getTipoQuestao() == null) {
			throw new EntidadeInvalidaException("Questão deve conter tipo.");
		}
		
		boolean tipoValido = false;
		for(TipoQuestao questaoValida : TipoQuestao.values()) {
			if(questao.getTipoQuestao().equals(questaoValida)) {
		       tipoValido = true;
		       break;
		   }
		}
		
		if(!tipoValido) {
			throw new EntidadeInvalidaException("Questão contém tipo inválido: " + questao.getTipoQuestao());
		}
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
	public Questao criaQuestao(Questao questao) throws EntidadeInvalidaException {
		validaQuestao(questao);
		return questaoRepository.save(questao);
	}

	public Questao atualizaQuestao(Questao questao) throws EntidadeNotFoundException {
		return questaoRepository.save(questao);
	}
}
