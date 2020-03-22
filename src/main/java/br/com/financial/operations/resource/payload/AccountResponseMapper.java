package br.com.financial.operations.resource.payload;

import br.com.financial.operations.domain.account.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountResponseMapper {

    AccountResponse from(Account account);

}