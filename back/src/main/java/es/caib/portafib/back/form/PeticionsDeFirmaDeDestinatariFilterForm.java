package es.caib.portafib.back.form;

import org.springframework.stereotype.Component;

import es.caib.portafib.back.form.webdb.PeticioDeFirmaFilterForm;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Component
public class PeticionsDeFirmaDeDestinatariFilterForm extends PeticioDeFirmaFilterForm {
  
  /**
   * Usuari del que volem revisar les firmes actives de peticions de firmes.
   * El llistat inclou l'usuari entitat i tots els càrrecs.
   */
  // TODO Guardar usuariEntitatID dins la sessió enlloc de fer un nou form !!!
  private String usuariEntitatID; 

  public PeticionsDeFirmaDeDestinatariFilterForm() {    
  }
  
  public PeticionsDeFirmaDeDestinatariFilterForm(PeticioDeFirmaFilterForm ffff) {
    super(ffff);
  }

  public String getUsuariEntitatID() {
    return usuariEntitatID;
  }

  public void setUsuariEntitatID(String usuariEntitatID) {
    this.usuariEntitatID = usuariEntitatID;
  }

}
