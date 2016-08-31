package es.caib.portafib.back.utils;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signature.api.StatusSignature;
import org.fundaciobit.plugins.signature.api.StatusSignaturesSet;
import org.fundaciobit.plugins.signatureweb.api.SignaturesSetWeb;

import es.caib.portafib.back.controller.common.SignatureModuleController;
import es.caib.portafib.logic.ModulDeFirmaWebLogicaLocal;

/**
 * Serveix per anar processant les firmes finalitzades just quan es firmen i no
 * al final.
 * 
 * @author anadal
 *
 */
public abstract class AbstractParallelSignedFilesProcessing extends Thread {

  protected final Logger log = Logger.getLogger(this.getClass());

  protected final ModulDeFirmaWebLogicaLocal modulDeFirmaEjb;

  protected final String signaturesSetID;

  protected final HttpServletRequest request;

  /**
   * @param signaturesSetID
   * @param peticioDeFirmaLogicaEjb
   * @param modulDeFirmaEjb
   */
  public AbstractParallelSignedFilesProcessing(HttpServletRequest request,
      String signaturesSetID, ModulDeFirmaWebLogicaLocal modulDeFirmaEjb) {
    super();
    this.request = request;
    this.signaturesSetID = signaturesSetID;
    this.modulDeFirmaEjb = modulDeFirmaEjb;
  }

  @Override
  public void run() {

    final boolean isDebug = log.isDebugEnabled();

    if (isDebug) {
      log.debug("(Thread) Iniciat thread ...");
    }
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e1) {
    }

    Date caducitat = null;
    do {

      try {
        Thread.sleep(500);
      } catch (InterruptedException e1) {
        e1.printStackTrace();
      }

      SignaturesSetWeb ss;
      ss = SignatureModuleController.getSignaturesSetByID(request, signaturesSetID,
          modulDeFirmaEjb);

      if (ss == null) {
        return;
      }

      if (caducitat == null) {
        caducitat = ss.getExpiryDate();
        if (caducitat == null) {
          // Valor per defecte
          Calendar cal = Calendar.getInstance();
          cal.add(Calendar.MINUTE, 10);
          caducitat = new Date(System.currentTimeMillis() + 10 * 60 * 1000);
        }

      }

      StatusSignaturesSet sss = ss.getStatusSignaturesSet();

      if (sss.getStatus() == StatusSignaturesSet.STATUS_INITIALIZING) {
        if (isDebug) {
          log.debug("(Thread) STATUS_INITIALIZING  Esperam ....");
        }
        continue;
      } else if (sss.getStatus() != StatusSignaturesSet.STATUS_IN_PROGRESS) {
        return;
      }

      FileInfoSignature[] signedFiles = ss.getFileInfoSignatureArray();

      for (int i = signedFiles.length - 1; i >= 0; i--) {

        final FileInfoSignature signedFile = signedFiles[i];

        StatusSignature status = signedFile.getStatusSignature();

        // Per a que no es trepitji Thread i finalProcesDeFirma()
        synchronized (status) {

          // Només Processarem els OK
          if (status.isProcessed() || status.getStatus() != StatusSignature.STATUS_FINAL_OK) {
            continue;
          }

          File firmat = null;
          try {

            firmat = status.getSignedData();

            if (isDebug) {
              log.debug("firmat.getAbsolutePath(): " + firmat.getAbsolutePath());
            }

            this.process(signedFile, firmat);

            status.setProcessed(true);

            if (isDebug) {
              log.debug("(Thread)    [" + i + "]  Processat Signature "
                  + signedFile.getSignID() + " ...");
            }

          } catch (Throwable e) {
            // Do Nothing

            if (isDebug) {
              log.debug("(Thread)   [" + i + "]   Thread Error Cridant a nouFitxerFirmat"
                  + " (No gestionam l'error): " + e.getMessage(), e);
            }

          }
        } // Final synchronized
      }

    } while (caducitat.getTime() > System.currentTimeMillis()); // Sortida en
                                                                // cas de
                                                                // bloqueig

  }

  public abstract void process(final FileInfoSignature signedFileInfo, File firmat)
      throws I18NException;

}
