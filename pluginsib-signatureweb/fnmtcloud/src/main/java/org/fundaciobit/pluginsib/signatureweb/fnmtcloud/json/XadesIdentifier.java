
package org.fundaciobit.pluginsib.signatureweb.fnmtcloud.json;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
    "uri",
    "qualifier"
})
public class XadesIdentifier {

    @JsonProperty("uri")
    private String uri;
    @JsonProperty("qualifier")
    private String qualifier;

    /**
     * No args constructor for use in serialization
     * 
     */
    public XadesIdentifier() {
    }

    /**
     * 
     * @param qualifier
     * @param uri
     */
    public XadesIdentifier(String uri, String qualifier) {
        super();
        this.uri = uri;
        this.qualifier = qualifier;
    }

    @JsonProperty("uri")
    public String getUri() {
        return uri;
    }

    @JsonProperty("uri")
    public void setUri(String uri) {
        this.uri = uri;
    }

    @JsonProperty("qualifier")
    public String getQualifier() {
        return qualifier;
    }

    @JsonProperty("qualifier")
    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

}
