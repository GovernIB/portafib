package es.caib.portafib.back.controller.dele;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import es.caib.portafib.back.form.webdb.EstatDeFirmaFilterForm;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = ConstantsV2.CONTEXT_DELE_ESTATFIRMA_REBUTJAT)
@SessionAttributes(types = { EstatDeFirmaFilterForm.class })
public class EstatFirmaRebutjatDeleController extends EstatFirmaAbstractDeleController {
  
  @Override
  public final int getFilterType() {   
    return FILTRAR_PER_NOACCEPTAT; // == REBUTJAT
  }

}
