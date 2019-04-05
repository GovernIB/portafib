package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.UsuariEntitatJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class UsuariEntitatForm extends PortaFIBBaseForm {
  
  private UsuariEntitatJPA usuariEntitat;
  
  
  private CommonsMultipartFile logoSegellID;
  private boolean logoSegellIDDelete;
  
  public UsuariEntitatForm() {
  }
  
  public UsuariEntitatForm(UsuariEntitatForm __toClone) {
    super(__toClone);
      this.usuariEntitat = __toClone.usuariEntitat;
    this.listOfUsuariPersonaForUsuariPersonaID = __toClone.listOfUsuariPersonaForUsuariPersonaID;
    this.listOfEntitatForEntitatID = __toClone.listOfEntitatForEntitatID;
    this.listOfValuesForPoliticaDePluginFirmaWeb = __toClone.listOfValuesForPoliticaDePluginFirmaWeb;
    this.listOfValuesForPoliticaCustodia = __toClone.listOfValuesForPoliticaCustodia;
    this.listOfCustodiaInfoForCustodiaInfoID = __toClone.listOfCustodiaInfoForCustodiaInfoID;
  }
  
  public UsuariEntitatForm(UsuariEntitatJPA usuariEntitat, boolean nou) {
    super(nou);
    this.usuariEntitat = usuariEntitat;
  }
  
  public UsuariEntitatJPA getUsuariEntitat() {
    return usuariEntitat;
  }
  public void setUsuariEntitat(UsuariEntitatJPA usuariEntitat) {
    this.usuariEntitat = usuariEntitat;
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
  private List<StringKeyValue> listOfUsuariPersonaForUsuariPersonaID;

  public List<StringKeyValue> getListOfUsuariPersonaForUsuariPersonaID() {
    return this.listOfUsuariPersonaForUsuariPersonaID;
  }

  public void setListOfUsuariPersonaForUsuariPersonaID(List<StringKeyValue> listOfUsuariPersonaForUsuariPersonaID) {
    this.listOfUsuariPersonaForUsuariPersonaID = listOfUsuariPersonaForUsuariPersonaID;
  }



  private List<StringKeyValue> listOfEntitatForEntitatID;

  public List<StringKeyValue> getListOfEntitatForEntitatID() {
    return this.listOfEntitatForEntitatID;
  }

  public void setListOfEntitatForEntitatID(List<StringKeyValue> listOfEntitatForEntitatID) {
    this.listOfEntitatForEntitatID = listOfEntitatForEntitatID;
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
