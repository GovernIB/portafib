
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


public class FluxDeFirmesJPAManager
		 extends AbstractPortaFIBJPAManager<FluxDeFirmes, Long>
		 implements IFluxDeFirmesManager, FluxDeFirmesFields {




  private static final long serialVersionUID = -423701400L;

	 public static final TableName<FluxDeFirmes> _TABLENAME =  new TableName<FluxDeFirmes>("FluxDeFirmesJPA");



  static final ModificationManager<FluxDeFirmes> __eventManager = new ModificationManager<FluxDeFirmes>();


  @PersistenceContext
  protected EntityManager __em;
  public FluxDeFirmesJPAManager() {
  }
  protected FluxDeFirmesJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return FluxDeFirmesJPA. class;
	}



	public ModificationManager<FluxDeFirmes> getEventManager() {
	return __eventManager;
	}


	public TableName<FluxDeFirmes> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public FluxDeFirmes[] listToArray(List<FluxDeFirmes> list)  {
		if(list == null) { return null; };
		return list.toArray(new FluxDeFirmes[list.size()]);
	};

	public synchronized FluxDeFirmes create( java.lang.String _nom_) throws I18NException {
		FluxDeFirmesJPA __bean =  new FluxDeFirmesJPA(_nom_);
		return create(__bean);
	}



 public void delete(long _fluxDeFirmesID_) {
   delete(findByPrimaryKey(_fluxDeFirmesID_));
 }




	public FluxDeFirmes findByPrimaryKey(long _fluxDeFirmesID_) {
	  return __em.find(FluxDeFirmesJPA.class, _fluxDeFirmesID_);  
	}
	@Override
	protected FluxDeFirmes getJPAInstance(FluxDeFirmes __bean) {
		return convertToJPA(__bean);
	}


	public static FluxDeFirmesJPA convertToJPA(FluxDeFirmes __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof FluxDeFirmesJPA) {
	    return (FluxDeFirmesJPA)__bean;
	  }
	  
	  return FluxDeFirmesJPA.toJPA(__bean);
	}


}