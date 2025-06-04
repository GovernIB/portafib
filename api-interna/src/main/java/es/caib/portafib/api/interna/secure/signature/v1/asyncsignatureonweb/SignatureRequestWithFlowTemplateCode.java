package es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb;

import java.util.List;

import es.caib.portafib.api.interna.secure.signature.v1.commons.Document;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

/**
 * 
 * @author anadal
 * 16 may 2025 12:02:33
 */
@Schema(
        description = "Estructura de dades per a la sol·licitud de signatura electrònica a partir d'un identificador de Plantilla de Flux de Firmes")
public class SignatureRequestWithFlowTemplateCode extends SignatureRequestBase {

    @Schema(
            description = "Codi de la Plantilla del Flux de Firmes a utilitzar que es troba en el PortaFirmes."
                    + " PortaFIB actualment no suporta codis en Plantilles de flux de firmes en format String,"
                    + " per la qual cosa aquí s'ha de posar l'ID de BBDD de la Plantilla de Flux de Firmes.",
            requiredMode = RequiredMode.REQUIRED)
    protected long flowTemplateCode;

    public SignatureRequestWithFlowTemplateCode() {
        super();
    }

    public SignatureRequestWithFlowTemplateCode(String profileCode, String title, String description, String reason,
            Document fileToSign, Document originalDetachedSignature, long documentType, String documentTypeDescription,
            String languageDoc, String languageUI, int priority, String senderName, String senderDescription,
            String expedientCode, String expedientName, String expedientUrl, String procedureCode, String procedureName,
            String additionalInformation, Double additionalInformationEvaluable, long flowTemplateCode) {
        this(profileCode, title, description, reason, fileToSign, originalDetachedSignature, documentType,
                documentTypeDescription, languageDoc, languageUI, priority, senderName, senderDescription,
                expedientCode, expedientName, expedientUrl, procedureCode, procedureName, additionalInformation,
                additionalInformationEvaluable, flowTemplateCode, null, null);
    }

    public SignatureRequestWithFlowTemplateCode(String profileCode, String title, String description, String reason,
            Document fileToSign, Document originalDetachedSignature, long documentType, String documentTypeDescription,
            String languageDoc, String languageUI, int priority, String senderName, String senderDescription,
            String expedientCode, String expedientName, String expedientUrl, String procedureCode, String procedureName,
            String additionalInformation, Double additionalInformationEvaluable, long flowTemplateCode,
            List<Annex> annexs, List<Metadata> metadadaList) {
        super(profileCode, title, description, reason, fileToSign, originalDetachedSignature, documentType,
                documentTypeDescription, languageDoc, languageUI, priority, senderName, senderDescription,
                expedientCode, expedientName, expedientUrl, procedureCode, procedureName, additionalInformation,
                additionalInformationEvaluable, annexs, metadadaList);
        this.flowTemplateCode = flowTemplateCode;

    }

    public SignatureRequestWithFlowTemplateCode(SignatureRequestBase base, long flowTemplateCode) {
        super(base);
        this.flowTemplateCode = flowTemplateCode;
    }

    public long getFlowTemplateCode() {
        return flowTemplateCode;
    }

    public void setFlowTemplateCode(long flowTemplateCode) {
        this.flowTemplateCode = flowTemplateCode;
    }

}
