
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


public class RoleJPAManager
		 extends AbstractPortaFIBJPAManager<Role, String>
		 implements IRoleManager, RoleFields {




  private static final long serialVersionUID = -1462624120L;

	 public static final TableName<Role> _TABLENAME =  new TableName<Role>("RoleJPA");



  static final ModificationManager<Role> __eventManager = new ModificationManager<Role>();


  @PersistenceContext
  protected EntityManager __em;
  public RoleJPAManager() {
  }
  protected RoleJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return RoleJPA. class;
	}



	public ModificationManager<Role> getEventManager() {
	return __eventManager;
	}


	public TableName<Role> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public Role[] listToArray(List<Role> list)  {
		if(list == null) { return null; };
		return list.toArray(new Role[list.size()]);
	};

	public Role create( java.lang.String _roleID_, java.lang.String _nom_, java.lang.String _descripcio_) throws I18NException {
		RoleJPA __bean =  new RoleJPA(_roleID_,_nom_,_descripcio_);
		return create(__bean);
	}



 public void delete(java.lang.String _roleID_) {
   delete(findByPrimaryKey(_roleID_));
 }




	public Role findByPrimaryKey(java.lang.String _roleID_) {
	  return __em.find(RoleJPA.class, _roleID_);  
	}
	@Override
	protected Role getJPAInstance(Role __bean) {
		return convertToJPA(__bean);
	}


	public static RoleJPA convertToJPA(Role __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof RoleJPA) {
	    return (RoleJPA)__bean;
	  }
	  
	  return RoleJPA.toJPA(__bean);
	}


}