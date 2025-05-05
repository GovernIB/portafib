package es.caib.portafib.api.interna.secure.signature.v1.commons;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "SignProfileConstants",
        description = "Perfil empleado en una firma con certificado electrónico. Los posibles valores asignables son los siguientes:\r\n"
                + "  AdES-BES\r\n" + "  AdES-EPES\r\n" + "  AdES-T\r\n" + "  AdES-C\r\n" + "  AdES-X\r\n"
                + "  AdES-X1\r\n" + "  AdES-X2\r\n" + "  AdES-XL\r\n" + "  AdES-XL1\r\n" + "  AdES-XL2\r\n"
                + "  AdES-A\r\n" + "  PAdES-LTV\r\n" + "  PAdES-Basic",
        enumAsRef = true,
        examples = "AdES-BES|AdES-EPES|AdES-T|AdES-C|AdES-X|AdES-X1|AdES-X2|AdES-XL|AdES-XL1|AdES-XL2|AdES-A|PAdES-LTV|PAdES-Basic")
public enum SignProfileConstants {
    SIGN_PROFILE_CONSTANTS_BES("AdES-BES"), SIGN_PROFILE_CONSTANTS_EPES("AdES-EPES"),
    SIGN_PROFILE_CONSTANTS_T("AdES-T"), SIGN_PROFILE_CONSTANTS_C("AdES-C"), SIGN_PROFILE_CONSTANTS_X("AdES-X"),
    SIGN_PROFILE_CONSTANTS_X1("AdES-X1"), SIGN_PROFILE_CONSTANTS_X2("AdES-X2"), SIGN_PROFILE_CONSTANTS_XL("AdES-XL"),
    SIGN_PROFILE_CONSTANTS_XL1("AdES-XL1"), SIGN_PROFILE_CONSTANTS_XL2("AdES-XL2"), SIGN_PROFILE_CONSTANTS_A("AdES-A"),
    SIGN_PROFILE_CONSTANTS_PADES_LTV("PAdES-LTV"), SIGN_PROFILE_CONSTANTS_PADES_BASIC("PAdES-Basic");

    public final String value;

    SignProfileConstants(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
