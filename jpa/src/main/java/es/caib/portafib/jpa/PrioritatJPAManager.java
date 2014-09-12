
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


public class PrioritatJPAManager
		 extends AbstractPortaFIBJPAManager<Prioritat, Integer >
		 implements IPrioritatManager, PrioritatFields {




  private static final long serialVersionUID = -1226273350L;

	 public static final TableName<Prioritat> _TABLENAME =  new TableName<Prioritat>("PrioritatJPA");



  static final ModificationManager<Prioritat> __eventManager = new ModificationManager<Prioritat>();


  @PersistenceContext
  protected EntityManager __em;
  public PrioritatJPAManager() {
  }
  protected PrioritatJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return PrioritatJPA. class;
	}



	public ModificationManager<Prioritat> getEventManager() {
	return __eventManager;
	}


	public TableName<Prioritat> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public Prioritat[] listToArray(List<Prioritat> list)  {
		if(list == null) { return null; };
		return list.toArray(new Prioritat[list.size()]);
	};

	public synchronized Prioritat create( int _prioritatID_, java.lang.String _nom_) throws I18NException {
		PrioritatJPA __bean =  new PrioritatJPA(_prioritatID_,_nom_);
		return create(__bean);
	}



 public void delete(int _prioritatID_) {
   delete(findByPrimaryKey(_prioritatID_));
 }




	public Prioritat findByPrimaryKey(int _prioritatID_) {
	  return __em.find(PrioritatJPA.class, _prioritatID_);  
	}
	@Override
	protected Prioritat getJPAInstance(Prioritat __bean) {
		return convertToJPA(__bean);
	}


	public static PrioritatJPA convertToJPA(Prioritat __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof PrioritatJPA) {
	    return (PrioritatJPA)__bean;
	  }
	  
	  return PrioritatJPA.toJPA(__bean);
	}


}