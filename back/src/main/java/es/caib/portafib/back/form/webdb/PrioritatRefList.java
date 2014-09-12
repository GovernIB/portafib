
package es.caib.portafib.back.form.webdb;

import java.util.List;
import javax.ejb.EJB;
import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.portafib.ejb.PrioritatLocal;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.fields.PrioritatFields;
import org.fundaciobit.genapp.common.web.controller.RefListBase;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class PrioritatRefList extends RefListBase
    implements PrioritatFields {

  @EJB(mappedName = PrioritatLocal.JNDI_NAME)
  private PrioritatLocal prioritatEjb;

  public PrioritatRefList(PrioritatRefList __clone) {
    super(__clone);
    this.prioritatEjb = __clone.prioritatEjb;
  }
  public PrioritatRefList() {
    setSelects(new Select<?>[] { NOM.select });
  }
  public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
    Select<StringKeyValue> select =  new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(keyField.select, getSeparator(), getSelects());
    List<StringKeyValue> list = prioritatEjb.executeQuery(select, where, (orderBy==null || orderBy.length == 0) ? getOrderBy() : orderBy);
    for (StringKeyValue skv : list) {
      skv.setValue(org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(skv.getValue()));
    }
    return list;
  }
}
