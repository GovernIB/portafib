
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
public class PadesPolicyIdentifier {

    @JsonProperty("policy_id")
    private PadesPolicyId policyId;
    @JsonProperty("policy_hash")
    private PadesPolicyHash policyHash;
    @JsonProperty("policy_qualifiers")
    private List<PadesPolicyQualifier> policyQualifiers = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public PadesPolicyIdentifier() {
    }

    /**
     * 
     * @param policyId
     * @param policyHash
     * @param policyQualifiers
     */
    public PadesPolicyIdentifier(PadesPolicyId policyId, PadesPolicyHash policyHash, List<PadesPolicyQualifier> policyQualifiers) {
        super();
        this.policyId = policyId;
        this.policyHash = policyHash;
        this.policyQualifiers = policyQualifiers;
    }

    @JsonProperty("policy_id")
    public PadesPolicyId getPolicyId() {
        return policyId;
    }

    @JsonProperty("policy_id")
    public void setPolicyId(PadesPolicyId policyId) {
        this.policyId = policyId;
    }

    @JsonProperty("policy_hash")
    public PadesPolicyHash getPolicyHash() {
        return policyHash;
    }

    @JsonProperty("policy_hash")
    public void setPolicyHash(PadesPolicyHash policyHash) {
        this.policyHash = policyHash;
    }

    @JsonProperty("policy_qualifiers")
    public List<PadesPolicyQualifier> getPolicyQualifiers() {
        return policyQualifiers;
    }

    @JsonProperty("policy_qualifiers")
    public void setPolicyQualifiers(List<PadesPolicyQualifier> policyQualifiers) {
        this.policyQualifiers = policyQualifiers;
    }

}
