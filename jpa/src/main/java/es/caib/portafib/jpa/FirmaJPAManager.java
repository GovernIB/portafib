
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


public class FirmaJPAManager
		 extends AbstractPortaFIBJPAManager<Firma, Long>
		 implements IFirmaManager, FirmaFields {




  private static final long serialVersionUID = 90585461L;

	 public static final TableName<Firma> _TABLENAME =  new TableName<Firma>("FirmaJPA");



  static final ModificationManager<Firma> __eventManager = new ModificationManager<Firma>();


  @PersistenceContext
  protected EntityManager __em;
  public FirmaJPAManager() {
  }
  protected FirmaJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return FirmaJPA. class;
	}



	public ModificationManager<Firma> getEventManager() {
	return __eventManager;
	}


	public TableName<Firma> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public Firma[] listToArray(List<Firma> list)  {
		if(list == null) { return null; };
		return list.toArray(new Firma[list.size()]);
	};

	public synchronized Firma create( java.lang.String _destinatariID_, long _blocDeFirmaID_, boolean _obligatori_, java.lang.Long _fitxerFirmatID_, java.lang.Integer _numFirmaDocument_, int _caixaPagina_, java.lang.Integer _caixaX_, java.lang.Integer _caixaY_, java.lang.Integer _caixaAmple_, java.lang.Integer _caixaAlt_, java.math.BigInteger _numeroSerieCertificat_, java.lang.String _emissorCertificat_, java.lang.String _nomCertificat_, java.lang.Long _tipusEstatDeFirmaFinalID_, boolean _mostrarRubrica_, java.lang.String _motiu_, int _minimDeRevisors_, java.lang.Boolean _checkAdministrationIdOfSigner_, java.lang.Boolean _checkDocumentModifications_, java.lang.Boolean _checkValidationSignature_, java.lang.String _perfilDeFirma_) throws I18NException {
		FirmaJPA __bean =  new FirmaJPA(_destinatariID_,_blocDeFirmaID_,_obligatori_,_fitxerFirmatID_,_numFirmaDocument_,_caixaPagina_,_caixaX_,_caixaY_,_caixaAmple_,_caixaAlt_,_numeroSerieCertificat_,_emissorCertificat_,_nomCertificat_,_tipusEstatDeFirmaFinalID_,_mostrarRubrica_,_motiu_,_minimDeRevisors_,_checkAdministrationIdOfSigner_,_checkDocumentModifications_,_checkValidationSignature_,_perfilDeFirma_);
		return create(__bean);
	}



 public void delete(long _firmaID_) {
   delete(findByPrimaryKey(_firmaID_));
 }




	public Firma findByPrimaryKey(long _firmaID_) {
	  return __em.find(FirmaJPA.class, _firmaID_);  
	}
	@Override
	protected Firma getJPAInstance(Firma __bean) {
		return convertToJPA(__bean);
	}


	public static FirmaJPA convertToJPA(Firma __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof FirmaJPA) {
	    return (FirmaJPA)__bean;
	  }
	  
	  return FirmaJPA.toJPA(__bean);
	}


}