package com.ufcg.sad.models.resposta;

import com.ufcg.sad.models.opcao.Opcao;
import com.ufcg.sad.models.questao.Questao;
import com.ufcg.sad.models.questionario.QuestionarioAplicado;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
public class RespostaSelecao extends Resposta implements Serializable {

    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "respostaSelecao", fetch = FetchType.LAZY)
    private Set<Opcao> opcoesSelecionadas;

    /**
     * Construtor vazio para o hibernate
     */
    public RespostaSelecao() {}

    public RespostaSelecao(Date dataResposta, Questao questao, QuestionarioAplicado questionarioAplicado, Set<Opcao> opcoesSelecionadas) {
        super(dataResposta, questao, questionarioAplicado);
        this.opcoesSelecionadas = opcoesSelecionadas;
    }

    public Set<Opcao> getOpcoesSelecionadas() {
        return opcoesSelecionadas;
    }

    public void setOpcoesSelecionadas(Set<Opcao> opcoesSelecionadas) {
        this.opcoesSelecionadas = opcoesSelecionadas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RespostaSelecao)) return false;
        if (!super.equals(o)) return false;

        RespostaSelecao that = (RespostaSelecao) o;

        return getOpcoesSelecionadas() != null ? getOpcoesSelecionadas().equals(that.getOpcoesSelecionadas()) : that.getOpcoesSelecionadas() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getOpcoesSelecionadas() != null ? getOpcoesSelecionadas().hashCode() : 0);
        return result;
    }
}
