package es.caib.portafib.api.interna.secure.firma.v1.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author anadal
 * 18 dic 2024 11:01:59
 */
public class AvailableProfilesRest {

    public List<AvailableProfile> data;

    public String language;

    public AvailableProfilesRest() {
        this.data = new ArrayList<AvailableProfile>();
    }

    public AvailableProfilesRest(List<AvailableProfile> data, String language) {
        this.data = data;
        this.language = language;
    }

    public List<AvailableProfile> getData() {
        return data;
    }

    public void setData(List<AvailableProfile> data) {
        this.data = data;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
