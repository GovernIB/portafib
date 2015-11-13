
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


public class ModulDeFirmaPerTipusDeDocumentJPAManager
		 extends AbstractPortaFIBJPAManager<ModulDeFirmaPerTipusDeDocument, Long>
		 implements IModulDeFirmaPerTipusDeDocumentManager, ModulDeFirmaPerTipusDeDocumentFields {




  private static final long serialVersionUID = 2091944947L;

	 public static final TableName<ModulDeFirmaPerTipusDeDocument> _TABLENAME =  new TableName<ModulDeFirmaPerTipusDeDocument>("ModulDeFirmaPerTipusDeDocumentJPA");



  static final ModificationManager<ModulDeFirmaPerTipusDeDocument> __eventManager = new ModificationManager<ModulDeFirmaPerTipusDeDocument>();


  @PersistenceContext
  protected EntityManager __em;
  public ModulDeFirmaPerTipusDeDocumentJPAManager() {
  }
  protected ModulDeFirmaPerTipusDeDocumentJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return ModulDeFirmaPerTipusDeDocumentJPA. class;
	}



	public ModificationManager<ModulDeFirmaPerTipusDeDocument> getEventManager() {
	return __eventManager;
	}


	public TableName<ModulDeFirmaPerTipusDeDocument> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public ModulDeFirmaPerTipusDeDocument[] listToArray(List<ModulDeFirmaPerTipusDeDocument> list)  {
		if(list == null) { return null; };
		return list.toArray(new ModulDeFirmaPerTipusDeDocument[list.size()]);
	};

	public synchronized ModulDeFirmaPerTipusDeDocument create( long _tipusDocumentID_, long _modulDeFirmaID_, java.lang.String _nom_) throws I18NException {
		ModulDeFirmaPerTipusDeDocumentJPA __bean =  new ModulDeFirmaPerTipusDeDocumentJPA(_tipusDocumentID_,_modulDeFirmaID_,_nom_);
		return create(__bean);
	}



 public void delete(long _ID_) {
   delete(findByPrimaryKey(_ID_));
 }




	public ModulDeFirmaPerTipusDeDocument findByPrimaryKey(long _ID_) {
	  return __em.find(ModulDeFirmaPerTipusDeDocumentJPA.class, _ID_);  
	}
	@Override
	protected ModulDeFirmaPerTipusDeDocument getJPAInstance(ModulDeFirmaPerTipusDeDocument __bean) {
		return convertToJPA(__bean);
	}


	public static ModulDeFirmaPerTipusDeDocumentJPA convertToJPA(ModulDeFirmaPerTipusDeDocument __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof ModulDeFirmaPerTipusDeDocumentJPA) {
	    return (ModulDeFirmaPerTipusDeDocumentJPA)__bean;
	  }
	  
	  return ModulDeFirmaPerTipusDeDocumentJPA.toJPA(__bean);
	}


}