package br.com.financial.operations.resource.payload;

import br.com.financial.operations.domain.OperationTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequestBody implements Serializable {

    @JsonProperty("account_id")
    @NotNull
    @Min(1)
    private Long accountId;

    @JsonProperty("operation_type_id")
    @NotNull
    private OperationTypeEnum operationType;

    @DecimalMin("0.01")
    @JsonProperty("amount")
    private BigDecimal amount;

}
