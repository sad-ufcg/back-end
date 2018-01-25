package com.ufcg.sad.services.questao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.sad.exceptions.EntidadeInvalidaException;
import com.ufcg.sad.exceptions.EntidadeNotFoundException;
import com.ufcg.sad.models.questao.Questao;
import com.ufcg.sad.models.questao.TipoQuestao;
import com.ufcg.sad.repositories.QuestaoRepository;
import com.ufcg.sad.services.professor.ProfessorService;

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
	private ProfessorService professorService;
	
	@Autowired
	private QuestaoRepository questaoRepository;

	/**
	 * Construtor para o tipo QuestaoService.
	 */
	public QuestaoServiceImpl() {}

	public void validaQuestao(Questao questao) throws EntidadeInvalidaException, EntidadeNotFoundException {
		if(questao.getAutor() != null) {
			professorService.validaProfessor(questao.getAutor());
		}
		
		if(questao.getEnunciado() == null || questao.getEnunciado().isEmpty()) {
			throw new EntidadeInvalidaException("Questão deve conter enunciado.");
		}

		if(questao.getTipoQuestao() == null) {
			throw new EntidadeInvalidaException("Questão deve conter tipo.");
		}
		
		if(questao.getDataCriacao() != null || questao.getDataUltimaEdicao() != null) {
			throw new EntidadeInvalidaException("Data não deve ser passada.");
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
	
	private void validaCriacaoQuestao(Questao questao) throws EntidadeInvalidaException, EntidadeNotFoundException {
		if(questao.getId() != null) {
			throw new EntidadeInvalidaException("Questão não se deve conter ID.");
		}
		validaQuestao(questao);
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
	 * @throws EntidadeNotFoundException, EntidadeInvalidaException
	 */
	public Questao criaQuestao(Questao questao) throws EntidadeInvalidaException, EntidadeNotFoundException {
		validaCriacaoQuestao(questao);
		
		Date hoje = new Date();
		questao.setDataCriacao(hoje);
		questao.setDataUltimaEdicao(hoje);
		
		return questaoRepository.save(questao);
	}

	public Questao atualizaQuestao(Questao questao) throws EntidadeNotFoundException, EntidadeInvalidaException {
		validaQuestao(questao);

		Date hoje = new Date();
		questao.setDataUltimaEdicao(hoje);
		
		return questaoRepository.save(questao);
	}
}
