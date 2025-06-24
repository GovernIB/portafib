package es.caib.portafib.api.interna.secure.signature.v1.signaturevalidation;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author anadal
 * 6 may 2025 9:12:24
 * 
 * 
 *  // certClassification:
    
 */
@Schema(
        description = 
                  "* ESEAL: Certificado de sello electrónico. Pertenece a una persona jurídica pero NO incluye un custodio.\n"
                + "* ESIG: Certificado para firma electrónica (persona física).\n"
                + "* WSA: Certificado para autenticación de servidor web.\r\n"
                + "    public static final String EIDAS_UNKNOWN = \"UNKNOWN\"; // Se desconoce el tipo del certificado\r\n"
                + "                                                          // (Se considera no cualificado a menos\r\n"
                + "                                                          // que se indique lo contrario en\r\n"
                + "                                                          // certQualified).",
        enumAsRef = true,
                /** Parxe utilitzat per a la generació correcta dels noms dels enums dins de l'openapi.json */
        example = "ESEAL|ESIG|WSA|UNKNOWN")
public enum CertificateTypeEidasConstants {
    ESEAL("ESEAL"), ESIG("ESIG"),
    WSA("WSA"), UNKNOWN("UNKNOWN") ;

    public final String value;

    CertificateTypeEidasConstants(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
