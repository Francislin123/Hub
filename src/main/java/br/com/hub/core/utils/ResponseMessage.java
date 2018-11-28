package br.com.hub.core.utils;

import lombok.Getter;

/**
 * Created by Francislin Dos Reis on 30/10/2018
 */
@Getter
public class ResponseMessage {

    private String message;

    private Object object;

    public ResponseMessage(String message, Object object) {
        this.message = message;
        this.object = object;
    }

    public ResponseMessage() {
    }
}
