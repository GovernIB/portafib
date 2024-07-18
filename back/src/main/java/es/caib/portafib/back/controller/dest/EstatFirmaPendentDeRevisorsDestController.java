package es.caib.portafib.back.controller.dest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.form.webdb.EstatDeFirmaFilterForm;
import es.caib.portafib.model.entity.EstatDeFirma;
import es.caib.portafib.utils.ConstantsV2;

/**
 * Controller per gestionar un EstatDeFirma Pendent
 * 
 * @author anadal
 */
@Controller
@RequestMapping(value = ConstantsV2.CONTEXT_DEST_ESTATFIRMA_PENDENT + "DeRevisors")
@SessionAttributes(types = { EstatDeFirmaFilterForm.class })
public class EstatFirmaPendentDeRevisorsDestController extends EstatFirmaPendentDestController {

    @Override
    protected boolean mostrarPendentDeRevisors() {
        return true;
    }

    public String getEntityNameByFilterType() {
        return ".pendentderevisors";
    }

    @Override
    public EstatDeFirmaFilterForm getEstatDeFirmaFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {

        log.info("  =========== getEstatDeFirmaFilterForm ============================");

        // Lleva "Llistat de" del Títol
        EstatDeFirmaFilterForm ff = super.getEstatDeFirmaFilterForm(pagina, mav, request);

        ff.setAttachedAdditionalJspCode(false);
        ff.setActionsRenderer(EstatDeFirmaFilterForm.ACTIONS_RENDERER_SIMPLE_ICON_LIST);
        ff.setVisibleMultipleSelection(false);

        return ff;
    }

    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, EstatDeFirmaFilterForm filterForm,
            List<EstatDeFirma> estatDeFirmaList) throws I18NException {

        super.postList(request, mav, filterForm, estatDeFirmaList);

        log.info("  =========== postList ============================");

        Map<Object, ArrayList<AdditionalButton>> nousBotons = new HashMap<Object, ArrayList<AdditionalButton>>();

        for (Entry<Object, ArrayList<AdditionalButton>> entry : filterForm.getAdditionalButtonsByPK().entrySet()) {

            log.info(" -------------------------------------- ");
            for (AdditionalButton additionalButton : entry.getValue()) {

                if (additionalButton.getLink().contains("/fullView/")) {

                    ArrayList<AdditionalButton> al = new ArrayList<AdditionalButton>();
                    al.add(additionalButton);

                    nousBotons.put(entry.getKey(), al);
                    break;
                }
            }
        }

        filterForm.setAdditionalButtonsByPK(nousBotons);

        filterForm.getAdditionalButtons().clear();

    }

}
