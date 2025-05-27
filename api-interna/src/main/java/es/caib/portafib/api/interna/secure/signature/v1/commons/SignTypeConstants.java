package es.caib.portafib.api.interna.secure.signature.v1.commons;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Constants for the different types of signatures.
 * 
 * @author anadal
 * 6 may 2025 8:55:43
 */
@Schema(
        description = "Tipus de Firma. Valors possibles:\n" + "    - “PAdES” (Constant SIGN_TYPE_PADES)\n"
                + "    - “XAdES” (Constant SIGN_TYPE_XADES)\n" + "    - “CAdES” (Constant SIGN_TYPE_CADES)\n"
                + "    - “FacturaE” (Constant SIGN_TYPE_FACTURAE)\n" + "    - “OOXML” (Constant SIGN_TYPE_OOXML)\n"
                + "    - “ODF” (Constant SIGN_TYPE_ODF)\n" + "    - “SMIME” (Constant SIGN_TYPE_SMIME)\n"
                + "    - “CAdES-ASiC-S” (Constant SIGN_TYPE_CADES_ASIC_S)\n"
                + "    - “XAdES-ASiC-S” (Constant SIGN_TYPE_XADES_ASIC_S)\n"
                + "    - “PKCS#1” (Constant SIGN_TYPE_PKCS1)",
        enumAsRef = true,
        /** Parxe utilitzat per a la generació correcta dels noms dels enums dins de l'openapi.json */
        example = "SIGN_TYPE_PADES|SIGN_TYPE_XADES|SIGN_TYPE_CADES|SIGN_TYPE_FACTURAE"
                + "|SIGN_TYPE_OOXML|SIGN_TYPE_ODF|SIGN_TYPE_SMIME|SIGN_TYPE_CADESASICS"
                + "|SIGN_TYPE_XADESASICS|SIGN_TYPE_PKCS")
public enum SignTypeConstants {
    SIGN_TYPE_PADES("PAdES"), SIGN_TYPE_XADES("XAdES"), SIGN_TYPE_CADES("CAdES"), SIGN_TYPE_FACTURAE("FacturaE"),
    SIGN_TYPE_OOXML("OOXML"), SIGN_TYPE_ODF("ODF"), SIGN_TYPE_SMIME("SMIME"), SIGN_TYPE_CADESASICS("CAdES-ASiC-S"),
    SIGN_TYPE_XADESASICS("XAdES-ASiC-S"), SIGN_TYPE_PKCS("PKCS#1");

    public final String value;

    SignTypeConstants(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
