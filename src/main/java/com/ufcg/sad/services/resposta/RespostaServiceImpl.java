package com.ufcg.sad.services.resposta;

import com.ufcg.sad.exceptions.EntidadeInvalidaException;
import com.ufcg.sad.exceptions.EntidadeNotFoundException;
import com.ufcg.sad.exceptions.ParametroInvalidoException;
import com.ufcg.sad.models.opcao.Opcao;
import com.ufcg.sad.models.questao.Questao;
import com.ufcg.sad.models.questao.TipoQuestao;
import com.ufcg.sad.models.questionario.QuestionarioAplicado;
import com.ufcg.sad.models.resposta.Resposta;
import com.ufcg.sad.models.resposta.RespostaAberta;
import com.ufcg.sad.models.resposta.RespostaEscolhaSimples;
import com.ufcg.sad.models.resposta.RespostaMultiplaEscolha;
import com.ufcg.sad.models.resposta.RespostaSelecao;
import com.ufcg.sad.repositories.RespostaRepository;
import com.ufcg.sad.services.questao.QuestaoService;
import com.ufcg.sad.services.questionario.QuestionarioAplicadoService;
import com.ufcg.sad.services.token.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RespostaServiceImpl implements RespostaService {

	@Autowired
    RespostaRepository respostaRepository;

	@Autowired
    QuestaoService questaoService;

	@Autowired
	QuestionarioAplicadoService questionarioAplicadoService;

	@Autowired
	TokenService tokenService;

	
	public void validaResposta(Resposta resposta) throws EntidadeInvalidaException, EntidadeNotFoundException {
		if(resposta.getDataResposta() != null) {
			throw new ParametroInvalidoException("Data não deve ser passada para criar Resposta.");
		}
		
		Long idQuestao = resposta.getIdQuestao();
		if(idQuestao == null) {
			throw new EntidadeInvalidaException("idQuestão não pode ser nulo.");
		} else {
			questaoService.getQuestao(idQuestao);
		}
		
		Long idQuestionarioAplicado = resposta.getIdQuestionarioAplicado();
		if(idQuestionarioAplicado == null) {
			throw new EntidadeInvalidaException("idQuestionário não pode ser nulo.");
		} else {
			questionarioAplicadoService.getQuestionarioAplicado(idQuestionarioAplicado);
		}
		
		if(resposta instanceof RespostaAberta) {
			validaAberta((RespostaAberta) resposta);
		} else if(resposta instanceof RespostaEscolhaSimples) {
			validaEscolhaSimples((RespostaEscolhaSimples) resposta);
		} else if(resposta instanceof RespostaMultiplaEscolha) {
			validaMultiplaEscolha((RespostaMultiplaEscolha) resposta);
		} else if(resposta instanceof RespostaSelecao) {
			validaSelecao((RespostaSelecao) resposta);
		}
	}
	
	private void validaSelecao(RespostaSelecao resposta) throws EntidadeInvalidaException, EntidadeNotFoundException {
		List<Opcao> opcoes = resposta.getOpcoesSelecionadas();
		if(opcoes == null) {
			throw new EntidadeInvalidaException("Opção escolhida não pode ser nula.");
		}
		
		Questao questao = questaoService.getQuestao(resposta.getIdQuestao());
		if(questao.getTipoQuestao() != TipoQuestao.SELECAO) {
			throw new EntidadeInvalidaException("Resposta não deveria ser do tipo Seleção.");
		}
		
		for(Opcao opcao: resposta.getOpcoesSelecionadas()) {
			if(!questao.getOpcoes().contains(opcao)) {
				throw new EntidadeInvalidaException("Opção escolhida é inválida.");
			}
		}
	}

	private void validaAberta(RespostaAberta resposta) throws EntidadeNotFoundException, EntidadeInvalidaException {
		Questao questao = questaoService.getQuestao(resposta.getIdQuestao());
		if(questao.getTipoQuestao() != TipoQuestao.ABERTA) {
			throw new EntidadeInvalidaException("Resposta não deveria ser do tipo Aberta.");
		}
	}

	private void validaMultiplaEscolha(RespostaMultiplaEscolha resposta) throws EntidadeInvalidaException, EntidadeNotFoundException {
		Opcao opcaoEscolhida = resposta.getOpcaoEscolhida();
		if(opcaoEscolhida == null) {
			throw new EntidadeInvalidaException("Opção escolhida não pode ser nula.");
		}
		
		Questao questao = questaoService.getQuestao(resposta.getIdQuestao());
		if(questao.getTipoQuestao() != TipoQuestao.MULTIPLA_ESCOLHA) {
			throw new EntidadeInvalidaException("Resposta não deveria ser do tipo Multipla Escolha.");
		}
		
		if(!questao.getOpcoes().contains(resposta.getOpcaoEscolhida())) {
			throw new EntidadeInvalidaException("Opção escolhida é inválida.");
		}
	}

	private void validaEscolhaSimples(RespostaEscolhaSimples resposta) throws EntidadeInvalidaException, EntidadeNotFoundException {
		Integer escolha = resposta.getEscolhaSimples();
		if(escolha == null || escolha < Questao.MIN_ESCOLHA_SIMPLES || 
		   escolha > Questao.MAX_ESCOLHA_SIMPLES) {
			throw new EntidadeInvalidaException("Escolha simples inválida: " + escolha);
		}
		
		Questao questao = questaoService.getQuestao(resposta.getIdQuestao());
		if(questao.getTipoQuestao() != TipoQuestao.ESCOLHA_SIMPLES) {
			throw new EntidadeInvalidaException("Resposta não deveria ser do tipo Escolha Simples.");
		}
	}

	private void validaCriacaoResposta(Resposta resposta) throws EntidadeInvalidaException, EntidadeNotFoundException {
		if(resposta.getId() != null) {
			throw new ParametroInvalidoException("Id deve ser nulo para a criação de resposta.");
		}
		validaResposta(resposta);
	}
	
    @Override
    public Resposta criarResposta(Resposta resposta) throws EntidadeNotFoundException, EntidadeInvalidaException {
    	
    	validaCriacaoResposta(resposta);
    	// Define data da resposta
    	resposta.setDataResposta(new Date());
		// Salva resposta
    	Resposta respostaSalva = respostaRepository.saveAndFlush(resposta);        
        // Recupera Questionario Aplicado
        Long id = respostaSalva.getIdQuestionarioAplicado();
        QuestionarioAplicado questionarioAplicado = questionarioAplicadoService.getQuestionarioAplicado(id);
    	// Adiciona Resposta
    	questionarioAplicado.addResposta(respostaSalva);
    	// Atualiza Questionario Aplicado
    	questionarioAplicadoService.atualizaQuestionarioAplicado(questionarioAplicado);

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
    		return respostaRepository.buscarRespostasComQuestaoId(idQuestao);
    	else if(isIdQuestaoNull)
    		return respostaRepository.buscarRespostasDeTipo(tipoResposta);
    	else
    		return respostaRepository.buscarRespostasDeTipoComQuestaoId(tipoResposta, idQuestao);
    }
    
    @Override
    public List<Resposta> addRespostas(String token, List<Resposta> respostas) throws Exception {
    	tokenService.verificaSeTokenExiste(token);
    	List<Resposta> ret = new ArrayList<Resposta>();
    	for (Resposta resp : respostas) {
            ret.add(criarResposta(resp));
    	}
    	
    	tokenService.deletaToken(token);
    	
    	return ret;
    }
}
