
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


public class RoleUsuariAplicacioJPAManager
		 extends AbstractPortaFIBJPAManager<RoleUsuariAplicacio, Long >
		 implements IRoleUsuariAplicacioManager, RoleUsuariAplicacioFields {




  private static final long serialVersionUID = 464531162L;

	 public static final TableName<RoleUsuariAplicacio> _TABLENAME =  new TableName<RoleUsuariAplicacio>("RoleUsuariAplicacioJPA");



  static final ModificationManager<RoleUsuariAplicacio> __eventManager = new ModificationManager<RoleUsuariAplicacio>();


  @PersistenceContext
  protected EntityManager __em;
  public RoleUsuariAplicacioJPAManager() {
  }
  protected RoleUsuariAplicacioJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return RoleUsuariAplicacioJPA. class;
	}



	public ModificationManager<RoleUsuariAplicacio> getEventManager() {
	return __eventManager;
	}


	public TableName<RoleUsuariAplicacio> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public RoleUsuariAplicacio[] listToArray(List<RoleUsuariAplicacio> list)  {
		if(list == null) { return null; };
		return list.toArray(new RoleUsuariAplicacio[list.size()]);
	};

	public synchronized RoleUsuariAplicacio create( java.lang.String _roleID_, java.lang.String _usuariAplicacioID_) throws I18NException {
		RoleUsuariAplicacioJPA __bean =  new RoleUsuariAplicacioJPA(_roleID_,_usuariAplicacioID_);
		return create(__bean);
	}



 public void delete(long _id_) {
   delete(findByPrimaryKey(_id_));
 }




	public RoleUsuariAplicacio findByPrimaryKey(long _id_) {
	  return __em.find(RoleUsuariAplicacioJPA.class, _id_);  
	}
	@Override
	protected RoleUsuariAplicacio getJPAInstance(RoleUsuariAplicacio __bean) {
		return convertToJPA(__bean);
	}


	public static RoleUsuariAplicacioJPA convertToJPA(RoleUsuariAplicacio __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof RoleUsuariAplicacioJPA) {
	    return (RoleUsuariAplicacioJPA)__bean;
	  }
	  
	  return RoleUsuariAplicacioJPA.toJPA(__bean);
	}


}