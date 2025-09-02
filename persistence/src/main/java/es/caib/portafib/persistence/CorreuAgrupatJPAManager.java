
package es.caib.portafib.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.portafib.model.entity.*;
import es.caib.portafib.model.fields.*;
import es.caib.portafib.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class CorreuAgrupatJPAManager
         extends AbstractJPAManager<CorreuAgrupat, Long>
         implements CorreuAgrupatIJPAManager, ICorreuAgrupatManager, CorreuAgrupatFields {



    public static final TableName<CorreuAgrupat> _TABLENAME =  new TableName<CorreuAgrupat>("CorreuAgrupatJPA");


    @PersistenceContext
    protected EntityManager __em;

    public CorreuAgrupatJPAManager() {
    }

    protected CorreuAgrupatJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return CorreuAgrupatJPA. class;
    }



    public TableName<CorreuAgrupat> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public CorreuAgrupat[] listToArray(List<CorreuAgrupat> list)  {
        if(list == null) { return null; };
        return list.toArray(new CorreuAgrupat[list.size()]);
    };

    public CorreuAgrupat create( java.lang.String _email_, java.lang.String _subject_, java.lang.String _message_, boolean _html_, java.lang.String _usuariEntitatID_, java.sql.Timestamp _dataCreacio_, java.lang.String _error_) throws I18NException {
        CorreuAgrupatJPA __bean =  new CorreuAgrupatJPA(_email_,_subject_,_message_,_html_,_usuariEntitatID_,_dataCreacio_,_error_);
        return create(__bean);
    }



 public void delete(long _correuAgrupatID_) {
   delete(findByPrimaryKey(_correuAgrupatID_));
 }




    public CorreuAgrupat findByPrimaryKey(long _correuAgrupatID_) {
        return __em.find(CorreuAgrupatJPA.class, _correuAgrupatID_);  
    }
    @Override
    protected CorreuAgrupat getJPAInstance(CorreuAgrupat __bean) {
        return convertToJPA(__bean);
    }


    public static CorreuAgrupatJPA convertToJPA(CorreuAgrupat __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof CorreuAgrupatJPA) {
        return (CorreuAgrupatJPA)__bean;
      }
      
      return CorreuAgrupatJPA.toJPA(__bean);
    }


}