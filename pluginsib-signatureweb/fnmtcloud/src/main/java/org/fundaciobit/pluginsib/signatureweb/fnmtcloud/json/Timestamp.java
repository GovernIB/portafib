
package org.fundaciobit.pluginsib.signatureweb.fnmtcloud.json;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
    "provider_id"
})
public class Timestamp {

    @JsonProperty("provider_id")
    private String providerId;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Timestamp() {
    }

    /**
     * 
     * @param providerId
     */
    public Timestamp(String providerId) {
        super();
        this.providerId = providerId;
    }

    @JsonProperty("provider_id")
    public String getProviderId() {
        return providerId;
    }

    @JsonProperty("provider_id")
    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

}
