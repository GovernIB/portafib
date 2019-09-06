package es.caib.portafib.back.controller.aden;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.controller.AbstractAnnexController;
import es.caib.portafib.back.form.webdb.AnnexFilterForm;
import es.caib.portafib.back.form.webdb.AnnexForm;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Controller
@RequestMapping(value = "/aden/gestioannexes")
@SessionAttributes(types = { AnnexForm.class, AnnexFilterForm.class })
public class AnnexAdenController extends AbstractAnnexController {

  @Override
  public boolean isSoli() {
    return false;
  }

}
