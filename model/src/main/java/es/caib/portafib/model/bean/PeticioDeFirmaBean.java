
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.PeticioDeFirma;


public class PeticioDeFirmaBean implements PeticioDeFirma {



private static final long serialVersionUID = 906207731L;

	long peticioDeFirmaID;// PK
	java.lang.String titol;
	java.lang.String descripcio;
	java.lang.String motiu;
	java.lang.Long fitxerAFirmarID;
	java.lang.Long firmaOriginalDetachedID;
	java.lang.Long fitxerAdaptatID;
	long tipusDocumentID;
	java.lang.String descripcioTipusDocument;
	java.sql.Timestamp dataSolicitud;
	java.sql.Timestamp dataFinal;
	java.sql.Timestamp dataCaducitat;
	int tipusOperacioFirma;
	int tipusFirmaID;
	int algorismeDeFirmaID;
	java.lang.Boolean modeDeFirma;
	int posicioTaulaFirmesID;
	int tipusEstatPeticioDeFirmaID;
	java.lang.String motiuDeRebuig;
	java.lang.String idiomaID;
	int prioritatID;
	long fluxDeFirmesID;
	java.lang.String solicitantUsuariAplicacioID;
	java.lang.String remitentNom;
	java.lang.String remitentDescripcio;
	java.lang.String expedientCodi;
	java.lang.String expedientNom;
	java.lang.String expedientUrl;
	java.lang.String procedimentCodi;
	java.lang.String procedimentNom;
	java.lang.String informacioAddicional;
	java.lang.Double informacioAddicionalAvaluable;
	java.lang.Long logoSegellID;
	java.lang.Long custodiaInfoID;
	java.lang.String solicitantUsuariEntitat1ID;
	java.lang.String solicitantUsuariEntitat2ID;
	java.lang.String solicitantUsuariEntitat3ID;
	boolean avisWeb;
	boolean segellatDeTemps;
	int origenPeticioDeFirma;
	java.lang.Long configuracioDeFirmaID;


  /** Constructor Buit */
  public PeticioDeFirmaBean() {
  }

