package es.caib.portafib.back.editor.webdb;

import es.caib.portafib.jpa.TipusFirmaJPA;


import java.beans.PropertyEditorSupport;

import org.apache.log4j.Logger;

/**
 * @author GenApp
 */
public class TipusFirmaEditor extends PropertyEditorSupport {

  protected final Logger log = Logger.getLogger(getClass());

  public TipusFirmaEditor() {
    super();
  }

  public void setAsText(String text) throws IllegalArgumentException {
    
    log.debug(" XXXXXXXXXXXXXXXXXXXXXXXXXX  ");
    log.debug("  --------- Passa per EDITOR ---------------  ");
    log.debug(" XXXXXXXXXXXXXXXXXXXXXXXXXX  ", new Exception());
    

    TipusFirmaJPA tipusFirmaJPA = new TipusFirmaJPA();

    if (text != null && text.length() > 0) {
      tipusFirmaJPA.setTipusFirmaID(Integer.parseInt(text));
    }

    setValue(tipusFirmaJPA);
  }
}
