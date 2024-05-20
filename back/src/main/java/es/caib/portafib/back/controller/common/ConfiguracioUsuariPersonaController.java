package es.caib.portafib.back.controller.common;

import es.caib.portafib.back.controller.webdb.UsuariPersonaController;
import es.caib.portafib.back.form.webdb.UsuariPersonaForm;
import es.caib.portafib.back.reflist.IdiomaSuportatRefList;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.persistence.UsuariPersonaJPA;
import es.caib.portafib.logic.PropietatGlobalLogicaLocal;
import es.caib.portafib.logic.UsuariPersonaLogicaLocal;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.model.entity.UsuariPersona;
import es.caib.portafib.commons.utils.Configuracio;
import es.caib.portafib.utils.ConstantsV2;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

/**
 * Created 18/06/13 8:39
 *
 * @author mgonzalez
 * @author anadal
 */
@Controller
@RequestMapping(value= "/common/configuracio/usuaripersona")
public class ConfiguracioUsuariPersonaController extends UsuariPersonaController {

  @EJB(mappedName = PropietatGlobalLogicaLocal.JNDI_NAME)
  protected PropietatGlobalLogicaLocal propietatEjb;

  @EJB(mappedName = UsuariPersonaLogicaLocal.JNDI_NAME)
  protected UsuariPersonaLogicaLocal usuariPersonaLogicaEjb;

  @Override
  public String getTileForm() {
    return "configuracioUsuariPersonaForm";
  }

  @PostConstruct
  public void init() {
    this.idiomaRefList = new IdiomaSuportatRefList(this.idiomaRefList);
  }

  @Override
  public UsuariPersonaForm getUsuariPersonaForm(UsuariPersonaJPA _jpa, boolean __isView,
      HttpServletRequest request, ModelAndView mav) throws I18NException {

      UsuariPersonaForm form = super.getUsuariPersonaForm(_jpa,__isView, request, mav);

      // Obtenim l'usuari persona logueat
      UsuariPersonaJPA usuari = LoginInfo.getInstance().getUsuariPersona();

      // Obtenim l'usuaripersona carregat al form
      UsuariPersona up = form.getUsuariPersona();

      // Comprovam que no es modifiqui un usuari que no es amb el que t'has logueat
      if(!up.getUsuariPersonaID().equals(usuari.getUsuariPersonaID())){
          throw  new I18NException("error.unknown",
              "No es pot modificar un usuari persona que no és el teu");
      }

      // Només de lectura
      if(form.getUsuariPersona().getNif().equals("99999999R")) {
          form.getUsuariPersona().setNif("");
      } else {
          form.addReadOnlyField(NIF);
      }
      form.addReadOnlyField(USUARIPERSONAID);

      if (Configuracio.isCAIB() || request.isUserInRole(ConstantsV2.ROLE_ADMIN)
          || PropietatGlobalUtil.getDefaultEntity() != null) {
        // Podem modificar el nom i llinatge
      } else {
        form.addReadOnlyField(NOM);
        form.addReadOnlyField(LLINATGES);
      }

      form.addHiddenField(RUBRICAID);
      form.addHiddenField(USUARIINTERN);
      form.addHiddenField(CONTRASENYA);

      if(up.getEmail()!=null && !propietatEjb.getBooleanProperty(
          ConstantsV2.PORTAFIB_PROPERTY_BASE + "editableuser", false)) {
          form.addReadOnlyField(EMAIL);
      }

      // Ocultam boto Cancelar i esborrar
      form.setCancelButtonVisible(false);
      form.setDeleteButtonVisible(false);

      // Posar titol
      form.setTitleCode("configuracio_usuari_persona");

      return form;
  }

  @Override
  public String getRedirectWhenModified(HttpServletRequest request, UsuariPersonaForm usuariPersonaForm, Throwable __e) {
    return "redirect:" + getContextWeb() +"/"+usuariPersonaForm.getUsuariPersona().getUsuariPersonaID()+"/edit";
  }

  @Override
  public boolean isActiveList() {
    return false;
  }

  @Override
  public boolean isActiveFormNew() {
    return false;
  }

  @Override
  public boolean isActiveDelete() {
    return false;
  }

  @Override
  public UsuariPersonaJPA findByPrimaryKey(HttpServletRequest request, String usuariPersonaID) throws I18NException {
    UsuariPersonaJPA currentUser = LoginInfo.getInstance().getUsuariPersona();
    if (currentUser == null || !currentUser.getUsuariPersonaID().equals(usuariPersonaID)) {
      return null;
    }
    return usuariPersonaLogicaEjb.findByPrimaryKey(usuariPersonaID);
  }

  @Override
  public UsuariPersonaJPA update(HttpServletRequest request, UsuariPersonaJPA usuariPersona) throws I18NException, I18NValidationException {
    return (UsuariPersonaJPA) usuariPersonaLogicaEjb.update(usuariPersona);
  }
}
