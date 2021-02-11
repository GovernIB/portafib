
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


public class PlantillaFluxDeFirmesJPAManager
		 extends AbstractPortaFIBJPAManager<PlantillaFluxDeFirmes, Long>
		 implements IPlantillaFluxDeFirmesManager, PlantillaFluxDeFirmesFields {




  private static final long serialVersionUID = -971042101L;

	 public static final TableName<PlantillaFluxDeFirmes> _TABLENAME =  new TableName<PlantillaFluxDeFirmes>("PlantillaFluxDeFirmesJPA");



  static final ModificationManager<PlantillaFluxDeFirmes> __eventManager = new ModificationManager<PlantillaFluxDeFirmes>();


  @PersistenceContext
  protected EntityManager __em;
  public PlantillaFluxDeFirmesJPAManager() {
  }
  protected PlantillaFluxDeFirmesJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return PlantillaFluxDeFirmesJPA. class;
	}



	public ModificationManager<PlantillaFluxDeFirmes> getEventManager() {
	return __eventManager;
	}


	public TableName<PlantillaFluxDeFirmes> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public PlantillaFluxDeFirmes[] listToArray(List<PlantillaFluxDeFirmes> list)  {
		if(list == null) { return null; };
		return list.toArray(new PlantillaFluxDeFirmes[list.size()]);
	};

	public PlantillaFluxDeFirmes create( long _fluxDeFirmesID_, java.lang.String _descripcio_, java.lang.String _usuariEntitatID_, java.lang.String _usuariAplicacioID_, java.lang.Boolean _compartir_) throws I18NException {
		PlantillaFluxDeFirmesJPA __bean =  new PlantillaFluxDeFirmesJPA(_fluxDeFirmesID_,_descripcio_,_usuariEntitatID_,_usuariAplicacioID_,_compartir_);
		return create(__bean);
	}



 public void delete(long _fluxDeFirmesID_) {
   delete(findByPrimaryKey(_fluxDeFirmesID_));
 }




	public PlantillaFluxDeFirmes findByPrimaryKey(long _fluxDeFirmesID_) {
	  return __em.find(PlantillaFluxDeFirmesJPA.class, _fluxDeFirmesID_);  
	}
	@Override
	protected PlantillaFluxDeFirmes getJPAInstance(PlantillaFluxDeFirmes __bean) {
		return convertToJPA(__bean);
	}


	public static PlantillaFluxDeFirmesJPA convertToJPA(PlantillaFluxDeFirmes __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof PlantillaFluxDeFirmesJPA) {
	    return (PlantillaFluxDeFirmesJPA)__bean;
	  }
	  
	  return PlantillaFluxDeFirmesJPA.toJPA(__bean);
	}


}