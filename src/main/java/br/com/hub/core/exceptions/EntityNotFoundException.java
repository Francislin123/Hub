package br.com.hub.core.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Created by Francislin Dos Reis on 19/07/18.
 */

public class EntityNotFoundException extends UserException {

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getErrorCode() {
        return HttpStatus.NOT_FOUND;
    }
}
