package es.caib.portafib.back.controller.admin;

import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.utils.Tab;
import es.caib.portafib.logic.AgentsCAIBLocal;
import es.caib.portafib.logic.utils.EmailUtil;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.model.entity.UsuariPersona;
import es.caib.portafib.utils.Constants;

/**
 * 
 * @author anadal
 * 14 feb 2025 13:41:57
 */
@Controller
@RequestMapping(value = "/admin/enviarcorreu")
@MenuOption(
        group = Tab.MENU_ADMIN,
        labelCode = "provarservidorcorreu",
        baseLink = "/admin/enviarcorreu/usuariloguejat",
        relativeLink = "",
        addSeparatorBefore = true,
        order = 140)
@MenuOption(
        group = Tab.MENU_ADMIN,
        labelCode = "provarcorreuadministradors.requerit",
        baseLink = "/admin/enviarcorreu/administradorsrequerit",
        relativeLink = "",
        order = 145)
@MenuOption(
        group = Tab.MENU_ADMIN,
        labelCode = "provarcorreuadministradors.opcional",
        baseLink = "/admin/enviarcorreu/administradorsopcional",
        relativeLink = "",
        order = 150)
public class EnviarCorreuProva {

    protected Logger log = Logger.getLogger(EnviarCorreuProva.class);

    @EJB(mappedName = AgentsCAIBLocal.JNDI_NAME)
    protected AgentsCAIBLocal agentsCAIBEjb;

    @RequestMapping(value = "/usuariloguejat")
    public String enviarCorreuUsuariLoguejat(HttpServletRequest request, HttpServletResponse response)
            throws I18NException {

        try {
            UsuariPersona up = LoginInfo.getInstance().getUsuariPersona();

            boolean resultat = EmailUtil.postMail("Missatge de prova de PortaFIB", "Missatge de Prova de PortaFIB",
                    false, PropietatGlobalUtil.getAppEmail(), up.getEmail());

            if (resultat) {
                HtmlUtils.saveMessageInfo(request, "Missatge de Correu Enviat correctament");
            } else {
                HtmlUtils.saveMessageWarning(request,
                        "El misatge de correu no s'ha enviat però no ha llançat cap excepció (Veure log pels detalls de l'error)");
            }

        } catch (Exception e) {
            String msg = "Error al enviar correu de prova: " + e.getMessage();
            log.error(msg, e);
            HtmlUtils.saveMessageError(request, msg);
        }

        return "redirect:/canviarPipella/" + Constants.ROLE_ADMIN;

    }

    @RequestMapping(value = "/administradorsrequerit")
    public String enviarCorreuAdministradorsRequerit(HttpServletRequest request, HttpServletResponse response)
            throws I18NException {
        return enviarCorreuAdministradors(request, response, true);
    }

    @RequestMapping(value = "/administradorsopcional")
    public String enviarCorreuAdministradorsOpcional(HttpServletRequest request, HttpServletResponse response)
            throws I18NException {
        return enviarCorreuAdministradors(request, response, false);
    }

    protected String enviarCorreuAdministradors(HttpServletRequest request, HttpServletResponse response,
            boolean requerit) throws I18NException {

        final String entitatID = LoginInfo.getInstance().getEntitatID();
        if (entitatID == null) {
            HtmlUtils.saveMessageError(request,
                    "Per enviar als Administradors d'Entitat necessit que estiguis associat a una Entitat.");
        } else {
            try {
                String subject = "Missatge de prova als Administradors d'Entitat de '" + entitatID + "'";
                Collection<String> emails;
                if (requerit) {
                    subject = subject + " (Requerit)";
                    emails = agentsCAIBEjb.enviarCorreuAdmistradorsRequerit(subject, "Missatge de Prova de PortaFIB",
                            entitatID);
                } else {
                    subject = subject + " (Opcional)";
                    emails = agentsCAIBEjb.enviarCorreuAdmistradorsOpcional(subject, "Missatge de Prova de PortaFIB",
                            entitatID);
                }
                
                

                String msg = requerit ? "Requerit = S'envia de forma obligatoria a tots els Administradors d'Entitat."
                        : "Opcional = S'envia només als Administradors d'Entitat que tinguin donada d'alta la Notificació per Correu de tipus INCIDENCIA_ADMINISTRADOR";

                HtmlUtils.saveMessageInfo(request,
                        msg + ". Missatges de Correu enviats als següents administradors de l'entitat de '" + entitatID
                                + "':" + emails);

            } catch (Exception e) {
                String msg = "Error al enviar correu als Administradors d'Entitat de '" + entitatID + "'"
                        + e.getMessage();
                log.error(msg, e);
                HtmlUtils.saveMessageError(request, msg);
            }
        }

        return "redirect:/canviarPipella/" + Constants.ROLE_ADMIN;

    }

}
