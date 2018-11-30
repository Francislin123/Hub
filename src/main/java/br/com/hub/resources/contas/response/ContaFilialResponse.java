package br.com.hub.resources.contas.response;

import br.com.hub.core.repository.conta.models.ContaFilialEntity;
import br.com.hub.core.repository.conta.models.ContaMatrizEntity;
import br.com.hub.core.repository.person.model.LegalPersonEntity;
import br.com.hub.core.repository.person.model.PersonFisicaEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by Francislin Dos Reis on 28/11/2018
 */
@Data
@Builder
public class ContaFilialResponse {

    private Long id;

    private String nameAccount;

    private LocalDateTime startDate;

    private PersonFisicaEntity personFisica;

    private LegalPersonEntity legalPerson;

    private ContaMatrizEntity contaMatriz;

    private ContaFilialEntity contaFilial;
}
