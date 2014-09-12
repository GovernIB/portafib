
package es.caib.portafib.back.form.webdb;

import java.util.List;
import javax.ejb.EJB;
import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.portafib.ejb.CustodiaInfoLocal;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.fields.CustodiaInfoFields;
import org.fundaciobit.genapp.common.web.controller.RefListBase;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class CustodiaInfoRefList extends RefListBase
    implements CustodiaInfoFields {

  @EJB(mappedName = CustodiaInfoLocal.JNDI_NAME)
  private CustodiaInfoLocal custodiaInfoEjb;

  public CustodiaInfoRefList(CustodiaInfoRefList __clone) {
    super(__clone);
    this.custodiaInfoEjb = __clone.custodiaInfoEjb;
  }
  public CustodiaInfoRefList() {
    setSelects(new Select<?>[] { CUSTODIAINFOID.select, CUSTODIAPLUGINID.select });
  }
  public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
    Select<StringKeyValue> select =  new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(keyField.select, getSeparator(), getSelects());
    List<StringKeyValue> list = custodiaInfoEjb.executeQuery(select, where, (orderBy==null || orderBy.length == 0) ? getOrderBy() : orderBy);
    return list;
  }
}
