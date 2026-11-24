package es.caib.portafib.back.controller.all;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.web.menuoptions.MenuOptionCondition;

import es.caib.portafib.back.security.LoginInfo;

/**
 * 
 * @author anadal (u80067)
 * 23 nov 2026 15:05:10
 */
public class MenuOptionConditionIsUsuariExtern implements MenuOptionCondition {

    @Override
    public boolean isVisible(HttpServletRequest request) {
        return LoginInfo.getInstance().getUsuariPersona() != null
                && !LoginInfo.getInstance().getUsuariPersona().isUsuariIntern();
    }

}
