package br.com.hub.resources.person;

import br.com.hub.core.repository.person.model.LegalPersonEntity;
import br.com.hub.core.service.pessoa.PessoaService;
import br.com.hub.resources.person.request.LegalPersonRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

/**
 * Created by Francislin Dos Reis on 28/11/2018
 */
@RestController
@RequestMapping(LegalPersonController.URI_USER)
@Api
public class LegalPersonController {

    public static final String URI_USER = "/user/";

    @Autowired
    private PessoaService pessoaService;

    @ApiOperation(value = "Create new user",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful user creation", response = ResponseEntity.class),
            @ApiResponse(code = 409, message = "Validation error"),
            @ApiResponse(code = 500, message = "Unhandled exception")})
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createUser(@Valid @RequestBody LegalPersonRequest legalPersonRequest, UriComponentsBuilder builder) {

        LegalPersonEntity legalPerson =
                LegalPersonEntity.builder()
                        .nameOfFantasy(legalPersonRequest.getNameOfFantasy())
                        .reasonSocial(legalPersonRequest.getReasonSocial())
                        .cnpj(legalPersonRequest.getCnpj())
                        .build();

        pessoaService.savePerson(legalPerson);

        UriComponents uriComponents = builder.path(URI_USER.concat("{name}")).buildAndExpand(legalPerson.getNameOfFantasy());

        return ResponseEntity.created(uriComponents.toUri())
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .build();
    }
}
