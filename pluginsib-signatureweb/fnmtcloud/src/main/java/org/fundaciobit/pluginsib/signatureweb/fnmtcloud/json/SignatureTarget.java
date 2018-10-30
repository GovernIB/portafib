
package org.fundaciobit.pluginsib.signatureweb.fnmtcloud.json;

import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
    "type",
    "signature_packaging",
    "nodes_to_sign",
    "signature_placement"
})
public class SignatureTarget {

    @JsonProperty("type")
    private String type;
    @JsonProperty("signature_packaging")
    private String signaturePackaging;
    @JsonProperty("nodes_to_sign")
    private List<NodesToSign> nodesToSign = null;
    @JsonProperty("signature_placement")
    private SignaturePlacement signaturePlacement;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SignatureTarget() {
    }

    /**
     * 
     * @param signaturePackaging
     * @param signaturePlacement
     * @param type
     * @param nodesToSign
     */
    public SignatureTarget(String type, String signaturePackaging, List<NodesToSign> nodesToSign, SignaturePlacement signaturePlacement) {
        super();
        this.type = type;
        this.signaturePackaging = signaturePackaging;
        this.nodesToSign = nodesToSign;
        this.signaturePlacement = signaturePlacement;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("signature_packaging")
    public String getSignaturePackaging() {
        return signaturePackaging;
    }

    @JsonProperty("signature_packaging")
    public void setSignaturePackaging(String signaturePackaging) {
        this.signaturePackaging = signaturePackaging;
    }

    @JsonProperty("nodes_to_sign")
    public List<NodesToSign> getNodesToSign() {
        return nodesToSign;
    }

    @JsonProperty("nodes_to_sign")
    public void setNodesToSign(List<NodesToSign> nodesToSign) {
        this.nodesToSign = nodesToSign;
    }

    @JsonProperty("signature_placement")
    public SignaturePlacement getSignaturePlacement() {
        return signaturePlacement;
    }

    @JsonProperty("signature_placement")
    public void setSignaturePlacement(SignaturePlacement signaturePlacement) {
        this.signaturePlacement = signaturePlacement;
    }

}
