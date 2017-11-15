package com.ufcg.sad.services.questionario;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.sad.exceptions.questionario.QuestaoInvalidaException;
import com.ufcg.sad.exceptions.questionario.QuestionarioNaoExisteException;
import com.ufcg.sad.exceptions.questionario.QuestionarioSemNomeException;
import com.ufcg.sad.exceptions.questionario.QuestionarioVazioException;
import com.ufcg.sad.exceptions.utils.ParametroInvalidoException;
import com.ufcg.sad.models.questionario.Questao;
import com.ufcg.sad.models.questionario.Questionario;
import com.ufcg.sad.repositories.questionario.QuestionarioRepository;

/**
 * Serviço para um Questionário.
 * 
 * @author Marianne Linhares
 */
/*
	TODO: precisa criar uma interface QuestionarioService
 */
@Service
public class QuestionarioService {
	
	private QuestionarioRepository questionarioRepository;
	private QuestaoService questaoService;

	/**
	 * Construtor para o tipo QuestionarioService.
	 */
	@Autowired
	public QuestionarioService() {}
	
	/**
	 * Método que busca um questionário a partir de um certo id.
	 * @param id
	 * @return questionario
	 */
	public Questionario getQuestionario(Long id) throws QuestionarioNaoExisteException {
		
		Questionario questionario = questionarioRepository.findOne(id);
		
		if(questionario != null) {
			return questionario;
		}
		else {
			throw new QuestionarioNaoExisteException();
		}
	}
	
	/**
	 * Método que busca todos os questionários.
	 * @return lista contendo questionarios
	 */
	public List<Questionario> getTodosQuestionarios() {
		List<Questionario> questionarios = new ArrayList<>();

		for (Questionario questionario : questionarioRepository.findAll()) {
			questionarios.add(questionario);
		}

		return questionarios;
	}
	
	/**
	 * Método que salva um questionário.
	 * @param questionario
	 * @throws QuestionarioVazioException, QuestionarioSemNomeException, QuestaoInvalidaException, ParametroInvalidoException 
	 */
	public void criaQuestionario(Questionario questionario) throws QuestionarioVazioException, QuestionarioSemNomeException, QuestaoInvalidaException, QuestionarioVazioException, QuestionarioSemNomeException, QuestaoInvalidaException, ParametroInvalidoException {
		
		if(questionario.getId() != null) {
			throw new ParametroInvalidoException();
		}
		
		if(questionario.getNome() == null || questionario.getNome().isEmpty()) {
			throw new QuestionarioSemNomeException();
		}
		else if(questionario.getQuestoes() == null || questionario.getQuestoes().isEmpty()) {
			throw new QuestionarioVazioException();
		}
		
		for(Questao questao : questionario.getQuestoes()) {
			if(!questaoService.validaQuestao(questao)) {
				throw new QuestaoInvalidaException();
			}
		}
		
		questionarioRepository.save(questionario);
	}

	/**
	 * Método que atualiza um questionário.
	 * @param id
	 * @param questionario
	 */
	public void atualizaQuestionario(Long id, Questionario questionario) throws QuestionarioNaoExisteException, QuestaoInvalidaException {
		
		Questionario questionarioAtualizado = getQuestionario(id);
		
		if(questionario.getQuestoes() != null && !questionario.getQuestoes().isEmpty()) {
			
			for(Questao questao : questionario.getQuestoes()) {
				if(!questaoService.validaQuestao(questao)) {
					throw new QuestaoInvalidaException();
				}
			}
			
			questionarioAtualizado.setQuestoes(questionario.getQuestoes());
		}
		
		if(questionario.getNome() != null && !questionario.getNome().equals(questionarioAtualizado.getNome())) {
			questionarioAtualizado.setNome(questionario.getNome());
		}
		
		questionarioRepository.save(questionarioAtualizado);
	}
}