package br.com.financial.operations.repository;

import br.com.financial.operations.domain.transaction.Transaction;
import br.com.financial.operations.infrastructure.database.AccountEntity;
import br.com.financial.operations.infrastructure.database.TransactionDAO;
import br.com.financial.operations.infrastructure.database.TransactionEntity;
import br.com.financial.operations.repository.exception.ForeignKeyNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TransactionRepository {

    private final TransactionDAO transactionDAO;

    public void create(final Transaction transaction) throws ForeignKeyNotFoundException {

        final var entity = new TransactionEntity(new AccountEntity(transaction.getAccountId()), transaction.getOperationTypeEnum(), transaction.getAmount());

        try {
            transactionDAO.saveAndFlush(entity);
        } catch (DataIntegrityViolationException e){
            throw new ForeignKeyNotFoundException();
        }

    }
}
