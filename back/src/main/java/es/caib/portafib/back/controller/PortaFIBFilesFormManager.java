package es.caib.portafib.back.controller;

import es.caib.portafib.jpa.FitxerJPA;
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

  public PortaFIBFilesFormManager(IFileManager<Fitxer, Long> fitxerEjb) {
    super(fitxerEjb);
  }

  @Override
  public FitxerJPA createEmptyFile() {    
    return new FitxerJPA();
  }

}