  /** Constructor amb tots els camps  */
  public PeticioDeFirmaBean(long peticioDeFirmaID , java.lang.String titol , java.lang.String descripcio , java.lang.String motiu , java.lang.Long fitxerAFirmarID , java.lang.Long firmaOriginalDetachedID , java.lang.Long fitxerAdaptatID , long tipusDocumentID , java.lang.String descripcioTipusDocument , java.sql.Timestamp dataSolicitud , java.sql.Timestamp dataFinal , java.sql.Timestamp dataCaducitat , int tipusOperacioFirma , int tipusFirmaID , int algorismeDeFirmaID , java.lang.Boolean modeDeFirma , int posicioTaulaFirmesID , int tipusEstatPeticioDeFirmaID , java.lang.String motiuDeRebuig , java.lang.String idiomaID , int prioritatID , long fluxDeFirmesID , java.lang.String solicitantUsuariAplicacioID , java.lang.String remitentNom , java.lang.String remitentDescripcio , java.lang.String expedientCodi , java.lang.String expedientNom , java.lang.String expedientUrl , java.lang.String procedimentCodi , java.lang.String procedimentNom , java.lang.String informacioAddicional , java.lang.Double informacioAddicionalAvaluable , java.lang.Long logoSegellID , java.lang.Long custodiaInfoID , java.lang.String solicitantUsuariEntitat1ID , java.lang.String solicitantUsuariEntitat2ID , java.lang.String solicitantUsuariEntitat3ID , boolean avisWeb , boolean segellatDeTemps , int origenPeticioDeFirma , java.lang.Long configuracioDeFirmaID) {
    this.peticioDeFirmaID=peticioDeFirmaID;
    this.titol=titol;
    this.descripcio=descripcio;
    this.motiu=motiu;
    this.fitxerAFirmarID=fitxerAFirmarID;
    this.firmaOriginalDetachedID=firmaOriginalDetachedID;
    this.fitxerAdaptatID=fitxerAdaptatID;
    this.tipusDocumentID=tipusDocumentID;
    this.descripcioTipusDocument=descripcioTipusDocument;
    this.dataSolicitud=dataSolicitud;
    this.dataFinal=dataFinal;
    this.dataCaducitat=dataCaducitat;
    this.tipusOperacioFirma=tipusOperacioFirma;
    this.tipusFirmaID=tipusFirmaID;
    this.algorismeDeFirmaID=algorismeDeFirmaID;
    this.modeDeFirma=modeDeFirma;
    this.posicioTaulaFirmesID=posicioTaulaFirmesID;
    this.tipusEstatPeticioDeFirmaID=tipusEstatPeticioDeFirmaID;
    this.motiuDeRebuig=motiuDeRebuig;
    this.idiomaID=idiomaID;
    this.prioritatID=prioritatID;
    this.fluxDeFirmesID=fluxDeFirmesID;
    this.solicitantUsuariAplicacioID=solicitantUsuariAplicacioID;
    this.remitentNom=remitentNom;
    this.remitentDescripcio=remitentDescripcio;
    this.expedientCodi=expedientCodi;
    this.expedientNom=expedientNom;
    this.expedientUrl=expedientUrl;
    this.procedimentCodi=procedimentCodi;
    this.procedimentNom=procedimentNom;
    this.informacioAddicional=informacioAddicional;
    this.informacioAddicionalAvaluable=informacioAddicionalAvaluable;
    this.logoSegellID=logoSegellID;
    this.custodiaInfoID=custodiaInfoID;
    this.solicitantUsuariEntitat1ID=solicitantUsuariEntitat1ID;
    this.solicitantUsuariEntitat2ID=solicitantUsuariEntitat2ID;
    this.solicitantUsuariEntitat3ID=solicitantUsuariEntitat3ID;
    this.avisWeb=avisWeb;
    this.segellatDeTemps=segellatDeTemps;
    this.origenPeticioDeFirma=origenPeticioDeFirma;
    this.configuracioDeFirmaID=configuracioDeFirmaID;
}
  /** Constructor sense valors autoincrementals */
  public PeticioDeFirmaBean(java.lang.String titol , java.lang.String descripcio , java.lang.String motiu , java.lang.Long fitxerAFirmarID , java.lang.Long firmaOriginalDetachedID , java.lang.Long fitxerAdaptatID , long tipusDocumentID , java.lang.String descripcioTipusDocument , java.sql.Timestamp dataSolicitud , java.sql.Timestamp dataFinal , java.sql.Timestamp dataCaducitat , int tipusOperacioFirma , int tipusFirmaID , int algorismeDeFirmaID , java.lang.Boolean modeDeFirma , int posicioTaulaFirmesID , int tipusEstatPeticioDeFirmaID , java.lang.String motiuDeRebuig , java.lang.String idiomaID , int prioritatID , long fluxDeFirmesID , java.lang.String solicitantUsuariAplicacioID , java.lang.String remitentNom , java.lang.String remitentDescripcio , java.lang.String expedientCodi , java.lang.String expedientNom , java.lang.String expedientUrl , java.lang.String procedimentCodi , java.lang.String procedimentNom , java.lang.String informacioAddicional , java.lang.Double informacioAddicionalAvaluable , java.lang.Long logoSegellID , java.lang.Long custodiaInfoID , java.lang.String solicitantUsuariEntitat1ID , java.lang.String solicitantUsuariEntitat2ID , java.lang.String solicitantUsuariEntitat3ID , boolean avisWeb , boolean segellatDeTemps , int origenPeticioDeFirma , java.lang.Long configuracioDeFirmaID) {
    this.titol=titol;
    this.descripcio=descripcio;
    this.motiu=motiu;
    this.fitxerAFirmarID=fitxerAFirmarID;
    this.firmaOriginalDetachedID=firmaOriginalDetachedID;
    this.fitxerAdaptatID=fitxerAdaptatID;
    this.tipusDocumentID=tipusDocumentID;
    this.descripcioTipusDocument=descripcioTipusDocument;
    this.dataSolicitud=dataSolicitud;
    this.dataFinal=dataFinal;
    this.dataCaducitat=dataCaducitat;
    this.tipusOperacioFirma=tipusOperacioFirma;
    this.tipusFirmaID=tipusFirmaID;
    this.algorismeDeFirmaID=algorismeDeFirmaID;
    this.modeDeFirma=modeDeFirma;
    this.posicioTaulaFirmesID=posicioTaulaFirmesID;
    this.tipusEstatPeticioDeFirmaID=tipusEstatPeticioDeFirmaID;
    this.motiuDeRebuig=motiuDeRebuig;
    this.idiomaID=idiomaID;
    this.prioritatID=prioritatID;
    this.fluxDeFirmesID=fluxDeFirmesID;
    this.solicitantUsuariAplicacioID=solicitantUsuariAplicacioID;
    this.remitentNom=remitentNom;
    this.remitentDescripcio=remitentDescripcio;
    this.expedientCodi=expedientCodi;
    this.expedientNom=expedientNom;
    this.expedientUrl=expedientUrl;
    this.procedimentCodi=procedimentCodi;
    this.procedimentNom=procedimentNom;
    this.informacioAddicional=informacioAddicional;
    this.informacioAddicionalAvaluable=informacioAddicionalAvaluable;
    this.logoSegellID=logoSegellID;
    this.custodiaInfoID=custodiaInfoID;
    this.solicitantUsuariEntitat1ID=solicitantUsuariEntitat1ID;
    this.solicitantUsuariEntitat2ID=solicitantUsuariEntitat2ID;
    this.solicitantUsuariEntitat3ID=solicitantUsuariEntitat3ID;
    this.avisWeb=avisWeb;
    this.segellatDeTemps=segellatDeTemps;
    this.origenPeticioDeFirma=origenPeticioDeFirma;
    this.configuracioDeFirmaID=configuracioDeFirmaID;
}
  /** Constructor dels valors Not Null */
  public PeticioDeFirmaBean(long peticioDeFirmaID , java.lang.String titol , java.lang.String motiu , long tipusDocumentID , java.sql.Timestamp dataCaducitat , int tipusOperacioFirma , int tipusFirmaID , int algorismeDeFirmaID , java.lang.Boolean modeDeFirma , int posicioTaulaFirmesID , int tipusEstatPeticioDeFirmaID , java.lang.String idiomaID , int prioritatID , long fluxDeFirmesID , java.lang.String solicitantUsuariAplicacioID , java.lang.String remitentNom , boolean avisWeb , boolean segellatDeTemps , int origenPeticioDeFirma) {
    this.peticioDeFirmaID=peticioDeFirmaID;
    this.titol=titol;
    this.motiu=motiu;
    this.tipusDocumentID=tipusDocumentID;
    this.dataCaducitat=dataCaducitat;
    this.tipusOperacioFirma=tipusOperacioFirma;
    this.tipusFirmaID=tipusFirmaID;
    this.algorismeDeFirmaID=algorismeDeFirmaID;
    this.modeDeFirma=modeDeFirma;
    this.posicioTaulaFirmesID=posicioTaulaFirmesID;
    this.tipusEstatPeticioDeFirmaID=tipusEstatPeticioDeFirmaID;
    this.idiomaID=idiomaID;
    this.prioritatID=prioritatID;
    this.fluxDeFirmesID=fluxDeFirmesID;
    this.solicitantUsuariAplicacioID=solicitantUsuariAplicacioID;
    this.remitentNom=remitentNom;
    this.avisWeb=avisWeb;
    this.segellatDeTemps=segellatDeTemps;
    this.origenPeticioDeFirma=origenPeticioDeFirma;
}
  public PeticioDeFirmaBean(PeticioDeFirma __bean) {
    this.setPeticioDeFirmaID(__bean.getPeticioDeFirmaID());
    this.setTitol(__bean.getTitol());
    this.setDescripcio(__bean.getDescripcio());
    this.setMotiu(__bean.getMotiu());
    this.setFitxerAFirmarID(__bean.getFitxerAFirmarID());
    this.setFirmaOriginalDetachedID(__bean.getFirmaOriginalDetachedID());
    this.setFitxerAdaptatID(__bean.getFitxerAdaptatID());
    this.setTipusDocumentID(__bean.getTipusDocumentID());
    this.setDescripcioTipusDocument(__bean.getDescripcioTipusDocument());
    this.setDataSolicitud(__bean.getDataSolicitud());
    this.setDataFinal(__bean.getDataFinal());
    this.setDataCaducitat(__bean.getDataCaducitat());
    this.setTipusOperacioFirma(__bean.getTipusOperacioFirma());
    this.setTipusFirmaID(__bean.getTipusFirmaID());
    this.setAlgorismeDeFirmaID(__bean.getAlgorismeDeFirmaID());
    this.setModeDeFirma(__bean.getModeDeFirma());
    this.setPosicioTaulaFirmesID(__bean.getPosicioTaulaFirmesID());
    this.setTipusEstatPeticioDeFirmaID(__bean.getTipusEstatPeticioDeFirmaID());
    this.setMotiuDeRebuig(__bean.getMotiuDeRebuig());
    this.setIdiomaID(__bean.getIdiomaID());
    this.setPrioritatID(__bean.getPrioritatID());
    this.setFluxDeFirmesID(__bean.getFluxDeFirmesID());
    this.setSolicitantUsuariAplicacioID(__bean.getSolicitantUsuariAplicacioID());
    this.setRemitentNom(__bean.getRemitentNom());
    this.setRemitentDescripcio(__bean.getRemitentDescripcio());
    this.setExpedientCodi(__bean.getExpedientCodi());
    this.setExpedientNom(__bean.getExpedientNom());
    this.setExpedientUrl(__bean.getExpedientUrl());
    this.setProcedimentCodi(__bean.getProcedimentCodi());
    this.setProcedimentNom(__bean.getProcedimentNom());
    this.setInformacioAddicional(__bean.getInformacioAddicional());
    this.setInformacioAddicionalAvaluable(__bean.getInformacioAddicionalAvaluable());
    this.setLogoSegellID(__bean.getLogoSegellID());
    this.setCustodiaInfoID(__bean.getCustodiaInfoID());
    this.setSolicitantUsuariEntitat1ID(__bean.getSolicitantUsuariEntitat1ID());
    this.setSolicitantUsuariEntitat2ID(__bean.getSolicitantUsuariEntitat2ID());
    this.setSolicitantUsuariEntitat3ID(__bean.getSolicitantUsuariEntitat3ID());
    this.setAvisWeb(__bean.isAvisWeb());
    this.setSegellatDeTemps(__bean.isSegellatDeTemps());
    this.setOrigenPeticioDeFirma(__bean.getOrigenPeticioDeFirma());
    this.setConfiguracioDeFirmaID(__bean.getConfiguracioDeFirmaID());
    // Fitxer
    this.setFitxerAFirmar(FitxerBean.toBean(__bean.getFitxerAFirmar()));
    // Fitxer
    this.setFirmaOriginalDetached(FitxerBean.toBean(__bean.getFirmaOriginalDetached()));
    // Fitxer
    this.setFitxerAdaptat(FitxerBean.toBean(__bean.getFitxerAdaptat()));
    // Fitxer
    this.setLogoSegell(FitxerBean.toBean(__bean.getLogoSegell()));
	}

