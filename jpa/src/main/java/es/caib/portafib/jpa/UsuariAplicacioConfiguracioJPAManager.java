
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


public class UsuariAplicacioConfiguracioJPAManager
		 extends AbstractPortaFIBJPAManager<UsuariAplicacioConfiguracio, Long>
		 implements IUsuariAplicacioConfiguracioManager, UsuariAplicacioConfiguracioFields {




  private static final long serialVersionUID = -427596873L;

	 public static final TableName<UsuariAplicacioConfiguracio> _TABLENAME =  new TableName<UsuariAplicacioConfiguracio>("UsuariAplicacioConfiguracioJPA");



  static final ModificationManager<UsuariAplicacioConfiguracio> __eventManager = new ModificationManager<UsuariAplicacioConfiguracio>();


  @PersistenceContext
  protected EntityManager __em;
  public UsuariAplicacioConfiguracioJPAManager() {
  }
  protected UsuariAplicacioConfiguracioJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return UsuariAplicacioConfiguracioJPA. class;
	}



	public ModificationManager<UsuariAplicacioConfiguracio> getEventManager() {
	return __eventManager;
	}


	public TableName<UsuariAplicacioConfiguracio> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public UsuariAplicacioConfiguracio[] listToArray(List<UsuariAplicacioConfiguracio> list)  {
		if(list == null) { return null; };
		return list.toArray(new UsuariAplicacioConfiguracio[list.size()]);
	};

	public synchronized UsuariAplicacioConfiguracio create( java.lang.String _nom_, java.lang.String _entitatID_, boolean _usEnFirmaApiSimpleServidor_, boolean _usEnFirmaApiSimpleWeb_, boolean _usEnFirmaWeb_, boolean _usEnFirmaWS2_, boolean _usEnFirmaPassarelaServidor_, boolean _usEnFirmaPassarelaWeb_, int _usPoliticaDeFirma_, java.lang.String _policyIdentifier_, java.lang.String _policyIdentifierHash_, java.lang.String _policyIdentifierHashAlgorithm_, java.lang.String _policyUrlDocument_, java.lang.String _filtreCertificats_, int _tipusOperacioFirma_, int _tipusFirmaID_, java.lang.Integer _algorismeDeFirmaID_, boolean _modeDeFirma_, int _politicaCustodia_, java.lang.Long _custodiaInfoID_, int _politicaTaulaFirmes_, int _posicioTaulaFirmesID_, java.lang.Long _firmatPerFormatID_, java.lang.String _propietatsTaulaFirmes_, java.lang.Long _motiuDelegacioID_, int _politicaSegellatDeTemps_, java.lang.Long _pluginSegellatID_, java.lang.String _htmlPerLlistarPluginsFirmaWeb_, java.lang.Long _pluginFirmaServidorID_, java.lang.Integer _maxFirmesEnServidor_, java.lang.Integer _upgradeSignFormat_, java.lang.Long _loginCertificateID_, java.lang.Boolean _comprovarNifFirma_, java.lang.Boolean _checkCanviatDocFirmat_, java.lang.Boolean _validarFirma_, java.lang.Boolean _validarCertificat_) throws I18NException {
		UsuariAplicacioConfiguracioJPA __bean =  new UsuariAplicacioConfiguracioJPA(_nom_,_entitatID_,_usEnFirmaApiSimpleServidor_,_usEnFirmaApiSimpleWeb_,_usEnFirmaWeb_,_usEnFirmaWS2_,_usEnFirmaPassarelaServidor_,_usEnFirmaPassarelaWeb_,_usPoliticaDeFirma_,_policyIdentifier_,_policyIdentifierHash_,_policyIdentifierHashAlgorithm_,_policyUrlDocument_,_filtreCertificats_,_tipusOperacioFirma_,_tipusFirmaID_,_algorismeDeFirmaID_,_modeDeFirma_,_politicaCustodia_,_custodiaInfoID_,_politicaTaulaFirmes_,_posicioTaulaFirmesID_,_firmatPerFormatID_,_propietatsTaulaFirmes_,_motiuDelegacioID_,_politicaSegellatDeTemps_,_pluginSegellatID_,_htmlPerLlistarPluginsFirmaWeb_,_pluginFirmaServidorID_,_maxFirmesEnServidor_,_upgradeSignFormat_,_loginCertificateID_,_comprovarNifFirma_,_checkCanviatDocFirmat_,_validarFirma_,_validarCertificat_);
		return create(__bean);
	}



 public void delete(long _usuariAplicacioConfigID_) {
   delete(findByPrimaryKey(_usuariAplicacioConfigID_));
 }




	public UsuariAplicacioConfiguracio findByPrimaryKey(long _usuariAplicacioConfigID_) {
	  return __em.find(UsuariAplicacioConfiguracioJPA.class, _usuariAplicacioConfigID_);  
	}
	@Override
	protected UsuariAplicacioConfiguracio getJPAInstance(UsuariAplicacioConfiguracio __bean) {
		return convertToJPA(__bean);
	}


	public static UsuariAplicacioConfiguracioJPA convertToJPA(UsuariAplicacioConfiguracio __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof UsuariAplicacioConfiguracioJPA) {
	    return (UsuariAplicacioConfiguracioJPA)__bean;
	  }
	  
	  return UsuariAplicacioConfiguracioJPA.toJPA(__bean);
	}

  @Override
  public UsuariAplicacioConfiguracio create(UsuariAplicacioConfiguracio transientInstance) throws I18NException {
    processTranslations(transientInstance);
    return super.create(transientInstance);
  }


  @Override
  public UsuariAplicacioConfiguracio update(UsuariAplicacioConfiguracio transientInstance) throws I18NException {
    processTranslations(transientInstance);
    return super.update(transientInstance);
  }


  private void processTranslations(UsuariAplicacioConfiguracio transientInstance) {
    if (transientInstance != null) {
      if (transientInstance.getFirmatPerFormatID() == null) {
        if (transientInstance instanceof UsuariAplicacioConfiguracioJPA) {
          UsuariAplicacioConfiguracioJPA _jpa = (UsuariAplicacioConfiguracioJPA)transientInstance;
          TraduccioJPA _trad = _jpa.getFirmatPerFormat();
           if (_trad != null) {
            if (_trad.getTraduccioID() == 0) {
              getEntityManager().persist(_trad);
            } 
            transientInstance.setFirmatPerFormatID(_trad.getTraduccioID());
          }
        }
      }
      if (transientInstance.getMotiuDelegacioID() == null) {
        if (transientInstance instanceof UsuariAplicacioConfiguracioJPA) {
          UsuariAplicacioConfiguracioJPA _jpa = (UsuariAplicacioConfiguracioJPA)transientInstance;
          TraduccioJPA _trad = _jpa.getMotiuDelegacio();
           if (_trad != null) {
            if (_trad.getTraduccioID() == 0) {
              getEntityManager().persist(_trad);
            } 
            transientInstance.setMotiuDelegacioID(_trad.getTraduccioID());
          }
        }
      }
    }
  }


}