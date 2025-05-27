package es.caib.portafib.api.interna.secure.signature.v1.commons;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal
 * 6 may 2025 9:12:24
 */
@Schema(
        description = "Perfil empleado en una firma con certificado electrónico. Los posibles valores asignables son los siguientes:\r\n"
                + "  AdES-BES\r\n" + "  AdES-EPES\r\n" + "  AdES-T\r\n" + "  AdES-C\r\n" + "  AdES-X\r\n"
                + "  AdES-X1\r\n" + "  AdES-X2\r\n" + "  AdES-XL\r\n" + "  AdES-XL1\r\n" + "  AdES-XL2\r\n"
                + "  AdES-A\r\n" + "  PAdES-LTV\r\n" + "  PAdES-Basic",
        enumAsRef = true,
                /** Parxe utilitzat per a la generació correcta dels noms dels enums dins de l'openapi.json */
        example = "SIGN_PROFILE_BES|SIGN_PROFILE_EPES|SIGN_PROFILE_T|SIGN_PROFILE_C|SIGN_PROFILE_X"
                + "|SIGN_PROFILE_X1|SIGN_PROFILE_X2|SIGN_PROFILE_XL|SIGN_PROFILE_XL1"
                + "|SIGN_PROFILE_XL2|SIGN_PROFILE_A|SIGN_PROFILE_PADES_LTV|SIGN_PROFILE_PADES_BASIC")
public enum SignProfileConstants {
    SIGN_PROFILE_BES("AdES-BES"), SIGN_PROFILE_EPES("AdES-EPES"),
    SIGN_PROFILE_T("AdES-T"), SIGN_PROFILE_C("AdES-C"), SIGN_PROFILE_X("AdES-X"),
    SIGN_PROFILE_X1("AdES-X1"), SIGN_PROFILE_X2("AdES-X2"), SIGN_PROFILE_XL("AdES-XL"),
    SIGN_PROFILE_XL1("AdES-XL1"), SIGN_PROFILE_XL2("AdES-XL2"), SIGN_PROFILE_A("AdES-A"),
    SIGN_PROFILE_PADES_LTV("PAdES-LTV"), SIGN_PROFILE_PADES_BASIC("PAdES-Basic");

    public final String value;

    SignProfileConstants(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
