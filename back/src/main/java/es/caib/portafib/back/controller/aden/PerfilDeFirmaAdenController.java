package es.caib.portafib.back.controller.aden;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.webdb.PerfilDeFirmaController;
import es.caib.portafib.back.form.webdb.PerfilDeFirmaFilterForm;
import es.caib.portafib.back.form.webdb.PerfilDeFirmaForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.jpa.PerfilDeFirmaJPA;
import es.caib.portafib.logic.ConfiguracioUsuariAplicacioLogicaLocal;
import es.caib.portafib.model.fields.PerfilDeFirmaFields;
import es.caib.portafib.model.fields.PerfilDeFirmaQueryPath;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Controller
@RequestMapping(value = PerfilDeFirmaAdenController.CONTEXT_WEB)
@SessionAttributes(types = { PerfilDeFirmaForm.class, PerfilDeFirmaFilterForm.class })
public class PerfilDeFirmaAdenController extends PerfilDeFirmaController {

  public static final String CONTEXT_WEB = "/aden/perfildefirma";

  @EJB(mappedName = ConfiguracioUsuariAplicacioLogicaLocal.JNDI_NAME)
  public ConfiguracioUsuariAplicacioLogicaLocal configuracioUsuariAplicacioLogicaLocalEjb;

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

      perfilDeFirmaFilterForm.addAdditionalButton(new AdditionalButton("icon-info-sign icon-white",
          "ajuda.titol", "javascript:window.open('" + request.getContextPath()
              + "/img/perfil_i_configuracio_de_firma.png', '_blank');", "btn-info"));
    }

    return perfilDeFirmaFilterForm;
  }

  @Override
  public PerfilDeFirmaForm getPerfilDeFirmaForm(PerfilDeFirmaJPA _jpa, boolean __isView,
      HttpServletRequest request, ModelAndView mav) throws I18NException {
    PerfilDeFirmaForm form = super.getPerfilDeFirmaForm(_jpa, __isView, request, mav);

    form.addReadOnlyField(CONFIGURACIODEFIRMA2ID);
    form.addReadOnlyField(CONFIGURACIODEFIRMA3ID);
    form.addReadOnlyField(CONDICIO);

    return form;
  }

 

}
