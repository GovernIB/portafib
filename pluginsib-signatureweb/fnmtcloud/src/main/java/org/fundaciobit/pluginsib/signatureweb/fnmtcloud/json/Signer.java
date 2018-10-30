
package org.fundaciobit.pluginsib.signatureweb.fnmtcloud.json;

import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
    "id",
    "self",
    "signature_policy_id",
    "parameters",
    "to_be_signed",
    "signature",
    "to_be_timestamped",
    "timestamps"
})
public class Signer {

    @JsonProperty("id")
    private String id;
    @JsonProperty("self")
    private String self;
    @JsonProperty("signature_policy_id")
    private String signaturePolicyId;
    @JsonProperty("parameters")
    private IParameters parameters;
    @JsonProperty("to_be_signed")
    private ToBeSigned toBeSigned;
    @JsonProperty("signature")
    private String signature;
    @JsonProperty("to_be_timestamped")
    private ToBeTimestamped toBeTimestamped;
    @JsonProperty("timestamps")
    private List<String> timestamps = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Signer() {
    }

    /**
     * 
     * @param id
     * @param timestamps
     * @param toBeSigned
     * @param signaturePolicyId
     * @param self
     * @param toBeTimestamped
     * @param parameters
     * @param signature
     */
    public Signer(String id, String self, String signaturePolicyId, IParameters parameters, ToBeSigned toBeSigned, String signature, ToBeTimestamped toBeTimestamped, List<String> timestamps) {
        super();
        this.id = id;
        this.self = self;
        this.signaturePolicyId = signaturePolicyId;
        this.parameters = parameters;
        this.toBeSigned = toBeSigned;
        this.signature = signature;
        this.toBeTimestamped = toBeTimestamped;
        this.timestamps = timestamps;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("self")
    public String getSelf() {
        return self;
    }

    @JsonProperty("self")
    public void setSelf(String self) {
        this.self = self;
    }

    @JsonProperty("signature_policy_id")
    public String getSignaturePolicyId() {
        return signaturePolicyId;
    }

    @JsonProperty("signature_policy_id")
    public void setSignaturePolicyId(String signaturePolicyId) {
        this.signaturePolicyId = signaturePolicyId;
    }

    @JsonProperty("parameters")
    public IParameters getParameters() {
        return parameters;
    }

    @JsonProperty("parameters")
    public void setParameters(IParameters parameters) {
        this.parameters = parameters;
    }

    @JsonProperty("to_be_signed")
    public ToBeSigned getToBeSigned() {
        return toBeSigned;
    }

    @JsonProperty("to_be_signed")
    public void setToBeSigned(ToBeSigned toBeSigned) {
        this.toBeSigned = toBeSigned;
    }

    @JsonProperty("signature")
    public String getSignature() {
        return signature;
    }

    @JsonProperty("signature")
    public void setSignature(String signature) {
        this.signature = signature;
    }

    @JsonProperty("to_be_timestamped")
    public ToBeTimestamped getToBeTimestamped() {
        return toBeTimestamped;
    }

    @JsonProperty("to_be_timestamped")
    public void setToBeTimestamped(ToBeTimestamped toBeTimestamped) {
        this.toBeTimestamped = toBeTimestamped;
    }

    @JsonProperty("timestamps")
    public List<String> getTimestamps() {
        return timestamps;
    }

    @JsonProperty("timestamps")
    public void setTimestamps(List<String> timestamps) {
        this.timestamps = timestamps;
    }

}
