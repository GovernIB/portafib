package es.caib.portafib.back.form;

import org.fundaciobit.genapp.common.web.form.BaseFilterForm;

/**
 * 
 * @author anadal
 *
 */
public abstract class PortaFIBBaseFilterForm extends BaseFilterForm {
  
  
  
  public PortaFIBBaseFilterForm() {
    setVisibleExportList(false);
  }
  
  
  
  public PortaFIBBaseFilterForm(PortaFIBBaseFilterForm __toClone) {
    super(__toClone);
  }
  

}
