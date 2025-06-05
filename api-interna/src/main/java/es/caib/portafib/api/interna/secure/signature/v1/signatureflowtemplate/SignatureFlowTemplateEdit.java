package es.caib.portafib.api.interna.secure.signature.v1.signatureflowtemplate;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Schema(description = "Informació requerida per iniciar un procés web de modificació d'una Plantilla")
public class SignatureFlowTemplateEdit {

    @Schema(description = "Codi de l'idioma de la interfície d'usuari", example = "ca")
    protected String languageUI;

    @Schema(description = "Identificador de la plantilla de flux que volem editar")
    protected String flowTemplateId;

    @Schema(
            description = "URL de retorn a la que s'ha de redirigir l'usuari un cop finalitzat el procés de modificació de la plantilla")
    protected String returnUrl;

    public SignatureFlowTemplateEdit() {
        super();
    }

    public SignatureFlowTemplateEdit(String languageUI, String flowTemplateId, String returnUrl) {
        super();
        this.languageUI = languageUI;
        this.flowTemplateId = flowTemplateId;
        this.returnUrl = returnUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getLanguageUI() {
        return languageUI;
    }

    public void setLanguageUI(String languageUI) {
        this.languageUI = languageUI;
    }

    public String getFlowTemplateId() {
        return flowTemplateId;
    }

    public void setFlowTemplateId(String flowTemplateId) {
        this.flowTemplateId = flowTemplateId;
    }

}
