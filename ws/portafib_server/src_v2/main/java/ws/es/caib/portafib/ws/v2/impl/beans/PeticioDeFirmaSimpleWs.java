package es.caib.portafib.ws.v2.impl.beans;

import java.sql.Timestamp;

import es.caib.portafib.model.entity.PeticioDeFirma;

/**
 * 
 * @author anadal
 *
 */
public class PeticioDeFirmaSimpleWs {

  long peticioDeFirmaID;// PK
  java.lang.String titol;

  java.lang.String descripcio;

  int tipusEstatPeticioDeFirmaID;

  java.sql.Timestamp dataSolicitud;
  java.sql.Timestamp dataFinal;
  
  
  

  /**
   * 
   */
  public PeticioDeFirmaSimpleWs() {
    super();
  }

  /**
   * @param peticioDeFirmaID
   * @param titol
   * @param descripcio
   * @param tipusEstatPeticioDeFirmaID
   * @param dataSolicitud
   * @param dataFinal
   */
  public PeticioDeFirmaSimpleWs(long peticioDeFirmaID, String titol, String descripcio,
      int tipusEstatPeticioDeFirmaID, Timestamp dataSolicitud, Timestamp dataFinal) {
    super();
    this.peticioDeFirmaID = peticioDeFirmaID;
    this.titol = titol;
    this.descripcio = descripcio;
    this.tipusEstatPeticioDeFirmaID = tipusEstatPeticioDeFirmaID;
    this.dataSolicitud = dataSolicitud;
    this.dataFinal = dataFinal;
  }

  public long getPeticioDeFirmaID() {
    return peticioDeFirmaID;
  }

  public void setPeticioDeFirmaID(long peticioDeFirmaID) {
    this.peticioDeFirmaID = peticioDeFirmaID;
  }

  public java.lang.String getTitol() {
    return titol;
  }

  public void setTitol(java.lang.String titol) {
    this.titol = titol;
  }

  public java.lang.String getDescripcio() {
    return descripcio;
  }

  public void setDescripcio(java.lang.String descripcio) {
    this.descripcio = descripcio;
  }

  public int getTipusEstatPeticioDeFirmaID() {
    return tipusEstatPeticioDeFirmaID;
  }

  public void setTipusEstatPeticioDeFirmaID(int tipusEstatPeticioDeFirmaID) {
    this.tipusEstatPeticioDeFirmaID = tipusEstatPeticioDeFirmaID;
  }

  public java.sql.Timestamp getDataSolicitud() {
    return dataSolicitud;
  }

  public void setDataSolicitud(java.sql.Timestamp dataSolicitud) {
    this.dataSolicitud = dataSolicitud;
  }

  public java.sql.Timestamp getDataFinal() {
    return dataFinal;
  }

  public void setDataFinal(java.sql.Timestamp dataFinal) {
    this.dataFinal = dataFinal;
  }

  
  public static PeticioDeFirmaSimpleWs toWs(PeticioDeFirma peticioDeFirma) {
    if (peticioDeFirma == null) {
      return null;
    }
    return  new PeticioDeFirmaSimpleWs(peticioDeFirma.getPeticioDeFirmaID(),
        peticioDeFirma.getTitol(), peticioDeFirma.getDescripcio(),
        peticioDeFirma.getTipusEstatPeticioDeFirmaID(), peticioDeFirma.getDataSolicitud(),
        peticioDeFirma.getDataFinal());
  }
}
