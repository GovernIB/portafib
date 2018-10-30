
package org.fundaciobit.pluginsib.signatureweb.fnmtcloud.json;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
    "digest"
})
public class ToBeSigned {

    @JsonProperty("digest")
    private Digest digest;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ToBeSigned() {
    }

    /**
     * 
     * @param digest
     */
    public ToBeSigned(Digest digest) {
        super();
        this.digest = digest;
    }

    @JsonProperty("digest")
    public Digest getDigest() {
        return digest;
    }

    @JsonProperty("digest")
    public void setDigest(Digest digest) {
        this.digest = digest;
    }

}
