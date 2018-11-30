package br.com.hub.resources.contas.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Francislin Dos Reis on 28/11/2018
 */
@Data
public class ContaMatrizRequest {

    private String nameAccount;

    private BigDecimal AccountCredit;

    private String situacaoDesc;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime startDate;

    private Long personFisicaId;

    private Long legalPersonId;

    private List<String> names;
}
