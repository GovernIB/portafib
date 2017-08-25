package es.caib.portafib.ws.v2.impl.beans;

/**
 * 
 * @author anadal
 * 
 */
public class TipusDocumentInfoWs implements Comparable<TipusDocumentInfoWs> {

  protected long tipusDocumentID;

  protected String nom;

  protected Long tipusDocumentNTI;

  /**
   * 
   */
  public TipusDocumentInfoWs() {
    super();
  }


  /**
   * 
   * @param tipusDocumentID
   * @param nom
   * @param tipusDocumentNTI
   */
  public TipusDocumentInfoWs(long tipusDocumentID, String nom, Long tipusDocumentNTI) {
    super();
    this.tipusDocumentID = tipusDocumentID;
    this.nom = nom;
    this.tipusDocumentNTI = tipusDocumentNTI;
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

  public Long getTipusDocumentNTI() {
    return tipusDocumentNTI;
  }


  public void setTipusDocumentNTI(Long tipusDocumentNTI) {
    this.tipusDocumentNTI = tipusDocumentNTI;
  }


  @Override
  public int compareTo(TipusDocumentInfoWs o) {
    return this.nom.compareToIgnoreCase(o.nom);
  }

}
