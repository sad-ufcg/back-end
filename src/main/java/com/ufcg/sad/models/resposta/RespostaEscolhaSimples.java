package com.ufcg.sad.models.resposta;

import com.ufcg.sad.models.questao.Questao;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;

import static com.ufcg.sad.models.util.Utils.TAMANHO_MAX_STRING;

@Entity
@DiscriminatorValue(value = "ESCOLHA_SIMPLES")
public class RespostaEscolhaSimples extends Resposta implements Serializable{

    private static final long serialVersionUID = 1L;

    @Range(min=Questao.MIN_ESCOLHA_SIMPLES, max=Questao.MAX_ESCOLHA_SIMPLES)
    @Column
    @NotNull
    private Integer escolhaSimples;

    @Column
    @Length(max = TAMANHO_MAX_STRING)
    private String comentario;

    /**
     * Construtor vazio para o hibernate
     */
    public RespostaEscolhaSimples() {}

    public RespostaEscolhaSimples(Long id, Date dataResposta, Long questao, Long questionarioAplicado, Integer escolhaSimples, String comentario) {
        super(id, dataResposta, questao, questionarioAplicado);
        this.escolhaSimples = escolhaSimples;
        this.comentario = comentario;
    }

    public Integer getEscolhaSimples() {
        return escolhaSimples;
    }

    public void setEscolhaSimples(int escolhaSimples) {
        this.escolhaSimples = escolhaSimples;
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
        if (!(o instanceof RespostaEscolhaSimples)) return false;
        if (!super.equals(o)) return false;

        RespostaEscolhaSimples that = (RespostaEscolhaSimples) o;

        if (getEscolhaSimples() != null ? !getEscolhaSimples().equals(that.getEscolhaSimples()) : that.getEscolhaSimples() != null)
            return false;
        return getComentario() != null ? getComentario().equals(that.getComentario()) : that.getComentario() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getEscolhaSimples() != null ? getEscolhaSimples().hashCode() : 0);
        result = 31 * result + (getComentario() != null ? getComentario().hashCode() : 0);
        return result;
    }
}
