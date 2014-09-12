package es.caib.portafib.back.controller.aden;

import es.caib.portafib.back.controller.admin.GestioUsuariPersonaController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author mgonzalez
 * 
 */
@Controller
@RequestMapping(value = "/aden/usuariPersona")
public class GestioUsuariPersonaAdenController extends GestioUsuariPersonaController {

  @Override
  public String getTileForm() {
    return "gestioUsuariPersonaFormAden";
  }

  @Override
  public String getTileList() {
    return "gestioUsuariPersonaListAden";
  }

  @Override
  public String getTileNif() {
    return "selectUsuariPersonaByNifAden";
  }
 
  @Override
  public boolean isAden() {
    return true;
  }

}
