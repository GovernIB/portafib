package es.caib.portafib.back.security;

import java.security.Principal;
import java.security.acl.Group;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;

import es.caib.portafib.logic.utils.DNIUtils;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pluginsib.core.utils.CertificateUtils;
import org.fundaciobit.pluginsib.userinformation.IUserInformationPlugin;
import org.fundaciobit.pluginsib.userinformation.RolesInfo;
import org.jboss.security.SimpleGroup;
import org.jboss.security.SimplePrincipal;
import org.jboss.security.auth.certs.X509CertificateVerifier;

import es.caib.portafib.jpa.UsuariPersonaJPA;
import es.caib.portafib.logic.UsuariPersonaLogicaLocal;
import es.caib.portafib.logic.utils.EjbManager;
import es.caib.portafib.logic.utils.PdfUtils;
import es.caib.portafib.logic.utils.PortaFIBPluginsManager;
import es.caib.portafib.model.entity.UsuariPersona;
import es.caib.portafib.model.fields.UsuariPersonaFields;
import es.caib.portafib.utils.Configuracio;


/**
 * Gestiona el Login emprant Certificats
 * 
 * TODO Moure a Plugins
 * 
 * @author anadal
 */
public class BaseCertLoginModule extends org.jboss.security.auth.spi.BaseCertLoginModule
    implements UsuariPersonaFields {

  /** A principal derived from the certificate alias */
  private Principal identity;
  /** The client certificate */
  private X509Certificate credential;

  protected final Logger log = Logger.getLogger(getClass());

  protected RolesInfo rolesInfo = null;

  /**
   * Override the super version to pickup the following options after first
   * calling the super method.
   * 
   * option: securityDomain - the name of the SecurityDomain to obtain the trust
   * and keystore from. option: verifier - the class name of the
   * X509CertificateVerifier to use for verification of the login certificate
   *
   * @see X509CertificateVerifier
   * 
   * @param subject
   *          the Subject to update after a successful login.
   * @param callbackHandler
   *          the CallbackHandler that will be used to obtain the the user
   *          identity and credentials.
   * @param sharedState
   *          a Map shared between all configured login module instances
   * @param options
   *          the parameters passed to the login module.
   */
  @Override
  public void initialize(Subject subject, CallbackHandler callbackHandler,
      Map<String, ?> sharedState, Map<String, ?> options) {
    try {
      super.initialize(subject, callbackHandler, sharedState, overWriteVerifier(options));
    } catch (Throwable t) {
      log.error(t.getMessage(), t);
    } finally {

      log.debug("Ha passat per initialize()");
    }
  }

  /**
   * Sobreescrivim el Verificador del Certificat
   * 
   * @param options
   * @return
   */
  protected Map<String, ?> overWriteVerifier(Map<String, ?> options) {
    Map<String, Object> newoptions = new HashMap<String, Object>();
    if (options != null) {
      newoptions.putAll(options);
      newoptions.put("verifier", "es.caib.portafib.back.security.PortaFIBCertVerifier");
    }
    return newoptions;
  }

  /**
   * Perform the authentication of the username and password.
   */
  @SuppressWarnings("unchecked")
  public boolean login() throws LoginException {
    
    super.loginOk = false;

    try {
      if (super.login() == true) {

        // Info Usuari EJB
        UsuariPersonaLogicaLocal usuariPersonaEjb;
        try {
          usuariPersonaEjb = EjbManager.getUsuariPersonaLogicaEJB();
        } catch (I18NException e) {
          // TODO traduccio
          log.error(e);
          throw new LoginException("No puc accedir a la informació de l'usuari "
              + super.getUsername());
        }

        // Cercar username de l'usuari
        X509Certificate cert = (X509Certificate) super.getCredentials();
        if(log.isDebugEnabled()) {
          log.debug("Empram el certificat " + cert);
        }
        // TODO fer-ho amb IB-KEY
        String dni = DNIUtils.getDNI(cert);
        UsuariPersonaJPA usuariPersona;
        if (dni != null) {
          log.info(" Feim el Login emprant el DNI " + dni);
          List<UsuariPersona> usuaris;
          try {
            Where w = Where.AND(
                NIF.equal(dni),
                USUARIINTERN.equal(true)
                );
            usuaris = usuariPersonaEjb.select(w);
          } catch (Exception e) {
            log.error(e);
            throw new LoginException("No puc accedir a la informació de l'usuari amb dni "
                + dni);
          }
          if (usuaris == null || usuaris.size() == 0) {
            throw new LoginException("No existeix Cap usuari-Persona amb nif " + dni);
          }
          usuariPersona = (UsuariPersonaJPA) usuaris.get(0);
        } else {
          // En Desenvolupament permetre certificats sense DNI on el CN ja és el
          // nom de l'usuari
          if (Configuracio.isDesenvolupament()) {
            String commonname = CertificateUtils.getCommonName(cert);
            if (commonname == null) {
              String msg = " No puc extreure el CommonName del certificat "
                  + cert.getSubjectDN().getName().trim();
              log.info(msg);
              throw new LoginException(msg);
            }
            usuariPersona = usuariPersonaEjb.findByPrimaryKeyFull(commonname);
            if (usuariPersona == null) {
              String msg = "No existeix Cap usuari-Persona amb identificador " + commonname;
              log.error(msg);
              throw new LoginException(msg);
            }
          } else {
            // TODO
            String msg = "No puc obtenir el DNI del certificat !!!";
            log.error(msg);
            throw new LoginException(msg);
          }
        }

        String username = usuariPersona.getUsuariPersonaID();

        // Cercar Roles de per l'usuari user
        IUserInformationPlugin plugin = null;
        try {
          plugin = PortaFIBPluginsManager.getUserInformationPluginInstance();

          this.rolesInfo = plugin.getRolesByUsername(username);

          log.info("ROLES SEYCON DB: " + Arrays.toString(this.rolesInfo.getRoles()));

        } catch (Exception e1) {
          // TODO Traduir
          final String msg = "No puc obtenir els roles de l'usuari " + username + ": "
              + e1.getMessage();
          log.error(msg, e1);
          throw new LoginException(msg);
        }
        
        // Verificar Certificat si està revocat
        if (Configuracio.checkCertificateInClientCert()) {
          PdfUtils.validateCertificat(cert);
        }

        super.loginOk = true;

        if (getUseFirstPass() == true) {
          // Add authentication info to shared state map
          sharedState.put("javax.security.auth.login.name", username);
          sharedState.put("javax.security.auth.login.password", username);
        }

        try {
          identity = createIdentity(username);
        } catch (Exception e) {

          log.error("Failed to create identity for alias:", e);
        }

        super.loginOk = true;
        log.debug("Surt de login: true");
        return true;
      } else {
        log.debug("Surt de login: false");
        return false;
      }

    } catch (LoginException le) {
      log.error(le.getMessage(), le);
      throw le;
    } catch (Throwable th) {
      log.error("Error greu executant login():  " + th.getMessage(), th);
      throw new LoginException(th.getMessage());
    }

  }

  /**
   * Override to add the X509Certificate to the public credentials
   * 
   * @return
   * @throws LoginException
   */
  public boolean commit() throws LoginException {
    log.debug("ENTRA EN COMMIT");

    boolean ok = super.commit();
    if (ok == true) {
      // Add the cert to the public credentials
      if (credential != null) {
        subject.getPublicCredentials().add(credential);
      }
    }
    return ok;
  }

  /**
   * Subclasses need to override this to provide the roles for authorization
   * 
   * @return
   * @throws LoginException
   */
  protected Group[] getRoleSets() throws LoginException {

    log.info("Entra dins getRoleSets()");

    Group rolesGroup = new SimpleGroup("Roles");

    if (rolesInfo != null) {
      String[] roles = rolesInfo.getRoles();
      if (roles != null) {
        for (int i = 0; i < roles.length; i++) {
          log.info("  getRoleSets(): Adding role " + roles[i]);
          rolesGroup.addMember(new SimplePrincipal(roles[i]));
        }
      }
    }

    Group principalGroup = new SimpleGroup("CallerPrincipal");
    Principal principal = getIdentity();
    principalGroup.addMember(principal);

    return new Group[] { rolesGroup, principalGroup };
    // return new Group[0];
  }

  protected Principal getIdentity() {
    log.info("getIdentity(): " + identity.getName());
    return identity;
  }

  protected Object getCredentials() {
    log.info("getCredentials(): " + credential.getSubjectDN());
    return credential;
  }

  protected String getUsername() {
    String username = null;
    if (getIdentity() != null)
      username = getIdentity().getName();
    log.info("getUsername(): " + username);
    return username;
  }

}
