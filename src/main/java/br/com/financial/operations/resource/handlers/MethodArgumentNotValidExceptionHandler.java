package br.com.financial.operations.resource.handlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class MethodArgumentNotValidExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(final MethodArgumentNotValidException ex) {

        final BindingResult result = ex.getBindingResult();
        final List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();

        return new ResponseEntity(processFieldErrors(fieldErrors), HttpStatus.BAD_REQUEST);
    }

    private Error processFieldErrors(final List<org.springframework.validation.FieldError> fieldErrors) {
        final Error error = new Error(HttpStatus.BAD_REQUEST.value(), "validation error");
        fieldErrors.forEach((fieldError) -> {
            error.addFieldError(fieldError.getField(), fieldError.getDefaultMessage());
        });

        return error;
    }

    static class Error {
        private final int status;
        private final String message;
        private final List<FieldError> fieldErrors = new ArrayList<>();

        Error(int status, String message) {
            this.status = status;
            this.message = message;
        }

        public int getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }

        public void addFieldError(final String path, final String message) {
            FieldError error = new FieldError(path, message);
            fieldErrors.add(error);
        }

        public List<FieldError> getFieldErrors() {
            return fieldErrors;
        }
    }

    static class FieldError {
        private final String path;
        private final String message;

        public FieldError(final String path, final String message) {
            this.path = path;
            this.message = message;
        }

        public String getPath() {
            return path;
        }

        public String getMessage() {
            return message;
        }
    }
}