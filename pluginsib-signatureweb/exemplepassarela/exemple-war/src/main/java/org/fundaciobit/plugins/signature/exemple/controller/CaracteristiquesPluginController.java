package org.fundaciobit.plugins.signature.exemple.controller;


import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.plugins.signatureweb.exemple.ejb.SignatureWebModuleLocal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/common/plugins")
public class CaracteristiquesPluginController {

  
  @EJB(mappedName = SignatureWebModuleLocal.JNDI_NAME)
  protected SignatureWebModuleLocal signatureModuleEjb;
  
  
  @RequestMapping(value = "/web", method = RequestMethod.GET)
  public ModelAndView caracteristiques(HttpServletRequest request) throws Exception {
    String[][] matrix;
    
    matrix = signatureModuleEjb.generateMatrixPluginInformation();
    
    
    ModelAndView mav = new ModelAndView("caracteristiquesplugin"); // .jsp
    mav.addObject("matrix", matrix);
    
    return mav;
    
  }



  
  
}
