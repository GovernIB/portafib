
package org.fundaciobit.pluginsib.signatureweb.fnmtcloud.json;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
    "type",
    "certificate",
    "default_digest_algorithm",
    "signature_target",
    "include_data_object_format",
    "default_c14n_method",
    "policy_identifier"
})
public class XadesEnvelopedParameters implements IParameters {

    @JsonProperty("type")
    private String type;
    @JsonProperty("certificate")
    private String certificate;
    @JsonProperty("default_digest_algorithm")
    private String defaultDigestAlgorithm;
    @JsonProperty("signature_target")
    private SignatureTarget signatureTarget;
    @JsonProperty("include_data_object_format")
    private Boolean includeDataObjectFormat;
    @JsonProperty("default_c14n_method")
    private String defaultC14nMethod;
    @JsonProperty("policy_identifier")
    private XadesPolicyIdentifier policyIdentifier;

    /**
     * No args constructor for use in serialization
     * 
     */
    public XadesEnvelopedParameters() {
    }

    /**
     * 
     * @param certificate
     * @param defaultC14nMethod
     * @param signatureTarget
     * @param includeDataObjectFormat
     * @param policyIdentifier
     * @param defaultDigestAlgorithm
     * @param type
     */
    public XadesEnvelopedParameters(String type, String certificate, String defaultDigestAlgorithm, SignatureTarget signatureTarget, Boolean includeDataObjectFormat, String defaultC14nMethod, XadesPolicyIdentifier policyIdentifier) {
        super();
        this.type = type;
        this.certificate = certificate;
        this.defaultDigestAlgorithm = defaultDigestAlgorithm;
        this.signatureTarget = signatureTarget;
        this.includeDataObjectFormat = includeDataObjectFormat;
        this.defaultC14nMethod = defaultC14nMethod;
        this.policyIdentifier = policyIdentifier;
    }

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

    @JsonProperty("signature_target")
    public SignatureTarget getSignatureTarget() {
        return signatureTarget;
    }

    @JsonProperty("signature_target")
    public void setSignatureTarget(SignatureTarget signatureTarget) {
        this.signatureTarget = signatureTarget;
    }

    @JsonProperty("include_data_object_format")
    public Boolean getIncludeDataObjectFormat() {
        return includeDataObjectFormat;
    }

    @JsonProperty("include_data_object_format")
    public void setIncludeDataObjectFormat(Boolean includeDataObjectFormat) {
        this.includeDataObjectFormat = includeDataObjectFormat;
    }

    @JsonProperty("default_c14n_method")
    public String getDefaultC14nMethod() {
        return defaultC14nMethod;
    }

    @JsonProperty("default_c14n_method")
    public void setDefaultC14nMethod(String defaultC14nMethod) {
        this.defaultC14nMethod = defaultC14nMethod;
    }

    @JsonProperty("policy_identifier")
    public XadesPolicyIdentifier getPolicyIdentifier() {
        return policyIdentifier;
    }

    @JsonProperty("policy_identifier")
    public void setPolicyIdentifier(XadesPolicyIdentifier policyIdentifier) {
        this.policyIdentifier = policyIdentifier;
    }

}
