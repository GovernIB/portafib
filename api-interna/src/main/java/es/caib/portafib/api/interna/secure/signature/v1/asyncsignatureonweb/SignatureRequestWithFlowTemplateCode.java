package es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import es.caib.portafib.api.interna.secure.signature.v1.commons.Document;

/**
 * 
 * @author anadal
 * 16 may 2025 12:02:33
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SignatureRequestWithFlowTemplateCode extends
    SignatureRequestBase {

  protected String flowTemplateCode = null;

  public SignatureRequestWithFlowTemplateCode() {
    super();
  }

  public SignatureRequestWithFlowTemplateCode(String profileCode,
      String title, String description, String reason, Document fileToSign,
      Document originalDetachedSignature, long documentType,
      String documentTypeDescription, String languageDoc, String languageUI, int priority,
      String senderName, String senderDescription, String expedientCode, String expedientName,
      String expedientUrl, String procedureCode, String procedureName,
      String additionalInformation, Double additionalInformationEvaluable,
      String flowTemplateCode) {
    this(profileCode, title, description, reason, fileToSign, originalDetachedSignature,
        documentType, documentTypeDescription, languageDoc, languageUI, priority, senderName,
        senderDescription, expedientCode, expedientName, expedientUrl, procedureCode,
        procedureName, additionalInformation, additionalInformationEvaluable,
        flowTemplateCode, null, null);
  }

  public SignatureRequestWithFlowTemplateCode(String profileCode,
      String title, String description, String reason, Document fileToSign,
      Document originalDetachedSignature, long documentType,
      String documentTypeDescription, String languageDoc, String languageUI, int priority,
      String senderName, String senderDescription, String expedientCode, String expedientName,
      String expedientUrl, String procedureCode, String procedureName,
      String additionalInformation, Double additionalInformationEvaluable,
      String flowTemplateCode, List<Annex> annexs,
      List<Metadata> metadadaList) {
    super(profileCode, title, description, reason, fileToSign, originalDetachedSignature,
        documentType, documentTypeDescription, languageDoc, languageUI, priority, senderName,
        senderDescription, expedientCode, expedientName, expedientUrl, procedureCode,
        procedureName, additionalInformation, additionalInformationEvaluable, annexs,
        metadadaList);
    this.flowTemplateCode = flowTemplateCode;

  }

  public SignatureRequestWithFlowTemplateCode(
      SignatureRequestBase base, String flowTemplateCode) {
    super(base);
    this.flowTemplateCode = flowTemplateCode;
  }

  public String getFlowTemplateCode() {
    return flowTemplateCode;
  }

  public void setFlowTemplateCode(String flowTemplateCode) {
    this.flowTemplateCode = flowTemplateCode;
  }

}
