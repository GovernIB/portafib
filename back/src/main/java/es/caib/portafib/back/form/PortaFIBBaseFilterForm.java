package es.caib.portafib.back.form;

import org.fundaciobit.genapp.common.web.form.BaseFilterForm;

/**
 * 
 * @author anadal
 * @author areus
 */
public abstract class PortaFIBBaseFilterForm extends BaseFilterForm {

  public PortaFIBBaseFilterForm() {
    setVisibleExportList(false);
    setAllItemsPerPage(new int[] {10, 20, 50, 100});
  }

  public PortaFIBBaseFilterForm(PortaFIBBaseFilterForm __toClone) {
    super(__toClone);
  }

}
