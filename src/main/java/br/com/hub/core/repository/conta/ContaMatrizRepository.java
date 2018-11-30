package br.com.hub.core.repository.conta;

import br.com.hub.core.repository.conta.models.ContaMatrizEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Francislin Dos Reis on 28/11/2018
 */
@Repository
public interface ContaMatrizRepository extends JpaRepository<ContaMatrizEntity, Long> {

}
