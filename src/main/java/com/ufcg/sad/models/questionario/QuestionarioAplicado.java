package com.ufcg.sad.models.questionario;

import com.ufcg.sad.models.resposta.Resposta;
import com.ufcg.sad.models.token.Token;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entidade que representa um Questionario Aplicado.
 *
 * @author Arthur Costa
 */

@Entity
public class QuestionarioAplicado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotNull
    private Long idQuestionario;

    @Column
    private Long idProfessor;

    @Column
    @NotNull
    private Long idDisciplina;

    @Transient
    private String disciplina;

    @OneToMany(fetch = FetchType.LAZY,
               cascade=CascadeType.ALL)
    private Set<Resposta> respostas;
    

    @OneToMany(fetch = FetchType.LAZY,
                cascade = CascadeType.ALL)
    private Set<Token> tokens;

    /**
     *
     * Construtor vazio para o hibernate.
     *
     */
    public QuestionarioAplicado() {
        this.respostas = new HashSet<>();
        this.tokens = new HashSet<>();
    }


    public QuestionarioAplicado(Long id,Long idQuestionario, Long idDisciplina, Long idProfessor, String disciplina) {
        this.id = id;
        this.idDisciplina = idDisciplina;
        this.idProfessor = idProfessor;
        this.disciplina = disciplina;
        this.idQuestionario = idQuestionario;
    }

    /**
     * Construtor de questionario aplicado.
     *
     * @param idQuestionario O questionario associado
     * @param idProfessor O professor para o qual o questionario foi aplicado
     * @param idDisciplina A disciplina para a qual o questionario foi aplicada
     * @param respostas As resposta do questionario que foi aplicado
     */
    public QuestionarioAplicado(Long id, Long idQuestionario, Long idProfessor, Long idDisciplina, Set<Resposta> respostas, Set<Token> tokens) {
        this.id = id;
        this.idQuestionario = idQuestionario;
        this.idProfessor = idProfessor;
        this.idDisciplina = idDisciplina;
        this.respostas = respostas;
        this.tokens = tokens;
    }



    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdQuestionario() {
        return idQuestionario;
    }

    public void setIdQuestionario(Long idQuestionario) {
        this.idQuestionario = idQuestionario;
    }

    public Long getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(Long idProfessor) {
        this.idProfessor = idProfessor;
    }

    public Long getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(Long idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public Set<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(Set<Resposta> respostas) {
        this.respostas = respostas;
    }

    public void addResposta(Resposta resposta) {
    	this.respostas.add(resposta);
    }

    public Set<Token> getTokens() {
        return tokens;
    }

    public void setTokens(Set<Token> tokens) {
        this.tokens = tokens;
    }

    public void addToken(Token token) {
    	this.tokens.add(token);
    }
    
    public void removeToken(Token token) {
    	this.tokens.remove(token);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionarioAplicado that = (QuestionarioAplicado) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(idQuestionario, that.idQuestionario) &&
                Objects.equals(idProfessor, that.idProfessor) &&
                Objects.equals(idDisciplina, that.idDisciplina);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idQuestionario, idProfessor, idDisciplina);
    }
}
