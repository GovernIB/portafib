
package es.caib.portafib.back.form.webdb;

import java.util.List;
import javax.ejb.EJB;
import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.portafib.ejb.EstatDeFirmaLocal;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.fields.EstatDeFirmaFields;
import org.fundaciobit.genapp.common.web.controller.RefListBase;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class EstatDeFirmaRefList extends RefListBase
    implements EstatDeFirmaFields {

  @EJB(mappedName = EstatDeFirmaLocal.JNDI_NAME)
  private EstatDeFirmaLocal estatDeFirmaEjb;

  public EstatDeFirmaRefList(EstatDeFirmaRefList __clone) {
    super(__clone);
    this.estatDeFirmaEjb = __clone.estatDeFirmaEjb;
  }
  public EstatDeFirmaRefList() {
    setSelects(new Select<?>[] { ESTATDEFIRMAID.select, FIRMAID.select, USUARIENTITATID.select });
  }
  public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
    Select<StringKeyValue> select =  new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(keyField.select, getSeparator(), getSelects());
    List<StringKeyValue> list = estatDeFirmaEjb.executeQuery(select, where, (orderBy==null || orderBy.length == 0) ? getOrderBy() : orderBy);
    return list;
  }
}
