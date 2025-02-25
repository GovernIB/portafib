package es.caib.portafib.api.interna.secure.firma.v1.commons;

import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "SignAlgorithm", description = "Algorismes de firma disponibles", enumAsRef = true, examples ="SHA-1|SHA-256")
public enum SignAlgorithm {
    SIGN_ALGORITHM_SHA1("SHA-1"),
    SIGN_ALGORITHM_SHA256("SHA-256"),
    SIGN_ALGORITHM_SHA384("SHA-384"),
    SIGN_ALGORITHM_SHA512("SHA-512");
    
    public final String value;
    
    SignAlgorithm(String value){
        this.value = value;
    }
    
    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }
}