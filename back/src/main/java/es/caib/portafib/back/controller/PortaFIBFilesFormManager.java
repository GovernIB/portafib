package es.caib.portafib.back.controller;

import es.caib.portafib.persistence.FitxerJPA;
import es.caib.portafib.model.entity.Fitxer;

import org.fundaciobit.genapp.common.filesystem.IFileManager;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;


/**
 * Gestiona Multiples Fitxers d'un Form
 * 
 * @author anadal
 * 
 */
public class PortaFIBFilesFormManager extends FilesFormManager<Fitxer> {

  public PortaFIBFilesFormManager(IFileManager<Fitxer> fitxerEjb) {
    super(fitxerEjb);
  }

  @Override
  public FitxerJPA createEmptyFile() {    
    return new FitxerJPA();
  }

}
