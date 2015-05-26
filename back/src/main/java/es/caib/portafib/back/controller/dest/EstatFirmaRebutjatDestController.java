package es.caib.portafib.back.controller.dest;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.form.webdb.EstatDeFirmaFilterForm;
import es.caib.portafib.model.fields.ColaboracioDelegacioFields;
import es.caib.portafib.model.fields.EstatDeFirmaQueryPath;
import es.caib.portafib.utils.Constants;

/**
 * Controller per gestionar els EstatDeFirma Rebutjat
 * 
 * @author anadal
 */
@Controller
@RequestMapping(value = Constants.CONTEXT_DEST_ESTATFIRMA_REBUTJAT )
@SessionAttributes(types = { EstatDeFirmaFilterForm.class })
public class EstatFirmaRebutjatDestController extends  EstatFirmaAbstractDestController {
  
  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
   
    return Where.AND(
      super.getAdditionalCondition(request), 
      Where.OR(
                // El propi usuari (destinatari o delegat) ha rebutjat el document
                TIPUSESTATDEFIRMAFINALID.equal(TIPUSESTATDEFIRMAFINAL_REBUTJAT),
                // Algun altre usuari (delegat o destinatari/delegat) ha rebutjat el document
                Where.AND(
                    TIPUSESTATDEFIRMAFINALID.equal(TIPUSESTATDEFIRMAFINAL_DESCARTAT),
                    new EstatDeFirmaQueryPath().FIRMA().FITXERFIRMATID().isNull() )
                    )  
       );
  }
  
  
  @Override
  public String getBaseEntityNameCode() {
    return "solicituddefirma.llistat.rebutjat";
  }
  
  //TODO Això ho ha de fer la classe pare
  @Override
  public EstatDeFirmaFilterForm getEstatDeFirmaFilterForm(Integer pagina, ModelAndView mav,
      HttpServletRequest request) throws I18NException {

    EstatDeFirmaFilterForm ff = super.getEstatDeFirmaFilterForm(pagina, mav, request);

    if (ff.isNou()) {
      ff.addHiddenField(TIPUSESTATDEFIRMAFINALID);
      // Es la forma d'indicar que no volem la columna de Col·laborador
      ff.addHiddenField(ColaboracioDelegacioFields.DESTINATARIID);
      // Descripció conté el motiu de rebuig
      ff.getHiddenFields().remove(DESCRIPCIO);
      
      ff.addLabel(DESCRIPCIO, "motiurebutjar");
      
      if (ff.getGroupByFields() != null) {
        ff.getGroupByFields().remove(TIPUSESTATDEFIRMAFINALID);
      }
    }
    
    return ff;
  }
  
 
  
  @Override
  public final int getFilterType() {   
    return FILTRAR_PER_NOACCEPTAT; // == REBUTJAT
  }
  
}
