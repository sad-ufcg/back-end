package com.ufcg.sad.models.professor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ufcg.sad.models.disciplina.Disciplina;
import com.ufcg.sad.models.questionario.QuestionarioAplicado;
import org.hibernate.validator.constraints.Length;

import static com.ufcg.sad.models.util.Utils.TAMANHO_MAX_STRING;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Classe que representa um Professor.
 *
 * @author Antunes Dantas
 */
@Entity
@Table
public class Professor implements Serializable {

    private static final long serialVersionUID = 4472829715476139640L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String siape;

    @Column
    @NotNull
    @Length(max = TAMANHO_MAX_STRING)
    private String nome;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "professor")
    private Set<Disciplina> disciplinas;

    @OneToOne(mappedBy = "professor", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private QuestionarioAplicado questionarioAplicado;

    /**
     * Construtor padrão para o Hibernate.
     */
    public Professor() {
        this.disciplinas = new HashSet<>();
    }

    /**
     * Construtor para classe Professor.
     * @param siape Código de identificação único do professor.
     * @param nome Nome do professor.
     * @param disciplinas Conjunto de Disciplinas do professor.
     * @param questionarioAplicado Questionario aplicado pelo professor.
     */
    public Professor(String siape, String nome, Set<Disciplina> disciplinas, QuestionarioAplicado questionarioAplicado) {
        this.siape = siape;
        this.nome = nome;
        this.disciplinas = disciplinas;
        this.questionarioAplicado = questionarioAplicado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSiape() {
        return siape;
    }

    public void setSiape(String siape) {
        this.siape = siape;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(Set<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public void addDisciplina(Disciplina disciplina) {
        this.disciplinas.add(disciplina);
    }

    public QuestionarioAplicado getQuestionarioAplicado() { return questionarioAplicado; }

    public void setQuestionarioAplicado(QuestionarioAplicado questionarioAplicado) { this.questionarioAplicado = questionarioAplicado; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Professor professor = (Professor) o;
        return Objects.equals(id, professor.id) &&
                Objects.equals(siape, professor.siape) &&
                Objects.equals(nome, professor.nome) &&
                Objects.equals(disciplinas, professor.disciplinas) &&
                Objects.equals(questionarioAplicado, professor.questionarioAplicado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, siape, nome, disciplinas, questionarioAplicado);
    }
}
