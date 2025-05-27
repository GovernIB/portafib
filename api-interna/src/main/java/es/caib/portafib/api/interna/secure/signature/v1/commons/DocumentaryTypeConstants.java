package es.caib.portafib.api.interna.secure.signature.v1.commons;


import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal
 * 16 may 2025 12:30:53
 */
@Schema(
        name = "DocumentaryTypeConstants",
        description = "Valors:\n"
                + "• RESOLUCIO=1(TipusResolució - TD01)\n"
                + "• ACORD=2(Tipus Acord - TD02)\n"
                + "• CONTRACTE=3(Tipus Contracte - TD03)\n"
                + "• CONVENI=4(Tipus Conveni - TD04)\n"
                + "• DECLARACIO=5(Tipus Declaració - TD05)\n"
                + "• COMUNICACIO=6(Tipus Comunicació - TD06)\n"
                + "• NOTIFICACIO=7(Tipus Notificació - TD07)\n"
                + "• PUBLICACIO=8(Tipus Publicació - TD08)\n"
                + "• JUSTIFICANT_RECEPCIO=9(Tipus Justificant de Recepció - TD09)\n"
                + "• ACTA=10(Tipus Acta - TD10)\n"
                + "• CERTIFICAT=11(Tipus Certificat - TD11)\n"
                + "• DILIGENCIA=12(Tipus Diligencia - TD12)\n"
                + "• INFORME=13(Tipus Informe - TD13)\n"
                + "• SOLICITUD=14(Tipus Sol·licitud - TD14)\n"
                + "• DENUNCIA=15(Tipus Denuncia - TD15)\n"
                + "• ALEGACIO=16(Tipus Al·legació - TD16)\n"
                + "• RECURS=17(Tipus Recurs - TD17)\n"
                + "• COMUNICACIO_CIUTADA=18(Tipus Comunicació Ciutadà - TD18)\n"
                + "• FACTURA=19(Tipus Factura - TD19)\n"
                + "• ALTRES_INCAUTATS=20(Tipus Altres Encautats - TD20)\n"
                + "• ALTRES=99(Tipus Altres - TD99)"
                       ,
        format = "int64",
        enumAsRef = true,
        /** Parxe utilitzat per a la generació correcta dels noms dels enums dins de l'openapi.json */
        example = "RESOLUCIO(Tipus Resolució - TD01)"
                + "|ACORD(Tipus Acord - TD02)"
                + "|CONTRACTE(Tipus Contracte - TD03)"
                + "|CONVENI(Tipus Conveni - TD04)"
                + "|DECLARACIO(Tipus Declaració - TD05)"
                + "|COMUNICACIO(Tipus Comunicació - TD06)"
                + "|NOTIFICACIO(Tipus Notificació - TD07)"
                + "|PUBLICACIO(Tipus Publicació - TD08)"
                + "|JUSTIFICANT_RECEPCIO(Tipus Justificant de Recepció - TD09)"
                + "|ACTA(Tipus Acta - TD10)"
                + "|CERTIFICAT(Tipus Certificat - TD11)"
                + "|DILIGENCIA(Tipus Diligencia - TD12)"
                + "|INFORME(Tipus Informe - TD13)"
                + "|SOLICITUD(Tipus Sol·licitud - TD14)"
                + "|DENUNCIA(Tipus Denuncia - TD15)"
                + "|ALEGACIO(Tipus Al·legació - TD16)"
                + "|RECURS(Tipus Recurs - TD17)"
                + "|COMUNICACIO_CIUTADA(Tipus Comunicació Ciutadà - TD18)"
                + "|FACTURA(Tipus Factura - TD19)"
                + "|ALTRES_INCAUTATS(Tipus Altres Encautats - TD20)"
                + "|ALTRES(Tipus Altres - TD99)")
public enum DocumentaryTypeConstants {
/*
public static final long DOCUMENT_TYPE_RESOLUCIO = 1; // TD01
    public static final long DOCUMENT_TYPE_ACORD = 2; // TD02
    public static final long DOCUMENT_TYPE_CONTRACTE = 3; // ...
    public static final long DOCUMENT_TYPE_CONVENI = 4;
    public static final long DOCUMENT_TYPE_DECLARACIO = 5;
    public static final long DOCUMENT_TYPE_COMUNICACIO = 6;
    public static final long DOCUMENT_TYPE_NOTIFICACIO = 7;
    public static final long DOCUMENT_TYPE_PUBLICACIO = 8;
    public static final long DOCUMENT_TYPE_JUSTIFICANT_RECEPCIO = 9;
    public static final long DOCUMENT_TYPE_ACTA = 10;
    public static final long DOCUMENT_TYPE_CERTIFICAT = 11;
    public static final long DOCUMENT_TYPE_DILIGENCIA = 12;
    public static final long DOCUMENT_TYPE_INFORME = 13;
    public static final long DOCUMENT_TYPE_SOLICITUD = 14;
    public static final long DOCUMENT_TYPE_DENUNCIA = 15;
    public static final long DOCUMENT_TYPE_ALEGACIO = 16;
    public static final long DOCUMENT_TYPE_RECURS = 17;
    public static final long DOCUMENT_TYPE_COMUNICACIO_CIUTADA = 18;
    public static final long DOCUMENT_TYPE_FACTURA = 19; // TD19
    public static final long DOCUMENT_TYPE_ALTRES_INCAUTATS = 20; // TD20
    public static final long DOCUMENT_TYPE_ALTRES = 99; // TD99
*/
    RESOLUCIO(1L), // TD01
    ACORD(2L), // TD02
    CONTRACTE(3L), // ...
    CONVENI(4L),
    DECLARACIO(5L),
    COMUNICACIO(6L),
    NOTIFICACIO(7L),
    PUBLICACIO(8L),
    JUSTIFICANT_RECEPCIO(9L),
    ACTA(10L),
    CERTIFICAT(11L),
    DILIGENCIA(12L),
    INFORME(13L),
    SOLICITUD(14L),
    DENUNCIA(15L),
    ALEGACIO(16L),
    RECURS(17L),
    COMUNICACIO_CIUTADA(18L),
    FACTURA(19L), // TD19
    ALTRES_INCAUTATS(20L), // TD20
    ALTRES(99L); // TD99
    
    public final Long value;

    DocumentaryTypeConstants(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static DocumentaryTypeConstants fromValue(Long value) {
        for (DocumentaryTypeConstants b : DocumentaryTypeConstants.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "' for DocumentaryTypeConstants.");
    }
}
