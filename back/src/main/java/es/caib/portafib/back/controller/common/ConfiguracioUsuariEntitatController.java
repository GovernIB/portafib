package es.caib.portafib.back.controller.common;

import es.caib.portafib.back.controller.admin.GestioEntitatController;
import es.caib.portafib.back.controller.webdb.UsuariEntitatController;
import es.caib.portafib.back.form.webdb.UsuariEntitatForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.logic.PropietatGlobalLogicaLocal;
import es.caib.portafib.model.entity.Entitat;
import es.caib.portafib.model.entity.UsuariEntitat;
import es.caib.portafib.model.entity.UsuariPersona;
import es.caib.portafib.utils.ConstantsV2;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created 18/06/13 13:08
 * 
 * @author mgonzalez
 * @author anadal
 */

@Controller
@RequestMapping(value = "/common/configuracio/usuarientitat")
public class ConfiguracioUsuariEntitatController extends UsuariEntitatController {

  @EJB(mappedName = "portafib/EntitatEJB/local")
  protected es.caib.portafib.ejb.EntitatLocal entitatEjb;
  
  
  @EJB(mappedName = PropietatGlobalLogicaLocal.JNDI_NAME)
  protected PropietatGlobalLogicaLocal propietatEjb;

  @Override
  public String getTileForm() {
    return "configuracioUsuariEntitatForm";
  }

  
  @Override
  public UsuariEntitatForm getUsuariEntitatForm(UsuariEntitatJPA _jpa, boolean __isView,
      HttpServletRequest request, ModelAndView mav) throws I18NException {

    UsuariEntitatForm usuariEntitatForm = super.getUsuariEntitatForm(_jpa, __isView, request,
        mav);

    // Obtenim l'usuari entitat logueat
    LoginInfo loginInfo = LoginInfo.getInstance();
    String usuariEntitatID = loginInfo.getUsuariEntitatID();

    // Obtenim l'usuarientitat carregat al form
    UsuariEntitat ue = usuariEntitatForm.getUsuariEntitat();
    UsuariPersona up = loginInfo.getUsuariPersona();

    // Comprovam que no es modifiqui un usuari que no es amb el que t'has
    // logueat
    if (!ue.getUsuariEntitatID().equals(usuariEntitatID)) {
      // TODO Traduir
      throw new I18NException("error.unknown",
          "No es pot modificar un usuari persona que no és el teu");
    }

    // Ocultam camps
    usuariEntitatForm.addHiddenField(USUARIENTITATID);
    usuariEntitatForm.addHiddenField(USUARIPERSONAID);
    usuariEntitatForm.addReadOnlyField(ENTITATID);
    usuariEntitatForm.addHiddenField(ACTIU);
    usuariEntitatForm.addHiddenField(CARREC);
    usuariEntitatForm.addReadOnlyField(POTCUSTODIAR);
    usuariEntitatForm.addReadOnlyField(POLITICACUSTODIA);
    usuariEntitatForm.addReadOnlyField(POLITICADEPLUGINFIRMAWEB);
    
    if (!propietatEjb.getBooleanProperty(
        ConstantsV2.PORTAFIB_PROPERTY_BASE + "editableuser", false)) {
      
      usuariEntitatForm.addReadOnlyField(EMAIL);
      usuariEntitatForm.addReadOnlyField(LOGOSEGELLID);
    }

    // Ocultam boto Cancelar i Borrar
    usuariEntitatForm.setCancelButtonVisible(false);
    usuariEntitatForm.setDeleteButtonVisible(false);

    // Posar titol
    usuariEntitatForm.setTitleCode("configuracio_usuari_entitat");
    usuariEntitatForm.setSubTitleCode("=" + I18NUtils.tradueix(
        "configuracio_usuari_entitat.subtitol", 
        LoginInfo.getInstance().getEntitat().getNom()));
    
    usuariEntitatForm.addHelpToField(EMAIL,
        I18NUtils.tradueix("configuracio_usuari_entitat.help.email"));

    // Fixam la llista de usuaripersona amb l'usuari persona indicat
    StringKeyValue skvUP = new StringKeyValue(up.getUsuariPersonaID(), up.getNom() + " "
        + up.getLlinatges() + " (" + up.getNif() + ")");
    List<StringKeyValue> lskvUP = new ArrayList<StringKeyValue>(1);
    lskvUP.add(skvUP);
    usuariEntitatForm.setListOfUsuariPersonaForUsuariPersonaID(lskvUP);

    // Fixam la llista d'entitats amb l'entitat actual
    Entitat entitat = entitatEjb.findByPrimaryKey(ue.getEntitatID());
    StringKeyValue skvEntitat = new StringKeyValue(entitat.getEntitatID(), entitat.getNom());
    List<StringKeyValue> lskvEntitat = new ArrayList<StringKeyValue>(1);
    lskvEntitat.add(skvEntitat);
    usuariEntitatForm.setListOfEntitatForEntitatID(lskvEntitat);

    return usuariEntitatForm;

  }
  
  // No permetem que es crei un de nou, estam a configuració
  @Override
  public boolean isActiveFormNew() {
    return false;
  }
  

  @Override
  public boolean isActiveList() {
    return false;
  }

  @Override
  public String getRedirectWhenModified(HttpServletRequest request, UsuariEntitatForm usuariEntitatForm, Throwable __e) {
    return "redirect:" + getContextWeb() + "/"
        + usuariEntitatForm.getUsuariEntitat().getUsuariEntitatID() + "/edit";
  }

  /**
   * #165
   */
  @Override
  public List<StringKeyValue> getReferenceListForPoliticaCustodia(
      HttpServletRequest request, ModelAndView mav, Where where)
      throws I18NException {
    final boolean isEntitat = false;
    return GestioEntitatController.staticGetReferenceListForPoliticaCustodia(isEntitat);
  }

  


  /**
   * #173
   */
  @Override
  public List<StringKeyValue> getReferenceListForPoliticaDePluginFirmaWeb(
      HttpServletRequest request, ModelAndView mav, Where where)
      throws I18NException {
    return staticGetReferenceListForPoliticaDePluginFirmaWeb();
  }

  public static List<StringKeyValue> staticGetReferenceListForPoliticaDePluginFirmaWeb() {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    
    for (int i = 0; i < ConstantsV2.POLITICA_PLUGIN_FIRMA_WEB.length; i++) {
      __tmp.add(new StringKeyValue(String.valueOf(ConstantsV2.POLITICA_PLUGIN_FIRMA_WEB[i]),
          I18NUtils.tradueix("usuarientitat.politicapluginfirmaweb." + ConstantsV2.POLITICA_PLUGIN_FIRMA_WEB[i])));
    }

    return __tmp;
  }
  
  
}
