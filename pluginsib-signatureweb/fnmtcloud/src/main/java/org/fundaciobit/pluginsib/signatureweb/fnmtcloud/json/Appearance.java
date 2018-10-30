
package org.fundaciobit.pluginsib.signatureweb.fnmtcloud.json;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
    "background_image",
    "foreground_image",
    "signature_details"
})
public class Appearance {

    @JsonProperty("background_image")
    private BackgroundImage backgroundImage;
    @JsonProperty("foreground_image")
    private ForegroundImage foregroundImage;
    @JsonProperty("signature_details")
    private SignatureDetails signatureDetails;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Appearance() {
    }

    /**
     * 
     * @param foregroundImage
     * @param signatureDetails
     * @param backgroundImage
     */
    public Appearance(BackgroundImage backgroundImage, ForegroundImage foregroundImage, SignatureDetails signatureDetails) {
        super();
        this.backgroundImage = backgroundImage;
        this.foregroundImage = foregroundImage;
        this.signatureDetails = signatureDetails;
    }

    @JsonProperty("background_image")
    public BackgroundImage getBackgroundImage() {
        return backgroundImage;
    }

    @JsonProperty("background_image")
    public void setBackgroundImage(BackgroundImage backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @JsonProperty("foreground_image")
    public ForegroundImage getForegroundImage() {
        return foregroundImage;
    }

    @JsonProperty("foreground_image")
    public void setForegroundImage(ForegroundImage foregroundImage) {
        this.foregroundImage = foregroundImage;
    }

    @JsonProperty("signature_details")
    public SignatureDetails getSignatureDetails() {
        return signatureDetails;
    }

    @JsonProperty("signature_details")
    public void setSignatureDetails(SignatureDetails signatureDetails) {
        this.signatureDetails = signatureDetails;
    }

}
