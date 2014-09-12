package es.caib.portafib.callback.impl;

/**
 * 
 * @author anadal
 *
 */
public class MCGDWSSoapBindingSkeleton extends es.indra.www.portafirmasmcgdws.mcgdws.MCGDWSSoapBindingSkeleton {

  /**
   * 
   */
  public MCGDWSSoapBindingSkeleton() {
    super(new MCGDWSSoapBindingImpl());
  }

}
