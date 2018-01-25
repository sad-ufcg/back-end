package com.ufcg.sad.models.resposta;

import com.ufcg.sad.models.opcao.Opcao;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@DiscriminatorValue(value = "SELECAO")
public class RespostaSelecao extends Resposta implements Serializable {

    private static final long serialVersionUID = 1L;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @NotNull
    private List<Opcao> opcoesSelecionadas;

    /**
     * Construtor vazio para o hibernate
     */
    public RespostaSelecao() { this.opcoesSelecionadas = new ArrayList<Opcao>();}

    public RespostaSelecao(Long id, Date dataResposta, Long questao, Long questionarioAplicado, List<Opcao> opcoesSelecionadas) {
        super(id, dataResposta, questao, questionarioAplicado);
        this.opcoesSelecionadas = opcoesSelecionadas;
    }

    public List<Opcao> getOpcoesSelecionadas() {
        return opcoesSelecionadas;
    }

    public void setOpcoesSelecionadas(List<Opcao> opcoesSelecionadas) {
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
