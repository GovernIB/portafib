package es.caib.portafib.api.interna.secure.revisors.v1;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Informació basica d'un usuari
 */
@Schema(description = "Informació basica d'un usuari")
public class BasicUserInfo {
    
    
    private String username;
    
    private String name;
    
    private String surname;

    @Schema(description = "NIF de l'usuari")
    private String administrationId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname1) {
        this.surname = surname1;
    }

    public String getAdministrationId() {
        return administrationId;
    }

    public void setAdministrationId(String administrationId) {
        this.administrationId = administrationId;
    }
    
    

}
