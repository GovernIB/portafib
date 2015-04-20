package es.caib.portafib.back.controller.common;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.portafib.back.form.webdb.UsuariEntitatFilterForm;

/**
 *
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/common/senseSuportJava")
@SessionAttributes(types = { UsuariEntitatFilterForm.class })
public class SenseSuportJavaController {

  protected static Logger log = Logger.getLogger(SenseSuportJavaController.class);

  @RequestMapping(value = "", method = RequestMethod.GET)
  public ModelAndView senseSuportJava(HttpServletRequest request) throws I18NException {
    
    ModelAndView mav = new ModelAndView("senseSuportJava");

    StringBuffer params = new StringBuffer();
    Enumeration<String> paramsList = request.getParameterNames();
    while (paramsList.hasMoreElements()) {
      if (params.length() != 0) {
        params.append("&");
      }
      String name = paramsList.nextElement();
      params.append(name).append("=").append(request.getParameter(name));

      //log.info("PARAM " + name + " => " + request.getParameter(name));
    }
    String redirectJava = "http://java.com/dt-redirect?" + params.toString();

    String ua = request.getHeader("User-Agent");


    int indexChrome = ua.toUpperCase().indexOf("CHROME");
    if (indexChrome == -1) {
      indexChrome = ua.toUpperCase().indexOf("CHROMIUM");
    }
    // cercam la versió de chrome
    mav.addObject("enable_npapi",false);
    if (indexChrome != -1) {
      // Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.90 Safari/537.36
      int index1 = ua.indexOf("/", indexChrome);
      int index2 = ua.indexOf(".", indexChrome);
      
      
      try {
        //log.info(" Versio = " + ua.substring(index1, index2));
        int versio = Integer.parseInt(ua.substring(index1, index2));
        if (versio > 41) {
          mav.addObject("enable_npapi",true);
        }
        
      } catch(Throwable th) {
        log.error("Error cercant la versió de Chrome dins " + ua);
        mav.addObject("enable_npapi",true);
      }
      
      
    }

      

    
      //return new ModelAndView(new RedirectView(redirectJava, false));
    
    
    
    

    mav.addObject("redirectJava", redirectJava);
    return mav;
    

  }

}
