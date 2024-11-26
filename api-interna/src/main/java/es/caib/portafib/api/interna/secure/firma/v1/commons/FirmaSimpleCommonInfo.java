package es.caib.portafib.api.interna.secure.firma.v1.commons;

public class FirmaSimpleCommonInfo {

	// Perfil de Firma definit en el servidor intermig
	protected String signProfile;

	protected String languageUI;

	protected String username;

	// Inidica el NIF de la persona que signarà
	protected String administrationID;

	// Indica el CIF de l'empresa si és un certificat de representant
	protected String organizationID;

	protected String signerEmail;

	/**
	 * 
	 */
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