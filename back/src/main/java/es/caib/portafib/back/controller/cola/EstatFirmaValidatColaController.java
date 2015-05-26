package es.caib.portafib.back.controller.cola;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.form.webdb.EstatDeFirmaFilterForm;
import es.caib.portafib.utils.Constants;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = Constants.CONTEXT_COLA_ESTATFIRMA_VALIDAT )
@SessionAttributes(types = { EstatDeFirmaFilterForm.class })
public class EstatFirmaValidatColaController extends EstatFirmaAbstractColaController {

  @Override
  public String getBaseEntityNameCode() {
    return "solicitudvalidada";
  }
  
  
  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    Where pare = super.getAdditionalCondition(request);
    
    Where fill = TIPUSESTATDEFIRMAFINALID.equal(Constants.TIPUSESTATDEFIRMAFINAL_VALIDAT);
    
    return Where.AND(pare, fill);
  }

  @Override  
  public EstatDeFirmaFilterForm getEstatDeFirmaFilterForm(Integer pagina, ModelAndView mav,
      HttpServletRequest request) throws I18NException {

    EstatDeFirmaFilterForm ff = super.getEstatDeFirmaFilterForm(pagina, mav, request);

    if (ff.isNou()) {
      ff.addHiddenField(TIPUSESTATDEFIRMAFINALID);
      
      if (ff.getGroupByFields() != null) {
        ff.getGroupByFields().remove(TIPUSESTATDEFIRMAFINALID);
      }
    }
    
    return ff;
  }


  @Override
  public int getFilterType() {
    // TODO Auto-generated method stub
    return FILTRAR_PER_ACCEPTAT; // == Validat
  }

}
