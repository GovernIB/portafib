package es.caib.portafib.api.interna.secure.signature.v1.signaturevalidation;

import java.util.List;

/**
 * 
 * @author anadal
 *
 */
public class ValidateSignatureResponse {

    /** CADES, XADES, PADES, ... */
    private String signType;

    /** Attached-implicit, detached-explicit, enveloped, enveloping, ..., */
    private int signMode;

    /** Perfil: BES, EPES, A, XL, .. */
    private String signProfile; //

    private ValidationStatus validationStatus = new ValidationStatus();

    private List<SignatureDetailInfo> signatureDetailInfo = null;

    public ValidationStatus getValidationStatus() {
        return validationStatus;
    }

    public List<SignatureDetailInfo> getSignatureDetailInfo() {
        return signatureDetailInfo;
    }

    public void setSignatureDetailInfo(List<SignatureDetailInfo> signatureDetailInfo) {
        this.signatureDetailInfo = signatureDetailInfo;
    }

    public void setValidationStatus(ValidationStatus validationStatus) {
        this.validationStatus = validationStatus;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public int getSignMode() {
        return signMode;
    }

    public void setSignMode(int signMode) {
        this.signMode = signMode;
    }

    public String getSignProfile() {
        return signProfile;
    }

    public void setSignProfile(String signProfile) {
        this.signProfile = signProfile;
    }

}
