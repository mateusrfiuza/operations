package br.com.financial.operations.infrastructure.database;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDAO extends JpaRepository<TransactionEntity, Long> {
}

