package br.com.financial.operations.domain.transaction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Set;

import static br.com.financial.operations.domain.transaction.OperationTypeEnum.*;

@Valid
@NoArgsConstructor
public class Transaction {

    @Getter
    private OperationTypeEnum operationTypeEnum;

    @Getter
    private Long accountId;

    @Getter
    private BigDecimal amount;

    private final Set<OperationTypeEnum> purchaseOperation = Set.of(COMPRA_A_VISTA, COMPRA_PARCELADA, SAQUE);

    private final Set<OperationTypeEnum> paymentTransactionsOperation = Set.of(PAGAMENTO);

    public Transaction(Long accountId, @NonNull OperationTypeEnum operationTypeEnum, BigDecimal amount) {
        this.accountId = accountId;
        this.operationTypeEnum = operationTypeEnum;

        if (purchaseOperation.contains(operationTypeEnum)) {
            this.amount = amount.negate();
        } else if (paymentTransactionsOperation.contains(operationTypeEnum)) {
            this.amount = amount;
        } else {
            throw new IllegalArgumentException("Invalid Operation");
        }
    }

}
