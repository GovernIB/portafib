
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


public class PeticioDeFirmaJPAManager
		 extends AbstractPortaFIBJPAManager<PeticioDeFirma, Long>
		 implements IPeticioDeFirmaManager, PeticioDeFirmaFields {




  private static final long serialVersionUID = -1070718123L;

	 public static final TableName<PeticioDeFirma> _TABLENAME =  new TableName<PeticioDeFirma>("PeticioDeFirmaJPA");



  static final ModificationManager<PeticioDeFirma> __eventManager = new ModificationManager<PeticioDeFirma>();


  @PersistenceContext
  protected EntityManager __em;
  public PeticioDeFirmaJPAManager() {
  }
  protected PeticioDeFirmaJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return PeticioDeFirmaJPA. class;
	}



	public ModificationManager<PeticioDeFirma> getEventManager() {
	return __eventManager;
	}


	public TableName<PeticioDeFirma> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public PeticioDeFirma[] listToArray(List<PeticioDeFirma> list)  {
		if(list == null) { return null; };
		return list.toArray(new PeticioDeFirma[list.size()]);
	};

	public synchronized PeticioDeFirma create( java.lang.String _titol_, java.lang.String _descripcio_, java.lang.String _motiu_, java.lang.Long _fitxerAFirmarID_, java.lang.Long _firmaOriginalDetachedID_, java.lang.Long _fitxerAdaptatID_, long _tipusDocumentID_, java.lang.String _descripcioTipusDocument_, int _posicioTaulaFirmesID_, java.sql.Timestamp _dataSolicitud_, java.sql.Timestamp _dataFinal_, java.sql.Timestamp _dataCaducitat_, int _tipusFirmaID_, int _algorismeDeFirmaID_, java.lang.Boolean _modeDeFirma_, int _tipusEstatPeticioDeFirmaID_, java.lang.String _motiuDeRebuig_, java.lang.String _idiomaID_, int _prioritatID_, long _fluxDeFirmesID_, java.lang.String _usuariAplicacioID_, java.lang.String _remitentNom_, java.lang.String _remitentDescripcio_, java.lang.String _informacioAdicional_, java.lang.Long _logoSegellID_, java.lang.Long _custodiaInfoID_, java.lang.String _usuariEntitatID_, boolean _avisWeb_, boolean _segellatDeTemps_, int _tipusOperacioFirma_, java.lang.String _expedientCodi_, java.lang.String _expedientNom_, java.lang.String _expedientUrl_, java.lang.String _procedimentCodi_, java.lang.String _procedimentnom_) throws I18NException {
		PeticioDeFirmaJPA __bean =  new PeticioDeFirmaJPA(_titol_,_descripcio_,_motiu_,_fitxerAFirmarID_,_firmaOriginalDetachedID_,_fitxerAdaptatID_,_tipusDocumentID_,_descripcioTipusDocument_,_posicioTaulaFirmesID_,_dataSolicitud_,_dataFinal_,_dataCaducitat_,_tipusFirmaID_,_algorismeDeFirmaID_,_modeDeFirma_,_tipusEstatPeticioDeFirmaID_,_motiuDeRebuig_,_idiomaID_,_prioritatID_,_fluxDeFirmesID_,_usuariAplicacioID_,_remitentNom_,_remitentDescripcio_,_informacioAdicional_,_logoSegellID_,_custodiaInfoID_,_usuariEntitatID_,_avisWeb_,_segellatDeTemps_,_tipusOperacioFirma_,_expedientCodi_,_expedientNom_,_expedientUrl_,_procedimentCodi_,_procedimentnom_);
		return create(__bean);
	}



 public void delete(long _peticioDeFirmaID_) {
   delete(findByPrimaryKey(_peticioDeFirmaID_));
 }




	public PeticioDeFirma findByPrimaryKey(long _peticioDeFirmaID_) {
	  return __em.find(PeticioDeFirmaJPA.class, _peticioDeFirmaID_);  
	}
	@Override
	protected PeticioDeFirma getJPAInstance(PeticioDeFirma __bean) {
		return convertToJPA(__bean);
	}


	public static PeticioDeFirmaJPA convertToJPA(PeticioDeFirma __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof PeticioDeFirmaJPA) {
	    return (PeticioDeFirmaJPA)__bean;
	  }
	  
	  return PeticioDeFirmaJPA.toJPA(__bean);
	}


}