
package es.caib.portafib.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.portafib.model.entity.*;
import es.caib.portafib.model.fields.*;
import es.caib.portafib.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class GrupEntitatJPAManager
         extends AbstractJPAManager<GrupEntitat, Long>
         implements GrupEntitatIJPAManager, IGrupEntitatManager, GrupEntitatFields {



    public static final TableName<GrupEntitat> _TABLENAME =  new TableName<GrupEntitat>("GrupEntitatJPA");


    @PersistenceContext
    protected EntityManager __em;

    public GrupEntitatJPAManager() {
    }

    protected GrupEntitatJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return GrupEntitatJPA. class;
    }



    public TableName<GrupEntitat> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public GrupEntitat[] listToArray(List<GrupEntitat> list)  {
        if(list == null) { return null; };
        return list.toArray(new GrupEntitat[list.size()]);
    };

    public GrupEntitat create( java.lang.String _nom_, java.lang.String _descripcio_, java.lang.String _entitatID_) throws I18NException {
        GrupEntitatJPA __bean =  new GrupEntitatJPA(_nom_,_descripcio_,_entitatID_);
        return create(__bean);
    }



 public void delete(long _grupEntitatID_) {
   delete(findByPrimaryKey(_grupEntitatID_));
 }




    public GrupEntitat findByPrimaryKey(long _grupEntitatID_) {
        return __em.find(GrupEntitatJPA.class, _grupEntitatID_);  
    }
    @Override
    protected GrupEntitat getJPAInstance(GrupEntitat __bean) {
        return convertToJPA(__bean);
    }


    public static GrupEntitatJPA convertToJPA(GrupEntitat __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof GrupEntitatJPA) {
        return (GrupEntitatJPA)__bean;
      }
      
      return GrupEntitatJPA.toJPA(__bean);
    }


}