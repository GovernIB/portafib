package es.caib.portafib.back.controller.common;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.web.menuoptions.MenuOptionCondition;

import es.caib.portafib.commons.utils.Configuracio;

/**
 * Condició per només mostrar opció de menú si estam en mode Desenvolupament 
 * @author anadal (u80067)
 * 19 nov 2026 11:16:59
 */
public class MenuOptionConditionIsDesenvolupament implements MenuOptionCondition {

    @Override
    public boolean isVisible(HttpServletRequest request) {
        return Configuracio.isDesenvolupament();
    }
}
