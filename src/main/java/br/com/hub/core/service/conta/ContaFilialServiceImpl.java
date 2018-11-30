package br.com.hub.core.service.conta;

import br.com.hub.core.exceptions.EntityNotFoundException;
import br.com.hub.core.exceptions.UserException;
import br.com.hub.core.repository.conta.ContaFilialRepository;
import br.com.hub.core.repository.conta.ContaMatrizRepository;
import br.com.hub.core.repository.conta.SituacaoRepository;
import br.com.hub.core.repository.conta.models.ContaFilialEntity;
import br.com.hub.core.repository.conta.models.ContaMatrizEntity;
import br.com.hub.core.repository.conta.models.Situacao;
import br.com.hub.core.repository.person.LegalPersonRepository;
import br.com.hub.core.repository.person.PersonFiscalRepository;
import br.com.hub.core.repository.person.model.LegalPersonEntity;
import br.com.hub.core.repository.person.model.PersonFisicaEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Francislin Dos Reis on 30/11/2018
 */
@Slf4j
@Service
public class ContaFilialServiceImpl implements ContaFilialService {

    @Autowired
    private ContaFilialRepository contaFilialRepository;

    @Autowired
    private ContaMatrizRepository contaMatrizRepository;

    @Autowired
    private PersonFiscalRepository personFiscalRepository;

    @Autowired
    private LegalPersonRepository legalPersonRepository;

    @Autowired
    private SituacaoRepository situacaoRepository;

    @Override
    public void saveContaFilial(ContaFilialEntity contaFilial) {

        LegalPersonEntity legalPerson = getLegalPerson(contaFilial);

        PersonFisicaEntity personFisicaEntity = getPersonFisica(contaFilial);

        ContaMatrizEntity contaMatrizEntity = getContaMatriz(contaFilial);

        ContaFilialEntity contaFilialEntity = getContaFilial(contaFilial);

        Situacao situacao = getSituation(contaFilial);

        ContaFilialEntity saveContaFilial =
                ContaFilialEntity.builder()
                        .nameAccount(contaFilial.getNameAccount())
                        .startDate(LocalDateTime.now())
                        .personFisica(personFisicaEntity)
                        .legalPerson(legalPerson)
                        .situacao(situacao)
                        .contaMatriz(contaMatrizEntity)
                        .contaFilial(contaFilialEntity)
                        .build();

        if (saveContaFilial.getContaMatriz() == null && saveContaFilial.getContaFilial() == null) {
            throw new UserException("The Subsidiary Account can not be created without a Parent account or a Subsidiary");
        }

        persistiContaFilial(saveContaFilial);
    }

    @Override
    public void updateContaFilial(ContaFilialEntity contaFilial) {

        LegalPersonEntity legalPerson = getLegalPerson(contaFilial);

        PersonFisicaEntity personFisicaEntity = getPersonFisica(contaFilial);

        ContaMatrizEntity contaMatrizEntity = getContaMatriz(contaFilial);

        ContaFilialEntity contaFilialEntity = getContaFilial(contaFilial);

        Situacao situacao = getSituation(contaFilial);

        ContaFilialEntity updateContaFilial = findById(contaFilial.getId());

        ContaFilialEntity saveContaFilial =
                ContaFilialEntity.builder()
                        .id(updateContaFilial.getId())
                        .nameAccount(contaFilial.getNameAccount())
                        .startDate(updateContaFilial.getStartDate())
                        .personFisica(personFisicaEntity)
                        .legalPerson(legalPerson)
                        .situacao(situacao)
                        .contaMatriz(contaMatrizEntity)
                        .contaFilial(contaFilialEntity)
                        .build();

        persistiContaFilial(saveContaFilial);
    }

    @Override
    public List<ContaFilialEntity> findAllContaFilial() {
        return contaFilialRepository.findAll();
    }

    @Override
    public void deleteAffiliateAccount(Long id) {
        ContaFilialEntity contaFilialEntity = findById(id);
        log.info("docaEntity={} message=delete_successfully");
        this.contaFilialRepository.delete(contaFilialEntity);
    }

    private ContaFilialEntity findById(Long id) {
        return contaFilialRepository.findById(id).orElseThrow(() -> new
                EntityNotFoundException(String.format("Account contaFilial='%s' not found", id)));
    }

    private ContaFilialEntity persistiContaFilial(ContaFilialEntity contaMatrizEntity) {
        ContaFilialEntity contaFilial = contaFilialRepository.save(contaMatrizEntity);
        log.info("contaMatrizEntity={} message=insert_successfully");
        return contaFilial;
    }

    private ContaFilialEntity getContaFilial(ContaFilialEntity contaFilial) {

        ContaFilialEntity contaFilialEntity = contaFilial.getContaFilial();

        if (contaFilialEntity == null || contaFilialEntity.getId() == null) {
            return null;
        }

        return contaFilialRepository.findById(contaFilial.getContaFilial().getId()).orElseThrow(() ->
                new EntityNotFoundException(String.format("Account contaFilial='%s' not found",
                        contaFilial.getContaFilial().getId())));
    }

    private ContaMatrizEntity getContaMatriz(ContaFilialEntity contaFilial) {

        ContaMatrizEntity contaMatriz = contaFilial.getContaMatriz();

        if (contaMatriz == null || contaMatriz.getId() == null) {
            return null;
        }

        return contaMatrizRepository.findById(contaFilial.getContaMatriz().getId()).orElseThrow(() ->
                new EntityNotFoundException(String.format("Account contaFilial='%s' not found",
                        contaFilial.getContaMatriz().getId())));
    }

    private PersonFisicaEntity getPersonFisica(ContaFilialEntity contaFilial) {

        PersonFisicaEntity personFisica = contaFilial.getPersonFisica();

        if (personFisica == null || personFisica.getId() == null) {
            return null;
        }

        return personFiscalRepository.findById(contaFilial.getPersonFisica().getId()).orElseThrow(() ->
                new EntityNotFoundException(String.format("Person fisicaEntity personFisica='%s' not found",
                        contaFilial.getPersonFisica().getId())));
    }

    private LegalPersonEntity getLegalPerson(ContaFilialEntity contaFilial) {

        LegalPersonEntity legalPerson = contaFilial.getLegalPerson();

        if (legalPerson == null || legalPerson.getId() == null) {
            return null;
        }

        return legalPersonRepository.findById(contaFilial.getLegalPerson().getId()).orElseThrow(() ->
                new EntityNotFoundException(String.format("Legal person legalPerson='%s' not found",
                        contaFilial.getLegalPerson().getId())));
    }

    private Situacao getSituation(ContaFilialEntity contaFilial) {

        Situacao situacao = contaFilial.getSituacao();

        if (situacao == null || situacao.getDesc() == null) {
            return null;
        }

        return situacaoRepository.findByDesc(contaFilial.getSituacao().getDesc()).orElseThrow(() ->
                new EntityNotFoundException(String.format("Situation situacao='%s' not found",
                        contaFilial.getSituacao().getDesc())));
    }
}
