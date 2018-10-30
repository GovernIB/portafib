
package org.fundaciobit.pluginsib.signatureweb.fnmtcloud.json;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * 
 * @author anadal
 *
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
    "type",
    "certificate",
    "default_digest_algorithm",
    "estimated_signature_size",
    "certification_level",
    "contact_info",
    "location",
    "reason",
    "signature_field",
    "policy_identifier",
    "commitments"
})
public class PadesParameters implements IParameters {

    @JsonProperty("type")
    private String type;
    @JsonProperty("certificate")
    private String certificate;
    @JsonProperty("default_digest_algorithm")
    private String defaultDigestAlgorithm;
    @JsonProperty("estimated_signature_size")
    private Integer estimatedSignatureSize;
    @JsonProperty("certification_level")
    private Integer certificationLevel;
    @JsonProperty("contact_info")
    private String contactInfo;
    @JsonProperty("location")
    private String location;
    @JsonProperty("reason")
    private String reason;
    @JsonProperty("signature_field")
    private SignatureField signatureField;
    @JsonProperty("policy_identifier")
    private PadesPolicyIdentifier policyIdentifier;
    @JsonProperty("commitments")
    private List<Commitment> commitments = null;

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("certificate")
    public String getCertificate() {
        return certificate;
    }

    @JsonProperty("certificate")
    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    @JsonProperty("default_digest_algorithm")
    public String getDefaultDigestAlgorithm() {
        return defaultDigestAlgorithm;
    }

    @JsonProperty("default_digest_algorithm")
    public void setDefaultDigestAlgorithm(String defaultDigestAlgorithm) {
        this.defaultDigestAlgorithm = defaultDigestAlgorithm;
    }

    @JsonProperty("estimated_signature_size")
    public Integer getEstimatedSignatureSize() {
        return estimatedSignatureSize;
    }

    @JsonProperty("estimated_signature_size")
    public void setEstimatedSignatureSize(Integer estimatedSignatureSize) {
        this.estimatedSignatureSize = estimatedSignatureSize;
    }

    @JsonProperty("certification_level")
    public Integer getCertificationLevel() {
        return certificationLevel;
    }

    @JsonProperty("certification_level")
    public void setCertificationLevel(Integer certificationLevel) {
        this.certificationLevel = certificationLevel;
    }

    @JsonProperty("contact_info")
    public String getContactInfo() {
        return contactInfo;
    }

    @JsonProperty("contact_info")
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    @JsonProperty("location")
    public String getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(String location) {
        this.location = location;
    }

    @JsonProperty("reason")
    public String getReason() {
        return reason;
    }

    @JsonProperty("reason")
    public void setReason(String reason) {
        this.reason = reason;
    }

    @JsonProperty("signature_field")
    public SignatureField getSignatureField() {
        return signatureField;
    }

    @JsonProperty("signature_field")
    public void setSignatureField(SignatureField signatureField) {
        this.signatureField = signatureField;
    }

    @JsonProperty("policy_identifier")
    public PadesPolicyIdentifier getPolicyIdentifier() {
        return policyIdentifier;
    }

    @JsonProperty("policy_identifier")
    public void setPolicyIdentifier(PadesPolicyIdentifier policyIdentifier) {
        this.policyIdentifier = policyIdentifier;
    }

    @JsonProperty("commitments")
    public List<Commitment> getCommitments() {
        return commitments;
    }

    @JsonProperty("commitments")
    public void setCommitments(List<Commitment> commitments) {
        this.commitments = commitments;
    }


}
