
package es.caib.portafib.back.form.webdb;

import java.util.List;
import javax.ejb.EJB;
import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.portafib.ejb.TipusDocumentColaboracioDelegacioLocal;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.portafib.model.fields.TipusDocumentColaboracioDelegacioFields;
import org.fundaciobit.genapp.common.web.controller.RefListBase;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class TipusDocumentColaboracioDelegacioRefList extends RefListBase
    implements TipusDocumentColaboracioDelegacioFields {

  @EJB(mappedName = TipusDocumentColaboracioDelegacioLocal.JNDI_NAME)
  private TipusDocumentColaboracioDelegacioLocal tipusDocumentColaboracioDelegacioEjb;

  public TipusDocumentColaboracioDelegacioRefList(TipusDocumentColaboracioDelegacioRefList __clone) {
    super(__clone);
    this.tipusDocumentColaboracioDelegacioEjb = __clone.tipusDocumentColaboracioDelegacioEjb;
  }
  public TipusDocumentColaboracioDelegacioRefList() {
    setSelects(new Select<?>[] { COLABORACIODELEGACIOID.select, TIPUSDOCUMENTID.select });
  }
  public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
    Select<StringKeyValue> select =  new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(keyField.select, getSeparator(), getSelects());
    List<StringKeyValue> list = tipusDocumentColaboracioDelegacioEjb.executeQuery(select, where, (orderBy==null || orderBy.length == 0) ? getOrderBy() : orderBy);
    return list;
  }
}
