
package org.fundaciobit.pluginsib.signatureweb.fnmtcloud.json;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
    "skip_server_id",
    "document_info"
})
public class DocumentAgreement {

    @JsonProperty("skip_server_id")
    private Boolean skipServerId;
    @JsonProperty("document_info")
    private DocumentInfo documentInfo;

    /**
     * No args constructor for use in serialization
     * 
     */
    public DocumentAgreement() {
    }

    /**
     * 
     * @param skipServerId
     * @param documentInfo
     */
    public DocumentAgreement(Boolean skipServerId, DocumentInfo documentInfo) {
        super();
        this.skipServerId = skipServerId;
        this.documentInfo = documentInfo;
    }

    @JsonProperty("skip_server_id")
    public Boolean getSkipServerId() {
        return skipServerId;
    }

    @JsonProperty("skip_server_id")
    public void setSkipServerId(Boolean skipServerId) {
        this.skipServerId = skipServerId;
    }

    @JsonProperty("document_info")
    public DocumentInfo getDocumentInfo() {
        return documentInfo;
    }

    @JsonProperty("document_info")
    public void setDocumentInfo(DocumentInfo documentInfo) {
        this.documentInfo = documentInfo;
    }

}
