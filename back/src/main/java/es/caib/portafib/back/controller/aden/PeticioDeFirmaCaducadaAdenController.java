package es.caib.portafib.back.controller.aden;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.form.webdb.PeticioDeFirmaFilterForm;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.model.fields.PeticioDeFirmaQueryPath;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Controller
@RequestMapping(value = "/aden/peticionscaducades")
@SessionAttributes(types = { PeticioDeFirmaForm.class, PeticioDeFirmaFilterForm.class })
public class PeticioDeFirmaCaducadaAdenController extends AbstractPeticioDeFirmaAdenController {

  @Override
  public String getTileList() {
    return "peticionsDeFirmaCaducadesList";
  }

  @Override
  public String getTileForm() {
    return "peticioDeFirmaCaducadaForm";
  }

  @Override
  public String getSessionAttributeFilterForm() {
    return "PeticionsDeFirmaCaducades_FilterForm";
  }

  @Override
  public TipusSolicitant getTipusSolicitant() {
    return TipusSolicitant.SOLICITANT_TOTS;
  }
  
  @Override
  public boolean addCreateButton() {
    return false;
  }

  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

    Where w0 = super.getAdditionalCondition(request);

    Where w1 = DATACADUCITAT.lessThan(new Timestamp(Calendar.getInstance().getTimeInMillis()));
    String entitatID = LoginInfo.getInstance().getEntitatID();

    Where w2 = new PeticioDeFirmaQueryPath().USUARIAPLICACIO().ENTITATID().equal(entitatID);

    Where w3 = TIPUSESTATPETICIODEFIRMAID.in(new Integer[] {
        ConstantsV2.TIPUSESTATPETICIODEFIRMA_ENPROCES,
        ConstantsV2.TIPUSESTATPETICIODEFIRMA_PAUSAT, });
    return Where.AND(w0, w1, w2, w3);
  }

  @Override
  public PeticioDeFirmaFilterForm getPeticioDeFirmaFilterForm(Integer pagina,
      ModelAndView mav, HttpServletRequest request) throws I18NException {
    PeticioDeFirmaFilterForm peticioDeFirmaFilterForm;
    peticioDeFirmaFilterForm = super.getPeticioDeFirmaFilterForm(pagina, mav, request);
    if (peticioDeFirmaFilterForm.isNou()) {

      peticioDeFirmaFilterForm.setTitleCode("peticionscaducades.llistat");

      peticioDeFirmaFilterForm.getHiddenFields().remove(DATACADUCITAT);

    }
    return peticioDeFirmaFilterForm;
  }

  @Override
  public boolean isNomesConsulta() {
    return false;
  }

}
