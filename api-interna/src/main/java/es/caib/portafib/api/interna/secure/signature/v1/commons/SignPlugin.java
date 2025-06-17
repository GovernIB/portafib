package es.caib.portafib.api.interna.secure.signature.v1.commons;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Informació del Plugin de firma utilitzat en la signatura d'aquest document.
 * 
 * @author anadal
 * 16 jun 2025 12:59:41
 */
@Schema(description = "Informació del Plugin de firma utilitzat en la signatura d'aquest document.")
public class SignPlugin {

    @Schema(description = "Identificador del plugin de firma utilitzat.")
    protected String signaturePluginId;

    @Schema(description = "Codi del plugin de firma utilitzat.")
    protected String signaturePluginCode;

    @Schema(description = "Nom intern del plugin de firma utilitzat.")
    protected String signaturePluginNameInternal;

    @Schema(description = "Nom públic del plugin de firma utilitzat.")
    protected String signaturePluginNamePublic;

    @Schema(description = "Descripció pública del plugin de firma utilitzat.")
    protected String signaturePluginDescriptionPublic;

    public SignPlugin() {
        super();
    }

    public SignPlugin(String signaturePluginId, String signaturePluginCode, String signaturePluginNameInternal,
            String signaturePluginNamePublic, String signaturePluginDescriptionPublic) {
        super();
        this.signaturePluginId = signaturePluginId;
        this.signaturePluginCode = signaturePluginCode;
        this.signaturePluginNameInternal = signaturePluginNameInternal;
        this.signaturePluginNamePublic = signaturePluginNamePublic;
        this.signaturePluginDescriptionPublic = signaturePluginDescriptionPublic;
    }

    public String getSignaturePluginId() {
        return signaturePluginId;
    }

    public void setSignaturePluginId(String signaturePluginId) {
        this.signaturePluginId = signaturePluginId;
    }

    public String getSignaturePluginNameInternal() {
        return signaturePluginNameInternal;
    }

    public void setSignaturePluginNameInternal(String signaturePluginNameInternal) {
        this.signaturePluginNameInternal = signaturePluginNameInternal;
    }

    public String getSignaturePluginNamePublic() {
        return signaturePluginNamePublic;
    }

    public void setSignaturePluginNamePublic(String signaturePluginNamePublic) {
        this.signaturePluginNamePublic = signaturePluginNamePublic;
    }

    public String getSignaturePluginDescriptionPublic() {
        return signaturePluginDescriptionPublic;
    }

    public void setSignaturePluginDescriptionPublic(String signaturePluginDescriptionPublic) {
        this.signaturePluginDescriptionPublic = signaturePluginDescriptionPublic;
    }

    public String getSignaturePluginCode() {
        return signaturePluginCode;
    }

    public void setSignaturePluginCode(String signaturePluginCode) {
        this.signaturePluginCode = signaturePluginCode;
    }

}
