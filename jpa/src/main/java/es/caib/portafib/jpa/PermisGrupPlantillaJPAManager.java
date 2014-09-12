
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


public class PermisGrupPlantillaJPAManager
		 extends AbstractPortaFIBJPAManager<PermisGrupPlantilla, Long >
		 implements IPermisGrupPlantillaManager, PermisGrupPlantillaFields {




  private static final long serialVersionUID = 2004644405L;

	 public static final TableName<PermisGrupPlantilla> _TABLENAME =  new TableName<PermisGrupPlantilla>("PermisGrupPlantillaJPA");



  static final ModificationManager<PermisGrupPlantilla> __eventManager = new ModificationManager<PermisGrupPlantilla>();


  @PersistenceContext
  protected EntityManager __em;
  public PermisGrupPlantillaJPAManager() {
  }
  protected PermisGrupPlantillaJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return PermisGrupPlantillaJPA. class;
	}



	public ModificationManager<PermisGrupPlantilla> getEventManager() {
	return __eventManager;
	}


	public TableName<PermisGrupPlantilla> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public PermisGrupPlantilla[] listToArray(List<PermisGrupPlantilla> list)  {
		if(list == null) { return null; };
		return list.toArray(new PermisGrupPlantilla[list.size()]);
	};

	public synchronized PermisGrupPlantilla create( long _grupEntitatID_, long _plantillaFluxDeFirmesID_) throws I18NException {
		PermisGrupPlantillaJPA __bean =  new PermisGrupPlantillaJPA(_grupEntitatID_,_plantillaFluxDeFirmesID_);
		return create(__bean);
	}



 public void delete(long _permisGrupPlantillaID_) {
   delete(findByPrimaryKey(_permisGrupPlantillaID_));
 }




	public PermisGrupPlantilla findByPrimaryKey(long _permisGrupPlantillaID_) {
	  return __em.find(PermisGrupPlantillaJPA.class, _permisGrupPlantillaID_);  
	}
	@Override
	protected PermisGrupPlantilla getJPAInstance(PermisGrupPlantilla __bean) {
		return convertToJPA(__bean);
	}


	public static PermisGrupPlantillaJPA convertToJPA(PermisGrupPlantilla __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof PermisGrupPlantillaJPA) {
	    return (PermisGrupPlantillaJPA)__bean;
	  }
	  
	  return PermisGrupPlantillaJPA.toJPA(__bean);
	}


}