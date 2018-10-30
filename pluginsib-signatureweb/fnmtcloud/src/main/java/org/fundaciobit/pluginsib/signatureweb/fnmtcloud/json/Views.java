
package org.fundaciobit.pluginsib.signatureweb.fnmtcloud.json;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
    "document_agreement"
})
public class Views {

    @JsonProperty("document_agreement")
    private DocumentAgreement documentAgreement;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Views() {
    }

    /**
     * 
     * @param documentAgreement
     */
    public Views(DocumentAgreement documentAgreement) {
        super();
        this.documentAgreement = documentAgreement;
    }

    @JsonProperty("document_agreement")
    public DocumentAgreement getDocumentAgreement() {
        return documentAgreement;
    }

    @JsonProperty("document_agreement")
    public void setDocumentAgreement(DocumentAgreement documentAgreement) {
        this.documentAgreement = documentAgreement;
    }

}
