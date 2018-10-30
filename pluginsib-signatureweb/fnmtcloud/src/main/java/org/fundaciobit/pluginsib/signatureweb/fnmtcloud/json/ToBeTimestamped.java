
package org.fundaciobit.pluginsib.signatureweb.fnmtcloud.json;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
    "type",
    "digest_algorithm",
    "digest_value"
})
public class ToBeTimestamped {

    @JsonProperty("type")
    private String type;
    @JsonProperty("digest_algorithm")
    private String digestAlgorithm;
    @JsonProperty("digest_value")
    private String digestValue;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ToBeTimestamped() {
    }

    /**
     * 
     * @param digestValue
     * @param type
     * @param digestAlgorithm
     */
    public ToBeTimestamped(String type, String digestAlgorithm, String digestValue) {
        super();
        this.type = type;
        this.digestAlgorithm = digestAlgorithm;
        this.digestValue = digestValue;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("digest_algorithm")
    public String getDigestAlgorithm() {
        return digestAlgorithm;
    }

    @JsonProperty("digest_algorithm")
    public void setDigestAlgorithm(String digestAlgorithm) {
        this.digestAlgorithm = digestAlgorithm;
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
