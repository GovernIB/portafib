package es.caib.portafib.back.controller.dele;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.controller.dest.EstatFirmaDescartatDestController;
import es.caib.portafib.back.form.webdb.EstatDeFirmaFilterForm;
import es.caib.portafib.utils.Constants;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = Constants.CONTEXT_DELE_ESTATFIRMA_DESCARTAT)
@SessionAttributes(types = { EstatDeFirmaFilterForm.class })
public class EstatFirmaDescartatDeleController extends EstatFirmaDescartatDestController {

  @Override
  public String getRole() {
    return Constants.ROLE_DELE;
  }
}
