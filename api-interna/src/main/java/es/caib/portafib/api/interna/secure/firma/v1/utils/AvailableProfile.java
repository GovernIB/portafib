package es.caib.portafib.api.interna.secure.firma.v1.utils;

import java.util.List;

import es.caib.portafib.api.interna.secure.firma.v1.commons.KeyValue;

/**
 * 
 * @author anadal
 * 18 dic 2024 11:28:34
 */
public class AvailableProfile {

    protected String code;

    protected String name;

    protected String description;

    protected List<KeyValue> properties;

    public AvailableProfile() {
        super();
    }

    public AvailableProfile(String code, String name, String description, List<KeyValue> properties) {
        super();
        this.code = code;
        this.name = name;
        this.description = description;
        this.properties = properties;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
