package es.caib.portafib.api.interna.secure.signature.v1.signatureflow;

import java.util.List;


import es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb.SignatureBlock;

/**
 * 
 * @author anadal
 * 3 jun 2025 11:54:06
 */
public class SignatureFlowTemplate {

    protected String intermediateServerFlowTemplateId;

    protected String name;

    protected String description;

    protected List<SignatureBlock> blocks;

    public SignatureFlowTemplate() {
        super();
    }

    public SignatureFlowTemplate(String intermediateServerFlowTemplateId, String name, String description,
            List<SignatureBlock> blocks) {
        super();
        this.intermediateServerFlowTemplateId = intermediateServerFlowTemplateId;
        this.name = name;
        this.description = description;
        this.blocks = blocks;
    }

    public String getIntermediateServerFlowTemplateId() {
        return intermediateServerFlowTemplateId;
    }

    public void setIntermediateServerFlowTemplateId(String intermediateServerFlowTemplateId) {
        this.intermediateServerFlowTemplateId = intermediateServerFlowTemplateId;
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

        str.append("FluxID => " + flux.getIntermediateServerFlowTemplateId()).append("\n");
        str.append("Name:  " + flux.getName()).append("\n");
        List<SignatureBlock> blocks = flux.getBlocks();

        for (SignatureBlock block : blocks) {
            str.append(" =========== BLOC [" + block.getOrder() + " ] ==============").append("\n");
            str.append(SignatureBlock.toString(block)).append("\n");
        }

        return str.toString();

    }

}
