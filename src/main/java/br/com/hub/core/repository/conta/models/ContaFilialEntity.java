package br.com.hub.core.repository.conta.models;

import br.com.hub.core.repository.person.model.LegalPersonEntity;
import br.com.hub.core.repository.person.model.PersonFisicaEntity;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by Francislin Dos Reis on 28/11/2018
 */
@Entity
@Data
@Table(name = "conta_filial")
public class ContaFilialEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String nameAccount;

    private BigDecimal AccountCredit;

    private LocalDateTime startDate;

    @ManyToOne
    private PersonFisicaEntity personFisica;

    @ManyToOne
    private LegalPersonEntity legalPerson;

    @ManyToOne
    private ContaMatrizEntity contaMatriz;

    @ManyToOne
    private ContaFilialEntity contaFilial;

    @ManyToOne
    private Situacao situacao;

    @Tolerate
    public ContaFilialEntity() {
        // Default method constructor for hibernate
    }

    @Builder
    public ContaFilialEntity(Long id, String nameAccount, BigDecimal accountCredit, LocalDateTime startDate,
                             PersonFisicaEntity personFisica, LegalPersonEntity legalPerson, ContaMatrizEntity contaMatriz,
                             ContaFilialEntity contaFilial, Situacao situacao) {
        this.id = id;
        this.nameAccount = nameAccount;
        AccountCredit = accountCredit;
        this.startDate = startDate;
        this.personFisica = personFisica;
        this.legalPerson = legalPerson;
        this.contaMatriz = contaMatriz;
        this.contaFilial = contaFilial;
        this.situacao = situacao;
    }
}
