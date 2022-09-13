
package es.caib.portafib.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.portafib.model.entity.*;
import es.caib.portafib.model.fields.*;
import es.caib.portafib.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class RoleUsuariEntitatJPAManager
         extends AbstractJPAManager<RoleUsuariEntitat, Long>
         implements RoleUsuariEntitatIJPAManager, IRoleUsuariEntitatManager, RoleUsuariEntitatFields {



    public static final TableName<RoleUsuariEntitat> _TABLENAME =  new TableName<RoleUsuariEntitat>("RoleUsuariEntitatJPA");


    @PersistenceContext
    protected EntityManager __em;

    public RoleUsuariEntitatJPAManager() {
    }

    protected RoleUsuariEntitatJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return RoleUsuariEntitatJPA. class;
    }



    public TableName<RoleUsuariEntitat> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public RoleUsuariEntitat[] listToArray(List<RoleUsuariEntitat> list)  {
        if(list == null) { return null; };
        return list.toArray(new RoleUsuariEntitat[list.size()]);
    };

    public RoleUsuariEntitat create( java.lang.String _roleID_, java.lang.String _usuariEntitatID_) throws I18NException {
        RoleUsuariEntitatJPA __bean =  new RoleUsuariEntitatJPA(_roleID_,_usuariEntitatID_);
        return create(__bean);
    }



 public void delete(long _id_) {
   delete(findByPrimaryKey(_id_));
 }




    public RoleUsuariEntitat findByPrimaryKey(long _id_) {
        return __em.find(RoleUsuariEntitatJPA.class, _id_);  
    }
    @Override
    protected RoleUsuariEntitat getJPAInstance(RoleUsuariEntitat __bean) {
        return convertToJPA(__bean);
    }


    public static RoleUsuariEntitatJPA convertToJPA(RoleUsuariEntitat __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof RoleUsuariEntitatJPA) {
        return (RoleUsuariEntitatJPA)__bean;
      }
      
      return RoleUsuariEntitatJPA.toJPA(__bean);
    }


}