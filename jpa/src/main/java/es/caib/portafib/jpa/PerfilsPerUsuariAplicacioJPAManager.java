
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


public class PerfilsPerUsuariAplicacioJPAManager
		 extends AbstractPortaFIBJPAManager<PerfilsPerUsuariAplicacio, Long>
		 implements IPerfilsPerUsuariAplicacioManager, PerfilsPerUsuariAplicacioFields {




  private static final long serialVersionUID = 929072090L;

	 public static final TableName<PerfilsPerUsuariAplicacio> _TABLENAME =  new TableName<PerfilsPerUsuariAplicacio>("PerfilsPerUsuariAplicacioJPA");



  static final ModificationManager<PerfilsPerUsuariAplicacio> __eventManager = new ModificationManager<PerfilsPerUsuariAplicacio>();


  @PersistenceContext
  protected EntityManager __em;
  public PerfilsPerUsuariAplicacioJPAManager() {
  }
  protected PerfilsPerUsuariAplicacioJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return PerfilsPerUsuariAplicacioJPA. class;
	}



	public ModificationManager<PerfilsPerUsuariAplicacio> getEventManager() {
	return __eventManager;
	}


	public TableName<PerfilsPerUsuariAplicacio> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public PerfilsPerUsuariAplicacio[] listToArray(List<PerfilsPerUsuariAplicacio> list)  {
		if(list == null) { return null; };
		return list.toArray(new PerfilsPerUsuariAplicacio[list.size()]);
	};

	public PerfilsPerUsuariAplicacio create( long _perfilDeFirmaID_, java.lang.String _usuariAplicacioID_) throws I18NException {
		PerfilsPerUsuariAplicacioJPA __bean =  new PerfilsPerUsuariAplicacioJPA(_perfilDeFirmaID_,_usuariAplicacioID_);
		return create(__bean);
	}



 public void delete(long _perfilsPerUsrAppID_) {
   delete(findByPrimaryKey(_perfilsPerUsrAppID_));
 }




	public PerfilsPerUsuariAplicacio findByPrimaryKey(long _perfilsPerUsrAppID_) {
	  return __em.find(PerfilsPerUsuariAplicacioJPA.class, _perfilsPerUsrAppID_);  
	}
	@Override
	protected PerfilsPerUsuariAplicacio getJPAInstance(PerfilsPerUsuariAplicacio __bean) {
		return convertToJPA(__bean);
	}


	public static PerfilsPerUsuariAplicacioJPA convertToJPA(PerfilsPerUsuariAplicacio __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof PerfilsPerUsuariAplicacioJPA) {
	    return (PerfilsPerUsuariAplicacioJPA)__bean;
	  }
	  
	  return PerfilsPerUsuariAplicacioJPA.toJPA(__bean);
	}


}