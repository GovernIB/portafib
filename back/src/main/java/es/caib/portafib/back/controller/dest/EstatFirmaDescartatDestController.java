package es.caib.portafib.back.controller.dest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


import es.caib.portafib.back.form.webdb.EstatDeFirmaFilterForm;
import es.caib.portafib.utils.Constants;

/**
 * Controller per gestionar els EstatDeFirma que un delegat ha firmat enlloc del destinatari
 * 
 * @author anadal
 */
@Controller
@RequestMapping(value = Constants.CONTEXT_DEST_ESTATFIRMA_DESCARTAT )
@SessionAttributes(types = { EstatDeFirmaFilterForm.class })
//TODO Aquesta classe ha de desapareixer 
public class EstatFirmaDescartatDestController extends  EstatFirmaAbstractDestController {
  


  @Override
  public String getBaseEntityNameCode() {
    return "solicituddefirma.llistat.descartat";
  }
  
  
  @Override
  public int getFilterType() {   
    return FILTRAR_PER_NODEFINIT; // == ha firmat Delegat
  }
  
}
