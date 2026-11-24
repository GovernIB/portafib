package es.caib.portafib.back.controller.common;

import es.caib.portafib.back.utils.Utils;
import es.caib.portafib.commons.utils.Configuracio;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * @author anadal
 * @author areus
 */

/**
 *      String androidApk = Configuracio.getAndroidApk();
        pageContext.setAttribute("androidApk", androidApk);
        if (androidApk != null && !androidApk.isEmpty()) {
            optionalMenus.add(null); // Separator
            optionalMenus.add(new MenuItem("appmobil", "/common/app.html", "/common/app.html", 90));
        }
 * @author anadal (u80067)
 * 24 nov 2026 9:23:34
 */
@MenuOption(
        labelCode = "appmobil",
        baseLink = "/common/app.html",
        relativeLink = "/common/app.html",
        addSeparatorBefore = true,
        order = 90,
        condition = AppInfoController.MenuOptionConditionShowAppMobil.class)
@Controller
public class AppInfoController {

    /**
     * 
     * @author anadal (u80067)
     * 24 nov 2026 9:25:56
     */
    public static class MenuOptionConditionShowAppMobil extends MenuOptionConditionAbstractUsuariIntern {

        @Override
        public boolean isVisible(HttpServletRequest request) throws Exception {

            String androidApk = Configuracio.getAndroidApk();
            request.getSession().setAttribute("androidApk", androidApk);
            //pageContext.setAttribute("androidApk", androidApk);

            return super.isVisible(request) && (androidApk != null && !androidApk.isEmpty());
        }
    }

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
        response.setHeader("Content-Disposition", Utils.getContentDispositionHeader(true, "portafibapp.apk"));
        FileUtils.copyFile(appFile, response.getOutputStream());
    }

}
