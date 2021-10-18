
package es.caib.portafib.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.portafib.model.entity.*;
import es.caib.portafib.model.fields.*;
import es.caib.portafib.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class PluginFirmaWebPerUsuariEntitatJPAManager
         extends AbstractJPAManager<PluginFirmaWebPerUsuariEntitat, Long>
         implements PluginFirmaWebPerUsuariEntitatIJPAManager, IPluginFirmaWebPerUsuariEntitatManager, PluginFirmaWebPerUsuariEntitatFields {




    private static final long serialVersionUID = -1961971967L;

    public static final TableName<PluginFirmaWebPerUsuariEntitat> _TABLENAME =  new TableName<PluginFirmaWebPerUsuariEntitat>("PluginFirmaWebPerUsuariEntitatJPA");


    @PersistenceContext
    protected EntityManager __em;

    public PluginFirmaWebPerUsuariEntitatJPAManager() {
    }

    protected PluginFirmaWebPerUsuariEntitatJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return PluginFirmaWebPerUsuariEntitatJPA. class;
    }



    public TableName<PluginFirmaWebPerUsuariEntitat> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public PluginFirmaWebPerUsuariEntitat[] listToArray(List<PluginFirmaWebPerUsuariEntitat> list)  {
        if(list == null) { return null; };
        return list.toArray(new PluginFirmaWebPerUsuariEntitat[list.size()]);
    };

    public synchronized PluginFirmaWebPerUsuariEntitat create( java.lang.String _usuariEntitatID_, long _pluginFirmaWebID_, int _accio_) throws I18NException {
        PluginFirmaWebPerUsuariEntitatJPA __bean =  new PluginFirmaWebPerUsuariEntitatJPA(_usuariEntitatID_,_pluginFirmaWebID_,_accio_);
        return create(__bean);
    }



 public void delete(long _pluginFirmaWebPerUsrEntID_) {
   delete(findByPrimaryKey(_pluginFirmaWebPerUsrEntID_));
 }




    public PluginFirmaWebPerUsuariEntitat findByPrimaryKey(long _pluginFirmaWebPerUsrEntID_) {
        return __em.find(PluginFirmaWebPerUsuariEntitatJPA.class, _pluginFirmaWebPerUsrEntID_);  
    }
    @Override
    protected PluginFirmaWebPerUsuariEntitat getJPAInstance(PluginFirmaWebPerUsuariEntitat __bean) {
        return convertToJPA(__bean);
    }


    public static PluginFirmaWebPerUsuariEntitatJPA convertToJPA(PluginFirmaWebPerUsuariEntitat __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof PluginFirmaWebPerUsuariEntitatJPA) {
        return (PluginFirmaWebPerUsuariEntitatJPA)__bean;
      }
      
      return PluginFirmaWebPerUsuariEntitatJPA.toJPA(__bean);
    }


}