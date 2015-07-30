package es.caib.portafib.back.controller.agentscaib;

import org.apache.log4j.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.portafib.logic.AgentsCAIBLocal;

import es.caib.portafib.model.entity.UsuariEntitat;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

/**
 *
 * @author anadal
 */
@Controller
public class CarrecsCAIBController {

  protected final Logger log = Logger.getLogger(getClass());

  @EJB(mappedName = AgentsCAIBLocal.JNDI_NAME)
  protected AgentsCAIBLocal carrecsCaibEjb;

  protected static final Locale locale = new Locale("ca");

  @RequestMapping(value = "/carrecscaib", method = RequestMethod.GET)
  @ResponseBody
  public void index(HttpServletRequest request, HttpServletResponse response) throws Exception {

    log.info(" ==================== Entra dins INDEX CARRECS ========================");

    if (log.isDebugEnabled()) {
      Map<String, String[]> params = request.getParameterMap();
      for (String key : params.keySet()) {
        log.debug("Param[" + key + "]: " + Arrays.toString(params.get(key)));
      }
    }

    String tipus = request.getParameter("tipus");

    String codusu = request.getParameter("codusu");
    String nomrol = request.getParameter("nomrol");
    String valordomini = request.getParameter("valordomini");
    String agentsql = request.getParameter("agentsql");
    String nom = request.getParameter("nom");

    UsuariEntitat ue = carrecsCaibEjb
        .processarCarrecCAIB(tipus, codusu, nomrol, valordomini, agentsql, nom);

    if (ue == null) {
      response.getOutputStream().println("ERROR");
    } else {
      response.getOutputStream().println("OK: " + ue.getUsuariEntitatID());
    }

    log.info(" ------------------ Final INDEX CARRECS ------------------");

  }

}
