package br.com.financial.operations.resource;

import br.com.financial.operations.domain.account.AccountService;
import br.com.financial.operations.domain.exception.AccountAlreadyRegisteredException;
import br.com.financial.operations.domain.exception.AccountNotFoundException;
import br.com.financial.operations.domain.exception.UnregisteredTransactionAccountException;
import br.com.financial.operations.domain.transaction.TransactionService;
import br.com.financial.operations.resource.payload.AccountRequestBody;
import br.com.financial.operations.resource.payload.AccountResponse;
import br.com.financial.operations.resource.payload.AccountResponseMapper;
import br.com.financial.operations.resource.payload.TransactionRequestBody;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequiredArgsConstructor
public class OperationsResource {

    public static final String ACCOUNTS_URI = "/accounts";

    public static final String TRANSACTIONS_URI = "/transactions";

    private final AccountService accountService;

    private final TransactionService transactionService;

    private final AccountResponseMapper accountResponseMapper;

    @PostMapping(value = ACCOUNTS_URI, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create account")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created Account"),
            @ApiResponse(code = 400, message = "Invalid input data"),
            @ApiResponse(code = 409, message = "Account Already Registered")

    })
    public ResponseEntity<AccountResponse> createAccount(@RequestBody @Valid final AccountRequestBody request) throws AccountAlreadyRegisteredException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(accountResponseMapper.from(accountService.create(request.getDocumentNumber())));

    }

    @GetMapping(value = ACCOUNTS_URI+"/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get account")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Account Found"),
            @ApiResponse(code = 400, message = "Invalid input data"),
            @ApiResponse(code = 404, message = "Account Not Found")
    })
    public ResponseEntity<AccountResponse> getAccount(@PathVariable final Long accountId) throws AccountNotFoundException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountResponseMapper.from(accountService.getAccount(accountId)));

    }

    @PostMapping(value = TRANSACTIONS_URI, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create transaction")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created transaction"),
            @ApiResponse(code = 400, message = "Invalid input data"),
            @ApiResponse(code = 422, message = "Account Already Registered")

    })
    @ResponseStatus(HttpStatus.CREATED)
    public void createTransaction(@RequestBody @Valid final TransactionRequestBody request) throws UnregisteredTransactionAccountException {
        transactionService.create(request.toDomain());
    }

}
