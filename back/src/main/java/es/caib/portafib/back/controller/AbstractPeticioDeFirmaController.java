package es.caib.portafib.back.controller;

import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.beans.factory.annotation.Autowired;

import es.caib.portafib.back.controller.webdb.PeticioDeFirmaController;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.validator.PeticioDeFirmaAmbFitxerAFirmarWebValidator;
import es.caib.portafib.logic.PeticioDeFirmaLogicaLocal;
import es.caib.portafib.model.entity.PeticioDeFirma;


/**
 * Afegeix un Validador per no deixar passar FitxersAFirma NULLs
 * 
 * @author anadal
 *
 */
public abstract class AbstractPeticioDeFirmaController extends PeticioDeFirmaController {

  @EJB(mappedName = "portafib/PeticioDeFirmaLogicaEJB/local")
  protected PeticioDeFirmaLogicaLocal peticioDeFirmaLogicaEjb;
  
  @Autowired
  private PeticioDeFirmaAmbFitxerAFirmarWebValidator peticioDeFirmaAmbFitxerAFirmarWebValidator;
  
  
  @PostConstruct
  public void initValidador() {
    //log.error("\n\n\nXYZ ZZZ Inicialitzant NOU VALIDADOR per Peticio de Firma\n\n\n");
    setWebValidator(this.peticioDeFirmaAmbFitxerAFirmarWebValidator);
  }

  @Override
  public void delete(HttpServletRequest request, PeticioDeFirma peticioDeFirma)
    throws Exception, I18NException {

    Set<Long> fitxers;
    fitxers = peticioDeFirmaLogicaEjb.deleteFullUsingUsuariEntitat(
        peticioDeFirma.getPeticioDeFirmaID(), LoginInfo.getInstance().getUsuariEntitatID());
    
    borrarFitxers(fitxers);
  }
  
}
