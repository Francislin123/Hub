package br.com.hub.core.repository.conta;

import br.com.hub.core.repository.conta.models.Situacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Francislin Dos Reis on 06/09/2018
 */
@Repository
public interface SituacaoRepository extends JpaRepository<Situacao, Long> {

    Optional<Situacao> findByDesc(String desc);
}
