package es.caib.portafib.api.interna.secure.firma.v1.commons;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "SignTypeConstants",
        description = "Tipus de Firma. Valors possibles:\r\n" + "    - “PAdES” (Constant SIGN_TYPE_PADES)\r\n"
                + "    - “XAdES” (Constant SIGN_TYPE_XADES)\r\n" + "    - “CAdES” (Constant SIGN_TYPE_CADES)\r\n"
                + "    - “FacturaE” (Constant SIGN_TYPE_FACTURAE)\r\n" + "    - “OOXML” (Constant SIGN_TYPE_OOXML)\r\n"
                + "    - “ODF” (Constant SIGN_TYPE_ODF)\r\n" + "    - “SMIME” (Constant SIGN_TYPE_SMIME)\r\n"
                + "    - “CAdES-ASiC-S” (Constant SIGN_TYPE_CADES_ASIC_S)\r\n"
                + "    - “XAdES-ASiC-S” (Constant SIGN_TYPE_XADES_ASIC_S)\r\n"
                + "    - “PKCS#1” (Constant SIGN_TYPE_PKCS1)",
        enumAsRef = true,
        examples = "PAdES|XAdES|CAdES|FacturaE|OOXML|ODF|SMIME|CAdES-ASiC-S|XAdES-ASiC-S|PKCS#1")
public enum SignTypeConstants {
    SIGN_TYPE_CONSTNATS_PADES("PAdES"), SIGN_TYPE_CONSTNATS_XADES("XAdES"), SIGN_TYPE_CONSTNATS_CADES("CAdES"),
    SIGN_TYPE_CONSTNATS_FACTURAE("FacturaE"), SIGN_TYPE_CONSTNATS_OOXML("OOXML"), SIGN_TYPE_CONSTNATS_ODF("ODF"),
    SIGN_TYPE_CONSTNATS_SMIME("SMIME"), SIGN_TYPE_CONSTNATS_CADESASICS("CAdES-ASiC-S"),
    SIGN_TYPE_CONSTNATS_XADESASICS("XAdES-ASiC-S"), SIGN_TYPE_CONSTNATS_PKCS("PKCS#1");

    public final String value;

    SignTypeConstants(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
