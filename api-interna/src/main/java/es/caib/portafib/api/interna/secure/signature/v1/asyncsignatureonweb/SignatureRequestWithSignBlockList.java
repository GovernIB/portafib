package es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import es.caib.portafib.api.interna.secure.signature.v1.commons.Document;

/**
 * 
 * @author anadal(u80067)
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SignatureRequestWithSignBlockList extends SignatureRequestBase {

  protected SignatureBlock[] signatureBlocks = null;

  public SignatureRequestWithSignBlockList() {
    super();
  }

  public SignatureRequestWithSignBlockList(SignatureRequestBase base,
      SignatureBlock[] signatureBlocks) {
    super(base);
    this.signatureBlocks = signatureBlocks;
  }

  public SignatureRequestWithSignBlockList(String profileCode, String title,
      String description, String reason, Document fileToSign,
      Document originalDetachedSignature, long documentType,
      String documentTypeDescription, String languageDoc, String languageUI, int priority,
      String senderName, String senderDescription, String expedientCode, String expedientName,
      String expedientUrl, String procedureCode, String procedureName,
      String additionalInformation, Double additionalInformationEvaluable,
      SignatureBlock[] signatureBlocks) {
    this(profileCode, title, description, reason, fileToSign, originalDetachedSignature,
        documentType, documentTypeDescription, languageDoc, languageUI, priority, senderName,
        senderDescription, expedientCode, expedientName, expedientUrl, procedureCode,
        procedureName, additionalInformation, additionalInformationEvaluable, signatureBlocks,
        null, null);
  }

  public SignatureRequestWithSignBlockList(String profileCode, String title,
      String description, String reason, Document fileToSign,
      Document originalDetachedSignature, long documentType,
      String documentTypeDescription, String languageDoc, String languageUI, int priority,
      String senderName, String senderDescription, String expedientCode, String expedientName,
      String expedientUrl, String procedureCode, String procedureName,
      String additionalInformation, Double additionalInformationEvaluable,
      SignatureBlock[] signatureBlocks, List<Annex> annexs,
      List<Metadata> metadadaList) {
    super(profileCode, title, description, reason, fileToSign, originalDetachedSignature,
        documentType, documentTypeDescription, languageDoc, languageUI, priority, senderName,
        senderDescription, expedientCode, expedientName, expedientUrl, procedureCode,
        procedureName, additionalInformation, additionalInformationEvaluable, annexs,
        metadadaList);
    this.signatureBlocks = signatureBlocks;

  }

  public SignatureBlock[] getSignatureBlocks() {
    return signatureBlocks;
  }

  public void setSignatureBlocks(SignatureBlock[] signatureBlocks) {
    this.signatureBlocks = signatureBlocks;
  }

}
