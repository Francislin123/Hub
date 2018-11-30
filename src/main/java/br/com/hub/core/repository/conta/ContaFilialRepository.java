package br.com.hub.core.repository.conta;

import br.com.hub.core.repository.conta.models.ContaFilialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Francislin Dos Reis on 28/11/2018
 */
@Repository
public interface ContaFilialRepository extends JpaRepository<ContaFilialEntity, Long> {

    Optional<ContaFilialEntity> findByNameAccount(String nameAccount);
}
