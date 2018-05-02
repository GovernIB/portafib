package es.caib.portafib.utils;

/**
 * 
 * @author anadal
 *
 */
public enum RoleUsuariAplicacioEnum {

  PFI_ADMIN(ConstantsV2.PFI_ADMIN),

  PFI_USER(ConstantsV2.PFI_USER);

  String value;

  RoleUsuariAplicacioEnum(String value) {
    this.value = value;
  }
  
  @Override
  public String toString() {
    return value;
  }

}
