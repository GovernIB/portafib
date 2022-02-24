package es.caib.portafib.commons.utils;

/**
 *
 * @author anadal
 *
 */
public interface Constants {

  public static final String PORTAFIB_PROPERTY_BASE="es.caib.portafib.";

    public static final String MAIL_SERVICE = "java:/es.caib.portafib.mail";

    // TRUE ROLES
    public static final String PFI_ADMIN="PFI_ADMIN";
    public static final String PFI_USER="PFI_USER";

    // VIRTUAL SECURITY ROLES
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    
    // EJB HIGH LEVEL ROLES
    public static final String ROLE_EJB_FULL_ACCESS  = PFI_ADMIN;
    public static final String ROLE_EJB_BASIC_ACCESS = PFI_USER;

}