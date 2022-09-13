package es.caib.portafib.back.controller.admin;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import es.caib.portafib.ejb.PeticioDeFirmaService;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.webdb.FluxDeFirmesController;

import es.caib.portafib.back.form.webdb.FluxDeFirmesFilterForm;
import es.caib.portafib.back.form.webdb.FluxDeFirmesForm;
import es.caib.portafib.ejb.PlantillaFluxDeFirmesService;
import es.caib.portafib.logic.FluxDeFirmesLogicaLocal;
import es.caib.portafib.model.entity.FluxDeFirmes;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.model.fields.PlantillaFluxDeFirmesFields;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/admin/fluxosorfes")
@SessionAttributes(types = { FluxDeFirmesForm.class, FluxDeFirmesFilterForm.class })
public class FluxDeFirmesOrfesController extends FluxDeFirmesController {

  @EJB(mappedName = FluxDeFirmesLogicaLocal.JNDI_NAME)
  private FluxDeFirmesLogicaLocal fluxDeFirmesLogicaEjb;

  @EJB(mappedName = PeticioDeFirmaService.JNDI_NAME)
  protected PeticioDeFirmaService peticioDeFirmaEjb;

  @EJB(mappedName = PlantillaFluxDeFirmesService.JNDI_NAME)
  private PlantillaFluxDeFirmesService plantillaFluxDeFirmesEjb;

  @Override
  public String getTileList() {
    return "fluxDeFirmesOrfes";
  }

  @Override
  public boolean isActiveFormNew() {
    return false;
  }

  @Override
  public boolean isActiveFormEdit() {
    return false;
  }

  @Override
  public void delete(HttpServletRequest request, FluxDeFirmes fluxDeFirmes) throws I18NException {
    fluxDeFirmesLogicaEjb.deleteFull(fluxDeFirmes.getFluxDeFirmesID());
  }
  
  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    
    Where w1 = FLUXDEFIRMESID.notIn(peticioDeFirmaEjb.getSubQuery(
        PeticioDeFirmaFields.FLUXDEFIRMESID, null));
    Where w2 = FLUXDEFIRMESID.notIn(plantillaFluxDeFirmesEjb.getSubQuery(
        PlantillaFluxDeFirmesFields.FLUXDEFIRMESID, null));
   
    return Where.AND(w1,w2);
  }
  
  @Override
  public FluxDeFirmesFilterForm getFluxDeFirmesFilterForm(Integer pagina, ModelAndView mav,
      HttpServletRequest request) throws I18NException {
      FluxDeFirmesFilterForm fluxDeFirmesFilterForm;
      fluxDeFirmesFilterForm = super.getFluxDeFirmesFilterForm(pagina, mav, request);
      if(fluxDeFirmesFilterForm.isNou()) {
        fluxDeFirmesFilterForm.setAddButtonVisible(false);
        fluxDeFirmesFilterForm.setEditButtonVisible(false);
        fluxDeFirmesFilterForm.setDefaultOrderBy(new OrderBy[] { new OrderBy(FLUXDEFIRMESID) });
        fluxDeFirmesFilterForm.setTitleCode("fluxos.orfes");
      }
      return fluxDeFirmesFilterForm;
  }
  
  @Override
  public String getSessionAttributeFilterForm() {
    return "FluxDeFirmesOrfes_FilterForm";
  }
  
}
