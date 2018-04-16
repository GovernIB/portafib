
package es.caib.portafib.back.form.webdb;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import es.caib.portafib.back.form.PortaFIBBaseFilterForm;

import es.caib.portafib.model.fields.UsuariEntitatRevisorFields;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class UsuariEntitatRevisorFilterForm extends PortaFIBBaseFilterForm implements UsuariEntitatRevisorFields {

  private java.lang.Long usuariEntitatRevisorIdDesde;

  public java.lang.Long getUsuariEntitatRevisorIdDesde() {
    return this.usuariEntitatRevisorIdDesde;
  }

  public void setUsuariEntitatRevisorIdDesde(java.lang.Long usuariEntitatRevisorIdDesde) {
    this.usuariEntitatRevisorIdDesde = usuariEntitatRevisorIdDesde;
  }


  private java.lang.Long usuariEntitatRevisorIdFins;

  public java.lang.Long getUsuariEntitatRevisorIdFins() {
    return this.usuariEntitatRevisorIdFins;
  }

  public void setUsuariEntitatRevisorIdFins(java.lang.Long usuariEntitatRevisorIdFins) {
    this.usuariEntitatRevisorIdFins = usuariEntitatRevisorIdFins;
  }


  private java.lang.String usuariEntitatID;

  public java.lang.String getUsuariEntitatID() {
    return this.usuariEntitatID;
  }

  public void setUsuariEntitatID(java.lang.String usuariEntitatID) {
    this.usuariEntitatID = usuariEntitatID;
  }


  public UsuariEntitatRevisorFilterForm() {
  }
  
  public UsuariEntitatRevisorFilterForm(UsuariEntitatRevisorFilterForm __toClone) {
    super(__toClone);
    this.usuariEntitatRevisorIdDesde = __toClone.usuariEntitatRevisorIdDesde;
    this.usuariEntitatRevisorIdFins = __toClone.usuariEntitatRevisorIdFins;
    this.usuariEntitatID = __toClone.usuariEntitatID;
    this.mapOfUsuariEntitatForUsuariEntitatID = __toClone.mapOfUsuariEntitatForUsuariEntitatID;
  }
  
  /* ========= UTILS ========== */

  @Override
  public List<Field<?>> getDefaultFilterByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] {  }));
  }

  @Override
  public List<Field<?>> getDefaultGroupByFields() {
    return new java.util.ArrayList<Field<?>>(Arrays.asList(new Field<?>[] { ACTIU }));
  }


  protected OrderBy[] defaultOrderBy = null;


  public OrderBy[] getDefaultOrderBy() {
    return this.defaultOrderBy;
  }

  public void setDefaultOrderBy(OrderBy[] defOrderBy) {
    this.defaultOrderBy = defOrderBy;
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

   // -----------------------
   // Maps de referencies.
   // -----------------------
  private Map<String, String> mapOfUsuariEntitatForUsuariEntitatID;

  public Map<String, String> getMapOfUsuariEntitatForUsuariEntitatID() {
    return this.mapOfUsuariEntitatForUsuariEntitatID;
  }

  public void setMapOfUsuariEntitatForUsuariEntitatID(Map<String, String> mapOfUsuariEntitatForUsuariEntitatID) {
    this.mapOfUsuariEntitatForUsuariEntitatID = mapOfUsuariEntitatForUsuariEntitatID;
  }




   // --------------------------------
   // Camps traduibles de referencies.
   // ---------------------------------
   public static final List<String> traduibles;

   static {
     traduibles = new java.util.ArrayList<String>();
   };

}
