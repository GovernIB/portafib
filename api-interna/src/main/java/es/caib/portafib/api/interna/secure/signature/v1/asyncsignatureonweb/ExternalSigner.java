package es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb;


import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Schema(description = "Informació requerida per definir un Usuari Extern per a la firma electrònica.")
public class ExternalSigner {

    @Schema(description = "NIF o DNI", requiredMode = RequiredMode.REQUIRED)
    protected String administrationId = null;
    @Schema(description = "Nom de la Persona", requiredMode = RequiredMode.REQUIRED)
    protected java.lang.String name = null;
    @Schema(description = "Llinatges de la Persona", requiredMode = RequiredMode.REQUIRED)
    protected String surnames = null;
    @Schema(description = "Correu electrònic de la persona", requiredMode = RequiredMode.REQUIRED)
    protected String email = null;
    @Schema(description = "Idioma en que mostrar les pantalles web.", requiredMode = RequiredMode.REQUIRED)
    protected String language = null;
    @Schema(description = "Nivell de Seguretat", requiredMode = RequiredMode.REQUIRED)
    protected int securityLevel = ExternalSignerSecurityLevelConstants.TOKEN.value;

    public ExternalSigner() {
        super();
    }

    public ExternalSigner(String administrationId, String name, String surnames, String email, String language,
            int securityLevel) {
        super();
        this.administrationId = administrationId;
        this.name = name;
        this.surnames = surnames;
        this.email = email;
        this.language = language;
        this.securityLevel = securityLevel;
    }

    public String getAdministrationId() {
        return administrationId;
    }

    public void setAdministrationId(String administrationId) {
        this.administrationId = administrationId;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(int securityLevel) {
        this.securityLevel = securityLevel;
    }

}
