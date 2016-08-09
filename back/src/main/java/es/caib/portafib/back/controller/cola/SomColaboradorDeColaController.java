package es.caib.portafib.back.controller.cola;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.controller.dele.SomDelegatDeDeleController;
import es.caib.portafib.back.form.webdb.ColaboracioDelegacioFilterForm;
import es.caib.portafib.back.form.webdb.ColaboracioDelegacioForm;

@Controller
@RequestMapping(value = "/cola/colaboradorde")
@SessionAttributes(types = { ColaboracioDelegacioForm.class, ColaboracioDelegacioFilterForm.class })
public class SomColaboradorDeColaController extends SomDelegatDeDeleController {

  @Override
  public boolean esDelegat() {
    return false;
  }
  
}
