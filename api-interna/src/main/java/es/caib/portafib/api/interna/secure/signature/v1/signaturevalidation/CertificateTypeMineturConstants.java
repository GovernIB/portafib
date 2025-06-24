package es.caib.portafib.api.interna.secure.signature.v1.signaturevalidation;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author anadal
 * 23 juny 2025 11:54
 */
/**
 * 
    CLASSIFICACIO_DESCONEGUDA(-1;

    // 0 –Persona física-
    CLASSIFICACIO_PERSONA_FISICA(0;
    // 1 –Persona jurídica (no cualificado)-
    CLASSIFICACIO_PERSONA_JURIDICA(1;
    // 2 –No cualificados-
    CLASSIFICACIO_NO_QUALIFICATS(2;

    // 3 –Sede según la ley 40/2015(no cualificado)-
    CLASSIFICACIO_SEU_ELECTRONICA(3;
    // 4 –Sello según la ley 40/2015(no cualificado)-
    CLASSIFICACIO_SEGELL(4;
    // 5 –Empleado Público según la ley 40/2015-
    CLASSIFICACIO_EMPLEAT_PUBLIC(5;
    // 6 –Entidad sin personalidad jurídica (no cualificado)-
    CLASSIFICACIO_ENTITAT_SENSE_PERSONALITAT_JURIDICA(6;
    // 7 –Empleado público con seudónimo según el RD 1671/2009-
    CLASSIFICACIO_EMPLEAT_PUBLIC_AMB_PSEUDONIM(7;
    // 8 –Cualificado de sello, según el reglamente UE 910/2014-
    CLASSIFICACIO_SEGELL_QUALIFICAT(8;
    // 9 –Cualificado de autenticación, según el reglamente UE 910/2014-
    CLASSIFICACIO_AUTENTIFICACIO_QUALIFICAT(9;
    // 10 –Cualificado de servicio cualificado de sello de tiempo-
    CLASSIFICACIO_SEGELL_DE_TEMPS(10;
    // 11 –Persona física representante ante las Administraciones Públicas de persona jurídica-
    CLASSIFICACIO_AUTENTIFICACIO_REPRESENTANT_ADMINISTRACIO_PERSONA_JURIDICA(11;
    // 12 –Persona física representante ante las Administraciones Públicas de entidad sin persona
    // jurídica
    CLASSIFICACIO_AUTENTIFICACIO_REPRESENTANT_ADMINISTRACIO_ENTITAT(12;
 */
