package br.com.financial.operations.helper;

import br.com.financial.operations.infrastructure.database.AccountDAO;
import br.com.financial.operations.infrastructure.database.TransactionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DatabaseHelper {

    @Autowired
    public AccountDAO accountDAO;

    @Autowired
    public TransactionDAO transactionDAO;

    @Transactional
    public void clearDatabase() {
        transactionDAO.deleteAll();
        accountDAO.deleteAll();
    }

}

