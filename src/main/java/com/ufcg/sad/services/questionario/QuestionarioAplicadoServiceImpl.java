package com.ufcg.sad.services.questionario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.sad.exceptions.EntidadeInvalidaException;
import com.ufcg.sad.exceptions.EntidadeNotFoundException;
import com.ufcg.sad.exceptions.ParametroInvalidoException;
import com.ufcg.sad.models.questionario.QuestionarioAplicado;
import com.ufcg.sad.repositories.QuestionarioAplicadoRepository;
import com.ufcg.sad.services.disciplina.DisciplinaService;
import com.ufcg.sad.services.professor.ProfessorService;

/**
 * Serviço para um Questionário Aplicado.
 * 
 * @author Arthur Vinícius
 */

@Service
public class QuestionarioAplicadoServiceImpl implements QuestionarioAplicadoService {

	@Autowired
	private QuestionarioAplicadoRepository questionarioAplicadoRepository;

	@Autowired
	private QuestionarioService questionarioService;
	
	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	
	private void validaQuestionarioAplicado(QuestionarioAplicado questionarioAplicado) throws EntidadeInvalidaException, EntidadeNotFoundException {
		Long idQuestionario = questionarioAplicado.getIdQuestionario();
		if(idQuestionario == null) {
			throw new EntidadeInvalidaException("idQuestionário não deve ser nulo.");
		} else {
			questionarioService.getQuestionario(idQuestionario);
		}
		
		// IdProfessor pode ser nulo
		Long idProfessor = questionarioAplicado.getIdProfessor();
		if(idProfessor != null) {
			professorService.getProfessor(idProfessor);
		}
		
		Long idDisciplina = questionarioAplicado.getIdDisciplina();
		if(idDisciplina == null) {
			throw new EntidadeInvalidaException("idDisciplina não deve ser nulo.");
		} else {
			disciplinaService.getDisciplina(idDisciplina);
		}
	}
	
	/**
	 * Construtor para o tipo QuestionarioAplicadoService.
	 */
	public QuestionarioAplicadoServiceImpl() {}
	
	/**
	 * Método que salva um questionário aplicado.
	 * 
	 * @param questionarioAplicado
	 * @throws EntidadeNotFoundException 
	 * @throws EntidadeInvalidaException 
	 */
	public QuestionarioAplicado criaQuestionarioAplicado(QuestionarioAplicado questionarioAplicado) throws ParametroInvalidoException, EntidadeInvalidaException, EntidadeNotFoundException {
		if(questionarioAplicado.getId() != null) {
			throw new ParametroInvalidoException("Id deve ser nulo para a criação do questionário aplicado.");
		}
		
		validaQuestionarioAplicado(questionarioAplicado);

		return questionarioAplicadoRepository.save(questionarioAplicado);
	}
	
	/**
	 * Método que busca um questionário aplicado a partir de um certo id.
	 * 
	 * @param id
	 * 
	 * @return questionarioAplicado
	 */
	public QuestionarioAplicado getQuestionarioAplicado(Long id) throws EntidadeNotFoundException {
		QuestionarioAplicado questionarioAplicado = questionarioAplicadoRepository.findOne(id);
		
		if(questionarioAplicado != null) {
			return questionarioAplicado;
		}
		else {
			throw new EntidadeNotFoundException();
		}
	}
	
	/**
	 * Método que busca todos os questionários aplicados.
	 * 
	 * @return lista contendo questionários aplicados
	 */
	public List<QuestionarioAplicado> getTodosQuestionariosAplicados() {
		return questionarioAplicadoRepository.findAll();
	}

	/**
	 * Método que atualiza um questionário aplicado.
	 * 
	 * @param questionarioAplicado
	 * 
	 * @return questionarioAplicado atualizado
	 * @throws EntidadeInvalidaException 
	 */
	public QuestionarioAplicado atualizaQuestionarioAplicado(QuestionarioAplicado questionarioAplicado) throws EntidadeNotFoundException, EntidadeInvalidaException {
		validaQuestionarioAplicado(questionarioAplicado);
		return questionarioAplicadoRepository.save(questionarioAplicado);
	}

    @Override
    public List<QuestionarioAplicado> getListaDeQuestionariosAplicados(List<Long> ids) {
        return questionarioAplicadoRepository.findByIdIn(ids);
    }
}