package es.caib.portafib.back.controller.rest.utils;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import es.caib.portafib.logic.UsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.utils.EjbManager;
import es.caib.portafib.persistence.EntitatJPA;
import es.caib.portafib.persistence.UsuariAplicacioJPA;

/**
 * @author anadal
 */

public class LoginInfo {

    //@EJB(mappedName = UsuariAplicacioLogicaLocal.JNDI_NAME)
    //protected UsuariAplicacioLogicaLocal usuariAplicacioLogicaEjb;

    public static LoginInfo getInstance() {
        return new LoginInfo();
    }

    public UsuariAplicacioLogicaLocal getUsuariAplicacioLogicaEjb() {
        try {
            return EjbManager.getUsuariAplicacioLogicaEJB();
        } catch (I18NException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 
     * @return
     */
    public EntitatJPA getEntitat() {
        return getUsuariAplicacio().getEntitat();
    }

    /**
     * 
     * @return
     * @throws I18NException
     */
    public UsuariAplicacioJPA getUsuariAplicacio() {

        // Call to Spring Http Request Holder
        //RequestContextHolder.getRequestAttributes().get;
        HttpServletRequest request;
        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String userapp = request.getUserPrincipal().getName();

        UsuariAplicacioJPA usuariAplicacio = getUsuariAplicacioLogicaEjb().findByPrimaryKeyFull(userapp);

        return usuariAplicacio;

    }

    public boolean hasRole(String role) {

        HttpServletRequest request;
        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        return request.isUserInRole(role);

        /*
        Collection<? extends GrantedAuthority> rols = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority grantedAuthority : rols) {
          if (grantedAuthority.getAuthority().equals(role)) {
            return true;
          }
        }
        return false;
        */
    }

}
