package es.caib.portafib.api.interna.secure.signature.v1.signaturevalidation;

import es.caib.portafib.api.interna.secure.signature.v1.commons.Document;

/**
 * Request object for validating a signature.
 * @author anadal
 * 19 jun 2025 13:38:29
 */
public class ValidateSignatureRequest {

    protected Document detachedDocument;

    protected Document signatureDocument;

    protected SignatureRequestedInformation signatureRequestedInformation;

    public ValidateSignatureRequest() {
    }

    public ValidateSignatureRequest(Document detachedDocument, Document signatureDocument,
            SignatureRequestedInformation signatureRequestedInformation) {
        super();
        this.detachedDocument = detachedDocument;
        this.signatureDocument = signatureDocument;
        this.signatureRequestedInformation = signatureRequestedInformation;
    }

    public Document getDetachedDocument() {
        return detachedDocument;
    }

    public void setDetachedDocument(Document detachedDocument) {
        this.detachedDocument = detachedDocument;
    }

    public Document getSignatureDocument() {
        return signatureDocument;
    }

    public void setSignatureDocument(Document signatureDocument) {
        this.signatureDocument = signatureDocument;
    }

    public SignatureRequestedInformation getSignatureRequestedInformation() {
        return signatureRequestedInformation;
    }

    public void setSignatureRequestedInformation(SignatureRequestedInformation signatureRequestedInformation) {
        this.signatureRequestedInformation = signatureRequestedInformation;
    }

}
