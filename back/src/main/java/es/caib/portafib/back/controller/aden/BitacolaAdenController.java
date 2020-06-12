package es.caib.portafib.back.controller.aden;

import javax.servlet.http.HttpServletRequest;


import es.caib.portafib.model.entity.Bitacola;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.form.webdb.BitacolaFilterForm;
import es.caib.portafib.back.form.webdb.BitacolaForm;

import java.util.List;

/**
 * 
 * @author anadal(u80067)
 * @author areus
 */
@Controller
@RequestMapping(value = "/aden/bitacola")
@SessionAttributes(types = { BitacolaForm.class, BitacolaFilterForm.class })
public class BitacolaAdenController extends AbstractBitacolaAdenController {

  private static final String OBJECTEID_REQUEST_ATTRIBUTE = "BitacolaAdenController.objecteid";

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

  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    Where superWhere = super.getAdditionalCondition(request);

    String objecteId = (String) request.getAttribute(OBJECTEID_REQUEST_ATTRIBUTE);
    if (objecteId != null) {
      superWhere = Where.AND(superWhere, OBJECTEID.equal(objecteId));
    }

    return superWhere;
  }

  @Override
  public void preList(HttpServletRequest request, ModelAndView mav, BitacolaFilterForm filterForm) throws I18NException {
    super.preList(request, mav, filterForm);
    if (filterForm.getObjecteid() != null && !filterForm.getObjecteid().isEmpty()) {
      request.setAttribute(OBJECTEID_REQUEST_ATTRIBUTE, filterForm.getObjecteid());
      filterForm.setObjecteid(null);
    }
  }

  @Override
  public void postList(HttpServletRequest request, ModelAndView mav, BitacolaFilterForm filterForm, List<Bitacola> list)
          throws I18NException {
    String objecteId = (String) request.getAttribute(OBJECTEID_REQUEST_ATTRIBUTE);
    if (objecteId != null) {
      request.removeAttribute(OBJECTEID_REQUEST_ATTRIBUTE);
      filterForm.setObjecteid(objecteId);
    }
    super.postList(request, mav, filterForm, list);
  }
}
