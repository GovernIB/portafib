package es.caib.portafib.api.interna.secure.firma.v1.commons;

import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;


    @Schema(name = "SignProfile", description = "Perfils de firma disponibles", enumAsRef = true, examples ="SHA-1|SHA-256")
    public enum SignProfile {
        SIGNPROFILE_BES("AdES-BES"),
        SIGNPROFILE_EPES("AdES-EPES"),
        SIGNPROFILE_T("AdES-T"),
        SIGNPROFILE_C("AdES-C"),
        SIGNPROFILE_X("AdES-X"),
        SIGNPROFILE_X1("AdES-X1"),
        SIGNPROFILE_X2("AdES-X2"),
        SIGNPROFILE_XL("AdES-XL"),
        SIGNPROFILE_XL1("AdES-XL1"),
        SIGNPROFILE_XL2("AdES-XL2"),
        SIGNPROFILE_A("AdES-A"),
        SIGNPROFILE_PADES_LTV("PAdES-LTV"),
        SIGNPROFILE_PADES_BASIC("PAdES-Basic");
        
        public final String value;
        
        SignProfile(String value){
            this.value = value;
        }
        
        @Override
        @JsonValue
        public String toString() {
          return String.valueOf(value);
        }
    }
