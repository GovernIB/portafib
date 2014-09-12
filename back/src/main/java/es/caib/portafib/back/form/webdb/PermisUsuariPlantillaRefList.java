
package es.caib.portafib.back.form.webdb;

import java.util.List;
import javax.ejb.EJB;
import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.portafib.ejb.PermisUsuariPlantillaLocal;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.fields.PermisUsuariPlantillaFields;
import org.fundaciobit.genapp.common.web.controller.RefListBase;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class PermisUsuariPlantillaRefList extends RefListBase
    implements PermisUsuariPlantillaFields {

  @EJB(mappedName = PermisUsuariPlantillaLocal.JNDI_NAME)
  private PermisUsuariPlantillaLocal permisUsuariPlantillaEjb;

  public PermisUsuariPlantillaRefList(PermisUsuariPlantillaRefList __clone) {
    super(__clone);
    this.permisUsuariPlantillaEjb = __clone.permisUsuariPlantillaEjb;
  }
  public PermisUsuariPlantillaRefList() {
    setSelects(new Select<?>[] { USUARIENTITATID.select, PLANTILLAFLUXDEFIRMESID.select });
  }
  public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
    Select<StringKeyValue> select =  new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(keyField.select, getSeparator(), getSelects());
    List<StringKeyValue> list = permisUsuariPlantillaEjb.executeQuery(select, where, (orderBy==null || orderBy.length == 0) ? getOrderBy() : orderBy);
    return list;
  }
}
