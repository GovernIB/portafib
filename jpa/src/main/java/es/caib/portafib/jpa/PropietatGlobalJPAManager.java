
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


public class PropietatGlobalJPAManager
		 extends AbstractPortaFIBJPAManager<PropietatGlobal, Long>
		 implements IPropietatGlobalManager, PropietatGlobalFields {




  private static final long serialVersionUID = -1396349219L;

	 public static final TableName<PropietatGlobal> _TABLENAME =  new TableName<PropietatGlobal>("PropietatGlobalJPA");



  static final ModificationManager<PropietatGlobal> __eventManager = new ModificationManager<PropietatGlobal>();


  @PersistenceContext
  protected EntityManager __em;
  public PropietatGlobalJPAManager() {
  }
  protected PropietatGlobalJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return PropietatGlobalJPA. class;
	}



	public ModificationManager<PropietatGlobal> getEventManager() {
	return __eventManager;
	}


	public TableName<PropietatGlobal> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public PropietatGlobal[] listToArray(List<PropietatGlobal> list)  {
		if(list == null) { return null; };
		return list.toArray(new PropietatGlobal[list.size()]);
	};

	public PropietatGlobal create( java.lang.String _clau_, java.lang.String _valor_, java.lang.String _entitatID_, java.lang.String _descripcio_) throws I18NException {
		PropietatGlobalJPA __bean =  new PropietatGlobalJPA(_clau_,_valor_,_entitatID_,_descripcio_);
		return create(__bean);
	}



 public void delete(long _propietatGlobalID_) {
   delete(findByPrimaryKey(_propietatGlobalID_));
 }




	public PropietatGlobal findByPrimaryKey(long _propietatGlobalID_) {
	  return __em.find(PropietatGlobalJPA.class, _propietatGlobalID_);  
	}
	@Override
	protected PropietatGlobal getJPAInstance(PropietatGlobal __bean) {
		return convertToJPA(__bean);
	}


	public static PropietatGlobalJPA convertToJPA(PropietatGlobal __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof PropietatGlobalJPA) {
	    return (PropietatGlobalJPA)__bean;
	  }
	  
	  return PropietatGlobalJPA.toJPA(__bean);
	}


}