package br.com.hub.core.repository.conta.models;

import br.com.hub.core.repository.person.model.LegalPersonEntity;
import br.com.hub.core.repository.person.model.PersonFisicaEntity;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Francislin Dos Reis on 28/11/2018
 */
@Entity
@Data
@Table(name = "conta_matriz")
public class ContaMatrizEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String nameAccount;

    private LocalDateTime startDate;

    @ManyToOne
    private PersonFisicaEntity personFisicaEntity;

    @ManyToOne
    private LegalPersonEntity legalPerson;

    @Tolerate
    public ContaMatrizEntity() {
        // Default method constructor for hibernate
    }

    @Builder
    public ContaMatrizEntity(String nameAccount, LocalDateTime startDate, PersonFisicaEntity personFisicaEntity, LegalPersonEntity legalPerson) {
        this.nameAccount = nameAccount;
        this.startDate = startDate;
        this.personFisicaEntity = personFisicaEntity;
        this.legalPerson = legalPerson;
    }
}
