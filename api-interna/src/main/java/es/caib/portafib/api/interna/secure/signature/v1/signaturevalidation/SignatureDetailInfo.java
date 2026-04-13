package es.caib.portafib.api.interna.secure.signature.v1.signaturevalidation;

import java.util.Date;
import java.util.List;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal
 *
 */
@Schema(description = "Información detallada de la firma")
public class SignatureDetailInfo {

    protected String algorithm;

    protected String digestValue;

    protected Date signDate;

    /**
     * 
     */
    protected List<SignatureCheck> validChecks;

    protected List<SignatureCheck> invalidChecks;

    protected List<SignatureCheck> indeterminateChecks;

    /**
     * En firmes EPES retorna l'identificaor de la política de firma
     */
    protected String policyIdentifier;

    protected CertificateInformation certificateInfo;

    @ArraySchema(schema = @Schema(type = "string", format = "byte"))
    protected byte[][] certificateChain;

    protected TimeStampInfo timeStampInfo;

    public CertificateInformation getCertificateInfo() {
        return certificateInfo;
    }

    public void setCertificateInfo(CertificateInformation certificateInfo) {
        this.certificateInfo = certificateInfo;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getDigestValue() {
        return digestValue;
    }

    public void setDigestValue(String digestValue) {
        this.digestValue = digestValue;
    }

    public String getPolicyIdentifier() {
        return policyIdentifier;
    }

    public void setPolicyIdentifier(String policyIdentifier) {
        this.policyIdentifier = policyIdentifier;
    }

    public List<SignatureCheck> getValidChecks() {
        return validChecks;
    }

    public void setValidChecks(List<SignatureCheck> validChecks) {
        this.validChecks = validChecks;
    }

    public List<SignatureCheck> getInvalidChecks() {
        return invalidChecks;
    }

    public void setInvalidChecks(List<SignatureCheck> invalidChecks) {
        this.invalidChecks = invalidChecks;
    }

    public List<SignatureCheck> getIndeterminateChecks() {
        return indeterminateChecks;
    }

    public void setIndeterminateChecks(List<SignatureCheck> indeterminateChecks) {
        this.indeterminateChecks = indeterminateChecks;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public byte[][] getCertificateChain() {
        return certificateChain;
    }

    public void setCertificateChain(byte[][] certificateChain) {
        this.certificateChain = certificateChain;
    }

    public TimeStampInfo getTimeStampInfo() {
        return timeStampInfo;
    }

    public void setTimeStampInfo(TimeStampInfo timeStampInfo) {
        this.timeStampInfo = timeStampInfo;
    }



}
