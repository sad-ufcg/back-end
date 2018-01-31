package com.ufcg.sad.models.disciplina;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ufcg.sad.models.aluno.Aluno;
import com.ufcg.sad.models.matricula.Matricula;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Classe que representa a lógica de uma disciplina.
 *
 * @author Antunes Dantas
 */
@Entity
@Table
public class Disciplina implements Serializable {

    private static final long serialVersionUID = 8411864352529800054L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotNull
    private String nome;

    @Column
    @NotNull
    private Integer turma;

    @Column
    private Long professorId;

    @Column
    @NotNull
    private String semestre;

    @Column
    private String codigo;

    @OneToMany(mappedBy = "disciplina",
    		fetch = FetchType.LAZY,
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JsonIgnore
    private Set<Matricula> matriculas;

    /**
     * Contrutor padrão para o Hibernate.
     */
    public Disciplina() {
        this.matriculas = new HashSet<>();
    }

    public Disciplina(String nome) {
        this.matriculas = new HashSet<>();
        this.nome = nome;
    }

    /**
     * Construtor para a classe disciplina
     * @param id Id da disciplina.
     * @param nome Nome da disciplina.
     * @param turma Número da turma.
     * @param semestre Semestre a qual a disciplina foi ministrada.
     * @param matriculas Conjunto de matriculas que cursaram a disciplina.
     */
    public Disciplina(Long id, String nome, Integer turma, Long professorId, String semestre, Set<Matricula> matriculas) {
        this.id = id;
        this.nome = nome;
        this.turma = turma;
        this.professorId = professorId;
        this.semestre = semestre;
        this.matriculas = matriculas;
    }

    public Disciplina(String nome, int turma, Long professorId, String semestre, Set<Matricula> matriculas) {
        this.nome = nome;
        this.turma = turma;
        this.professorId = professorId;
        this.semestre = semestre;
        this.matriculas = matriculas;
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

    public Integer getTurma() {
        return turma;
    }

    public void setTurma(Integer turma) {
        this.turma = turma;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public Set<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(Set<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    /**
     * Adiciona um novo aluno a disciplina.
     *
     * @param aluno aluno a ser adicionado.
     */
    public void adicionarAluno(Aluno aluno) {
        Matricula matricula = new Matricula(aluno, this);
        this.matriculas.add(matricula);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disciplina that = (Disciplina) o;
        return id == that.id &&
                turma == that.turma &&
                Objects.equals(nome, that.nome) &&
                Objects.equals(semestre, that.semestre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, turma, semestre);
    }
}
