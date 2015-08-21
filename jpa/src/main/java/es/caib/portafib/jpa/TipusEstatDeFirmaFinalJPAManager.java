
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


public class TipusEstatDeFirmaFinalJPAManager
		 extends AbstractPortaFIBJPAManager<TipusEstatDeFirmaFinal, Long>
		 implements ITipusEstatDeFirmaFinalManager, TipusEstatDeFirmaFinalFields {




  private static final long serialVersionUID = -98846042L;

	 public static final TableName<TipusEstatDeFirmaFinal> _TABLENAME =  new TableName<TipusEstatDeFirmaFinal>("TipusEstatDeFirmaFinalJPA");



  static final ModificationManager<TipusEstatDeFirmaFinal> __eventManager = new ModificationManager<TipusEstatDeFirmaFinal>();


  @PersistenceContext
  protected EntityManager __em;
  public TipusEstatDeFirmaFinalJPAManager() {
  }
  protected TipusEstatDeFirmaFinalJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return TipusEstatDeFirmaFinalJPA. class;
	}



	public ModificationManager<TipusEstatDeFirmaFinal> getEventManager() {
	return __eventManager;
	}


	public TableName<TipusEstatDeFirmaFinal> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public TipusEstatDeFirmaFinal[] listToArray(List<TipusEstatDeFirmaFinal> list)  {
		if(list == null) { return null; };
		return list.toArray(new TipusEstatDeFirmaFinal[list.size()]);
	};

	public synchronized TipusEstatDeFirmaFinal create( long _tipusEstatDeFirmaFinalID_, java.lang.String _nom_, java.lang.String _descripcio_) throws I18NException {
		TipusEstatDeFirmaFinalJPA __bean =  new TipusEstatDeFirmaFinalJPA(_tipusEstatDeFirmaFinalID_,_nom_,_descripcio_);
		return create(__bean);
	}



 public void delete(long _tipusEstatDeFirmaFinalID_) {
   delete(findByPrimaryKey(_tipusEstatDeFirmaFinalID_));
 }




	public TipusEstatDeFirmaFinal findByPrimaryKey(long _tipusEstatDeFirmaFinalID_) {
	  return __em.find(TipusEstatDeFirmaFinalJPA.class, _tipusEstatDeFirmaFinalID_);  
	}
	@Override
	protected TipusEstatDeFirmaFinal getJPAInstance(TipusEstatDeFirmaFinal __bean) {
		return convertToJPA(__bean);
	}


	public static TipusEstatDeFirmaFinalJPA convertToJPA(TipusEstatDeFirmaFinal __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof TipusEstatDeFirmaFinalJPA) {
	    return (TipusEstatDeFirmaFinalJPA)__bean;
	  }
	  
	  return TipusEstatDeFirmaFinalJPA.toJPA(__bean);
	}


}