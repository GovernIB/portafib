package es.caib.portafib.back.controller.cola;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.dest.EstatFirmaDestController;
import es.caib.portafib.back.form.webdb.EstatDeFirmaFilterForm;
import es.caib.portafib.utils.Constants;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = Constants.CONTEXT_COLA_ESTATFIRMA )
@SessionAttributes(types = { EstatDeFirmaFilterForm.class })
public class EstatFirmaColaController extends EstatFirmaDestController {

  @Override
  public String getRole() {
    return Constants.ROLE_COLA;
  }

  @Override
  public String getBaseEntityNameCode() {
    return "solicituddevalidacio";
  }
  
  
  @Override  
  public EstatDeFirmaFilterForm getEstatDeFirmaFilterForm(Integer pagina, ModelAndView mav,
      HttpServletRequest request) throws I18NException {

    EstatDeFirmaFilterForm ff = super.getEstatDeFirmaFilterForm(pagina, mav, request);
    
    if (!getBaseEntityNameCode().equals("solicituddevalidacio")) {
      ff.addHiddenField(TIPUSESTATDEFIRMAFINALID);
    }
    
    return ff;
  }

  

}
