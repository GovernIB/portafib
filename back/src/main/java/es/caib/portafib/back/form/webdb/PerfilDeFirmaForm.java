package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.jpa.PerfilDeFirmaJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class PerfilDeFirmaForm extends PortaFIBBaseForm {
  
  private PerfilDeFirmaJPA perfilDeFirma;
  
  public PerfilDeFirmaForm() {
  }
  
  public PerfilDeFirmaForm(PerfilDeFirmaForm __toClone) {
    super(__toClone);
      this.perfilDeFirma = __toClone.perfilDeFirma;
    this.listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma1ID = __toClone.listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma1ID;
    this.listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma2ID = __toClone.listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma2ID;
    this.listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma3ID = __toClone.listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma3ID;
  }
  
  public PerfilDeFirmaForm(PerfilDeFirmaJPA perfilDeFirma, boolean nou) {
    super(nou);
    this.perfilDeFirma = perfilDeFirma;
  }
  
  public PerfilDeFirmaJPA getPerfilDeFirma() {
    return perfilDeFirma;
  }
  public void setPerfilDeFirma(PerfilDeFirmaJPA perfilDeFirma) {
    this.perfilDeFirma = perfilDeFirma;
  }
  
  
  private List<StringKeyValue> listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma1ID;

  public List<StringKeyValue> getListOfUsuariAplicacioConfiguracioForConfiguracioDeFirma1ID() {
    return this.listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma1ID;
  }

  public void setListOfUsuariAplicacioConfiguracioForConfiguracioDeFirma1ID(List<StringKeyValue> listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma1ID) {
    this.listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma1ID = listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma1ID;
  }



  private List<StringKeyValue> listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma2ID;

  public List<StringKeyValue> getListOfUsuariAplicacioConfiguracioForConfiguracioDeFirma2ID() {
    return this.listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma2ID;
  }

  public void setListOfUsuariAplicacioConfiguracioForConfiguracioDeFirma2ID(List<StringKeyValue> listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma2ID) {
    this.listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma2ID = listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma2ID;
  }



  private List<StringKeyValue> listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma3ID;

  public List<StringKeyValue> getListOfUsuariAplicacioConfiguracioForConfiguracioDeFirma3ID() {
    return this.listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma3ID;
  }

  public void setListOfUsuariAplicacioConfiguracioForConfiguracioDeFirma3ID(List<StringKeyValue> listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma3ID) {
    this.listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma3ID = listOfUsuariAplicacioConfiguracioForConfiguracioDeFirma3ID;
  }



  
} // Final de Classe 
