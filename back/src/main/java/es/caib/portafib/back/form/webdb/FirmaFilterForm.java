
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.FirmaFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class FirmaFilterForm extends PortaFIBBaseFilterForm implements FirmaFields {

  private java.lang.Long firmaIDDesde;

  public java.lang.Long getFirmaIDDesde() {
    return this.firmaIDDesde;
  }

  public void setFirmaIDDesde(java.lang.Long firmaIDDesde) {
    this.firmaIDDesde = firmaIDDesde;
  }


  private java.lang.Long firmaIDFins;

  public java.lang.Long getFirmaIDFins() {
    return this.firmaIDFins;
  }

  public void setFirmaIDFins(java.lang.Long firmaIDFins) {
    this.firmaIDFins = firmaIDFins;
  }


  private java.lang.String destinatariID;

  public java.lang.String getDestinatariID() {
    return this.destinatariID;
  }

  public void setDestinatariID(java.lang.String destinatariID) {
    this.destinatariID = destinatariID;
  }


  private java.lang.Long blocDeFirmaIDDesde;

  public java.lang.Long getBlocDeFirmaIDDesde() {
    return this.blocDeFirmaIDDesde;
  }

  public void setBlocDeFirmaIDDesde(java.lang.Long blocDeFirmaIDDesde) {
    this.blocDeFirmaIDDesde = blocDeFirmaIDDesde;
  }


  private java.lang.Long blocDeFirmaIDFins;

  public java.lang.Long getBlocDeFirmaIDFins() {
    return this.blocDeFirmaIDFins;
  }

  public void setBlocDeFirmaIDFins(java.lang.Long blocDeFirmaIDFins) {
    this.blocDeFirmaIDFins = blocDeFirmaIDFins;
  }


  private java.lang.Integer numFirmaDocumentDesde;

  public java.lang.Integer getNumFirmaDocumentDesde() {
    return this.numFirmaDocumentDesde;
  }

  public void setNumFirmaDocumentDesde(java.lang.Integer numFirmaDocumentDesde) {
    this.numFirmaDocumentDesde = numFirmaDocumentDesde;
  }


  private java.lang.Integer numFirmaDocumentFins;

  public java.lang.Integer getNumFirmaDocumentFins() {
    return this.numFirmaDocumentFins;
  }

  public void setNumFirmaDocumentFins(java.lang.Integer numFirmaDocumentFins) {
    this.numFirmaDocumentFins = numFirmaDocumentFins;
  }


  private java.lang.Integer caixaPaginaDesde;

  public java.lang.Integer getCaixaPaginaDesde() {
    return this.caixaPaginaDesde;
  }

  public void setCaixaPaginaDesde(java.lang.Integer caixaPaginaDesde) {
    this.caixaPaginaDesde = caixaPaginaDesde;
  }


  private java.lang.Integer caixaPaginaFins;

  public java.lang.Integer getCaixaPaginaFins() {
    return this.caixaPaginaFins;
  }

  public void setCaixaPaginaFins(java.lang.Integer caixaPaginaFins) {
    this.caixaPaginaFins = caixaPaginaFins;
  }


  private java.lang.Integer caixaXDesde;

  public java.lang.Integer getCaixaXDesde() {
    return this.caixaXDesde;
  }

  public void setCaixaXDesde(java.lang.Integer caixaXDesde) {
    this.caixaXDesde = caixaXDesde;
  }


  private java.lang.Integer caixaXFins;

  public java.lang.Integer getCaixaXFins() {
    return this.caixaXFins;
  }

  public void setCaixaXFins(java.lang.Integer caixaXFins) {
    this.caixaXFins = caixaXFins;
  }


  private java.lang.Integer caixaYDesde;

  public java.lang.Integer getCaixaYDesde() {
    return this.caixaYDesde;
  }

  public void setCaixaYDesde(java.lang.Integer caixaYDesde) {
    this.caixaYDesde = caixaYDesde;
  }


  private java.lang.Integer caixaYFins;

  public java.lang.Integer getCaixaYFins() {
    return this.caixaYFins;
  }

  public void setCaixaYFins(java.lang.Integer caixaYFins) {
    this.caixaYFins = caixaYFins;
  }


  private java.lang.Integer caixaAmpleDesde;

  public java.lang.Integer getCaixaAmpleDesde() {
    return this.caixaAmpleDesde;
  }

  public void setCaixaAmpleDesde(java.lang.Integer caixaAmpleDesde) {
    this.caixaAmpleDesde = caixaAmpleDesde;
  }


  private java.lang.Integer caixaAmpleFins;

  public java.lang.Integer getCaixaAmpleFins() {
    return this.caixaAmpleFins;
  }

  public void setCaixaAmpleFins(java.lang.Integer caixaAmpleFins) {
    this.caixaAmpleFins = caixaAmpleFins;
  }


  private java.lang.Integer caixaAltDesde;

  public java.lang.Integer getCaixaAltDesde() {
    return this.caixaAltDesde;
  }

  public void setCaixaAltDesde(java.lang.Integer caixaAltDesde) {
    this.caixaAltDesde = caixaAltDesde;
  }


  private java.lang.Integer caixaAltFins;

  public java.lang.Integer getCaixaAltFins() {
    return this.caixaAltFins;
  }

  public void setCaixaAltFins(java.lang.Integer caixaAltFins) {
    this.caixaAltFins = caixaAltFins;
  }


  private java.math.BigInteger numeroSerieCertificatDesde;

  public java.math.BigInteger getNumeroSerieCertificatDesde() {
    return this.numeroSerieCertificatDesde;
  }

  public void setNumeroSerieCertificatDesde(java.math.BigInteger numeroSerieCertificatDesde) {
    this.numeroSerieCertificatDesde = numeroSerieCertificatDesde;
  }


  private java.math.BigInteger numeroSerieCertificatFins;

  public java.math.BigInteger getNumeroSerieCertificatFins() {
    return this.numeroSerieCertificatFins;
  }

  public void setNumeroSerieCertificatFins(java.math.BigInteger numeroSerieCertificatFins) {
    this.numeroSerieCertificatFins = numeroSerieCertificatFins;
  }


  private java.lang.String emissorCertificat;

  public java.lang.String getEmissorCertificat() {
    return this.emissorCertificat;
  }

  public void setEmissorCertificat(java.lang.String emissorCertificat) {
    this.emissorCertificat = emissorCertificat;
  }


  private java.lang.String nomCertificat;

  public java.lang.String getNomCertificat() {
    return this.nomCertificat;
  }

  public void setNomCertificat(java.lang.String nomCertificat) {
    this.nomCertificat = nomCertificat;
  }


  private java.lang.Long tipusEstatDeFirmaFinalIDDesde;

  public java.lang.Long getTipusEstatDeFirmaFinalIDDesde() {
    return this.tipusEstatDeFirmaFinalIDDesde;
  }

  public void setTipusEstatDeFirmaFinalIDDesde(java.lang.Long tipusEstatDeFirmaFinalIDDesde) {
    this.tipusEstatDeFirmaFinalIDDesde = tipusEstatDeFirmaFinalIDDesde;
  }


  private java.lang.Long tipusEstatDeFirmaFinalIDFins;

  public java.lang.Long getTipusEstatDeFirmaFinalIDFins() {
    return this.tipusEstatDeFirmaFinalIDFins;
  }

  public void setTipusEstatDeFirmaFinalIDFins(java.lang.Long tipusEstatDeFirmaFinalIDFins) {
    this.tipusEstatDeFirmaFinalIDFins = tipusEstatDeFirmaFinalIDFins;
  }


  public FirmaFilterForm() {
  }
  
  public FirmaFilterForm(FirmaFilterForm __toClone) {
    super(__toClone);
    this.firmaIDDesde = __toClone.firmaIDDesde;
    this.firmaIDFins = __toClone.firmaIDFins;
    this.destinatariID = __toClone.destinatariID;
    this.blocDeFirmaIDDesde = __toClone.blocDeFirmaIDDesde;
    this.blocDeFirmaIDFins = __toClone.blocDeFirmaIDFins;
    this.numFirmaDocumentDesde = __toClone.numFirmaDocumentDesde;
    this.numFirmaDocumentFins = __toClone.numFirmaDocumentFins;
    this.caixaPaginaDesde = __toClone.caixaPaginaDesde;
    this.caixaPaginaFins = __toClone.caixaPaginaFins;
    this.caixaXDesde = __toClone.caixaXDesde;
    this.caixaXFins = __toClone.caixaXFins;
    this.caixaYDesde = __toClone.caixaYDesde;
    this.caixaYFins = __toClone.caixaYFins;
    this.caixaAmpleDesde = __toClone.caixaAmpleDesde;
    this.caixaAmpleFins = __toClone.caixaAmpleFins;
    this.caixaAltDesde = __toClone.caixaAltDesde;
    this.caixaAltFins = __toClone.caixaAltFins;
    this.numeroSerieCertificatDesde = __toClone.numeroSerieCertificatDesde;
    this.numeroSerieCertificatFins = __toClone.numeroSerieCertificatFins;
    this.emissorCertificat = __toClone.emissorCertificat;
    this.nomCertificat = __toClone.nomCertificat;
    this.tipusEstatDeFirmaFinalIDDesde = __toClone.tipusEstatDeFirmaFinalIDDesde;
    this.tipusEstatDeFirmaFinalIDFins = __toClone.tipusEstatDeFirmaFinalIDFins;
    this.mapOfUsuariEntitatForDestinatariID = __toClone.mapOfUsuariEntitatForDestinatariID;
    this.mapOfBlocDeFirmesForBlocDeFirmaID = __toClone.mapOfBlocDeFirmesForBlocDeFirmaID;
    this.mapOfTipusEstatDeFirmaFinalForTipusEstatDeFirmaFinalID = __toClone.mapOfTipusEstatDeFirmaFinalForTipusEstatDeFirmaFinalID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { NUMEROSERIECERTIFICAT ,NOMCERTIFICAT }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { EMISSORCERTIFICAT ,TIPUSESTATDEFIRMAFINALID }));
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
  private Map<String, String> mapOfUsuariEntitatForDestinatariID;

  public Map<String, String> getMapOfUsuariEntitatForDestinatariID() {
    return this.mapOfUsuariEntitatForDestinatariID;
  }

  public void setMapOfUsuariEntitatForDestinatariID(Map<String, String> mapOfUsuariEntitatForDestinatariID) {
    this.mapOfUsuariEntitatForDestinatariID = mapOfUsuariEntitatForDestinatariID;
  }



  private Map<String, String> mapOfBlocDeFirmesForBlocDeFirmaID;

  public Map<String, String> getMapOfBlocDeFirmesForBlocDeFirmaID() {
    return this.mapOfBlocDeFirmesForBlocDeFirmaID;
  }

  public void setMapOfBlocDeFirmesForBlocDeFirmaID(Map<String, String> mapOfBlocDeFirmesForBlocDeFirmaID) {
    this.mapOfBlocDeFirmesForBlocDeFirmaID = mapOfBlocDeFirmesForBlocDeFirmaID;
  }



  private Map<String, String> mapOfTipusEstatDeFirmaFinalForTipusEstatDeFirmaFinalID;

  public Map<String, String> getMapOfTipusEstatDeFirmaFinalForTipusEstatDeFirmaFinalID() {
    return this.mapOfTipusEstatDeFirmaFinalForTipusEstatDeFirmaFinalID;
  }

  public void setMapOfTipusEstatDeFirmaFinalForTipusEstatDeFirmaFinalID(Map<String, String> mapOfTipusEstatDeFirmaFinalForTipusEstatDeFirmaFinalID) {
    this.mapOfTipusEstatDeFirmaFinalForTipusEstatDeFirmaFinalID = mapOfTipusEstatDeFirmaFinalForTipusEstatDeFirmaFinalID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   traduibles.add(TIPUSESTATDEFIRMAFINALID.javaName);
   };

}