	public long getPeticioDeFirmaID() {
		return(peticioDeFirmaID);
	};
	public void setPeticioDeFirmaID(long _peticioDeFirmaID_) {
		this.peticioDeFirmaID = _peticioDeFirmaID_;
	};

	public java.lang.String getTitol() {
		return(titol);
	};
	public void setTitol(java.lang.String _titol_) {
		this.titol = _titol_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};

	public java.lang.String getMotiu() {
		return(motiu);
	};
	public void setMotiu(java.lang.String _motiu_) {
		this.motiu = _motiu_;
	};

	public java.lang.Long getFitxerAFirmarID() {
		return(fitxerAFirmarID);
	};
	public void setFitxerAFirmarID(java.lang.Long _fitxerAFirmarID_) {
		this.fitxerAFirmarID = _fitxerAFirmarID_;
	};

	public java.lang.Long getFirmaOriginalDetachedID() {
		return(firmaOriginalDetachedID);
	};
	public void setFirmaOriginalDetachedID(java.lang.Long _firmaOriginalDetachedID_) {
		this.firmaOriginalDetachedID = _firmaOriginalDetachedID_;
	};

	public java.lang.Long getFitxerAdaptatID() {
		return(fitxerAdaptatID);
	};
	public void setFitxerAdaptatID(java.lang.Long _fitxerAdaptatID_) {
		this.fitxerAdaptatID = _fitxerAdaptatID_;
	};

