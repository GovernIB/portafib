
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


public class ColaboracioDelegacioJPAManager
		 extends AbstractPortaFIBJPAManager<ColaboracioDelegacio, Long >
		 implements IColaboracioDelegacioManager, ColaboracioDelegacioFields {




  private static final long serialVersionUID = 785381339L;

	 public static final TableName<ColaboracioDelegacio> _TABLENAME =  new TableName<ColaboracioDelegacio>("ColaboracioDelegacioJPA");



  static final ModificationManager<ColaboracioDelegacio> __eventManager = new ModificationManager<ColaboracioDelegacio>();


  @PersistenceContext
  protected EntityManager __em;
  public ColaboracioDelegacioJPAManager() {
  }
  protected ColaboracioDelegacioJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return ColaboracioDelegacioJPA. class;
	}



	public ModificationManager<ColaboracioDelegacio> getEventManager() {
	return __eventManager;
	}


	public TableName<ColaboracioDelegacio> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public ColaboracioDelegacio[] listToArray(List<ColaboracioDelegacio> list)  {
		if(list == null) { return null; };
		return list.toArray(new ColaboracioDelegacio[list.size()]);
	};

	public synchronized ColaboracioDelegacio create( java.lang.String _destinatariID_, java.lang.String _colaboradorDelegatID_, boolean _esDelegat_, java.lang.String _motiu_, java.lang.String _descripcio_, java.sql.Timestamp _dataInici_, java.sql.Timestamp _dataFi_, boolean _activa_, boolean _revisor_, java.lang.String _motiuDeshabilitada_, java.lang.Long _fitxerAutoritzacioID_) throws I18NException {
		ColaboracioDelegacioJPA __bean =  new ColaboracioDelegacioJPA(_destinatariID_,_colaboradorDelegatID_,_esDelegat_,_motiu_,_descripcio_,_dataInici_,_dataFi_,_activa_,_revisor_,_motiuDeshabilitada_,_fitxerAutoritzacioID_);
		return create(__bean);
	}



 public void delete(long _colaboracioDelegacioID_) {
   delete(findByPrimaryKey(_colaboracioDelegacioID_));
 }




	public ColaboracioDelegacio findByPrimaryKey(long _colaboracioDelegacioID_) {
	  return __em.find(ColaboracioDelegacioJPA.class, _colaboracioDelegacioID_);  
	}
	@Override
	protected ColaboracioDelegacio getJPAInstance(ColaboracioDelegacio __bean) {
		return convertToJPA(__bean);
	}


	public static ColaboracioDelegacioJPA convertToJPA(ColaboracioDelegacio __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof ColaboracioDelegacioJPA) {
	    return (ColaboracioDelegacioJPA)__bean;
	  }
	  
	  return ColaboracioDelegacioJPA.toJPA(__bean);
	}


}