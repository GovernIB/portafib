
package org.fundaciobit.pluginsib.signatureweb.fnmtcloud.json;

import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
    "identifier",
    "description",
    "documentation_references"
})
public class XadesPolicyId {

    @JsonProperty("identifier")
    private XadesIdentifier identifier;
    @JsonProperty("description")
    private String description;
    @JsonProperty("documentation_references")
    private List<XadesDocumentationReference> documentationReferences = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public XadesPolicyId() {
    }

    /**
     * 
     * @param description
     * @param identifier
     * @param documentationReferences
     */
    public XadesPolicyId(XadesIdentifier identifier, String description, List<XadesDocumentationReference> documentationReferences) {
        super();
        this.identifier = identifier;
        this.description = description;
        this.documentationReferences = documentationReferences;
    }

    @JsonProperty("identifier")
    public XadesIdentifier getIdentifier() {
        return identifier;
    }

    @JsonProperty("identifier")
    public void setIdentifier(XadesIdentifier identifier) {
        this.identifier = identifier;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("documentation_references")
    public List<XadesDocumentationReference> getDocumentationReferences() {
        return documentationReferences;
    }

    @JsonProperty("documentation_references")
    public void setDocumentationReferences(List<XadesDocumentationReference> documentationReferences) {
        this.documentationReferences = documentationReferences;
    }

}
