
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


  private java.lang.Integer posicioTaulaFirmesIDDesde;

  public java.lang.Integer getPosicioTaulaFirmesIDDesde() {
    return this.posicioTaulaFirmesIDDesde;
  }

  public void setPosicioTaulaFirmesIDDesde(java.lang.Integer posicioTaulaFirmesIDDesde) {
    this.posicioTaulaFirmesIDDesde = posicioTaulaFirmesIDDesde;
  }


  private java.lang.Integer posicioTaulaFirmesIDFins;

  public java.lang.Integer getPosicioTaulaFirmesIDFins() {
    return this.posicioTaulaFirmesIDFins;
  }

  public void setPosicioTaulaFirmesIDFins(java.lang.Integer posicioTaulaFirmesIDFins) {
    this.posicioTaulaFirmesIDFins = posicioTaulaFirmesIDFins;
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


  private java.lang.Integer tipusOperacioFirmaDesde;

  public java.lang.Integer getTipusOperacioFirmaDesde() {
    return this.tipusOperacioFirmaDesde;
  }

  public void setTipusOperacioFirmaDesde(java.lang.Integer tipusOperacioFirmaDesde) {
    this.tipusOperacioFirmaDesde = tipusOperacioFirmaDesde;
  }


  private java.lang.Integer tipusOperacioFirmaFins;

  public java.lang.Integer getTipusOperacioFirmaFins() {
    return this.tipusOperacioFirmaFins;
  }

  public void setTipusOperacioFirmaFins(java.lang.Integer tipusOperacioFirmaFins) {
    this.tipusOperacioFirmaFins = tipusOperacioFirmaFins;
  }


  private java.lang.Integer tipusFirmaIDDesde;

  public java.lang.Integer getTipusFirmaIDDesde() {
    return this.tipusFirmaIDDesde;
  }

  public void setTipusFirmaIDDesde(java.lang.Integer tipusFirmaIDDesde) {
    this.tipusFirmaIDDesde = tipusFirmaIDDesde;
  }


  private java.lang.Integer tipusFirmaIDFins;

  public java.lang.Integer getTipusFirmaIDFins() {
    return this.tipusFirmaIDFins;
  }

  public void setTipusFirmaIDFins(java.lang.Integer tipusFirmaIDFins) {
    this.tipusFirmaIDFins = tipusFirmaIDFins;
  }


  private java.lang.Integer algorismeDeFirmaIDDesde;

  public java.lang.Integer getAlgorismeDeFirmaIDDesde() {
    return this.algorismeDeFirmaIDDesde;
  }

  public void setAlgorismeDeFirmaIDDesde(java.lang.Integer algorismeDeFirmaIDDesde) {
    this.algorismeDeFirmaIDDesde = algorismeDeFirmaIDDesde;
  }


  private java.lang.Integer algorismeDeFirmaIDFins;

  public java.lang.Integer getAlgorismeDeFirmaIDFins() {
    return this.algorismeDeFirmaIDFins;
  }

  public void setAlgorismeDeFirmaIDFins(java.lang.Integer algorismeDeFirmaIDFins) {
    this.algorismeDeFirmaIDFins = algorismeDeFirmaIDFins;
  }


  private java.lang.Integer tipusEstatPeticioDeFirmaIDDesde;

  public java.lang.Integer getTipusEstatPeticioDeFirmaIDDesde() {
    return this.tipusEstatPeticioDeFirmaIDDesde;
  }

  public void setTipusEstatPeticioDeFirmaIDDesde(java.lang.Integer tipusEstatPeticioDeFirmaIDDesde) {
    this.tipusEstatPeticioDeFirmaIDDesde = tipusEstatPeticioDeFirmaIDDesde;
  }


  private java.lang.Integer tipusEstatPeticioDeFirmaIDFins;

  public java.lang.Integer getTipusEstatPeticioDeFirmaIDFins() {
    return this.tipusEstatPeticioDeFirmaIDFins;
  }

  public void setTipusEstatPeticioDeFirmaIDFins(java.lang.Integer tipusEstatPeticioDeFirmaIDFins) {
    this.tipusEstatPeticioDeFirmaIDFins = tipusEstatPeticioDeFirmaIDFins;
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


  private java.lang.Integer prioritatIDDesde;

  public java.lang.Integer getPrioritatIDDesde() {
    return this.prioritatIDDesde;
  }

  public void setPrioritatIDDesde(java.lang.Integer prioritatIDDesde) {
    this.prioritatIDDesde = prioritatIDDesde;
  }


  private java.lang.Integer prioritatIDFins;

  public java.lang.Integer getPrioritatIDFins() {
    return this.prioritatIDFins;
  }

  public void setPrioritatIDFins(java.lang.Integer prioritatIDFins) {
    this.prioritatIDFins = prioritatIDFins;
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


  private java.lang.String usuariAplicacioID;

  public java.lang.String getUsuariAplicacioID() {
    return this.usuariAplicacioID;
  }

  public void setUsuariAplicacioID(java.lang.String usuariAplicacioID) {
    this.usuariAplicacioID = usuariAplicacioID;
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


  private java.lang.String usuariEntitatID;

  public java.lang.String getUsuariEntitatID() {
    return this.usuariEntitatID;
  }

  public void setUsuariEntitatID(java.lang.String usuariEntitatID) {
    this.usuariEntitatID = usuariEntitatID;
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
    this.posicioTaulaFirmesIDDesde = __toClone.posicioTaulaFirmesIDDesde;
    this.posicioTaulaFirmesIDFins = __toClone.posicioTaulaFirmesIDFins;
    this.dataSolicitudDesde = __toClone.dataSolicitudDesde;
    this.dataSolicitudFins = __toClone.dataSolicitudFins;
    this.dataFinalDesde = __toClone.dataFinalDesde;
    this.dataFinalFins = __toClone.dataFinalFins;
    this.dataCaducitatDesde = __toClone.dataCaducitatDesde;
    this.dataCaducitatFins = __toClone.dataCaducitatFins;
    this.tipusOperacioFirmaDesde = __toClone.tipusOperacioFirmaDesde;
    this.tipusOperacioFirmaFins = __toClone.tipusOperacioFirmaFins;
    this.tipusFirmaIDDesde = __toClone.tipusFirmaIDDesde;
    this.tipusFirmaIDFins = __toClone.tipusFirmaIDFins;
    this.algorismeDeFirmaIDDesde = __toClone.algorismeDeFirmaIDDesde;
    this.algorismeDeFirmaIDFins = __toClone.algorismeDeFirmaIDFins;
    this.tipusEstatPeticioDeFirmaIDDesde = __toClone.tipusEstatPeticioDeFirmaIDDesde;
    this.tipusEstatPeticioDeFirmaIDFins = __toClone.tipusEstatPeticioDeFirmaIDFins;
    this.motiuDeRebuig = __toClone.motiuDeRebuig;
    this.idiomaID = __toClone.idiomaID;
    this.prioritatIDDesde = __toClone.prioritatIDDesde;
    this.prioritatIDFins = __toClone.prioritatIDFins;
    this.fluxDeFirmesIDDesde = __toClone.fluxDeFirmesIDDesde;
    this.fluxDeFirmesIDFins = __toClone.fluxDeFirmesIDFins;
    this.usuariAplicacioID = __toClone.usuariAplicacioID;
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
    this.usuariEntitatID = __toClone.usuariEntitatID;
    this.mapOfTipusDocumentForTipusDocumentID = __toClone.mapOfTipusDocumentForTipusDocumentID;
    this.mapOfValuesForPosicioTaulaFirmesID = __toClone.mapOfValuesForPosicioTaulaFirmesID;
    this.mapOfValuesForTipusOperacioFirma = __toClone.mapOfValuesForTipusOperacioFirma;
    this.mapOfTipusFirmaForTipusFirmaID = __toClone.mapOfTipusFirmaForTipusFirmaID;
    this.mapOfAlgorismeDeFirmaForAlgorismeDeFirmaID = __toClone.mapOfAlgorismeDeFirmaForAlgorismeDeFirmaID;
    this.mapOfTipusEstatPeticioDeFirmaForTipusEstatPeticioDeFirmaID = __toClone.mapOfTipusEstatPeticioDeFirmaForTipusEstatPeticioDeFirmaID;
    this.mapOfIdiomaForIdiomaID = __toClone.mapOfIdiomaForIdiomaID;
    this.mapOfPrioritatForPrioritatID = __toClone.mapOfPrioritatForPrioritatID;
    this.mapOfFluxDeFirmesForFluxDeFirmesID = __toClone.mapOfFluxDeFirmesForFluxDeFirmesID;
    this.mapOfUsuariAplicacioForUsuariAplicacioID = __toClone.mapOfUsuariAplicacioForUsuariAplicacioID;
    this.mapOfCustodiaInfoForCustodiaInfoID = __toClone.mapOfCustodiaInfoForCustodiaInfoID;
    this.mapOfUsuariEntitatForUsuariEntitatID = __toClone.mapOfUsuariEntitatForUsuariEntitatID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { TITOL ,DESCRIPCIO ,MOTIU ,DATASOLICITUD ,DATAFINAL ,DATACADUCITAT ,USUARIAPLICACIOID ,EXPEDIENTCODI ,EXPEDIENTNOM ,PROCEDIMENTCODI ,PROCEDIMENTNOM ,INFORMACIOADDICIONAL ,INFORMACIOADDICIONALAVALUABLE }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { TIPUSDOCUMENTID ,ALGORISMEDEFIRMAID ,TIPUSESTATPETICIODEFIRMAID ,IDIOMAID ,PRIORITATID ,USUARIAPLICACIOID ,EXPEDIENTCODI ,PROCEDIMENTCODI ,INFORMACIOADDICIONALAVALUABLE ,AVISWEB }));
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



  private Map<String, String> mapOfValuesForPosicioTaulaFirmesID;

  public Map<String, String> getMapOfValuesForPosicioTaulaFirmesID() {
    return this.mapOfValuesForPosicioTaulaFirmesID;
  }

  public void setMapOfValuesForPosicioTaulaFirmesID(Map<String, String> mapOfValuesForPosicioTaulaFirmesID) {
    this.mapOfValuesForPosicioTaulaFirmesID = mapOfValuesForPosicioTaulaFirmesID;
  }



  private Map<String, String> mapOfValuesForTipusOperacioFirma;

  public Map<String, String> getMapOfValuesForTipusOperacioFirma() {
    return this.mapOfValuesForTipusOperacioFirma;
  }

  public void setMapOfValuesForTipusOperacioFirma(Map<String, String> mapOfValuesForTipusOperacioFirma) {
    this.mapOfValuesForTipusOperacioFirma = mapOfValuesForTipusOperacioFirma;
  }



  private Map<String, String> mapOfTipusFirmaForTipusFirmaID;

  public Map<String, String> getMapOfTipusFirmaForTipusFirmaID() {
    return this.mapOfTipusFirmaForTipusFirmaID;
  }

  public void setMapOfTipusFirmaForTipusFirmaID(Map<String, String> mapOfTipusFirmaForTipusFirmaID) {
    this.mapOfTipusFirmaForTipusFirmaID = mapOfTipusFirmaForTipusFirmaID;
  }



  private Map<String, String> mapOfAlgorismeDeFirmaForAlgorismeDeFirmaID;

  public Map<String, String> getMapOfAlgorismeDeFirmaForAlgorismeDeFirmaID() {
    return this.mapOfAlgorismeDeFirmaForAlgorismeDeFirmaID;
  }

  public void setMapOfAlgorismeDeFirmaForAlgorismeDeFirmaID(Map<String, String> mapOfAlgorismeDeFirmaForAlgorismeDeFirmaID) {
    this.mapOfAlgorismeDeFirmaForAlgorismeDeFirmaID = mapOfAlgorismeDeFirmaForAlgorismeDeFirmaID;
  }



  private Map<String, String> mapOfTipusEstatPeticioDeFirmaForTipusEstatPeticioDeFirmaID;

  public Map<String, String> getMapOfTipusEstatPeticioDeFirmaForTipusEstatPeticioDeFirmaID() {
    return this.mapOfTipusEstatPeticioDeFirmaForTipusEstatPeticioDeFirmaID;
  }

  public void setMapOfTipusEstatPeticioDeFirmaForTipusEstatPeticioDeFirmaID(Map<String, String> mapOfTipusEstatPeticioDeFirmaForTipusEstatPeticioDeFirmaID) {
    this.mapOfTipusEstatPeticioDeFirmaForTipusEstatPeticioDeFirmaID = mapOfTipusEstatPeticioDeFirmaForTipusEstatPeticioDeFirmaID;
  }



  private Map<String, String> mapOfIdiomaForIdiomaID;

  public Map<String, String> getMapOfIdiomaForIdiomaID() {
    return this.mapOfIdiomaForIdiomaID;
  }

  public void setMapOfIdiomaForIdiomaID(Map<String, String> mapOfIdiomaForIdiomaID) {
    this.mapOfIdiomaForIdiomaID = mapOfIdiomaForIdiomaID;
  }



  private Map<String, String> mapOfPrioritatForPrioritatID;

  public Map<String, String> getMapOfPrioritatForPrioritatID() {
    return this.mapOfPrioritatForPrioritatID;
  }

  public void setMapOfPrioritatForPrioritatID(Map<String, String> mapOfPrioritatForPrioritatID) {
    this.mapOfPrioritatForPrioritatID = mapOfPrioritatForPrioritatID;
  }



  private Map<String, String> mapOfFluxDeFirmesForFluxDeFirmesID;

  public Map<String, String> getMapOfFluxDeFirmesForFluxDeFirmesID() {
    return this.mapOfFluxDeFirmesForFluxDeFirmesID;
  }

  public void setMapOfFluxDeFirmesForFluxDeFirmesID(Map<String, String> mapOfFluxDeFirmesForFluxDeFirmesID) {
    this.mapOfFluxDeFirmesForFluxDeFirmesID = mapOfFluxDeFirmesForFluxDeFirmesID;
  }



  private Map<String, String> mapOfUsuariAplicacioForUsuariAplicacioID;

  public Map<String, String> getMapOfUsuariAplicacioForUsuariAplicacioID() {
    return this.mapOfUsuariAplicacioForUsuariAplicacioID;
  }

  public void setMapOfUsuariAplicacioForUsuariAplicacioID(Map<String, String> mapOfUsuariAplicacioForUsuariAplicacioID) {
    this.mapOfUsuariAplicacioForUsuariAplicacioID = mapOfUsuariAplicacioForUsuariAplicacioID;
  }



  private Map<String, String> mapOfCustodiaInfoForCustodiaInfoID;

  public Map<String, String> getMapOfCustodiaInfoForCustodiaInfoID() {
    return this.mapOfCustodiaInfoForCustodiaInfoID;
  }

  public void setMapOfCustodiaInfoForCustodiaInfoID(Map<String, String> mapOfCustodiaInfoForCustodiaInfoID) {
    this.mapOfCustodiaInfoForCustodiaInfoID = mapOfCustodiaInfoForCustodiaInfoID;
  }



  private Map<String, String> mapOfUsuariEntitatForUsuariEntitatID;

  public Map<String, String> getMapOfUsuariEntitatForUsuariEntitatID() {
    return this.mapOfUsuariEntitatForUsuariEntitatID;
  }

  public void setMapOfUsuariEntitatForUsuariEntitatID(Map<String, String> mapOfUsuariEntitatForUsuariEntitatID) {
    this.mapOfUsuariEntitatForUsuariEntitatID = mapOfUsuariEntitatForUsuariEntitatID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   traduibles.add(TIPUSESTATPETICIODEFIRMAID.javaName);
   traduibles.add(PRIORITATID.javaName);
   };

}
