package es.caib.portafib.api.interna.secure.firma.v1.firmaenservidor;

import es.caib.portafib.api.interna.secure.firma.v1.commons.FirmaSimpleFile;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

/**
 * 
 * @author anadal(u80067)
 *
 */

public class FirmaSimpleUpgradeRequest {
    @Schema(
            description = "Codi del perfil a utilitzar. Si no es defineix, llavors requerim que quest usuari aplicación només tengui un Perfil definit.",
            example = "",
            requiredMode = RequiredMode.NOT_REQUIRED)
	String profileCode;

    @Schema(
            description = "Firma a actualitzar",
            example = "",
            requiredMode = RequiredMode.REQUIRED)
	FirmaSimpleFile signature;
    
    @Schema(
            description = "Document detached. Només s'usa per les validacions",
            example = "",
            requiredMode = RequiredMode.NOT_REQUIRED)
	FirmaSimpleFile detachedDocument;

	/**
	 * Certificat del que penjar l'upgrade a l'hora de fer cofirmes i contrafirmes
	 */
    @Schema(
            description = "Certificat del que penjar l'upgrade a l'hora de fer cofirmes i contrafirmes",
            example = "",
            requiredMode = RequiredMode.NOT_REQUIRED)
	FirmaSimpleFile targetCertificate;

    @Schema(
            description = "Idioma dels missatges en cas d'informar o d'errors.",
            example = "ca",
            requiredMode = RequiredMode.REQUIRED)
	String languageUI;

	public FirmaSimpleUpgradeRequest() {
		super();
	}

	public FirmaSimpleUpgradeRequest(String profileCode, FirmaSimpleFile signature, FirmaSimpleFile detachedDocument,
			FirmaSimpleFile targetCertificate, String languageUI) {
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

	public FirmaSimpleFile getSignature() {
		return signature;
	}

	public void setSignature(FirmaSimpleFile signature) {
		this.signature = signature;
	}

	public FirmaSimpleFile getTargetCertificate() {
		return targetCertificate;
	}

	public void setTargetCertificate(FirmaSimpleFile targetCertificate) {
		this.targetCertificate = targetCertificate;
	}

	public FirmaSimpleFile getDetachedDocument() {
		return detachedDocument;
	}

	public void setDetachedDocument(FirmaSimpleFile detachedDocument) {
		this.detachedDocument = detachedDocument;
	}

}
