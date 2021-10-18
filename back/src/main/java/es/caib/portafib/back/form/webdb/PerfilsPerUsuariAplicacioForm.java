package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.persistence.PerfilsPerUsuariAplicacioJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class PerfilsPerUsuariAplicacioForm extends PortaFIBBaseForm {
  
  private PerfilsPerUsuariAplicacioJPA perfilsPerUsuariAplicacio;
  
  public PerfilsPerUsuariAplicacioForm() {
  }
  
  public PerfilsPerUsuariAplicacioForm(PerfilsPerUsuariAplicacioForm __toClone) {
    super(__toClone);
      this.perfilsPerUsuariAplicacio = __toClone.perfilsPerUsuariAplicacio;
    this.listOfPerfilDeFirmaForPerfilDeFirmaID = __toClone.listOfPerfilDeFirmaForPerfilDeFirmaID;
    this.listOfUsuariAplicacioForUsuariAplicacioID = __toClone.listOfUsuariAplicacioForUsuariAplicacioID;
  }
  
  public PerfilsPerUsuariAplicacioForm(PerfilsPerUsuariAplicacioJPA perfilsPerUsuariAplicacio, boolean nou) {
    super(nou);
    this.perfilsPerUsuariAplicacio = perfilsPerUsuariAplicacio;
  }
  
  public PerfilsPerUsuariAplicacioJPA getPerfilsPerUsuariAplicacio() {
    return perfilsPerUsuariAplicacio;
  }
  public void setPerfilsPerUsuariAplicacio(PerfilsPerUsuariAplicacioJPA perfilsPerUsuariAplicacio) {
    this.perfilsPerUsuariAplicacio = perfilsPerUsuariAplicacio;
  }
  
  
  private List<StringKeyValue> listOfPerfilDeFirmaForPerfilDeFirmaID;

  public List<StringKeyValue> getListOfPerfilDeFirmaForPerfilDeFirmaID() {
    return this.listOfPerfilDeFirmaForPerfilDeFirmaID;
  }

  public void setListOfPerfilDeFirmaForPerfilDeFirmaID(List<StringKeyValue> listOfPerfilDeFirmaForPerfilDeFirmaID) {
    this.listOfPerfilDeFirmaForPerfilDeFirmaID = listOfPerfilDeFirmaForPerfilDeFirmaID;
  }



  private List<StringKeyValue> listOfUsuariAplicacioForUsuariAplicacioID;

  public List<StringKeyValue> getListOfUsuariAplicacioForUsuariAplicacioID() {
    return this.listOfUsuariAplicacioForUsuariAplicacioID;
  }

  public void setListOfUsuariAplicacioForUsuariAplicacioID(List<StringKeyValue> listOfUsuariAplicacioForUsuariAplicacioID) {
    this.listOfUsuariAplicacioForUsuariAplicacioID = listOfUsuariAplicacioForUsuariAplicacioID;
  }



  
} // Final de Classe 
