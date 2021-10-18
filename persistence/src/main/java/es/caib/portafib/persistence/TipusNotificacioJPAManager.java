
package es.caib.portafib.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.portafib.model.entity.*;
import es.caib.portafib.model.fields.*;
import es.caib.portafib.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class TipusNotificacioJPAManager
         extends AbstractJPAManager<TipusNotificacio, Long>
         implements TipusNotificacioIJPAManager, ITipusNotificacioManager, TipusNotificacioFields {




    private static final long serialVersionUID = 1514095147L;

    public static final TableName<TipusNotificacio> _TABLENAME =  new TableName<TipusNotificacio>("TipusNotificacioJPA");


    @PersistenceContext
    protected EntityManager __em;

    public TipusNotificacioJPAManager() {
    }

    protected TipusNotificacioJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return TipusNotificacioJPA. class;
    }



    public TableName<TipusNotificacio> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public TipusNotificacio[] listToArray(List<TipusNotificacio> list)  {
        if(list == null) { return null; };
        return list.toArray(new TipusNotificacio[list.size()]);
    };

    public synchronized TipusNotificacio create( long _tipusNotificacioID_, java.lang.String _nom_, java.lang.String _descripcio_, java.lang.Boolean _esAvis_) throws I18NException {
        TipusNotificacioJPA __bean =  new TipusNotificacioJPA(_tipusNotificacioID_,_nom_,_descripcio_,_esAvis_);
        return create(__bean);
    }



 public void delete(long _tipusNotificacioID_) {
   delete(findByPrimaryKey(_tipusNotificacioID_));
 }




    public TipusNotificacio findByPrimaryKey(long _tipusNotificacioID_) {
        return __em.find(TipusNotificacioJPA.class, _tipusNotificacioID_);  
    }
    @Override
    protected TipusNotificacio getJPAInstance(TipusNotificacio __bean) {
        return convertToJPA(__bean);
    }


    public static TipusNotificacioJPA convertToJPA(TipusNotificacio __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof TipusNotificacioJPA) {
        return (TipusNotificacioJPA)__bean;
      }
      
      return TipusNotificacioJPA.toJPA(__bean);
    }


}