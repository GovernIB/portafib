
package org.fundaciobit.pluginsib.signatureweb.fnmtcloud.json;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
    "type",
    "xpath",
    "uri_type"
})
public class NodesToSign {

    @JsonProperty("type")
    private String type;
    @JsonProperty("xpath")
    private String xpath;
    @JsonProperty("uri_type")
    private String uriType;

    /**
     * No args constructor for use in serialization
     * 
     */
    public NodesToSign() {
    }

    /**
     * 
     * @param uriType
     * @param type
     * @param xpath
     */
    public NodesToSign(String type, String xpath, String uriType) {
        super();
        this.type = type;
        this.xpath = xpath;
        this.uriType = uriType;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("xpath")
    public String getXpath() {
        return xpath;
    }

    @JsonProperty("xpath")
    public void setXpath(String xpath) {
        this.xpath = xpath;
    }

    @JsonProperty("uri_type")
    public String getUriType() {
        return uriType;
    }

    @JsonProperty("uri_type")
    public void setUriType(String uriType) {
        this.uriType = uriType;
    }

}
