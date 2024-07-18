package es.caib.portafib.back.controller.dest;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.AbstractEstatDeFirmaDestDeleColaController;
import es.caib.portafib.back.form.webdb.EstatDeFirmaFilterForm;
import es.caib.portafib.utils.ConstantsV2;

/**
 * @author anadal
 */
public abstract class EstatFirmaAbstractDestController extends AbstractEstatDeFirmaDestDeleColaController {

    @Override
    public final String getBaseEntityNameCode() {
        return "solicituddefirma.llistat";
    }

    @Override
    public final String getRole() {
        return ConstantsV2.ROLE_DEST;
    }

    @Override
    public EstatDeFirmaFilterForm getEstatDeFirmaFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {

        // Lleva "Llistat de" del Títol
        EstatDeFirmaFilterForm ff = super.getEstatDeFirmaFilterForm(pagina, mav, request);

        ff.setTitleCode(getEntityNameCodePlural());

        return ff;
    }

} // Final de Classe
