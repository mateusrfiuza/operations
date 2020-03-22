package br.com.financial.operations.domain.transaction;

public enum OperationTypeEnum {

    COMPRA_A_VISTA(1),
    COMPRA_PARCELADA(2),
    SAQUE(3),
    PAGAMENTO(4);

    private final int value;

    OperationTypeEnum(final int value) {
        this.value = value;
    }

    public int getValue() { return value; }

    public static OperationTypeEnum getValue(final int value) {
        for (final OperationTypeEnum o : OperationTypeEnum.values()) {
            if (o.value == value) {
                return o;
            }
        }
        return null;
    }

}
