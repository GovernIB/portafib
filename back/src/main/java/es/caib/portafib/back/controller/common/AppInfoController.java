package es.caib.portafib.back.controller.common;

import es.caib.portafib.back.utils.Utils;
import es.caib.portafib.commons.utils.Configuracio;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author areus
 */
@Controller
public class AppInfoController {

  protected final Logger log = Logger.getLogger(getClass());

  @RequestMapping(value = "/common/app.html")
  public ModelAndView appInfo(HttpServletRequest request, HttpServletResponse response) {

      return new ModelAndView("appinfo");
  }

  @RequestMapping(value = "/common/appDownload")
  public void appDownload(HttpServletRequest request, HttpServletResponse response) throws IOException {

      String androidApk = Configuracio.getAndroidApk();
      if (androidApk == null || androidApk.isEmpty()) {
          log.error("No existeix la propietat androidapk");
          response.sendError(HttpServletResponse.SC_NOT_FOUND);
          return;
      }

      if (androidApk.equalsIgnoreCase("server")) {
          response.sendRedirect(request.getContextPath() + "/doc/portafibapp.apk");
          return;
      }

      File appFile = new File(androidApk);
      if (!appFile.canRead()) {
          log.error("La propietat androidapk no conté un fitxer vàlid: '" + androidApk + "'");
          response.sendError(HttpServletResponse.SC_NOT_FOUND);
          return;
      }

      response.setContentLength((int) appFile.length());
      response.setContentType("application/vnd.android.package-archive");
      response.setHeader("Content-Disposition",
              Utils.getContentDispositionHeader(true, "portafibapp.apk"));
      FileUtils.copyFile(appFile, response.getOutputStream());
  }

}
