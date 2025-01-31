package es.caib.portafib.back.controller.adapp;

import es.caib.portafib.back.controller.webdb.NotificacioWSController;
import es.caib.portafib.back.form.webdb.NotificacioWSFilterForm;
import es.caib.portafib.back.form.webdb.NotificacioWSForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.utils.menuoptions.MenuOption;
import es.caib.portafib.ejb.PeticioDeFirmaService;
import es.caib.portafib.ejb.UsuariAplicacioService;
import es.caib.portafib.persistence.NotificacioWSJPA;
import es.caib.portafib.utils.ConstantsV2;
import es.caib.portafib.logic.NotificacioWSLogicaLocal;
import es.caib.portafib.model.entity.NotificacioWS;
import es.caib.portafib.model.fields.UsuariAplicacioFields;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author anadal
 * @author areus
 */
@Controller
@RequestMapping(value = "/aden/notificaciows")
@SessionAttributes(types = { NotificacioWSForm.class, NotificacioWSFilterForm.class })
@MenuOption(labelCode = "notificaciows.llistat", group = ConstantsV2.ROLE_ADAPP, order=180)
public class GestioNotificacionsWSAdappController extends NotificacioWSController {

    private static final String USUARIAPLICACIOID_REQUEST_ATTRIBUTE = "GestioNotificacionsWSController.usuariAplicacioID";

    @EJB(mappedName = NotificacioWSLogicaLocal.JNDI_NAME)
    protected NotificacioWSLogicaLocal notificacioLogicaEjb;

    @EJB(mappedName = PeticioDeFirmaService.JNDI_NAME)
    protected PeticioDeFirmaService peticioDeFirmaEjb;

    @EJB(mappedName = UsuariAplicacioService.JNDI_NAME)
    protected UsuariAplicacioService usuariAplicacioEjb;

    @Override
    public boolean isActiveFormNew() {
        return false;
    }

    @Override
    public boolean isActiveFormEdit() {
        return false;
    }

    @Override
    public boolean isActiveFormView() {
        return true;
    }

    // TODO XYZ ZZZ S'ha d'emprar el DateFormatter de GenApp 

