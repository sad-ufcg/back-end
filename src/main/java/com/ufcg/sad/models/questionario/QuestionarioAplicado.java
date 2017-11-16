package com.ufcg.sad.models.questionario;

import com.ufcg.sad.models.disciplina.Disciplina;
import com.ufcg.sad.models.professor.Professor;
import com.ufcg.sad.models.questionario.resposta.Resposta;

import javax.persistence.*;
import java.io.Serializable;
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
    @ManyToOne
    private Questionario questionario;

    @Column
    @OneToOne
    private Professor professor;

    @Column
    @OneToOne
    private Disciplina disciplina;

    @Column
    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "QuestionarioAplicado")
    private Set<Resposta> respostas;

    /**
     *
     * Construtor vazio para o hibernate.
     *
     */
    public QuestionarioAplicado() {}

    /**
     * Construtor de questionario aplicado.
     *
     * @param questionario O questionario associado
     * @param professor O professor para o qual o questionario foi aplicado
     * @param disciplina A disciplina para a qual o questionario foi aplicada
     * @param respostas As respostas do questionario que foi aplicado
     */
    public QuestionarioAplicado(Questionario questionario, Professor professor, Disciplina disciplina, Set<Resposta> respostas) {
        this.questionario = questionario;
        this.professor = professor;
        this.disciplina = disciplina;
        this.respostas = respostas;
    }

    public Questionario getQuestionario() {
        return questionario;
    }

    public void setQuestionario(Questionario questionario) {
        this.questionario = questionario;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Set<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(Set<Resposta> respostas) {
        this.respostas = respostas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuestionarioAplicado)) return false;

        QuestionarioAplicado that = (QuestionarioAplicado) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (getQuestionario() != null ? !getQuestionario().equals(that.getQuestionario()) : that.getQuestionario() != null)
            return false;
        if (getProfessor() != null ? !getProfessor().equals(that.getProfessor()) : that.getProfessor() != null)
            return false;
        if (getDisciplina() != null ? !getDisciplina().equals(that.getDisciplina()) : that.getDisciplina() != null)
            return false;
        return getRespostas() != null ? getRespostas().equals(that.getRespostas()) : that.getRespostas() == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (getQuestionario() != null ? getQuestionario().hashCode() : 0);
        result = 31 * result + (getProfessor() != null ? getProfessor().hashCode() : 0);
        result = 31 * result + (getDisciplina() != null ? getDisciplina().hashCode() : 0);
        result = 31 * result + (getRespostas() != null ? getRespostas().hashCode() : 0);
        return result;
    }
}
