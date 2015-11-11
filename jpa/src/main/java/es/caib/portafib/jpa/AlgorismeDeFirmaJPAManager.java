
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


public class AlgorismeDeFirmaJPAManager
		 extends AbstractPortaFIBJPAManager<AlgorismeDeFirma, Integer>
		 implements IAlgorismeDeFirmaManager, AlgorismeDeFirmaFields {




  private static final long serialVersionUID = 2094608307L;

	 public static final TableName<AlgorismeDeFirma> _TABLENAME =  new TableName<AlgorismeDeFirma>("AlgorismeDeFirmaJPA");



  static final ModificationManager<AlgorismeDeFirma> __eventManager = new ModificationManager<AlgorismeDeFirma>();


  @PersistenceContext
  protected EntityManager __em;
  public AlgorismeDeFirmaJPAManager() {
  }
  protected AlgorismeDeFirmaJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return AlgorismeDeFirmaJPA. class;
	}



	public ModificationManager<AlgorismeDeFirma> getEventManager() {
	return __eventManager;
	}


	public TableName<AlgorismeDeFirma> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public AlgorismeDeFirma[] listToArray(List<AlgorismeDeFirma> list)  {
		if(list == null) { return null; };
		return list.toArray(new AlgorismeDeFirma[list.size()]);
	};

	public synchronized AlgorismeDeFirma create( int _algorismeDeFirmaID_, java.lang.String _nom_, java.lang.String _descripcio_, boolean _suportat_) throws I18NException {
		AlgorismeDeFirmaJPA __bean =  new AlgorismeDeFirmaJPA(_algorismeDeFirmaID_,_nom_,_descripcio_,_suportat_);
		return create(__bean);
	}



 public void delete(int _algorismeDeFirmaID_) {
   delete(findByPrimaryKey(_algorismeDeFirmaID_));
 }




	public AlgorismeDeFirma findByPrimaryKey(int _algorismeDeFirmaID_) {
	  return __em.find(AlgorismeDeFirmaJPA.class, _algorismeDeFirmaID_);  
	}
	@Override
	protected AlgorismeDeFirma getJPAInstance(AlgorismeDeFirma __bean) {
		return convertToJPA(__bean);
	}


	public static AlgorismeDeFirmaJPA convertToJPA(AlgorismeDeFirma __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof AlgorismeDeFirmaJPA) {
	    return (AlgorismeDeFirmaJPA)__bean;
	  }
	  
	  return AlgorismeDeFirmaJPA.toJPA(__bean);
	}


}