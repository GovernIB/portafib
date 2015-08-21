
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


public class AnnexFirmatJPAManager
		 extends AbstractPortaFIBJPAManager<AnnexFirmat, Long>
		 implements IAnnexFirmatManager, AnnexFirmatFields {




  private static final long serialVersionUID = 1810866039L;

	 public static final TableName<AnnexFirmat> _TABLENAME =  new TableName<AnnexFirmat>("AnnexFirmatJPA");



  static final ModificationManager<AnnexFirmat> __eventManager = new ModificationManager<AnnexFirmat>();


  @PersistenceContext
  protected EntityManager __em;
  public AnnexFirmatJPAManager() {
  }
  protected AnnexFirmatJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return AnnexFirmatJPA. class;
	}



	public ModificationManager<AnnexFirmat> getEventManager() {
	return __eventManager;
	}


	public TableName<AnnexFirmat> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public AnnexFirmat[] listToArray(List<AnnexFirmat> list)  {
		if(list == null) { return null; };
		return list.toArray(new AnnexFirmat[list.size()]);
	};

	public synchronized AnnexFirmat create( long _fitxerID_, long _annexID_, long _firmaID_) throws I18NException {
		AnnexFirmatJPA __bean =  new AnnexFirmatJPA(_fitxerID_,_annexID_,_firmaID_);
		return create(__bean);
	}



 public void delete(long _annexfirmatID_) {
   delete(findByPrimaryKey(_annexfirmatID_));
 }




	public AnnexFirmat findByPrimaryKey(long _annexfirmatID_) {
	  return __em.find(AnnexFirmatJPA.class, _annexfirmatID_);  
	}
	@Override
	protected AnnexFirmat getJPAInstance(AnnexFirmat __bean) {
		return convertToJPA(__bean);
	}


	public static AnnexFirmatJPA convertToJPA(AnnexFirmat __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof AnnexFirmatJPA) {
	    return (AnnexFirmatJPA)__bean;
	  }
	  
	  return AnnexFirmatJPA.toJPA(__bean);
	}


}