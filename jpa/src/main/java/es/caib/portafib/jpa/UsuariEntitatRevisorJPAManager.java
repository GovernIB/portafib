
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


public class UsuariEntitatRevisorJPAManager
		 extends AbstractPortaFIBJPAManager<UsuariEntitatRevisor, Long>
		 implements IUsuariEntitatRevisorManager, UsuariEntitatRevisorFields {




  private static final long serialVersionUID = 280243898L;

	 public static final TableName<UsuariEntitatRevisor> _TABLENAME =  new TableName<UsuariEntitatRevisor>("UsuariEntitatRevisorJPA");



  static final ModificationManager<UsuariEntitatRevisor> __eventManager = new ModificationManager<UsuariEntitatRevisor>();


  @PersistenceContext
  protected EntityManager __em;
  public UsuariEntitatRevisorJPAManager() {
  }
  protected UsuariEntitatRevisorJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return UsuariEntitatRevisorJPA. class;
	}



	public ModificationManager<UsuariEntitatRevisor> getEventManager() {
	return __eventManager;
	}


	public TableName<UsuariEntitatRevisor> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public UsuariEntitatRevisor[] listToArray(List<UsuariEntitatRevisor> list)  {
		if(list == null) { return null; };
		return list.toArray(new UsuariEntitatRevisor[list.size()]);
	};

	public synchronized UsuariEntitatRevisor create( java.lang.String _usuariEntitatID_, boolean _actiu_) throws I18NException {
		UsuariEntitatRevisorJPA __bean =  new UsuariEntitatRevisorJPA(_usuariEntitatID_,_actiu_);
		return create(__bean);
	}



 public void delete(long _usuariEntitatRevisorId_) {
   delete(findByPrimaryKey(_usuariEntitatRevisorId_));
 }




	public UsuariEntitatRevisor findByPrimaryKey(long _usuariEntitatRevisorId_) {
	  return __em.find(UsuariEntitatRevisorJPA.class, _usuariEntitatRevisorId_);  
	}
	@Override
	protected UsuariEntitatRevisor getJPAInstance(UsuariEntitatRevisor __bean) {
		return convertToJPA(__bean);
	}


	public static UsuariEntitatRevisorJPA convertToJPA(UsuariEntitatRevisor __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof UsuariEntitatRevisorJPA) {
	    return (UsuariEntitatRevisorJPA)__bean;
	  }
	  
	  return UsuariEntitatRevisorJPA.toJPA(__bean);
	}


}