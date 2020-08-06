package es.caib.portafib.back.controller.common.destinatariextern;

import es.caib.portafib.back.controller.AbstractEstatDeFirmaDestDeleColaController;
import es.caib.portafib.back.form.webdb.EstatDeFirmaFilterForm;
import es.caib.portafib.utils.ConstantsV2;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller per gestionar Firmes d'un Usuari Extern
 * 
 * @author anadal
 */
@Controller
@RequestMapping(value = ConstantsV2.CONTEXT_EXTERNALUSER_ESTATDEFIRMA)
@SessionAttributes(types = { EstatDeFirmaFilterForm.class })
public class DestinatariExternEstatFirmaPendentController extends AbstractEstatDeFirmaDestDeleColaController {

  @Override
  public final String getBaseEntityNameCode() {
    return "solicituddefirma.llistat";
  }

  @Override
  public final String getRole() {
    return ConstantsV2.ROLE_DEST;
  }

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

    String motiuDeRebuig = request.getParameter("motiu");
    if (rebutjarInternal(request, response, estatDeFirmaID, peticioDeFirmaID, motiuDeRebuig)) {
      return new ModelAndView(new RedirectView(ConstantsV2.CONTEXT_EXTERNALUSER_TOKEN + "/final", true));
    } else {
      String token = (String)request.getSession().getAttribute(DestinatariExternByTokenController.EXTERNAL_USER_TOKEN);
      ModelAndView model = new ModelAndView("externaluser_showerror");
      model.addObject("token", token);
      model.addObject("error", "S'ha produït un error durant el procés de rebuig.");
      return model;
    }
  }
  
  @Override
  public boolean isActiveList() {
    return false;
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
  public boolean isActiveDelete() {
    return false;
  }

  @Override
  public boolean isActiveFormView() {
    return false;
  }
  
  


}
