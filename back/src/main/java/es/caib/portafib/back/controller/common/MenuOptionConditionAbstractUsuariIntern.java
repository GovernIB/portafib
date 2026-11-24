package es.caib.portafib.back.controller.common;


import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.web.menuoptions.MenuOptionCondition;
import org.jboss.logging.Logger;

import es.caib.portafib.back.security.LoginInfo;

/**
 * Condició per mostrar opcions de menú només a usuaris interns.
 * @author anadal (u80067)
 * 24 nov 2026 8:54:37
 */
public abstract class MenuOptionConditionAbstractUsuariIntern implements MenuOptionCondition {
    protected final org.jboss.logging.Logger log = Logger.getLogger(getClass());
    @Override
    public boolean isVisible(HttpServletRequest request) throws Exception {
        return !(LoginInfo.getInstance().getUsuariPersona() != null
                && !LoginInfo.getInstance().getUsuariPersona().isUsuariIntern());
    }

}
