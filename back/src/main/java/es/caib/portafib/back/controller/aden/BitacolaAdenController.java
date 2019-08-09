package es.caib.portafib.back.controller.aden;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.webdb.BitacolaController;
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
public class BitacolaAdenController extends BitacolaController {

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
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    // XYZ ZZZ ZZZ falta filtar per EntitatId !!! => ISSUE #321
    return null;
  }

  @Override
  public BitacolaFilterForm getBitacolaFilterForm(Integer pagina, ModelAndView mav,
      HttpServletRequest request) throws I18NException {
    BitacolaFilterForm bitacolaFilterForm = super.getBitacolaFilterForm(pagina, mav, request);
    if (bitacolaFilterForm.isNou()) {
      bitacolaFilterForm.setAddButtonVisible(false);
      bitacolaFilterForm.setEditButtonVisible(false);

      bitacolaFilterForm.addHiddenField(BITACOLAID);

      bitacolaFilterForm.addAdditionalButtonForEachItem(new AdditionalButton("icon-info-sign",
          "genapp.viewtitle", getContextWeb() + "/view/{0}", "btn-info"));

      bitacolaFilterForm.addFilterByField(DATA);
      bitacolaFilterForm.addFilterByField(DESCRIPCIO);

      bitacolaFilterForm.addFilterByField(PETICIODEFIRMAID);
      bitacolaFilterForm.addFilterByField(USUARIAPLICACIOID);
      bitacolaFilterForm.addFilterByField(USUARIENTITATID);

      bitacolaFilterForm.setOrderBy(DATA.javaName);
      bitacolaFilterForm.setOrderAsc(false);

    }

    return bitacolaFilterForm;
  }

  @Override
  public boolean isActiveFormNew() {
    return false;
  }

  @Override
  public boolean isActiveFormEdit() {
    return false;
  }

  @Override
  public boolean isActiveFormView() {
    return true;
  }

}
