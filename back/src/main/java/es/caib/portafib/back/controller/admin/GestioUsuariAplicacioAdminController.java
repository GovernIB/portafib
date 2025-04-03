package es.caib.portafib.back.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.portafib.back.controller.adapp.GestioUsuariAplicacioAdappController;
import es.caib.portafib.back.form.webdb.UsuariAplicacioFilterForm;
import es.caib.portafib.back.form.webdb.UsuariAplicacioForm;

/**
 * 
 * @author anadal
 * 
 */
@Controller
@RequestMapping(value = "/admin/usuariAplicacio")
@SessionAttributes(types = { UsuariAplicacioForm.class, UsuariAplicacioFilterForm.class })
public class GestioUsuariAplicacioAdminController extends GestioUsuariAplicacioAdappController {

  @Override
  protected boolean isAdmin() {
    return true;
  }

}
