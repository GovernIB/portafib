package es.caib.portafib.back.controller.revi;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.AbstractEstatDeFirmaDestDeleColaController;
import es.caib.portafib.back.form.webdb.EstatDeFirmaFilterForm;
import es.caib.portafib.commons.utils.Constants;
import es.caib.portafib.model.entity.EstatDeFirma;

/**
 * Controlador abstracte per a les estadístiques de firma dels revisors.
 * @author anadal (u80067)
 * 10 mar 2026 13:46:48
 */
public abstract class EstatFirmaAbstractReviController extends AbstractEstatDeFirmaDestDeleColaController {

    @Override
    public String getBaseEntityNameCode() {
        return "revisor";
    }

    @Override
    public String getRole() {
        return Constants.ROLE_REVI;
    }


    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, EstatDeFirmaFilterForm filterForm,
            List<EstatDeFirma> estatDeFirmaList) throws I18NException {

        super.postList(request, mav, filterForm, estatDeFirmaList);

        // Issue: Columna de Revisor en pipella revisor i opcio de menú 
        // "revisions de Firma Pendents" no es mostra correctament.
        // https://github.com/GovernIB/portafib/issues/1135
        filterForm.getAdditionalFields().remove(COLUMN_REVISORS);

    }

} // Final de Classe
