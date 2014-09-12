
package es.caib.portafib.back.form.webdb;

import java.util.List;
import javax.ejb.EJB;
import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.portafib.ejb.PosicioPaginaLocal;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.fields.PosicioPaginaFields;
import org.fundaciobit.genapp.common.web.controller.RefListBase;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class PosicioPaginaRefList extends RefListBase
    implements PosicioPaginaFields {

  @EJB(mappedName = PosicioPaginaLocal.JNDI_NAME)
  private PosicioPaginaLocal posicioPaginaEjb;

  public PosicioPaginaRefList(PosicioPaginaRefList __clone) {
    super(__clone);
    this.posicioPaginaEjb = __clone.posicioPaginaEjb;
  }
  public PosicioPaginaRefList() {
    setSelects(new Select<?>[] { NOM.select });
  }
  public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
    Select<StringKeyValue> select =  new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(keyField.select, getSeparator(), getSelects());
    List<StringKeyValue> list = posicioPaginaEjb.executeQuery(select, where, (orderBy==null || orderBy.length == 0) ? getOrderBy() : orderBy);
    for (StringKeyValue skv : list) {
      skv.setValue(org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(skv.getValue()));
    }
    return list;
  }
}
