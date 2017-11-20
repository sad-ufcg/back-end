package com.ufcg.sad.models.questionario.resposta;

import com.ufcg.sad.models.questao.Questao;
import com.ufcg.sad.models.questionario.QuestionarioAplicado;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Entidade que representa uma resposta genérica
 *
 * @author Arthur Costa
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Resposta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column
    @Temporal(TemporalType.DATE)
    private Date  dataResposta;

    @OneToOne
    private Questao questao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private QuestionarioAplicado questionarioAplicado;

    /**
     * Construtor vazio para o hibernate
     */
    public Resposta() {}

    /**
     * Construtor de uma resposta generica
     *
     * @param dataResposta Data que a resposta foi feita
     * @param questao Questao a qual a resposta esta associada
     * @param questionarioAplicado Questionario aplicado o qual a resposta esta associada
     */
    public Resposta(Date dataResposta, Questao questao, QuestionarioAplicado questionarioAplicado) {
        this.dataResposta = dataResposta;
        this.questao = questao;
        this.questionarioAplicado = questionarioAplicado;
    }

    public Date getDataResposta() {
        return dataResposta;
    }

    public void setDataResposta(Date dataResposta) {
        this.dataResposta = dataResposta;
    }

    public Questao getQuestao() {
        return questao;
    }

    public void setQuestao(Questao questao) {
        this.questao = questao;
    }

    public QuestionarioAplicado getQuestionarioAplicado() {
        return questionarioAplicado;
    }

    public void setQuestionarioAplicado(QuestionarioAplicado questionarioAplicado) {
        this.questionarioAplicado = questionarioAplicado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resposta)) return false;

        Resposta resposta = (Resposta) o;

        if (id != null ? !id.equals(resposta.id) : resposta.id != null) return false;
        if (getDataResposta() != null ? !getDataResposta().equals(resposta.getDataResposta()) : resposta.getDataResposta() != null)
            return false;
        if (getQuestao() != null ? !getQuestao().equals(resposta.getQuestao()) : resposta.getQuestao() != null)
            return false;
        return getQuestionarioAplicado() != null ? getQuestionarioAplicado().equals(resposta.getQuestionarioAplicado()) : resposta.getQuestionarioAplicado() == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (getDataResposta() != null ? getDataResposta().hashCode() : 0);
        result = 31 * result + (getQuestao() != null ? getQuestao().hashCode() : 0);
        result = 31 * result + (getQuestionarioAplicado() != null ? getQuestionarioAplicado().hashCode() : 0);
        return result;
    }
}
