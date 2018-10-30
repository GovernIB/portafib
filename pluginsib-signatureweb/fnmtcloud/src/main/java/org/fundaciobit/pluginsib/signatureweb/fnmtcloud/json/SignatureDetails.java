
package org.fundaciobit.pluginsib.signatureweb.fnmtcloud.json;

import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
    "font",
    "details"
})
public class SignatureDetails {

    @JsonProperty("font")
    private Font font;
    @JsonProperty("details")
    private List<Detail> details = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SignatureDetails() {
    }

    /**
     * 
     * @param details
     * @param font
     */
    public SignatureDetails(Font font, List<Detail> details) {
        super();
        this.font = font;
        this.details = details;
    }

    @JsonProperty("font")
    public Font getFont() {
        return font;
    }

    @JsonProperty("font")
    public void setFont(Font font) {
        this.font = font;
    }

    @JsonProperty("details")
    public List<Detail> getDetails() {
        return details;
    }

    @JsonProperty("details")
    public void setDetails(List<Detail> details) {
        this.details = details;
    }

}
