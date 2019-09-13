package es.caib.portafib.back.controller.aden;

import es.caib.portafib.back.controller.webdb.NotificacioWSController;
import es.caib.portafib.back.form.webdb.NotificacioWSFilterForm;
import es.caib.portafib.back.form.webdb.NotificacioWSForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.ejb.PeticioDeFirmaLocal;
import es.caib.portafib.jpa.NotificacioWSJPA;
import es.caib.portafib.logic.NotificacioWSLogicaLocal;
import es.caib.portafib.model.entity.NotificacioWS;
import es.caib.portafib.model.fields.UsuariAplicacioFields;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/aden/notificaciows")
@SessionAttributes(types = { NotificacioWSForm.class, NotificacioWSFilterForm.class })
public class GestioNotificacionsWSController extends NotificacioWSController {

  @EJB(mappedName = NotificacioWSLogicaLocal.JNDI_NAME)
  protected NotificacioWSLogicaLocal notificacioLogicaEjb;
  
  @EJB(mappedName = PeticioDeFirmaLocal.JNDI_NAME)
  protected PeticioDeFirmaLocal peticioDeFirmaEjb;

  @EJB(mappedName = "portafib/UsuariAplicacioEJB/local")
  protected es.caib.portafib.ejb.UsuariAplicacioLocal usuariAplicacioEjb;

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
  public static final SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

  @Override
  public NotificacioWSFilterForm getNotificacioWSFilterForm(Integer pagina, ModelAndView mav,
      HttpServletRequest request) throws I18NException {
    NotificacioWSFilterForm notificacioFilterForm;
    notificacioFilterForm = super.getNotificacioWSFilterForm(pagina, mav, request);
    if (notificacioFilterForm.isNou()) {
      
      notificacioFilterForm.setTitleCode("notificaciows.llistat");

      notificacioFilterForm.setItemsPerPage(20);

      notificacioFilterForm
          .setDefaultOrderBy(new OrderBy[] { new OrderBy(BLOQUEJADA), 
              new OrderBy(DATACREACIO, OrderType.DESC),
              new OrderBy(NOTIFICACIOID, OrderType.DESC) });

      notificacioFilterForm.addGroupByField(TIPUSNOTIFICACIOID);
      notificacioFilterForm.addGroupByField(BLOQUEJADA);
      notificacioFilterForm.addGroupByField(DATACREACIO);
      notificacioFilterForm.addGroupByField(USUARIAPLICACIOID);

      notificacioFilterForm.addFilterByField(DESCRIPCIO);
      notificacioFilterForm.addFilterByField(ERROR);
      notificacioFilterForm.addFilterByField(DATACREACIO);

      notificacioFilterForm.addHiddenField(DATAENVIAMENT);
      notificacioFilterForm.addHiddenField(DESCRIPCIO);
      notificacioFilterForm.addHiddenField(DATAERROR);
      notificacioFilterForm.addHiddenField(ERROR);
      
      // Noves etiquetes
      notificacioFilterForm.addLabel(BLOQUEJADA, "=<i class=\"icon-lock\" title=\"" 
          + I18NUtils.tradueix(BLOQUEJADA.fullName) + "\"></i>");
      notificacioFilterForm.addLabel(REINTENTS, "=<i class=\"icon-repeat\" title=\"" 
          + I18NUtils.tradueix(REINTENTS.fullName) + "\"></i>");

      notificacioFilterForm.addAdditionalButtonForEachItem(new AdditionalButton(
          "icon-eye-open", "veuredetalls", getContextWeb() + "/view/{0}", null));

      notificacioFilterForm.setAddButtonVisible(false);
      notificacioFilterForm.setEditButtonVisible(false);
      notificacioFilterForm.setDeleteButtonVisible(false);
      notificacioFilterForm.setDeleteSelectedButtonVisible(false);
      notificacioFilterForm.setVisibleMultipleSelection(false);
    }

    notificacioFilterForm.getAdditionalButtons().clear();
    
    if (notificacioLogicaEjb.isTimerRunning()) {
      
      long[] times = notificacioLogicaEjb.getExecutionsInfo();
      
      String lef = SDF.format(new Date(times[0]));
      String le = SDF.format(new Date(times[1]));
      String ne = SDF.format(new Date(times[2]));

      HtmlUtils.saveMessageSuccess(request,
          I18NUtils.tradueix("notificaciows.timer.estaarrancat", lef, le, ne));
      
      notificacioFilterForm.addAdditionalButton(new AdditionalButton(
          "icon-stop icon-white", "notificaciows.timer.aturar", getContextWeb() + "/stopTimer", "btn-danger"));
      
      notificacioFilterForm.addAdditionalButton(new AdditionalButton(
          "icon-bell icon-white", "notificaciows.timer.despertar", getContextWeb() + "/wakeupTimer", "btn-warning"));
      
    } else {

      HtmlUtils.saveMessageError(request, I18NUtils.tradueix("notificaciows.timer.estaaturat"));
      
      notificacioFilterForm.addAdditionalButton(new AdditionalButton(
          "icon-play-circle icon-white", "notificaciows.timer.arrancar", getContextWeb() + "/startTimer", "btn-success"));
    }
    
    

    return notificacioFilterForm;
  }

  @Override
  public void postList(HttpServletRequest request, ModelAndView mav,
      NotificacioWSFilterForm filterForm, List<NotificacioWS> list) throws I18NException {

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
            new AdditionalButton("icon-play", "notificaciows.desbloquejar", getContextWeb()
                + "/desbloquejar/{0}", "btn-success"));
        filterForm.addAdditionalButtonByPK(notificacio.getNotificacioID(),
            new AdditionalButton("icon-stop", "notificaciows.aturar", getContextWeb()
                + "/aturar/{0}", "btn-warning"));
        break;

