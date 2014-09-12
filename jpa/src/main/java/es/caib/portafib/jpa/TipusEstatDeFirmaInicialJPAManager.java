
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


public class TipusEstatDeFirmaInicialJPAManager
		 extends AbstractPortaFIBJPAManager<TipusEstatDeFirmaInicial, Long >
		 implements ITipusEstatDeFirmaInicialManager, TipusEstatDeFirmaInicialFields {




  private static final long serialVersionUID = -222221339L;

	 public static final TableName<TipusEstatDeFirmaInicial> _TABLENAME =  new TableName<TipusEstatDeFirmaInicial>("TipusEstatDeFirmaInicialJPA");



  static final ModificationManager<TipusEstatDeFirmaInicial> __eventManager = new ModificationManager<TipusEstatDeFirmaInicial>();


  @PersistenceContext
  protected EntityManager __em;
  public TipusEstatDeFirmaInicialJPAManager() {
  }
  protected TipusEstatDeFirmaInicialJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return TipusEstatDeFirmaInicialJPA. class;
	}



	public ModificationManager<TipusEstatDeFirmaInicial> getEventManager() {
	return __eventManager;
	}


	public TableName<TipusEstatDeFirmaInicial> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public TipusEstatDeFirmaInicial[] listToArray(List<TipusEstatDeFirmaInicial> list)  {
		if(list == null) { return null; };
		return list.toArray(new TipusEstatDeFirmaInicial[list.size()]);
	};

	public synchronized TipusEstatDeFirmaInicial create( long _tipusEstatDeFirmaInicialID_, java.lang.String _nom_, java.lang.String _descripcio_) throws I18NException {
		TipusEstatDeFirmaInicialJPA __bean =  new TipusEstatDeFirmaInicialJPA(_tipusEstatDeFirmaInicialID_,_nom_,_descripcio_);
		return create(__bean);
	}



 public void delete(long _tipusEstatDeFirmaInicialID_) {
   delete(findByPrimaryKey(_tipusEstatDeFirmaInicialID_));
 }




	public TipusEstatDeFirmaInicial findByPrimaryKey(long _tipusEstatDeFirmaInicialID_) {
	  return __em.find(TipusEstatDeFirmaInicialJPA.class, _tipusEstatDeFirmaInicialID_);  
	}
	@Override
	protected TipusEstatDeFirmaInicial getJPAInstance(TipusEstatDeFirmaInicial __bean) {
		return convertToJPA(__bean);
	}


	public static TipusEstatDeFirmaInicialJPA convertToJPA(TipusEstatDeFirmaInicial __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof TipusEstatDeFirmaInicialJPA) {
	    return (TipusEstatDeFirmaInicialJPA)__bean;
	  }
	  
	  return TipusEstatDeFirmaInicialJPA.toJPA(__bean);
	}


}