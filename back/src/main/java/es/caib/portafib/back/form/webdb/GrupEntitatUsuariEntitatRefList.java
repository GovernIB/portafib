
package es.caib.portafib.back.form.webdb;

import java.util.List;
import javax.ejb.EJB;
import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.portafib.ejb.GrupEntitatUsuariEntitatService;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.fields.GrupEntitatUsuariEntitatFields;
import org.fundaciobit.genapp.common.web.controller.RefListBase;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class GrupEntitatUsuariEntitatRefList extends RefListBase
    implements GrupEntitatUsuariEntitatFields {

  @EJB(mappedName = GrupEntitatUsuariEntitatService.JNDI_NAME)
  private GrupEntitatUsuariEntitatService grupEntitatUsuariEntitatEjb;

  public GrupEntitatUsuariEntitatRefList(GrupEntitatUsuariEntitatRefList __clone) {
    super(__clone);
    this.grupEntitatUsuariEntitatEjb = __clone.grupEntitatUsuariEntitatEjb;
  }
  public GrupEntitatUsuariEntitatRefList() {
    setSelects(new Select<?>[] { GRUPENTITATUSUARIENTITATID.select });
  }
  public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
    Select<StringKeyValue> select =  new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(keyField.select, getSeparator(), getSelects());
    List<StringKeyValue> list = grupEntitatUsuariEntitatEjb.executeQuery(select, where, (orderBy==null || orderBy.length == 0) ? getOrderBy() : orderBy);
    return list;
  }
}
