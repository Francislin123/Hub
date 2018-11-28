package br.com.hub.resources.response;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Tolerate;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Created by Francislin Dos Reis on 19/07/18.
 */
@Getter
public class ErrorResponse {

    private String code;
    private String description;
    private List<? extends ErrorElementResponse> errors;

    @Tolerate
    public ErrorResponse() {
        new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Unhandled Exception", null);
    }

    @Builder
    public ErrorResponse(String code, String description, List<? extends ErrorElementResponse> errors) {
        this.code = code;
        this.description = description;
        this.errors = errors;
    }
}
