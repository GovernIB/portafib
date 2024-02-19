
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


  private java.lang.String nomPlantilla;

  public java.lang.String getNomPlantilla() {
    return this.nomPlantilla;
  }

  public void setNomPlantilla(java.lang.String nomPlantilla) {
    this.nomPlantilla = nomPlantilla;
  }


  private java.lang.String custodiaDocumentID;

  public java.lang.String getCustodiaDocumentID() {
    return this.custodiaDocumentID;
  }

  public void setCustodiaDocumentID(java.lang.String custodiaDocumentID) {
    this.custodiaDocumentID = custodiaDocumentID;
  }


  private java.lang.Long pluginIDDesde;

  public java.lang.Long getPluginIDDesde() {
    return this.pluginIDDesde;
  }

  public void setPluginIDDesde(java.lang.Long pluginIDDesde) {
    this.pluginIDDesde = pluginIDDesde;
  }


  private java.lang.Long pluginIDFins;

  public java.lang.Long getPluginIDFins() {
    return this.pluginIDFins;
  }

  public void setPluginIDFins(java.lang.Long pluginIDFins) {
    this.pluginIDFins = pluginIDFins;
  }


  private java.lang.String custodiaPluginParameters;

  public java.lang.String getCustodiaPluginParameters() {
    return this.custodiaPluginParameters;
  }

  public void setCustodiaPluginParameters(java.lang.String custodiaPluginParameters) {
    this.custodiaPluginParameters = custodiaPluginParameters;
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


  private java.util.List<java.lang.Long> missatgePosicioPaginaIDSelect;

  public java.util.List<java.lang.Long> getMissatgePosicioPaginaIDSelect() {
    return this.missatgePosicioPaginaIDSelect;
  }

  public void setMissatgePosicioPaginaIDSelect(java.util.List<java.lang.Long> missatgePosicioPaginaIDSelect) {
    this.missatgePosicioPaginaIDSelect = missatgePosicioPaginaIDSelect;
  }


  private java.lang.String codiBarresID;

  public java.lang.String getCodiBarresID() {
    return this.codiBarresID;
  }

  public void setCodiBarresID(java.lang.String codiBarresID) {
    this.codiBarresID = codiBarresID;
  }


  private java.util.List<java.lang.Long> codiBarresPosicioPaginaIDSelect;

  public java.util.List<java.lang.Long> getCodiBarresPosicioPaginaIDSelect() {
    return this.codiBarresPosicioPaginaIDSelect;
  }

  public void setCodiBarresPosicioPaginaIDSelect(java.util.List<java.lang.Long> codiBarresPosicioPaginaIDSelect) {
    this.codiBarresPosicioPaginaIDSelect = codiBarresPosicioPaginaIDSelect;
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


  private java.lang.String csv;

  public java.lang.String getCsv() {
    return this.csv;
  }

  public void setCsv(java.lang.String csv) {
    this.csv = csv;
  }


  private java.lang.String csvValidationWeb;

  public java.lang.String getCsvValidationWeb() {
    return this.csvValidationWeb;
  }

  public void setCsvValidationWeb(java.lang.String csvValidationWeb) {
    this.csvValidationWeb = csvValidationWeb;
  }


  private java.lang.String csvGenerationDefinition;

  public java.lang.String getCsvGenerationDefinition() {
    return this.csvGenerationDefinition;
  }

  public void setCsvGenerationDefinition(java.lang.String csvGenerationDefinition) {
    this.csvGenerationDefinition = csvGenerationDefinition;
  }


  private java.lang.String urlFitxerCustodiat;

  public java.lang.String getUrlFitxerCustodiat() {
    return this.urlFitxerCustodiat;
  }

  public void setUrlFitxerCustodiat(java.lang.String urlFitxerCustodiat) {
    this.urlFitxerCustodiat = urlFitxerCustodiat;
  }


  private java.lang.String originalFileDirectUrl;

  public java.lang.String getOriginalFileDirectUrl() {
    return this.originalFileDirectUrl;
  }

  public void setOriginalFileDirectUrl(java.lang.String originalFileDirectUrl) {
    this.originalFileDirectUrl = originalFileDirectUrl;
  }


  private java.lang.String printableFileDirectUrl;

  public java.lang.String getPrintableFileDirectUrl() {
    return this.printableFileDirectUrl;
  }

  public void setPrintableFileDirectUrl(java.lang.String printableFileDirectUrl) {
    this.printableFileDirectUrl = printableFileDirectUrl;
  }


  private java.lang.String eniFileDirectUrl;

  public java.lang.String getEniFileDirectUrl() {
    return this.eniFileDirectUrl;
  }

  public void setEniFileDirectUrl(java.lang.String eniFileDirectUrl) {
    this.eniFileDirectUrl = eniFileDirectUrl;
  }


  private java.lang.String expedientArxiuId;

  public java.lang.String getExpedientArxiuId() {
    return this.expedientArxiuId;
  }

  public void setExpedientArxiuId(java.lang.String expedientArxiuId) {
    this.expedientArxiuId = expedientArxiuId;
  }


  private java.lang.String documentArxiuId;

  public java.lang.String getDocumentArxiuId() {
    return this.documentArxiuId;
  }

  public void setDocumentArxiuId(java.lang.String documentArxiuId) {
    this.documentArxiuId = documentArxiuId;
  }


  public CustodiaInfoFilterForm() {
  }
  
  public CustodiaInfoFilterForm(CustodiaInfoFilterForm __toClone) {
    super(__toClone);
    this.custodiaInfoIDDesde = __toClone.custodiaInfoIDDesde;
    this.custodiaInfoIDFins = __toClone.custodiaInfoIDFins;
    this.nomPlantilla = __toClone.nomPlantilla;
    this.custodiaDocumentID = __toClone.custodiaDocumentID;
    this.pluginIDDesde = __toClone.pluginIDDesde;
    this.pluginIDFins = __toClone.pluginIDFins;
    this.custodiaPluginParameters = __toClone.custodiaPluginParameters;
    this.pagines = __toClone.pagines;
    this.missatge = __toClone.missatge;
    this.missatgePosicioPaginaIDSelect = __toClone.missatgePosicioPaginaIDSelect;
    this.codiBarresID = __toClone.codiBarresID;
    this.codiBarresPosicioPaginaIDSelect = __toClone.codiBarresPosicioPaginaIDSelect;
    this.codiBarresText = __toClone.codiBarresText;
    this.usuariEntitatID = __toClone.usuariEntitatID;
    this.usuariAplicacioID = __toClone.usuariAplicacioID;
    this.entitatID = __toClone.entitatID;
    this.titolPeticio = __toClone.titolPeticio;
    this.dataCustodiaDesde = __toClone.dataCustodiaDesde;
    this.dataCustodiaFins = __toClone.dataCustodiaFins;
    this.csv = __toClone.csv;
    this.csvValidationWeb = __toClone.csvValidationWeb;
    this.csvGenerationDefinition = __toClone.csvGenerationDefinition;
    this.urlFitxerCustodiat = __toClone.urlFitxerCustodiat;
    this.originalFileDirectUrl = __toClone.originalFileDirectUrl;
    this.printableFileDirectUrl = __toClone.printableFileDirectUrl;
    this.eniFileDirectUrl = __toClone.eniFileDirectUrl;
    this.expedientArxiuId = __toClone.expedientArxiuId;
    this.documentArxiuId = __toClone.documentArxiuId;
    this.mapOfPluginForPluginID = __toClone.mapOfPluginForPluginID;
    this.mapOfValuesForMissatgePosicioPaginaID = __toClone.mapOfValuesForMissatgePosicioPaginaID;
    this.mapOfCodiBarresForCodiBarresID = __toClone.mapOfCodiBarresForCodiBarresID;
    this.mapOfValuesForCodiBarresPosicioPaginaID = __toClone.mapOfValuesForCodiBarresPosicioPaginaID;
    this.mapOfUsuariEntitatForUsuariEntitatID = __toClone.mapOfUsuariEntitatForUsuariEntitatID;
    this.mapOfUsuariAplicacioForUsuariAplicacioID = __toClone.mapOfUsuariAplicacioForUsuariAplicacioID;
    this.mapOfEntitatForEntitatID = __toClone.mapOfEntitatForEntitatID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { NOMPLANTILLA ,CUSTODIADOCUMENTID ,TITOLPETICIO ,DATACUSTODIA }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { PLUGINID ,CUSTODIAR ,MISSATGEPOSICIOPAGINAID ,CODIBARRESID ,CODIBARRESPOSICIOPAGINAID ,USUARIENTITATID ,USUARIAPLICACIOID ,ENTITATID ,DATACUSTODIA }));
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
  private Map<String, String> mapOfPluginForPluginID;

  public Map<String, String> getMapOfPluginForPluginID() {
    return this.mapOfPluginForPluginID;
  }

  public void setMapOfPluginForPluginID(Map<String, String> mapOfPluginForPluginID) {
    this.mapOfPluginForPluginID = mapOfPluginForPluginID;
  }



  private Map<String, String> mapOfValuesForMissatgePosicioPaginaID;

  public Map<String, String> getMapOfValuesForMissatgePosicioPaginaID() {
    return this.mapOfValuesForMissatgePosicioPaginaID;
  }

  public void setMapOfValuesForMissatgePosicioPaginaID(Map<String, String> mapOfValuesForMissatgePosicioPaginaID) {
    this.mapOfValuesForMissatgePosicioPaginaID = mapOfValuesForMissatgePosicioPaginaID;
  }



  private Map<String, String> mapOfCodiBarresForCodiBarresID;

  public Map<String, String> getMapOfCodiBarresForCodiBarresID() {
    return this.mapOfCodiBarresForCodiBarresID;
  }

  public void setMapOfCodiBarresForCodiBarresID(Map<String, String> mapOfCodiBarresForCodiBarresID) {
    this.mapOfCodiBarresForCodiBarresID = mapOfCodiBarresForCodiBarresID;
  }



  private Map<String, String> mapOfValuesForCodiBarresPosicioPaginaID;

  public Map<String, String> getMapOfValuesForCodiBarresPosicioPaginaID() {
    return this.mapOfValuesForCodiBarresPosicioPaginaID;
  }

  public void setMapOfValuesForCodiBarresPosicioPaginaID(Map<String, String> mapOfValuesForCodiBarresPosicioPaginaID) {
    this.mapOfValuesForCodiBarresPosicioPaginaID = mapOfValuesForCodiBarresPosicioPaginaID;
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
   };

}
