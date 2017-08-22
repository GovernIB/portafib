package es.caib.portafib.ws.v1.impl;

/**
 * 
 * @author anadal
 * 
 */
public class TipusDocumentInfoWs implements Comparable<TipusDocumentInfoWs> {

  protected long tipusDocumentID;

  protected String nom;

  /**
   * 
   */
  public TipusDocumentInfoWs() {
    super();
  }

  /**
   * @param tipusDocumentID
   * @param nom
   */
  public TipusDocumentInfoWs(long tipusDocumentID, String nom) {
    super();
    this.tipusDocumentID = tipusDocumentID;
    this.nom = nom;
  }

  public long getTipusDocumentID() {
    return tipusDocumentID;
  }

  public void setTipusDocumentID(long tipusDocumentID) {
    this.tipusDocumentID = tipusDocumentID;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  @Override
  public int compareTo(TipusDocumentInfoWs o) {
    return this.nom.compareToIgnoreCase(o.nom);
  }

}
