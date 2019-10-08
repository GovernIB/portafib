package es.caib.portafib.back.controller.common.destinatariextern;

import javax.annotation.security.RunAs;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.portafib.back.controller.dest.EstatFirmaAbstractDestController;
import es.caib.portafib.back.form.webdb.EstatDeFirmaFilterForm;
import es.caib.portafib.utils.ConstantsV2;

/**
 * Controller per gestionar Firmes d'un Usuari Extern
 * 
 * @author anadal
 */
@Controller
@RequestMapping(value = ConstantsV2.CONTEXT_EXTERNALUSER_ESTATDEFIRMA)
@SessionAttributes(types = { EstatDeFirmaFilterForm.class })
@RunAs("PFI_USER")
public class DestinatariExternEstatFirmaPendentController extends
    EstatFirmaAbstractDestController {

  @Override
  public int getFilterType() {
    return FILTRAR_PER_PENDENT;
  }

  @Override
  public String getFullViewTile() {
    return "externaluser_estatFirmaFullView";
  }
  
  

  /**
   * Llistat de totes EstatDeFirma
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request, HttpServletResponse response)
      throws I18NException {
    return "redirect:" + ConstantsV2.CONTEXT_EXTERNALUSER_TOKEN + "/final";
  }
  
  
  @RequestMapping(value = "/rebutjar/{estatDeFirmaID}/{peticioDeFirmaID}")
  public ModelAndView rebutjar(HttpServletRequest request, HttpServletResponse response,
      @PathVariable Long estatDeFirmaID, @PathVariable Long peticioDeFirmaID) throws I18NException {

    if (rebutjarInternal(request, response, estatDeFirmaID, peticioDeFirmaID)) {
      return new ModelAndView(new RedirectView(ConstantsV2.CONTEXT_EXTERNALUSER_TOKEN + "/final", true));
    } else {
      String token = (String)request.getSession().getAttribute(DestinatariExternByTokenController.EXTERNAL_USER_TOKEN);
      // XYZ ZZZ ZZZ
      ModelAndView model = new ModelAndView("externaluser_showerror");
      model.addObject("token", token);
      // XYZ ZZZ ZZZ
      model.addObject("error", "S'ha produït un error durant el procés de rebuig.");
      return model;
    }
  }

}
