package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.UsuariEntitatFavoritJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class UsuariEntitatFavoritForm extends PortaFIBBaseForm {
  
  private UsuariEntitatFavoritJPA usuariEntitatFavorit;
  
  public UsuariEntitatFavoritForm() {
  }
  
  public UsuariEntitatFavoritForm(UsuariEntitatFavoritForm __toClone) {
    super(__toClone);
      this.usuariEntitatFavorit = __toClone.usuariEntitatFavorit;
    this.listOfUsuariEntitatForOrigenID = __toClone.listOfUsuariEntitatForOrigenID;
    this.listOfUsuariEntitatForFavoritID = __toClone.listOfUsuariEntitatForFavoritID;
  }
  
  public UsuariEntitatFavoritForm(UsuariEntitatFavoritJPA usuariEntitatFavorit, boolean nou) {
    super(nou);
    this.usuariEntitatFavorit = usuariEntitatFavorit;
  }
  
  public UsuariEntitatFavoritJPA getUsuariEntitatFavorit() {
    return usuariEntitatFavorit;
  }
  public void setUsuariEntitatFavorit(UsuariEntitatFavoritJPA usuariEntitatFavorit) {
    this.usuariEntitatFavorit = usuariEntitatFavorit;
  }
  
  
  private List<StringKeyValue> listOfUsuariEntitatForOrigenID;

  public List<StringKeyValue> getListOfUsuariEntitatForOrigenID() {
    return this.listOfUsuariEntitatForOrigenID;
  }

  public void setListOfUsuariEntitatForOrigenID(List<StringKeyValue> listOfUsuariEntitatForOrigenID) {
    this.listOfUsuariEntitatForOrigenID = listOfUsuariEntitatForOrigenID;
  }



  private List<StringKeyValue> listOfUsuariEntitatForFavoritID;

  public List<StringKeyValue> getListOfUsuariEntitatForFavoritID() {
    return this.listOfUsuariEntitatForFavoritID;
  }

  public void setListOfUsuariEntitatForFavoritID(List<StringKeyValue> listOfUsuariEntitatForFavoritID) {
    this.listOfUsuariEntitatForFavoritID = listOfUsuariEntitatForFavoritID;
  }



  
} // Final de Classe 
