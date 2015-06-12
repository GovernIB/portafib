package es.caib.portafib.back.controller.cola;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.form.webdb.EstatDeFirmaFilterForm;
import es.caib.portafib.utils.Constants;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = Constants.CONTEXT_COLA_ESTATFIRMA_PENDENT)
@SessionAttributes(types = { EstatDeFirmaFilterForm.class })
public class EstatFirmaPendentColaController extends EstatFirmaAbstractColaController {

  @Override
  public String getBaseEntityNameCode() {
    return "solicitudpendent";
  }

  @Override
  public int getFilterType() {
    return FILTRAR_PER_PENDENT;
  }

}
