package br.com.hub.core.exceptions;

public class SystemException extends InternalArchitectureException {

    public SystemException(String message) {
        super(message);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }
}
