package es.caib.portafib.api.interna.all.exemplepublic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal
 *
 */
@Schema(name = "ExempleDeEnum", enumAsRef = true)
public enum ExempleDeEnum {
    VALOR("VALOR"), FITXER("FITXER");

    private String value;

    ExempleDeEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
    
    @Override
    public String toString() {
      return String.valueOf(value);
    }
    @JsonCreator
    public static ExempleDeEnum fromValue(String input) {
      for (ExempleDeEnum b : ExempleDeEnum.values()) {
        if (b.value.equals(input)) {
          return b;
        }
      }
      return null;
    }

}
