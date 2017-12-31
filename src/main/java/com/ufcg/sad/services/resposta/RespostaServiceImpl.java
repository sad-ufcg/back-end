package com.ufcg.sad.services.resposta;

import com.ufcg.sad.exceptions.EntidadeNotFoundException;
import com.ufcg.sad.models.questionario.QuestionarioAplicado;
import com.ufcg.sad.models.resposta.Resposta;
import com.ufcg.sad.repositories.resposta.RespostaRepository;
import com.ufcg.sad.services.questionario.QuestionarioAplicadoService;
import com.ufcg.sad.services.token.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RespostaServiceImpl implements RespostaService {

	@Autowired
    RespostaRepository respostaRepository;

	@Autowired
	QuestionarioAplicadoService questionarioAplicadoService;

	@Autowired
	TokenService tokenService;

    @Override
    public Resposta criarResposta(Resposta resposta) throws EntidadeNotFoundException {
        Resposta respostaSalva = respostaRepository.saveAndFlush(resposta);
        if(respostaSalva.getIdQuestionarioAplicado() != null) {
        	// Recupera Questionario Aplicado
        	Long id = respostaSalva.getIdQuestionarioAplicado();
        	QuestionarioAplicado questionarioAplicado = questionarioAplicadoService.getQuestionarioAplicado(id);

        	// Adiciona Resposta
        	questionarioAplicado.addResposta(respostaSalva);

        	// Atualiza Questionario Aplicado
        	questionarioAplicadoService.atualizaQuestionarioAplicado(questionarioAplicado);
        }
        return respostaSalva;
    }

    @Override
    public Resposta getResposta(Long id) throws EntidadeNotFoundException {
        if(respostaRepository.exists(id)) {
            return respostaRepository.findOne(id);
        } else {
            throw new EntidadeNotFoundException();
        }
    }

    @Override
    public List<Resposta> getTodasAsRespostas(String tipoResposta, Long idQuestao) {
    	boolean isTipoRespostaNull = (tipoResposta == null || tipoResposta.isEmpty());
    	boolean isIdQuestaoNull = (idQuestao == null);

    	if(isTipoRespostaNull && isIdQuestaoNull)
    		return respostaRepository.findAll();
    	else if(isTipoRespostaNull)
    		return respostaRepository.findAllOfQuestaoId(idQuestao);
    	else if(isIdQuestaoNull)
    		return respostaRepository.findAllOfType(tipoResposta);
    	else
    		return respostaRepository.findAllOfTypeAndQuestaoId(tipoResposta, idQuestao);
    }
    
    @Override
    public List<Resposta> addRespostas(String token, List<Resposta> respostas) throws Exception {
    	tokenService.verificaSeTokenExiste(token);
    	List<Resposta> ret = new ArrayList<Resposta>();
    	for (Resposta resp : respostas) {
            ret.add(criarResposta(resp));
    	}    	
    	return ret;
    }
}
