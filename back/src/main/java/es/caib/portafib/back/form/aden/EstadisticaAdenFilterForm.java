package es.caib.portafib.back.form.aden;

import es.caib.portafib.back.form.webdb.EstadisticaFilterForm;
import org.springframework.stereotype.Component;

/**
 * @author areus
 */
@Component
public class EstadisticaAdenFilterForm extends EstadisticaFilterForm {

   private Boolean aplicacio;

   public Boolean getAplicacio() {
      return aplicacio;
   }

   public void setAplicacio(Boolean aplicacio) {
      this.aplicacio = aplicacio;
   }

   private String exporter;

   public String getExporter() {
      return exporter;
   }

   public void setExporter(String exporter) {
      this.exporter = exporter;
   }


   public EstadisticaAdenFilterForm() {
   }

   public EstadisticaAdenFilterForm(EstadisticaFilterForm __toClone) {
      super(__toClone);
   }

   public EstadisticaAdenFilterForm(EstadisticaAdenFilterForm __toClone) {
      super(__toClone);
      this.aplicacio = __toClone.aplicacio;
      this.exporter = __toClone.exporter;
   }
}
