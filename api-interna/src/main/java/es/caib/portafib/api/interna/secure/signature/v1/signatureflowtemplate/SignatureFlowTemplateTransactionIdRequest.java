package es.caib.portafib.api.interna.secure.signature.v1.signatureflowtemplate;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Schema(description = "Informació requerida a que el sistema retorni un identificador de transacció "
        + "per a la creació web d'una plantilla de flux de signatura")
public class SignatureFlowTemplateTransactionIdRequest {

    @Schema(description = "Codi de l'idioma de la interfície d'usuari", example = "ca")
    protected String languageUI;

    @Schema(description = "Indica si s'ha de guardar la plantilla al servidor", example = "true")
    protected boolean saveOnServer;

    @Schema(description = "Nom de la plantilla de flux", example = "Plantilla de flux de signatura")
    protected String name;

    @Schema(description = "Descripció de la plantilla de flux")
    protected String description;

    @Schema(description = "Indica si la descripció de la plantilla de flux és visible per l'usuari que l'edita o el veu.", example = "true")
    protected boolean visibleDescription;

    /**
     * 
     */
    public SignatureFlowTemplateTransactionIdRequest() {
        super();
    }

    public SignatureFlowTemplateTransactionIdRequest(String languageUI, boolean saveOnServer, String name, String description,
            boolean visibleDescription) {
        super();
        this.languageUI = languageUI;
        this.saveOnServer = saveOnServer;
        this.name = name;
        this.description = description;
        this.visibleDescription = visibleDescription;
    }

    public String getLanguageUI() {
        return languageUI;
    }

    public void setLanguageUI(String languageUI) {
        this.languageUI = languageUI;
    }

    public boolean isVisibleDescription() {
        return visibleDescription;
    }

    public void setVisibleDescription(boolean visibleDescription) {
        this.visibleDescription = visibleDescription;
    }

    public boolean isSaveOnServer() {
        return saveOnServer;
    }

    public void setSaveOnServer(boolean saveOnServer) {
        this.saveOnServer = saveOnServer;
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
