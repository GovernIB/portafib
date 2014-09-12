package es.caib.portafib.back.form;

import org.fundaciobit.genapp.common.web.form.BaseForm;

/**
 * 
 * @author anadal
 *
 */
public abstract class PortaFIBBaseForm extends BaseForm {

  public PortaFIBBaseForm() {
  }
  
  public PortaFIBBaseForm(boolean nou) {
    super(nou);
  }
  
  public PortaFIBBaseForm(PortaFIBBaseForm __toClone) {
    super(__toClone);
  }
  
}
