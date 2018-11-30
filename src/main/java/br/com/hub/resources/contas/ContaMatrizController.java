package br.com.hub.resources.contas;

import br.com.hub.core.repository.conta.models.ContaFilialEntity;
import br.com.hub.core.repository.conta.models.ContaMatrizEntity;
import br.com.hub.core.repository.conta.models.Situacao;
import br.com.hub.core.repository.person.model.LegalPersonEntity;
import br.com.hub.core.repository.person.model.PersonFisicaEntity;
import br.com.hub.core.service.conta.ContaMatrizService;
import br.com.hub.resources.contas.request.ContaMatrizRequest;
import br.com.hub.resources.contas.response.ContaFilialResponse;
import br.com.hub.resources.contas.response.ContaMatrizResponse;
import br.com.hub.resources.response.CollectionResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Francislin Dos Reis on 28/11/2018
 */
@RestController
@RequestMapping(ContaMatrizController.URI_CONTAMATRIZ)
@Api
public class ContaMatrizController {

    public static final String URI_CONTAMATRIZ = "/conta/";

    @Autowired
    private ContaMatrizService contaMatrizService;

    @ApiOperation(value = "Create new parent account", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful parent account creation", response = ResponseEntity.class),
            @ApiResponse(code = 409, message = "Validation error"),
            @ApiResponse(code = 500, message = "Unhandled exception")})
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createUser(@Valid @RequestBody ContaMatrizRequest contaMatrizRequest, UriComponentsBuilder builder) {

        ContaMatrizEntity contaMatriz =
                ContaMatrizEntity.builder()
                        .nameAccount(contaMatrizRequest.getNameAccount())
                        .startDate(contaMatrizRequest.getStartDate())
                        .situacao(Situacao.builder().desc(contaMatrizRequest.getSituacaoDesc()).build())
                        .personFisicaEntity(PersonFisicaEntity.builder().id(contaMatrizRequest.getPersonFisicaId()).build())
                        .legalPerson(LegalPersonEntity.builder().id(contaMatrizRequest.getLegalPersonId()).build())
                        .contaFilial(getContaFilial(contaMatrizRequest.getNames()))
                        .build();

        contaMatrizService.saveContaMatriz(contaMatriz);

        UriComponents uriComponents = builder.path(URI_CONTAMATRIZ.concat("{nameAccount}")).buildAndExpand(contaMatriz.getNameAccount());

        return ResponseEntity.created(uriComponents.toUri())
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .build();
    }

    @ApiOperation(value = "Update parent account", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful parent account", response = ResponseEntity.class),
            @ApiResponse(code = 404, message = "Parent account not found"),
            @ApiResponse(code = 500, message = "Unhandled error update affiliate account")})
    @RequestMapping(value = "{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateFicha(@Valid @RequestBody ContaMatrizRequest contaMatrizRequest, @PathVariable(value = "id") Long id) {

        ContaMatrizEntity contaMatriz =
                ContaMatrizEntity.builder()
                        .id(id)
                        .nameAccount(contaMatrizRequest.getNameAccount())
                        .startDate(contaMatrizRequest.getStartDate())
                        .situacao(Situacao.builder().desc(contaMatrizRequest.getSituacaoDesc()).build())
                        .personFisicaEntity(PersonFisicaEntity.builder().id(contaMatrizRequest.getPersonFisicaId()).build())
                        .legalPerson(LegalPersonEntity.builder().id(contaMatrizRequest.getLegalPersonId()).build())
                        .contaFilial(getContaFilial(contaMatrizRequest.getNames()))
                        .build();

        contaMatrizService.updateContaMatriz(contaMatriz);

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Method to delete parent account by id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Parent account deleted successfully"),
            @ApiResponse(code = 404, message = "Parent account does not deleted because it was not found."),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteParentAccount(@PathVariable("id") Long id) {

        contaMatrizService.deleteContaMatriz(id);

        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "List of all parent account", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of all parent account", response = CollectionResponse.class, responseContainer = "List"),
            @ApiResponse(code = 500, message = "Unhandled exception")})
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CollectionResponse<ContaMatrizResponse>> listAllParentAccount() {

        List<ContaMatrizEntity> allContaMatrix = contaMatrizService.findAllContaMatriz();

        return ResponseEntity.ok().body(CollectionResponse.<ContaMatrizResponse>builder()
                .result(allContaMatrix.stream().map(f ->
                        ContaMatrizResponse.builder()
                                .id(f.getId())
                                .nameAccount(f.getNameAccount())
                                .legalPersonId(f.getLegalPerson().getId())
                                .personFisicaId(f.getPersonFisicaEntity().getId())
                                .startDate(f.getStartDate())
                                .build())
                        .collect(Collectors.toList()))
                .build());
    }

    public Set<ContaFilialEntity> getContaFilial(List<String> names) {

        Set<ContaFilialEntity> contaFilialList = new HashSet<>();

        if (names == null || names.isEmpty()) {
            return contaFilialList;
        }

        names.forEach(name -> contaFilialList.add(contaMatrizService.findByName(name)));

        return contaFilialList;
    }

}
