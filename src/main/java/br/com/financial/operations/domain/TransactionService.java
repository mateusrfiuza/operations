package br.com.financial.operations.domain;

import br.com.financial.operations.domain.exception.UnregisteredTransactionAccountException;
import br.com.financial.operations.infrastructure.database.AccountEntity;
import br.com.financial.operations.infrastructure.database.TransactionEntity;
import br.com.financial.operations.repository.TransactionRepository;
import br.com.financial.operations.repository.exception.ForeignKeyNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public void create(final Long accountId, final OperationTypeEnum operationTypeEnum, BigDecimal amount) throws UnregisteredTransactionAccountException {

        final var transaction = new TransactionEntity(new AccountEntity(accountId), operationTypeEnum, amount);

        try {
            transactionRepository.create(transaction);
        } catch (ForeignKeyNotFoundException e) {
            throw new UnregisteredTransactionAccountException();
        }
    }



}
