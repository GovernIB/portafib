package es.caib.portafib.api.interna.secure.signature.v1.signatureflowtemplate;

import java.util.List;

import es.caib.portafib.api.interna.secure.signature.v1.commons.KeyValue;
import es.caib.portafib.api.interna.secure.signature.v1.commons.ProcessStatus;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Resultat d'una firma
 * 
 * @author anadal
 *
 */
@Schema(description = "Informació de l'estat d'un procés de creacio d'una plantilla de flux de signatura via web.")
public class SignatureFlowTemplateTransactionResult {

    @Schema(
            description = "Estat del procés de creació de la plantilla de flux de signatura",
            requiredMode = Schema.RequiredMode.REQUIRED)
    protected ProcessStatus status;

    @Schema(
            description = "Detalls de la plantilla de Flux de Firmes Creada. Només si el procés ha finalitzat correctament.",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    protected SignatureFlowTemplate flowInfo;

    @Schema(description = "Llistat de propietat addicionals", requiredMode = Schema.RequiredMode.REQUIRED)
    protected List<KeyValue> properties;

    /**
     * 
     */
    public SignatureFlowTemplateTransactionResult() {
        super();
    }

    public SignatureFlowTemplateTransactionResult(ProcessStatus status, SignatureFlowTemplate flowInfo,
            List<KeyValue> properties) {
        super();
        this.status = status;
        this.flowInfo = flowInfo;
        this.properties = properties;
    }

    public ProcessStatus getStatus() {
        return status;
    }

    public void setStatus(ProcessStatus status) {
        this.status = status;
    }

    public SignatureFlowTemplate getFlowInfo() {
        return flowInfo;
    }

    public void setFlowInfo(SignatureFlowTemplate flowInfo) {
        this.flowInfo = flowInfo;
    }

    public List<KeyValue> getProperties() {
        return properties;
    }

    public void setProperties(List<KeyValue> properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        List<KeyValue> additionalInformation = getProperties();

        if (additionalInformation != null && additionalInformation.size() != 0) {
            str.append("\n").append("        + INFORMACIO ADDICIONAL:");
            for (KeyValue firmaSimpleKeyValue : additionalInformation) {
                str.append("\n").append(
                        "          >> KEY[" + firmaSimpleKeyValue.getKey() + "]: " + firmaSimpleKeyValue.getValue());
            }
        }

        return str.toString();

    }

}
