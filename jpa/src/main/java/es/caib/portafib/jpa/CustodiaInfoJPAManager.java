
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


public class CustodiaInfoJPAManager
		 extends AbstractPortaFIBJPAManager<CustodiaInfo, Long>
		 implements ICustodiaInfoManager, CustodiaInfoFields {




  private static final long serialVersionUID = -257242784L;

	 public static final TableName<CustodiaInfo> _TABLENAME =  new TableName<CustodiaInfo>("CustodiaInfoJPA");



  static final ModificationManager<CustodiaInfo> __eventManager = new ModificationManager<CustodiaInfo>();


  @PersistenceContext
  protected EntityManager __em;
  public CustodiaInfoJPAManager() {
  }
  protected CustodiaInfoJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return CustodiaInfoJPA. class;
	}



	public ModificationManager<CustodiaInfo> getEventManager() {
	return __eventManager;
	}


	public TableName<CustodiaInfo> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public CustodiaInfo[] listToArray(List<CustodiaInfo> list)  {
		if(list == null) { return null; };
		return list.toArray(new CustodiaInfo[list.size()]);
	};

	public synchronized CustodiaInfo create( java.lang.String _nomPlantilla_, java.lang.String _custodiaDocumentID_, long _pluginID_, java.lang.String _custodiaPluginParameters_, boolean _custodiar_, java.lang.String _pagines_, java.lang.String _missatge_, long _missatgePosicioPaginaID_, java.lang.String _codiBarresID_, long _codiBarresPosicioPaginaID_, java.lang.String _codiBarresText_, java.lang.String _usuariEntitatID_, java.lang.String _usuariAplicacioID_, java.lang.String _entitatID_, java.lang.String _titolPeticio_, java.sql.Timestamp _dataCustodia_, boolean _editable_, java.lang.String _csv_, java.lang.String _csvValidationWeb_, java.lang.String _csvGenerationDefinition_, java.lang.String _urlFitxerCustodiat_, java.lang.String _originalFileDirectUrl_, java.lang.String _printableFileDirectUrl_, java.lang.String _eniFileDirectUrl_, java.lang.String _expedientArxiuId_, java.lang.String _documentArxiuId_) throws I18NException {
		CustodiaInfoJPA __bean =  new CustodiaInfoJPA(_nomPlantilla_,_custodiaDocumentID_,_pluginID_,_custodiaPluginParameters_,_custodiar_,_pagines_,_missatge_,_missatgePosicioPaginaID_,_codiBarresID_,_codiBarresPosicioPaginaID_,_codiBarresText_,_usuariEntitatID_,_usuariAplicacioID_,_entitatID_,_titolPeticio_,_dataCustodia_,_editable_,_csv_,_csvValidationWeb_,_csvGenerationDefinition_,_urlFitxerCustodiat_,_originalFileDirectUrl_,_printableFileDirectUrl_,_eniFileDirectUrl_,_expedientArxiuId_,_documentArxiuId_);
		return create(__bean);
	}



 public void delete(long _custodiaInfoID_) {
   delete(findByPrimaryKey(_custodiaInfoID_));
 }




	public CustodiaInfo findByPrimaryKey(long _custodiaInfoID_) {
	  return __em.find(CustodiaInfoJPA.class, _custodiaInfoID_);  
	}
	@Override
	protected CustodiaInfo getJPAInstance(CustodiaInfo __bean) {
		return convertToJPA(__bean);
	}


	public static CustodiaInfoJPA convertToJPA(CustodiaInfo __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof CustodiaInfoJPA) {
	    return (CustodiaInfoJPA)__bean;
	  }
	  
	  return CustodiaInfoJPA.toJPA(__bean);
	}


}