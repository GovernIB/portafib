package es.caib.portafib.back.form;

/**
 * Created 2/07/13 12:00
 *
 * @author mgonzalez
 * @author anadal (11/08/2015)
 */
public class SeleccioCarrecForm extends SeleccioUsuariForm {

  String carrec; // Nom del càrrec
  String idCarrec; // Identificador del càrrec sense l'entitat
  String entitatID; // Identificador de la entitat.

  public SeleccioCarrecForm() {
    super();
  }

  public String getCarrec() {
    return carrec;
  }

  public void setCarrec(String carrec) {
    this.carrec = carrec;
  }

  public String getIdCarrec() {
    return idCarrec;
  }

  public void setIdCarrec(String idCarrec) {
    this.idCarrec = idCarrec;
  }

  public String getEntitatID() {
    return entitatID;
  }

  public void setEntitatID(String entitatID) {
    this.entitatID = entitatID;
  }

}