	public long getTipusDocumentID() {
		return(tipusDocumentID);
	};
	public void setTipusDocumentID(long _tipusDocumentID_) {
		this.tipusDocumentID = _tipusDocumentID_;
	};

	public java.lang.String getDescripcioTipusDocument() {
		return(descripcioTipusDocument);
	};
	public void setDescripcioTipusDocument(java.lang.String _descripcioTipusDocument_) {
		this.descripcioTipusDocument = _descripcioTipusDocument_;
	};

	public java.sql.Timestamp getDataSolicitud() {
		return(dataSolicitud);
	};
	public void setDataSolicitud(java.sql.Timestamp _dataSolicitud_) {
		this.dataSolicitud = _dataSolicitud_;
	};

	public java.sql.Timestamp getDataFinal() {
		return(dataFinal);
	};
	public void setDataFinal(java.sql.Timestamp _dataFinal_) {
		this.dataFinal = _dataFinal_;
	};

	public java.sql.Timestamp getDataCaducitat() {
		return(dataCaducitat);
	};
	public void setDataCaducitat(java.sql.Timestamp _dataCaducitat_) {
		this.dataCaducitat = _dataCaducitat_;
	};

	public int getTipusOperacioFirma() {
		return(tipusOperacioFirma);
	};
	public void setTipusOperacioFirma(int _tipusOperacioFirma_) {
		this.tipusOperacioFirma = _tipusOperacioFirma_;
	};