@Schema(
        description = "Classificació del tipus de certificats:" + "\r\nCLASSIFICACIO_DESCONEGUDA(-1;\r\n" + "\r\n"
                + "                // 0 –Persona física-\r\n" + "                CLASSIFICACIO_PERSONA_FISICA(0;\r\n"
                + "                // 1 –Persona jurídica (no cualificado)-\r\n"
                + "                CLASSIFICACIO_PERSONA_JURIDICA(1;\r\n" + "                // 2 –No cualificados-\r\n"
                + "                CLASSIFICACIO_NO_QUALIFICATS(2;\r\n" + "\r\n"
                + "                // 3 –Sede según la ley 40/2015(no cualificado)-\r\n"
                + "                CLASSIFICACIO_SEU_ELECTRONICA(3;\r\n"
                + "                // 4 –Sello según la ley 40/2015(no cualificado)-\r\n"
                + "                CLASSIFICACIO_SEGELL(4;\r\n"
                + "                // 5 –Empleado Público según la ley 40/2015-\r\n"
                + "                CLASSIFICACIO_EMPLEAT_PUBLIC(5;\r\n"
                + "                // 6 –Entidad sin personalidad jurídica (no cualificado)-\r\n"
                + "                CLASSIFICACIO_ENTITAT_SENSE_PERSONALITAT_JURIDICA(6;\r\n"
                + "                // 7 –Empleado público con seudónimo según el RD 1671/2009-\r\n"
                + "                CLASSIFICACIO_EMPLEAT_PUBLIC_AMB_PSEUDONIM(7;\r\n"
                + "                // 8 –Cualificado de sello, según el reglamente UE 910/2014-\r\n"
                + "                CLASSIFICACIO_SEGELL_QUALIFICAT(8;\r\n"
                + "                // 9 –Cualificado de autenticación, según el reglamente UE 910/2014-\r\n"
                + "                CLASSIFICACIO_AUTENTIFICACIO_QUALIFICAT(9;\r\n"
                + "                // 10 –Cualificado de servicio cualificado de sello de tiempo-\r\n"
                + "                CLASSIFICACIO_SEGELL_DE_TEMPS(10;\r\n"
                + "                // 11 –Persona física representante ante las Administraciones Públicas de persona jurídica-\r\n"
                + "                CLASSIFICACIO_AUTENTIFICACIO_REPRESENTANT_ADMINISTRACIO_PERSONA_JURIDICA(11;\r\n"
                + "                // 12 –Persona física representante ante las Administraciones Públicas de entidad sin persona\r\n"
                + "                // jurídica\r\n"
                + "                CLASSIFICACIO_AUTENTIFICACIO_REPRESENTANT_ADMINISTRACIO_ENTITAT(12;",
        format = "int32",
        enumAsRef = true,
        /** Parxe utilitzat per a la generació correcta dels noms dels enums dins de l'openapi.json */
        example = "CLASSIFICACIO_DESCONEGUDA|CLASSIFICACIO_PERSONA_FISICA|CLASSIFICACIO_PERSONA_JURIDICA\n"
                + "|CLASSIFICACIO_NO_QUALIFICATS|CLASSIFICACIO_SEU_ELECTRONICA|CLASSIFICACIO_SEGELL"
                + "|CLASSIFICACIO_EMPLEAT_PUBLIC|CLASSIFICACIO_ENTITAT_SENSE_PERSONALITAT_JURIDICA"
                + "|CLASSIFICACIO_EMPLEAT_PUBLIC_AMB_PSEUDONIM|CLASSIFICACIO_SEGELL_QUALIFICAT"
                + "|CLASSIFICACIO_AUTENTIFICACIO_QUALIFICAT|CLASSIFICACIO_SEGELL_DE_TEMPS"
                + "|CLASSIFICACIO_AUTENTIFICACIO_REPRESENTANT_ADMINISTRACIO_PERSONA_JURIDICA"
                + "|CLASSIFICACIO_AUTENTIFICACIO_REPRESENTANT_ADMINISTRACIO_ENTITAT")
public enum CertificateTypeMineturConstants {

    CLASSIFICACIO_DESCONEGUDA(-1),
    // 0 –Persona física-
    CLASSIFICACIO_PERSONA_FISICA(0),
    // 1 –Persona jurídica (no cualificado)-
    CLASSIFICACIO_PERSONA_JURIDICA(1),
    // 2 –No cualificados-
    CLASSIFICACIO_NO_QUALIFICATS(2),

    // 3 –Sede según la ley 40/2015(no cualificado)-
    CLASSIFICACIO_SEU_ELECTRONICA(3),
    // 4 –Sello según la ley 40/2015(no cualificado)-
    CLASSIFICACIO_SEGELL(4),
    // 5 –Empleado Público según la ley 40/2015-
    CLASSIFICACIO_EMPLEAT_PUBLIC(5),
    // 6 –Entidad sin personalidad jurídica (no cualificado)-
    CLASSIFICACIO_ENTITAT_SENSE_PERSONALITAT_JURIDICA(6),
    // 7 –Empleado público con seudónimo según el RD 1671/2009-
    CLASSIFICACIO_EMPLEAT_PUBLIC_AMB_PSEUDONIM(7),
    // 8 –Cualificado de sello, según el reglamente UE 910/2014-
    CLASSIFICACIO_SEGELL_QUALIFICAT(8),
    // 9 –Cualificado de autenticación, según el reglamente UE 910/2014-
    CLASSIFICACIO_AUTENTIFICACIO_QUALIFICAT(9),
    // 10 –Cualificado de servicio cualificado de sello de tiempo-
    CLASSIFICACIO_SEGELL_DE_TEMPS(10),
    // 11 –Persona física representante ante las Administraciones Públicas de persona jurídica-
    CLASSIFICACIO_AUTENTIFICACIO_REPRESENTANT_ADMINISTRACIO_PERSONA_JURIDICA(11),
    // 12 –Persona física representante ante las Administraciones Públicas de entidad sin persona
    // jurídica
    CLASSIFICACIO_AUTENTIFICACIO_REPRESENTANT_ADMINISTRACIO_ENTITAT(12);

    public final Integer value;

    CertificateTypeMineturConstants(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
