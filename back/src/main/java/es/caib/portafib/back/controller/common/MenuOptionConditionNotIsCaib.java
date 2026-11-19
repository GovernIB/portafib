package es.caib.portafib.back.controller.common;

import javax.servlet.http.HttpServletRequest;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOptionCondition;
import es.caib.portafib.commons.utils.Configuracio;

/**
 * 
 * @author anadal (u80067)
 * 19 nov 2026 10:33:40
 */
public class MenuOptionConditionNotIsCaib implements MenuOptionCondition {

    @Override
    public boolean isVisible(HttpServletRequest request) {
        return !Configuracio.isCAIB();
    }

}
