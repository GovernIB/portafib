package es.caib.portafib.back.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import es.caib.portafib.back.controller.common.AutoFirmaController;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.logic.utils.PdfUtils;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;

/**
 * 
 * @author anadal
 * 
 */
@Component
public class PortaFIBCommonsMultipartResolver extends
    org.springframework.web.multipart.commons.CommonsMultipartResolver {

  protected final Logger log = Logger.getLogger(getClass());

  public PortaFIBCommonsMultipartResolver() {
    super();
  }

  @Override
  public MultipartHttpServletRequest resolveMultipart(HttpServletRequest request)
      throws MultipartException {
    /*
     * log.info(" ++++ Scheme: " + request.getScheme());
     * log.info(" ++++ PathInfo: " + request.getPathInfo());
     * log.info(" ++++ PathTrans: " + request.getPathTranslated());
     * log.info(" ++++ ContextPath: " + request.getContextPath());
     * log.info(" ++++ ServletPath: " + request.getServletPath());
     * log.info(" ++++ getRequestURI: " + request.getRequestURI());
     * log.info(" ++++ getRequestURL: " + request.getRequestURL().toString());
     * log.info(" ++++ getQueryString: " + request.getQueryString());
     */

    // NOTA: Les pujades des de l'applet no tenen límit i no es controlen des
    // d'aquí
    Long maxUploadSize;
    String msgCode;
    if (AutoFirmaController.CONTEXTWEB.equals(request.getServletPath())) {
      // PUJADA DES D'AUTOFIRMA
      // El màxim es tria per fitxer adaptat
      maxUploadSize = getMaxFitxerAdaptatSize();
      msgCode = "tamanyfitxeradaptatsuperat";
    } else {
      // Pujada d'un fitxer
      // Es fa una mescla entre el màxim global i màxim per entitat
      maxUploadSize = getMaxUploadSize();
      msgCode = "tamanyfitxerpujatsuperat";
    }

    if (maxUploadSize == null) {
      this.setMaxUploadSize(-1); // -1 = No Limit
    } else {
      this.setMaxUploadSize(maxUploadSize);
    }
    if (log.isDebugEnabled()) {
      log.debug("Tamany de pujada de Fitxers: "
        + (maxUploadSize == null ? "Sense limit" : maxUploadSize));
    }

    try {
      return super.resolveMultipart(request);
    } catch (MaxUploadSizeExceededException musee) {
      throw new PortaFIBMaxUploadSizeExceededException(musee.getCause(),
          musee.getMaxUploadSize(), msgCode);
    }

  }

  private Long getMaxUploadSize() {
    Long maxUploadSizeGlobal = PropietatGlobalUtil.getMaxUploadSizeInBytes();
    log.info("XYZ maxUploadSizeGlobal = " + maxUploadSizeGlobal);
    if (log.isDebugEnabled()) {
      if (maxUploadSizeGlobal == null) {
        log.debug("No s'ha definit limit de tamany global en la pujada de Fitxers");
      } else {
        log.info("S'ha definit un tamany màxim de pujada global de Fitxers a "
            + maxUploadSizeGlobal + " bytes");
      }
    }

    Long maxUploadSizeEntitat;
    try {
      maxUploadSizeEntitat = LoginInfo.getInstance().getEntitat().getMaxUploadSize();
    } catch (Throwable th) {
      maxUploadSizeEntitat = null;
    }

    Long maxUploadSize = PdfUtils.selectMin(maxUploadSizeGlobal, maxUploadSizeEntitat);
    return maxUploadSize;
  }

  private Long getMaxFitxerAdaptatSize() {
    Long maxFitxerAdaptatSizeGlobal = PropietatGlobalUtil.getMaxFitxerAdaptatSizeInBytes();
    log.info("XYZ maxFitxerAdaptatSizeGlobal = " + maxFitxerAdaptatSizeGlobal);
    if (log.isDebugEnabled()) {
      if (maxFitxerAdaptatSizeGlobal == null) {
        log.info("No s'ha definit limit de tamany global en el fitxer adaptat");
      } else {
        log.info("S'ha definit un tamany màxim de Fitxer Adaptat a "
            + maxFitxerAdaptatSizeGlobal + " bytes");
      }
    }

    Long maxFitxerAdaptatSizeEntitat;
    try {
      maxFitxerAdaptatSizeEntitat = LoginInfo.getInstance().getEntitat()
          .getMaxSizeFitxerAdaptat();
    } catch (Throwable th) {
      maxFitxerAdaptatSizeEntitat = null;
    }

    Long maxFitxerAdaptatSize = PdfUtils.selectMin(maxFitxerAdaptatSizeGlobal,
        maxFitxerAdaptatSizeEntitat);
    return maxFitxerAdaptatSize;
  }

}
