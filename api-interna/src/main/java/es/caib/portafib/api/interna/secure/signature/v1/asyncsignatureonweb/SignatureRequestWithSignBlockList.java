package es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb;

import java.util.List;

import es.caib.portafib.api.interna.secure.signature.v1.commons.Document;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

/**
 * 
 * @author anadal
 * 21 may 2025 10:47:13
 */
@Schema(
        description = "Estructura de dades per a la sol·licitud de signatura electrònica amb una llista de blocs de signatura")
public class SignatureRequestWithSignBlockList extends SignatureRequestBase {

    @Schema(
            description = "Estructura del Flux de Firmes, és a dir, dels destinataris que han de signar el document.",
            requiredMode = RequiredMode.REQUIRED)
    protected SignatureBlock[] signatureBlocks = null;

    public SignatureRequestWithSignBlockList() {
        super();
    }

    public SignatureRequestWithSignBlockList(SignatureRequestBase base, SignatureBlock[] signatureBlocks) {
        super(base);
        this.signatureBlocks = signatureBlocks;
    }

    public SignatureRequestWithSignBlockList(String profileCode, String title, String description, String reason,
            Document fileToSign, Document originalDetachedSignature, long documentType, String documentTypeDescription,
            String languageDoc, String languageUI, int priority, String senderName, String senderDescription,
            String expedientCode, String expedientName, String expedientUrl, String procedureCode, String procedureName,
            String additionalInformation, Double additionalInformationEvaluable, SignatureBlock[] signatureBlocks) {
        this(profileCode, title, description, reason, fileToSign, originalDetachedSignature, documentType,
                documentTypeDescription, languageDoc, languageUI, priority, senderName, senderDescription,
                expedientCode, expedientName, expedientUrl, procedureCode, procedureName, additionalInformation,
                additionalInformationEvaluable, signatureBlocks, null, null);
    }

    public SignatureRequestWithSignBlockList(String profileCode, String title, String description, String reason,
            Document fileToSign, Document originalDetachedSignature, long documentType, String documentTypeDescription,
            String languageDoc, String languageUI, int priority, String senderName, String senderDescription,
            String expedientCode, String expedientName, String expedientUrl, String procedureCode, String procedureName,
            String additionalInformation, Double additionalInformationEvaluable, SignatureBlock[] signatureBlocks,
            List<Annex> annexs, List<Metadata> metadadaList) {
        super(profileCode, title, description, reason, fileToSign, originalDetachedSignature, documentType,
                documentTypeDescription, languageDoc, languageUI, priority, senderName, senderDescription,
                expedientCode, expedientName, expedientUrl, procedureCode, procedureName, additionalInformation,
                additionalInformationEvaluable, annexs, metadadaList);
        this.signatureBlocks = signatureBlocks;

    }

    public SignatureBlock[] getSignatureBlocks() {
        return signatureBlocks;
    }

    public void setSignatureBlocks(SignatureBlock[] signatureBlocks) {
        this.signatureBlocks = signatureBlocks;
    }

}
