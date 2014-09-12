package es.caib.portafib.back.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mgonzalez
 */
@Controller
public class LogoutErrorController {

  protected final Logger log = Logger.getLogger(getClass());
  

	
	@RequestMapping("/logout.html")
	protected String logout(HttpServletRequest request) {
		log.info("Entra dins logout: "+request.getSession().getCreationTime());
    request.getSession().invalidate();
		return "forward:principal.html";
	}


    @RequestMapping(value = "/exception")
    public ModelAndView exception(HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
        try{
            String errorType = (String)request.getSession().getAttribute("tipusExcepcio");
            if(request.getSession().getAttribute("exceptionObj")!=null){
                Exception e = (Exception)request.getSession().getAttribute("exceptionObj");
                List<Throwable> llistaExcepcions = new ArrayList<Throwable>();
                if(e!=null){
                    getCausaPrincipal(e,llistaExcepcions);
                }
                ModelAndView mav = new ModelAndView("exceptionTile");
                mav.addObject("llistaExcepcions", llistaExcepcions);
                mav.addObject("tipusExcepcio", errorType);
                return mav;
            }else{
                return new ModelAndView("exceptionTile");

            }
        }catch(Exception ec){
              return new ModelAndView("exceptionTile");
        }

    }




   public void getCausaPrincipal(Throwable e, List<Throwable> listExc){
       if(e.getCause()!=null){
           listExc.add(e);
           getCausaPrincipal(e.getCause(), listExc);
       }else {
           listExc.add(e);
        }
    }
   
   
   

   
   

   
   

}
