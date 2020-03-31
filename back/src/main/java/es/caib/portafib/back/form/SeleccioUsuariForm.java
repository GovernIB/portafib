package es.caib.portafib.back.form;

import java.util.List;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;

/**
 * 
 * @author anadal
 *
 */
public class SeleccioUsuariForm {

  public static final String PARAM_1 = "param1";
  public static final String PARAM_2 = "param2";

  String id; // id de la persona o del usuari-entitat
  String titol; // Titol del formulari
  String subtitol; // Subtitol del formulari
  String cancelUrl; // Url de cancel·lació
  String param1; // Parametre opcional 1
  String param2; // Parametre opcional 1
  String urlData; // Url d'on obtenir les dades JSON. Podrà retornar id de persones
                  // o ids de usuari-entitat

  /**
   * Boto secundary:
   * 
   *  secondayButton.key   : Codelabel
   *  secondayButton.value : ActionJavascript
   *  
   */
  AdditionalButton secondaryButton;

  List<StringKeyValue> usuarisFavorits = null;

  public String getUrlData() {
    return urlData;
  }

  public void setUrlData(String urlData) {
    this.urlData = urlData;
  }


  public AdditionalButton getSecondaryButton() {
    return secondaryButton;
  }

  public void setSecondaryButton(AdditionalButton secondaryButton) {
    this.secondaryButton = secondaryButton;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitol() {
    return titol;
  }

  public void setTitol(String titol) {
    this.titol = titol;
  }

  public String getSubtitol() {
    return subtitol;
  }

  public void setSubtitol(String subtitol) {
    this.subtitol = subtitol;
  }

  public String getCancelUrl() {
    return cancelUrl;
  }

  public void setCancelUrl(String cancelUrl) {
    this.cancelUrl = cancelUrl;
  }

  public String getParam1() {
    return param1;
  }

  public void setParam1(String param1) {
    this.param1 = param1;
  }

  public String getParam2() {
    return param2;
  }

  public void setParam2(String param2) {
    this.param2 = param2;
  }

  public List<StringKeyValue> getUsuarisFavorits() {
    return usuarisFavorits;
  }

  public void setUsuarisFavorits(List<StringKeyValue> usuarisFavorits) {
    this.usuarisFavorits = usuarisFavorits;
  }

}
