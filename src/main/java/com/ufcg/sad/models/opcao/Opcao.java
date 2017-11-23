package com.ufcg.sad.models.opcao;

import static com.ufcg.sad.models.util.Utils.TAMANHO_MAX_STRING;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.ufcg.sad.models.resposta.RespostaMultiplaEscolha;
import com.ufcg.sad.models.resposta.RespostaSelecao;
import org.hibernate.validator.constraints.Length;

import com.ufcg.sad.models.questao.Questao;

@Entity
public class Opcao {
	
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    @NotNull
    @Length(max = TAMANHO_MAX_STRING)
    private String nome;

	@Column
    @NotNull
    @Length(max = TAMANHO_MAX_STRING)
    private String descricao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Questao questao;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private RespostaMultiplaEscolha respostaMultiplaEscolha;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private RespostaSelecao respostaSelecao;
    
    /**
     * Contrutor padrão para o Hibernate.
     */
    public Opcao() {}

    /**
     * Construtor para a classe Opcao
     * @param id Id da opcao.
     * @param nome Nome da opcao.
	 * @param descricao Descricao da opcao.
	 * @param questao Questao relacionada a opcao.
	 * @param respostaMultiplaEscolha Resposta multipla escolha associada a opcao.
	 * @param respostaSelecao Resposta selecao associada a opcao.
     */
    public Opcao(Long id, String nome, String descricao, Questao questao, RespostaMultiplaEscolha respostaMultiplaEscolha, RespostaSelecao respostaSelecao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.questao = questao;
        this.respostaMultiplaEscolha = respostaMultiplaEscolha;
        this.respostaSelecao = respostaSelecao;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((questao == null) ? 0 : questao.hashCode());
		result = prime * result + ((respostaMultiplaEscolha == null) ? 0 : respostaMultiplaEscolha.hashCode());
		result = prime * result + ((respostaSelecao == null) ? 0 : respostaSelecao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Opcao other = (Opcao) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (questao == null) {
			if (other.questao != null)
				return false;
		} else if (!questao.equals(other.questao))
			return false;
		if (respostaMultiplaEscolha == null) {
			if (other.respostaMultiplaEscolha != null)
				return false;
		} else if (!respostaMultiplaEscolha.equals(other.respostaMultiplaEscolha))
			return false;
		if (respostaSelecao == null) {
			if (other.respostaSelecao!= null)
				return false;
		} else if (!respostaSelecao.equals(other.respostaSelecao))
			return false;
		return true;
	}

}
