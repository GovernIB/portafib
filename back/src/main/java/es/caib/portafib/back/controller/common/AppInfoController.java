package es.caib.portafib.back.controller.common;

import es.caib.portafib.utils.ConstantsV2;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author areus
 */
@Controller
public class AppInfoController implements ConstantsV2 {

  protected final Logger log = Logger.getLogger(getClass());

  @RequestMapping(value = "/common/app.html")
  public ModelAndView appInfo(HttpSession session,
      HttpServletRequest request, HttpServletResponse response) {

      return new ModelAndView("appinfo");

  }

}
