package es.caib.portafib.back.controller.soli;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.form.SeleccioFluxDeFirmesForm;
import es.caib.portafib.back.form.webdb.*;
import es.caib.portafib.logic.PropietatGlobalLogicaLocal;
import es.caib.portafib.model.entity.PeticioDeFirma;
import es.caib.portafib.persistence.PeticioDeFirmaJPA;
import es.caib.portafib.utils.ConstantsV2;

/**
 * Controller per gestionar Peticions De Firma en progres.
 * 
 * @author anadal
 */
@Controller
@RequestMapping(value = ConstantsV2.CONTEXT_SOLI_PETICIOFIRMA_ACTIVA)
@SessionAttributes(types = { SeleccioFluxDeFirmesForm.class, PeticioDeFirmaForm.class,
    PeticioDeFirmaFilterForm.class, AnnexFilterForm.class, AnnexForm.class })
public class PeticioDeFirmaActivaSoliController extends PeticioDeFirmaSoliController {
  
  public static final String FILTER_BY_TITOL_KEY = "filterbytitol";
  
  @EJB(mappedName = PropietatGlobalLogicaLocal.JNDI_NAME)
  protected PropietatGlobalLogicaLocal propietatEjb;

  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    Where pare = super.getAdditionalCondition(request);
    
    Where enprogres_pausades_noiniciades = Where.OR( 
       TIPUSESTATPETICIODEFIRMAID.equal(TIPUSESTATPETICIODEFIRMA_ENPROCES),
       TIPUSESTATPETICIODEFIRMAID.equal(TIPUSESTATPETICIODEFIRMA_PAUSAT),
       TIPUSESTATPETICIODEFIRMAID.equal(TIPUSESTATPETICIODEFIRMA_NOINICIAT));
    
    
    Where fill;
    // Seleccionar les peticions actives més les que tenen avis web a true
    fill = Where.OR(enprogres_pausades_noiniciades,
        AVISWEB.equal(true));
    return Where.AND(pare, fill);

  }


  @Override
  public final String getEntityNameCode() {
    return "peticiodefirma.activa";
  }
  
  @Override
  public PeticioDeFirmaFilterForm getPeticioDeFirmaFilterForm(Integer pagina,
      ModelAndView mav, HttpServletRequest request) throws I18NException {

    PeticioDeFirmaFilterForm peticioDeFirmaFilterForm;
    peticioDeFirmaFilterForm = super.getPeticioDeFirmaFilterForm(pagina, mav, request);

    if (peticioDeFirmaFilterForm.isNou()) {
      peticioDeFirmaFilterForm.setSubTitleCode("peticiodefirma.activa.desc");
      
      if (peticioDeFirmaFilterForm.getGroupByFields() != null) {
        peticioDeFirmaFilterForm.getGroupByFields().remove(TIPUSESTATPETICIODEFIRMAID);
      }
    }

    String filterByTitol = (String)request.getSession().getAttribute(FILTER_BY_TITOL_KEY);
    if (filterByTitol != null) {
      peticioDeFirmaFilterForm.setTitol(filterByTitol);
      request.getSession().removeAttribute(FILTER_BY_TITOL_KEY);
    }

    return peticioDeFirmaFilterForm;
  }
  
  
  @Override
  public void postList(HttpServletRequest request, ModelAndView mav,
      PeticioDeFirmaFilterForm filterForm, List<PeticioDeFirma> list) throws I18NException {
     super.postList(request, mav, filterForm, list);

     for(PeticioDeFirma pf : list) {
       if (pf.getDataFinal() != null) {
         filterForm.getHiddenFields().remove(DATAFINAL);
         return;
       }
       
     }
     
     filterForm.addHiddenField(DATAFINAL);
     
  }


  @Override
  public PeticioDeFirmaForm getPeticioDeFirmaForm(PeticioDeFirmaJPA _jpa2, boolean __isView,
          HttpServletRequest request, ModelAndView mav) throws I18NException {

      PeticioDeFirmaForm peticioDeFirmaForm = super.getPeticioDeFirmaForm(_jpa2, __isView, request, mav);
      
      boolean descripcioTipusVisible = propietatEjb
              .getBooleanPropertyByEntitat("fundaciobit", ConstantsV2.PORTAFIB_PROPERTY_BASE + "descripcioTipusVisible");
      mav.addObject("descripcioTipusVisible", descripcioTipusVisible);

      return peticioDeFirmaForm;
  }
}
