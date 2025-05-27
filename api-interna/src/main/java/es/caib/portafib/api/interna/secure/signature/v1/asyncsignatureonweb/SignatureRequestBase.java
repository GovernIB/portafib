package es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb;

import java.util.List;
import es.caib.portafib.api.interna.secure.signature.v1.commons.Document;
import es.caib.portafib.api.interna.secure.signature.v1.commons.DocumentaryTypeConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

/**
 * 
 * @author anadal
 * 16 may 2025 11:57:23
 */
public class SignatureRequestBase {

    @Schema(
            description = "Perfil a utilitzar per la Firma.Consultar amb administrador del PortaFirmes",
            requiredMode = RequiredMode.REQUIRED)
    protected String profileCode;

    @Schema(description = "Document a signar", requiredMode = RequiredMode.REQUIRED)
    protected Document fileToSign;

    @Schema(description = "Només per CAdES i XAdEs Detached amb firma prèvia")
    protected Document originalDetachedSignature;

    @Schema(description = "Títol de la Petició de Firma", requiredMode = RequiredMode.REQUIRED)
    protected String title;

    @Schema(description = "Descripció de la Petició de Firma", requiredMode = RequiredMode.REQUIRED)
    protected String description;

    @Schema(description = "Raó de la realització de la firma", requiredMode = RequiredMode.REQUIRED)
    protected String reason;

    @Schema(
            description = "Identificador de Tipus de Document.Els valors base s poden obtenir de l'enumeració"
                    + " DocumentaryTypeConstants però es recomana fer una consulta al mètode getDocumentaryTypes()",
            requiredMode = RequiredMode.REQUIRED)
    protected long documentType = DocumentaryTypeConstants.ALTRES.getValue();

    @Schema(description = "Descripció detallada del Tipus documental.")
    protected String documentTypeDescription; // Quan tipus es TD99

    @Schema(
            description = "Idioma en que està escrit el document.Valors són 'es' o 'ca' però es realitzar "
                    + "una cridada al mètode getLanguages()",
            requiredMode = RequiredMode.REQUIRED)
    protected String languageDoc;

    @Schema(description = "Idioma de la interficie d'usuari (es o ca)", requiredMode = RequiredMode.REQUIRED)
    protected String languageUI;

    @Schema(
            description = "Prioritat de la Petició. Veure enumeració PriorityConstants.",
            requiredMode = RequiredMode.REQUIRED)
    protected int priority;

    @Schema(description = "Nom de la persona/aplicació que envia la petició.", requiredMode = RequiredMode.REQUIRED)
    protected String senderName;

    @Schema(
            description = "Descripció de la persona o responsable de l'aplicació que envia la petició."
                    + " Es sol posar el correu electronic de la persona que que envia la petició.")
    protected String senderDescription;

    @Schema(description = "Codi de l'expedient")
    protected String expedientCode;

    @Schema(description = "Nom de l'expedient")
    protected String expedientName;

    @Schema(description = "URL de l'expedient")
    protected String expedientUrl;

    @Schema(description = "Codi del Procediment ")
    protected String procedureCode;

    @Schema(description = "Nom del Procediment")
    protected String procedureName;

    @Schema(description = "Informació Addicional")
    protected String additionalInformation;

    @Schema(
            description = "Informació Addicional avauluable. Per exemple en documents de tipus factura"
                    + " en aquest camp s'insereix la quantitat final de la factura.")
    protected Double additionalInformationEvaluable;

    @Schema(description = "Llista de document annexes a la petició de firma")
    protected List<Annex> annexs = null;

    @Schema(description = "Llista de Metadades associades a la Petició de Firma")
    protected List<Metadata> metadadaList = null;

    public SignatureRequestBase() {
        super();
    }

    public SignatureRequestBase(String profileCode, String title, String description, String reason,
            Document fileToSign, Document originalDetachedSignature, long documentType, String documentTypeDescription,
            String languageDoc, String languageUI, int priority, String senderName, String senderDescription,
            String expedientCode, String expedientName, String expedientUrl, String procedureCode, String procedureName,
            String additionalInformation, Double additionalInformationEvaluable) {
        this(profileCode, title, description, reason, fileToSign, originalDetachedSignature, documentType,
                documentTypeDescription, languageDoc, languageUI, priority, senderName, senderDescription,
                expedientCode, expedientName, expedientUrl, procedureCode, procedureName, additionalInformation,
                additionalInformationEvaluable, null, null);
    }

