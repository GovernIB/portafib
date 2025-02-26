package es.caib.portafib.api.interna.secure.firma.v1.commons;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "SignOperation", description = "Operacions disponibles", format = "int32", enumAsRef = true, example ="SIGN_OPERATION_SIGN|SIGN_OPERATION_COSIGN|SIGN_OPERATION_COUNTERSIGN")
public enum SignOperation {
    SIGN_OPERATION_SIGN(0),
    SIGN_OPERATION_COSIGN(1),
    SIGN_OPERATION_COUNTERSIGN(2);
    
    public final Integer value;
    
    SignOperation(Integer value){
        this.value = value;
    }
    
    public Integer getValue() {
        return value;
      }

      @Override
      public String toString() {
        return String.valueOf(value);
      }

      public static SignOperation fromValue(Integer value) {
        for (SignOperation b : SignOperation.values()) {
          if (b.value.equals(value)) {
            return b;
          }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
      }
    
}
/*@Schema(name = "SignOperation", description = "Operacions disponibles", enumAsRef = true, example ="SIGN_OPERATION_SIGN|SIGN_OPERATION_COSIGN|SIGN_OPERATION_COUNTERSIGN")
public enum SignOperation {
    SIGN_OPERATION_SIGN,
    SIGN_OPERATION_COSIGN,
    SIGN_OPERATION_COUNTERSIGN;
}*/