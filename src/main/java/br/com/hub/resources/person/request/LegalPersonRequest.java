package br.com.hub.resources.person.request;

import lombok.Data;

/**
 * Created by Francislin Dos Reis on 28/11/2018
 */
@Data
public class LegalPersonRequest {

    private Long id;

    private String cnpj;

    private String reasonSocial;

    private String nameOfFantasy;
}
