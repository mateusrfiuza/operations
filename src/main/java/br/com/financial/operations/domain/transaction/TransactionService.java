package br.com.financial.operations.domain.transaction;

import br.com.financial.operations.domain.exception.TransactionAccountNotRegisteredException;
import br.com.financial.operations.repository.TransactionRepository;
import br.com.financial.operations.repository.exception.ForeignKeyNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public void create(final Transaction transaction) throws TransactionAccountNotRegisteredException {
        try {
            transactionRepository.create(transaction);
        } catch (ForeignKeyNotFoundException e) {
            throw new TransactionAccountNotRegisteredException();
        }
    }

}
