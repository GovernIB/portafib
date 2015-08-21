
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


public class PosicioTaulaFirmesJPAManager
		 extends AbstractPortaFIBJPAManager<PosicioTaulaFirmes, Integer>
		 implements IPosicioTaulaFirmesManager, PosicioTaulaFirmesFields {




  private static final long serialVersionUID = 1093485959L;

	 public static final TableName<PosicioTaulaFirmes> _TABLENAME =  new TableName<PosicioTaulaFirmes>("PosicioTaulaFirmesJPA");



  static final ModificationManager<PosicioTaulaFirmes> __eventManager = new ModificationManager<PosicioTaulaFirmes>();


  @PersistenceContext
  protected EntityManager __em;
  public PosicioTaulaFirmesJPAManager() {
  }
  protected PosicioTaulaFirmesJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return PosicioTaulaFirmesJPA. class;
	}



	public ModificationManager<PosicioTaulaFirmes> getEventManager() {
	return __eventManager;
	}


	public TableName<PosicioTaulaFirmes> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public PosicioTaulaFirmes[] listToArray(List<PosicioTaulaFirmes> list)  {
		if(list == null) { return null; };
		return list.toArray(new PosicioTaulaFirmes[list.size()]);
	};

	public synchronized PosicioTaulaFirmes create( int _posicioTaulaFirmesID_, java.lang.String _nom_, java.lang.String _descripcio_, boolean _suportada_) throws I18NException {
		PosicioTaulaFirmesJPA __bean =  new PosicioTaulaFirmesJPA(_posicioTaulaFirmesID_,_nom_,_descripcio_,_suportada_);
		return create(__bean);
	}



 public void delete(int _posicioTaulaFirmesID_) {
   delete(findByPrimaryKey(_posicioTaulaFirmesID_));
 }




	public PosicioTaulaFirmes findByPrimaryKey(int _posicioTaulaFirmesID_) {
	  return __em.find(PosicioTaulaFirmesJPA.class, _posicioTaulaFirmesID_);  
	}
	@Override
	protected PosicioTaulaFirmes getJPAInstance(PosicioTaulaFirmes __bean) {
		return convertToJPA(__bean);
	}


	public static PosicioTaulaFirmesJPA convertToJPA(PosicioTaulaFirmes __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof PosicioTaulaFirmesJPA) {
	    return (PosicioTaulaFirmesJPA)__bean;
	  }
	  
	  return PosicioTaulaFirmesJPA.toJPA(__bean);
	}


}