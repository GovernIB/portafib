package es.caib.portafib.back.controller.dest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.form.SeleccioUsuariForm;
import es.caib.portafib.back.form.dest.ColaboracioDelegacioDestForm;
import es.caib.portafib.back.form.webdb.ColaboracioDelegacioFilterForm;
import es.caib.portafib.back.form.webdb.ColaboracioDelegacioForm;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/dest/colaborador")
@SessionAttributes(types = { ColaboracioDelegacioDestForm.class,
    ColaboracioDelegacioForm.class, ColaboracioDelegacioFilterForm.class, SeleccioUsuariForm.class })
public class ColaboracioDestController extends DelegacioDestController {

  /**
   * 
   * @return
   */
  public boolean esDelegat() {
    return false;
  }

}
