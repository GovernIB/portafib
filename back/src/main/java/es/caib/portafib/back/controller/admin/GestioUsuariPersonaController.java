package es.caib.portafib.back.controller.admin;

import es.caib.portafib.back.controller.aden.GestioUsuariEntitatAdenController;
import es.caib.portafib.back.controller.webdb.UsuariPersonaController;
import es.caib.portafib.back.form.SeleccioNifForm;
import es.caib.portafib.back.form.SeleccioUsuariEntitatForm;
import es.caib.portafib.back.form.webdb.UsuariPersonaFilterForm;
import es.caib.portafib.back.form.webdb.UsuariPersonaForm;
import es.caib.portafib.back.reflist.IdiomaSuportatRefList;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.jpa.UsuariPersonaJPA;
import es.caib.portafib.logic.UsuariPersonaLogicaLocal;
import es.caib.portafib.model.entity.UsuariPersona;
import es.caib.portafib.model.fields.UsuariEntitatFields;
import es.caib.portafib.utils.Configuracio;
import es.caib.portafib.utils.Constants;

import org.fundaciobit.plugins.userinformation.UserInfo;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NTranslation;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Set;

/**
 * 
 * @author anadal
 * 
 */
@Controller
@RequestMapping(value = "/admin/usuariPersona")
@SessionAttributes(types = { UsuariPersonaForm.class, UsuariPersonaFilterForm.class,
    SeleccioNifForm.class })
public class GestioUsuariPersonaController extends UsuariPersonaController {

  @EJB(mappedName = UsuariPersonaLogicaLocal.JNDI_NAME)
  protected UsuariPersonaLogicaLocal usuariPersonaLogicaEjb;
  
  @EJB(mappedName = es.caib.portafib.ejb.UsuariEntitatLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariEntitatLocal usuariEntitatEjb;

  @Override
  public boolean isActiveList() {    
    return false;
  }

  @Override
  public String getTileForm() {
    return "gestioUsuariPersonaForm";
  }

  @Override
  public String getTileList() {
    return "gestioUsuariPersonaList";
  }

  public String getTileNif() {
    return "selectUsuariPersonaByNif";
  }

  @PostConstruct
  public void init() {
    this.idiomaRefList = new IdiomaSuportatRefList(this.idiomaRefList);
  }

  @RequestMapping(value = "/nif", method = RequestMethod.GET)
  public ModelAndView selectUsuaripersonaByNifGet() throws Exception {

    ModelAndView mav = new ModelAndView(getTileNif());
    SeleccioNifForm seleccioNifForm = new SeleccioNifForm();
    seleccioNifForm.setTitol("usuaripersona.gestio");
    seleccioNifForm.setSubtitol("usuaripersona.alta.introduirnif");
    seleccioNifForm.setCancelUrl("/canviarPipella/" + Constants.ROLE_ADEN);
    mav.addObject(seleccioNifForm);
    return mav;
  }

  @RequestMapping(value = "/nif", method = RequestMethod.POST)
  public ModelAndView selectUsuaripersonaByNifPost(SeleccioNifForm seleccioNifForm,
      BindingResult result, HttpServletRequest request) throws I18NException {

    ModelAndView mav = new ModelAndView(getTileNif());

    String nif = seleccioNifForm.getNif();

    if (result.hasErrors()) {
      log.debug("entramos aqui result con errores");
      return mav;
    }

    // Si no han introduit Nif
    if (nif == null || nif.trim().length() == 0) {
      result.rejectValue("nif", "genapp.validation.required", new Object[] { "nif" }, null);
      return mav;
    }

    // Verificar que no hi ha usuari ja creat amb aquest nif
    UsuariPersonaJPA up = usuariPersonaLogicaEjb.getUsuariPersonaIDByAdministrationID(nif);
    if (up == null) { // No existeix
      // Obtenim la informació del usuari del sistema d'autenticació.
      /*
      ILoginPlugin loginPlugin = PortaFIBPluginsManager.getLoginPluginInstance();
      UserInfo pfui = loginPlugin.getUserInfoByAdministrationID(nif);

      // No hi ha informació de l'usuari al sistema d'autenticació.
      if (pfui == null) {
        result.rejectValue("nif", "usuaripersona.senseinformacio",
            // TODO extreure nif de les traduccions
            new Object[] { "nif", nif }, null);
        return mav;
      }
      // El plugin de login no ha tornat la informació correcta.
      if (pfui.getAdministrationID() == null || pfui.getUsername() == null) {
        result.rejectValue("nif", "usuaripersona.infoincorrecta");
        return mav;
      }
      */
      UserInfo pfui;
      try {
        pfui = usuariPersonaLogicaEjb.checkAdministrationIDInUserInformationPlugin(nif);
      } catch(I18NException i18ne) {
        I18NTranslation traduccio = i18ne.getTraduccio();
        result.rejectValue("nif", traduccio.getCode(),
            I18NUtils.tradueixArguments(traduccio.getArgs()), null);
        return mav;
      }
      

      request.getSession().setAttribute("pfui", pfui);
      mav = new ModelAndView(new RedirectView(getContextWeb() + "/new", true));
      return mav;

    } else {
      mav = new ModelAndView(new RedirectView(getContextWeb() + "/" + up.getUsuariPersonaID()
          + "/edit", true));
      return mav;

    }

  }

  @Override
  public UsuariPersonaForm getUsuariPersonaForm(UsuariPersonaJPA _jpa, boolean __isView,
      HttpServletRequest request, ModelAndView mav) throws I18NException {

    UsuariPersonaForm form = super.getUsuariPersonaForm(_jpa, __isView, request, mav);
    UserInfo pfui;
    pfui = (UserInfo) request.getSession().getAttribute("pfui");
    // Obtenim de la sessió les dades del PortaFIBUserInfo
    if (form.isNou()) {
      if (pfui == null) {
        mav.setView(new RedirectView(getContextWeb() + "/nif", true));
        return new UsuariPersonaForm();
      }

      // Omplim l'usuariPersona amb les dades del PortaFIBUserInfo
      UsuariPersona up = form.getUsuariPersona();
      up.setNif(pfui.getAdministrationID().toUpperCase());
      up.setUsuariPersonaID(pfui.getUsername());
      up.setEmail(pfui.getEmail());
      up.setIdiomaID(pfui.getLanguage() == null ? Configuracio.getDefaultLanguage() : pfui
          .getLanguage());
      

      up.setLlinatges(pfui.getSurname1() + 
                         ((pfui.getSurname2()== null)? "" : (" " + pfui.getSurname2()))
                       );
      

      up.setNom(pfui.getName());

      String nomPersona = "";
      if (up.getNom() != null) {
        nomPersona = up.getNom();
      }
      if (up.getLlinatges() != null) {
        if (nomPersona.length() != 0) {
          nomPersona = nomPersona + " ";
        }
        nomPersona = nomPersona + up.getLlinatges();
      }
      HtmlUtils.saveMessageInfo(request,
          I18NUtils.tradueix("usuaripersona.verificacio", nomPersona, up.getNif()));
      // eliminam de la sessió el PortaFIBUserInfo.
      request.getSession().removeAttribute("pfui");
    } else {
      if (isAden()) {
           
        UsuariPersona up = form.getUsuariPersona();
        Long count = usuariEntitatEjb.count(Where.AND(
            UsuariEntitatFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID()),
            UsuariEntitatFields.USUARIPERSONAID.equal(up.getUsuariPersonaID())
        ));
        if (count == 0) {
          // No té usuari entitat definit, llavors afegim boto per donar-lo
          // d'alta com a usuari-entitat
          form.addAdditionalButton(new AdditionalButton("icon-plus-sign",
              "usuaripersona.crearusuarientitat",
              getContextWeb() + "/addpersontothisentity/" + up.getUsuariPersonaID(),
              "btn-success"));
        }
      }
    }


    
    
    form.addReadOnlyField(NIF);
    

    // Feim només de lectura el camp ID perque no els puguin modificar.
    form.addReadOnlyField(USUARIPERSONAID);
    form.addHiddenField(RUBRICAID);
    

    return form;
  }
  
