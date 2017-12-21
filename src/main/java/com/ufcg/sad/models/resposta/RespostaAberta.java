package com.ufcg.sad.models.resposta;

import com.ufcg.sad.models.questao.Questao;
import com.ufcg.sad.models.questionario.QuestionarioAplicado;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

import static com.ufcg.sad.models.util.Utils.TAMANHO_MAX_STRING;

/**
 * Classe que representa uma resposta aberta.
 *
 * @author Arthur Costa
 */
@Entity
public class RespostaAberta extends Resposta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Length(max = TAMANHO_MAX_STRING)
    private String comentario;

    /**
     * Construtor vazio para o hibernate
     */
    public RespostaAberta() {}

    public RespostaAberta(Date dataResposta, Questao questao, QuestionarioAplicado questionarioAplicado, String comentario) {
        super(dataResposta, questao, questionarioAplicado);
        this.comentario = comentario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RespostaAberta)) return false;
        if (!super.equals(o)) return false;

        RespostaAberta that = (RespostaAberta) o;

        return getComentario() != null ? getComentario().equals(that.getComentario()) : that.getComentario() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getComentario() != null ? getComentario().hashCode() : 0);
        return result;
    }
}
