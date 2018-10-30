
package org.fundaciobit.pluginsib.signatureweb.fnmtcloud.json;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
    "page",
    "rectangle"
})
public class Location {

    @JsonProperty("page")
    private Page page;
    @JsonProperty("rectangle")
    private Rectangle rectangle;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Location() {
    }

    /**
     * 
     * @param rectangle
     * @param page
     */
    public Location(Page page, Rectangle rectangle) {
        super();
        this.page = page;
        this.rectangle = rectangle;
    }

    @JsonProperty("page")
    public Page getPage() {
        return page;
    }

    @JsonProperty("page")
    public void setPage(Page page) {
        this.page = page;
    }

    @JsonProperty("rectangle")
    public Rectangle getRectangle() {
        return rectangle;
    }

    @JsonProperty("rectangle")
    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

}
