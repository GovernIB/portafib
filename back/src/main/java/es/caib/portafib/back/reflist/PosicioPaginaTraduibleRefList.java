package es.caib.portafib.back.reflist;

import java.util.List;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;

import es.caib.portafib.back.form.webdb.PosicioPaginaRefList;

/**
 * 
 * @author anadal
 *
 */
public class PosicioPaginaTraduibleRefList extends PosicioPaginaRefList {
  
  public PosicioPaginaTraduibleRefList(PosicioPaginaRefList __clone) {
    super(__clone);
  }

  @Override
  public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderby) throws I18NException {
    List<StringKeyValue> list = super.getReferenceList(keyField, where, orderby);
    for (StringKeyValue skv : list) {
        // Si no te traducció usar el valor        
        skv.setValue(I18NUtils.tradueix(true, skv.getValue()));
    }
    return list;
  }   

}
