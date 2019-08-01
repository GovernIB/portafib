package es.caib.portafib.back.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Controller
public class UserController implements ConstantsV2 {

  @RequestMapping(value = "/admin")
  public ModelAndView aden() throws Exception {
    ModelAndView mav = new ModelAndView(
        new RedirectView("/canviarPipella/" + ROLE_ADMIN, true));
    return mav;
  }

  @RequestMapping(value = "/aden")
  public ModelAndView admin() throws Exception {
    ModelAndView mav = new ModelAndView(new RedirectView("/canviarPipella/" + ROLE_ADEN, true));
    return mav;
  }

  @RequestMapping(value = "/dest")
  public ModelAndView dest() throws Exception {
    ModelAndView mav = new ModelAndView(new RedirectView("/canviarPipella/" + ROLE_DEST, true));
    return mav;
  }

  @RequestMapping(value = "/dele")
  public ModelAndView dele() throws Exception {
    ModelAndView mav = new ModelAndView(new RedirectView("/canviarPipella/" + ROLE_DELE, true));
    return mav;
  }

  @RequestMapping(value = "/cola")
  public ModelAndView cola() throws Exception {
    ModelAndView mav = new ModelAndView(new RedirectView("/canviarPipella/" + ROLE_COLA, true));
    return mav;
  }

  @RequestMapping(value = "/soli")
  public ModelAndView soli() throws Exception {
    ModelAndView mav = new ModelAndView(new RedirectView("/canviarPipella/" + ROLE_SOLI, true));
    return mav;
  }

  @RequestMapping(value = "/revi")
  public ModelAndView revi() throws Exception {
    ModelAndView mav = new ModelAndView(new RedirectView("/canviarPipella/" + ROLE_REVI, true));
    return mav;
  }

}
