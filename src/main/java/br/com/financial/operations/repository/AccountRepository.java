package br.com.financial.operations.repository;

import br.com.financial.operations.infrastructure.database.AccountDAO;
import br.com.financial.operations.infrastructure.database.AccountEntity;
import br.com.financial.operations.repository.exception.DuplicateKeyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Slf4j
@RequiredArgsConstructor
public class AccountRepository {

    private final AccountDAO accountDAO;

    @Transactional(readOnly = true)
    public Optional<AccountEntity> getAccount(Long accountId) {
        return accountDAO.findById(accountId);
    }

    public AccountEntity createAccount(final String documentNumber) throws DuplicateKeyException {

        try {
            return accountDAO.saveAndFlush(new AccountEntity(documentNumber));
        } catch (DataIntegrityViolationException e){
            throw new DuplicateKeyException();
        }
    }

}
