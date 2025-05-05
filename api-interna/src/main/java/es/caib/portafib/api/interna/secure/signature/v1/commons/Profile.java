package es.caib.portafib.api.interna.secure.signature.v1.commons;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

/**
 * 
 * @author anadal
 * 18 dic 2024 11:28:34
 */
@Schema(description = "Detalls del Perfil d'un usuari Aplicació")
public class Profile {

    @Schema(description = "Codi del perfil",  requiredMode = RequiredMode.REQUIRED)
    protected String code;
    @Schema(description = "Nom del perfil en l’idioma elegit.",  requiredMode = RequiredMode.REQUIRED)
    protected String name;
    @Schema(description = "Descripció del perfil en l’idioma elegit.",  requiredMode = RequiredMode.REQUIRED)
    protected String description;

    protected List<KeyValue> properties;

    public Profile() {
        super();
    }

    public Profile(String code, String name, String description, List<KeyValue> properties) {
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
