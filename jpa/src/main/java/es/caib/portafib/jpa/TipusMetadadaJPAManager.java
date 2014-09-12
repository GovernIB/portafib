
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


public class TipusMetadadaJPAManager
		 extends AbstractPortaFIBJPAManager<TipusMetadada, Integer >
		 implements ITipusMetadadaManager, TipusMetadadaFields {




  private static final long serialVersionUID = 1808630314L;

	 public static final TableName<TipusMetadada> _TABLENAME =  new TableName<TipusMetadada>("TipusMetadadaJPA");



  static final ModificationManager<TipusMetadada> __eventManager = new ModificationManager<TipusMetadada>();


  @PersistenceContext
  protected EntityManager __em;
  public TipusMetadadaJPAManager() {
  }
  protected TipusMetadadaJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return TipusMetadadaJPA. class;
	}



	public ModificationManager<TipusMetadada> getEventManager() {
	return __eventManager;
	}


	public TableName<TipusMetadada> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public TipusMetadada[] listToArray(List<TipusMetadada> list)  {
		if(list == null) { return null; };
		return list.toArray(new TipusMetadada[list.size()]);
	};

	public synchronized TipusMetadada create( int _tipusMetadadaID_, java.lang.String _nom_, java.lang.String _descripcio_) throws I18NException {
		TipusMetadadaJPA __bean =  new TipusMetadadaJPA(_tipusMetadadaID_,_nom_,_descripcio_);
		return create(__bean);
	}



 public void delete(int _tipusMetadadaID_) {
   delete(findByPrimaryKey(_tipusMetadadaID_));
 }




	public TipusMetadada findByPrimaryKey(int _tipusMetadadaID_) {
	  return __em.find(TipusMetadadaJPA.class, _tipusMetadadaID_);  
	}
	@Override
	protected TipusMetadada getJPAInstance(TipusMetadada __bean) {
		return convertToJPA(__bean);
	}


	public static TipusMetadadaJPA convertToJPA(TipusMetadada __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof TipusMetadadaJPA) {
	    return (TipusMetadadaJPA)__bean;
	  }
	  
	  return TipusMetadadaJPA.toJPA(__bean);
	}


}