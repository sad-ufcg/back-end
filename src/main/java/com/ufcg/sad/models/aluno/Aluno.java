package com.ufcg.sad.models.aluno;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

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

import com.ufcg.sad.models.disciplina.Disciplina;
import com.ufcg.sad.models.matricula.Matricula;
import org.hibernate.validator.constraints.Length;

import static com.ufcg.sad.models.util.Utils.TAMANHO_MAX_STRING;

/**
 * Classe que representa um aluno no sistema.
 *
 * @author Antunes Dantas
 */
@Entity
@Table
public class Aluno implements Serializable {

    private static final long serialVersionUID = 1L;

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
    private String email;

    @OneToMany(mappedBy = "aluno",
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Matricula> disciplinas;

    /**
     * Construtor padrão para o Hibernate.
     */
    public Aluno() {}

    /**
     * @param id Id do aluno no bando de dados.
     * @param nome Nome do aluno.
     * @param email Email do aluno.
     * @param matriculas Conjunto de matriculas onde o aluno está matriculado.
     */
    public Aluno(Long id, String nome, String email, Set<Matricula> matriculas) {
        this.id = id;
        this.nome = nome;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(Set<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    /**
     * Adiciona uma nova Disciplina ao aluno.
     *
     * @param disciplina disciplina a ser adicionada.
     */
    public void adicionarDisciplina(Disciplina disciplina) {
        Matricula matricula = new Matricula(this, disciplina);
        this.disciplinas.add(matricula);
        disciplina.getAlunos().add(matricula);
    }

    /**
     * Remove uma disciplina
     *
     * @param disciplina disciplina a ser removida
     */
    public void removerDisciplina(Disciplina disciplina) {
        for (Matricula matricula : this.disciplinas) {
            if (matricula.getDisciplina().getId().equals(disciplina.getId())) {
                matricula.getDisciplina().getAlunos().remove(matricula);
                this.disciplinas.remove(matricula);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return Objects.equals(id, aluno.id) &&
                Objects.equals(nome, aluno.nome) &&
                Objects.equals(email, aluno.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email);
    }
}
