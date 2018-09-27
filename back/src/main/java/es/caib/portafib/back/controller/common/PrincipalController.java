package es.caib.portafib.back.controller.common;

import java.util.Map;

import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.utils.Configuracio;
import es.caib.portafib.utils.ConstantsV2;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *  
 * @autor anadal
 * 
 */
@Controller
public class PrincipalController {

  protected final Logger log = Logger.getLogger(getClass());

  @RequestMapping(value = "/common/principal.html")
  public ModelAndView principal(HttpSession session,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    
    Boolean initialized = (Boolean)session.getAttribute("inicialitzat");
    
    if (initialized == null) {
      HtmlUtils.saveMessageInfo(request, I18NUtils.tradueix("app.benvingut"));      
      session.setAttribute("inicialitzat", true);
    }
    /*
    // Compatibilitat IE8
    String userAgent = request.getHeader("User-Agent");
    if (userAgent != null) {
      int index = userAgent.toUpperCase().indexOf("MSIE");
      if (index != -1) {
        try {
           String ieversion = userAgent.substring(index + 4,userAgent.indexOf(";", index + 4));
           if (Float.parseFloat(ieversion) < 9.0f) {
             HtmlUtils.saveMessageError(request, I18NUtils.tradueix("navegador.nosuportat"));
           }
        } catch(Throwable e) {
          log.debug(e);
        }
      }
    }
    */
    
    /*
    HtmlUtils.saveMessageInfo(request, "Prova info 2");    
    
    HtmlUtils.saveMessageError(request, "Prova error 1");
    
    HtmlUtils.saveMessageError(request, "Prova error 2");
    
    HtmlUtils.saveMessageWarning(request, "Prova warning 1");
    
    HtmlUtils.saveMessageWarning(request, "Prova warning 2");
    
    
    HtmlUtils.saveMessageSuccess(request, "Prova SUCCES");
    
    HtmlUtils.saveMessageSuccess(request, "Prova SUCCES 22");
    */
    
    if (initialized == null && Configuracio.isCAIB() && request.isUserInRole(ConstantsV2.ROLE_DEST)) {
      return new ModelAndView(new RedirectView(ConstantsV2.CONTEXT_DEST_ESTATFIRMA_PENDENT + "/list", true));
    } else {
      return new ModelAndView("principal");
    }

  }
 

  @RequestMapping(value = "/canviarPipella", method = RequestMethod.GET)
  public ModelAndView canviarPipella(HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    return canviarPipella(request, response, null);
  }

  @RequestMapping(value = "/canviarPipella/{pipella}", method = RequestMethod.GET)
  public ModelAndView canviarPipella(HttpServletRequest request, HttpServletResponse response,
      @PathVariable String pipella) throws Exception {

    if (pipella != null && pipella.trim().length() != 0) {
      if ("webdb".equals(pipella)) {
        return new ModelAndView("webdb");
      }      
      if ("ROLE_ADEN".equals(pipella)) {
        //return new ModelAndView("role_aden");
        return new ModelAndView(new RedirectView("/aden/peticionscaducades/list/1", true));
      }
      if ("ROLE_ADMIN".equals(pipella)) {
        // return new ModelAndView("role_admin");
        // "/admin/fitxersorfes/list/1"
        return new ModelAndView(new RedirectView("/admin/entitat/list/1", true));
      }
      if ("ROLE_COLA".equals(pipella)) {
        //return new ModelAndView("role_cola");
        return new ModelAndView(new RedirectView(ConstantsV2.CONTEXT_COLA_ESTATFIRMA_PENDENT + "/list", true));
      }
      if ("ROLE_DELE".equals(pipella)) {
        //return new ModelAndView("role_dele");
        return new ModelAndView(new RedirectView(ConstantsV2.CONTEXT_DELE_ESTATFIRMA_PENDENT + "/list", true));
      }
      if ("ROLE_DEST".equals(pipella)) {
        return new ModelAndView(new RedirectView(ConstantsV2.CONTEXT_DEST_ESTATFIRMA_PENDENT + "/list", true));
      }
      if ("ROLE_SOLI".equals(pipella)) {
        //return new ModelAndView("role_soli");
        return new ModelAndView(new RedirectView(ConstantsV2.CONTEXT_SOLI_PETICIOFIRMA_ACTIVA + "/list", true));
      }
      if ("ROLE_REVI".equals(pipella)) {
        //return new ModelAndView("role_soli");
        return new ModelAndView(new RedirectView(ConstantsV2.CONTEXT_REVI_ESTATFIRMA_PENDENT + "/list", true));
      }
      if (Configuracio.isDesenvolupament() && "desenvolupament".equals(pipella)) {
        return new ModelAndView("desenvolupament");
      }
            
      throw new Exception("S'ha accedit a canviarPipella amb un paràmetre desconegut: " + pipella);
    }

    return new ModelAndView("principal");
  }
  
  
  @RequestMapping(value = "/canviarEntitat/{entitatID}", method = RequestMethod.GET)
  public ModelAndView canviarEntitat(HttpServletRequest request, HttpServletResponse response,
      @PathVariable String entitatID) throws Exception {

    LoginInfo loginInfo = LoginInfo.getInstance();
    
    Map<String, EntitatJPA> entitats = loginInfo.getEntitats();
    
    if (entitats.get(entitatID) == null) {
      HtmlUtils.saveMessageError(request, I18NUtils.tradueix("error.noentitat", entitatID));
    } else {
      String entitatActual = loginInfo.getEntitatID();
      try {
        loginInfo.setEntitatID(entitatID);
        SecurityContextHolder.getContext().setAuthentication(loginInfo.generateToken());
      } catch (Exception e) {
        // Restauram l'entitat
        loginInfo.setEntitatID(entitatActual);
        throw e;
      }
    }

    return new ModelAndView("principal");
    
  }

}
