package es.caib.portafib.back.controller.aden;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.controller.AbstractCustodiaInfoController;
import es.caib.portafib.back.form.webdb.CustodiaInfoFilterForm;
import es.caib.portafib.back.form.webdb.CustodiaInfoForm;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Controller
@RequestMapping(value = CustodiaInfoAdenController.ADEN_CUSTODIA_CONTEXT)
@SessionAttributes(types = { CustodiaInfoForm.class, CustodiaInfoFilterForm.class })
public class CustodiaInfoAdenController extends AbstractCustodiaInfoController {

  public static final String ADEN_CUSTODIA_CONTEXT = "/aden/peticio/custodiainfo";

  /**
   * 
   */
  @Override
  public boolean isSolicitantWeb() {
    return false;
  }
}
