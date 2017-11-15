package com.ufcg.sad.models.matricula;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Representação do id de Matrícula.
 */
@Embeddable
public class IdMatricula implements Serializable {

    @Column
    private Long idAluno;

    @Column
    private Long idDisciplina;

    public IdMatricula() {}

    public IdMatricula(Long idAluno, Long idDisciplina) {
        this.idAluno = idAluno;
        this.idDisciplina = idDisciplina;
    }

    public Long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Long idAluno) {
        this.idAluno = idAluno;
    }

    public Long getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(Long idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdMatricula that = (IdMatricula) o;
        return Objects.equals(idAluno, that.idAluno) &&
                Objects.equals(idDisciplina, that.idDisciplina);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAluno, idDisciplina);
    }
}
