
package es.caib.portafib.back.form.webdb;

import java.util.List;
import javax.ejb.EJB;
import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.portafib.ejb.TipusEstatDeFirmaFinalLocal;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.fields.TipusEstatDeFirmaFinalFields;
import org.fundaciobit.genapp.common.web.controller.RefListBase;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class TipusEstatDeFirmaFinalRefList extends RefListBase
    implements TipusEstatDeFirmaFinalFields {

  @EJB(mappedName = TipusEstatDeFirmaFinalLocal.JNDI_NAME)
  private TipusEstatDeFirmaFinalLocal tipusEstatDeFirmaFinalEjb;

  public TipusEstatDeFirmaFinalRefList(TipusEstatDeFirmaFinalRefList __clone) {
    super(__clone);
    this.tipusEstatDeFirmaFinalEjb = __clone.tipusEstatDeFirmaFinalEjb;
  }
  public TipusEstatDeFirmaFinalRefList() {
    setSelects(new Select<?>[] { NOM.select });
  }
  public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
    Select<StringKeyValue> select =  new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(keyField.select, getSeparator(), getSelects());
    List<StringKeyValue> list = tipusEstatDeFirmaFinalEjb.executeQuery(select, where, (orderBy==null || orderBy.length == 0) ? getOrderBy() : orderBy);
    for (StringKeyValue skv : list) {
      skv.setValue(org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(skv.getValue()));
    }
    return list;
  }
}
