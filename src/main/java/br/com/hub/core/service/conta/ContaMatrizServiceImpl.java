package br.com.hub.core.service.conta;

import br.com.hub.core.exceptions.EntityNotFoundException;
import br.com.hub.core.exceptions.UserException;
import br.com.hub.core.repository.conta.ContaFilialRepository;
import br.com.hub.core.repository.conta.ContaMatrizRepository;
import br.com.hub.core.repository.conta.models.ContaFilialEntity;
import br.com.hub.core.repository.conta.models.ContaMatrizEntity;
import br.com.hub.core.repository.person.LegalPersonRepository;
import br.com.hub.core.repository.person.PersonFiscalRepository;
import br.com.hub.core.repository.person.model.LegalPersonEntity;
import br.com.hub.core.repository.person.model.PersonFisicaEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Francislin Dos Reis on 28/11/2018
 */
@Slf4j
@Service
public class ContaMatrizServiceImpl implements ContaMatrizService {

    @Autowired
    private ContaMatrizRepository contaMatrizRepository;

    @Autowired
    private ContaFilialRepository contaFilialRepository;

    @Autowired
    private LegalPersonRepository legalPersonRepository;

    @Autowired
    private PersonFiscalRepository personFiscalRepository;

    @Override
    public void saveContaMatriz(ContaMatrizEntity contaMatriz) {

        LegalPersonEntity legalPerson = getLegalPerson(contaMatriz);

        PersonFisicaEntity personFisicaEntity = getPersonFisica(contaMatriz);

        Set<ContaFilialEntity> contaFilial = getContaFilial(contaMatriz);

        ContaMatrizEntity contaMatrizEntity =
                ContaMatrizEntity.builder()
                        .nameAccount(contaMatriz.getNameAccount())
                        .legalPerson(legalPerson)
                        .personFisicaEntity(personFisicaEntity)
                        .startDate(contaMatriz.getStartDate())
                        .contaFilial(contaFilial)
                        .build();

        persistiContaMatriz(contaMatrizEntity);
    }

    @Override
    public void updateContaMatriz(ContaMatrizEntity contaMatriz) {

        LegalPersonEntity legalPerson = getLegalPerson(contaMatriz);

        PersonFisicaEntity personFisicaEntity = getPersonFisica(contaMatriz);

        Set<ContaFilialEntity> contaFilial = getContaFilial(contaMatriz);

        ContaMatrizEntity updateContaMatriz = findById(contaMatriz.getId());

        ContaMatrizEntity contaMatrizEntity =
                ContaMatrizEntity.builder()
                        .id(updateContaMatriz.getId())
                        .nameAccount(contaMatriz.getNameAccount())
                        .legalPerson(legalPerson)
                        .personFisicaEntity(personFisicaEntity)
                        .startDate(updateContaMatriz.getStartDate())
                        .contaFilial(contaFilial)
                        .build();

        persistiContaMatriz(contaMatrizEntity);
    }

    private ContaMatrizEntity findById(Long id) {
        return contaMatrizRepository.findById(id).orElseThrow(() -> new
                EntityNotFoundException(String.format("Account contaFilial='%s' not found", id)));
    }

    @Override
    public ContaFilialEntity findByName(String name) {
        return contaFilialRepository.findByNameAccount(name).orElseThrow(() -> new UserException(
                String.format("Name for Account is not fold for name='%s' ", name)));
    }

    @Override
    public void deleteContaMatriz(Long id) {
        ContaMatrizEntity contaMatrizEntity = findById(id);
        log.info("docaEntity={} message=delete_successfully");
        this.contaMatrizRepository.delete(contaMatrizEntity);
    }

    @Override
    public List<ContaMatrizEntity> findAllContaMatriz() {
        return contaMatrizRepository.findAll();
    }

    private ContaMatrizEntity persistiContaMatriz(ContaMatrizEntity contaMatrizEntity) {
        ContaMatrizEntity contaMatriz = contaMatrizRepository.save(contaMatrizEntity);
        log.info("contaMatrizEntity={} message=insert_successfully", contaMatrizEntity);
        return contaMatriz;
    }

    private Set<ContaFilialEntity> getContaFilial(ContaMatrizEntity contaMatriz) {

        if (contaMatriz.getContaFilial() == null) {
            return null;
        }

        Set<ContaFilialEntity> response = new HashSet<>();

        contaMatriz.getContaFilial().forEach(contaFilialEntity ->
                response.add(contaFilialRepository.findByNameAccount(contaFilialEntity.getNameAccount()).get()));

        return response;
    }

    private PersonFisicaEntity getPersonFisica(ContaMatrizEntity contaMatriz) {

        PersonFisicaEntity personFisica = contaMatriz.getPersonFisicaEntity();

        if (personFisica == null || personFisica.getId() == null) {
            return null;
        }

        return personFiscalRepository.findById(contaMatriz.getPersonFisicaEntity().getId()).orElseThrow(() ->
                new EntityNotFoundException(String.format("Person fisicaEntity personFisica='%s' not found",
                        contaMatriz.getPersonFisicaEntity().getId())));
    }

    private LegalPersonEntity getLegalPerson(ContaMatrizEntity contaMatriz) {

        LegalPersonEntity legalPerson = contaMatriz.getLegalPerson();

        if (legalPerson == null || legalPerson.getId() == null) {
            return null;
        }

        return legalPersonRepository.findById(contaMatriz.getLegalPerson().getId()).orElseThrow(() ->
                new EntityNotFoundException(String.format("Legal person legalPerson='%s' not found",
                        contaMatriz.getLegalPerson().getId())));
    }
}
