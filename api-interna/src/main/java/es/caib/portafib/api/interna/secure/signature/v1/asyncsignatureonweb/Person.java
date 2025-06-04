package es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

/**
 * Identificador de persona. Només s'ha d'omplir un camp.
 * 
 * @author anadal(u80067)
 *
 */
public class Person {

    /**
     * Identificador de Càrrec: fundaciobit_gerent, caib_president, ...
     */
    @Schema(
            description = "Identificador que representa un Càrrec. Exemples: fundaciobit_gerent, caib_president, ...",
            requiredMode = RequiredMode.NOT_REQUIRED)
    protected String positionInTheCompany;

    /**
     * NIF
     */
    @Schema(
            description = "Identificador administratiu. En el cas de PortaFIB serà NIF, NIE, ...",
            requiredMode = RequiredMode.NOT_REQUIRED)
    protected String administrationID;

    /**
     * Nom d'usuari: u806666 o anadal
     */
    @Schema(
            description = "Nom d'usuari que té la persona en la corporació o entitat. Exemples: u806666 o anadal",
            requiredMode = RequiredMode.NOT_REQUIRED)
    protected String username;

    /**
     * ID del servidor intermedi(PortaFIB): fundaciobit_anadal, caib_u80067
     */
    @Schema(
            description = "ID intern del servidor intermedi (en el nostre cas PortaFIB). Exemples: fundaciobit_anadal, caib_u80067, ...",
            requiredMode = RequiredMode.NOT_REQUIRED)
    protected String intermediateServerUsername;

    public Person() {
        super();
    }

    public Person(String positionInTheCompany, String administrationID, String username,
            String intermediateServerUsername) {
        super();
        this.positionInTheCompany = positionInTheCompany;
        this.administrationID = administrationID;
        this.username = username;
        this.intermediateServerUsername = intermediateServerUsername;
    }

    public String getPositionInTheCompany() {
        return positionInTheCompany;
    }

    public void setPositionInTheCompany(String positionInTheCompany) {
        this.positionInTheCompany = positionInTheCompany;
    }

    public String getAdministrationID() {
        return administrationID;
    }

    public void setAdministrationID(String administrationID) {
        this.administrationID = administrationID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIntermediateServerUsername() {
        return intermediateServerUsername;
    }

    public void setIntermediateServerUsername(String intermediateServerUsername) {
        this.intermediateServerUsername = intermediateServerUsername;
    }
    

    public static String toString(Person person) {
      
      if (person.getUsername() != null) {
        return "Username: " + person.getUsername();
      }
      
      
      if (person.getAdministrationID() != null) {
        return "AdministrationID: " + person.getAdministrationID();
      }
      
      if (person.getIntermediateServerUsername() != null) {
        return "IntermediateServerUsername: " + person.getIntermediateServerUsername();
      }
      
      if (person.getPositionInTheCompany() != null) {
        return "PositionInTheCompany: " + person.getPositionInTheCompany();
      }
      
      return "Error Name: Not defined value for FlowTemplateSimplePerson !!!";
      
      
    }

}
