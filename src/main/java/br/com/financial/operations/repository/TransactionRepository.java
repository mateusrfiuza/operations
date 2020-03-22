package br.com.financial.operations.repository;

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

    public void create(final TransactionEntity transactionEntity) throws ForeignKeyNotFoundException {

        try {
            transactionDAO.saveAndFlush(transactionEntity);
        } catch (DataIntegrityViolationException e){
            throw new ForeignKeyNotFoundException();
        }

    }
}
