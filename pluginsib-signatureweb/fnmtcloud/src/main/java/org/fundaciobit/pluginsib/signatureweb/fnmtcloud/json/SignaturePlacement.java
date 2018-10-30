
package org.fundaciobit.pluginsib.signatureweb.fnmtcloud.json;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
    "type",
    "xpath"
})
public class SignaturePlacement {

    @JsonProperty("type")
    private String type;
    @JsonProperty("xpath")
    private String xpath;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SignaturePlacement() {
    }

    /**
     * 
     * @param type
     * @param xpath
     */
    public SignaturePlacement(String type, String xpath) {
        super();
        this.type = type;
        this.xpath = xpath;
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

}
