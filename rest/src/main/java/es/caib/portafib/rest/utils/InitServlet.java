package es.caib.portafib.rest.utils;


import es.caib.portafib.logic.utils.I18NLogicUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.security.RunAs;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Servlet emprat per inicialitzar el Back
 * 
 * @author anadal
 * 
 */
@RunAs("PFI_ADMIN")
@Component
public class InitServlet extends HttpServlet {

    protected final Logger log = Logger.getLogger(getClass());

    @Override
    public void init(ServletConfig config) throws ServletException {

        // Sistema de Traduccions WEB
        try {
            ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
            String[] basenames = { "missatges", // /WEB-INF/classes/
                    "logicmissatges", "genapp", "portafib_genapp" };
            ms.setDefaultEncoding("UTF-8");
            ms.setBasenames(basenames);
            I18NUtils.setMessageSource(ms);
        } catch (Throwable th) {
            log.error("Error inicialitzant el sistema de traduccions web: " + th.getMessage(), th);
        }

        // Sistema de Traduccions LOGIC
        // TODO Moure a logic
        try {
            Class.forName(I18NLogicUtils.class.getName());
        } catch (Throwable th) {
            log.error("Error inicialitzant el sistema de traduccions logic: " + th.getMessage(), th);
        }

    }

    @Override
    public void destroy() {
        log.info("Aturant PortaFIB Rest");
        // Evitar memory leak de Log4j
        LogManager.shutdown();
    }
}
