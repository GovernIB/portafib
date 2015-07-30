package es.caib.portafib.back.controller.agentscaib;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.portafib.logic.AgentsCAIBLocal;
import es.caib.portafib.model.entity.UsuariEntitat;
import es.caib.portafib.utils.Configuracio;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

/**
 *
 * @author anadal
 */
@Controller
public class UsuarisCAIBController {

  protected final Logger log = Logger.getLogger(getClass());

  @EJB(mappedName = AgentsCAIBLocal.JNDI_NAME)
  protected AgentsCAIBLocal carrecsCaibEjb;

  protected static final Locale locale = new Locale("ca");

  @RequestMapping(value = "/usuariscaib", method = RequestMethod.GET)
  @ResponseBody
  public void index(HttpServletRequest request, HttpServletResponse response) throws Exception {

    log.info(" ==================== Entra dins INDEX USUARIS ========================");

    if (log.isDebugEnabled()) {
      Map<String, String[]> params = request.getParameterMap();
      for (String key : params.keySet()) {
        log.debug("Param[" + key + "]: " + Arrays.toString(params.get(key)));
      }
    }

    String tipus = request.getParameter("tipus");

    String codusu = request.getParameter("codusu");
    //String nomrol = request.getParameter("nomrol");
    //String valordomini = request.getParameter("valordomini");
    String agentsql = request.getParameter("agentsql");
    //String nom = request.getParameter("nom");
    String password = request.getParameter("password");
    
    // Check password
    
    String passwordOK = Configuracio.getPasswordForAgentsSQL();
    
    if (passwordOK == null) {
      log.error("S'ha de definir la propietat es.caib.portafib.passwordforagentssql !!!!!");
      response.getOutputStream().println("ERROR");
      return;
    }
    
    if (!passwordOK.trim().equals(password)) {
      log.error("Algu està intentant accedir a la Gestió d'Usuaris via AgentSeycon però"
          + " la contrasenya passada no s'ajusta al valor definit dins la propietat"
          + " es.caib.portafib.passwordforagentssql:\n"
          + "  + getRemoteAddr: " + request.getRemoteAddr() + "\n"
          + "  + RemoteHost: " + request.getRemoteHost() + "\n"
          + "  + RemotePort: " + request.getRemotePort() + "\n");
      response.getOutputStream().println("ERROR");
      return;
    } 
    

    UsuariEntitat ue;
    ue = carrecsCaibEjb
        .processarUsuariCAIB(tipus, codusu, agentsql, password);

    if (ue == null) {
      response.getOutputStream().println("ERROR");
    } else {
      response.getOutputStream().println("OK: " + ue.getUsuariEntitatID());
    }

    log.info(" ------------------ Final INDEX USUARIS ------------------");

  }

}
