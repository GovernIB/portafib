package es.caib.portafib.back.controller.common;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleStatus;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.portafib.back.controller.rest.apiplantillaflux.v1.RestApiPlantillaFluxV1Controller;
import es.caib.portafib.back.controller.rest.apiplantillaflux.v1.RestApiPlantillaFluxV1Controller.TransactionInfo;
import es.caib.portafib.back.controller.soli.PlantillaDeFluxDeFirmesController;
import es.caib.portafib.back.form.PlantillaDeFluxDeFirmesFilterForm;
import es.caib.portafib.back.form.PlantillaDeFluxDeFirmesForm;
import es.caib.portafib.back.form.SeleccioUsuariForm;
import es.caib.portafib.back.form.webdb.FluxDeFirmesFilterForm;
import es.caib.portafib.back.form.webdb.FluxDeFirmesForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.persistence.UsuariAplicacioJPA;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = PlantillaDeFluxDeFirmesRestController.CONTEXT)
@SessionAttributes(types = { PlantillaDeFluxDeFirmesFilterForm.class, SeleccioUsuariForm.class,
    PlantillaDeFluxDeFirmesForm.class, FluxDeFirmesForm.class, FluxDeFirmesFilterForm.class })
public class PlantillaDeFluxDeFirmesRestController extends PlantillaDeFluxDeFirmesController {

  public static final String CONTEXT = "/public/flowtemplate";

  public static final String SESSION_TRANSACTION_ID_FLOW_TEMPLATE_REST = "SESSION_TRANSACTION_ID_FLOW_TEMPLATE_REST";

  @Override
  public String getTileForm() {
    return "PlantillaDeFluxDeFirmesFormRest";
  }

  @Override
  public String getTileList() {
    return "PlantillaDeFluxDeFirmesListRest";
  }

  @Override
  public String getSessionAttributeFilterForm() {
    return "PlantillaFluxDeFirmes_public_rest";
  }

  @Override
  public boolean isUsuariEntitat() {
    return false;
  }

  @Override
  public boolean isEditingPlantilla() {
    return true;
  }

  @Override
  public boolean isPlantillaRest() {
    return true;
  }

  @RequestMapping(value = "/new/{transactionID}", method = RequestMethod.GET)
  public ModelAndView createFromRestRequest(
      @PathVariable("transactionID") String transactionID, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
    
    initializeRestLoginInfo(transactionID, request);

    return new ModelAndView(new RedirectView(getContextWeb() + "/new", true));
  }
  
  /**
   * NOMES PER REST
   * @param fluxDeFirmesIDStr
   * @param request
   * @param response
   * @return
   * @throws I18NException
   */
  @RequestMapping(value = "/editflux/{transactionID}", method = RequestMethod.GET)
  public ModelAndView editFluxDeFirmesRest(
      @PathVariable("transactionID") String transactionID, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

//   request.getSession().setAttribute(
//        PlantillaDeFluxDeFirmesRestController.SESSION_TRANSACTION_ID_FLOW_TEMPLATE_REST, transactionID);
//
//    TransactionInfo restTransaction = RestApiPlantillaFluxV1Controller.currentTransactions
//          .get(transactionID);
//    
    
    Long fluxDeFirmesID = initializeRestLoginInfo(transactionID, request);
    
    
    ModelAndView mav = new ModelAndView(
        new RedirectView(getContextWeb() + "/" + fluxDeFirmesID + "/edit", true));

    return mav;

  }
  
  
  
  protected Long initializeRestLoginInfo(String transactionID, HttpServletRequest request)
      throws I18NException {
    
    TransactionInfo ti = RestApiPlantillaFluxV1Controller.currentTransactions
        .get(transactionID);

    if (ti == null) {
      // XYZ ZZZ TRA
      throw new I18NException("genapp.comodi",
          "No es troba la transacció de Rest Flow Template amb ID " + transactionID);
    }

    UsuariAplicacioJPA usuariAplicacio = ti.getUsuariAplicacio();

    String username = usuariAplicacio.getUsuariAplicacioID();
    String password = "";
    Set<GrantedAuthority> seyconAuthorities = new HashSet<GrantedAuthority>();
    seyconAuthorities.add(new SimpleGrantedAuthority(PFI_USER));

    User user = new User(username, password, seyconAuthorities);

    // create a new authentication token for usuariAplicacio
    LoginInfo loginInfo = new LoginInfo(user, usuariAplicacio, usuariAplicacio.getEntitat(),
        seyconAuthorities);

    // and set the authentication of the current Session context
    SecurityContextHolder.getContext().setAuthentication(loginInfo.generateToken());

    request.getSession().setAttribute(PlantillaDeFluxDeFirmesRestController.SESSION_TRANSACTION_ID_FLOW_TEMPLATE_REST,
        ti.getTransactionID());
    
    return ti.getFluxDeFirmesID();
  }
  


  @RequestMapping(value = "/finalRestOK", method = RequestMethod.GET)
  public ModelAndView finalRestOK(HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    String transactionID = (String) request.getSession()
        .getAttribute(SESSION_TRANSACTION_ID_FLOW_TEMPLATE_REST);

    if (transactionID == null) {
      throw new Exception("No es troba la transacció de Rest Flow Template dins la sessio");
    }

    TransactionInfo ti = RestApiPlantillaFluxV1Controller.currentTransactions
        .get(transactionID);

    ti.getStatus().setStatus(FlowTemplateSimpleStatus.STATUS_FINAL_OK);

    return new ModelAndView(new RedirectView(ti.getStartTransactionInfo().getReturnUrl()));
  }

  @RequestMapping(value = "/finalRestCanceled", method = RequestMethod.GET)
  public ModelAndView finalRest(HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    String transactionID = (String) request.getSession()
        .getAttribute(SESSION_TRANSACTION_ID_FLOW_TEMPLATE_REST);

    if (transactionID == null) {
      // XYZ ZZZ TRA
      throw new Exception("No es troba la transacció de Rest Flow Template dins la sessio");
    }

    TransactionInfo ti = RestApiPlantillaFluxV1Controller.currentTransactions
        .get(transactionID);

    ti.getStatus().setStatus(FlowTemplateSimpleStatus.STATUS_CANCELLED);

    //  XYZ ZZZ TRA
    ti.getStatus().setErrorMessage("Cancel·lat per l'usuari");

    return new ModelAndView(new RedirectView(ti.getStartTransactionInfo().getReturnUrl()));
  }

  @Override
  public boolean isActiveList() {
    return false;
  }

  @Override
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request, HttpServletResponse response)
      throws I18NException {

    return "redirect:" + getContextWeb() + "/finalRestCanceled";
  }
/*
  @Override
  @RequestMapping(value = "/list/{pagina}", method = RequestMethod.GET)
  public ModelAndView llistatPaginat(HttpServletRequest request, HttpServletResponse response,
      @PathVariable Integer pagina) throws I18NException {

    return new ModelAndView(new RedirectView(getContextWeb() + "/finalRestCanceled", true));
  }

  @Override
  public boolean isActiveDelete() {
    return true;
  }

  @Override
  public String getRedirectWhenCancel(HttpServletRequest request,
      java.lang.Long fluxDeFirmesID) {
    return "redirect:" + getContextWeb() + "/finalRestCanceled";
  }
*/
}