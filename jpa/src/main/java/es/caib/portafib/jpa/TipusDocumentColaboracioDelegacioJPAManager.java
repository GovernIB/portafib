
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


public class TipusDocumentColaboracioDelegacioJPAManager
		 extends AbstractPortaFIBJPAManager<TipusDocumentColaboracioDelegacio, Long>
		 implements ITipusDocumentColaboracioDelegacioManager, TipusDocumentColaboracioDelegacioFields {




  private static final long serialVersionUID = 319729007L;

	 public static final TableName<TipusDocumentColaboracioDelegacio> _TABLENAME =  new TableName<TipusDocumentColaboracioDelegacio>("TipusDocumentColaboracioDelegacioJPA");



  static final ModificationManager<TipusDocumentColaboracioDelegacio> __eventManager = new ModificationManager<TipusDocumentColaboracioDelegacio>();


  @PersistenceContext
  protected EntityManager __em;
  public TipusDocumentColaboracioDelegacioJPAManager() {
  }
  protected TipusDocumentColaboracioDelegacioJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return TipusDocumentColaboracioDelegacioJPA. class;
	}



	public ModificationManager<TipusDocumentColaboracioDelegacio> getEventManager() {
	return __eventManager;
	}


	public TableName<TipusDocumentColaboracioDelegacio> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public TipusDocumentColaboracioDelegacio[] listToArray(List<TipusDocumentColaboracioDelegacio> list)  {
		if(list == null) { return null; };
		return list.toArray(new TipusDocumentColaboracioDelegacio[list.size()]);
	};

	public TipusDocumentColaboracioDelegacio create( long _colaboracioDelegacioID_, long _tipusDocumentID_) throws I18NException {
		TipusDocumentColaboracioDelegacioJPA __bean =  new TipusDocumentColaboracioDelegacioJPA(_colaboracioDelegacioID_,_tipusDocumentID_);
		return create(__bean);
	}



 public void delete(long _id_) {
   delete(findByPrimaryKey(_id_));
 }




	public TipusDocumentColaboracioDelegacio findByPrimaryKey(long _id_) {
	  return __em.find(TipusDocumentColaboracioDelegacioJPA.class, _id_);  
	}
	@Override
	protected TipusDocumentColaboracioDelegacio getJPAInstance(TipusDocumentColaboracioDelegacio __bean) {
		return convertToJPA(__bean);
	}


	public static TipusDocumentColaboracioDelegacioJPA convertToJPA(TipusDocumentColaboracioDelegacio __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof TipusDocumentColaboracioDelegacioJPA) {
	    return (TipusDocumentColaboracioDelegacioJPA)__bean;
	  }
	  
	  return TipusDocumentColaboracioDelegacioJPA.toJPA(__bean);
	}


}