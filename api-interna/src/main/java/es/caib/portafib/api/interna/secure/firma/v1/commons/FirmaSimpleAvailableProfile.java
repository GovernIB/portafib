package es.caib.portafib.api.interna.secure.firma.v1.commons;

import es.caib.portafib.api.interna.secure.firma.v1.commons.apisib.ApisIBAvailableProfile;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal(u80067)
 *
 */

@Schema(description = "Indica si inclou política de firma (true, EPES) o no (false)", example="True", required = true)
public class FirmaSimpleAvailableProfile extends ApisIBAvailableProfile<FirmaSimpleKeyValue> {
    @Schema(description = "Codi del perfil", required = true)
	protected String code;
    @Schema(description = "Nom del perfil en l’idioma elegit.", required = true)
	protected String name;
    @Schema(description = "Descripció del perfil en l’idioma elegit.", required = true)
	protected String description;

	public FirmaSimpleAvailableProfile() {
		super();
	}

	public FirmaSimpleAvailableProfile(String code, String name, String description) {
		super();
		this.code = code;
		this.name = name;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

}
