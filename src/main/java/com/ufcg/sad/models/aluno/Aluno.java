package com.ufcg.sad.models.aluno;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ufcg.sad.models.disciplina.Disciplina;
import com.ufcg.sad.models.matricula.Matricula;

import org.hibernate.validator.constraints.Email;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotNull
    @Length(max = TAMANHO_MAX_STRING)
    private String nome;

    @Column
    @NotNull
    @Length(max = TAMANHO_MAX_STRING)
    private String sobrenome;

    @Column(unique = true)
    @NotNull
    @Length(max = TAMANHO_MAX_STRING)
    @Email(message = "Email inválido.")
    private String email;

    @OneToMany(mappedBy = "aluno",
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JsonIgnore
    private Set<Matricula> matriculas;

    /**
     * Construtor padrão para o Hibernate.
     */
    public Aluno() {
        this.matriculas = new HashSet<>();
    }

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
     * Adiciona uma nova disciplina ao aluno.
     *
     * @param disciplina disciplina a ser adicionada.
     */
    public void adicionarDisciplina(Disciplina disciplina) {
        Matricula matricula = new Matricula(this, disciplina);
        this.matriculas.add(matricula);
    }

    /**
     * Remove uma disciplina
     *
     * @param disciplina disciplina a ser removida
     */
    public void removerDisciplina(Disciplina disciplina) {
        Iterator<Matricula> iterator = this.matriculas.iterator();
        Matricula matriculaRemovida = (Matricula) iterator.next();
        while (iterator.hasNext() && !matriculaRemovida.getDisciplina().equals(disciplina)) {
            matriculaRemovida = (Matricula) iterator.next();
        }

        matriculaRemovida.getDisciplina().getMatriculas().remove(matriculaRemovida);
        this.matriculas.remove(matriculaRemovida);
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return Objects.equals(id, aluno.id) &&
                Objects.equals(nome, aluno.nome) &&
                Objects.equals(sobrenome, aluno.sobrenome) &&
                Objects.equals(email, aluno.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, sobrenome, email);
    }
}
