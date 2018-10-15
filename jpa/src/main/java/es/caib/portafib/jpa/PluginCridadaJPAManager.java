
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


public class PluginCridadaJPAManager
		 extends AbstractPortaFIBJPAManager<PluginCridada, Long>
		 implements IPluginCridadaManager, PluginCridadaFields {




  private static final long serialVersionUID = 235523571L;

	 public static final TableName<PluginCridada> _TABLENAME =  new TableName<PluginCridada>("PluginCridadaJPA");



  static final ModificationManager<PluginCridada> __eventManager = new ModificationManager<PluginCridada>();


  @PersistenceContext
  protected EntityManager __em;
  public PluginCridadaJPAManager() {
  }
  protected PluginCridadaJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return PluginCridadaJPA. class;
	}



	public ModificationManager<PluginCridada> getEventManager() {
	return __eventManager;
	}


	public TableName<PluginCridada> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public PluginCridada[] listToArray(List<PluginCridada> list)  {
		if(list == null) { return null; };
		return list.toArray(new PluginCridada[list.size()]);
	};

	public synchronized PluginCridada create( java.lang.String _entitatID_, java.sql.Timestamp _data_, long _pluginID_, java.lang.String _metodePlugin_, java.lang.String _parametresText_, java.lang.Long _parametresFitxerID_, java.lang.String _retornText_, java.lang.Long _retornFitxerID_, int _tipusTesultat_, long _tempsExecucio_) throws I18NException {
		PluginCridadaJPA __bean =  new PluginCridadaJPA(_entitatID_,_data_,_pluginID_,_metodePlugin_,_parametresText_,_parametresFitxerID_,_retornText_,_retornFitxerID_,_tipusTesultat_,_tempsExecucio_);
		return create(__bean);
	}



 public void delete(long _pluginCridadaID_) {
   delete(findByPrimaryKey(_pluginCridadaID_));
 }




	public PluginCridada findByPrimaryKey(long _pluginCridadaID_) {
	  return __em.find(PluginCridadaJPA.class, _pluginCridadaID_);  
	}
	@Override
	protected PluginCridada getJPAInstance(PluginCridada __bean) {
		return convertToJPA(__bean);
	}


	public static PluginCridadaJPA convertToJPA(PluginCridada __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof PluginCridadaJPA) {
	    return (PluginCridadaJPA)__bean;
	  }
	  
	  return PluginCridadaJPA.toJPA(__bean);
	}


}