package es.caib.portafib.back.controller.common;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author anadal (u80067)
 * 23 nov 2026 11:46:53
 */
public class MenuOptionConditionAuthenticated
        implements org.fundaciobit.genapp.common.web.menuoptions.MenuOptionCondition {

    @Override
    public boolean isVisible(HttpServletRequest request) throws Exception {

        Object loginInfo = request.getAttribute("loginInfo");

        return (loginInfo != null);
    }

}
