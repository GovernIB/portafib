package es.caib.portafib.back.form;

import es.caib.portafib.model.entity.UsuariPersona;


/**
 * Created 28/05/13 14:14
 *
 * @author mgonzalez
 * Formulari que representa les opcions per crear un usuari entitat a partir d'un nif
 */
public class SeleccioUsuariEntitatForm {


   String nif;                                // nif de usuari entitat persona a crear.
   UsuariPersona up;                          // UsuariPersona que volem crear o modificar.
   String entitatID;                          // Identificador de la entitat.


   public SeleccioUsuariEntitatForm() {
     super();
   }


    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public UsuariPersona getUp() {
        return up;
    }

    public void setUp(UsuariPersona up) {
        this.up = up;
    }

    public String getEntitatID() {
        return entitatID;
    }

    public void setEntitatID(String entitatID) {
        this.entitatID = entitatID;
    }
}
