package br.com.financial.operations.infrastructure.database;

import br.com.financial.operations.domain.OperationTypeEnum;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TRANSACTION")
@Data
@Getter
public class TransactionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "TRANSACTION_ID")
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private AccountEntity accountEntity;

    @Column(name = "OPERATION_TYPE_ID", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private OperationTypeEnum operationType;

    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;

    @Setter(AccessLevel.NONE)
    @Column(name = "EVENT_DATE", nullable = false)
    private LocalDateTime eventDate;

    public TransactionEntity(AccountEntity accountEntity, OperationTypeEnum operationType, BigDecimal amount) {
        this.accountEntity = accountEntity;
        this.operationType = operationType;
        this.amount = amount;
        this.eventDate = LocalDateTime.now();
    }

}
