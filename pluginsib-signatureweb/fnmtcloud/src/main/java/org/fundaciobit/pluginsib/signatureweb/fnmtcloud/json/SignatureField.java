
package org.fundaciobit.pluginsib.signatureweb.fnmtcloud.json;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
    "name",
    "location",
    "appearance"
})
public class SignatureField {

    @JsonProperty("name")
    private String name;
    @JsonProperty("location")
    private Location location;
    @JsonProperty("appearance")
    private Appearance appearance;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SignatureField() {
    }

    /**
     * 
     * @param appearance
     * @param location
     * @param name
     */
    public SignatureField(String name, Location location, Appearance appearance) {
        super();
        this.name = name;
        this.location = location;
        this.appearance = appearance;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("location")
    public Location getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(Location location) {
        this.location = location;
    }

    @JsonProperty("appearance")
    public Appearance getAppearance() {
        return appearance;
    }

    @JsonProperty("appearance")
    public void setAppearance(Appearance appearance) {
        this.appearance = appearance;
    }

}
