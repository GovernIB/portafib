/**
 * 
 */
package es.caib.portafib.back.security;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.mapping.MappableAttributesRetriever;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.j2ee.J2eeBasedPreAuthenticatedWebAuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Proveeix els detalls de preautenticació per a Spring Security
 * 
 */
public class RolesBasedPreAuthenticatedWebAuthenticationDetailsSource extends
    J2eeBasedPreAuthenticatedWebAuthenticationDetailsSource {

  protected final Logger log = Logger.getLogger(getClass());

  MappableAttributesRetriever mappableAttributesRetriever;

  public RolesBasedPreAuthenticatedWebAuthenticationDetailsSource() {
    super();
  }

  @Override
  protected Collection<String> getUserRoles(HttpServletRequest request) {

    if (request.getUserPrincipal() == null) {
      log.info("TTTTTTTTTT PRINCIPAL NULL ");
    } else {
      log.info("TTTTTTTTTT USER = " + request.getUserPrincipal().getName());
    }
    
    Authentication au = SecurityContextHolder.getContext().getAuthentication();
    if (au == null) {
      log.info("TTTTTTTTTT AU = NULL");
    } else {
      log.info("TTTTTTTTTT AU = " + au.getName());
    }

    Set<String> roles = mappableAttributesRetriever.getMappableAttributes();
    
    log.info("TTTTTTTTTT ROLES = " + roles.size());
    
    Set<String> j2eeUserRolesList = new HashSet<String>();
    for (String role : roles) {
      log.info("TTTTTTTTTT ROLE = " + role);
      if (request.isUserInRole(role)) {
        log.info("TTTTTTTTTT isUserInRole = " + role);
        j2eeUserRolesList.add(role);
      }
    }
    return j2eeUserRolesList;
  }

  @Override
  public void setMappableRolesRetriever(MappableAttributesRetriever mappableAttributesRetriever) {
    this.mappableAttributesRetriever = mappableAttributesRetriever;
  }

}
