package es.caib.portafib.logic.utils;

/**
 * 
 * @author anadal (u80067)
 *
 */
public class UsuariExtern {

  protected String nom;
  protected String llinatges;
  protected String email;
  protected String idioma;

  public UsuariExtern(String nom, String llinatges, String email, String idioma) {
    super();
    this.nom = nom;
    this.llinatges = llinatges;
    this.email = email;
    this.idioma = idioma;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getLlinatges() {
    return llinatges;
  }

  public void setLlinatges(String llinatges) {
    this.llinatges = llinatges;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getIdioma() {
    return idioma;
  }

  public void setIdioma(String idioma) {
    this.idioma = idioma;
  }

}
