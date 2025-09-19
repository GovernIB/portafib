package es.caib.portafib.api.interna.secure.signature.v1.signatureflowtemplate;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal
 * 18 sept 2025 12:13:17
 */
@Schema(description = "Informació Bàsica d'una Plantilla de flux de firmes. Requereix com a mínim PortaFIB 3.0.9", requiredMode = Schema.RequiredMode.REQUIRED)
public class SignatureFlowTemplateInfo {

    // NOT REQUIRED !!!! Durant la creació
    @Schema(description = "Identificador de la plantilla flux de firmes."
            + "Ha de quedar buit durant l'operació de creació."
            + " Durant la creació via web només s'omplirà si es una plantilla a guardar en servidor.",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    protected String flowTemplateId;

    @Schema(description = "Nom de la plantilla flux de firmes.", requiredMode = Schema.RequiredMode.REQUIRED)
    protected String name;

    @Schema(description = "Descripció de la plantilla flux de firmes.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    protected String description;

    public SignatureFlowTemplateInfo() {
        super();
    }

    public SignatureFlowTemplateInfo(String flowTemplateId, String name, String description) {
        super();
        this.flowTemplateId = flowTemplateId;
        this.name = name;
        this.description = description;
    }

    

    public String getFlowTemplateId() {
        return flowTemplateId;
    }

    public void setFlowTemplateId(String flowTemplateId) {
        this.flowTemplateId = flowTemplateId;
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


    public static String toString(SignatureFlowTemplateInfo flux) {
        StringBuffer str = new StringBuffer();

        str.append("FluxID => " + flux.getFlowTemplateId()).append("\n");
        str.append("Name:  " + flux.getName()).append("\n");
        str.append("Desc:  " + flux.getDescription()).append("\n");
       
        return str.toString();

    }

}
