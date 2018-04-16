
package es.caib.portafib.jpa;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.portafib.model.entity.*;
import es.caib.portafib.model.fields.*;
import es.caib.portafib.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.events.ModificationManager;


public class PluginFirmaWebPerUsuariAplicacioJPAManager
		 extends AbstractPortaFIBJPAManager<PluginFirmaWebPerUsuariAplicacio, Long>
		 implements IPluginFirmaWebPerUsuariAplicacioManager, PluginFirmaWebPerUsuariAplicacioFields {




  private static final long serialVersionUID = 2099850551L;

	 public static final TableName<PluginFirmaWebPerUsuariAplicacio> _TABLENAME =  new TableName<PluginFirmaWebPerUsuariAplicacio>("PluginFirmaWebPerUsuariAplicacioJPA");



  static final ModificationManager<PluginFirmaWebPerUsuariAplicacio> __eventManager = new ModificationManager<PluginFirmaWebPerUsuariAplicacio>();


  @PersistenceContext
  protected EntityManager __em;
  public PluginFirmaWebPerUsuariAplicacioJPAManager() {
  }
  protected PluginFirmaWebPerUsuariAplicacioJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return PluginFirmaWebPerUsuariAplicacioJPA. class;
	}



	public ModificationManager<PluginFirmaWebPerUsuariAplicacio> getEventManager() {
	return __eventManager;
	}


	public TableName<PluginFirmaWebPerUsuariAplicacio> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public PluginFirmaWebPerUsuariAplicacio[] listToArray(List<PluginFirmaWebPerUsuariAplicacio> list)  {
		if(list == null) { return null; };
		return list.toArray(new PluginFirmaWebPerUsuariAplicacio[list.size()]);
	};

	public synchronized PluginFirmaWebPerUsuariAplicacio create( java.lang.String _usuariAplicacioID_, long _pluginFirmaWebID_, int _accio_) throws I18NException {
		PluginFirmaWebPerUsuariAplicacioJPA __bean =  new PluginFirmaWebPerUsuariAplicacioJPA(_usuariAplicacioID_,_pluginFirmaWebID_,_accio_);
		return create(__bean);
	}



 public void delete(long _pluginfirmawebperusrappid_) {
   delete(findByPrimaryKey(_pluginfirmawebperusrappid_));
 }




	public PluginFirmaWebPerUsuariAplicacio findByPrimaryKey(long _pluginfirmawebperusrappid_) {
	  return __em.find(PluginFirmaWebPerUsuariAplicacioJPA.class, _pluginfirmawebperusrappid_);  
	}
	@Override
	protected PluginFirmaWebPerUsuariAplicacio getJPAInstance(PluginFirmaWebPerUsuariAplicacio __bean) {
		return convertToJPA(__bean);
	}


	public static PluginFirmaWebPerUsuariAplicacioJPA convertToJPA(PluginFirmaWebPerUsuariAplicacio __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof PluginFirmaWebPerUsuariAplicacioJPA) {
	    return (PluginFirmaWebPerUsuariAplicacioJPA)__bean;
	  }
	  
	  return PluginFirmaWebPerUsuariAplicacioJPA.toJPA(__bean);
	}


}