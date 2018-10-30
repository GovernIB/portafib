
package org.fundaciobit.pluginsib.signatureweb.fnmtcloud.json;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
    "oid"
})
public class PadesPolicyId {

    @JsonProperty("oid")
    private String oid;

    /**
     * No args constructor for use in serialization
     * 
     */
    public PadesPolicyId() {
    }

    /**
     * 
     * @param oid
     */
    public PadesPolicyId(String oid) {
        super();
        this.oid = oid;
    }

    @JsonProperty("oid")
    public String getOid() {
        return oid;
    }

    @JsonProperty("oid")
    public void setOid(String oid) {
        this.oid = oid;
    }

}
