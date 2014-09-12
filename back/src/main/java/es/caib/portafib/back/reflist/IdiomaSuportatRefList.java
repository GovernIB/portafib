package es.caib.portafib.back.reflist;

import java.util.List;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.portafib.back.form.webdb.IdiomaRefList;
import es.caib.portafib.model.fields.IdiomaFields;


/**
 * 
 * @author anadal
 *
 */
public class IdiomaSuportatRefList extends IdiomaRefList {
  
  public IdiomaSuportatRefList(IdiomaRefList __clone) {
    super(__clone);
  }

  @Override
  public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderby) throws I18NException {
    List<StringKeyValue> list = super.getReferenceList(keyField, Where.AND(where, IdiomaFields.SUPORTAT.equal(true)), orderby);
    return list;
  }   

}
