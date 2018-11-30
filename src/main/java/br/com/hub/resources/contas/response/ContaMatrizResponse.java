package br.com.hub.resources.contas.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by Francislin Dos Reis on 28/11/2018
 */
@Data
@Builder
public class ContaMatrizResponse {

    private Long id;

    private String nameAccount;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime startDate;

    private Long personFisicaId;

    private Long legalPersonId;
}
