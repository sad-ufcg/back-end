package com.ufcg.sad.models.disciplina;

import com.ufcg.sad.models.matricula.Matricula;
import com.ufcg.sad.models.professor.Professor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * Classe que representa a lógica de uma Disciplina.
 *
 * @author Antunes Dantas
 */
@Entity
@Table
public class Disciplina implements Serializable {

    private static final long serialVersionUID = 8411864352529800054L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    @NotNull
    private String nome;

    @Column
    @NotNull
    private int turma;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Professor professor;

    @Column
    @NotNull
    private String semestre;

    @Column
    private String codigo;

    @OneToMany(mappedBy = "disciplina",
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Matricula> alunos;

    /**
     * Contrutor padrão para o Hibernate.
     */
    public Disciplina() {}

    /**
     * Construtor para a classe Disciplina
     * @param id Id da disciplina.
     * @param nome Nome da disciplina.
     * @param turma Número da turma.
     * @param professor Professor responsável pela disciplina.
     * @param semestre Semestre a qual a disciplina foi ministrada.
     * @param alunos Conjunto de alunos que cursaram a disciplina.
     */
    public Disciplina(Long id, String nome, int turma, Professor professor, String semestre, Set<Matricula> alunos) {
        this.id = id;
        this.nome = nome;
        this.turma = turma;
        this.professor = professor;
        this.semestre = semestre;
        this.alunos = alunos;
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

    public int getTurma() {
        return turma;
    }

    public void setTurma(int turma) {
        this.turma = turma;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public Set<Matricula> getAlunos() {
        return alunos;
    }

    public void setAlunos(Set<Matricula> alunos) {
        this.alunos = alunos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disciplina that = (Disciplina) o;
        return id == that.id &&
                turma == that.turma &&
                Objects.equals(nome, that.nome) &&
                Objects.equals(professor, that.professor) &&
                Objects.equals(semestre, that.semestre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, turma, professor, semestre);
    }
}
