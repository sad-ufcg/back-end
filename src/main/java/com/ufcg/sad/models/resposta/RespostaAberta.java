package com.ufcg.sad.models.resposta;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
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
@DiscriminatorValue(value = "ABERTA")
public class RespostaAberta extends Resposta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column
    @Length(max = TAMANHO_MAX_STRING)
    private String comentario;

    /**
     * Construtor vazio para o hibernate
     */
    public RespostaAberta() {}

    public RespostaAberta(Long id, Date dataResposta, Long questao, Long questionarioAplicado, String comentario) {
        super(id, dataResposta, questao, questionarioAplicado);
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
