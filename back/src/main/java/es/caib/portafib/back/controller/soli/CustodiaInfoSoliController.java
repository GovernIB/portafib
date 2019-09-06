package es.caib.portafib.back.controller.soli;

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
@RequestMapping(value = CustodiaInfoSoliController.SOLI_CUSTODIA_CONTEXT)
@SessionAttributes(types = { CustodiaInfoForm.class, CustodiaInfoFilterForm.class })
public class CustodiaInfoSoliController extends AbstractCustodiaInfoController {

  public static final String SOLI_CUSTODIA_CONTEXT = "/soli/peticio/custodiainfo";

  /**
   * 
   * @return
   */
  @Override
  public boolean isSolicitantWeb() {
    return true;
  }

}
