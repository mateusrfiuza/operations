package br.com.financial.operations.domain;

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

}
