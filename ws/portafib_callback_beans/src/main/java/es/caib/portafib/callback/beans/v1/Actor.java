package es.caib.portafib.callback.beans.v1;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author anadal
 * 
 */
public class Actor {

    @JsonProperty("ID")
    private String iD;

    private String name;

    private String administrationID;

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdministrationID() {
        return administrationID;
    }

    public void setAdministrationID(String administrationID) {
        this.administrationID = administrationID;
    }

}
