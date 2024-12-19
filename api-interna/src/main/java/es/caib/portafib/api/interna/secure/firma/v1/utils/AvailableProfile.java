package es.caib.portafib.api.interna.secure.firma.v1.utils;

import java.util.List;

import es.caib.portafib.api.interna.secure.firma.v1.commons.KeyValue;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal
 * 18 dic 2024 11:28:34
 */
@Schema(description = "Detalls del Perfil d'un usuari Aplicació")
public class AvailableProfile {

    @Schema(description = "Codi del perfil", required = true)
    protected String code;
    @Schema(description = "Nom del perfil en l’idioma elegit.", required = true)
    protected String name;
    @Schema(description = "Descripció del perfil en l’idioma elegit.", required = true)
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
