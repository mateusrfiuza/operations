package br.com.financial.operations.repository;

import br.com.financial.operations.domain.transaction.OperationTypeEnum;

import javax.persistence.AttributeConverter;

public class OperationTypeEnumConverter implements AttributeConverter<OperationTypeEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(OperationTypeEnum attribute) {
        if (attribute == null)
            return null;

        switch (attribute) {
        case COMPRA_A_VISTA:
            return 1;

        case COMPRA_PARCELADA:
            return 2;

        case SAQUE:
            return 3;

        case PAGAMENTO:
            return 4;

        default:
            throw new IllegalArgumentException(attribute + " not supported.");
        }
    }

    @Override
    public OperationTypeEnum convertToEntityAttribute(Integer dbData) {
        if (dbData == null)
            return null;

        switch (dbData) {
        case 1:
            return OperationTypeEnum.COMPRA_A_VISTA;

        case 2:
            return OperationTypeEnum.COMPRA_PARCELADA;

        case 3:
            return OperationTypeEnum.SAQUE;

        case 4:
            return OperationTypeEnum.PAGAMENTO;

        default:
            throw new IllegalArgumentException(dbData + " not supported.");
        }
    }

}
