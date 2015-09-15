package es.caib.portafib.back.controller.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.springframework.stereotype.Component;

import es.caib.portafib.logic.misc.AutoFirmaBean;
import es.caib.portafib.logic.misc.AutofirmaLocal;

/**
 * Aquest mètode és controlat per Servlet no pel Controller Spring
 * !!!!! Revisar web.xml. *
 * @RequestMapping(value = "/destination/{id}") és a dir /common/destination/{id}
 * @author anadal
 *
 */
@Component
public class AutoFirmaServlet extends HttpServlet {


  protected Logger log = Logger.getLogger(AutoFirmaServlet.class);
  
  @EJB(mappedName = "portafib/AutofirmaEJB/local")
  protected AutofirmaLocal autofirmaEjb;

  /**
   * Mètode NO-AUTENTICAT. No fer ús de LoginInfo
   */
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      
      String uri = request.getRequestURI();
      String id = uri.substring(uri.lastIndexOf('/') + 1, uri.length());      
      if (log.isDebugEnabled()) {
        log.debug("UUUUUUUUUUUUU1 " + request.getQueryString());
        log.debug("UUUUUUUUUUUUU1 " + request.getRequestURI());
        log.debug("UUUUUUUUUUUUU1 ID=" + id);
      }

      Long idLong = new Long(id);

      AutoFirmaBean form = autofirmaEjb.get(idLong);

      if (form == null) {
        response.setStatus(404);
        return;
      }

      // Llegir certificat com un Stream
      InputStream is = request.getInputStream();
      
      String usuariEntitat = form.getUsuariEntitatID();

      File firmat = AutoFirmaController.getFitxerFirmatPath(usuariEntitat,
          Long.parseLong(id));
      
      FileOutputStream fos = new FileOutputStream(firmat);
      
      
      FileSystemManager.copy(is, fos);

      fos.close();


      if (log.isDebugEnabled()) {
        log.debug("Autofirma[" + id + "]:: Fitxer guardat a " + firmat.getAbsolutePath());
      }
      
      // FASE I: Aquí s'ha de borrar directoris adaptat i attach   
      try {
        
        final Long idlong = Long.parseLong(id);
        
        for (int number = 0; number < 4; number++) {
          File f = new  File(AutoFirmaController.autofirmaBasePath 
            + File.separatorChar + usuariEntitat
            + File.separatorChar + id 
            + File.separatorChar + "attach" + number);
          if (f.exists()) {            
            FileUtils.deleteDirectory(f);
          } else {
            break;
          }
          
        }

        File adapt = AutoFirmaController.getFitxerAdaptatPath(usuariEntitat,idlong);
        File parent = adapt.getParentFile();
        if (parent.exists()) {
          FileUtils.deleteDirectory(parent);
        }
        
        // FASE II: Aquí s'ha de borrar LA SESSIÓ
        autofirmaEjb.remove(idlong);

      } catch (Throwable e) {
        log.error("Error esborrant directoris temporals d´Autofirma: " + e.getMessage(), e);
      }


    } catch (Throwable e) {
      log.error("Error al pujar el fitxer des de l'applet: " + e.getMessage(), e);
      response.setStatus(200);
    }
  }

  
  
}
