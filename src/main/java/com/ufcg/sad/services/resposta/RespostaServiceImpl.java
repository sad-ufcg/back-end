package com.ufcg.sad.services.resposta;

import com.ufcg.sad.exceptions.EntidadeNotFoundException;
import com.ufcg.sad.models.resposta.Resposta;
import com.ufcg.sad.repositories.resposta.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RespostaServiceImpl implements RespostaService {

	@Autowired
    RespostaRepository respostaRepository;

    @Override
    public Resposta criarResposta(Resposta resposta) {
        return respostaRepository.saveAndFlush(resposta);
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
    public List<Resposta> getTodasAsRespostas() {
        return respostaRepository.findAll();
    }
    
    @Override
    public List<Resposta> addRespostas(List<Resposta> respostas) {
    	List<Resposta> ret = new ArrayList<Resposta>();
    	for (Resposta resp : respostas)
    		ret.add(respostaRepository.saveAndFlush(resp));
    	return ret;
    }
}
