package es.caib.portafib.back.controller.aden;

import javax.ejb.EJB;

import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.persistence.ColaboracioDelegacioJPA;
import es.caib.portafib.persistence.UsuariEntitatJPA;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.controller.dest.DelegacioDestController;
import es.caib.portafib.back.form.dest.ColaboracioDelegacioDestForm;
import es.caib.portafib.back.form.webdb.ColaboracioDelegacioFilterForm;
import es.caib.portafib.back.form.webdb.ColaboracioDelegacioForm;
import es.caib.portafib.ejb.UsuariEntitatService;

import es.caib.portafib.back.form.SeleccioUsuariForm;
/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/aden/colaboradordecarrec")
@SessionAttributes(types = { ColaboracioDelegacioDestForm.class, SeleccioUsuariForm.class,
    ColaboracioDelegacioForm.class, ColaboracioDelegacioFilterForm.class })
public class ColaboracioDeCarrecAdenController extends DelegacioDestController {

  
  @EJB(mappedName = UsuariEntitatService.JNDI_NAME)
  protected UsuariEntitatService usuariEntitatEjb;

  @Override
  public boolean esDelegat() {
    return false;
  }
  
  @Override
  public boolean esDeCarrec() {
    return true;
  }

  /**
   * Indica si l'usuari actual pot accedir a aquesta colaboracio delegacio.
   * En aquest cas, si és d'un càrrec de la mateixa entitat.
   */
  protected boolean userCanAccess(ColaboracioDelegacioJPA colaDele) {
    String entitatId = LoginInfo.getInstance().getEntitatID();
    UsuariEntitatJPA destinatari = usuariEntitatEjb.findByPrimaryKey(colaDele.getDestinatariID());

    return (destinatari.getCarrec() != null && destinatari.getEntitatID().equals(entitatId));
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

}
