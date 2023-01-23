package es.caib.portafib.back.controller.soli;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.form.SeleccioFluxDeFirmesForm;
import es.caib.portafib.back.form.webdb.AnnexFilterForm;
import es.caib.portafib.back.form.webdb.AnnexForm;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaFilterForm;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaForm;
import es.caib.portafib.logic.PropietatGlobalLogicaLocal;
import es.caib.portafib.model.entity.PeticioDeFirma;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
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

    OrderBy[] order = { new OrderBy(PeticioDeFirmaFields.DATASOLICITUD) };
    peticioDeFirmaFilterForm.setDefaultOrderBy(order);
    peticioDeFirmaFilterForm.setOrderAsc(false);

    
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
      
      Boolean descripciotipusvisible = propietatEjb
              .getBooleanPropertyByEntitat("fundaciobit", ConstantsV2.PORTAFIB_PROPERTY_BASE + "descripciotipusvisible");
      
      if (descripciotipusvisible == null) {
          descripciotipusvisible = false;
      }
      
      mav.addObject("descripciotipusvisible", descripciotipusvisible);

      return peticioDeFirmaForm;
  }

  @Override
  public List<StringKeyValue> getReferenceListForTipusDocumentID(HttpServletRequest request, ModelAndView mav,
          PeticioDeFirmaForm peticioDeFirmaForm, Where where) throws I18NException {

      List<StringKeyValue> result;
      result = super.getReferenceListForTipusDocumentID(request, mav, peticioDeFirmaForm, where);
      result.add(new StringKeyValue(String.valueOf(Long.MIN_VALUE),
              I18NUtils.tradueix("peticiodefirma.tipusdocumental.seleccionar")));

      java.util.Collections.sort(result, STRINGKEYVALUE_COMPARATOR);

      peticioDeFirmaForm.getPeticioDeFirma().setTipusDocumentID(Long.MIN_VALUE);

      return result;
  }

  @Override
  public void preValidate(HttpServletRequest request, PeticioDeFirmaForm peticioDeFirmaForm, BindingResult result)
          throws I18NException {

      super.preValidate(request, peticioDeFirmaForm, result);

      PeticioDeFirma peticio = peticioDeFirmaForm.getPeticioDeFirma();

      Long tipusDocumental = peticio.getTipusDocumentID();

      if (tipusDocumental == Long.MIN_VALUE) {
          String tipusDocKey = peticioDeFirmaForm.getListOfTipusDocumentForTipusDocumentID().get(1).getKey();
          peticio.setTipusDocumentID(Long.parseLong(tipusDocKey));

          result.rejectValue(get(TIPUSDOCUMENTID), "genapp.validation.required",
                  new String[] { I18NUtils.tradueix(get(TIPUSDOCUMENTID)) }, null);
      }
  }

  @Override
  public void postValidate(HttpServletRequest request, PeticioDeFirmaForm peticioDeFirmaForm, BindingResult result)
          throws I18NException {

      super.postValidate(request, peticioDeFirmaForm, result);

      PeticioDeFirma peticio = peticioDeFirmaForm.getPeticioDeFirma();

      if (result.hasFieldErrors(get(TIPUSDOCUMENTID))) {
          peticio.setTipusDocumentID(Long.MIN_VALUE);
      }
  }

}
