package es.caib.portafib.back.controller.dele;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.dest.EstatFirmaPendentDestController;
import es.caib.portafib.back.form.webdb.EstatDeFirmaFilterForm;
import es.caib.portafib.utils.Constants;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = Constants.CONTEXT_DELE_ESTATFIRMA_PENDENT)
@SessionAttributes(types = { EstatDeFirmaFilterForm.class })
public class EstatFirmaPendentDeleController extends EstatFirmaPendentDestController {

  @Override
  public String getRole() {
    return Constants.ROLE_DELE;
  }
  
  @Override  
  public EstatDeFirmaFilterForm getEstatDeFirmaFilterForm(Integer pagina, ModelAndView mav,
      HttpServletRequest request) throws I18NException {

    EstatDeFirmaFilterForm ff = super.getEstatDeFirmaFilterForm(pagina, mav, request);

    if (ff.isNou()) {
      ff.addHiddenField(TIPUSESTATDEFIRMAFINALID);
      ff.addHiddenField(DATAFI);
      
      if (ff.getGroupByFields() != null) {
        ff.getGroupByFields().remove(TIPUSESTATDEFIRMAFINALID);
        ff.getGroupByFields().remove(DATAFI);
      }
    }
    
    return ff;
  }

}
