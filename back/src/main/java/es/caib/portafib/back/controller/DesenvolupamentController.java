package es.caib.portafib.back.controller;

import javax.ejb.EJB;
import javax.ejb.EJBAccessException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.caib.portafib.ejb.IdiomaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.persistence.IdiomaJPA;
import es.caib.portafib.model.entity.Idioma;

/**
 * 
 * @author anadal
 *
 */
@Controller
public class DesenvolupamentController {

  
  @EJB(mappedName = IdiomaService.JNDI_NAME)
  private IdiomaService idiomaEjb;
  
  @RequestMapping(value = "/deletemethod")
  public ModelAndView deleteMethod(HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    
    IdiomaJPA i = new IdiomaJPA();
    i.setIdiomaID("eeeee");
    idiomaEjb.delete(i);
    
    return new ModelAndView("desenvolupament");
  }

  @RequestMapping(value = "/hibernateerror")
  public ModelAndView hibernateError(HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    
    Idioma i = new IdiomaJPA();    
    idiomaEjb.delete(i);
    
    return new ModelAndView("desenvolupament");
  }
  
  
  
  @RequestMapping(value = "/errorcallback")
  public ModelAndView errorcallback(HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    boolean test = true;
    if (test) {
      throw new IllegalArgumentException("Either callerSubject or callerRunAs should be non-null");
    } else {
      return new ModelAndView("desenvolupament");
    }
    
  }


  @RequestMapping(value = "/errorinvaliduser")
  public ModelAndView errorinvaliduser(HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    boolean test = true;
    if (test) {
      throw new EJBAccessException("Invalid User");
    } else {
      return new ModelAndView("desenvolupament");
    }

  }
  
  
  @RequestMapping(value = "/modelandviewnoexisteix")
  public ModelAndView modelandviewnoexisteix(HttpServletRequest request,
      HttpServletResponse response) throws Exception {

    return new ModelAndView("modelandviewnoexisteix");
    
  }
  
  
  @RequestMapping(value = "/servletexception")
  public ModelAndView servletexception(HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    return new ModelAndView("redirect:/WEB-INF/jsp/webdb/menu_desenvolupament.jsp");
  }
  
  
  @RequestMapping(value = "/jspexception")
  public ModelAndView jspexception(HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    ModelAndView mv = new ModelAndView("desenvolupament"); 
    mv.addObject("accio", "excepcio");
    return mv;
  }
 
  
}
