
package org.fundaciobit.pluginsib.signatureweb.fnmtcloud.json;

import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
    "process_type",
    "signer",
    "labels",
    "ui_locales",
    "finish_callback_url",
    "views",
    "timestamp"
})
public class SignatureRequest {

    @JsonProperty("process_type")
    private String processType;
    @JsonProperty("signer")
    private Signer signer;
    @JsonProperty("labels")
    private List<List<String>> labels = null;
    @JsonProperty("ui_locales")
    private List<String> uiLocales = null;
    @JsonProperty("finish_callback_url")
    private String finishCallbackUrl;
    @JsonProperty("views")
    private Views views;
    @JsonProperty("timestamp")
    private Timestamp timestamp;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SignatureRequest() {
    }

    /**
     * 
     * @param timestamp
     * @param processType
     * @param signer
     * @param views
     * @param finishCallbackUrl
     * @param labels
     * @param uiLocales
     */
    public SignatureRequest(String processType, Signer signer, List<List<String>> labels, List<String> uiLocales, String finishCallbackUrl, Views views, Timestamp timestamp) {
        super();
        this.processType = processType;
        this.signer = signer;
        this.labels = labels;
        this.uiLocales = uiLocales;
        this.finishCallbackUrl = finishCallbackUrl;
        this.views = views;
        this.timestamp = timestamp;
    }

    @JsonProperty("process_type")
    public String getProcessType() {
        return processType;
    }

    @JsonProperty("process_type")
    public void setProcessType(String processType) {
        this.processType = processType;
    }

    @JsonProperty("signer")
    public Signer getSigner() {
        return signer;
    }

    @JsonProperty("signer")
    public void setSigner(Signer signer) {
        this.signer = signer;
    }

    @JsonProperty("labels")
    public List<List<String>> getLabels() {
        return labels;
    }

    @JsonProperty("labels")
    public void setLabels(List<List<String>> labels) {
        this.labels = labels;
    }

    @JsonProperty("ui_locales")
    public List<String> getUiLocales() {
        return uiLocales;
    }

    @JsonProperty("ui_locales")
    public void setUiLocales(List<String> uiLocales) {
        this.uiLocales = uiLocales;
    }

    @JsonProperty("finish_callback_url")
    public String getFinishCallbackUrl() {
        return finishCallbackUrl;
    }

    @JsonProperty("finish_callback_url")
    public void setFinishCallbackUrl(String finishCallbackUrl) {
        this.finishCallbackUrl = finishCallbackUrl;
    }

    @JsonProperty("views")
    public Views getViews() {
        return views;
    }

    @JsonProperty("views")
    public void setViews(Views views) {
        this.views = views;
    }

    @JsonProperty("timestamp")
    public Timestamp getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

}
