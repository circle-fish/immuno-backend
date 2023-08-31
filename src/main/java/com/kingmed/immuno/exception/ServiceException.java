package com.kingmed.immuno.exception;


public class ServiceException extends RuntimeException {


    public ServiceException() {
    }

    public ServiceException( String message) {
        super(message);
    }


}