	public int getTipusFirmaID() {
		return(tipusFirmaID);
	};
	public void setTipusFirmaID(int _tipusFirmaID_) {
		this.tipusFirmaID = _tipusFirmaID_;
	};

	public int getAlgorismeDeFirmaID() {
		return(algorismeDeFirmaID);
	};
	public void setAlgorismeDeFirmaID(int _algorismeDeFirmaID_) {
		this.algorismeDeFirmaID = _algorismeDeFirmaID_;
	};

	public java.lang.Boolean getModeDeFirma() {
		return(modeDeFirma);
	};
	public void setModeDeFirma(java.lang.Boolean _modeDeFirma_) {
		this.modeDeFirma = _modeDeFirma_;
	};

	public int getPosicioTaulaFirmesID() {
		return(posicioTaulaFirmesID);
	};
	public void setPosicioTaulaFirmesID(int _posicioTaulaFirmesID_) {
		this.posicioTaulaFirmesID = _posicioTaulaFirmesID_;
	};

	public int getTipusEstatPeticioDeFirmaID() {
		return(tipusEstatPeticioDeFirmaID);
	};
	public void setTipusEstatPeticioDeFirmaID(int _tipusEstatPeticioDeFirmaID_) {
		this.tipusEstatPeticioDeFirmaID = _tipusEstatPeticioDeFirmaID_;
	};

	public java.lang.String getMotiuDeRebuig() {
		return(motiuDeRebuig);
	};
	public void setMotiuDeRebuig(java.lang.String _motiuDeRebuig_) {
		this.motiuDeRebuig = _motiuDeRebuig_;
	};

	public java.lang.String getIdiomaID() {
		return(idiomaID);
	};
	public void setIdiomaID(java.lang.String _idiomaID_) {
		this.idiomaID = _idiomaID_;
	};

	public int getPrioritatID() {
		return(prioritatID);
	};
	public void setPrioritatID(int _prioritatID_) {
		this.prioritatID = _prioritatID_;
	};

	public long getFluxDeFirmesID() {
		return(fluxDeFirmesID);
	};
	public void setFluxDeFirmesID(long _fluxDeFirmesID_) {
		this.fluxDeFirmesID = _fluxDeFirmesID_;
	};

	public java.lang.String getSolicitantUsuariAplicacioID() {
		return(solicitantUsuariAplicacioID);
	};
	public void setSolicitantUsuariAplicacioID(java.lang.String _solicitantUsuariAplicacioID_) {
		this.solicitantUsuariAplicacioID = _solicitantUsuariAplicacioID_;
	};

	public java.lang.String getRemitentNom() {
		return(remitentNom);
	};
	public void setRemitentNom(java.lang.String _remitentNom_) {
		this.remitentNom = _remitentNom_;
	};

	public java.lang.String getRemitentDescripcio() {
		return(remitentDescripcio);
	};
	public void setRemitentDescripcio(java.lang.String _remitentDescripcio_) {
		this.remitentDescripcio = _remitentDescripcio_;
	};

	public java.lang.String getExpedientCodi() {
		return(expedientCodi);
	};
	public void setExpedientCodi(java.lang.String _expedientCodi_) {
		this.expedientCodi = _expedientCodi_;
	};

	public java.lang.String getExpedientNom() {
		return(expedientNom);
	};
	public void setExpedientNom(java.lang.String _expedientNom_) {
		this.expedientNom = _expedientNom_;
	};

	public java.lang.String getExpedientUrl() {
		return(expedientUrl);
	};
	public void setExpedientUrl(java.lang.String _expedientUrl_) {
		this.expedientUrl = _expedientUrl_;
	};