    public SignatureRequestBase(String profileCode, String title, String description, String reason,
            Document fileToSign, Document originalDetachedSignature, long documentType, String documentTypeDescription,
            String languageDoc, String languageUI, int priority, String senderName, String senderDescription,
            String expedientCode, String expedientName, String expedientUrl, String procedureCode, String procedureName,
            String additionalInformation, Double additionalInformationEvaluable, List<Annex> annexs,
            List<Metadata> metadadaList) {
        super();
        this.profileCode = profileCode;
        this.title = title;
        this.description = description;
        this.reason = reason;
        this.fileToSign = fileToSign;
        this.originalDetachedSignature = originalDetachedSignature;
        this.documentType = documentType;
        this.documentTypeDescription = documentTypeDescription;
        this.languageDoc = languageDoc;
        this.languageUI = languageUI;
        this.priority = priority;
        this.senderName = senderName;
        this.senderDescription = senderDescription;
        this.expedientCode = expedientCode;
        this.expedientName = expedientName;
        this.expedientUrl = expedientUrl;
        this.procedureCode = procedureCode;
        this.procedureName = procedureName;
        this.additionalInformation = additionalInformation;
        this.additionalInformationEvaluable = additionalInformationEvaluable;
        this.annexs = annexs;
        this.metadadaList = metadadaList;
    }

    public SignatureRequestBase(SignatureRequestBase base) {
        super();
        this.profileCode = base.profileCode;
        this.title = base.title;
        this.description = base.description;
        this.reason = base.reason;
        this.fileToSign = base.fileToSign;
        this.originalDetachedSignature = base.originalDetachedSignature;
        this.documentType = base.documentType;
        this.documentTypeDescription = base.documentTypeDescription;
        this.languageDoc = base.languageDoc;
        this.languageUI = base.languageUI;
        this.priority = base.priority;
        this.senderName = base.senderName;
        this.senderDescription = base.senderDescription;
        this.expedientCode = base.expedientCode;
        this.expedientName = base.expedientName;
        this.expedientUrl = base.expedientUrl;
        this.procedureCode = base.procedureCode;
        this.procedureName = base.procedureName;
        this.additionalInformation = base.additionalInformation;
        this.additionalInformationEvaluable = base.additionalInformationEvaluable;
        this.annexs = base.annexs;
        this.metadadaList = base.metadadaList;
    }

    public String getProfileCode() {
        return profileCode;
    }

    public void setProfileCode(String profileCode) {
        this.profileCode = profileCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Document getFileToSign() {
        return fileToSign;
    }

    public void setFileToSign(Document fileToSign) {
        this.fileToSign = fileToSign;
    }

    public Document getOriginalDetachedSignature() {
        return originalDetachedSignature;
    }

    public void setOriginalDetachedSignature(Document originalDetachedSignature) {
        this.originalDetachedSignature = originalDetachedSignature;
    }

    public long getDocumentType() {
        return documentType;
    }

    public void setDocumentType(long documentType) {
        this.documentType = documentType;
    }

    public String getDocumentTypeDescription() {
        return documentTypeDescription;
    }

    public void setDocumentTypeDescription(String documentTypeDescription) {
        this.documentTypeDescription = documentTypeDescription;
    }

    public String getLanguageDoc() {
        return languageDoc;
    }

    public void setLanguageDoc(String languageDoc) {
        this.languageDoc = languageDoc;
    }

    public String getLanguageUI() {
        return languageUI;
    }

    public void setLanguageUI(String languageUI) {
        this.languageUI = languageUI;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderDescription() {
        return senderDescription;
    }

    public void setSenderDescription(String senderDescription) {
        this.senderDescription = senderDescription;
    }

    public String getExpedientCode() {
        return expedientCode;
    }

    public void setExpedientCode(String expedientCode) {
        this.expedientCode = expedientCode;
    }

    public String getExpedientName() {
        return expedientName;
    }

    public void setExpedientName(String expedientName) {
        this.expedientName = expedientName;
    }

    public String getExpedientUrl() {
        return expedientUrl;
    }

    public void setExpedientUrl(String expedientUrl) {
        this.expedientUrl = expedientUrl;
    }

    public String getProcedureCode() {
        return procedureCode;
    }

    public void setProcedureCode(String procedureCode) {
        this.procedureCode = procedureCode;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public Double getAdditionalInformationEvaluable() {
        return additionalInformationEvaluable;
    }

    public void setAdditionalInformationEvaluable(Double additionalInformationEvaluable) {
        this.additionalInformationEvaluable = additionalInformationEvaluable;
    }

    public List<Annex> getAnnexs() {
        return annexs;
    }

    public void setAnnexs(List<Annex> annexs) {
        this.annexs = annexs;
    }

    public List<Metadata> getMetadadaList() {
        return metadadaList;
    }

    public void setMetadadaList(List<Metadata> metadadaList) {
        this.metadadaList = metadadaList;
    }

}
