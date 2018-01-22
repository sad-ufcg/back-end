package com.ufcg.sad.models.resposta;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;

/**
 * Entidade que representa uma resposta genérica
 *
 * @author Arthur Costa
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = RespostaAberta.class, name = "ABERTA"),
    @JsonSubTypes.Type(value = RespostaEscolhaSimples.class, name = "ESCOLHA_SIMPLES"),
    @JsonSubTypes.Type(value = RespostaMultiplaEscolha.class, name = "MULTIPLA_ESCOLHA"),
    @JsonSubTypes.Type(value = RespostaSelecao.class, name = "SELECAO")
    }
)
public abstract class Resposta implements Serializable {

    private static final long serialVersionUID = 1L;

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column
    @Temporal(TemporalType.DATE)
	@JsonFormat(pattern="dd/MM/yyyy")
    private Date dataResposta;

    @Column
    @NotNull
    private Long idQuestao;

    @Column
    @NotNull
    private Long idQuestionarioAplicado;

    /**
     * Construtor vazio para o hibernate
     */
    public Resposta() {}

    /**
     * Construtor de uma resposta generica
     *
     * @param dataResposta Data que a resposta foi feita
     * @param IdQuestao IdQuestao a qual a resposta esta associada
     * @param idQuestionarioAplicado Questionario aplicado o qual a resposta esta associada
     */
    public Resposta(Long id, Date dataResposta, Long idQuestao, Long idQuestionarioAplicado) {
        this.id = id;
        this.dataResposta = dataResposta;
        this.idQuestao = idQuestao;
        this.idQuestionarioAplicado = idQuestionarioAplicado;
    }
    
    public Long getId() {
    	return this.id;
    }
    
    public void setId(Long id) {
    	this.id = id;
    }

    public Date getDataResposta() {
        return dataResposta;
    }

    public void setDataResposta(Date dataResposta) {
        this.dataResposta = dataResposta;
    }

    public Long getIdQuestao() {
        return idQuestao;
    }

    public void setIdQuestao(Long idIdQuestao) {
        this.idQuestao = idIdQuestao;
    }

    public Long getIdQuestionarioAplicado() {
        return idQuestionarioAplicado;
    }

    public void setIdQuestionarioAplicado(Long idQuestionarioAplicado) {
        this.idQuestionarioAplicado = idQuestionarioAplicado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resposta)) return false;

        Resposta resposta = (Resposta) o;

        if (id != null ? !id.equals(resposta.id) : resposta.id != null) return false;
        if (getDataResposta() != null ? !getDataResposta().equals(resposta.getDataResposta()) : resposta.getDataResposta() != null)
            return false;
        if (getIdQuestao() != null ? !getIdQuestao().equals(resposta.getIdQuestao()) : resposta.getIdQuestao() != null)
            return false;
        return getIdQuestionarioAplicado() != null ? getIdQuestionarioAplicado().equals(resposta.getIdQuestionarioAplicado()) : resposta.getIdQuestionarioAplicado() == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (getDataResposta() != null ? getDataResposta().hashCode() : 0);
        result = 31 * result + (getIdQuestao() != null ? getIdQuestao().hashCode() : 0);
        result = 31 * result + (getIdQuestionarioAplicado() != null ? getIdQuestionarioAplicado().hashCode() : 0);
        return result;
    }
}
