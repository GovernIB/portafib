
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


public class RebreAvisJPAManager
		 extends AbstractPortaFIBJPAManager<RebreAvis, Long>
		 implements IRebreAvisManager, RebreAvisFields {




  private static final long serialVersionUID = 1908931667L;

	 public static final TableName<RebreAvis> _TABLENAME =  new TableName<RebreAvis>("RebreAvisJPA");



  static final ModificationManager<RebreAvis> __eventManager = new ModificationManager<RebreAvis>();


  @PersistenceContext
  protected EntityManager __em;
  public RebreAvisJPAManager() {
  }
  protected RebreAvisJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return RebreAvisJPA. class;
	}



	public ModificationManager<RebreAvis> getEventManager() {
	return __eventManager;
	}


	public TableName<RebreAvis> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public RebreAvis[] listToArray(List<RebreAvis> list)  {
		if(list == null) { return null; };
		return list.toArray(new RebreAvis[list.size()]);
	};

	public RebreAvis create( java.lang.String _usuariEntitatID_, long _tipusNotificacioID_, boolean _rebreAgrupat_) throws I18NException {
		RebreAvisJPA __bean =  new RebreAvisJPA(_usuariEntitatID_,_tipusNotificacioID_,_rebreAgrupat_);
		return create(__bean);
	}



 public void delete(long _id_) {
   delete(findByPrimaryKey(_id_));
 }




	public RebreAvis findByPrimaryKey(long _id_) {
	  return __em.find(RebreAvisJPA.class, _id_);  
	}
	@Override
	protected RebreAvis getJPAInstance(RebreAvis __bean) {
		return convertToJPA(__bean);
	}


	public static RebreAvisJPA convertToJPA(RebreAvis __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof RebreAvisJPA) {
	    return (RebreAvisJPA)__bean;
	  }
	  
	  return RebreAvisJPA.toJPA(__bean);
	}


}