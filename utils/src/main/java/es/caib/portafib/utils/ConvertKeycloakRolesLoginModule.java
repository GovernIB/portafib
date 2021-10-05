package es.caib.portafib.utils;


import java.util.Map;
import java.util.Set;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import org.jboss.logging.Logger;
import org.jboss.security.SimpleGroup;
import org.jboss.security.SimplePrincipal;
import org.keycloak.adapters.jaas.RolePrincipal;

/**
 * Mòdul per convertir la representació dels roles que retorna Keycloak amb el format de roles que espera trobar JBoss.
 * El mòdul JAAS de Keycloak, retorna un RolePrincipal per cada role que té assignat el subject,
 * mentre que JBoss espera trobar un principal de tipus Group amb el nom "Roles", i dins aquest un Principal
 * per cada Role.
 * Aquest mòdul s'ha de posar dins la configuració JAAS a continuació del mòdul JAAS de keycloak perquè faci
 * la conversió.
 * La idea i implementació està agafada de: https://github.com/mposolda/keycloak-remote-ejb
 */
public class ConvertKeycloakRolesLoginModule implements LoginModule {

    private static final Logger LOG = Logger.getLogger(ConvertKeycloakRolesLoginModule.class);

    private Subject subject;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler,
                           Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject;
    }

    @Override
    public boolean login() throws LoginException {
        return true;
    }

    @Override
    public boolean commit() throws LoginException {
        Set<RolePrincipal> kcRoles = subject.getPrincipals(RolePrincipal.class);
        LOG.trace("commit invoked. Keycloak roles: " + kcRoles);
        SimpleGroup wfRoles = new SimpleGroup("Roles");
        for (RolePrincipal kcRole : kcRoles) {
            wfRoles.addMember(new SimplePrincipal(kcRole.getName()));
        }

        subject.getPrincipals().add(wfRoles);

        return true;
    }

    @Override
    public boolean abort() throws LoginException {
        return true;
    }

    @Override
    public boolean logout() throws LoginException {
        return true;
    }
}
