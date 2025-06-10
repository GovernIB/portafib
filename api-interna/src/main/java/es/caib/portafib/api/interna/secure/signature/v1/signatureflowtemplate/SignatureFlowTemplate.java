package es.caib.portafib.api.interna.secure.signature.v1.signatureflowtemplate;

import java.util.List;


import es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb.SignatureBlock;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal
 * 3 jun 2025 11:54:06
 */
@Schema(description = "Informació d'una Plantilla de flux de firmes.", requiredMode = Schema.RequiredMode.REQUIRED)
public class SignatureFlowTemplate {

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

    @Schema(
            description = "Llista de blocs de signatura que composen el flux de firmes de la plantilla.",
            requiredMode = Schema.RequiredMode.REQUIRED)
    protected List<SignatureBlock> blocks;

    public SignatureFlowTemplate() {
        super();
    }

    public SignatureFlowTemplate(String flowTemplateId, String name, String description,
            List<SignatureBlock> blocks) {
        super();
        this.flowTemplateId = flowTemplateId;
        this.name = name;
        this.description = description;
        this.blocks = blocks;
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

    public List<SignatureBlock> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<SignatureBlock> blocks) {
        this.blocks = blocks;
    }

    public static String toString(SignatureFlowTemplate flux) {
        StringBuffer str = new StringBuffer();

        str.append("FluxID => " + flux.getFlowTemplateId()).append("\n");
        str.append("Name:  " + flux.getName()).append("\n");
        List<SignatureBlock> blocks = flux.getBlocks();

        for (SignatureBlock block : blocks) {
            str.append(" =========== BLOC [" + block.getOrder() + " ] ==============").append("\n");
            str.append(SignatureBlock.toString(block)).append("\n");
        }

        return str.toString();

    }

}
