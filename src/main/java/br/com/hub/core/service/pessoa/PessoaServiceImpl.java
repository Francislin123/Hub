package br.com.hub.core.service.pessoa;

import br.com.hub.core.repository.person.LegalPersonRepository;
import br.com.hub.core.repository.person.model.LegalPersonEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Francislin Dos Reis on 28/11/2018
 */
@Slf4j
@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private LegalPersonRepository legalPersonRepository;

    @Override
    public void savePerson(LegalPersonEntity legalPerson) {

        LegalPersonEntity saveLegalPerson =
                LegalPersonEntity.builder()
                        .cnpj(legalPerson.getCnpj())
                        .reasonSocial(legalPerson.getReasonSocial())
                        .nameOfFantasy(legalPerson.getNameOfFantasy())
                        .build();

        persistiLegalPerson(saveLegalPerson);

    }

    private LegalPersonEntity persistiLegalPerson(LegalPersonEntity legalPersonEntity) {
        LegalPersonEntity LegalPerson = legalPersonRepository.save(legalPersonEntity);
        log.info("fichaEntity={} message=insert_successfully", legalPersonEntity);
        return LegalPerson;
    }
}
