
package org.fundaciobit.pluginsib.signatureweb.fnmtcloud.json;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
    "file",
    "binary"
})
public class ForegroundImage {

    @JsonProperty("file")
    private String file;
    @JsonProperty("binary")
    private String binary;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ForegroundImage() {
    }

    /**
     * 
     * @param binary
     * @param file
     */
    public ForegroundImage(String file, String binary) {
        super();
        this.file = file;
        this.binary = binary;
    }

    @JsonProperty("file")
    public String getFile() {
        return file;
    }

    @JsonProperty("file")
    public void setFile(String file) {
        this.file = file;
    }

    @JsonProperty("binary")
    public String getBinary() {
        return binary;
    }

    @JsonProperty("binary")
    public void setBinary(String binary) {
        this.binary = binary;
    }

}
