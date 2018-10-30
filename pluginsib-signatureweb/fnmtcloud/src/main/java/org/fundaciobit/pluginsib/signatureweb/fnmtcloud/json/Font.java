
package org.fundaciobit.pluginsib.signatureweb.fnmtcloud.json;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
    "name",
    "size",
    "style",
    "color",
    "encoding",
    "embed"
})
public class Font {

    @JsonProperty("name")
    private String name;
    @JsonProperty("size")
    private Integer size;
    @JsonProperty("style")
    private String style;
    @JsonProperty("color")
    private Color color;
    @JsonProperty("encoding")
    private String encoding;
    @JsonProperty("embed")
    private Boolean embed;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Font() {
    }

    /**
     * 
     * @param style
     * @param color
     * @param name
     * @param encoding
     * @param embed
     * @param size
     */
    public Font(String name, Integer size, String style, Color color, String encoding, Boolean embed) {
        super();
        this.name = name;
        this.size = size;
        this.style = style;
        this.color = color;
        this.encoding = encoding;
        this.embed = embed;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("size")
    public Integer getSize() {
        return size;
    }

    @JsonProperty("size")
    public void setSize(Integer size) {
        this.size = size;
    }

    @JsonProperty("style")
    public String getStyle() {
        return style;
    }

    @JsonProperty("style")
    public void setStyle(String style) {
        this.style = style;
    }

    @JsonProperty("color")
    public Color getColor() {
        return color;
    }

    @JsonProperty("color")
    public void setColor(Color color) {
        this.color = color;
    }

    @JsonProperty("encoding")
    public String getEncoding() {
        return encoding;
    }

    @JsonProperty("encoding")
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    @JsonProperty("embed")
    public Boolean getEmbed() {
        return embed;
    }

    @JsonProperty("embed")
    public void setEmbed(Boolean embed) {
        this.embed = embed;
    }

}
