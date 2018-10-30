
package org.fundaciobit.pluginsib.signatureweb.fnmtcloud.json;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
    "html_body_content"
})
public class DocumentInfo {

    @JsonProperty("html_body_content")
    private String htmlBodyContent;

    /**
     * No args constructor for use in serialization
     * 
     */
    public DocumentInfo() {
    }

    /**
     * 
     * @param htmlBodyContent
     */
    public DocumentInfo(String htmlBodyContent) {
        super();
        this.htmlBodyContent = htmlBodyContent;
    }

    @JsonProperty("html_body_content")
    public String getHtmlBodyContent() {
        return htmlBodyContent;
    }

    @JsonProperty("html_body_content")
    public void setHtmlBodyContent(String htmlBodyContent) {
        this.htmlBodyContent = htmlBodyContent;
    }

}
