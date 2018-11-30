package br.com.hub.core.repository.person.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * Created by Francislin Dos Reis on 28/11/2018
 */
@Entity
@Data
@Table(name = "pessoa")
public class PersonFisicaEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String cpf;

    private String nameComplete;

    private LocalDate dateOfBirth;

    @Tolerate
    public PersonFisicaEntity() {
        // Default method constructor for hibernate
    }

    @Builder
    public PersonFisicaEntity(Long id, String cpf, String nameComplete, LocalDate dateOfBirth) {
        this.id = id;
        this.cpf = cpf;
        this.nameComplete = nameComplete;
        this.dateOfBirth = dateOfBirth;
    }
}
