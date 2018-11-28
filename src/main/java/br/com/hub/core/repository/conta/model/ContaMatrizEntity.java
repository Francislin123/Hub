package br.com.hub.core.repository.conta.model;

import br.com.hub.core.repository.person.model.Person;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Created by Francislin Dos Reis on 28/11/2018
 */
@Entity
@Data
@Table(name = "conta_matriz")
public class ContaMatrizEntity {

    private String nameAccount;

    private LocalDateTime startDate;

    @ManyToOne
    private Person person;

    @Tolerate
    public ContaMatrizEntity() {
        // Default method constructor for hibernate
    }

    @Builder
    public ContaMatrizEntity(String nameAccount, LocalDateTime dateOfInit) {
        this.nameAccount = nameAccount;
        this.startDate = dateOfInit;
    }
}
