package es.caib.portafib.ws.utils;


import java.util.List;

import javax.activation.DataHandler;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.io.FileUtils;
import org.fundaciobit.genapp.common.ws.WsI18NException;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;

import es.caib.portafib.hibernate.HibernateFileUtil;
import es.caib.portafib.jpa.FitxerJPA;
import es.caib.portafib.logic.FitxerLogicaLocal;
import es.caib.portafib.model.bean.FitxerBean;
import es.caib.portafib.model.entity.Fitxer;
import es.caib.portafib.model.fields.IdiomaFields;

/**
 * 
 * @author anadal
 * 
 */
public class AuthenticatedBaseWsImpl extends BaseWsImpl {

  @EJB(mappedName = FitxerLogicaLocal.JNDI_NAME)
  protected FitxerLogicaLocal fitxerLogicaEjb;
  
  @EJB(mappedName = es.caib.portafib.ejb.IdiomaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.IdiomaLocal idiomaEjb;

  // -------------------------------------------------------------------
  // -------------------------------------------------------------------
  // --------------------------| UTILITATS |----------------------------
  // -------------------------------------------------------------------
  // -------------------------------------------------------------------

  @RolesAllowed({ PFI_USER, PFI_ADMIN })
  @WebMethod
  public List<String> getSupportedLanguages() throws WsI18NException, Throwable {
    List<String> idiomes;
    idiomes = idiomaEjb.executeQuery(IdiomaFields.IDIOMAID, IdiomaFields.SUPORTAT.equal(true));
    return idiomes;
  }
  
  
  @RolesAllowed({ PFI_USER, PFI_ADMIN })
  @WebMethod
  public String getEntitatID() {
    return UsuariAplicacioCache.get().getEntitatID();
  }

  @RolesAllowed({ PFI_ADMIN, PFI_USER })
  @WebMethod
  public FitxerBean downloadFileUsingEncryptedFileID(String encryptedFileID)
      throws WsI18NException, Throwable {

    FitxerJPA.enableEncryptedFileIDGeneration();
    try {
      long fitxerID = HibernateFileUtil.decryptFileID(encryptedFileID);

      // Checks
      Fitxer fitxer = fitxerLogicaEjb.checkBasic(fitxerID);

      FitxerBean fitxerBean = new FitxerBean(fitxer);
      
      byte[] data = FileUtils.readFileToByteArray(FileSystemManager.getFile(fitxerID));

      DataHandler dh = new DataHandler(new ByteArrayDataSource(data, fitxerBean.getMime()));

      fitxerBean.setData(dh);

      return fitxerBean;

    } finally {
      FitxerJPA.disableEncryptedFileIDGeneration();
    }

  }

}
