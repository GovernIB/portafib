package es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author anadal
 * 16 may 2025 12:47:45
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SignatureRequestInfo {

    protected long signatureRequestID;

    protected String languageUI;

    /**
     * @param message
     */
    public SignatureRequestInfo() {
        super();
    }

    public SignatureRequestInfo(long signatureRequestID, String languageUI) {
        super();
        this.signatureRequestID = signatureRequestID;
        this.languageUI = languageUI;
    }

    public long getSignatureRequestID() {
        return signatureRequestID;
    }

    public void setSignatureRequestID(long signatureRequestID) {
        this.signatureRequestID = signatureRequestID;
    }

    public String getLanguageUI() {
        return languageUI;
    }

    public void setLanguageUI(String languageUI) {
        this.languageUI = languageUI;
    }

}
