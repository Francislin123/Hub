package br.com.hub.core.service.conta;

import br.com.hub.core.repository.conta.models.ContaFilialEntity;
import br.com.hub.core.repository.conta.models.ContaMatrizEntity;

import java.util.List;

/**
 * Created by Francislin Dos Reis on 28/11/2018
 */
public interface ContaMatrizService {

    void saveContaMatriz(ContaMatrizEntity contaMatriz);

    void updateContaMatriz(ContaMatrizEntity contaMatriz);

    ContaFilialEntity findByName(String name);

    void deleteContaMatriz(Long id);

    List<ContaMatrizEntity> findAllContaMatriz();
}
