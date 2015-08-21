
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


public class EntitatJPAManager
		 extends AbstractPortaFIBJPAManager<Entitat, String>
		 implements IEntitatManager, EntitatFields {




  private static final long serialVersionUID = 1660227099L;

	 public static final TableName<Entitat> _TABLENAME =  new TableName<Entitat>("EntitatJPA");



  static final ModificationManager<Entitat> __eventManager = new ModificationManager<Entitat>();


  @PersistenceContext
  protected EntityManager __em;
  public EntitatJPAManager() {
  }
  protected EntitatJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return EntitatJPA. class;
	}



	public ModificationManager<Entitat> getEventManager() {
	return __eventManager;
	}


	public TableName<Entitat> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public Entitat[] listToArray(List<Entitat> list)  {
		if(list == null) { return null; };
		return list.toArray(new Entitat[list.size()]);
	};

	public synchronized Entitat create( java.lang.String _entitatID_, java.lang.String _nom_, java.lang.String _descripcio_, boolean _activa_, java.lang.String _web_, java.lang.Long _faviconID_, java.lang.Long _logoWebID_, java.lang.Long _logoWebPeuID_, java.lang.Long _logoSegellID_, java.lang.String _adrezaHtml_, java.lang.String _filtreCertificats_, java.lang.Long _pdfAutoritzacioDelegacioID_, java.lang.String _suportTelefon_, java.lang.String _suportWeb_, java.lang.String _suportEmail_, java.lang.String _usuariAplicacioID_, java.lang.Long _maxUploadSize_, java.lang.Long _maxSizeFitxerAdaptat_, java.lang.Integer _maxFilesToSignAtSameTime_, java.lang.String _policyIdentifier_, java.lang.String _policyIdentifierHash_, java.lang.String _policyIdentifierHashAlgorithm_, java.lang.String _policyUrlDocument_) throws I18NException {
		EntitatJPA __bean =  new EntitatJPA(_entitatID_,_nom_,_descripcio_,_activa_,_web_,_faviconID_,_logoWebID_,_logoWebPeuID_,_logoSegellID_,_adrezaHtml_,_filtreCertificats_,_pdfAutoritzacioDelegacioID_,_suportTelefon_,_suportWeb_,_suportEmail_,_usuariAplicacioID_,_maxUploadSize_,_maxSizeFitxerAdaptat_,_maxFilesToSignAtSameTime_,_policyIdentifier_,_policyIdentifierHash_,_policyIdentifierHashAlgorithm_,_policyUrlDocument_);
		return create(__bean);
	}



 public void delete(java.lang.String _entitatID_) {
   delete(findByPrimaryKey(_entitatID_));
 }




	public Entitat findByPrimaryKey(java.lang.String _entitatID_) {
	  return __em.find(EntitatJPA.class, _entitatID_);  
	}
	@Override
	protected Entitat getJPAInstance(Entitat __bean) {
		return convertToJPA(__bean);
	}


	public static EntitatJPA convertToJPA(Entitat __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof EntitatJPA) {
	    return (EntitatJPA)__bean;
	  }
	  
	  return EntitatJPA.toJPA(__bean);
	}


}