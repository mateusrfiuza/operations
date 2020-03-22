package br.com.financial.operations.infrastructure.database;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDAO extends JpaRepository<AccountEntity, Long> {

}

