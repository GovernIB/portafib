package es.caib.portafib.back.controller.aden;

import javax.servlet.http.HttpServletRequest;


import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.form.webdb.BitacolaFilterForm;
import es.caib.portafib.back.form.webdb.BitacolaForm;

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
