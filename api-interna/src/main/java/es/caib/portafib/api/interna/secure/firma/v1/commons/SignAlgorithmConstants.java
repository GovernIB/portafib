package es.caib.portafib.api.interna.secure.firma.v1.commons;

import org.springframework.context.annotation.Description;

import io.swagger.v3.oas.annotations.media.Schema;

@Description("Hola")
@Schema(
        name = "SignAlgorithmConstantsConstants",
        description = "Algorisme de Firma. Valors:\r\n" + "    • \"SHA-1\"\r\n" + "    • \"SHA-256\"\r\n"
                + "    • \"SHA-384\"\r\n" + "    • \"SHA-512\"",
        enumAsRef = true,
        examples = "SHA-1|SHA-256|SHA-384|SHA-512")
public enum SignAlgorithmConstants {
    SIGN_ALGORITHM_CONSTANTS_SHA1("SHA-1"), SIGN_ALGORITHM_CONSTANTS_SHA256("SHA-256"),
    SIGN_ALGORITHM_CONSTANTS_SHA384("SHA-384"), SIGN_ALGORITHM_CONSTANTS_SHA512("SHA-512");

    public final String value;

    SignAlgorithmConstants(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}