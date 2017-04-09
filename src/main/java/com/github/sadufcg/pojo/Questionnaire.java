package com.github.sadufcg.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Antunes Dantas on 31/03/17.
 */
@Table
@Entity
public class Questionnaire {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Questionnaire() {}

    public Questionnaire(Long id) { this.id = id; }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}