
package org.fundaciobit.pluginsib.signatureweb.fnmtcloud.json;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
    "digest_algorithm_identifier",
    "digest_value"
})
public class PadesPolicyHash {

    @JsonProperty("digest_algorithm_identifier")
    private DigestAlgorithmIdentifier digestAlgorithmIdentifier;
    @JsonProperty("digest_value")
    private String digestValue;

    /**
     * No args constructor for use in serialization
     * 
     */
    public PadesPolicyHash() {
    }

    /**
     * 
     * @param digestValue
     * @param digestAlgorithmIdentifier
     */
    public PadesPolicyHash(DigestAlgorithmIdentifier digestAlgorithmIdentifier, String digestValue) {
        super();
        this.digestAlgorithmIdentifier = digestAlgorithmIdentifier;
        this.digestValue = digestValue;
    }

    @JsonProperty("digest_algorithm_identifier")
    public DigestAlgorithmIdentifier getDigestAlgorithmIdentifier() {
        return digestAlgorithmIdentifier;
    }

    @JsonProperty("digest_algorithm_identifier")
    public void setDigestAlgorithmIdentifier(DigestAlgorithmIdentifier digestAlgorithmIdentifier) {
        this.digestAlgorithmIdentifier = digestAlgorithmIdentifier;
    }

    @JsonProperty("digest_value")
    public String getDigestValue() {
        return digestValue;
    }

    @JsonProperty("digest_value")
    public void setDigestValue(String digestValue) {
        this.digestValue = digestValue;
    }

}
