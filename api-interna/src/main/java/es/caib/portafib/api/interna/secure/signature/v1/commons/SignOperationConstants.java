package es.caib.portafib.api.interna.secure.signature.v1.commons;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal
 * 6 may 2025 9:09:10
 */
@Schema(
        description = "Operació de firma realitzada: Firma (0), Cofirma (1) o Contrafirma (2).\r\n"
                + "Les constants són:\r\n" + "    • SIGN_OPERATION_SIGN = 0;\r\n"
                + "    • SIGN_OPERATION_COSIGN = 1;\r\n" + "    • SIGN_OPERATION_COUNTERSIGN = 2;",
        format = "int32",
        enumAsRef = true,
                /** Parxe utilitzat per a la generació correcta dels noms dels enums dins de l'openapi.json */
        example = "SIGN_OPERATION_SIGN(Firma)|SIGN_OPERATION_COSIGN(Cofirma)|SIGN_OPERATION_COUNTERSIGN(Contrafirma)")
public enum SignOperationConstants {
    SIGN_OPERATION_SIGN(0), SIGN_OPERATION_COSIGN(1), SIGN_OPERATION_COUNTERSIGN(2);

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
