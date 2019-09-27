package es.caib.portafib.back.controller.aden;

import javax.servlet.http.HttpServletRequest;

import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.model.entity.Bitacola;
import es.caib.portafib.model.fields.BitacolaFields;
import es.caib.portafib.utils.ConstantsV2;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.webdb.BitacolaController;
import es.caib.portafib.back.form.webdb.BitacolaFilterForm;
import es.caib.portafib.back.form.webdb.BitacolaForm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Controller
@RequestMapping(value = "/aden/bitacola")
@SessionAttributes(types = { BitacolaForm.class, BitacolaFilterForm.class })
public class BitacolaAdenController extends AbstractBitacolaAdenController {

  @Override
  public String getTileForm() {
    return "bitacolaFormAden";
  }

  @Override
  public String getTileList() {
    return "bitacolaListAden";
  }

  @Override
  public String getSessionAttributeFilterForm() {
    return "BitacolaWebAden_FilterForm";
  }

  @Override
  public BitacolaFilterForm getBitacolaFilterForm(Integer pagina, ModelAndView mav,
      HttpServletRequest request) throws I18NException {

    return super.getBitacolaFilterForm(pagina, mav, request);
    /*
    BitacolaFilterForm bitacolaFilterForm = super.getBitacolaFilterForm(pagina, mav, request);
    if (bitacolaFilterForm.isNou()) {
    }

    return bitacolaFilterForm;
     */
  }

}
