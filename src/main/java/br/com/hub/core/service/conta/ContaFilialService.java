package br.com.hub.core.service.conta;

import br.com.hub.core.repository.conta.models.ContaFilialEntity;

import java.util.List;

/**
 * Created by Francislin Dos Reis on 30/11/2018
 */
public interface ContaFilialService {

    void saveContaFilial(ContaFilialEntity contaFilial);

    void updateContaFilial(ContaFilialEntity contaFilial);

    List<ContaFilialEntity> findAllContaFilial();

    void deleteAffiliateAccount(Long id);
}
