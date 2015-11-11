package es.caib.portafib.utils;

/**
 * 
 * @author anadal
 *
 */
public enum RoleUsuariAplicacioEnum {

  PFI_ADMIN(Constants.PFI_ADMIN),

  PFI_USER(Constants.PFI_USER);

  String value;

  RoleUsuariAplicacioEnum(String value) {
    this.value = value;
  }
  
  @Override
  public String toString() {
    return value;
  }

}
