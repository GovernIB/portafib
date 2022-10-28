package es.caib.portafib.back.controller.aden;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import es.caib.portafib.model.fields.UsuariAplicacioConfiguracioFields;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.webdb.PerfilDeFirmaController;
import es.caib.portafib.back.form.webdb.PerfilDeFirmaFilterForm;
import es.caib.portafib.back.form.webdb.PerfilDeFirmaForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.persistence.PerfilDeFirmaJPA;
import es.caib.portafib.model.fields.PerfilDeFirmaFields;
import es.caib.portafib.model.fields.PerfilDeFirmaQueryPath;

/**
 * 
 * @author anadal(u80067)
 * @author areus
 */
@Controller
@RequestMapping(value = PerfilDeFirmaAdenController.CONTEXT_WEB)
@SessionAttributes(types = { PerfilDeFirmaForm.class, PerfilDeFirmaFilterForm.class })
public class PerfilDeFirmaAdenController extends PerfilDeFirmaController {

  public static final String CONTEXT_WEB = "/aden/perfildefirma";

  @Override
  public String getTileForm() {
    return "perfilDeFirmaFormAden";
  }

  @Override
  public String getTileList() {
    return "perfilDeFirmaListAden";
  }

  @Override
  public String getSessionAttributeFilterForm() {
    return "PerfilDeFirmaAden_FilterForm";
  }

  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    // Els que tenguin Configuracion de Firma amb entitat aquesta
    String entitatID = LoginInfo.getInstance().getEntitatID();

    PerfilDeFirmaQueryPath qp = new PerfilDeFirmaQueryPath();

