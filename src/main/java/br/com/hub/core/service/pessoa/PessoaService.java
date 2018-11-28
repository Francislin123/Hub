package br.com.hub.core.service.pessoa;

import br.com.hub.core.repository.person.model.LegalPersonEntity;

/**
 * Created by Francislin Dos Reis on 28/11/2018
 */
public interface PessoaService {

    void savePerson(LegalPersonEntity legalPerson);
}
