package es.caib.portafib.back.controller.dest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import es.caib.portafib.back.form.webdb.EstatDeFirmaFilterForm;
import es.caib.portafib.utils.ConstantsV2;


/**
 * Controller per gestionar EstatDeFirma en qualsevol estat
 * 
 * @author anadal
 */
@Controller
@RequestMapping(value = ConstantsV2.CONTEXT_DEST_ESTATFIRMA)
@SessionAttributes(types = { EstatDeFirmaFilterForm.class })
public class EstatFirmaDestController extends EstatFirmaAbstractDestController {


  @Override
  public int getFilterType() {
    return FILTRAR_PER_RES;
  }

} // Final de Classe

