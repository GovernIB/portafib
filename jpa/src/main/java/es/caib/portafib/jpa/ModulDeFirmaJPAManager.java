
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


public class ModulDeFirmaJPAManager
		 extends AbstractPortaFIBJPAManager<ModulDeFirma, Long>
		 implements IModulDeFirmaManager, ModulDeFirmaFields {




  private static final long serialVersionUID = -985719077L;

	 public static final TableName<ModulDeFirma> _TABLENAME =  new TableName<ModulDeFirma>("ModulDeFirmaJPA");



  static final ModificationManager<ModulDeFirma> __eventManager = new ModificationManager<ModulDeFirma>();


  @PersistenceContext
  protected EntityManager __em;
  public ModulDeFirmaJPAManager() {
  }
  protected ModulDeFirmaJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return ModulDeFirmaJPA. class;
	}



	public ModificationManager<ModulDeFirma> getEventManager() {
	return __eventManager;
	}


	public TableName<ModulDeFirma> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public ModulDeFirma[] listToArray(List<ModulDeFirma> list)  {
		if(list == null) { return null; };
		return list.toArray(new ModulDeFirma[list.size()]);
	};

	public synchronized ModulDeFirma create( long _nomID_, long _descripcioCurtaID_, java.lang.String _classe_, java.lang.String _propertiesAdmin_, java.lang.String _propertiesEntitat_, java.lang.String _entitatID_, boolean _actiu_) throws I18NException {
		ModulDeFirmaJPA __bean =  new ModulDeFirmaJPA(_nomID_,_descripcioCurtaID_,_classe_,_propertiesAdmin_,_propertiesEntitat_,_entitatID_,_actiu_);
		return create(__bean);
	}



 public void delete(long _modulDeFirmaID_) {
   delete(findByPrimaryKey(_modulDeFirmaID_));
 }




	public ModulDeFirma findByPrimaryKey(long _modulDeFirmaID_) {
	  return __em.find(ModulDeFirmaJPA.class, _modulDeFirmaID_);  
	}
	@Override
	protected ModulDeFirma getJPAInstance(ModulDeFirma __bean) {
		return convertToJPA(__bean);
	}


	public static ModulDeFirmaJPA convertToJPA(ModulDeFirma __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof ModulDeFirmaJPA) {
	    return (ModulDeFirmaJPA)__bean;
	  }
	  
	  return ModulDeFirmaJPA.toJPA(__bean);
	}

  @Override
  public ModulDeFirma create(ModulDeFirma transientInstance) throws I18NException {
    if (transientInstance != null) {
      if (transientInstance.getNomID() == 0) {
        if (transientInstance instanceof ModulDeFirmaJPA) {
          ModulDeFirmaJPA _jpa = (ModulDeFirmaJPA)transientInstance;
          TraduccioJPA _trad = _jpa.getNom();
           if (_trad != null) {
            if (_trad.getTraduccioID() == 0) {
              getEntityManager().persist(_trad);
            } 
            transientInstance.setNomID(_trad.getTraduccioID());
          }
        }
      }
      if (transientInstance.getDescripcioCurtaID() == 0) {
        if (transientInstance instanceof ModulDeFirmaJPA) {
          ModulDeFirmaJPA _jpa = (ModulDeFirmaJPA)transientInstance;
          TraduccioJPA _trad = _jpa.getDescripcioCurta();
           if (_trad != null) {
            if (_trad.getTraduccioID() == 0) {
              getEntityManager().persist(_trad);
            } 
            transientInstance.setDescripcioCurtaID(_trad.getTraduccioID());
          }
        }
      }
    }
    return super.create(transientInstance);
  }


}