    @Override
    public NotificacioWSFilterForm getNotificacioWSFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {
        NotificacioWSFilterForm notificacioFilterForm;
        notificacioFilterForm = super.getNotificacioWSFilterForm(pagina, mav, request);
        if (notificacioFilterForm.isNou()) {

            notificacioFilterForm.setTitleCode("notificaciows.llistat");

            notificacioFilterForm.setItemsPerPage(20);

            notificacioFilterForm.setDefaultOrderBy(new OrderBy[] { new OrderBy(DATACREACIO, OrderType.DESC) });

            notificacioFilterForm.setGroupByFields(new ArrayList<Field<?>>());
            notificacioFilterForm.addGroupByField(BLOQUEJADA);
            notificacioFilterForm.addGroupByField(USUARIAPLICACIOID);
            /*
            notificacioFilterForm.addGroupByField(DATACREACIO);
             */

            //notificacioFilterForm.addFilterByField(DESCRIPCIO);
            notificacioFilterForm.addFilterByField(ERROR);
            notificacioFilterForm.addFilterByField(DATACREACIO);
            notificacioFilterForm.addFilterByField(USUARIAPLICACIOID);
            notificacioFilterForm.addFilterByField(PETICIODEFIRMAID);

            notificacioFilterForm.addHiddenField(DATAENVIAMENT);
            notificacioFilterForm.addHiddenField(DESCRIPCIO);
            notificacioFilterForm.addHiddenField(DATAERROR);
            notificacioFilterForm.addHiddenField(ERROR);

            // Noves etiquetes
            notificacioFilterForm.addLabel(BLOQUEJADA,
                    "=<i class=\"fas fa-lock\" title=\"" + I18NUtils.tradueix(BLOQUEJADA.fullName) + "\"></i>");
            notificacioFilterForm.addLabel(REINTENTS,
                    "=<i class=\"fas fa-redo\" title=\"" + I18NUtils.tradueix(REINTENTS.fullName) + "\"></i>");

            notificacioFilterForm.addAdditionalButtonForEachItem(new AdditionalButton("fas fa-eye", "veuredetalls",
                    getContextWeb() + "/view/{0}", AdditionalButtonStyle.SECONDARY));

            notificacioFilterForm.setAddButtonVisible(false);
            notificacioFilterForm.setEditButtonVisible(false);
            notificacioFilterForm.setDeleteButtonVisible(false);
            notificacioFilterForm.setDeleteSelectedButtonVisible(false);
            notificacioFilterForm.setVisibleMultipleSelection(false);
        }

        notificacioFilterForm.getAdditionalButtons().clear();

        if (notificacioLogicaEjb.isTimerRunning()) {

            long[] times = notificacioLogicaEjb.getExecutionsInfo();

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            String lef = sdf.format(new Date(times[0]));
            String le = sdf.format(new Date(times[1]));
            String ne = sdf.format(new Date(times[2]));

            HtmlUtils.saveMessageSuccess(request, I18NUtils.tradueix("notificaciows.timer.estaarrancat", lef, le, ne));

            notificacioFilterForm.addAdditionalButton(new AdditionalButton("fas fa-stop", "notificaciows.timer.aturar",
                    getContextWeb() + "/stopTimer", AdditionalButtonStyle.DANGER));

            notificacioFilterForm.addAdditionalButton(new AdditionalButton("fas fa-bell",
                    "notificaciows.timer.despertar", getContextWeb() + "/wakeupTimer", AdditionalButtonStyle.WARNING));

        } else {

            HtmlUtils.saveMessageError(request, I18NUtils.tradueix("notificaciows.timer.estaaturat"));

            notificacioFilterForm.addAdditionalButton(new AdditionalButton("fal fa-play-circle",
                    "notificaciows.timer.arrancar", getContextWeb() + "/startTimer", AdditionalButtonStyle.SUCCESS));
        }

        return notificacioFilterForm;
    }

