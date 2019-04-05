package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.UsuariAplicacioJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class UsuariAplicacioForm extends PortaFIBBaseForm {
  
  private UsuariAplicacioJPA usuariAplicacio;
  
  
  private CommonsMultipartFile logoSegellID;
  private boolean logoSegellIDDelete;
  
  public UsuariAplicacioForm() {
  }
  
  public UsuariAplicacioForm(UsuariAplicacioForm __toClone) {
    super(__toClone);
      this.usuariAplicacio = __toClone.usuariAplicacio;
    this.listOfEntitatForEntitatID = __toClone.listOfEntitatForEntitatID;
    this.listOfValuesForCallbackVersio = __toClone.listOfValuesForCallbackVersio;
    this.listOfIdiomaForIdiomaID = __toClone.listOfIdiomaForIdiomaID;
    this.listOfValuesForPoliticaDePluginFirmaWeb = __toClone.listOfValuesForPoliticaDePluginFirmaWeb;
    this.listOfValuesForPoliticaCustodia = __toClone.listOfValuesForPoliticaCustodia;
    this.listOfCustodiaInfoForCustodiaInfoID = __toClone.listOfCustodiaInfoForCustodiaInfoID;
  }
  
  public UsuariAplicacioForm(UsuariAplicacioJPA usuariAplicacio, boolean nou) {
    super(nou);
    this.usuariAplicacio = usuariAplicacio;
  }
  
  public UsuariAplicacioJPA getUsuariAplicacio() {
    return usuariAplicacio;
  }
  public void setUsuariAplicacio(UsuariAplicacioJPA usuariAplicacio) {
    this.usuariAplicacio = usuariAplicacio;
  }
  
  
  public CommonsMultipartFile getLogoSegellID() {
    return logoSegellID;
  }
  
   public void setLogoSegellID(CommonsMultipartFile logoSegellID) {
    this.logoSegellID = logoSegellID;
  }
  public boolean isLogoSegellIDDelete() {
    return logoSegellIDDelete;
  }
  
  public void setLogoSegellIDDelete(boolean logoSegellIDDelete) {
    this.logoSegellIDDelete = logoSegellIDDelete;
   }
  private List<StringKeyValue> listOfEntitatForEntitatID;

  public List<StringKeyValue> getListOfEntitatForEntitatID() {
    return this.listOfEntitatForEntitatID;
  }

  public void setListOfEntitatForEntitatID(List<StringKeyValue> listOfEntitatForEntitatID) {
    this.listOfEntitatForEntitatID = listOfEntitatForEntitatID;
  }



  private List<StringKeyValue> listOfValuesForCallbackVersio;

  public List<StringKeyValue> getListOfValuesForCallbackVersio() {
    return this.listOfValuesForCallbackVersio;
  }

  public void setListOfValuesForCallbackVersio(List<StringKeyValue> listOfValuesForCallbackVersio) {
    this.listOfValuesForCallbackVersio = listOfValuesForCallbackVersio;
  }



  private List<StringKeyValue> listOfIdiomaForIdiomaID;

  public List<StringKeyValue> getListOfIdiomaForIdiomaID() {
    return this.listOfIdiomaForIdiomaID;
  }

  public void setListOfIdiomaForIdiomaID(List<StringKeyValue> listOfIdiomaForIdiomaID) {
    this.listOfIdiomaForIdiomaID = listOfIdiomaForIdiomaID;
  }



  private List<StringKeyValue> listOfValuesForPoliticaDePluginFirmaWeb;

  public List<StringKeyValue> getListOfValuesForPoliticaDePluginFirmaWeb() {
    return this.listOfValuesForPoliticaDePluginFirmaWeb;
  }

  public void setListOfValuesForPoliticaDePluginFirmaWeb(List<StringKeyValue> listOfValuesForPoliticaDePluginFirmaWeb) {
    this.listOfValuesForPoliticaDePluginFirmaWeb = listOfValuesForPoliticaDePluginFirmaWeb;
  }



  private List<StringKeyValue> listOfValuesForPoliticaCustodia;

  public List<StringKeyValue> getListOfValuesForPoliticaCustodia() {
    return this.listOfValuesForPoliticaCustodia;
  }

  public void setListOfValuesForPoliticaCustodia(List<StringKeyValue> listOfValuesForPoliticaCustodia) {
    this.listOfValuesForPoliticaCustodia = listOfValuesForPoliticaCustodia;
  }



  private List<StringKeyValue> listOfCustodiaInfoForCustodiaInfoID;

  public List<StringKeyValue> getListOfCustodiaInfoForCustodiaInfoID() {
    return this.listOfCustodiaInfoForCustodiaInfoID;
  }

  public void setListOfCustodiaInfoForCustodiaInfoID(List<StringKeyValue> listOfCustodiaInfoForCustodiaInfoID) {
    this.listOfCustodiaInfoForCustodiaInfoID = listOfCustodiaInfoForCustodiaInfoID;
  }



  
} // Final de Classe 
