
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


public class AnnexJPAManager
		 extends AbstractPortaFIBJPAManager<Annex, Long>
		 implements IAnnexManager, AnnexFields {




  private static final long serialVersionUID = 375709862L;

	 public static final TableName<Annex> _TABLENAME =  new TableName<Annex>("AnnexJPA");



  static final ModificationManager<Annex> __eventManager = new ModificationManager<Annex>();


  @PersistenceContext
  protected EntityManager __em;
  public AnnexJPAManager() {
  }
  protected AnnexJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return AnnexJPA. class;
	}



	public ModificationManager<Annex> getEventManager() {
	return __eventManager;
	}


	public TableName<Annex> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public Annex[] listToArray(List<Annex> list)  {
		if(list == null) { return null; };
		return list.toArray(new Annex[list.size()]);
	};

	public synchronized Annex create( long _peticioDeFirmaID_, long _fitxerID_, boolean _adjuntar_, boolean _firmar_) throws I18NException {
		AnnexJPA __bean =  new AnnexJPA(_peticioDeFirmaID_,_fitxerID_,_adjuntar_,_firmar_);
		return create(__bean);
	}



 public void delete(long _annexID_) {
   delete(findByPrimaryKey(_annexID_));
 }




	public Annex findByPrimaryKey(long _annexID_) {
	  return __em.find(AnnexJPA.class, _annexID_);  
	}
	@Override
	protected Annex getJPAInstance(Annex __bean) {
		return convertToJPA(__bean);
	}


	public static AnnexJPA convertToJPA(Annex __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof AnnexJPA) {
	    return (AnnexJPA)__bean;
	  }
	  
	  return AnnexJPA.toJPA(__bean);
	}


}