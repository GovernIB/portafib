
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


public class TipusFirmaJPAManager
		 extends AbstractPortaFIBJPAManager<TipusFirma, Integer >
		 implements ITipusFirmaManager, TipusFirmaFields {




  private static final long serialVersionUID = -1822509060L;

	 public static final TableName<TipusFirma> _TABLENAME =  new TableName<TipusFirma>("TipusFirmaJPA");



  static final ModificationManager<TipusFirma> __eventManager = new ModificationManager<TipusFirma>();


  @PersistenceContext
  protected EntityManager __em;
  public TipusFirmaJPAManager() {
  }
  protected TipusFirmaJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return TipusFirmaJPA. class;
	}



	public ModificationManager<TipusFirma> getEventManager() {
	return __eventManager;
	}


	public TableName<TipusFirma> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public TipusFirma[] listToArray(List<TipusFirma> list)  {
		if(list == null) { return null; };
		return list.toArray(new TipusFirma[list.size()]);
	};

	public synchronized TipusFirma create( int _tipusFirmaID_, java.lang.String _nom_, boolean _suportada_, java.lang.String _descripcio_) throws I18NException {
		TipusFirmaJPA __bean =  new TipusFirmaJPA(_tipusFirmaID_,_nom_,_suportada_,_descripcio_);
		return create(__bean);
	}



 public void delete(int _tipusFirmaID_) {
   delete(findByPrimaryKey(_tipusFirmaID_));
 }




	public TipusFirma findByPrimaryKey(int _tipusFirmaID_) {
	  return __em.find(TipusFirmaJPA.class, _tipusFirmaID_);  
	}
	@Override
	protected TipusFirma getJPAInstance(TipusFirma __bean) {
		return convertToJPA(__bean);
	}


	public static TipusFirmaJPA convertToJPA(TipusFirma __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof TipusFirmaJPA) {
	    return (TipusFirmaJPA)__bean;
	  }
	  
	  return TipusFirmaJPA.toJPA(__bean);
	}


}