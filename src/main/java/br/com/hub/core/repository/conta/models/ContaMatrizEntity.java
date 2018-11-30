package br.com.hub.core.repository.conta.models;

import br.com.hub.core.repository.person.model.LegalPersonEntity;
import br.com.hub.core.repository.person.model.PersonFisicaEntity;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;

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

    private BigDecimal AccountCredit;

    private LocalDateTime startDate;

    @ManyToOne
    private PersonFisicaEntity personFisicaEntity;

    @ManyToOne
    private LegalPersonEntity legalPerson;

    @OneToMany(mappedBy = "contaMatriz", cascade = CascadeType.ALL)
    private Collection<ContaFilialEntity> contaFilial;

    @ManyToOne
    private Situacao situacao;

    @Tolerate
    public ContaMatrizEntity() {
        // Default method constructor for hibernate
    }

    @Builder
    public ContaMatrizEntity(Long id, String nameAccount, BigDecimal accountCredit, LocalDateTime startDate,
                             PersonFisicaEntity personFisicaEntity, LegalPersonEntity legalPerson,
                             Collection<ContaFilialEntity> contaFilial, Situacao situacao) {
        this.id = id;
        this.nameAccount = nameAccount;
        AccountCredit = accountCredit;
        this.startDate = startDate;
        this.personFisicaEntity = personFisicaEntity;
        this.legalPerson = legalPerson;
        this.contaFilial = contaFilial;
        this.situacao = situacao;
    }
}
