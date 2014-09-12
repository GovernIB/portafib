
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


public class GrupEntitatJPAManager
		 extends AbstractPortaFIBJPAManager<GrupEntitat, Long >
		 implements IGrupEntitatManager, GrupEntitatFields {




  private static final long serialVersionUID = 103036405L;

	 public static final TableName<GrupEntitat> _TABLENAME =  new TableName<GrupEntitat>("GrupEntitatJPA");



  static final ModificationManager<GrupEntitat> __eventManager = new ModificationManager<GrupEntitat>();


  @PersistenceContext
  protected EntityManager __em;
  public GrupEntitatJPAManager() {
  }
  protected GrupEntitatJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return GrupEntitatJPA. class;
	}



	public ModificationManager<GrupEntitat> getEventManager() {
	return __eventManager;
	}


	public TableName<GrupEntitat> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public GrupEntitat[] listToArray(List<GrupEntitat> list)  {
		if(list == null) { return null; };
		return list.toArray(new GrupEntitat[list.size()]);
	};

	public synchronized GrupEntitat create( java.lang.String _nom_, java.lang.String _descripcio_, java.lang.String _entitatID_) throws I18NException {
		GrupEntitatJPA __bean =  new GrupEntitatJPA(_nom_,_descripcio_,_entitatID_);
		return create(__bean);
	}



 public void delete(long _grupEntitatID_) {
   delete(findByPrimaryKey(_grupEntitatID_));
 }




	public GrupEntitat findByPrimaryKey(long _grupEntitatID_) {
	  return __em.find(GrupEntitatJPA.class, _grupEntitatID_);  
	}
	@Override
	protected GrupEntitat getJPAInstance(GrupEntitat __bean) {
		return convertToJPA(__bean);
	}


	public static GrupEntitatJPA convertToJPA(GrupEntitat __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof GrupEntitatJPA) {
	    return (GrupEntitatJPA)__bean;
	  }
	  
	  return GrupEntitatJPA.toJPA(__bean);
	}


}