package es.caib.portafib.back.controller.dest;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.form.webdb.EstatDeFirmaFilterForm;
import es.caib.portafib.utils.Constants;

/**
 * Controller per gestionar un EstatDeFirma Pendent
 * 
 * @author anadal
 */
@Controller
@RequestMapping(value = Constants.CONTEXT_DEST_ESTATFIRMA_PENDENT )
@SessionAttributes(types = { EstatDeFirmaFilterForm.class })
public class EstatFirmaPendentDestController extends  EstatFirmaAbstractDestController {
  
  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return Where.AND(
            TIPUSESTATDEFIRMAFINALID.isNull(),
            super.getAdditionalCondition(request)            
        );
  }
  
  
  @Override
  public String getBaseEntityNameCode() {
    return "solicituddefirma.llistat.pendent";
  }
  
  
  // TODO Això ho ha de fer la classe pare
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


  @Override
  public int getFilterType() {   
    return FILTRAR_PER_PENDENT;
  }
  
  
}
