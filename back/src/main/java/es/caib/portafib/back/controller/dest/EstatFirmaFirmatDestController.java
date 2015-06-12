package es.caib.portafib.back.controller.dest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.form.webdb.EstatDeFirmaFilterForm;
import es.caib.portafib.utils.Constants;

/**
 * Controller per gestionar un EstatDeFirma Firmat
 * 
 * @author anadal
 */
@Controller
@RequestMapping(value = Constants.CONTEXT_DEST_ESTATFIRMA_FIRMAT )
@SessionAttributes(types = { EstatDeFirmaFilterForm.class })
public class EstatFirmaFirmatDestController extends  EstatFirmaAbstractDestController {

  
  @Override
  public String getBaseEntityNameCode() {
    return "solicituddefirma.llistat.firmat";
  }
  
  
  @Override
  public int getFilterType() {   
    return FILTRAR_PER_ACCEPTAT; // == FIRMAT
  }
  
}
