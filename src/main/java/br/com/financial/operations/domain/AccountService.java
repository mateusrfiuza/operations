package br.com.financial.operations.domain;

import br.com.financial.operations.domain.exception.AccountAlreadyRegisteredException;
import br.com.financial.operations.domain.exception.AccountNotFoundException;
import br.com.financial.operations.repository.AccountRepository;
import br.com.financial.operations.repository.exception.DuplicateKeyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    private final AccountMapper mapper;

    public Account create(final String documentNumber) throws AccountAlreadyRegisteredException {

        try {
            return mapper.from(accountRepository.createAccount(documentNumber));
        } catch (DuplicateKeyException e) {
            throw new AccountAlreadyRegisteredException();
        }
    }

    public Account getAccount(final Long accountId) throws AccountNotFoundException {
        return accountRepository.getAccount(accountId)
                .map(mapper::from)
                .orElseThrow(AccountNotFoundException::new);
    }


}
