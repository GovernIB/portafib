
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.CustodiaInfoFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class CustodiaInfoFilterForm extends PortaFIBBaseFilterForm implements CustodiaInfoFields {

  private java.lang.Long custodiaInfoIDDesde;

  public java.lang.Long getCustodiaInfoIDDesde() {
    return this.custodiaInfoIDDesde;
  }

  public void setCustodiaInfoIDDesde(java.lang.Long custodiaInfoIDDesde) {
    this.custodiaInfoIDDesde = custodiaInfoIDDesde;
  }


  private java.lang.Long custodiaInfoIDFins;

  public java.lang.Long getCustodiaInfoIDFins() {
    return this.custodiaInfoIDFins;
  }

  public void setCustodiaInfoIDFins(java.lang.Long custodiaInfoIDFins) {
    this.custodiaInfoIDFins = custodiaInfoIDFins;
  }


  private java.lang.String custodiaPluginID;

  public java.lang.String getCustodiaPluginID() {
    return this.custodiaPluginID;
  }

  public void setCustodiaPluginID(java.lang.String custodiaPluginID) {
    this.custodiaPluginID = custodiaPluginID;
  }


  private java.lang.String custodiaPluginClassID;

  public java.lang.String getCustodiaPluginClassID() {
    return this.custodiaPluginClassID;
  }

  public void setCustodiaPluginClassID(java.lang.String custodiaPluginClassID) {
    this.custodiaPluginClassID = custodiaPluginClassID;
  }


  private java.lang.String custodiaPluginParameters;

  public java.lang.String getCustodiaPluginParameters() {
    return this.custodiaPluginParameters;
  }

  public void setCustodiaPluginParameters(java.lang.String custodiaPluginParameters) {
    this.custodiaPluginParameters = custodiaPluginParameters;
  }


  private java.lang.String nomPlantilla;

  public java.lang.String getNomPlantilla() {
    return this.nomPlantilla;
  }

  public void setNomPlantilla(java.lang.String nomPlantilla) {
    this.nomPlantilla = nomPlantilla;
  }


  private java.lang.String urlFitxerCustodiat;

  public java.lang.String getUrlFitxerCustodiat() {
    return this.urlFitxerCustodiat;
  }

  public void setUrlFitxerCustodiat(java.lang.String urlFitxerCustodiat) {
    this.urlFitxerCustodiat = urlFitxerCustodiat;
  }


  private java.lang.String pagines;

  public java.lang.String getPagines() {
    return this.pagines;
  }

  public void setPagines(java.lang.String pagines) {
    this.pagines = pagines;
  }


  private java.lang.String missatge;

  public java.lang.String getMissatge() {
    return this.missatge;
  }

  public void setMissatge(java.lang.String missatge) {
    this.missatge = missatge;
  }


  private java.lang.Long missatgePosicioPaginaIDDesde;

  public java.lang.Long getMissatgePosicioPaginaIDDesde() {
    return this.missatgePosicioPaginaIDDesde;
  }

  public void setMissatgePosicioPaginaIDDesde(java.lang.Long missatgePosicioPaginaIDDesde) {
    this.missatgePosicioPaginaIDDesde = missatgePosicioPaginaIDDesde;
  }


  private java.lang.Long missatgePosicioPaginaIDFins;

  public java.lang.Long getMissatgePosicioPaginaIDFins() {
    return this.missatgePosicioPaginaIDFins;
  }

  public void setMissatgePosicioPaginaIDFins(java.lang.Long missatgePosicioPaginaIDFins) {
    this.missatgePosicioPaginaIDFins = missatgePosicioPaginaIDFins;
  }


  private java.lang.String codiBarresID;

  public java.lang.String getCodiBarresID() {
    return this.codiBarresID;
  }

  public void setCodiBarresID(java.lang.String codiBarresID) {
    this.codiBarresID = codiBarresID;
  }


  private java.lang.Long codiBarresPosicioPaginaIDDesde;

  public java.lang.Long getCodiBarresPosicioPaginaIDDesde() {
    return this.codiBarresPosicioPaginaIDDesde;
  }

  public void setCodiBarresPosicioPaginaIDDesde(java.lang.Long codiBarresPosicioPaginaIDDesde) {
    this.codiBarresPosicioPaginaIDDesde = codiBarresPosicioPaginaIDDesde;
  }


  private java.lang.Long codiBarresPosicioPaginaIDFins;

  public java.lang.Long getCodiBarresPosicioPaginaIDFins() {
    return this.codiBarresPosicioPaginaIDFins;
  }

  public void setCodiBarresPosicioPaginaIDFins(java.lang.Long codiBarresPosicioPaginaIDFins) {
    this.codiBarresPosicioPaginaIDFins = codiBarresPosicioPaginaIDFins;
  }


  private java.lang.String codiBarresText;

  public java.lang.String getCodiBarresText() {
    return this.codiBarresText;
  }

  public void setCodiBarresText(java.lang.String codiBarresText) {
    this.codiBarresText = codiBarresText;
  }


  private java.lang.String usuariEntitatID;

  public java.lang.String getUsuariEntitatID() {
    return this.usuariEntitatID;
  }

  public void setUsuariEntitatID(java.lang.String usuariEntitatID) {
    this.usuariEntitatID = usuariEntitatID;
  }


  private java.lang.String usuariAplicacioID;

  public java.lang.String getUsuariAplicacioID() {
    return this.usuariAplicacioID;
  }

  public void setUsuariAplicacioID(java.lang.String usuariAplicacioID) {
    this.usuariAplicacioID = usuariAplicacioID;
  }


  private java.lang.String entitatID;

  public java.lang.String getEntitatID() {
    return this.entitatID;
  }

  public void setEntitatID(java.lang.String entitatID) {
    this.entitatID = entitatID;
  }


  private java.lang.String titolPeticio;

  public java.lang.String getTitolPeticio() {
    return this.titolPeticio;
  }

  public void setTitolPeticio(java.lang.String titolPeticio) {
    this.titolPeticio = titolPeticio;
  }


  private java.sql.Timestamp dataCustodiaDesde;

  public java.sql.Timestamp getDataCustodiaDesde() {
    return this.dataCustodiaDesde;
  }

  public void setDataCustodiaDesde(java.sql.Timestamp dataCustodiaDesde) {
    this.dataCustodiaDesde = dataCustodiaDesde;
  }


  private java.sql.Timestamp dataCustodiaFins;

  public java.sql.Timestamp getDataCustodiaFins() {
    return this.dataCustodiaFins;
  }

  public void setDataCustodiaFins(java.sql.Timestamp dataCustodiaFins) {
    this.dataCustodiaFins = dataCustodiaFins;
  }


  public CustodiaInfoFilterForm() {
  }
  
  public CustodiaInfoFilterForm(CustodiaInfoFilterForm __toClone) {
    super(__toClone);
    this.custodiaInfoIDDesde = __toClone.custodiaInfoIDDesde;
    this.custodiaInfoIDFins = __toClone.custodiaInfoIDFins;
    this.custodiaPluginID = __toClone.custodiaPluginID;
    this.custodiaPluginClassID = __toClone.custodiaPluginClassID;
    this.custodiaPluginParameters = __toClone.custodiaPluginParameters;
    this.nomPlantilla = __toClone.nomPlantilla;
    this.urlFitxerCustodiat = __toClone.urlFitxerCustodiat;
    this.pagines = __toClone.pagines;
    this.missatge = __toClone.missatge;
    this.missatgePosicioPaginaIDDesde = __toClone.missatgePosicioPaginaIDDesde;
    this.missatgePosicioPaginaIDFins = __toClone.missatgePosicioPaginaIDFins;
    this.codiBarresID = __toClone.codiBarresID;
    this.codiBarresPosicioPaginaIDDesde = __toClone.codiBarresPosicioPaginaIDDesde;
    this.codiBarresPosicioPaginaIDFins = __toClone.codiBarresPosicioPaginaIDFins;
    this.codiBarresText = __toClone.codiBarresText;
    this.usuariEntitatID = __toClone.usuariEntitatID;
    this.usuariAplicacioID = __toClone.usuariAplicacioID;
    this.entitatID = __toClone.entitatID;
    this.titolPeticio = __toClone.titolPeticio;
    this.dataCustodiaDesde = __toClone.dataCustodiaDesde;
    this.dataCustodiaFins = __toClone.dataCustodiaFins;
    this.mapOfPosicioPaginaForMissatgePosicioPaginaID = __toClone.mapOfPosicioPaginaForMissatgePosicioPaginaID;
    this.mapOfCodiBarresForCodiBarresID = __toClone.mapOfCodiBarresForCodiBarresID;
    this.mapOfPosicioPaginaForCodiBarresPosicioPaginaID = __toClone.mapOfPosicioPaginaForCodiBarresPosicioPaginaID;
    this.mapOfUsuariEntitatForUsuariEntitatID = __toClone.mapOfUsuariEntitatForUsuariEntitatID;
    this.mapOfUsuariAplicacioForUsuariAplicacioID = __toClone.mapOfUsuariAplicacioForUsuariAplicacioID;
    this.mapOfEntitatForEntitatID = __toClone.mapOfEntitatForEntitatID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { NOMPLANTILLA ,TITOLPETICIO ,DATACUSTODIA }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { CUSTODIAPLUGINCLASSID ,CUSTODIAR ,MISSATGEPOSICIOPAGINAID ,CODIBARRESID ,CODIBARRESPOSICIOPAGINAID ,USUARIENTITATID ,USUARIAPLICACIOID ,ENTITATID ,DATACUSTODIA }));
  }


  protected OrderBy[] defaultOrderBy = null;


  public OrderBy[] getDefaultOrderBy() {
    return this.defaultOrderBy;
  }

  public void setDefaultOrderBy(OrderBy[] defOrderBy) {
    this.defaultOrderBy = defOrderBy;
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

   // -----------------------
   // Maps de referencies.
   // -----------------------
  private Map<String, String> mapOfPosicioPaginaForMissatgePosicioPaginaID;

  public Map<String, String> getMapOfPosicioPaginaForMissatgePosicioPaginaID() {
    return this.mapOfPosicioPaginaForMissatgePosicioPaginaID;
  }

  public void setMapOfPosicioPaginaForMissatgePosicioPaginaID(Map<String, String> mapOfPosicioPaginaForMissatgePosicioPaginaID) {
    this.mapOfPosicioPaginaForMissatgePosicioPaginaID = mapOfPosicioPaginaForMissatgePosicioPaginaID;
  }



  private Map<String, String> mapOfCodiBarresForCodiBarresID;

  public Map<String, String> getMapOfCodiBarresForCodiBarresID() {
    return this.mapOfCodiBarresForCodiBarresID;
  }

  public void setMapOfCodiBarresForCodiBarresID(Map<String, String> mapOfCodiBarresForCodiBarresID) {
    this.mapOfCodiBarresForCodiBarresID = mapOfCodiBarresForCodiBarresID;
  }



  private Map<String, String> mapOfPosicioPaginaForCodiBarresPosicioPaginaID;

  public Map<String, String> getMapOfPosicioPaginaForCodiBarresPosicioPaginaID() {
    return this.mapOfPosicioPaginaForCodiBarresPosicioPaginaID;
  }

  public void setMapOfPosicioPaginaForCodiBarresPosicioPaginaID(Map<String, String> mapOfPosicioPaginaForCodiBarresPosicioPaginaID) {
    this.mapOfPosicioPaginaForCodiBarresPosicioPaginaID = mapOfPosicioPaginaForCodiBarresPosicioPaginaID;
  }



  private Map<String, String> mapOfUsuariEntitatForUsuariEntitatID;

  public Map<String, String> getMapOfUsuariEntitatForUsuariEntitatID() {
    return this.mapOfUsuariEntitatForUsuariEntitatID;
  }

  public void setMapOfUsuariEntitatForUsuariEntitatID(Map<String, String> mapOfUsuariEntitatForUsuariEntitatID) {
    this.mapOfUsuariEntitatForUsuariEntitatID = mapOfUsuariEntitatForUsuariEntitatID;
  }



  private Map<String, String> mapOfUsuariAplicacioForUsuariAplicacioID;

  public Map<String, String> getMapOfUsuariAplicacioForUsuariAplicacioID() {
    return this.mapOfUsuariAplicacioForUsuariAplicacioID;
  }

  public void setMapOfUsuariAplicacioForUsuariAplicacioID(Map<String, String> mapOfUsuariAplicacioForUsuariAplicacioID) {
    this.mapOfUsuariAplicacioForUsuariAplicacioID = mapOfUsuariAplicacioForUsuariAplicacioID;
  }



  private Map<String, String> mapOfEntitatForEntitatID;

  public Map<String, String> getMapOfEntitatForEntitatID() {
    return this.mapOfEntitatForEntitatID;
  }

  public void setMapOfEntitatForEntitatID(Map<String, String> mapOfEntitatForEntitatID) {
    this.mapOfEntitatForEntitatID = mapOfEntitatForEntitatID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   traduibles.add(MISSATGEPOSICIOPAGINAID.javaName);
   traduibles.add(CODIBARRESPOSICIOPAGINAID.javaName);
   };

}
