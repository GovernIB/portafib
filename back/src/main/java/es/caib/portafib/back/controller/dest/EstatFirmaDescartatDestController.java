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
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
   
    return Where.AND(
      super.getAdditionalCondition(request), 
          // El propi usuari (destinatari o delegat) ha rebutjat el document
          TIPUSESTATDEFIRMAFINALID.equal(TIPUSESTATDEFIRMAFINAL_DESCARTAT)
      );
  }

  @Override
  public String getBaseEntityNameCode() {
    return "solicituddefirma.llistat.descartat";
  }

  @Override  
  public EstatDeFirmaFilterForm getEstatDeFirmaFilterForm(Integer pagina, ModelAndView mav,
      HttpServletRequest request) throws I18NException {

    EstatDeFirmaFilterForm ff = super.getEstatDeFirmaFilterForm(pagina, mav, request);

    if (ff.isNou()) {
      ff.addHiddenField(TIPUSESTATDEFIRMAFINALID);
      
      if (ff.getGroupByFields() != null) {
        ff.getGroupByFields().remove(TIPUSESTATDEFIRMAFINALID);
      }
    }
    
    return ff;
  }

  
  @Override
  public int getFilterType() {   
    return FILTRAR_PER_NODEFINIT; // == ha firmat Delegat
  }
  
}
