package es.caib.portafib.api.interna.secure.signature.v1.signatureflowtemplate;

import java.util.List;

import es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb.SignatureBlock;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal
 * 3 jun 2025 11:54:06
 */
@Schema(
        description = "Informació Completa d'una Plantilla de flux de firmes.",
        requiredMode = Schema.RequiredMode.REQUIRED)
public class SignatureFlowTemplate extends SignatureFlowTemplateInfo {

    @Schema(
            description = "Llista de blocs de signatura que composen el flux de firmes de la plantilla.",
            requiredMode = Schema.RequiredMode.REQUIRED)
    protected List<SignatureBlock> blocks;

    public SignatureFlowTemplate() {
        super();
    }

    public SignatureFlowTemplate(String flowTemplateId, String name, String description, List<SignatureBlock> blocks) {
        super(flowTemplateId, name, description);
        this.blocks = blocks;
    }

    public List<SignatureBlock> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<SignatureBlock> blocks) {
        this.blocks = blocks;
    }

    public static String toString(SignatureFlowTemplate flux) {

        StringBuffer str = new StringBuffer();

        str.append(SignatureFlowTemplateInfo.toString(flux));

        List<SignatureBlock> blocks = flux.getBlocks();

        for (SignatureBlock block : blocks) {
            str.append(" =========== BLOC [" + block.getOrder() + " ] ==============").append("\n");
            str.append(SignatureBlock.toString(block)).append("\n");
        }

        return str.toString();

    }

}
