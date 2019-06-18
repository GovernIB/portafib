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
public class FirmaAsyncSimpleSignatureRequest extends FirmaAsyncSimpleSignatureRequestBase {

  protected FirmaAsyncSimpleSignatureBlock[] signatureBlocks = null;

  public FirmaAsyncSimpleSignatureRequest() {
    super();
  }

  public FirmaAsyncSimpleSignatureRequest(FirmaAsyncSimpleSignatureRequestBase base,
      FirmaAsyncSimpleSignatureBlock[] signatureBlocks) {
    super(base);
    this.signatureBlocks = signatureBlocks;
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
    super(profileCode, title, description, reason, fileToSign, originalDetachedSignature,
        documentType, documentTypeDescription, languageDoc, languageUI, priority, senderName,
        senderDescription, expedientCode, expedientName, expedientUrl, procedureCode,
        procedureName, additionalInformation, additionalInformationEvaluable, annexs,
        metadadaList);
    this.signatureBlocks = signatureBlocks;

  }

  public FirmaAsyncSimpleSignatureBlock[] getSignatureBlocks() {
    return signatureBlocks;
  }

  public void setSignatureBlocks(FirmaAsyncSimpleSignatureBlock[] signatureBlocks) {
    this.signatureBlocks = signatureBlocks;
  }

}
