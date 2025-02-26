package es.caib.portafib.api.interna.secure.firma.v1.commons;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "SignatureStableLocation", description = "Posicio de la firma al document", format = "int32", enumAsRef = true, example ="SIGNATURESTABLELOCATION_WITHOUT|SIGNATURESTABLELOCATION_FIRSTPAGE|SIGNATURESTABLELOCATION_LASTPAGE")
public enum SignatureStableLocation {
    SIGNATURESTABLELOCATION_WITHOUT(0),
    SIGNATURESTABLELOCATION_FIRSTPAGE(1),
    SIGNATURESTABLELOCATION_LASTPAGE(-1);
    public final Integer value;

    SignatureStableLocation(Integer value) {
        this.value = value;
    }
    
    public Integer getValue() {
        return value;
      }

      @Override
      public String toString() {
        return String.valueOf(value);
      }

      public static SignatureStableLocation fromValue(Integer value) {
        for (SignatureStableLocation b : SignatureStableLocation.values()) {
          if (b.value.equals(value)) {
            return b;
          }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
      }
}
