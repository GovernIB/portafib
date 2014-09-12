
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


public class GrupEntitatUsuariEntitatJPAManager
		 extends AbstractPortaFIBJPAManager<GrupEntitatUsuariEntitat, Long >
		 implements IGrupEntitatUsuariEntitatManager, GrupEntitatUsuariEntitatFields {




  private static final long serialVersionUID = 1049010455L;

	 public static final TableName<GrupEntitatUsuariEntitat> _TABLENAME =  new TableName<GrupEntitatUsuariEntitat>("GrupEntitatUsuariEntitatJPA");



  static final ModificationManager<GrupEntitatUsuariEntitat> __eventManager = new ModificationManager<GrupEntitatUsuariEntitat>();


  @PersistenceContext
  protected EntityManager __em;
  public GrupEntitatUsuariEntitatJPAManager() {
  }
  protected GrupEntitatUsuariEntitatJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return GrupEntitatUsuariEntitatJPA. class;
	}



	public ModificationManager<GrupEntitatUsuariEntitat> getEventManager() {
	return __eventManager;
	}


	public TableName<GrupEntitatUsuariEntitat> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public GrupEntitatUsuariEntitat[] listToArray(List<GrupEntitatUsuariEntitat> list)  {
		if(list == null) { return null; };
		return list.toArray(new GrupEntitatUsuariEntitat[list.size()]);
	};

	public synchronized GrupEntitatUsuariEntitat create( java.lang.String _usuariEntitatID_, java.lang.Long _grupEntitatID_) throws I18NException {
		GrupEntitatUsuariEntitatJPA __bean =  new GrupEntitatUsuariEntitatJPA(_usuariEntitatID_,_grupEntitatID_);
		return create(__bean);
	}



 public void delete(long _grupEntitatUsuariEntitatID_) {
   delete(findByPrimaryKey(_grupEntitatUsuariEntitatID_));
 }




	public GrupEntitatUsuariEntitat findByPrimaryKey(long _grupEntitatUsuariEntitatID_) {
	  return __em.find(GrupEntitatUsuariEntitatJPA.class, _grupEntitatUsuariEntitatID_);  
	}
	@Override
	protected GrupEntitatUsuariEntitat getJPAInstance(GrupEntitatUsuariEntitat __bean) {
		return convertToJPA(__bean);
	}


	public static GrupEntitatUsuariEntitatJPA convertToJPA(GrupEntitatUsuariEntitat __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof GrupEntitatUsuariEntitatJPA) {
	    return (GrupEntitatUsuariEntitatJPA)__bean;
	  }
	  
	  return GrupEntitatUsuariEntitatJPA.toJPA(__bean);
	}


}