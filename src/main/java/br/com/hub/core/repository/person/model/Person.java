package br.com.hub.core.repository.person.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * Created by Francislin Dos Reis on 28/11/2018
 */
@Entity
@Data
@Table(name = "pessoa")
public class Person {

    private String cpf;

    private String nameComplete;

    private LocalDate dateOfBirth;

    @Tolerate
    public Person() {
        // Default method constructor for hibernate
    }

    @Builder
    public Person(String cpf, String nameComplete, LocalDate dateOfBirth) {
        this.cpf = cpf;
        this.nameComplete = nameComplete;
        this.dateOfBirth = dateOfBirth;
    }
}
