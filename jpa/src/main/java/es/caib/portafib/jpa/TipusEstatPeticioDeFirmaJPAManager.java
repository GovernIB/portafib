
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


public class TipusEstatPeticioDeFirmaJPAManager
		 extends AbstractPortaFIBJPAManager<TipusEstatPeticioDeFirma, Integer>
		 implements ITipusEstatPeticioDeFirmaManager, TipusEstatPeticioDeFirmaFields {




  private static final long serialVersionUID = -1081275723L;

	 public static final TableName<TipusEstatPeticioDeFirma> _TABLENAME =  new TableName<TipusEstatPeticioDeFirma>("TipusEstatPeticioDeFirmaJPA");



  static final ModificationManager<TipusEstatPeticioDeFirma> __eventManager = new ModificationManager<TipusEstatPeticioDeFirma>();


  @PersistenceContext
  protected EntityManager __em;
  public TipusEstatPeticioDeFirmaJPAManager() {
  }
  protected TipusEstatPeticioDeFirmaJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return TipusEstatPeticioDeFirmaJPA. class;
	}



	public ModificationManager<TipusEstatPeticioDeFirma> getEventManager() {
	return __eventManager;
	}


	public TableName<TipusEstatPeticioDeFirma> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public TipusEstatPeticioDeFirma[] listToArray(List<TipusEstatPeticioDeFirma> list)  {
		if(list == null) { return null; };
		return list.toArray(new TipusEstatPeticioDeFirma[list.size()]);
	};

	public synchronized TipusEstatPeticioDeFirma create( int _tipusEstatPeticioDeFirmaID_, java.lang.String _nom_, java.lang.String _descripcio_) throws I18NException {
		TipusEstatPeticioDeFirmaJPA __bean =  new TipusEstatPeticioDeFirmaJPA(_tipusEstatPeticioDeFirmaID_,_nom_,_descripcio_);
		return create(__bean);
	}



 public void delete(int _tipusEstatPeticioDeFirmaID_) {
   delete(findByPrimaryKey(_tipusEstatPeticioDeFirmaID_));
 }




	public TipusEstatPeticioDeFirma findByPrimaryKey(int _tipusEstatPeticioDeFirmaID_) {
	  return __em.find(TipusEstatPeticioDeFirmaJPA.class, _tipusEstatPeticioDeFirmaID_);  
	}
	@Override
	protected TipusEstatPeticioDeFirma getJPAInstance(TipusEstatPeticioDeFirma __bean) {
		return convertToJPA(__bean);
	}


	public static TipusEstatPeticioDeFirmaJPA convertToJPA(TipusEstatPeticioDeFirma __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof TipusEstatPeticioDeFirmaJPA) {
	    return (TipusEstatPeticioDeFirmaJPA)__bean;
	  }
	  
	  return TipusEstatPeticioDeFirmaJPA.toJPA(__bean);
	}


}