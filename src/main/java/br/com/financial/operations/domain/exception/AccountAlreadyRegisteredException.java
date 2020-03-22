package br.com.financial.operations.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AccountAlreadyRegisteredException extends Exception {

    public AccountAlreadyRegisteredException() {
        super("Account already registered");
    }
}
