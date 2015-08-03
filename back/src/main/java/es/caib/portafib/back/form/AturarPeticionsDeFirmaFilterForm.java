package es.caib.portafib.back.form;

import org.springframework.stereotype.Component;

import es.caib.portafib.back.form.webdb.PeticioDeFirmaFilterForm;

/**
 * 
 * @author anadal
 *
 */
@Component
public class AturarPeticionsDeFirmaFilterForm extends PeticioDeFirmaFilterForm {
  
  /**
   * Usuari del que volem revisar les firmes actives de peticions de firmes.
   * El llistat inclou l'usuari entitat i tots els càrrecs.
   */
  private String nifOrUsername; 

  public AturarPeticionsDeFirmaFilterForm() {    
  }
  
  public AturarPeticionsDeFirmaFilterForm(PeticioDeFirmaFilterForm ffff) {
    super(ffff);
  }

  public String getNifOrUsername() {
    return nifOrUsername;
  }

  public void setNifOrUsername(String nifOrUsername) {
    this.nifOrUsername = nifOrUsername;
  }

}
