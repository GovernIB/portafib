
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


public class BlocDeFirmesJPAManager
		 extends AbstractPortaFIBJPAManager<BlocDeFirmes, Long >
		 implements IBlocDeFirmesManager, BlocDeFirmesFields {




  private static final long serialVersionUID = 2145574621L;

	 public static final TableName<BlocDeFirmes> _TABLENAME =  new TableName<BlocDeFirmes>("BlocDeFirmesJPA");



  static final ModificationManager<BlocDeFirmes> __eventManager = new ModificationManager<BlocDeFirmes>();


  @PersistenceContext
  protected EntityManager __em;
  public BlocDeFirmesJPAManager() {
  }
  protected BlocDeFirmesJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return BlocDeFirmesJPA. class;
	}



	public ModificationManager<BlocDeFirmes> getEventManager() {
	return __eventManager;
	}


	public TableName<BlocDeFirmes> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public BlocDeFirmes[] listToArray(List<BlocDeFirmes> list)  {
		if(list == null) { return null; };
		return list.toArray(new BlocDeFirmes[list.size()]);
	};

	public synchronized BlocDeFirmes create( int _ordre_, java.sql.Timestamp _dataFinalitzacio_, long _fluxDeFirmesID_, int _minimDeFirmes_) throws I18NException {
		BlocDeFirmesJPA __bean =  new BlocDeFirmesJPA(_ordre_,_dataFinalitzacio_,_fluxDeFirmesID_,_minimDeFirmes_);
		return create(__bean);
	}



 public void delete(long _blocDeFirmesID_) {
   delete(findByPrimaryKey(_blocDeFirmesID_));
 }




	public BlocDeFirmes findByPrimaryKey(long _blocDeFirmesID_) {
	  return __em.find(BlocDeFirmesJPA.class, _blocDeFirmesID_);  
	}
	@Override
	protected BlocDeFirmes getJPAInstance(BlocDeFirmes __bean) {
		return convertToJPA(__bean);
	}


	public static BlocDeFirmesJPA convertToJPA(BlocDeFirmes __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof BlocDeFirmesJPA) {
	    return (BlocDeFirmesJPA)__bean;
	  }
	  
	  return BlocDeFirmesJPA.toJPA(__bean);
	}


}