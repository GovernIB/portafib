package es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb;


import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal
 * 19 may 2025 11:30:53
 */
@Schema(
        name = "ExternalSignerSecurityLevel",
        description = "Valors:\n"
                        + "TOKEN = 1;\r\n"
                        + "PASSWORD = 2;\r\n"
                        + "CERTIFICATE = 4;",
        format = "int32",
        enumAsRef = true,
        /** Parxe utilitzat per a la generació correcta dels noms dels enums dins de l'openapi.json */
        example = "TOKEN(token)"
                + "|PASSWORD(contrasenya)"
                + "|CERTIFICATE(certificat)")
public enum ExternalSignerSecurityLevel {
/*
      public static final int SECURITY_LEVEL_TOKEN = 1;
  public static final int SECURITY_LEVEL_PASSWORD = 2;
  public static final int SECURITY_LEVEL_CERTIFICATE = 4;
*/
    TOKEN(1),  
    PASSWORD(2), CERTIFICATE(4);
    
    public final Integer value;

    ExternalSignerSecurityLevel(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static ExternalSignerSecurityLevel fromValue(Integer value) {
        for (ExternalSignerSecurityLevel b : ExternalSignerSecurityLevel.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "' for ExternalSignerSecurityLevel.");
    }
}
