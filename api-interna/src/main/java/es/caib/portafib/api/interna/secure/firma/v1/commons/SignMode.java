package es.caib.portafib.api.interna.secure.firma.v1.commons;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "SignMode", description = "Modes de firma disponibles", format = "int32", enumAsRef = true, example ="SIGN_MODE_ATTACHED_ENVELOPED|SIGN_MODE_ATTACHED_ENVELOPING|SIGN_MODE_DETACHED|SIGN_MODE_INTERNALLY_DETACHED")
public enum SignMode {
    SIGN_MODE_ATTACHED_ENVELOPED(0),
    SIGN_MODE_ATTACHED_ENVELOPING(3),
    SIGN_MODE_DETACHED(1),
    SIGN_MODE_INTERNALLY_DETACHED(4);
    
    public final Integer value;
    
    SignMode(Integer value){
        this.value = value;
    }
    
    public Integer getValue() {
        return value;
      }

      @Override
      public String toString() {
        return String.valueOf(value);
      }

      public static SignMode fromValue(Integer value) {
        for (SignMode b : SignMode.values()) {
          if (b.value.equals(value)) {
            return b;
          }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
      }
    
}
