package br.com.hub.resources.contas.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by Francislin Dos Reis on 30/11/2018
 */
@Data
public class ContaFilialRequest {

    private String nameAccount;

    private BigDecimal AccountCredit;

    private String situacaoDesc;

    private LocalDateTime startDate;

    private Long personFisicaId;

    private Long legalPersonId;

    private Long contaMatrizId;
}
