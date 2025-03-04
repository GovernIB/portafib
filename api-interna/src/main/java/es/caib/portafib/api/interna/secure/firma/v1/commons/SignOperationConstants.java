package es.caib.portafib.api.interna.secure.firma.v1.commons;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "SignOperationConstants",
        description = "Operació de firma realitzada: Firma (0), Cofirma (1) o Contrafirma (2).\r\n"
                + "Les constants són:\r\n" + "    • SIGN_OPERATION_SIGN = 0;\r\n"
                + "    • SIGN_OPERATION_COSIGN = 1;\r\n" + "    • SIGN_OPERATION_COUNTERSIGN = 2;",
        format = "int32",
        enumAsRef = true,
        example = "SIGN_OPERATION_SIGN|SIGN_OPERATION_COSIGN|SIGN_OPERATION_COUNTERSIGN")
public enum SignOperationConstants {
    SIGN_OPERATION_CONSTANTS_SIGN(0), SIGN_OPERATION_CONSTANTS_COSIGN(1), SIGN_OPERATION_CONSTANTS_COUNTERSIGN(2);

    public final Integer value;

    SignOperationConstants(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
