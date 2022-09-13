
package es.caib.portafib.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.portafib.model.entity.*;
import es.caib.portafib.model.fields.*;
import es.caib.portafib.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class RevisorDeFirmaJPAManager
         extends AbstractJPAManager<RevisorDeFirma, Long>
         implements RevisorDeFirmaIJPAManager, IRevisorDeFirmaManager, RevisorDeFirmaFields {



    public static final TableName<RevisorDeFirma> _TABLENAME =  new TableName<RevisorDeFirma>("RevisorDeFirmaJPA");


    @PersistenceContext
    protected EntityManager __em;

    public RevisorDeFirmaJPAManager() {
    }

    protected RevisorDeFirmaJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return RevisorDeFirmaJPA. class;
    }



    public TableName<RevisorDeFirma> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public RevisorDeFirma[] listToArray(List<RevisorDeFirma> list)  {
        if(list == null) { return null; };
        return list.toArray(new RevisorDeFirma[list.size()]);
    };

    public RevisorDeFirma create( java.lang.String _usuariEntitatID_, long _firmaID_, boolean _obligatori_) throws I18NException {
        RevisorDeFirmaJPA __bean =  new RevisorDeFirmaJPA(_usuariEntitatID_,_firmaID_,_obligatori_);
        return create(__bean);
    }



 public void delete(long _revisorDeFirmaID_) {
   delete(findByPrimaryKey(_revisorDeFirmaID_));
 }




    public RevisorDeFirma findByPrimaryKey(long _revisorDeFirmaID_) {
        return __em.find(RevisorDeFirmaJPA.class, _revisorDeFirmaID_);  
    }
    @Override
    protected RevisorDeFirma getJPAInstance(RevisorDeFirma __bean) {
        return convertToJPA(__bean);
    }


    public static RevisorDeFirmaJPA convertToJPA(RevisorDeFirma __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof RevisorDeFirmaJPA) {
        return (RevisorDeFirmaJPA)__bean;
      }
      
      return RevisorDeFirmaJPA.toJPA(__bean);
    }


}