	public java.lang.String getProcedimentCodi() {
		return(procedimentCodi);
	};
	public void setProcedimentCodi(java.lang.String _procedimentCodi_) {
		this.procedimentCodi = _procedimentCodi_;
	};

	public java.lang.String getProcedimentNom() {
		return(procedimentNom);
	};
	public void setProcedimentNom(java.lang.String _procedimentNom_) {
		this.procedimentNom = _procedimentNom_;
	};

	public java.lang.String getInformacioAddicional() {
		return(informacioAddicional);
	};
	public void setInformacioAddicional(java.lang.String _informacioAddicional_) {
		this.informacioAddicional = _informacioAddicional_;
	};

	public java.lang.Double getInformacioAddicionalAvaluable() {
		return(informacioAddicionalAvaluable);
	};
	public void setInformacioAddicionalAvaluable(java.lang.Double _informacioAddicionalAvaluable_) {
		this.informacioAddicionalAvaluable = _informacioAddicionalAvaluable_;
	};

	public java.lang.Long getLogoSegellID() {
		return(logoSegellID);
	};
	public void setLogoSegellID(java.lang.Long _logoSegellID_) {
		this.logoSegellID = _logoSegellID_;
	};

	public java.lang.Long getCustodiaInfoID() {
		return(custodiaInfoID);
	};
	public void setCustodiaInfoID(java.lang.Long _custodiaInfoID_) {
		this.custodiaInfoID = _custodiaInfoID_;
	};

	public java.lang.String getSolicitantUsuariEntitat1ID() {
		return(solicitantUsuariEntitat1ID);
	};
	public void setSolicitantUsuariEntitat1ID(java.lang.String _solicitantUsuariEntitat1ID_) {
		this.solicitantUsuariEntitat1ID = _solicitantUsuariEntitat1ID_;
	};

	public java.lang.String getSolicitantUsuariEntitat2ID() {
		return(solicitantUsuariEntitat2ID);
	};
	public void setSolicitantUsuariEntitat2ID(java.lang.String _solicitantUsuariEntitat2ID_) {
		this.solicitantUsuariEntitat2ID = _solicitantUsuariEntitat2ID_;
	};

	public java.lang.String getSolicitantUsuariEntitat3ID() {
		return(solicitantUsuariEntitat3ID);
	};
	public void setSolicitantUsuariEntitat3ID(java.lang.String _solicitantUsuariEntitat3ID_) {
		this.solicitantUsuariEntitat3ID = _solicitantUsuariEntitat3ID_;
	};

	public boolean isAvisWeb() {
		return(avisWeb);
	};
	public void setAvisWeb(boolean _avisWeb_) {
		this.avisWeb = _avisWeb_;
	};

	public boolean isSegellatDeTemps() {
		return(segellatDeTemps);
	};
	public void setSegellatDeTemps(boolean _segellatDeTemps_) {
		this.segellatDeTemps = _segellatDeTemps_;
	};

	public int getOrigenPeticioDeFirma() {
		return(origenPeticioDeFirma);
	};
	public void setOrigenPeticioDeFirma(int _origenPeticioDeFirma_) {
		this.origenPeticioDeFirma = _origenPeticioDeFirma_;
	};

	public java.lang.Long getConfiguracioDeFirmaID() {
		return(configuracioDeFirmaID);
	};
	public void setConfiguracioDeFirmaID(java.lang.Long _configuracioDeFirmaID_) {
		this.configuracioDeFirmaID = _configuracioDeFirmaID_;
	};



  // ======================================

