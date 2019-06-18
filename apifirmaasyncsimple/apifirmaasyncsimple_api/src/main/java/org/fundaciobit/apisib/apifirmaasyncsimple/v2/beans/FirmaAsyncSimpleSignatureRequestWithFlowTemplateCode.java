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
public class FirmaAsyncSimpleSignatureRequestWithFlowTemplateCode extends
    FirmaAsyncSimpleSignatureRequestBase {

  protected String flowTemplateCode = null;

  public FirmaAsyncSimpleSignatureRequestWithFlowTemplateCode() {
    super();
  }

  public FirmaAsyncSimpleSignatureRequestWithFlowTemplateCode(String profileCode,
      String title, String description, String reason, FirmaAsyncSimpleFile fileToSign,
      FirmaAsyncSimpleFile originalDetachedSignature, long documentType,
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

  public FirmaAsyncSimpleSignatureRequestWithFlowTemplateCode(String profileCode,
      String title, String description, String reason, FirmaAsyncSimpleFile fileToSign,
      FirmaAsyncSimpleFile originalDetachedSignature, long documentType,
      String documentTypeDescription, String languageDoc, String languageUI, int priority,
      String senderName, String senderDescription, String expedientCode, String expedientName,
      String expedientUrl, String procedureCode, String procedureName,
      String additionalInformation, Double additionalInformationEvaluable,
      String flowTemplateCode, List<FirmaAsyncSimpleAnnex> annexs,
      List<FirmaAsyncSimpleMetadata> metadadaList) {
    super(profileCode, title, description, reason, fileToSign, originalDetachedSignature,
        documentType, documentTypeDescription, languageDoc, languageUI, priority, senderName,
        senderDescription, expedientCode, expedientName, expedientUrl, procedureCode,
        procedureName, additionalInformation, additionalInformationEvaluable, annexs,
        metadadaList);
    this.flowTemplateCode = flowTemplateCode;

  }

  public FirmaAsyncSimpleSignatureRequestWithFlowTemplateCode(
      FirmaAsyncSimpleSignatureRequestBase base, String flowTemplateCode) {
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
