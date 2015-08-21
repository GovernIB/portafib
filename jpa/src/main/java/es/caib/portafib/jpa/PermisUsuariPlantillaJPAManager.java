
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


public class PermisUsuariPlantillaJPAManager
		 extends AbstractPortaFIBJPAManager<PermisUsuariPlantilla, Long>
		 implements IPermisUsuariPlantillaManager, PermisUsuariPlantillaFields {




  private static final long serialVersionUID = -439873478L;

	 public static final TableName<PermisUsuariPlantilla> _TABLENAME =  new TableName<PermisUsuariPlantilla>("PermisUsuariPlantillaJPA");



  static final ModificationManager<PermisUsuariPlantilla> __eventManager = new ModificationManager<PermisUsuariPlantilla>();


  @PersistenceContext
  protected EntityManager __em;
  public PermisUsuariPlantillaJPAManager() {
  }
  protected PermisUsuariPlantillaJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return PermisUsuariPlantillaJPA. class;
	}



	public ModificationManager<PermisUsuariPlantilla> getEventManager() {
	return __eventManager;
	}


	public TableName<PermisUsuariPlantilla> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public PermisUsuariPlantilla[] listToArray(List<PermisUsuariPlantilla> list)  {
		if(list == null) { return null; };
		return list.toArray(new PermisUsuariPlantilla[list.size()]);
	};

	public synchronized PermisUsuariPlantilla create( java.lang.String _usuariEntitatID_, long _plantillaFluxDeFirmesID_) throws I18NException {
		PermisUsuariPlantillaJPA __bean =  new PermisUsuariPlantillaJPA(_usuariEntitatID_,_plantillaFluxDeFirmesID_);
		return create(__bean);
	}



 public void delete(long _permisUsuariPlantillaID_) {
   delete(findByPrimaryKey(_permisUsuariPlantillaID_));
 }




	public PermisUsuariPlantilla findByPrimaryKey(long _permisUsuariPlantillaID_) {
	  return __em.find(PermisUsuariPlantillaJPA.class, _permisUsuariPlantillaID_);  
	}
	@Override
	protected PermisUsuariPlantilla getJPAInstance(PermisUsuariPlantilla __bean) {
		return convertToJPA(__bean);
	}


	public static PermisUsuariPlantillaJPA convertToJPA(PermisUsuariPlantilla __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof PermisUsuariPlantillaJPA) {
	    return (PermisUsuariPlantillaJPA)__bean;
	  }
	  
	  return PermisUsuariPlantillaJPA.toJPA(__bean);
	}


}