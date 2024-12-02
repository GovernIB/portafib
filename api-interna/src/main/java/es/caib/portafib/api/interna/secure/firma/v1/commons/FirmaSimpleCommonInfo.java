package es.caib.portafib.api.interna.secure.firma.v1.commons;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Configuracions generals de firma i identificacio del solicitant i solicitat")
public class FirmaSimpleCommonInfo {

	// Perfil de Firma definit en el servidor intermig
    @Schema(description = "Identificador único del usuario", example = "PROFILE_PADES", required = false)
	protected String signProfile;

    @Schema(description = "Idioma en que retornar valors i missatges d'error", example = "ca", required = true)
	protected String languageUI;
    
    @Schema(description = " - FIRMA WEB: Requerit. És el codi d'usuari dins l'entitat. Per exemple en entorn CAIB serien els \"u800xx\" o \"u[DNI]\"\r\n"
            + " -FIRMA EN SERVIDOR: Opcional. Es reconama que valgui null a no ser que l'administrador digui el contrari. És la configuració de firma en el sistema específic de firma. Per exemple amb el Plugin de @firma federat et pots connectar amb un usuari-password però aquest pot tenir diverses configuracions per fer firmes en servidor o àlies: \"username\" s'utilitza de definir aquesta configuració o àlies.", example = "\"u800xx\" / \"u[DNI]\"", required = false)
	protected String username;

    @Schema(description = " - FIRMA WEB: Requerit. És el DNI de la persona signant. Si esta activa la validació dins PortaFIB llavors es valida que el DNI del Certificat sigui el mateix que aquest.\r\n"
            + " - FIRMA EN SERVIDOR: Opcional. És el CIF o NIF associat al certificat en servidor. Si es defineix i si esta activa la validació dins PortaFIB llavors es valida que el DNI del Certificat sigui el mateix que aquest.", example = "99999999Z", required = true)
	protected String administrationID;

    @Schema(description = "Opcional. És el CIF de l'organització representada pel signant. Si esta activa la validació dins PortaFIB llavors es valida que el Certificat sigui un certificat de representant d'aquest CIF.", example = "B99999999", required = false)
	protected String organizationID;
    
    @Schema(description = " - FIRMA WEB: Opcional. Correu del Firmant. Per afegir a les dades de la firma.\r\n"
            + " - FIRMA EN SERVIDOR: Opcional. Correu del departament que ordena la firma. Per afegir a les dades de la firma.", example = "correufirmant@entitat.org", required = false)
	protected String signerEmail;

	public FirmaSimpleCommonInfo() {
	}

	public FirmaSimpleCommonInfo(String signProfile, String languageUI, String username, String administrationID,
			String signerEmail) {
		super();
		this.signProfile = signProfile;
		this.languageUI = languageUI;
		this.username = username;
		this.administrationID = administrationID;
		this.signerEmail = signerEmail;
	}

	public FirmaSimpleCommonInfo(String signProfile, String languageUI, String username, String administrationID,
			String organizationID, String signerEmail) {
		this.signProfile = signProfile;
		this.languageUI = languageUI;
		this.username = username;
		this.administrationID = administrationID;
		this.organizationID = organizationID;
		this.signerEmail = signerEmail;
	}

	public String getLanguageUI() {
		return languageUI;
	}

	public void setLanguageUI(String languageUI) {
		this.languageUI = languageUI;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAdministrationID() {
		return administrationID;
	}

	public void setAdministrationID(String administrationID) {
		this.administrationID = administrationID;
	}

	public String getOrganizationID() {
		return organizationID;
	}

	public void setOrganizationID(String organizationID) {
		this.organizationID = organizationID;
	}

	public String getSignProfile() {
		return signProfile;
	}

	public void setSignProfile(String signProfile) {
		this.signProfile = signProfile;
	}

	public String getSignerEmail() {
		return signerEmail;
	}

	public void setSignerEmail(String signerEmail) {
		this.signerEmail = signerEmail;
	}

}