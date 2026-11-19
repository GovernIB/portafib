package es.caib.portafib.back.controller.common;

import javax.servlet.http.HttpServletRequest;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOptionCondition;
import es.caib.portafib.back.security.LoginInfo;

/**
 * 
 * @author anadal (u80067)
 * 19 nov 2026 10:33:40
 */
public class MenuOptionConditionHasRoleUser implements MenuOptionCondition {

    @Override
    public boolean isVisible(HttpServletRequest request) {
        return LoginInfo.getInstance().hasRole(es.caib.portafib.utils.Constants.ROLE_USER);
    }

}
