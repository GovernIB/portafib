package es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Revisor de Firmes
 * 
 * @author anadal(u80067)
 *
 */
@Schema(description = "Classe que representa un Revisor d'una Firma. Només s'ha d'omplir un camp dels que conté.")
public class Reviser extends Person {

    @Schema(description = "Indica si aquesta revisió es obligatoria", requiredMode = Schema.RequiredMode.REQUIRED)
    protected boolean required;

    public Reviser() {
        super();
    }

    public Reviser(Person persona, boolean required) {
        this(persona.getPositionInTheCompany(), persona.getAdministrationID(), persona.getUsername(),
                persona.getIntermediateServerUsername(), required);
    }

    public Reviser(String positionInTheCompany, String administrationID, String username,
            String intermediateServerUsername, boolean required) {
        super(positionInTheCompany, administrationID, username, intermediateServerUsername);
        this.required = required;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

}