      case SHOW_ACTION_BLOQUEJAR:
        filterForm.addAdditionalButtonByPK(notificacio.getNotificacioID(),
            new AdditionalButton("icon-pause", "notificaciows.bloquejar", getContextWeb()
                + "/bloquejar/{0}", "btn-warning"));
        filterForm.addAdditionalButtonByPK(notificacio.getNotificacioID(),
            new AdditionalButton("icon-stop", "notificaciows.aturar", getContextWeb()
                + "/aturar/{0}", "btn-warning"));
        break;

      case SHOW_ACTION_ESBORRAR:
        filterForm.addAdditionalButtonByPK(notificacio.getNotificacioID(),
            new AdditionalButton("icon-trash icon-white", "genapp.delete", getContextWeb()
                + "/{0}/delete", "btn-danger"));
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
      filterForm.addAdditionalButton(new AdditionalButton("icon-play",
          "notificaciows.desbloquejar", "javascript:submitTo('notificacioWSFilterForm', '"
              + context + "/desbloquejarSelected');", "btn-success"));
      filterForm.addAdditionalButton(new AdditionalButton("icon-stop", "notificaciows.aturar",
          "javascript:submitTo('notificacioWSFilterForm', '" + context + "/aturarSelected');",
          "btn-warning"));
      break;

    case SHOW_ACTION_BLOQUEJAR:
      filterForm.addAdditionalButton(new AdditionalButton("icon-pause",
          "notificaciows.bloquejar", "javascript:submitTo('notificacioWSFilterForm', '"
              + context + "/bloquejarSelected');", "btn-warning"));
      filterForm.addAdditionalButton(new AdditionalButton("icon-stop", "notificaciows.aturar",
          "javascript:submitTo('notificacioWSFilterForm', '" + context + "/aturarSelected');",
          "btn-warning"));
      break;

    case SHOW_ACTION_ESBORRAR:
      filterForm.addAdditionalButton(new AdditionalButton("icon-trash icon-white",
          "genapp.delete", "javascript:openModalSubmit('" + context
              + "/deleteSelected','show', 'notificacioWS')", "btn-danger"));
      break;

    }

  }

  @Override
  public NotificacioWSForm getNotificacioWSForm(NotificacioWSJPA _jpa, boolean __isView,
      HttpServletRequest request, ModelAndView mav) throws I18NException {
    NotificacioWSForm notificacioForm = super.getNotificacioWSForm(_jpa, __isView, request,
        mav);

    if (!notificacioForm.isNou() && __isView) {

      NotificacioWSJPA notificacio = notificacioForm.getNotificacioWS();
      
      notificacioForm.setTitleCode("notificaciows.veure");

      int action = getStatus(notificacio);

      switch (action) {

      case SHOW_ACTION_DESBLOQUEJAR:
        notificacioForm
            .addAdditionalButton(new AdditionalButton("icon-play",
                "notificaciows.desbloquejar", getContextWeb() + "/desbloquejar/{0}",
                "btn-success"));
        notificacioForm.addAdditionalButton(new AdditionalButton("icon-stop",
            "notificaciows.aturar", getContextWeb() + "/aturar/{0}", "btn-warning"));
        break;

      case SHOW_ACTION_BLOQUEJAR:
        notificacioForm.addAdditionalButton(new AdditionalButton("icon-pause",
            "notificaciows.bloquejar", getContextWeb() + "/bloquejar/{0}", "btn-warning"));
        notificacioForm.addAdditionalButton(new AdditionalButton("icon-stop",
            "notificaciows.aturar", getContextWeb() + "/aturar/{0}", "btn-warning"));
        break;

      case SHOW_ACTION_ESBORRAR:
        notificacioForm.addAdditionalButton(new AdditionalButton("icon-trash icon-white",
            "genapp.delete", getContextWeb() + "/{0}/delete", "btn-danger"));
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
    return USUARIAPLICACIOID.in(
            usuariAplicacioEjb.getSubQuery(
                  UsuariAplicacioFields.USUARIAPLICACIOID,
                  UsuariAplicacioFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID())
            )
    );
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
  public String bloquejarNotificacio(
      @PathVariable("notificacioID") java.lang.Long notificacioID, HttpServletRequest request,
      HttpServletResponse response) throws Exception {

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
  public String desbloquejarNotificacio(
      @PathVariable("notificacioID") java.lang.Long notificacioID, HttpServletRequest request,
      HttpServletResponse response) throws Exception {

    try {

      NotificacioWSJPA notificacio = notificacioLogicaEjb
          .desbloquejarNotificacio(notificacioID);

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
  public String startNotificacioCallBackTimer(HttpServletRequest request,
      HttpServletResponse response) throws Exception {

    notificacioLogicaEjb.startTimer();

    return "redirect:" + getContextWeb() + "/list/";
  }

  @RequestMapping(value = "/stopTimer", method = RequestMethod.GET)
  public String stopNotificacioCallBackTimer(HttpServletRequest request,
      HttpServletResponse response) throws Exception {

    notificacioLogicaEjb.stopTimer();

    return "redirect:" + getContextWeb() + "/list/";
  }
  
  
  @RequestMapping(value = "/wakeupTimer", method = RequestMethod.GET)
  public String wakeupNotificacioCallBackTimer(HttpServletRequest request,
      HttpServletResponse response) throws Exception {

    notificacioLogicaEjb.wakeupTimer();

    return "redirect:" + getContextWeb() + "/list/";
  }
}
