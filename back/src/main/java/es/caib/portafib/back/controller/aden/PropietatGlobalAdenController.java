package es.caib.portafib.back.controller.aden;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.controller.admin.PropietatGlobalAdminController;
import es.caib.portafib.back.form.webdb.PropietatGlobalFilterForm;
import es.caib.portafib.back.form.webdb.PropietatGlobalForm;
import es.caib.portafib.logic.utils.PropietatsConstants;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Controller
@RequestMapping(value = "/aden/propietatglobal")
@SessionAttributes(types = { PropietatGlobalForm.class, PropietatGlobalFilterForm.class })
public class PropietatGlobalAdenController extends PropietatGlobalAdminController {

  @Override
  public String getTileForm() {
    return "propietatGlobalFormAden";
  }

  @Override
  public String getTileList() {
    return "propietatGlobalListAden";
  }

  @Override
  public String getSessionAttributeFilterForm() {
    return "PropietatEntitatAden_FilterForm";
  }

  @Override
  protected int getTipusPropietat() {
    return PropietatsConstants.TIPUS_PROPIETAT_ENTITAT;
  }

  @Override
  public String getEntityNameCode() {
    return "propietatEntitat";
  }

  @Override
  public String getEntityNameCodePlural() {
    return "propietatEntitat.plural";
  }

}