  /**
   * NOMES PER ADEN !!!!
   * @param request
   * @param response
   * @param session
   * @param usuariPersonaID
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/addpersontothisentity/{usuariPersonaID}", method = RequestMethod.GET)
  public String addPersonToThisEntity(HttpServletRequest request, HttpServletResponse response,
      HttpSession session,  @PathVariable String usuariPersonaID) throws Exception {

    UsuariPersona up = this.usuariPersonaLogicaEjb.findByPrimaryKey(usuariPersonaID);
    
    if (up == null) {
      return getRedirectWhenCancel(usuariPersonaID);
    }

    SeleccioUsuariEntitatForm transmissioDadesForm = new SeleccioUsuariEntitatForm();
    transmissioDadesForm.setEntitatID(LoginInfo.getInstance().getEntitatID());
    transmissioDadesForm.setUp(up);
    transmissioDadesForm.setNif(up.getNif());
    
    session.setAttribute("transmissioDadesForm", transmissioDadesForm);
    
    return "redirect:" + GestioUsuariEntitatAdenController.CONTEXTWEB + "/new";

  }

  @Override
  public String getRedirectWhenCreated(UsuariPersonaForm usuariPersonaForm) {
    return  getRedirectWhenModified(usuariPersonaForm, null); 
  }

  @Override
  public String getRedirectWhenModified(UsuariPersonaForm usuariPersonaForm, Throwable __e) {
    String usuariPersonaID = usuariPersonaForm.getUsuariPersona().getUsuariPersonaID();
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/" + usuariPersonaID + "/edit";
    } else {
      return getRedirectWhenCancel(usuariPersonaID);
    }
  }

  @Override
  public String getRedirectWhenDelete(java.lang.String usuariPersonaID, Throwable __e) {
    return getRedirectWhenCancel(usuariPersonaID);
  }

  @Override
  public String getRedirectWhenCancel(java.lang.String usuariPersonaID) {
    return "redirect:/canviarPipella/" + (isAden()? Constants.ROLE_ADEN : Constants.ROLE_ADMIN);
  }

  @Override
  public void delete(HttpServletRequest request, UsuariPersona usuariPersona)
      throws Exception, I18NException {

    Set<Long> fitxers = usuariPersonaLogicaEjb.deleteFull(usuariPersona.getUsuariPersonaID());
    this.borrarFitxers(fitxers);

  }
  
  @Override
  public UsuariPersonaJPA create(UsuariPersonaJPA usuariPersona)
    throws Exception,I18NException, I18NValidationException {
    return (UsuariPersonaJPA) usuariPersonaLogicaEjb.createFull(usuariPersona);
  }
  
  public boolean isAden() {
    return false;
  }

}
