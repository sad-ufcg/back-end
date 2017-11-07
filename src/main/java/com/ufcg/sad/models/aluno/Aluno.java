package com.ufcg.sad.models.aluno;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Matricula> disciplinas;

    /**
     * Construtor padrão para o Hibernate.
     */
    public Aluno() {}

    /**
     * @param id Id do aluno no bando de dados.
     * @param nome Nome do aluno.
     * @param email Email do aluno.
     * @param disciplinas Conjunto de disciplinas onde o aluno está matriculado.
     */
    public Aluno(Long id, String nome, String email, Set<Matricula> disciplinas) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.disciplinas = disciplinas;
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

    public Set<Matricula> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(Set<Matricula> disciplinas) {
        this.disciplinas = disciplinas;
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
