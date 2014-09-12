package es.caib.portafib.back.controller.dest;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

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
import es.caib.portafib.logic.ColaboracioDelegacioLogicaLocal;

/**
 * 
 * @author anadal
 * 
 */
@Controller
public class AutoritzacioDelegacioServlet extends HttpServlet {

  @EJB(mappedName = "portafib/ColaboracioDelegacioLogicaEJB/local")
  protected ColaboracioDelegacioLogicaLocal colaboracioDelegacioLogicaEjb;

  protected final Logger log = Logger.getLogger(getClass());

  /**
   * IMPORTANT: Aquest mètode és controlat per Servlet no pel Controller Spring
   * !!!!! Revisar web.xml. *
   * 
   * @RequestMapping(value = "/dest/delegat/destination/{delegacioID})
   */
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String uri = request.getRequestURI();
    log.debug("WWWWWWW1 " + request.getRequestURI());
    final String search = "destination/";
    int index = uri.indexOf(search);
    if (index == -1) {
      response.setStatus(EstatFirmaServlet.ERROR_SERVLET);
      return;
    }
    String delegacioIDStr = uri.substring(index + search.length(), uri.length());
    log.debug("DELEGACIO ID = " + delegacioIDStr);

    Long delegacioID = new Long(delegacioIDStr);

    File firmat = null;
    boolean copiat = false;
    try {
      // Llegir certificat com un Stream
      InputStream is = request.getInputStream();

      firmat = File.createTempFile(DelegacioDestController.FITXER_AUTORITZACIO_PREFIX
          + "_Firmat_" + delegacioID, ".pdf", FileSystemManager.getFilesPath());
      firmat.deleteOnExit();

      FileOutputStream fos = new FileOutputStream(firmat);
      FileSystemManager.copy(is, fos);
      fos.close();

      log.debug("WWWWWWWWW " + firmat.getAbsolutePath());

      colaboracioDelegacioLogicaEjb.assignarAutoritzacioADelegacio(delegacioID, firmat,
          DelegacioDestController.FITXER_AUTORITZACIO_PREFIX + delegacioID + ".pdf");

      copiat = true;

      log.debug("WWWWWWWWW FINAL ");

    } catch (Throwable e) {
      log.error(" CLASS = " + e.getClass());
      String msg;
      if (e instanceof I18NException) {
        I18NException i18ne = (I18NException) e;

        /**
         * No se per quina rao, però perd la configuració de l'idioma de
         * l'usuari i posa per defecte la del navegador.
         */
        try {
          String idioma = LoginInfo.getInstance().getUsuariPersona().getIdiomaID();
          LocaleContextHolder.setLocale(new Locale(idioma));
        } catch (Throwable th) {
        }

        msg = I18NUtils.getMessage(i18ne);
        log.error("Error al pujar el fitxer des de l'applet(I18NException): " + msg,
            new Exception(msg));
      } else {
        msg = e.getMessage();
        log.error("Error al pujar el fitxer des de l'applet(Throwable): " + msg, e);
      }
      // Ja no es necessari ja que l'applet ja captura correncatment l'error i
      // el missatge.
      // HtmlUtils.saveMessageError(request, msg);
      response.setHeader("MSGPORTAFIB", msg);
      response.sendError(EstatFirmaServlet.ERROR_SERVLET);
    } finally {
      if (firmat != null && copiat == false) {
        firmat.delete();
      }
    }
  }

}
