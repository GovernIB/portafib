package es.caib.portafib.api.interna.secure.firma.v1.commons;

import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;

    @Schema(name = "SignType", description = "Tipus de firma disponibles", enumAsRef = true, examples ="PAdES|XAdES")
    public enum SignType {
        SIGNTYPE_PADES("PAdES"),
        SIGNTYPE_XADES("XAdES"),
        SIGNTYPE_CADES("CAdES"),
        SIGNTYPE_FACTURAE("FacturaE"),
        SIGNTYPE_OOXML("OOXML"),
        SIGNTYPE_ODF("ODF"),        
        SIGNTYPE_SMIME("SMIME"),
        SIGNTYPE_CADESASICS("CAdES-ASiC-S"),
        SIGNTYPE_XADESASICS("XAdES-ASiC-S"),
        SIGNTYPE_PKCS("PKCS#1");
        
        public final String value;
        
        SignType(String value){
            this.value = value;
        }
        
        @Override
        @JsonValue
        public String toString() {
          return String.valueOf(value);
        }
        
    }

