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
public class BitacolaPeticioAdenController extends BitacolaController {

    public static final String CONTEXT_WEB = "/aden/bitacolapeticio";

    public static final int COLUMN_PERSONA = 1;

    public static final String SESSION_PETICIOID = "bitacolapeticio_peticioID";
    public static final String SESSION_RETURNPATH = "bitacolapeticio_returnPath";

    @EJB(mappedName = UsuariEntitatLocal.JNDI_NAME)
    protected UsuariEntitatLocal usuariEntitatEjb;

    @Override
    public boolean isActiveFormNew() {
        return false;
    }

    @Override
    public boolean isActiveFormEdit() {
        return false;
    }

    @Override
    public boolean isActiveDelete() {
        return false;
    }

    @Override
    public boolean isActiveFormView() {
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
            bitacolaFilterForm.setAddButtonVisible(false);
            bitacolaFilterForm.setDeleteSelectedButtonVisible(false);
            bitacolaFilterForm.setDeleteButtonVisible(false);
            bitacolaFilterForm.setEditButtonVisible(false);

            bitacolaFilterForm.setGroupByFields(new ArrayList<Field<?>>());
            List<Field<?>> filterByFields = new ArrayList<Field<?>>(bitacolaFilterForm.getDefaultFilterByFields());
            filterByFields.remove(BitacolaFields.USUARIAPLICACIOID);
            bitacolaFilterForm.setFilterByFields(filterByFields);

            bitacolaFilterForm.setItemsPerPage(-1);
            bitacolaFilterForm.setAttachedAdditionalJspCode(true);
            bitacolaFilterForm.setAttachedAdditionalJspCode(true);

            bitacolaFilterForm.addHiddenField(BitacolaFields.BITACOLAID);
            bitacolaFilterForm.addHiddenField(BitacolaFields.PETICIODEFIRMAID);
            bitacolaFilterForm.addHiddenField(BitacolaFields.USUARIENTITATID);

            {
                AdditionalField<Long, String> personaField = new AdditionalField<Long, String>();
                personaField.setCodeName(BitacolaFields.USUARIENTITATID.fullName);
                personaField.setPosition(COLUMN_PERSONA);
                personaField.setValueMap(new HashMap<Long, String>());
                bitacolaFilterForm.addAdditionalField(personaField);
            }

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
    public void postList(HttpServletRequest request, ModelAndView mav, BitacolaFilterForm filterForm, List<Bitacola> list) throws I18NException {
        super.postList(request, mav, filterForm, list);

        Map<Long, String> mapPersona = (Map<Long, String>)filterForm.getAdditionalField(COLUMN_PERSONA).getValueMap();
        mapPersona.clear();

        // CONSULTA NOMS DELS USUARIS
        UsuariEntitatQueryPath ueqp = new UsuariEntitatQueryPath();
        StringField usuarientitatidField = UsuariEntitatFields.USUARIENTITATID;
        SelectMultipleStringKeyValue smskv = new SelectMultipleStringKeyValue(
                usuarientitatidField.select,
                ueqp.USUARIPERSONA().NOM().select,
                ueqp.USUARIPERSONA().LLINATGES().select,
                ueqp.USUARIPERSONA().NIF().select);

        Set<String> usuarisEntitatID = new HashSet<String>();
        for (Bitacola bitacola: list) {
            String usuariEntitatID = bitacola.getUsuariEntitatID();
            if (usuariEntitatID != null) {
                usuarisEntitatID.add(usuariEntitatID);
            }
        }

        List<StringKeyValue> nomsUsuaris = usuariEntitatEjb.executeQuery(smskv, usuarientitatidField.in(usuarisEntitatID));
        Map<String, String> mapUsuaris = Utils.listToMap(nomsUsuaris);

        for (Bitacola bitacola: list) {
            if (bitacola.getUsuariEntitatID() != null) {
                String nomUsuari = mapUsuaris.get(bitacola.getUsuariEntitatID());
                int index = nomUsuari.lastIndexOf(' ');
                String nomComplet = nomUsuari.substring(0, index +1) + " (" + nomUsuari.substring(index + 1) + ")";
                mapPersona.put(bitacola.getBitacolaID(), nomComplet);
            }
        }
    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        Long peticioID = (Long) request.getSession().getAttribute(SESSION_PETICIOID);
        return BitacolaFields.PETICIODEFIRMAID.equal(peticioID);
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
