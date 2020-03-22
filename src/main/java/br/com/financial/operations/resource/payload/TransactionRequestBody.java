package br.com.financial.operations.resource.payload;

import br.com.financial.operations.domain.transaction.OperationTypeEnum;
import br.com.financial.operations.domain.transaction.Transaction;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
public class TransactionRequestBody implements Serializable {

    @JsonProperty("account_id")
    @NotNull
    @Min(1)
    private Long accountId;

    @JsonProperty("operation_type_id")
    @Min(1)
    @Max(4)
    @NotNull
    private int operationType;

    @DecimalMin("0.01")
    @JsonProperty("amount")
    private BigDecimal amount;

    public Transaction toDomain() {
        return new Transaction(this.accountId, this.getOperationType(), this.amount);
    }

    private OperationTypeEnum getOperationType() {
        return OperationTypeEnum.getValue(this.operationType);
    }

}
