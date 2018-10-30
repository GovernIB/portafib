
package org.fundaciobit.pluginsib.signatureweb.fnmtcloud.json;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
    "r",
    "g",
    "b"
})
public class Color {

    @JsonProperty("r")
    private Integer r;
    @JsonProperty("g")
    private Integer g;
    @JsonProperty("b")
    private Integer b;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Color() {
    }

    /**
     * 
     * @param g
     * @param b
     * @param r
     */
    public Color(Integer r, Integer g, Integer b) {
        super();
        this.r = r;
        this.g = g;
        this.b = b;
    }

    @JsonProperty("r")
    public Integer getR() {
        return r;
    }

    @JsonProperty("r")
    public void setR(Integer r) {
        this.r = r;
    }

    @JsonProperty("g")
    public Integer getG() {
        return g;
    }

    @JsonProperty("g")
    public void setG(Integer g) {
        this.g = g;
    }

    @JsonProperty("b")
    public Integer getB() {
        return b;
    }

    @JsonProperty("b")
    public void setB(Integer b) {
        this.b = b;
    }

}
