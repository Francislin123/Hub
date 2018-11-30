package br.com.hub.resources.contas.response;

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

    private Long personFisica;

    private Long legalPerson;

    private Long contaMatriz;
}
