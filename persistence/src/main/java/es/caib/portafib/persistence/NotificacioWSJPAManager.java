
package es.caib.portafib.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.portafib.model.entity.*;
import es.caib.portafib.model.fields.*;
import es.caib.portafib.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class NotificacioWSJPAManager
         extends AbstractJPAManager<NotificacioWS, Long>
         implements NotificacioWSIJPAManager, INotificacioWSManager, NotificacioWSFields {



    public static final TableName<NotificacioWS> _TABLENAME =  new TableName<NotificacioWS>("NotificacioWSJPA");


    @PersistenceContext
    protected EntityManager __em;

    public NotificacioWSJPAManager() {
    }

    protected NotificacioWSJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return NotificacioWSJPA. class;
    }



    public TableName<NotificacioWS> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public NotificacioWS[] listToArray(List<NotificacioWS> list)  {
        if(list == null) { return null; };
        return list.toArray(new NotificacioWS[list.size()]);
    };

    public NotificacioWS create( long _peticioDeFirmaID_, long _tipusNotificacioID_, java.sql.Timestamp _dataCreacio_, java.sql.Timestamp _dataEnviament_, java.lang.String _descripcio_, boolean _bloquejada_, java.lang.String _error_, java.sql.Timestamp _dataError_, int _reintents_, java.lang.String _usuariAplicacioID_) throws I18NException {
        NotificacioWSJPA __bean =  new NotificacioWSJPA(_peticioDeFirmaID_,_tipusNotificacioID_,_dataCreacio_,_dataEnviament_,_descripcio_,_bloquejada_,_error_,_dataError_,_reintents_,_usuariAplicacioID_);
        return create(__bean);
    }



 public void delete(long _notificacioID_) {
   delete(findByPrimaryKey(_notificacioID_));
 }




    public NotificacioWS findByPrimaryKey(long _notificacioID_) {
        return __em.find(NotificacioWSJPA.class, _notificacioID_);  
    }
    @Override
    protected NotificacioWS getJPAInstance(NotificacioWS __bean) {
        return convertToJPA(__bean);
    }


    public static NotificacioWSJPA convertToJPA(NotificacioWS __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof NotificacioWSJPA) {
        return (NotificacioWSJPA)__bean;
      }
      
      return NotificacioWSJPA.toJPA(__bean);
    }


}