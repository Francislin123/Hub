package br.com.hub.resources.contas;

import br.com.hub.core.repository.conta.models.ContaFilialEntity;
import br.com.hub.core.repository.conta.models.ContaMatrizEntity;
import br.com.hub.core.repository.conta.models.Situacao;
import br.com.hub.core.repository.person.model.LegalPersonEntity;
import br.com.hub.core.repository.person.model.PersonFisicaEntity;
import br.com.hub.core.service.conta.ContaFilialService;
import br.com.hub.resources.contas.request.ContaFilialRequest;
import br.com.hub.resources.contas.response.ContaFilialResponse;
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
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Francislin Dos Reis on 28/11/2018
 */
@RestController
@RequestMapping(ContaFilialController.URI_CONTAFILIAL)
@Api
public class ContaFilialController {

    public static final String URI_CONTAFILIAL = "/contaFilial/";

    @Autowired
    private ContaFilialService contaFilialService;

    @ApiOperation(value = "Create new account filial",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful Affiliate account creation", response = ResponseEntity.class),
            @ApiResponse(code = 409, message = "Validation error"),
            @ApiResponse(code = 500, message = "Unhandled exception")})
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createUser(@Valid @RequestBody ContaFilialRequest contaFilialRequest, UriComponentsBuilder builder) {

        ContaFilialEntity contaFilial =
                ContaFilialEntity.builder()
                        .nameAccount(contaFilialRequest.getNameAccount())
                        .situacao(Situacao.builder().desc(contaFilialRequest.getSituacaoDesc()).build())
                        .legalPerson(LegalPersonEntity.builder().id(contaFilialRequest.getLegalPersonId()).build())
                        .personFisica(PersonFisicaEntity.builder().id(contaFilialRequest.getPersonFisicaId()).build())
                        .contaMatriz(ContaMatrizEntity.builder().id(contaFilialRequest.getContaMatrizId()).build())
                        .build();

        contaFilialService.saveContaFilial(contaFilial);

        UriComponents uriComponents = builder.path(URI_CONTAFILIAL.concat("{nameAccount}")).buildAndExpand(contaFilial.getNameAccount());

        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @ApiOperation(value = "Update affiliate account", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful affiliate account", response = ResponseEntity.class),
            @ApiResponse(code = 404, message = "Affiliate account not found"),
            @ApiResponse(code = 500, message = "Unhandled error update affiliate account")})
    @RequestMapping(value = "{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateFicha(@Valid @RequestBody ContaFilialRequest contaFilialRequest, @PathVariable(value = "id") Long id) {

        ContaFilialEntity contaFilial =
                ContaFilialEntity.builder()
                        .nameAccount(contaFilialRequest.getNameAccount())
                        .situacao(Situacao.builder().desc(contaFilialRequest.getSituacaoDesc()).build())
                        .legalPerson(LegalPersonEntity.builder().id(contaFilialRequest.getLegalPersonId()).build())
                        .personFisica(PersonFisicaEntity.builder().id(contaFilialRequest.getPersonFisicaId()).build())
                        .contaMatriz(ContaMatrizEntity.builder().id(contaFilialRequest.getContaMatrizId()).build())
                        .build();

        contaFilialService.updateContaFilial(contaFilial);

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "List of all affiliate account", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of all affiliate account", response = CollectionResponse.class, responseContainer = "List"),
            @ApiResponse(code = 500, message = "Unhandled exception")})
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CollectionResponse<ContaFilialResponse>> listAllAffiliateAccount() {

        List<ContaFilialEntity> allContaFilial = contaFilialService.findAllContaFilial();

        return ResponseEntity.ok().body(CollectionResponse.<ContaFilialResponse>builder()
                .result(allContaFilial.stream().map(f ->
                        ContaFilialResponse.builder()
                                .id(f.getId()).build())
                        .collect(Collectors.toList()))
                .build());
    }

    @ApiOperation(value = "Method to delete affiliate account by id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Affiliate account deleted successfully"),
            @ApiResponse(code = 404, message = "Affiliate account does not deleted because it was not found."),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteAffiliateAccount(@PathVariable("id") Long id) {

        contaFilialService.deleteAffiliateAccount(id);

        return ResponseEntity.noContent().build();
    }
}
