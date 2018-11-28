package br.com.hub.core.repository.person.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
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
    public PersonFisicaEntity(String cpf, String nameComplete, LocalDate dateOfBirth) {
        this.cpf = cpf;
        this.nameComplete = nameComplete;
        this.dateOfBirth = dateOfBirth;
    }
}
