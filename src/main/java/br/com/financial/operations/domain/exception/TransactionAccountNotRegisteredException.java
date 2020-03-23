package br.com.financial.operations.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class TransactionAccountNotRegisteredException extends Exception {

    public TransactionAccountNotRegisteredException() {
        super("Transaction account not found");
    }


}
