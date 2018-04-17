
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


public class UsuariAplicacioJPAManager
		 extends AbstractPortaFIBJPAManager<UsuariAplicacio, String>
		 implements IUsuariAplicacioManager, UsuariAplicacioFields {




  private static final long serialVersionUID = -1937379664L;

	 public static final TableName<UsuariAplicacio> _TABLENAME =  new TableName<UsuariAplicacio>("UsuariAplicacioJPA");



  static final ModificationManager<UsuariAplicacio> __eventManager = new ModificationManager<UsuariAplicacio>();


  @PersistenceContext
  protected EntityManager __em;
  public UsuariAplicacioJPAManager() {
  }
  protected UsuariAplicacioJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return UsuariAplicacioJPA. class;
	}



	public ModificationManager<UsuariAplicacio> getEventManager() {
	return __eventManager;
	}


	public TableName<UsuariAplicacio> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public UsuariAplicacio[] listToArray(List<UsuariAplicacio> list)  {
		if(list == null) { return null; };
		return list.toArray(new UsuariAplicacio[list.size()]);
	};

	public synchronized UsuariAplicacio create( java.lang.String _usuariAplicacioID_, java.lang.String _contrasenya_, java.lang.String _entitatID_, java.lang.String _emailAdmin_, int _callbackVersio_, java.lang.String _callbackURL_, boolean _actiu_, java.lang.String _idiomaID_, java.lang.String _descripcio_, java.lang.Long _logoSegellID_, java.lang.Boolean _potCustodiar_, int _politicaCustodia_, int _politicaDePluginFirmaWeb_) throws I18NException {
		UsuariAplicacioJPA __bean =  new UsuariAplicacioJPA(_usuariAplicacioID_,_contrasenya_,_entitatID_,_emailAdmin_,_callbackVersio_,_callbackURL_,_actiu_,_idiomaID_,_descripcio_,_logoSegellID_,_potCustodiar_,_politicaCustodia_,_politicaDePluginFirmaWeb_);
		return create(__bean);
	}



 public void delete(java.lang.String _usuariAplicacioID_) {
   delete(findByPrimaryKey(_usuariAplicacioID_));
 }




	public UsuariAplicacio findByPrimaryKey(java.lang.String _usuariAplicacioID_) {
	  return __em.find(UsuariAplicacioJPA.class, _usuariAplicacioID_);  
	}
	@Override
	protected UsuariAplicacio getJPAInstance(UsuariAplicacio __bean) {
		return convertToJPA(__bean);
	}


	public static UsuariAplicacioJPA convertToJPA(UsuariAplicacio __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof UsuariAplicacioJPA) {
	    return (UsuariAplicacioJPA)__bean;
	  }
	  
	  return UsuariAplicacioJPA.toJPA(__bean);
	}


}