package es.caib.portafib.back.controller.all;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import es.caib.portafib.logic.utils.LogicUtils;

import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author anadal
 *
 */
@Controller
public class VersioController {

  protected final Logger log = Logger.getLogger(getClass());
  
  @RequestMapping(value = "/public/versio")
  public void versio(HttpServletResponse response)
      throws Exception {
    response.getWriter().write(LogicUtils.getVersio() + "|" + LogicUtils.getBuild());
    response.getWriter().flush();
    response.getWriter().close();

  }

}