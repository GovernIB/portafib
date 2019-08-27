package es.caib.portafib.logic;


import es.caib.portafib.ejb.FitxerEJB;
import es.caib.portafib.jpa.FitxerJPA;
import es.caib.portafib.jpa.validator.FitxerBeanValidator;
import es.caib.portafib.logic.utils.LogicUtils;
import es.caib.portafib.logic.utils.datasource.IPortaFIBDataSource;
import org.apache.commons.io.FileUtils;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NArgumentCode;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.annotation.Resource;
import javax.annotation.security.RunAs;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "FitxerLogicaEJB")
@SecurityDomain("seycon")
@RunAs("PFI_USER")
public class FitxerLogicaEJB extends FitxerEJB implements FitxerLogicaLocal {

  @Resource
  private SessionContext context;
  
  @Override
  public FitxerJPA createFull(FitxerJPA fitxer) throws I18NException, I18NValidationException {

    FitxerBeanValidator fbv = new FitxerBeanValidator(this);
    fbv.throwValidationExceptionIfErrors(fitxer, true);

    return (FitxerJPA)this.create(fitxer);
  }
  
  @Override
  public FitxerJPA checkBasic(long fitxerID) throws I18NException {
    if (fitxerID == 0) {
      // error.notfound=No s´ha trobat cap {0} amb {1} igual a {2}
      throw new I18NException("error.notfound",
          new I18NArgumentCode(_TABLE_TRANSLATION),
          new I18NArgumentCode(FITXERID.fullName),
          new I18NArgumentString(String.valueOf(fitxerID))
          );
    }
    
    FitxerJPA ua = (FitxerJPA)findByPrimaryKey(fitxerID);
    if (ua == null) {
      // error.notfound=No s´ha trobat cap {0} amb {1} igual a {2}
      throw new I18NException("error.notfound",
          new I18NArgumentCode(_TABLE_TRANSLATION),
          new I18NArgumentCode(FITXERID.fullName),
          new I18NArgumentString(String.valueOf(fitxerID))
          );
    }
    
    return ua;
  }
  
  
  @Override
  public boolean deleteFull(long fitxerID) throws I18NException {
    
    // Esborrar de BBDD
    delete(fitxerID);
    
    // Esborrar fitxer Fisic
    if (!FileSystemManager.eliminarArxiu(fitxerID)) {
      log.warn("No s'ha pogut esborrar el fitxer amb ID " + fitxerID, new Exception());
      return false;
    } else {
      return true;
    }
  }

  @Override
  public FitxerJPA createFitxerField(FitxerJPA fitxer, IPortaFIBDataSource data, Set<Long> fitxersCreats, Field<?> field)
        throws I18NException, I18NValidationException {

    if (fitxer == null) {
      return null;
    }

    File tmp = null;
    try {
      if (data == null) {
        throw new IOException("No data");
      }

      tmp = File.createTempFile("ws_", ".tmp", FileSystemManager.getFilesPath());
      InputStream is = data.getInputStream();
      FileUtils.copyInputStreamToFile(is, tmp);
      is.close();

    } catch(IOException ioe) {
      log.error("Error llegint dades", ioe);
      if (tmp != null && tmp.exists()) {
        if (!tmp.delete()) {
          tmp.deleteOnExit();
        }
      }
      throw new I18NException("fitxer.sensedades", field.fullName);
    }

    log.info("Tamany de fitxer rebut = "+ tmp.length() );
    fitxer.setTamany(tmp.length());

    try {
      fitxer = createFull(fitxer);
    } catch (I18NValidationException e) {
      // Si falla la creació per errors de validació, borram el fitxer temporal i rellançam excepció
      if (tmp.exists()) {
        if (!tmp.delete()) {
          tmp.deleteOnExit();
        }
      }
      throw e;
    }

    long fitxerID = fitxer.getFitxerID();

    log.info("ID fitxer creat = "+ fitxerID );

    try {
      LogicUtils.sobreescriureFitxerChecked(tmp, fitxerID);
    } catch (Exception e) {
      // Si ha fallat el sobreescriure
      if (tmp.exists()) {
        if (!tmp.delete()) {
          tmp.deleteOnExit();
        }
      }
      // intentam borrar tot, feim rollback i llançam excepció
      context.setRollbackOnly();
      throw new I18NException("genapp.comodi", e.getMessage());
    }

    fitxersCreats.add(fitxer.getFitxerID());
    return fitxer;
  }


}