    return qp.CONFIGURACIODEFIRMA1().ENTITATID().equal(entitatID);

  }

  @Override
  public PerfilDeFirmaFilterForm getPerfilDeFirmaFilterForm(Integer pagina, ModelAndView mav,
      HttpServletRequest request) throws I18NException {
    PerfilDeFirmaFilterForm perfilDeFirmaFilterForm;
    perfilDeFirmaFilterForm = super.getPerfilDeFirmaFilterForm(pagina, mav, request);
    if (perfilDeFirmaFilterForm.isNou()) {
      Set<Field<?>> hiddenFields = new HashSet<Field<?>>(
          Arrays.asList(PerfilDeFirmaFields.ALL_PERFILDEFIRMA_FIELDS));
      hiddenFields.remove(NOM);
      hiddenFields.remove(CODI);
      hiddenFields.remove(DESCRIPCIO);

      perfilDeFirmaFilterForm.setHiddenFields(hiddenFields);

      perfilDeFirmaFilterForm.setDeleteSelectedButtonVisible(false);

      perfilDeFirmaFilterForm.setVisibleMultipleSelection(false);

      perfilDeFirmaFilterForm.addAdditionalButton(new AdditionalButton(
          "fas fa-info-circle icon-white", "ajuda.titol", "javascript:window.open('"
              + request.getContextPath()
              + "/img/perfil_i_configuracio_de_firma.png', '_blank');", "btn-info"));
    }

    return perfilDeFirmaFilterForm;
  }

  @Override
  public PerfilDeFirmaForm getPerfilDeFirmaForm(PerfilDeFirmaJPA _jpa, boolean __isView,
      HttpServletRequest request, ModelAndView mav) throws I18NException {
    PerfilDeFirmaForm form = super.getPerfilDeFirmaForm(_jpa, __isView, request, mav);

    
    form.addHelpToField(URLBASE, 
        I18NUtils.tradueix("perfildefirma.urlbase.ajuda"));
    
    
    
    if (__isView) {
      
      // Posam botons d'edició i d'accés directe a Configuracions
      PerfilDeFirmaJPA perfil = form.getPerfilDeFirma();
      
      form.addAdditionalButton(new AdditionalButton("far fa-edit","genapp.edit",
          CONTEXT_WEB + "/" + perfil.getUsuariAplicacioPerfilID()
              + "/edit", "btn-warning"));
      
      
      Long[] configuracions = new Long[] {
          perfil.getConfiguracioDeFirma1ID(),
          perfil.getConfiguracioDeFirma2ID(),
          perfil.getConfiguracioDeFirma3ID(),
          perfil.getConfiguracioDeFirma4ID(),          
          perfil.getConfiguracioDeFirma5ID()
      };
      
      for (int i = 0; i < configuracions.length; i++) {
        
        if (configuracions[i] != null) {
           form.addAdditionalButton(new AdditionalButton("fas fa-info-circle","Configuració " + (i +1),
            ConfiguracioDeFirmaAdenController.CONTEXT_WEB + "/view/" + configuracions[i]
              , "btn-info"));
        }
      }
      
      form.setCancelButtonVisible(false);
      /*
      + ConfiguracioDeFirmaAdenController.CONTEXT_WEB
      + "/"
      + perfil.getConfiguracioDeFirma1ID()
      + "/edit\"> 
      */
      
    }
    
    
    

    return form;
  }

  @Override
  public void postValidate(HttpServletRequest request, PerfilDeFirmaForm perfilDeFirmaForm,
      BindingResult result) throws I18NException {

    PerfilDeFirmaJPA perfil = perfilDeFirmaForm.getPerfilDeFirma();

    if (perfil.getConfiguracioDeFirma2ID() != null
        || perfil.getConfiguracioDeFirma3ID() != null
        || perfil.getConfiguracioDeFirma4ID() != null
        || perfil.getConfiguracioDeFirma5ID() != null) {
      // Condicio no ha de valer null
      if (perfil.getCondicio() == null) {
        result.rejectValue(get(CONDICIO), "genapp.validation.required",
            new String[] { I18NUtils.tradueix(get(CONDICIO)) }, null);
      }

    }

  }

  @Override
  public List<StringKeyValue> getReferenceListForConfiguracioDeFirma1ID(HttpServletRequest request, ModelAndView mav, Where where) throws I18NException {
    Where newWhere = newWhereForConfiguracioUsuariAplicacio(where);
    return super.getReferenceListForConfiguracioDeFirma1ID(request, mav, newWhere);
  }

  @Override
  public List<StringKeyValue> getReferenceListForConfiguracioDeFirma2ID(HttpServletRequest request, ModelAndView mav, Where where) throws I18NException {
    Where newWhere = newWhereForConfiguracioUsuariAplicacio(where);
    return super.getReferenceListForConfiguracioDeFirma2ID(request, mav, newWhere);
  }

  @Override
  public List<StringKeyValue> getReferenceListForConfiguracioDeFirma3ID(HttpServletRequest request, ModelAndView mav, Where where) throws I18NException {
    Where newWhere = newWhereForConfiguracioUsuariAplicacio(where);
    return super.getReferenceListForConfiguracioDeFirma3ID(request, mav, newWhere);
  }

  @Override
  public List<StringKeyValue> getReferenceListForConfiguracioDeFirma4ID(HttpServletRequest request, ModelAndView mav, Where where) throws I18NException {
    Where newWhere = newWhereForConfiguracioUsuariAplicacio(where);
    return super.getReferenceListForConfiguracioDeFirma4ID(request, mav, newWhere);
  }

  @Override
  public List<StringKeyValue> getReferenceListForConfiguracioDeFirma5ID(HttpServletRequest request, ModelAndView mav, Where where) throws I18NException {
    Where newWhere = newWhereForConfiguracioUsuariAplicacio(where);
    return super.getReferenceListForConfiguracioDeFirma5ID(request, mav, newWhere);
  }

  protected Where newWhereForConfiguracioUsuariAplicacio(Where where) {
    String entitatID = LoginInfo.getInstance().getEntitatID();
    Where newWhere = Where.AND(where,
        UsuariAplicacioConfiguracioFields.ENTITATID.equal(entitatID),
        UsuariAplicacioConfiguracioFields.ESDEPETICIO.equal(false));
    return newWhere;
  }
}
