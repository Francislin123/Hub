package br.com.hub.core.exceptions;

import br.com.hub.resources.response.ErrorElementResponse;
import org.springframework.http.HttpStatus;

import java.util.List;

public class UserException extends InternalArchitectureException {

    protected List<? extends ErrorElementResponse> errors;

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, List<? extends ErrorElementResponse> exceptionList) {
        super(message);
        this.errors = exceptionList;
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getErrorCode() {
        return HttpStatus.BAD_REQUEST;
    }

    public List<? extends ErrorElementResponse> getErrors() {
        return errors;
    }

}