    @Override
    public void preList(HttpServletRequest request, ModelAndView mav, NotificacioWSFilterForm filterForm)
            throws I18NException {
        super.preList(request, mav, filterForm);
        // Guardam el camp de cerca per usuariAplicacioID perquè l'hem de tractar de forma especial
        if (filterForm.getUsuariAplicacioID() != null && !filterForm.getUsuariAplicacioID().isEmpty()) {
            request.setAttribute(USUARIAPLICACIOID_REQUEST_ATTRIBUTE, filterForm.getUsuariAplicacioID());
            filterForm.setUsuariAplicacioID(null);
        }
    }

    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, NotificacioWSFilterForm filterForm,
            List<NotificacioWS> list) throws I18NException {
        // Recuperam el camp de certa per usuariAplicacioID que hem tractat de forma especial
        String usuariAplicacioID = (String) request.getAttribute(USUARIAPLICACIOID_REQUEST_ATTRIBUTE);
        if (usuariAplicacioID != null) {
            request.removeAttribute(USUARIAPLICACIOID_REQUEST_ATTRIBUTE);
            filterForm.setUsuariAplicacioID(usuariAplicacioID);
        }

        // Valors inicials, pendents del que es digui més endavant
        //filterForm.getAdditionalButtons().clear();
        filterForm.getAdditionalButtonsByPK().clear();
        filterForm.setVisibleMultipleSelection(false);

        if (list.isEmpty()) {
            return;
        }

        int count = 0;
        int[] counts = new int[4];
        Arrays.fill(counts, 0);

        int action = 0;
        boolean mostrarBotonsGlobals = true;
        for (NotificacioWS notificacio : list) {
            count++;

            action = getStatus(notificacio);

            switch (action) {

                case SHOW_ACTION_DESBLOQUEJAR:
                    filterForm.addAdditionalButtonByPK(notificacio.getNotificacioID(),
                            new AdditionalButton("fas fa-play", "notificaciows.desbloquejar",
                                    getContextWeb() + "/desbloquejar/{0}", AdditionalButtonStyle.SUCCESS));
                    filterForm.addAdditionalButtonByPK(notificacio.getNotificacioID(),
                            new AdditionalButton("fas fa-stop", "notificaciows.aturar", getContextWeb() + "/aturar/{0}",
                                    AdditionalButtonStyle.WARNING));
                break;

                case SHOW_ACTION_BLOQUEJAR:
                    filterForm.addAdditionalButtonByPK(notificacio.getNotificacioID(),
                            new AdditionalButton("fas fa-pause", "notificaciows.bloquejar",
                                    getContextWeb() + "/bloquejar/{0}", AdditionalButtonStyle.WARNING));
                    filterForm.addAdditionalButtonByPK(notificacio.getNotificacioID(),
                            new AdditionalButton("fas fa-stop", "notificaciows.aturar", getContextWeb() + "/aturar/{0}",
                                    AdditionalButtonStyle.WARNING));
                break;

                case SHOW_ACTION_ESBORRAR:
                    filterForm.addAdditionalButtonByPK(notificacio.getNotificacioID(),
                            new AdditionalButton("fas fa-trash", "genapp.delete", getContextWeb() + "/{0}/delete",
                                    AdditionalButtonStyle.DANGER));
                break;

                case SHOW_ACTION_NONE:
                    mostrarBotonsGlobals = false;
                    continue;

            }

            if (mostrarBotonsGlobals == true) {

                counts[action]++;

                if (counts[action] != count) {
                    mostrarBotonsGlobals = false;
                }
            }

        }

        if (!mostrarBotonsGlobals) {
            return;
        }

        filterForm.setVisibleMultipleSelection(true);

        String context = request.getContextPath() + getContextWeb();

        switch (action) {

            case SHOW_ACTION_DESBLOQUEJAR:
                filterForm.addAdditionalButton(new AdditionalButton("fas fa-play", "notificaciows.desbloquejar",
                        "javascript:submitTo('notificacioWSFilterForm', '" + context + "/desbloquejarSelected');",
                        AdditionalButtonStyle.SUCCESS));
                filterForm.addAdditionalButton(new AdditionalButton("fas fa-stop", "notificaciows.aturar",
                        "javascript:submitTo('notificacioWSFilterForm', '" + context + "/aturarSelected');",
                        AdditionalButtonStyle.WARNING));
            break;

            case SHOW_ACTION_BLOQUEJAR:
                filterForm.addAdditionalButton(new AdditionalButton("fas fa-pause", "notificaciows.bloquejar",
                        "javascript:submitTo('notificacioWSFilterForm', '" + context + "/bloquejarSelected');",
                        AdditionalButtonStyle.WARNING));
                filterForm.addAdditionalButton(new AdditionalButton("fas fa-stop", "notificaciows.aturar",
                        "javascript:submitTo('notificacioWSFilterForm', '" + context + "/aturarSelected');",
                        AdditionalButtonStyle.WARNING));
            break;

            case SHOW_ACTION_ESBORRAR:
                filterForm.addAdditionalButton(new AdditionalButton("fas fa-trash", "genapp.delete",
                        "javascript:openModalSubmit('" + context + "/deleteSelected','show', 'notificacioWS')",
                        AdditionalButtonStyle.DANGER));
            break;

        }

    }

    @Override
    public NotificacioWSForm getNotificacioWSForm(NotificacioWSJPA _jpa, boolean __isView, HttpServletRequest request,
            ModelAndView mav) throws I18NException {
        NotificacioWSForm notificacioForm = super.getNotificacioWSForm(_jpa, __isView, request, mav);

        if (!notificacioForm.isNou() && __isView) {

            NotificacioWSJPA notificacio = notificacioForm.getNotificacioWS();

            notificacioForm.setTitleCode("notificaciows.veure");

            int action = getStatus(notificacio);

            switch (action) {

                case SHOW_ACTION_DESBLOQUEJAR:
                    notificacioForm
                            .addAdditionalButton(new AdditionalButton("fas fa-play", "notificaciows.desbloquejar",
                                    getContextWeb() + "/desbloquejar/{0}", AdditionalButtonStyle.SUCCESS));
                    notificacioForm.addAdditionalButton(new AdditionalButton("fas fa-stop", "notificaciows.aturar",
                            getContextWeb() + "/aturar/{0}", AdditionalButtonStyle.WARNING));
                break;

                case SHOW_ACTION_BLOQUEJAR:
                    notificacioForm.addAdditionalButton(new AdditionalButton("fas fa-pause", "notificaciows.bloquejar",
                            getContextWeb() + "/bloquejar/{0}", AdditionalButtonStyle.WARNING));
                    notificacioForm.addAdditionalButton(new AdditionalButton("fas fa-stop", "notificaciows.aturar",
                            getContextWeb() + "/aturar/{0}", AdditionalButtonStyle.WARNING));
                break;

                case SHOW_ACTION_ESBORRAR:
                    notificacioForm.addAdditionalButton(new AdditionalButton("fas fa-trash", "genapp.delete",
                            getContextWeb() + "/{0}/delete", AdditionalButtonStyle.DANGER));
                break;

                case SHOW_ACTION_NONE:
                break;

            }

        } else {
            // TODO Traduir
            throw new I18NException("error.unknown", "No es poden crear ni editar les notificacions");
        }

        return notificacioForm;
    }

    public static final int SHOW_ACTION_NONE = 0;

    public static final int SHOW_ACTION_DESBLOQUEJAR = 1;

    public static final int SHOW_ACTION_BLOQUEJAR = 2;

    public static final int SHOW_ACTION_ESBORRAR = 3;

    public static int getStatus(NotificacioWS notificacio) {
        // Mostrar boto bloqueja/desbloquejar si dataenviament==null
        // i com a minim ho ha intentat una vegada
        if (notificacio.getDataEnviament() == null && notificacio.getReintents() >= 0) {
            if (notificacio.isBloquejada()) {
                return SHOW_ACTION_DESBLOQUEJAR;
            } else {
                return SHOW_ACTION_BLOQUEJAR;
            }

        } else {

            if (notificacio.getDataEnviament() != null && notificacio.isBloquejada()) {
                // Aturada, llavors l'usuari la pot esborrar si ho desitja
                return SHOW_ACTION_ESBORRAR;
            }
        }

        return SHOW_ACTION_NONE;
    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        // Seleccionar notificacions dels usuari aplicació de l'entitat del ADEN
        Where where = USUARIAPLICACIOID.in(usuariAplicacioEjb.getSubQuery(UsuariAplicacioFields.USUARIAPLICACIOID,
                UsuariAplicacioFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID())));

        // Aplicam un tractament especial a la cerca per usuariAplicacioID
        String usuariAplicacioID = (String) request.getAttribute(USUARIAPLICACIOID_REQUEST_ATTRIBUTE);
        if (usuariAplicacioID != null) {
            where = Where.AND(where, USUARIAPLICACIOID.equal(usuariAplicacioID));
        }

        return where;
    }

    @Override
    public String getTileForm() {
        return "notificacioWSForm";
    }

    @Override
    public String getTileList() {
        return "notificacioWSList";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "NotificacioWS_FilterForm";
    }

    @RequestMapping(value = "/aturar/{notificacioID}", method = RequestMethod.GET)
    public String aturarNotificacio(@PathVariable("notificacioID") java.lang.Long notificacioID,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            NotificacioWSJPA notificacio = notificacioLogicaEjb.aturarNotificacio(notificacioID);

            if (notificacio == null) {
                createMessageWarning(request, "error.notfound", notificacioID);
            }

        } catch (I18NException pe) {

            HtmlUtils.saveMessageError(request, I18NUtils.getMessage(pe));

        }

        return "redirect:" + getContextWeb() + "/list/";
    }

    @RequestMapping(value = "/aturarSelected", method = RequestMethod.POST)
    public String aturarSelected(HttpServletRequest request, HttpServletResponse response,
            @ModelAttribute NotificacioWSFilterForm filterForm) throws Exception {

        String[] seleccionats = filterForm.getSelectedItems();

        if (seleccionats != null && seleccionats.length != 0) {
            for (int i = 0; i < seleccionats.length; i++) {
                aturarNotificacio(Long.parseLong(seleccionats[i]), request, response);
            }
        }

        return "redirect:" + getContextWeb() + "/list/";
    }

    @RequestMapping(value = "/bloquejar/{notificacioID}", method = RequestMethod.GET)
    public String bloquejarNotificacio(@PathVariable("notificacioID") java.lang.Long notificacioID,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            NotificacioWSJPA notificacio = notificacioLogicaEjb.bloquejarNotificacio(notificacioID);

            if (notificacio == null) {
                createMessageWarning(request, "error.notfound", notificacioID);
            }

        } catch (I18NException pe) {
            HtmlUtils.saveMessageError(request, I18NUtils.getMessage(pe));
        }

        return "redirect:" + getContextWeb() + "/list/";

    }

    @RequestMapping(value = "/bloquejarSelected", method = RequestMethod.POST)
    public String bloquejarSelected(HttpServletRequest request, HttpServletResponse response,
            @ModelAttribute NotificacioWSFilterForm filterForm) throws Exception {

        String[] seleccionats = filterForm.getSelectedItems();

        if (seleccionats != null && seleccionats.length != 0) {
            for (int i = 0; i < seleccionats.length; i++) {
                bloquejarNotificacio(Long.parseLong(seleccionats[i]), request, response);
            }
        }

        return "redirect:" + getContextWeb() + "/list/";
    }

    @RequestMapping(value = "/desbloquejar/{notificacioID}", method = RequestMethod.GET)
    public String desbloquejarNotificacio(@PathVariable("notificacioID") java.lang.Long notificacioID,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {

            NotificacioWSJPA notificacio = notificacioLogicaEjb.desbloquejarNotificacio(notificacioID);

            if (notificacio == null) {
                createMessageWarning(request, "error.notfound", notificacioID);
            }

        } catch (I18NException pe) {

            HtmlUtils.saveMessageError(request, I18NUtils.getMessage(pe));

        }

        return "redirect:" + getContextWeb() + "/list/";
    }

    @RequestMapping(value = "/desbloquejarSelected", method = RequestMethod.POST)
    public String desbloquejarSelected(HttpServletRequest request, HttpServletResponse response,
            @ModelAttribute NotificacioWSFilterForm filterForm) throws Exception {

        String[] seleccionats = filterForm.getSelectedItems();

        if (seleccionats != null && seleccionats.length != 0) {
            for (int i = 0; i < seleccionats.length; i++) {
                desbloquejarNotificacio(Long.parseLong(seleccionats[i]), request, response);
            }
        }

        return "redirect:" + getContextWeb() + "/list/";
    }

    @RequestMapping(value = "/startTimer", method = RequestMethod.GET)
    public String startNotificacioCallBackTimer(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        notificacioLogicaEjb.startTimer();

        return "redirect:" + getContextWeb() + "/list/";
    }

    @RequestMapping(value = "/stopTimer", method = RequestMethod.GET)
    public String stopNotificacioCallBackTimer(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        notificacioLogicaEjb.stopTimer();

        return "redirect:" + getContextWeb() + "/list/";
    }

    @RequestMapping(value = "/wakeupTimer", method = RequestMethod.GET)
    public String wakeupNotificacioCallBackTimer(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        notificacioLogicaEjb.wakeupTimer();

        return "redirect:" + getContextWeb() + "/list/";
    }
}
