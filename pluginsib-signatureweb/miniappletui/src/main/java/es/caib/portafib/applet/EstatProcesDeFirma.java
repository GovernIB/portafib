package es.caib.portafib.applet;

/**
 * 
 * @author anadal
 *
 */
public enum EstatProcesDeFirma {

  NO_INICIAT("estat.no_iniciat"),
  LLEGINT_PDF("estat.llegint"),
  ESPERA_SELECCIO_CERTIFICAT("estat.espera_certificat"),
  FIRMANT("estat.firmant"),
  GUARDANT_PDF("estat.guardant"),
  FINAL("estat.final"),
  ERROR("error");

  final String code;
  
  EstatProcesDeFirma(String code) {
    this.code = code;
  }
  
  
}
