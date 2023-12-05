package com.ar.cac.IntegradorFinalGrupo5.Exceptions;

public class AccountNotExistException extends RuntimeException {

    public AccountNotExistException(String message){
        super(message);
    }
}
