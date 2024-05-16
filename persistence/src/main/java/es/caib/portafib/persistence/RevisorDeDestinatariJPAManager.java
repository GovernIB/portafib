
package es.caib.portafib.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.portafib.model.entity.*;
import es.caib.portafib.model.fields.*;
import es.caib.portafib.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class RevisorDeDestinatariJPAManager
         extends AbstractJPAManager<RevisorDeDestinatari, Long>
         implements RevisorDeDestinatariIJPAManager, IRevisorDeDestinatariManager, RevisorDeDestinatariFields {



    public static final TableName<RevisorDeDestinatari> _TABLENAME =  new TableName<RevisorDeDestinatari>("RevisorDeDestinatariJPA");


    @PersistenceContext
    protected EntityManager __em;

    public RevisorDeDestinatariJPAManager() {
    }

    protected RevisorDeDestinatariJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return RevisorDeDestinatariJPA. class;
    }



    public TableName<RevisorDeDestinatari> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public RevisorDeDestinatari[] listToArray(List<RevisorDeDestinatari> list)  {
        if(list == null) { return null; };
        return list.toArray(new RevisorDeDestinatari[list.size()]);
    };

    public RevisorDeDestinatari create( java.lang.String _destinatariID_, java.lang.String _revisorID_) throws I18NException {
        RevisorDeDestinatariJPA __bean =  new RevisorDeDestinatariJPA(_destinatariID_,_revisorID_);
        return create(__bean);
    }



 public void delete(long _revisorDeDestinatariID_) {
   delete(findByPrimaryKey(_revisorDeDestinatariID_));
 }




    public RevisorDeDestinatari findByPrimaryKey(long _revisorDeDestinatariID_) {
        return __em.find(RevisorDeDestinatariJPA.class, _revisorDeDestinatariID_);  
    }
    @Override
    protected RevisorDeDestinatari getJPAInstance(RevisorDeDestinatari __bean) {
        return convertToJPA(__bean);
    }


    public static RevisorDeDestinatariJPA convertToJPA(RevisorDeDestinatari __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof RevisorDeDestinatariJPA) {
        return (RevisorDeDestinatariJPA)__bean;
      }
      
      return RevisorDeDestinatariJPA.toJPA(__bean);
    }


}