package es.caib.portafib.api.interna.secure.firma.v1.firmaenservidor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import es.caib.portafib.api.interna.secure.firma.v1.commons.KeyValue;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Schema(description = "Informació del signant",  requiredMode = RequiredMode.REQUIRED)
public class FirmaSimpleSignerInfo {

    /**
     * - eEMGDE.Firma.RolFirma (eEMGDE17.2): Esquemas desarrollados a nivel local y
     * que pueden incluir valores como válida, autentica, refrenda, visa,
     * representa, testimonia, etc..
     */
    @Schema(
            description = "Esquemas desarrollados a nivel local y que pueden incluir valores como válida, autentica, refrenda, visa, representa, testimonia, etc..",
            requiredMode = RequiredMode.NOT_REQUIRED)
    protected String eniRolFirma;

    /**
     * eEMGDE.Firma.Firmante.NombreApellidos (eEMGDE17.5.1): Texto libre. Nombre o
     * razón social de los firmantes.
     */

    @Schema(description = "Nombre o razón social de los firmantes.", requiredMode = RequiredMode.NOT_REQUIRED)
    protected String eniSignerName;

    /**
     * eEMGDE.Firma.Firmante (eEMGDE17.5.2). Número Identificacion Firmantes (NIF)
     */
    @Schema(description = "NIF del firmant.", requiredMode = RequiredMode.NOT_REQUIRED)
    protected String eniSignerAdministrationId;

    /**
     * eEMGDE.Firma.NivelFirma (eEMGDE17.5.4) Indicador normalizado que refleja el
     * grado de confianza de la firma utilizado. Ejemplos: Nick, PIN ciudadano,
     * Firma electrónica avanzada, Claves concertadas, Firma electrónica avanzada
     * basada en certificados, CSV, ..
     */

    @Schema(
            description = "Indicador normalizado que refleja el grado de  confianza de la firma utilizado. "
                    + "Ejemplos: Nick, PIN ciudadano, Firma electrónica avanzada, Claves concertadas, "
                    + "Firma electrónica avanzada basada en certificados, CSV, ..",
            requiredMode = RequiredMode.NOT_REQUIRED)
    protected String eniSignLevel;
    @Schema(description = "Data en que es va realitzar la firma", requiredMode = RequiredMode.NOT_REQUIRED)
    protected Date signDate;

    @Schema(description = "Número de Sèrie del Certificat utilitzat en la firma", requiredMode = RequiredMode.NOT_REQUIRED)
    protected String serialNumberCert;

    @Schema(description = "Issuer del Certificat utilitzat en la firma", requiredMode = RequiredMode.NOT_REQUIRED)
    protected String issuerCert;

    @Schema(description = "Subject del Certificat utilitzat en la firma", requiredMode = RequiredMode.NOT_REQUIRED)
    protected String subjectCert;

    /**
     * eEMGDE.Firma.InformacionAdicional (eEMGDE17.5.5) Ofrecer cualquier otra
     * información que se considere útil acerca del firmante.
     */
    
    @Schema(description = "Ofrecer cualquier otra información que se  considere útil acerca del firmante.", requiredMode = RequiredMode.NOT_REQUIRED)
    protected List<KeyValue> additionalInformation = null;

    public FirmaSimpleSignerInfo() {
        super();
    }

    public FirmaSimpleSignerInfo(String eniRolFirma, String eniSignerName, String eniSignerAdministrationId,
            String eniSignLevel, Date signDate, String serialNumberCert, String issuerCert, String subjectCert,
            List<KeyValue> additionalInformation) {
        super();
        this.eniRolFirma = eniRolFirma;
        this.eniSignerName = eniSignerName;
        this.eniSignerAdministrationId = eniSignerAdministrationId;
        this.eniSignLevel = eniSignLevel;
        this.signDate = signDate;
        this.serialNumberCert = serialNumberCert;
        this.issuerCert = issuerCert;
        this.subjectCert = subjectCert;
        this.additionalInformation = additionalInformation;
    }

    public String getEniRolFirma() {
        return eniRolFirma;
    }

    public void setEniRolFirma(String eniRolFirma) {
        this.eniRolFirma = eniRolFirma;
    }

    public String getEniSignerName() {
        return eniSignerName;
    }

    public void setEniSignerName(String eniSignerName) {
        this.eniSignerName = eniSignerName;
    }

    public String getEniSignerAdministrationId() {
        return eniSignerAdministrationId;
    }

    public void setEniSignerAdministrationId(String eniSignerAdministrationId) {
        this.eniSignerAdministrationId = eniSignerAdministrationId;
    }

    public String getEniSignLevel() {
        return eniSignLevel;
    }

    public void setEniSignLevel(String eniSignLevel) {
        this.eniSignLevel = eniSignLevel;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public String getSerialNumberCert() {
        return serialNumberCert;
    }

    public void setSerialNumberCert(String serialNumberCert) {
        this.serialNumberCert = serialNumberCert;
    }

    public String getIssuerCert() {
        return issuerCert;
    }

    public void setIssuerCert(String issuerCert) {
        this.issuerCert = issuerCert;
    }

    public String getSubjectCert() {
        return subjectCert;
    }

    public void setSubjectCert(String subjectCert) {
        this.subjectCert = subjectCert;
    }

    public List<KeyValue> getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(List<KeyValue> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        str.append("        + eniRolFirma:\t" + getEniRolFirma());
        str.append("\n").append("        + eniSignerName:\t" + getEniSignerName());
        str.append("\n").append("        + eniSignerAdministrationId:\t" + getEniSignerAdministrationId());
        str.append("\n").append("        + eniSignLevel:\t" + getEniSignLevel());
        str.append("\n").append("        + Sign Date:\t" + new SimpleDateFormat().format(getSignDate()));
        str.append("\n").append("        + Subject Cert:\t" + getSubjectCert());
        str.append("\n").append("        + Issuer Cert:\t" + getIssuerCert());

        List<KeyValue> additionalInformation = getAdditionalInformation();

        if (additionalInformation != null && additionalInformation.size() != 0) {
            str.append("\n").append("        + INFORMACIO ADDICIONAL:");
            for (KeyValue KeyValue : additionalInformation) {
                str.append("\n").append(
                        "          >> KEY[" + KeyValue.getKey() + "]: " + KeyValue.getValue());
            }
        }

        return str.toString();

    }

}
