package es.caib.portafib.back.controller.dest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/dest/colaborador")
public class ColaboracioDestController extends DelegacioDestController {

  /**
   * 
   * @return
   */
  public boolean esDelegat() {
    return false;
  }

}
