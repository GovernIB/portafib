package es.caib.portafib.back.controller.aden;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import es.caib.portafib.back.controller.webdb.BitacolaController;
import es.caib.portafib.back.form.webdb.BitacolaFilterForm;
import es.caib.portafib.back.form.webdb.BitacolaForm;
import es.caib.portafib.ejb.UsuariEntitatLocal;
import es.caib.portafib.model.entity.Bitacola;
import es.caib.portafib.model.fields.BitacolaFields;
import es.caib.portafib.model.fields.UsuariEntitatFields;
import es.caib.portafib.model.fields.UsuariEntitatQueryPath;
import es.caib.portafib.utils.ConstantsV2;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue;
import org.fundaciobit.genapp.common.query.StringField;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.utils.Utils;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Controller per mostrar la bitàcola d'una petició de firma
 *
 * @author areus
 */
@Controller
@RequestMapping(value = BitacolaPeticioAdenController.CONTEXT_WEB)
@SessionAttributes(types = {BitacolaForm.class, BitacolaFilterForm.class })
public class BitacolaPeticioAdenController extends AbstractBitacolaAdenController {

    public static final String CONTEXT_WEB = "/aden/bitacolapeticio";

    public static final String SESSION_PETICIOID = "bitacolapeticio_peticioID";
    public static final String SESSION_RETURNPATH = "bitacolapeticio_returnPath";

    @EJB(mappedName = UsuariEntitatLocal.JNDI_NAME)
    protected UsuariEntitatLocal usuariEntitatEjb;

    @Override
    public boolean isActiveDelete() {
        return false;
    }

    @Override
    public String getTileList() {
        return "bitacolaPeticioListAden";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "BitacolaPeticioAden_FilterForm";
    }

    @Override
    public BitacolaFilterForm getBitacolaFilterForm(Integer pagina, ModelAndView mav,
                                                HttpServletRequest request) throws I18NException {


        BitacolaFilterForm bitacolaFilterForm = super.getBitacolaFilterForm(pagina, mav, request);

        if (bitacolaFilterForm.isNou()) {

            bitacolaFilterForm.setVisibleMultipleSelection(false);
            bitacolaFilterForm.setVisibleFilterBy(false);
            bitacolaFilterForm.setVisibleGroupBy(false);
            bitacolaFilterForm.setDeleteSelectedButtonVisible(false);
            bitacolaFilterForm.setDeleteButtonVisible(false);

            bitacolaFilterForm.setGroupByFields(new ArrayList<Field<?>>());
            bitacolaFilterForm.setFilterByFields(new ArrayList<Field<?>>());

            bitacolaFilterForm.setItemsPerPage(-1);
            bitacolaFilterForm.setAttachedAdditionalJspCode(true);

            bitacolaFilterForm.addHiddenField(BitacolaFields.TIPUSOBJECTE);
            bitacolaFilterForm.addHiddenField(BitacolaFields.OBJECTEID);
            bitacolaFilterForm.addHiddenField(BitacolaFields.OBJECTESERIALITZAT);

            bitacolaFilterForm.addAdditionalButton(
                    new AdditionalButton("icon-arrow-left icon-white", "tornar",
                            CONTEXT_WEB + "/tornar", "btn-primary"));

        }

        Long peticioID = (Long) request.getSession().getAttribute(SESSION_PETICIOID);
        bitacolaFilterForm.setTitleCode("bitacolapeticio.bitacolapeticio");
        bitacolaFilterForm.setTitleParam(String.valueOf(peticioID));

        return bitacolaFilterForm;
    }

    @Override
    @RequestMapping(value = "/list/{pagina}", method = RequestMethod.GET)
    public ModelAndView llistatPaginat(HttpServletRequest request, HttpServletResponse response,
                                       @PathVariable Integer pagina) throws I18NException {
        Long peticioID = (Long) request.getSession().getAttribute(SESSION_PETICIOID);
        if (peticioID == null) {
            HtmlUtils.saveMessageError(request, I18NUtils.tradueix("bitacolapeticio.nopeticio"));
            return new ModelAndView(
                    new RedirectView("/canviarPipella/" + ConstantsV2.ROLE_ADEN, true));
        } else {
            return super.llistatPaginat(request, response, pagina);
        }
    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        Where superWhere = super.getAdditionalCondition(request);
        Long peticioID = (Long) request.getSession().getAttribute(SESSION_PETICIOID);
        Where idpeticio = BitacolaFields.OBJECTEID.equal(String.valueOf(peticioID));
        Where tipuspeticio = BitacolaFields.TIPUSOBJECTE.equal(ConstantsV2.BITACOLA_TIPUS_PETICIO);
        return Where.AND(superWhere, idpeticio, tipuspeticio);
    }

    @RequestMapping(value = "/peticio/{peticioID}", method = RequestMethod.GET)
    public ModelAndView seleccioPeticio(HttpServletRequest request, HttpServletResponse response,
                                        @PathVariable Long peticioID,
                                        @RequestParam("returnPath") String returnPath) {
        request.getSession().setAttribute(SESSION_PETICIOID, peticioID);
        request.getSession().setAttribute(SESSION_RETURNPATH, returnPath);

        return new ModelAndView(new RedirectView(CONTEXT_WEB + "/list", true));
    }

    @RequestMapping(value = "/tornar", method = RequestMethod.GET)
    public ModelAndView tornar(HttpServletRequest request, HttpServletResponse response) {
        //String url = PeticioDeFirmaAplicacioController.CONTEXT_ADEN_PETICIOFIRMA + "/list";
        String returnPath = (String) request.getSession().getAttribute(SESSION_RETURNPATH);

        request.getSession().removeAttribute(SESSION_PETICIOID);
        request.getSession().removeAttribute(SESSION_RETURNPATH);

        return new ModelAndView(new RedirectView(returnPath, true));
    }
}
