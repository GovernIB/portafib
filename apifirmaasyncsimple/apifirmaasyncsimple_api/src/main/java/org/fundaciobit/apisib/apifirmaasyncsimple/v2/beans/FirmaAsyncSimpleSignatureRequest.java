package org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author anadal(u80067)
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FirmaAsyncSimpleSignatureRequest {

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

  public static final int PRIORITY_PAUSED_PAUSADA = 0; // Prioritat Pausada
  public static final int PRIORITY_INSIGNIFICANT_INSIGNIFICANT = 1; // =Prioritat Insignificant
  public static final int PRIORITY_VERYLOW_MOLTBAIXA = 2; // =Prioritat Molt Baixa
  public static final int PRIORITY_LOW_BAIXA = 3; // =Prioritat Baixa
  public static final int PRIORITY_NORMALLOW_NORMALBAIXA = 4; // =Prioritat Normal-Baixa
  public static final int PRIORITY_NORMAL_NORMAL = 5; // =Prioritat Normal
  public static final int PRIORITY_NORMALHIGH_NORMALALTA = 6; // =Prioritat Normal-Alta
  public static final int PRIORITY_HIGH_ALTA = 7; // =Prioritat Alta
  public static final int PRIORITY_VERYHIGH_MOLTALTA = 8; // =Prioritat Molt Alta
  public static final int PRIORITY_IMMEDIATE_INMEDIATA = 9; // =Prioritat Immediata

  public static final String LANGUAGE_CA = "ca"; // Català
  public static final String LANGUAGE_ES = "es"; // Castellano

  protected String profileCode;

  protected String title;
  protected String description;
  protected String reason;
  protected FirmaAsyncSimpleFile fileToSign;
  protected FirmaAsyncSimpleFile originalDetachedSignature;
  protected long documentType = DOCUMENT_TYPE_ALTRES;
  protected String documentTypeDescription; // Quan tipus es TD99

  protected String languageDoc;
  protected String languageUI;
  protected int priority;

  protected String senderName;
  protected String senderDescription;

  protected String expedientCode;
  protected String expedientName;
  protected String expedientUrl;

  protected String procedureCode;
  protected String procedureName;

  protected String additionalInformation;
  protected Double additionalInformationEvaluable;

  protected FirmaAsyncSimpleSignatureBlock[] signatureBlocks = null;

  protected List<FirmaAsyncSimpleAnnex> annexs = null;

  protected List<FirmaAsyncSimpleMetadata> metadadaList = null;

  public FirmaAsyncSimpleSignatureRequest() {
    super();
  }

  public FirmaAsyncSimpleSignatureRequest(String profileCode, String title,
      String description, String reason, FirmaAsyncSimpleFile fileToSign,
      FirmaAsyncSimpleFile originalDetachedSignature, long documentType,
      String documentTypeDescription, String languageDoc, String languageUI, int priority,
      String senderName, String senderDescription, String expedientCode, String expedientName,
      String expedientUrl, String procedureCode, String procedureName,
      String additionalInformation, Double additionalInformationEvaluable,
      FirmaAsyncSimpleSignatureBlock[] signatureBlocks) {
    this(profileCode, title, description, reason, fileToSign, originalDetachedSignature,
        documentType, documentTypeDescription, languageDoc, languageUI, priority, senderName,
        senderDescription, expedientCode, expedientName, expedientUrl, procedureCode,
        procedureName, additionalInformation, additionalInformationEvaluable, signatureBlocks,
        null, null);
  }

  public FirmaAsyncSimpleSignatureRequest(String profileCode, String title,
      String description, String reason, FirmaAsyncSimpleFile fileToSign,
      FirmaAsyncSimpleFile originalDetachedSignature, long documentType,
      String documentTypeDescription, String languageDoc, String languageUI, int priority,
      String senderName, String senderDescription, String expedientCode, String expedientName,
      String expedientUrl, String procedureCode, String procedureName,
      String additionalInformation, Double additionalInformationEvaluable,
      FirmaAsyncSimpleSignatureBlock[] signatureBlocks, List<FirmaAsyncSimpleAnnex> annexs,
      List<FirmaAsyncSimpleMetadata> metadadaList) {
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
    this.signatureBlocks = signatureBlocks;
    this.annexs = annexs;
    this.metadadaList = metadadaList;
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

  public FirmaAsyncSimpleFile getFileToSign() {
    return fileToSign;
  }

  public void setFileToSign(FirmaAsyncSimpleFile fileToSign) {
    this.fileToSign = fileToSign;
  }

  public FirmaAsyncSimpleFile getOriginalDetachedSignature() {
    return originalDetachedSignature;
  }

  public void setOriginalDetachedSignature(FirmaAsyncSimpleFile originalDetachedSignature) {
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

  public FirmaAsyncSimpleSignatureBlock[] getSignatureBlocks() {
    return signatureBlocks;
  }

  public void setSignatureBlocks(FirmaAsyncSimpleSignatureBlock[] signatureBlocks) {
    this.signatureBlocks = signatureBlocks;
  }

  public List<FirmaAsyncSimpleAnnex> getAnnexs() {
    return annexs;
  }

  public void setAnnexs(List<FirmaAsyncSimpleAnnex> annexs) {
    this.annexs = annexs;
  }

  public List<FirmaAsyncSimpleMetadata> getMetadadaList() {
    return metadadaList;
  }

  public void setMetadadaList(List<FirmaAsyncSimpleMetadata> metadadaList) {
    this.metadadaList = metadadaList;
  }

}
