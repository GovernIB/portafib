package es.caib.portafib.back.controller.dest;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.form.webdb.EstatDeFirmaFilterForm;
import es.caib.portafib.model.fields.EstatDeFirmaQueryPath;
import es.caib.portafib.utils.Constants;

/**
 * Controller per gestionar un EstatDeFirma Firmat
 * 
 * @author anadal
 */
@Controller
@RequestMapping(value = Constants.CONTEXT_DEST_ESTATFIRMA_FIRMAT )
@SessionAttributes(types = { EstatDeFirmaFilterForm.class })
public class EstatFirmaFirmatDestController extends  EstatFirmaDestController {
  
  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
   
    return Where.AND(
      super.getAdditionalCondition(request), 
      Where.OR(
          // El propi usuari (destinatari o delegat) ha firmat el document
          TIPUSESTATDEFIRMAFINALID.equal(TIPUSESTATDEFIRMAFINAL_FIRMAT),
          // Alguna altra persona (delegat o destinatari) ha firmat el document
          Where.AND(
              TIPUSESTATDEFIRMAFINALID.equal(TIPUSESTATDEFIRMAFINAL_DESCARTAT),
              new EstatDeFirmaQueryPath().FIRMA().FITXERFIRMATID().isNotNull() )
              )  
       );
  }
  
  @Override
  public String getBaseEntityNameCode() {
    return "solicituddefirma.llistat.firmat";
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
  
}
