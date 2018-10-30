
package org.fundaciobit.pluginsib.signatureweb.fnmtcloud.json;

import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
    "policy_id",
    "policy_hash",
    "policy_qualifiers"
})
public class XadesPolicyIdentifier {

    @JsonProperty("policy_id")
    private XadesPolicyId policyId;
    @JsonProperty("policy_hash")
    private XadesPolicyHash policyHash;
    @JsonProperty("policy_qualifiers")
    private List<XadesPolicyQualifier> policyQualifiers = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public XadesPolicyIdentifier() {
    }

    /**
     * 
     * @param policyId
     * @param policyHash
     * @param policyQualifiers
     */
    public XadesPolicyIdentifier(XadesPolicyId policyId, XadesPolicyHash policyHash, List<XadesPolicyQualifier> policyQualifiers) {
        super();
        this.policyId = policyId;
        this.policyHash = policyHash;
        this.policyQualifiers = policyQualifiers;
    }

    @JsonProperty("policy_id")
    public XadesPolicyId getPolicyId() {
        return policyId;
    }

    @JsonProperty("policy_id")
    public void setPolicyId(XadesPolicyId policyId) {
        this.policyId = policyId;
    }

    @JsonProperty("policy_hash")
    public XadesPolicyHash getPolicyHash() {
        return policyHash;
    }

    @JsonProperty("policy_hash")
    public void setPolicyHash(XadesPolicyHash policyHash) {
        this.policyHash = policyHash;
    }

    @JsonProperty("policy_qualifiers")
    public List<XadesPolicyQualifier> getPolicyQualifiers() {
        return policyQualifiers;
    }

    @JsonProperty("policy_qualifiers")
    public void setPolicyQualifiers(List<XadesPolicyQualifier> policyQualifiers) {
        this.policyQualifiers = policyQualifiers;
    }

}
