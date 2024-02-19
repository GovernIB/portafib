
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.PeticioDeFirmaFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class PeticioDeFirmaFilterForm extends PortaFIBBaseFilterForm implements PeticioDeFirmaFields {

  private java.lang.Long peticioDeFirmaIDDesde;

  public java.lang.Long getPeticioDeFirmaIDDesde() {
    return this.peticioDeFirmaIDDesde;
  }

  public void setPeticioDeFirmaIDDesde(java.lang.Long peticioDeFirmaIDDesde) {
    this.peticioDeFirmaIDDesde = peticioDeFirmaIDDesde;
  }


  private java.lang.Long peticioDeFirmaIDFins;

  public java.lang.Long getPeticioDeFirmaIDFins() {
    return this.peticioDeFirmaIDFins;
  }

  public void setPeticioDeFirmaIDFins(java.lang.Long peticioDeFirmaIDFins) {
    this.peticioDeFirmaIDFins = peticioDeFirmaIDFins;
  }


  private java.lang.String titol;

  public java.lang.String getTitol() {
    return this.titol;
  }

  public void setTitol(java.lang.String titol) {
    this.titol = titol;
  }


  private java.lang.String descripcio;

  public java.lang.String getDescripcio() {
    return this.descripcio;
  }

  public void setDescripcio(java.lang.String descripcio) {
    this.descripcio = descripcio;
  }


  private java.lang.String motiu;

  public java.lang.String getMotiu() {
    return this.motiu;
  }

  public void setMotiu(java.lang.String motiu) {
    this.motiu = motiu;
  }


  private java.lang.Long tipusDocumentIDDesde;

  public java.lang.Long getTipusDocumentIDDesde() {
    return this.tipusDocumentIDDesde;
  }

  public void setTipusDocumentIDDesde(java.lang.Long tipusDocumentIDDesde) {
    this.tipusDocumentIDDesde = tipusDocumentIDDesde;
  }


  private java.lang.Long tipusDocumentIDFins;

  public java.lang.Long getTipusDocumentIDFins() {
    return this.tipusDocumentIDFins;
  }

  public void setTipusDocumentIDFins(java.lang.Long tipusDocumentIDFins) {
    this.tipusDocumentIDFins = tipusDocumentIDFins;
  }


  private java.lang.String descripcioTipusDocument;

  public java.lang.String getDescripcioTipusDocument() {
    return this.descripcioTipusDocument;
  }

  public void setDescripcioTipusDocument(java.lang.String descripcioTipusDocument) {
    this.descripcioTipusDocument = descripcioTipusDocument;
  }


  private java.sql.Timestamp dataSolicitudDesde;

  public java.sql.Timestamp getDataSolicitudDesde() {
    return this.dataSolicitudDesde;
  }

  public void setDataSolicitudDesde(java.sql.Timestamp dataSolicitudDesde) {
    this.dataSolicitudDesde = dataSolicitudDesde;
  }


  private java.sql.Timestamp dataSolicitudFins;

  public java.sql.Timestamp getDataSolicitudFins() {
    return this.dataSolicitudFins;
  }

  public void setDataSolicitudFins(java.sql.Timestamp dataSolicitudFins) {
    this.dataSolicitudFins = dataSolicitudFins;
  }


  private java.sql.Timestamp dataFinalDesde;

  public java.sql.Timestamp getDataFinalDesde() {
    return this.dataFinalDesde;
  }

  public void setDataFinalDesde(java.sql.Timestamp dataFinalDesde) {
    this.dataFinalDesde = dataFinalDesde;
  }


  private java.sql.Timestamp dataFinalFins;

  public java.sql.Timestamp getDataFinalFins() {
    return this.dataFinalFins;
  }

  public void setDataFinalFins(java.sql.Timestamp dataFinalFins) {
    this.dataFinalFins = dataFinalFins;
  }


  private java.sql.Timestamp dataCaducitatDesde;

  public java.sql.Timestamp getDataCaducitatDesde() {
    return this.dataCaducitatDesde;
  }

  public void setDataCaducitatDesde(java.sql.Timestamp dataCaducitatDesde) {
    this.dataCaducitatDesde = dataCaducitatDesde;
  }


  private java.sql.Timestamp dataCaducitatFins;

  public java.sql.Timestamp getDataCaducitatFins() {
    return this.dataCaducitatFins;
  }

  public void setDataCaducitatFins(java.sql.Timestamp dataCaducitatFins) {
    this.dataCaducitatFins = dataCaducitatFins;
  }


  private java.util.List<java.lang.Integer> tipusOperacioFirmaSelect;

  public java.util.List<java.lang.Integer> getTipusOperacioFirmaSelect() {
    return this.tipusOperacioFirmaSelect;
  }

  public void setTipusOperacioFirmaSelect(java.util.List<java.lang.Integer> tipusOperacioFirmaSelect) {
    this.tipusOperacioFirmaSelect = tipusOperacioFirmaSelect;
  }


  private java.util.List<java.lang.Integer> tipusFirmaIDSelect;

  public java.util.List<java.lang.Integer> getTipusFirmaIDSelect() {
    return this.tipusFirmaIDSelect;
  }

  public void setTipusFirmaIDSelect(java.util.List<java.lang.Integer> tipusFirmaIDSelect) {
    this.tipusFirmaIDSelect = tipusFirmaIDSelect;
  }


  private java.util.List<java.lang.Integer> algorismeDeFirmaIDSelect;

  public java.util.List<java.lang.Integer> getAlgorismeDeFirmaIDSelect() {
    return this.algorismeDeFirmaIDSelect;
  }

  public void setAlgorismeDeFirmaIDSelect(java.util.List<java.lang.Integer> algorismeDeFirmaIDSelect) {
    this.algorismeDeFirmaIDSelect = algorismeDeFirmaIDSelect;
  }


  private java.util.List<java.lang.Integer> posicioTaulaFirmesIDSelect;

  public java.util.List<java.lang.Integer> getPosicioTaulaFirmesIDSelect() {
    return this.posicioTaulaFirmesIDSelect;
  }

  public void setPosicioTaulaFirmesIDSelect(java.util.List<java.lang.Integer> posicioTaulaFirmesIDSelect) {
    this.posicioTaulaFirmesIDSelect = posicioTaulaFirmesIDSelect;
  }


  private java.util.List<java.lang.Integer> tipusEstatPeticioDeFirmaIDSelect;

  public java.util.List<java.lang.Integer> getTipusEstatPeticioDeFirmaIDSelect() {
    return this.tipusEstatPeticioDeFirmaIDSelect;
  }

  public void setTipusEstatPeticioDeFirmaIDSelect(java.util.List<java.lang.Integer> tipusEstatPeticioDeFirmaIDSelect) {
    this.tipusEstatPeticioDeFirmaIDSelect = tipusEstatPeticioDeFirmaIDSelect;
  }


  private java.lang.String motiuDeRebuig;

  public java.lang.String getMotiuDeRebuig() {
    return this.motiuDeRebuig;
  }

  public void setMotiuDeRebuig(java.lang.String motiuDeRebuig) {
    this.motiuDeRebuig = motiuDeRebuig;
  }


  private java.lang.String idiomaID;

  public java.lang.String getIdiomaID() {
    return this.idiomaID;
  }

  public void setIdiomaID(java.lang.String idiomaID) {
    this.idiomaID = idiomaID;
  }


  private java.util.List<java.lang.Integer> prioritatIDSelect;

  public java.util.List<java.lang.Integer> getPrioritatIDSelect() {
    return this.prioritatIDSelect;
  }

  public void setPrioritatIDSelect(java.util.List<java.lang.Integer> prioritatIDSelect) {
    this.prioritatIDSelect = prioritatIDSelect;
  }


  private java.lang.Long fluxDeFirmesIDDesde;

  public java.lang.Long getFluxDeFirmesIDDesde() {
    return this.fluxDeFirmesIDDesde;
  }

  public void setFluxDeFirmesIDDesde(java.lang.Long fluxDeFirmesIDDesde) {
    this.fluxDeFirmesIDDesde = fluxDeFirmesIDDesde;
  }


  private java.lang.Long fluxDeFirmesIDFins;

  public java.lang.Long getFluxDeFirmesIDFins() {
    return this.fluxDeFirmesIDFins;
  }

  public void setFluxDeFirmesIDFins(java.lang.Long fluxDeFirmesIDFins) {
    this.fluxDeFirmesIDFins = fluxDeFirmesIDFins;
  }


  private java.lang.String solicitantUsuariAplicacioID;

  public java.lang.String getSolicitantUsuariAplicacioID() {
    return this.solicitantUsuariAplicacioID;
  }

  public void setSolicitantUsuariAplicacioID(java.lang.String solicitantUsuariAplicacioID) {
    this.solicitantUsuariAplicacioID = solicitantUsuariAplicacioID;
  }


  private java.lang.String remitentNom;

  public java.lang.String getRemitentNom() {
    return this.remitentNom;
  }

  public void setRemitentNom(java.lang.String remitentNom) {
    this.remitentNom = remitentNom;
  }


  private java.lang.String remitentDescripcio;

  public java.lang.String getRemitentDescripcio() {
    return this.remitentDescripcio;
  }

  public void setRemitentDescripcio(java.lang.String remitentDescripcio) {
    this.remitentDescripcio = remitentDescripcio;
  }


  private java.lang.String expedientCodi;

  public java.lang.String getExpedientCodi() {
    return this.expedientCodi;
  }

  public void setExpedientCodi(java.lang.String expedientCodi) {
    this.expedientCodi = expedientCodi;
  }


  private java.lang.String expedientNom;

  public java.lang.String getExpedientNom() {
    return this.expedientNom;
  }

  public void setExpedientNom(java.lang.String expedientNom) {
    this.expedientNom = expedientNom;
  }


  private java.lang.String expedientUrl;

  public java.lang.String getExpedientUrl() {
    return this.expedientUrl;
  }

  public void setExpedientUrl(java.lang.String expedientUrl) {
    this.expedientUrl = expedientUrl;
  }


  private java.lang.String procedimentCodi;

  public java.lang.String getProcedimentCodi() {
    return this.procedimentCodi;
  }

  public void setProcedimentCodi(java.lang.String procedimentCodi) {
    this.procedimentCodi = procedimentCodi;
  }


  private java.lang.String procedimentNom;

  public java.lang.String getProcedimentNom() {
    return this.procedimentNom;
  }

  public void setProcedimentNom(java.lang.String procedimentNom) {
    this.procedimentNom = procedimentNom;
  }


  private java.lang.String informacioAddicional;

  public java.lang.String getInformacioAddicional() {
    return this.informacioAddicional;
  }

  public void setInformacioAddicional(java.lang.String informacioAddicional) {
    this.informacioAddicional = informacioAddicional;
  }


  private java.lang.Double informacioAddicionalAvaluableDesde;

  public java.lang.Double getInformacioAddicionalAvaluableDesde() {
    return this.informacioAddicionalAvaluableDesde;
  }

  public void setInformacioAddicionalAvaluableDesde(java.lang.Double informacioAddicionalAvaluableDesde) {
    this.informacioAddicionalAvaluableDesde = informacioAddicionalAvaluableDesde;
  }


  private java.lang.Double informacioAddicionalAvaluableFins;

  public java.lang.Double getInformacioAddicionalAvaluableFins() {
    return this.informacioAddicionalAvaluableFins;
  }

  public void setInformacioAddicionalAvaluableFins(java.lang.Double informacioAddicionalAvaluableFins) {
    this.informacioAddicionalAvaluableFins = informacioAddicionalAvaluableFins;
  }


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


  private java.lang.String solicitantUsuariEntitat1ID;

  public java.lang.String getSolicitantUsuariEntitat1ID() {
    return this.solicitantUsuariEntitat1ID;
  }

  public void setSolicitantUsuariEntitat1ID(java.lang.String solicitantUsuariEntitat1ID) {
    this.solicitantUsuariEntitat1ID = solicitantUsuariEntitat1ID;
  }


  private java.lang.String solicitantUsuariEntitat2ID;

  public java.lang.String getSolicitantUsuariEntitat2ID() {
    return this.solicitantUsuariEntitat2ID;
  }

  public void setSolicitantUsuariEntitat2ID(java.lang.String solicitantUsuariEntitat2ID) {
    this.solicitantUsuariEntitat2ID = solicitantUsuariEntitat2ID;
  }


  private java.lang.String solicitantUsuariEntitat3ID;

  public java.lang.String getSolicitantUsuariEntitat3ID() {
    return this.solicitantUsuariEntitat3ID;
  }

  public void setSolicitantUsuariEntitat3ID(java.lang.String solicitantUsuariEntitat3ID) {
    this.solicitantUsuariEntitat3ID = solicitantUsuariEntitat3ID;
  }


  private java.util.List<java.lang.Integer> origenPeticioDeFirmaSelect;

  public java.util.List<java.lang.Integer> getOrigenPeticioDeFirmaSelect() {
    return this.origenPeticioDeFirmaSelect;
  }

  public void setOrigenPeticioDeFirmaSelect(java.util.List<java.lang.Integer> origenPeticioDeFirmaSelect) {
    this.origenPeticioDeFirmaSelect = origenPeticioDeFirmaSelect;
  }


  private java.lang.Long configuracioDeFirmaIDDesde;

  public java.lang.Long getConfiguracioDeFirmaIDDesde() {
    return this.configuracioDeFirmaIDDesde;
  }

  public void setConfiguracioDeFirmaIDDesde(java.lang.Long configuracioDeFirmaIDDesde) {
    this.configuracioDeFirmaIDDesde = configuracioDeFirmaIDDesde;
  }


  private java.lang.Long configuracioDeFirmaIDFins;

  public java.lang.Long getConfiguracioDeFirmaIDFins() {
    return this.configuracioDeFirmaIDFins;
  }

  public void setConfiguracioDeFirmaIDFins(java.lang.Long configuracioDeFirmaIDFins) {
    this.configuracioDeFirmaIDFins = configuracioDeFirmaIDFins;
  }


  public PeticioDeFirmaFilterForm() {
  }
  
  public PeticioDeFirmaFilterForm(PeticioDeFirmaFilterForm __toClone) {
    super(__toClone);
    this.peticioDeFirmaIDDesde = __toClone.peticioDeFirmaIDDesde;
    this.peticioDeFirmaIDFins = __toClone.peticioDeFirmaIDFins;
    this.titol = __toClone.titol;
    this.descripcio = __toClone.descripcio;
    this.motiu = __toClone.motiu;
    this.tipusDocumentIDDesde = __toClone.tipusDocumentIDDesde;
    this.tipusDocumentIDFins = __toClone.tipusDocumentIDFins;
    this.descripcioTipusDocument = __toClone.descripcioTipusDocument;
    this.dataSolicitudDesde = __toClone.dataSolicitudDesde;
    this.dataSolicitudFins = __toClone.dataSolicitudFins;
    this.dataFinalDesde = __toClone.dataFinalDesde;
    this.dataFinalFins = __toClone.dataFinalFins;
    this.dataCaducitatDesde = __toClone.dataCaducitatDesde;
    this.dataCaducitatFins = __toClone.dataCaducitatFins;
    this.tipusOperacioFirmaSelect = __toClone.tipusOperacioFirmaSelect;
    this.tipusFirmaIDSelect = __toClone.tipusFirmaIDSelect;
    this.algorismeDeFirmaIDSelect = __toClone.algorismeDeFirmaIDSelect;
    this.posicioTaulaFirmesIDSelect = __toClone.posicioTaulaFirmesIDSelect;
    this.tipusEstatPeticioDeFirmaIDSelect = __toClone.tipusEstatPeticioDeFirmaIDSelect;
    this.motiuDeRebuig = __toClone.motiuDeRebuig;
    this.idiomaID = __toClone.idiomaID;
    this.prioritatIDSelect = __toClone.prioritatIDSelect;
    this.fluxDeFirmesIDDesde = __toClone.fluxDeFirmesIDDesde;
    this.fluxDeFirmesIDFins = __toClone.fluxDeFirmesIDFins;
    this.solicitantUsuariAplicacioID = __toClone.solicitantUsuariAplicacioID;
    this.remitentNom = __toClone.remitentNom;
    this.remitentDescripcio = __toClone.remitentDescripcio;
    this.expedientCodi = __toClone.expedientCodi;
    this.expedientNom = __toClone.expedientNom;
    this.expedientUrl = __toClone.expedientUrl;
    this.procedimentCodi = __toClone.procedimentCodi;
    this.procedimentNom = __toClone.procedimentNom;
    this.informacioAddicional = __toClone.informacioAddicional;
    this.informacioAddicionalAvaluableDesde = __toClone.informacioAddicionalAvaluableDesde;
    this.informacioAddicionalAvaluableFins = __toClone.informacioAddicionalAvaluableFins;
    this.custodiaInfoIDDesde = __toClone.custodiaInfoIDDesde;
    this.custodiaInfoIDFins = __toClone.custodiaInfoIDFins;
    this.solicitantUsuariEntitat1ID = __toClone.solicitantUsuariEntitat1ID;
    this.solicitantUsuariEntitat2ID = __toClone.solicitantUsuariEntitat2ID;
    this.solicitantUsuariEntitat3ID = __toClone.solicitantUsuariEntitat3ID;
    this.origenPeticioDeFirmaSelect = __toClone.origenPeticioDeFirmaSelect;
    this.configuracioDeFirmaIDDesde = __toClone.configuracioDeFirmaIDDesde;
    this.configuracioDeFirmaIDFins = __toClone.configuracioDeFirmaIDFins;
    this.mapOfTipusDocumentForTipusDocumentID = __toClone.mapOfTipusDocumentForTipusDocumentID;
    this.mapOfValuesForTipusOperacioFirma = __toClone.mapOfValuesForTipusOperacioFirma;
    this.mapOfValuesForTipusFirmaID = __toClone.mapOfValuesForTipusFirmaID;
    this.mapOfValuesForAlgorismeDeFirmaID = __toClone.mapOfValuesForAlgorismeDeFirmaID;
    this.mapOfValuesForPosicioTaulaFirmesID = __toClone.mapOfValuesForPosicioTaulaFirmesID;
    this.mapOfValuesForTipusEstatPeticioDeFirmaID = __toClone.mapOfValuesForTipusEstatPeticioDeFirmaID;
    this.mapOfIdiomaForIdiomaID = __toClone.mapOfIdiomaForIdiomaID;
    this.mapOfValuesForPrioritatID = __toClone.mapOfValuesForPrioritatID;
    this.mapOfFluxDeFirmesForFluxDeFirmesID = __toClone.mapOfFluxDeFirmesForFluxDeFirmesID;
    this.mapOfUsuariAplicacioForSolicitantUsuariAplicacioID = __toClone.mapOfUsuariAplicacioForSolicitantUsuariAplicacioID;
    this.mapOfCustodiaInfoForCustodiaInfoID = __toClone.mapOfCustodiaInfoForCustodiaInfoID;
    this.mapOfUsuariEntitatForSolicitantUsuariEntitat1ID = __toClone.mapOfUsuariEntitatForSolicitantUsuariEntitat1ID;
    this.mapOfUsuariEntitatForSolicitantUsuariEntitat2ID = __toClone.mapOfUsuariEntitatForSolicitantUsuariEntitat2ID;
    this.mapOfUsuariEntitatForSolicitantUsuariEntitat3ID = __toClone.mapOfUsuariEntitatForSolicitantUsuariEntitat3ID;
    this.mapOfValuesForOrigenPeticioDeFirma = __toClone.mapOfValuesForOrigenPeticioDeFirma;
    this.mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirmaID = __toClone.mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirmaID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { TITOL ,DESCRIPCIO ,MOTIU ,DATASOLICITUD ,DATAFINAL ,DATACADUCITAT ,SOLICITANTUSUARIAPLICACIOID ,REMITENTNOM ,EXPEDIENTCODI ,EXPEDIENTNOM ,PROCEDIMENTCODI ,PROCEDIMENTNOM ,INFORMACIOADDICIONAL ,INFORMACIOADDICIONALAVALUABLE }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { TIPUSDOCUMENTID ,ALGORISMEDEFIRMAID ,TIPUSESTATPETICIODEFIRMAID ,IDIOMAID ,PRIORITATID ,SOLICITANTUSUARIAPLICACIOID ,EXPEDIENTCODI ,PROCEDIMENTCODI ,INFORMACIOADDICIONALAVALUABLE ,AVISWEB }));
  }


  protected OrderBy[] defaultOrderBy = new OrderBy[] {new OrderBy(DATAFINAL, org.fundaciobit.genapp.common.query.OrderType.DESC ) , new OrderBy(TIPUSESTATPETICIODEFIRMAID )};


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
  private Map<String, String> mapOfTipusDocumentForTipusDocumentID;

  public Map<String, String> getMapOfTipusDocumentForTipusDocumentID() {
    return this.mapOfTipusDocumentForTipusDocumentID;
  }

  public void setMapOfTipusDocumentForTipusDocumentID(Map<String, String> mapOfTipusDocumentForTipusDocumentID) {
    this.mapOfTipusDocumentForTipusDocumentID = mapOfTipusDocumentForTipusDocumentID;
  }



  private Map<String, String> mapOfValuesForTipusOperacioFirma;

  public Map<String, String> getMapOfValuesForTipusOperacioFirma() {
    return this.mapOfValuesForTipusOperacioFirma;
  }

  public void setMapOfValuesForTipusOperacioFirma(Map<String, String> mapOfValuesForTipusOperacioFirma) {
    this.mapOfValuesForTipusOperacioFirma = mapOfValuesForTipusOperacioFirma;
  }



  private Map<String, String> mapOfValuesForTipusFirmaID;

  public Map<String, String> getMapOfValuesForTipusFirmaID() {
    return this.mapOfValuesForTipusFirmaID;
  }

  public void setMapOfValuesForTipusFirmaID(Map<String, String> mapOfValuesForTipusFirmaID) {
    this.mapOfValuesForTipusFirmaID = mapOfValuesForTipusFirmaID;
  }



  private Map<String, String> mapOfValuesForAlgorismeDeFirmaID;

  public Map<String, String> getMapOfValuesForAlgorismeDeFirmaID() {
    return this.mapOfValuesForAlgorismeDeFirmaID;
  }

  public void setMapOfValuesForAlgorismeDeFirmaID(Map<String, String> mapOfValuesForAlgorismeDeFirmaID) {
    this.mapOfValuesForAlgorismeDeFirmaID = mapOfValuesForAlgorismeDeFirmaID;
  }



  private Map<String, String> mapOfValuesForPosicioTaulaFirmesID;

  public Map<String, String> getMapOfValuesForPosicioTaulaFirmesID() {
    return this.mapOfValuesForPosicioTaulaFirmesID;
  }

  public void setMapOfValuesForPosicioTaulaFirmesID(Map<String, String> mapOfValuesForPosicioTaulaFirmesID) {
    this.mapOfValuesForPosicioTaulaFirmesID = mapOfValuesForPosicioTaulaFirmesID;
  }



  private Map<String, String> mapOfValuesForTipusEstatPeticioDeFirmaID;

  public Map<String, String> getMapOfValuesForTipusEstatPeticioDeFirmaID() {
    return this.mapOfValuesForTipusEstatPeticioDeFirmaID;
  }

  public void setMapOfValuesForTipusEstatPeticioDeFirmaID(Map<String, String> mapOfValuesForTipusEstatPeticioDeFirmaID) {
    this.mapOfValuesForTipusEstatPeticioDeFirmaID = mapOfValuesForTipusEstatPeticioDeFirmaID;
  }



  private Map<String, String> mapOfIdiomaForIdiomaID;

  public Map<String, String> getMapOfIdiomaForIdiomaID() {
    return this.mapOfIdiomaForIdiomaID;
  }

  public void setMapOfIdiomaForIdiomaID(Map<String, String> mapOfIdiomaForIdiomaID) {
    this.mapOfIdiomaForIdiomaID = mapOfIdiomaForIdiomaID;
  }



  private Map<String, String> mapOfValuesForPrioritatID;

  public Map<String, String> getMapOfValuesForPrioritatID() {
    return this.mapOfValuesForPrioritatID;
  }

  public void setMapOfValuesForPrioritatID(Map<String, String> mapOfValuesForPrioritatID) {
    this.mapOfValuesForPrioritatID = mapOfValuesForPrioritatID;
  }



  private Map<String, String> mapOfFluxDeFirmesForFluxDeFirmesID;

  public Map<String, String> getMapOfFluxDeFirmesForFluxDeFirmesID() {
    return this.mapOfFluxDeFirmesForFluxDeFirmesID;
  }

  public void setMapOfFluxDeFirmesForFluxDeFirmesID(Map<String, String> mapOfFluxDeFirmesForFluxDeFirmesID) {
    this.mapOfFluxDeFirmesForFluxDeFirmesID = mapOfFluxDeFirmesForFluxDeFirmesID;
  }



  private Map<String, String> mapOfUsuariAplicacioForSolicitantUsuariAplicacioID;

  public Map<String, String> getMapOfUsuariAplicacioForSolicitantUsuariAplicacioID() {
    return this.mapOfUsuariAplicacioForSolicitantUsuariAplicacioID;
  }

  public void setMapOfUsuariAplicacioForSolicitantUsuariAplicacioID(Map<String, String> mapOfUsuariAplicacioForSolicitantUsuariAplicacioID) {
    this.mapOfUsuariAplicacioForSolicitantUsuariAplicacioID = mapOfUsuariAplicacioForSolicitantUsuariAplicacioID;
  }



  private Map<String, String> mapOfCustodiaInfoForCustodiaInfoID;

  public Map<String, String> getMapOfCustodiaInfoForCustodiaInfoID() {
    return this.mapOfCustodiaInfoForCustodiaInfoID;
  }

  public void setMapOfCustodiaInfoForCustodiaInfoID(Map<String, String> mapOfCustodiaInfoForCustodiaInfoID) {
    this.mapOfCustodiaInfoForCustodiaInfoID = mapOfCustodiaInfoForCustodiaInfoID;
  }



  private Map<String, String> mapOfUsuariEntitatForSolicitantUsuariEntitat1ID;

  public Map<String, String> getMapOfUsuariEntitatForSolicitantUsuariEntitat1ID() {
    return this.mapOfUsuariEntitatForSolicitantUsuariEntitat1ID;
  }

  public void setMapOfUsuariEntitatForSolicitantUsuariEntitat1ID(Map<String, String> mapOfUsuariEntitatForSolicitantUsuariEntitat1ID) {
    this.mapOfUsuariEntitatForSolicitantUsuariEntitat1ID = mapOfUsuariEntitatForSolicitantUsuariEntitat1ID;
  }



  private Map<String, String> mapOfUsuariEntitatForSolicitantUsuariEntitat2ID;

  public Map<String, String> getMapOfUsuariEntitatForSolicitantUsuariEntitat2ID() {
    return this.mapOfUsuariEntitatForSolicitantUsuariEntitat2ID;
  }

  public void setMapOfUsuariEntitatForSolicitantUsuariEntitat2ID(Map<String, String> mapOfUsuariEntitatForSolicitantUsuariEntitat2ID) {
    this.mapOfUsuariEntitatForSolicitantUsuariEntitat2ID = mapOfUsuariEntitatForSolicitantUsuariEntitat2ID;
  }



  private Map<String, String> mapOfUsuariEntitatForSolicitantUsuariEntitat3ID;

  public Map<String, String> getMapOfUsuariEntitatForSolicitantUsuariEntitat3ID() {
    return this.mapOfUsuariEntitatForSolicitantUsuariEntitat3ID;
  }

  public void setMapOfUsuariEntitatForSolicitantUsuariEntitat3ID(Map<String, String> mapOfUsuariEntitatForSolicitantUsuariEntitat3ID) {
    this.mapOfUsuariEntitatForSolicitantUsuariEntitat3ID = mapOfUsuariEntitatForSolicitantUsuariEntitat3ID;
  }



  private Map<String, String> mapOfValuesForOrigenPeticioDeFirma;

  public Map<String, String> getMapOfValuesForOrigenPeticioDeFirma() {
    return this.mapOfValuesForOrigenPeticioDeFirma;
  }

  public void setMapOfValuesForOrigenPeticioDeFirma(Map<String, String> mapOfValuesForOrigenPeticioDeFirma) {
    this.mapOfValuesForOrigenPeticioDeFirma = mapOfValuesForOrigenPeticioDeFirma;
  }



  private Map<String, String> mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirmaID;

  public Map<String, String> getMapOfUsuariAplicacioConfiguracioForConfiguracioDeFirmaID() {
    return this.mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirmaID;
  }

  public void setMapOfUsuariAplicacioConfiguracioForConfiguracioDeFirmaID(Map<String, String> mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirmaID) {
    this.mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirmaID = mapOfUsuariAplicacioConfiguracioForConfiguracioDeFirmaID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
