package br.com.financial.operations.domain.account;

import br.com.financial.operations.infrastructure.database.AccountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account from(AccountEntity account);

}