  public static PeticioDeFirmaBean toBean(PeticioDeFirma __bean) {
    if (__bean == null) { return null;}
    PeticioDeFirmaBean __tmp = new PeticioDeFirmaBean();
    __tmp.setPeticioDeFirmaID(__bean.getPeticioDeFirmaID());
    __tmp.setTitol(__bean.getTitol());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setMotiu(__bean.getMotiu());
    __tmp.setFitxerAFirmarID(__bean.getFitxerAFirmarID());
    __tmp.setFirmaOriginalDetachedID(__bean.getFirmaOriginalDetachedID());
    __tmp.setFitxerAdaptatID(__bean.getFitxerAdaptatID());
    __tmp.setTipusDocumentID(__bean.getTipusDocumentID());
    __tmp.setDescripcioTipusDocument(__bean.getDescripcioTipusDocument());
    __tmp.setDataSolicitud(__bean.getDataSolicitud());
    __tmp.setDataFinal(__bean.getDataFinal());
    __tmp.setDataCaducitat(__bean.getDataCaducitat());
    __tmp.setTipusOperacioFirma(__bean.getTipusOperacioFirma());
    __tmp.setTipusFirmaID(__bean.getTipusFirmaID());
    __tmp.setAlgorismeDeFirmaID(__bean.getAlgorismeDeFirmaID());
    __tmp.setModeDeFirma(__bean.getModeDeFirma());
    __tmp.setPosicioTaulaFirmesID(__bean.getPosicioTaulaFirmesID());
    __tmp.setTipusEstatPeticioDeFirmaID(__bean.getTipusEstatPeticioDeFirmaID());
    __tmp.setMotiuDeRebuig(__bean.getMotiuDeRebuig());
    __tmp.setIdiomaID(__bean.getIdiomaID());
    __tmp.setPrioritatID(__bean.getPrioritatID());
    __tmp.setFluxDeFirmesID(__bean.getFluxDeFirmesID());
    __tmp.setSolicitantUsuariAplicacioID(__bean.getSolicitantUsuariAplicacioID());
    __tmp.setRemitentNom(__bean.getRemitentNom());
    __tmp.setRemitentDescripcio(__bean.getRemitentDescripcio());
    __tmp.setExpedientCodi(__bean.getExpedientCodi());
    __tmp.setExpedientNom(__bean.getExpedientNom());
    __tmp.setExpedientUrl(__bean.getExpedientUrl());
    __tmp.setProcedimentCodi(__bean.getProcedimentCodi());
    __tmp.setProcedimentNom(__bean.getProcedimentNom());
    __tmp.setInformacioAddicional(__bean.getInformacioAddicional());
    __tmp.setInformacioAddicionalAvaluable(__bean.getInformacioAddicionalAvaluable());
    __tmp.setLogoSegellID(__bean.getLogoSegellID());
    __tmp.setCustodiaInfoID(__bean.getCustodiaInfoID());
    __tmp.setSolicitantUsuariEntitat1ID(__bean.getSolicitantUsuariEntitat1ID());
    __tmp.setSolicitantUsuariEntitat2ID(__bean.getSolicitantUsuariEntitat2ID());
    __tmp.setSolicitantUsuariEntitat3ID(__bean.getSolicitantUsuariEntitat3ID());
    __tmp.setAvisWeb(__bean.isAvisWeb());
    __tmp.setSegellatDeTemps(__bean.isSegellatDeTemps());
    __tmp.setOrigenPeticioDeFirma(__bean.getOrigenPeticioDeFirma());
    __tmp.setConfiguracioDeFirmaID(__bean.getConfiguracioDeFirmaID());
    // Fitxer
    __tmp.setFitxerAFirmar(FitxerBean.toBean(__bean.getFitxerAFirmar()));
    // Fitxer
    __tmp.setFirmaOriginalDetached(FitxerBean.toBean(__bean.getFirmaOriginalDetached()));
    // Fitxer
    __tmp.setFitxerAdaptat(FitxerBean.toBean(__bean.getFitxerAdaptat()));
    // Fitxer
    __tmp.setLogoSegell(FitxerBean.toBean(__bean.getLogoSegell()));
		return __tmp;
	}

  protected FitxerBean fitxerAFirmar;
  public FitxerBean getFitxerAFirmar() {
    return fitxerAFirmar;
  }
  public void setFitxerAFirmar(FitxerBean __field) {
    this. fitxerAFirmar = __field;
  }
  protected FitxerBean firmaOriginalDetached;
  public FitxerBean getFirmaOriginalDetached() {
    return firmaOriginalDetached;
  }
  public void setFirmaOriginalDetached(FitxerBean __field) {
    this. firmaOriginalDetached = __field;
  }
  protected FitxerBean fitxerAdaptat;
  public FitxerBean getFitxerAdaptat() {
    return fitxerAdaptat;
  }
  public void setFitxerAdaptat(FitxerBean __field) {
    this. fitxerAdaptat = __field;
  }
  protected FitxerBean logoSegell;
  public FitxerBean getLogoSegell() {
    return logoSegell;
  }
  public void setLogoSegell(FitxerBean __field) {
    this. logoSegell = __field;
  }


}
