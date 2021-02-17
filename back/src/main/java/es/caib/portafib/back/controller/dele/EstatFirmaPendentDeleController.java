package es.caib.portafib.back.controller.dele;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.form.webdb.EstatDeFirmaFilterForm;
import es.caib.portafib.utils.ConstantsV2;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = ConstantsV2.CONTEXT_DELE_ESTATFIRMA_PENDENT)
@SessionAttributes(types = { EstatDeFirmaFilterForm.class })
public class EstatFirmaPendentDeleController extends  EstatFirmaAbstractDeleController {
  
  
  @Override
  public int getFilterType() {   
    return FILTRAR_PER_PENDENT;
  }

  /**
   * Afegeix la condició addicional de que la firma a la que pertany l'estat de firma no tengui estats de firma
   * que pertanyen a revisors que encara no han acceptat
   */
  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return Where.AND(
            super.getAdditionalCondition(request),
            getWhereNoPendentRevisor());
  }


}
