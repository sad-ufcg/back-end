package com.ufcg.sad.services.questionario;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.sad.exceptions.EntidadeInvalidaException;
import com.ufcg.sad.exceptions.EntidadeNotFoundException;
import com.ufcg.sad.exceptions.ParametroInvalidoException;
import com.ufcg.sad.models.questao.Questao;
import com.ufcg.sad.models.questionario.Questionario;
import com.ufcg.sad.repositories.questionario.QuestionarioRepository;
import com.ufcg.sad.services.questao.QuestaoService;

/**
 * Serviço para um Questionário.
 * 
 * @author Marianne Linhares
 */

@Service
public class QuestionarioServiceImpl implements QuestionarioService {

	@Autowired
	private QuestionarioRepository questionarioRepository;

	@Autowired
	private QuestaoService questaoService;

	/**
	 * Construtor para o tipo QuestionarioService.
	 */
	public QuestionarioServiceImpl() {}
	
	/**
	 * Método que busca um questionário a partir de um certo id.
	 * @param id
	 * @return questionario
	 */
	public Questionario getQuestionario(Long id) throws EntidadeNotFoundException {
		Questionario questionario = questionarioRepository.findOne(id);
		
		if(questionario != null) {
			return questionario;
		}
		else {
			throw new EntidadeNotFoundException();
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
	public Questionario criaQuestionario(Questionario questionario) throws EntidadeInvalidaException, ParametroInvalidoException {
		
		if(questionario.getId() != null) {
			throw new ParametroInvalidoException();
		}
		
		if(questionario.getNome() == null || questionario.getNome().isEmpty()) {
			throw new EntidadeInvalidaException("Questionário sem nome.");
		}
		
		if(questionario.getQuestoes() == null || questionario.getQuestoes().isEmpty()) {
			throw new EntidadeInvalidaException("Questionário vazio.");
		}
		else {
			for(Questao questao : questionario.getQuestoes()) {
				questaoService.validaQuestao(questao);
			}
		}

		return questionarioRepository.save(questionario);
	}

	/**
	 * Método que atualiza um questionário.
	 * @param questionario
	 * @return questionario atualizado
	 * @throws EntidadeInvalidaException 
	 */
	public Questionario atualizaQuestionario(Questionario questionario) throws EntidadeNotFoundException, EntidadeInvalidaException {
		
		if(questionario.getQuestoes() != null && !questionario.getQuestoes().isEmpty()) {	
			for(Questao questao : questionario.getQuestoes()) {
				questaoService.validaQuestao(questao);
			}
		}
		
		return questionarioRepository.save(questionario);
	}
}