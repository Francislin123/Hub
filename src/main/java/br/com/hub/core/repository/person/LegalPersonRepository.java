package br.com.hub.core.repository.person;

import br.com.hub.core.repository.person.model.LegalPersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Francislin Dos Reis on 28/11/2018
 */
@Repository
public interface LegalPersonRepository extends JpaRepository<LegalPersonEntity, Long> {

}
