package es.caib.portafib.back.controller.dest;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Locale;

import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;

import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.logic.PeticioDeFirmaLogicaLocal;

/**
 * 
 * @author anadal
 * 
 */
@Controller
@RunAs("PFI_USER")
public class EstatFirmaServlet extends HttpServlet {

  @EJB(mappedName = "portafib/PeticioDeFirmaLogicaEJB/local")
  protected PeticioDeFirmaLogicaLocal peticioDeFirmaLogicaEjb;

  protected final Logger log = Logger.getLogger(getClass());
  
  public static final int ERROR_SERVLET =  HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
  

  /**
   * IMPORTANT: Aquest mètode és controlat per Servlet no pel Controller Spring
   * !!!!! Revisar web.xml. *
   * 
   * @RequestMapping(value =
   *     "/estatDeFirma/destination/{estatDeFirmaID}/{peticioDeFirmaID/{token}
   *     )
   */
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String uri = request.getRequestURI();
    log.debug("request.getRequestURI(): " + request.getRequestURI());
    final String search = "destination/";
    int index = uri.indexOf(search);
    if (index == -1) {
      // TODO traduir
      String msg = "URL incorrecte " + uri + " (Esperat /estatDeFirma/peticioDeFirma/token/numfirma)";
      log.error(msg, new Exception());
      response.setHeader("MSGPORTAFIB", msg);
      response.sendError(EstatFirmaServlet.ERROR_SERVLET);
      return;
    }
    String params = uri.substring(index + search.length(), uri.length());    
    String[] args = params.split("/");
    if (log.isDebugEnabled()) {
      log.debug("PARAMS=" + params);
      log.debug("PARAMS_SPLIT=" + Arrays.toString(args));
    }
    if (args.length != 4) {
      String msg = "Numero de paràmetres incorrectes " + args.length 
        + "(Esperat 4)(URI = " + uri + ")";
      log.error(msg, new Exception());
      response.setHeader("MSGPORTAFIB", msg);
      response.sendError(EstatFirmaServlet.ERROR_SERVLET);
      return;
    }

    Long estatDeFirmaID;
    Long peticioDeFirmaID;
    String token;
    Integer numFirma;
    try {
      estatDeFirmaID = new Long(args[0]);
      peticioDeFirmaID = new Long(args[1]);
      token = args[2];
      numFirma = new Integer(args[3]);
    } catch (NumberFormatException nfe) {
      String msg = "Format no numeric dels paràmetres " + uri;
      log.error(msg,  nfe);
      response.setHeader("MSGPORTAFIB", msg);
      response.sendError(EstatFirmaServlet.ERROR_SERVLET);

      return;
    }



    try {
      // Llegir certificat com un Stream
      InputStream is = request.getInputStream();

      File firmat = File.createTempFile(peticioDeFirmaID + "_Firma_Firmat_", ".pdf",
          FileSystemManager.getFilesPath());
      firmat.deleteOnExit();

      FileOutputStream fos = new FileOutputStream(firmat);
      FileSystemManager.copy(is, fos);
      fos.close();

      log.debug("firmat.getAbsolutePath(): " + firmat.getAbsolutePath());

      peticioDeFirmaLogicaEjb.nouFitxerFirmat(firmat, estatDeFirmaID, peticioDeFirmaID, token,
          numFirma);

      log.debug("WWWWWWWWW FINAL ");
      
    } catch (Throwable e) {
      log.error(" CLASS = " + e.getClass());
      String msg;
      if (e instanceof I18NException) {
        I18NException i18ne = (I18NException)e;

        /**
         * No se per quina rao, però perd la configuració de l'idioma de l'usuari
         * i posa per defecte la del navegador.
         */
        try {
          /*
          log.info("Instance:" + LoginInfo.getInstance());
          log.info("UPersona:" + LoginInfo.getInstance().getUsuariPersona());
          log.info("UPersona:" + LoginInfo.getInstance().getUsuariPersona().getUsuariPersonaID());
          log.info("IDIOMA:  " + );
          */
          String idioma = LoginInfo.getInstance().getUsuariPersona().getIdiomaID();
          LocaleContextHolder.setLocale(new Locale(idioma));
        } catch (Throwable th) {
        } 

        msg = I18NUtils.getMessage(i18ne);
        log.error("Error al pujar el fitxer des de l'applet(I18NException): " + msg, e);
      } else {
        msg = e.getMessage();
        log.error("Error al pujar el fitxer des de l'applet(Throwable): " + msg , e);
      }

      response.setHeader("MSGPORTAFIB", msg);
      response.sendError(ERROR_SERVLET); 
    }
  }

}
