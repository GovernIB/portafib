package es.caib.portafib.api.interna.secure.signature.v1.signatureonserver;

import es.caib.portafib.api.interna.secure.signature.v1.commons.Document;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

/**
 * 
 * @author anadal(u80067)
 *
 */

public class UpgradeRequest {
    @Schema(
            description = "Codi del perfil a utilitzar. Si no es defineix, llavors requerim que quest usuari aplicación només tengui un Perfil definit.",
            example = "",
            requiredMode = RequiredMode.NOT_REQUIRED)
	String profileCode;

    @Schema(
            description = "Firma a actualitzar",
            example = "",
            requiredMode = RequiredMode.REQUIRED)
	Document signature;
    
    @Schema(
            description = "Document detached. Només s'usa per les validacions",
            example = "",
            requiredMode = RequiredMode.NOT_REQUIRED)
	Document detachedDocument;

	/**
	 * Certificat del que penjar l'upgrade a l'hora de fer cofirmes i contrafirmes
	 */
    @Schema(
            description = "Certificat del que penjar l'upgrade a l'hora de fer cofirmes i contrafirmes",
            example = "",
            requiredMode = RequiredMode.NOT_REQUIRED)
	Document targetCertificate;

    @Schema(
            description = "Idioma dels missatges en cas d'informar o d'errors.",
            example = "ca",
            requiredMode = RequiredMode.REQUIRED)
	String languageUI;

	public UpgradeRequest() {
		super();
	}

	public UpgradeRequest(String profileCode, Document signature, Document detachedDocument,
			Document targetCertificate, String languageUI) {
		super();
		this.profileCode = profileCode;
		this.signature = signature;
		this.detachedDocument = detachedDocument;
		this.targetCertificate = targetCertificate;
		this.languageUI = languageUI;
	}

	public String getProfileCode() {
		return profileCode;
	}

	public void setProfileCode(String profileCode) {
		this.profileCode = profileCode;
	}

	public String getLanguageUI() {
		return languageUI;
	}

	public void setLanguageUI(String languageUI) {
		this.languageUI = languageUI;
	}

	public Document getSignature() {
		return signature;
	}

	public void setSignature(Document signature) {
		this.signature = signature;
	}

	public Document getTargetCertificate() {
		return targetCertificate;
	}

	public void setTargetCertificate(Document targetCertificate) {
		this.targetCertificate = targetCertificate;
	}

	public Document getDetachedDocument() {
		return detachedDocument;
	}

	public void setDetachedDocument(Document detachedDocument) {
		this.detachedDocument = detachedDocument;
	}

}
