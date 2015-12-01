package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.CustodiaInfoJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class CustodiaInfoForm extends PortaFIBBaseForm {
  
  private CustodiaInfoJPA custodiaInfo;
  
  public CustodiaInfoForm() {
  }
  
  public CustodiaInfoForm(CustodiaInfoForm __toClone) {
    super(__toClone);
      this.custodiaInfo = __toClone.custodiaInfo;
    this.listOfPluginForPluginID = __toClone.listOfPluginForPluginID;
    this.listOfPosicioPaginaForMissatgePosicioPaginaID = __toClone.listOfPosicioPaginaForMissatgePosicioPaginaID;
    this.listOfCodiBarresForCodiBarresID = __toClone.listOfCodiBarresForCodiBarresID;
    this.listOfPosicioPaginaForCodiBarresPosicioPaginaID = __toClone.listOfPosicioPaginaForCodiBarresPosicioPaginaID;
    this.listOfUsuariEntitatForUsuariEntitatID = __toClone.listOfUsuariEntitatForUsuariEntitatID;
    this.listOfUsuariAplicacioForUsuariAplicacioID = __toClone.listOfUsuariAplicacioForUsuariAplicacioID;
    this.listOfEntitatForEntitatID = __toClone.listOfEntitatForEntitatID;
  }
  
  public CustodiaInfoForm(CustodiaInfoJPA custodiaInfo, boolean nou) {
    super(nou);
    this.custodiaInfo = custodiaInfo;
  }
  
  public CustodiaInfoJPA getCustodiaInfo() {
    return custodiaInfo;
  }
  public void setCustodiaInfo(CustodiaInfoJPA custodiaInfo) {
    this.custodiaInfo = custodiaInfo;
  }
  
  
  private List<StringKeyValue> listOfPluginForPluginID;

  public List<StringKeyValue> getListOfPluginForPluginID() {
    return this.listOfPluginForPluginID;
  }

  public void setListOfPluginForPluginID(List<StringKeyValue> listOfPluginForPluginID) {
    this.listOfPluginForPluginID = listOfPluginForPluginID;
  }



  private List<StringKeyValue> listOfPosicioPaginaForMissatgePosicioPaginaID;

  public List<StringKeyValue> getListOfPosicioPaginaForMissatgePosicioPaginaID() {
    return this.listOfPosicioPaginaForMissatgePosicioPaginaID;
  }

  public void setListOfPosicioPaginaForMissatgePosicioPaginaID(List<StringKeyValue> listOfPosicioPaginaForMissatgePosicioPaginaID) {
    this.listOfPosicioPaginaForMissatgePosicioPaginaID = listOfPosicioPaginaForMissatgePosicioPaginaID;
  }



  private List<StringKeyValue> listOfCodiBarresForCodiBarresID;

  public List<StringKeyValue> getListOfCodiBarresForCodiBarresID() {
    return this.listOfCodiBarresForCodiBarresID;
  }

  public void setListOfCodiBarresForCodiBarresID(List<StringKeyValue> listOfCodiBarresForCodiBarresID) {
    this.listOfCodiBarresForCodiBarresID = listOfCodiBarresForCodiBarresID;
  }



  private List<StringKeyValue> listOfPosicioPaginaForCodiBarresPosicioPaginaID;

  public List<StringKeyValue> getListOfPosicioPaginaForCodiBarresPosicioPaginaID() {
    return this.listOfPosicioPaginaForCodiBarresPosicioPaginaID;
  }

  public void setListOfPosicioPaginaForCodiBarresPosicioPaginaID(List<StringKeyValue> listOfPosicioPaginaForCodiBarresPosicioPaginaID) {
    this.listOfPosicioPaginaForCodiBarresPosicioPaginaID = listOfPosicioPaginaForCodiBarresPosicioPaginaID;
  }



  private List<StringKeyValue> listOfUsuariEntitatForUsuariEntitatID;

  public List<StringKeyValue> getListOfUsuariEntitatForUsuariEntitatID() {
    return this.listOfUsuariEntitatForUsuariEntitatID;
  }

  public void setListOfUsuariEntitatForUsuariEntitatID(List<StringKeyValue> listOfUsuariEntitatForUsuariEntitatID) {
    this.listOfUsuariEntitatForUsuariEntitatID = listOfUsuariEntitatForUsuariEntitatID;
  }



  private List<StringKeyValue> listOfUsuariAplicacioForUsuariAplicacioID;

  public List<StringKeyValue> getListOfUsuariAplicacioForUsuariAplicacioID() {
    return this.listOfUsuariAplicacioForUsuariAplicacioID;
  }

  public void setListOfUsuariAplicacioForUsuariAplicacioID(List<StringKeyValue> listOfUsuariAplicacioForUsuariAplicacioID) {
    this.listOfUsuariAplicacioForUsuariAplicacioID = listOfUsuariAplicacioForUsuariAplicacioID;
  }



  private List<StringKeyValue> listOfEntitatForEntitatID;

  public List<StringKeyValue> getListOfEntitatForEntitatID() {
    return this.listOfEntitatForEntitatID;
  }

  public void setListOfEntitatForEntitatID(List<StringKeyValue> listOfEntitatForEntitatID) {
    this.listOfEntitatForEntitatID = listOfEntitatForEntitatID;
  }



  
} // Final de Classe 
