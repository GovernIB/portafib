
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


public class CodiBarresJPAManager
		 extends AbstractPortaFIBJPAManager<CodiBarres, String >
		 implements ICodiBarresManager, CodiBarresFields {




  private static final long serialVersionUID = 2103246608L;

	 public static final TableName<CodiBarres> _TABLENAME =  new TableName<CodiBarres>("CodiBarresJPA");



  static final ModificationManager<CodiBarres> __eventManager = new ModificationManager<CodiBarres>();


  @PersistenceContext
  protected EntityManager __em;
  public CodiBarresJPAManager() {
  }
  protected CodiBarresJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return CodiBarresJPA. class;
	}



	public ModificationManager<CodiBarres> getEventManager() {
	return __eventManager;
	}


	public TableName<CodiBarres> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public CodiBarres[] listToArray(List<CodiBarres> list)  {
		if(list == null) { return null; };
		return list.toArray(new CodiBarres[list.size()]);
	};

	public synchronized CodiBarres create( java.lang.String _codiBarresID_, java.lang.String _nom_, java.lang.String _descripcio_) throws I18NException {
		CodiBarresJPA __bean =  new CodiBarresJPA(_codiBarresID_,_nom_,_descripcio_);
		return create(__bean);
	}



 public void delete(java.lang.String _codiBarresID_) {
   delete(findByPrimaryKey(_codiBarresID_));
 }




	public CodiBarres findByPrimaryKey(java.lang.String _codiBarresID_) {
	  return __em.find(CodiBarresJPA.class, _codiBarresID_);  
	}
	@Override
	protected CodiBarres getJPAInstance(CodiBarres __bean) {
		return convertToJPA(__bean);
	}


	public static CodiBarresJPA convertToJPA(CodiBarres __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof CodiBarresJPA) {
	    return (CodiBarresJPA)__bean;
	  }
	  
	  return CodiBarresJPA.toJPA(__bean);
	}


}