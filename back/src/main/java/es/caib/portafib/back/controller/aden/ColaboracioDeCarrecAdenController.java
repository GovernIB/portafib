package es.caib.portafib.back.controller.aden;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.portafib.back.controller.dest.DelegacioDestController;
import es.caib.portafib.back.form.SeleccioNifForm;
import es.caib.portafib.back.form.dest.ColaboracioDelegacioDestForm;
import es.caib.portafib.back.form.webdb.ColaboracioDelegacioFilterForm;
import es.caib.portafib.back.form.webdb.ColaboracioDelegacioForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.ejb.UsuariEntitatLocal;
import es.caib.portafib.jpa.ColaboracioDelegacioJPA;
import es.caib.portafib.model.fields.UsuariEntitatFields;
import es.caib.portafib.model.fields.UsuariEntitatQueryPath;
import es.caib.portafib.utils.Constants;


/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/aden/colaboradordecarrec")
@SessionAttributes(types = { ColaboracioDelegacioDestForm.class, SeleccioNifForm.class,
    ColaboracioDelegacioForm.class, ColaboracioDelegacioFilterForm.class })
public class ColaboracioDeCarrecAdenController extends DelegacioDestController {

  
  @EJB(mappedName = UsuariEntitatLocal.JNDI_NAME)
  protected UsuariEntitatLocal usuariEntitatEjb;

  /**
   * 
   * @return
   */
  @Override
  public boolean esDelegat() {
    return false;
  }
  
  @Override
  public boolean esDeCarrec() {
    return true;
  }
  
  
  @Override
  public String getTileForm() {
    return "colaboracioDelegacioDeCarrecAdenForm";
  }

  @Override
  public String getTileList() {
    return "colaboracioDelegacioDeCarrecAdenList";
  }

  @Override
  public String getSessionAttributeFilterForm() {
    return getEntityNameCode() + "_Aden_FilterForm";
  }
  
  /**
   * =============================================================
   * ===========      N I F   S E L E  C C I O   ================= 
   * =============================================================
   *
   */
  
  public String getTileNif() {
    return "selectUsuariEntitatByNifUsernameAden";
  }
  
  @RequestMapping(value = "/nif", method = RequestMethod.GET)
  public ModelAndView selectUsuaripersonaByNifGet(HttpServletRequest request) throws Exception {

    ModelAndView mav = new ModelAndView(getTileNif());
    SeleccioNifForm seleccioNifForm = new SeleccioNifForm();
    seleccioNifForm.setTitol("colaboradordecarrec");
    seleccioNifForm.setSubtitol("colaboradordecarrec.alta.introduirnif");
    seleccioNifForm.setCancelUrl("/canviarPipella/" + Constants.ROLE_ADEN);
    mav.addObject(seleccioNifForm);
    
    
    request.getSession().removeAttribute(USUARI_ENTITAT_ID_DE_CARREC);
    
    return mav;
  }

  
  @RequestMapping(value = "/nif", method = RequestMethod.POST)
  public ModelAndView selectUsuaripersonaByNifPost(SeleccioNifForm seleccioNifForm,
      BindingResult result, HttpServletRequest request) throws I18NException {

    ModelAndView mav = new ModelAndView(getTileNif());

    String nifOrUsername = seleccioNifForm.getNif();

    if (result.hasErrors()) {
      log.debug("entramos aqui result con errores");
      return mav;
    }

    // Si no han introduit Nif
    if (nifOrUsername == null || nifOrUsername.trim().length() == 0) {
      // TODO en traduccions es i ca va bé. EN altre sidiomes no ho sé
      result.rejectValue("nif", "genapp.validation.required", new Object[] { "" }, null);
      return mav;
    }

    
    List<String> ups = usuariEntitatEjb.executeQuery(UsuariEntitatFields.USUARIENTITATID,
        getSelectUsuarisEntitatByNifUsername(nifOrUsername));
    if (ups.isEmpty()) { // No existeix
      
      // No existeix usuari-entitat amb aquest nif dins la meva entitat
      result.rejectValue("nif", "aturarpeticionsdefirma.nif.error.nifsenseusuarisentitat",
          new Object[]{nifOrUsername}, null);
      return mav;

    } else {
      request.getSession().setAttribute(USUARI_ENTITAT_ID_DE_CARREC, ups.get(0));
      mav = new ModelAndView(new RedirectView(getContextWeb() + "/new", true));
      return mav;
    }

    
  }
  
  private Where getSelectUsuarisEntitatByNifUsername(String nif) {
    // TODO Ha d'anar a variable estatica
    final UsuariEntitatQueryPath usuariEntitatQueryPath = new UsuariEntitatQueryPath();
    return Where.AND(
        Where.OR(
          usuariEntitatQueryPath.USUARIPERSONA().NIF().equal(nif.toUpperCase()),
          usuariEntitatQueryPath.USUARIPERSONA().USUARIPERSONAID().equal(nif.trim())
        ),
        UsuariEntitatFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID()),
        UsuariEntitatFields.CARREC.isNull()
        );
  }
  
  
  @Override
  public ColaboracioDelegacioForm getColaboracioDelegacioForm(ColaboracioDelegacioJPA _jpa,
      boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    
    // És una creació
    if (_jpa == null) {
      String usuariEntitatID;
      usuariEntitatID = (String) request.getSession().getAttribute(USUARI_ENTITAT_ID_DE_CARREC);
      if (usuariEntitatID == null) {
        mav.setView(new RedirectView(getContextWeb() + "/nif", true));
        return new ColaboracioDelegacioDestForm();
      }
    }

    ColaboracioDelegacioForm form;
    form = super.getColaboracioDelegacioForm(_jpa, __isView, request, mav);
    
    request.getSession().removeAttribute(USUARI_ENTITAT_ID_DE_CARREC);
    
    return form;
  }
  
  
}
