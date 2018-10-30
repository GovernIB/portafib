
package org.fundaciobit.pluginsib.signatureweb.fnmtcloud.json;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
    "type",
    "oid"
})
public class Commitment {

    @JsonProperty("type")
    private String type;
    @JsonProperty("oid")
    private String oid;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Commitment() {
    }

    /**
     * 
     * @param oid
     * @param type
     */
    public Commitment(String type, String oid) {
        super();
        this.type = type;
        this.oid = oid;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
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
