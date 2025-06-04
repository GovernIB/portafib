package es.caib.portafib.api.interna.secure.signature.v1.signatureflow;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class SignatureFlowTemplateTransactionIdRequest {

    protected String languageUI;

    protected boolean saveOnServer;

    protected String name;

    protected String description;

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
