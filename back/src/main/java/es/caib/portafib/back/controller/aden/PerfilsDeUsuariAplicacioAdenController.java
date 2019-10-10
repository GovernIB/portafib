package es.caib.portafib.back.controller.aden;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.model.fields.PerfilDeFirmaQueryPath;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.portafib.back.controller.webdb.PerfilsPerUsuariAplicacioController;
import es.caib.portafib.back.form.webdb.PerfilsPerUsuariAplicacioForm;
import es.caib.portafib.back.form.webdb.UsuariAplicacioRefList;
import es.caib.portafib.jpa.PerfilsPerUsuariAplicacioJPA;
import es.caib.portafib.model.fields.UsuariAplicacioFields;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Controller
@RequestMapping(value = PerfilsDeUsuariAplicacioAdenController.CONTEXT_WEB)
@SessionAttributes(types = { PerfilsPerUsuariAplicacioForm.class })
public class PerfilsDeUsuariAplicacioAdenController extends
    PerfilsPerUsuariAplicacioController {

  public static final String CONTEXT_WEB = "/aden/perfilsPerUsuariAplicacio";

  public static final String SESSION_USUARIAPLICACIOID = "SESSION_USUARIAPLICACIOID_EN_CONFIG";

  @PostConstruct
  public void init() {
    UsuariAplicacioRefList uaRefList = new UsuariAplicacioRefList(this.usuariAplicacioRefList);
    uaRefList.setSelects(new Select<?>[] { UsuariAplicacioFields.USUARIAPLICACIOID.select });
    this.usuariAplicacioRefList = uaRefList;
  }

  @Override
  public PerfilsPerUsuariAplicacioForm getPerfilsPerUsuariAplicacioForm(
      PerfilsPerUsuariAplicacioJPA _jpa, boolean __isView, HttpServletRequest request,
      ModelAndView mav) throws I18NException {
    PerfilsPerUsuariAplicacioForm perfilsPerUsuariAplicacioForm;
    perfilsPerUsuariAplicacioForm = super.getPerfilsPerUsuariAplicacioForm(_jpa, __isView,
        request, mav);

    String usuariAplicacioID = (String) request.getSession().getAttribute(
        SESSION_USUARIAPLICACIOID);
    if (usuariAplicacioID == null) {

      log.warn("S'ha cridat a crear configuració d'Aplicació però no s'ha guardat el USUARIAPLICACIOID dins la sessió.");

      mav.setView(new RedirectView(
          GestioUsuariAplicacioAdenController.GESTIO_USUARI_APLICACIO_CONTEXTWEB + "/list",
          true));
      return perfilsPerUsuariAplicacioForm;
    }

    perfilsPerUsuariAplicacioForm.getPerfilsPerUsuariAplicacio().setUsuariAplicacioID(
        usuariAplicacioID);
    perfilsPerUsuariAplicacioForm.addReadOnlyField(USUARIAPLICACIOID);

    return perfilsPerUsuariAplicacioForm;
  }

  // Filtrar Perfils per Entitat
  @Override
  public List<StringKeyValue> getReferenceListForPerfilDeFirmaID(HttpServletRequest request,
      ModelAndView mav, Where where)  throws I18NException {
    String entitatID = LoginInfo.getInstance().getEntitatID();
    Where newWhere = Where.AND(where, new PerfilDeFirmaQueryPath().CONFIGURACIODEFIRMA1().ENTITATID().equal(entitatID));
    return super.getReferenceListForPerfilDeFirmaID(request, mav, newWhere);
  }

  
  @Override
  public String getRedirectWhenCreated(HttpServletRequest request,
      PerfilsPerUsuariAplicacioForm form) {
    return getRedirectWhenCancel(request,
        form.getPerfilsPerUsuariAplicacio().getPerfilsPerUsrAppID());
  }


  @Override
  public String getRedirectWhenCancel(HttpServletRequest request,
      java.lang.Long perfilPerUsrAppID) {
    return "redirect:"
        + GestioUsuariAplicacioAdenController.GESTIO_USUARI_APLICACIO_CONTEXTWEB + "/list";
  }

  @Override
  public String getTileForm() {
    return "perfilsPerUsuariAplicacioFormAden";
  }

  @Override
  public boolean isActiveList() {
    return false;
  }

  @Override
  public boolean isActiveFormNew() {
    return true;
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

}
