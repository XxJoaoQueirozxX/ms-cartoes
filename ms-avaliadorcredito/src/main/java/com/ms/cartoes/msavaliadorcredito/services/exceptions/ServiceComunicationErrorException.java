package com.ms.cartoes.msavaliadorcredito.services.exceptions;

import lombok.Getter;

public class ServiceComunicationErrorException extends Exception {

    @Getter
    private Integer status;

    public ServiceComunicationErrorException(String message, Integer status) {
        super(message);
        this.status = status;

    }
}
