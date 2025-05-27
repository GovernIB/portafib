package es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb;

import es.caib.portafib.utils.ConstantsV2;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal
 * 16 may 2025 12:30:53
 */
@Schema(
        description = "Valors:\r\n"
                + "STRING(Conte una cadena de text)"
                + "|INTEGER(Conté un valor sencer)"
                + "|DECIMAL(conté un valor decimal)"
                + "|BOOLEAN(conte un booleà: true o false)"
                + "|BASE64(Conté un binari codificat en Base64)"
                + "|DATE(Conté un valor de tipus data en format ISO8601)",
        format = "int32",
        enumAsRef = true,
        /** Parxe utilitzat per a la generació correcta dels noms dels enums dins de l'openapi.json */
        example = "STRING(Conte una cadena de text)"
                + "|INTEGER(Conté un valor sencer)"
                + "|DECIMAL(conté un valor decimal)"
                + "|BOOLEAN(conte un booleà: true o false)"
                + "|BASE64(Conté un binari codificat en Base64)"
                + "|DATE(Conté un valor de tipus data en format ISO8601)")
public enum MetadataConstants {
/*
  public static final int STRING = 0;
  public static final int INTEGER = 1;
  public static final int DECIMAL = 2;
  public static final int BOOLEAN = 3;
  public static final int BASE64 = 4;
  public static final int DATE = 5; // ISO8601
*/
    STRING(ConstantsV2.TIPUSMETADADA_STRING),
    INTEGER(ConstantsV2.TIPUSMETADADA_INTEGER),  
    DECIMAL(ConstantsV2.TIPUSMETADADA_DECIMAL),
    BOOLEAN(ConstantsV2.TIPUSMETADADA_BOOLEAN),
    BASE64(ConstantsV2.TIPUSMETADADA_BASE64),
    DATE(ConstantsV2.TIPUSMETADADA_DATE); // ISO8601;
    
    public final Integer value;

    MetadataConstants(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static MetadataConstants fromValue(Integer value) {
        for (MetadataConstants b : MetadataConstants.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "' for " + MetadataConstants.class.getName());
    }
}
