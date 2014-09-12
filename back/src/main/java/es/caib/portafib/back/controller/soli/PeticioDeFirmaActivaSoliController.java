package es.caib.portafib.back.controller.soli;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.form.SeleccioFluxDeFirmesForm;
import es.caib.portafib.back.form.webdb.*;

import es.caib.portafib.utils.Constants;

/**
 * Controller per gestionar Peticions De Firma en progres.
 * 
 * @author anadal
 */
@Controller
@RequestMapping(value = Constants.CONTEXT_SOLI_PETICIOFIRMA_ACTIVA)
@SessionAttributes(types = { SeleccioFluxDeFirmesForm.class, PeticioDeFirmaForm.class,
    PeticioDeFirmaFilterForm.class, AnnexFilterForm.class, AnnexForm.class })
public class PeticioDeFirmaActivaSoliController extends PeticioDeFirmaSoliController {

  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    Where pare = super.getAdditionalCondition(request);
    
    Where enprogres_pausades_noiniciades = Where.OR( 
       TIPUSESTATPETICIODEFIRMAID.equal(TIPUSESTATPETICIODEFIRMA_ENPROCES),
       TIPUSESTATPETICIODEFIRMAID.equal(TIPUSESTATPETICIODEFIRMA_PAUSAT),
       TIPUSESTATPETICIODEFIRMAID.equal(TIPUSESTATPETICIODEFIRMA_NOINICIAT));
    
    
    Where fill;
    if (isSolicitantUsuariEntitat()) {
      // Seleccionar les peticions actives més les que tenen avis web a true
      // XYS
      /*
      fill = Where.OR(enprogres_pausades_noiniciades,
          new PeticioDeFirmaQueryPath().PETICIODEFIRMAUSUARIENTITAT().AVISWEB().equal(true));
          */
      fill = Where.OR(enprogres_pausades_noiniciades,
          AVISWEB.equal(true));
      
      
    } else {
      // Seleccionam només les peticions actives
      fill = enprogres_pausades_noiniciades;
    }
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
    return peticioDeFirmaFilterForm;
  }
  
  

}
