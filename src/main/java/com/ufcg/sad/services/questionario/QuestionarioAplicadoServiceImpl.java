package com.ufcg.sad.services.questionario;

import java.util.ArrayList;
import java.util.List;

import com.ufcg.sad.models.disciplina.Disciplina;
import com.ufcg.sad.models.questionario.Questionario;
import com.ufcg.sad.services.disciplina.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.sad.exceptions.EntidadeNotFoundException;
import com.ufcg.sad.exceptions.utils.ParametroInvalidoException;

import com.ufcg.sad.models.questionario.QuestionarioAplicado;
import com.ufcg.sad.repositories.questionario.QuestionarioAplicadoRepository;

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
    private DisciplinaService disciplinaService;

	/**
	 * Construtor para o tipo QuestionarioAplicadoService.
	 */
	public QuestionarioAplicadoServiceImpl() {}
	
	/**
	 * Método que salva um questionário aplicado.
	 * 
	 * @param questionarioAplicado
	 */
	public QuestionarioAplicado criaQuestionarioAplicado(QuestionarioAplicado questionarioAplicado) throws ParametroInvalidoException {
		
		if(questionarioAplicado.getId() != null) {
			throw new ParametroInvalidoException();
		}

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
		List<QuestionarioAplicado> questionariosAplicados = new ArrayList<>();

		for (QuestionarioAplicado questionarioAplicado : questionarioAplicadoRepository.findAll()) {
			questionariosAplicados.add(questionarioAplicado);
		}

		return questionariosAplicados;
	}

	/**
	 * Método que atualiza um questionário aplicado.
	 * 
	 * @param questionarioAplicado
	 * 
	 * @return questionarioAplicado atualizado
	 */
	public QuestionarioAplicado atualizaQuestionarioAplicado(QuestionarioAplicado questionarioAplicado) throws EntidadeNotFoundException {
		return questionarioAplicadoRepository.save(questionarioAplicado);
	}

	@Override
	public QuestionarioAplicado aplicarQuestionario(Long questionarioID, Long disciplinaId) throws EntidadeNotFoundException {

	    Questionario questionario = questionarioService.getQuestionario(questionarioID);
        Disciplina disciplina = disciplinaService.getDisciplina(disciplinaId);

        if(questionario != null || disciplina != null){
	        throw new EntidadeNotFoundException("Erro ao aplicar cadastro");
        }

        //sdds factory
        QuestionarioAplicado questionarioAplicado = new QuestionarioAplicado();
        questionarioAplicado.setIdDisciplina(disciplina.getId());
        questionarioAplicado.setIdProfessor(disciplina.getProfessorId());
        questionarioAplicado.setIdQuestionario(questionario.getId());


        return questionarioAplicadoRepository.save(questionarioAplicado);
	}
}