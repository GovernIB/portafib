
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


public class EstatDeFirmaJPAManager
		 extends AbstractPortaFIBJPAManager<EstatDeFirma, Long>
		 implements IEstatDeFirmaManager, EstatDeFirmaFields {




  private static final long serialVersionUID = 1355936699L;

	 public static final TableName<EstatDeFirma> _TABLENAME =  new TableName<EstatDeFirma>("EstatDeFirmaJPA");



  static final ModificationManager<EstatDeFirma> __eventManager = new ModificationManager<EstatDeFirma>();


  @PersistenceContext
  protected EntityManager __em;
  public EstatDeFirmaJPAManager() {
  }
  protected EstatDeFirmaJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return EstatDeFirmaJPA. class;
	}



	public ModificationManager<EstatDeFirma> getEventManager() {
	return __eventManager;
	}


	public TableName<EstatDeFirma> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public EstatDeFirma[] listToArray(List<EstatDeFirma> list)  {
		if(list == null) { return null; };
		return list.toArray(new EstatDeFirma[list.size()]);
	};

	public synchronized EstatDeFirma create( long _firmaID_, java.lang.String _usuariEntitatID_, java.sql.Timestamp _dataInici_, java.sql.Timestamp _dataFi_, long _tipusEstatDeFirmaInicialID_, java.lang.Long _tipusEstatDeFirmaFinalID_, java.lang.Long _colaboracioDelegacioID_, java.lang.String _descripcio_) throws I18NException {
		EstatDeFirmaJPA __bean =  new EstatDeFirmaJPA(_firmaID_,_usuariEntitatID_,_dataInici_,_dataFi_,_tipusEstatDeFirmaInicialID_,_tipusEstatDeFirmaFinalID_,_colaboracioDelegacioID_,_descripcio_);
		return create(__bean);
	}



 public void delete(long _estatDeFirmaID_) {
   delete(findByPrimaryKey(_estatDeFirmaID_));
 }




	public EstatDeFirma findByPrimaryKey(long _estatDeFirmaID_) {
	  return __em.find(EstatDeFirmaJPA.class, _estatDeFirmaID_);  
	}
	@Override
	protected EstatDeFirma getJPAInstance(EstatDeFirma __bean) {
		return convertToJPA(__bean);
	}


	public static EstatDeFirmaJPA convertToJPA(EstatDeFirma __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof EstatDeFirmaJPA) {
	    return (EstatDeFirmaJPA)__bean;
	  }
	  
	  return EstatDeFirmaJPA.toJPA(__bean);
	}


}