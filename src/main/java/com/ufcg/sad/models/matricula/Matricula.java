package com.ufcg.sad.models.matricula;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ufcg.sad.models.aluno.Aluno;
import com.ufcg.sad.models.disciplina.Disciplina;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Classe que representa a relação entre uma disciplina e um aluno.
 */
@Entity
@Table
public class Matricula implements Serializable {

    private static final long serialVersionUID = -104350015797590113L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @NotNull
    @JsonBackReference
    private Aluno aluno;

    @ManyToOne
    @NotNull
    @JsonBackReference
    private Disciplina disciplina;

    /**
     * Construtor padrão para Matrícula.
     */
    public Matricula() {  }

    public Matricula(Aluno aluno, Disciplina disciplina) {
        this.aluno = aluno;
        this.disciplina = disciplina;
    }

    /**
     * Construtor para a classe Matricula
     *
     * @param id Id da matrícula.
     * @param aluno Aluno matriculado na disciplina.
     * @param disciplina disciplina a qual o aluno está matriculado.
     */
    public Matricula(Long id, Aluno aluno, Disciplina disciplina) {
        this.id = id;
        this.aluno = aluno;
        this.disciplina = disciplina;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matricula matricula = (Matricula) o;
        return Objects.equals(aluno, matricula.aluno) &&
                Objects.equals(disciplina, matricula.disciplina);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aluno, disciplina);
    }
}
