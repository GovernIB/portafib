
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.PeticioDeFirma;


public class PeticioDeFirmaBean implements PeticioDeFirma {



private static final long serialVersionUID = 906207731L;

	long peticioDeFirmaID;// PK
	java.lang.String titol;
	java.lang.String descripcio;
	java.lang.String motiu;
	long fitxerAFirmarID;
	java.lang.Long fitxerAdaptatID;
	long tipusDocumentID;
	java.lang.String descripcioTipusDocument;
	int posicioTaulaFirmesID;
	java.sql.Timestamp dataSolicitud;
	java.sql.Timestamp dataFinal;
	java.sql.Timestamp dataCaducitat;
	int tipusFirmaID;
	int algorismeDeFirmaID;
	java.lang.Boolean modeDeFirma;
	int tipusEstatPeticioDeFirmaID;
	java.lang.String motiuDeRebuig;
	java.lang.String idiomaID;
	int prioritatID;
	long fluxDeFirmesID;
	java.lang.String usuariAplicacioID;
	java.lang.String remitentNom;
	java.lang.String remitentDescripcio;
	java.lang.String informacioAdicional;
	java.lang.Long logoSegellID;
	java.lang.Long custodiaInfoID;
	java.lang.String usuariEntitatID;
	boolean avisWeb;
	boolean segellatDeTemps;


  /** Constructor Buit */
  public PeticioDeFirmaBean() {
  }

  /** Constructor amb tots els camps  */
  public PeticioDeFirmaBean(long peticioDeFirmaID , java.lang.String titol , java.lang.String descripcio , java.lang.String motiu , long fitxerAFirmarID , java.lang.Long fitxerAdaptatID , long tipusDocumentID , java.lang.String descripcioTipusDocument , int posicioTaulaFirmesID , java.sql.Timestamp dataSolicitud , java.sql.Timestamp dataFinal , java.sql.Timestamp dataCaducitat , int tipusFirmaID , int algorismeDeFirmaID , java.lang.Boolean modeDeFirma , int tipusEstatPeticioDeFirmaID , java.lang.String motiuDeRebuig , java.lang.String idiomaID , int prioritatID , long fluxDeFirmesID , java.lang.String usuariAplicacioID , java.lang.String remitentNom , java.lang.String remitentDescripcio , java.lang.String informacioAdicional , java.lang.Long logoSegellID , java.lang.Long custodiaInfoID , java.lang.String usuariEntitatID , boolean avisWeb , boolean segellatDeTemps) {
    this.peticioDeFirmaID=peticioDeFirmaID;
    this.titol=titol;
    this.descripcio=descripcio;
    this.motiu=motiu;
    this.fitxerAFirmarID=fitxerAFirmarID;
    this.fitxerAdaptatID=fitxerAdaptatID;
    this.tipusDocumentID=tipusDocumentID;
    this.descripcioTipusDocument=descripcioTipusDocument;
    this.posicioTaulaFirmesID=posicioTaulaFirmesID;
    this.dataSolicitud=dataSolicitud;
    this.dataFinal=dataFinal;
    this.dataCaducitat=dataCaducitat;
    this.tipusFirmaID=tipusFirmaID;
    this.algorismeDeFirmaID=algorismeDeFirmaID;
    this.modeDeFirma=modeDeFirma;
    this.tipusEstatPeticioDeFirmaID=tipusEstatPeticioDeFirmaID;
    this.motiuDeRebuig=motiuDeRebuig;
    this.idiomaID=idiomaID;
    this.prioritatID=prioritatID;
    this.fluxDeFirmesID=fluxDeFirmesID;
    this.usuariAplicacioID=usuariAplicacioID;
    this.remitentNom=remitentNom;
    this.remitentDescripcio=remitentDescripcio;
    this.informacioAdicional=informacioAdicional;
    this.logoSegellID=logoSegellID;
    this.custodiaInfoID=custodiaInfoID;
    this.usuariEntitatID=usuariEntitatID;
    this.avisWeb=avisWeb;
    this.segellatDeTemps=segellatDeTemps;
}
  /** Constructor sense valors autoincrementals */
  public PeticioDeFirmaBean(java.lang.String titol , java.lang.String descripcio , java.lang.String motiu , long fitxerAFirmarID , java.lang.Long fitxerAdaptatID , long tipusDocumentID , java.lang.String descripcioTipusDocument , int posicioTaulaFirmesID , java.sql.Timestamp dataSolicitud , java.sql.Timestamp dataFinal , java.sql.Timestamp dataCaducitat , int tipusFirmaID , int algorismeDeFirmaID , java.lang.Boolean modeDeFirma , int tipusEstatPeticioDeFirmaID , java.lang.String motiuDeRebuig , java.lang.String idiomaID , int prioritatID , long fluxDeFirmesID , java.lang.String usuariAplicacioID , java.lang.String remitentNom , java.lang.String remitentDescripcio , java.lang.String informacioAdicional , java.lang.Long logoSegellID , java.lang.Long custodiaInfoID , java.lang.String usuariEntitatID , boolean avisWeb , boolean segellatDeTemps) {
    this.titol=titol;
    this.descripcio=descripcio;
    this.motiu=motiu;
    this.fitxerAFirmarID=fitxerAFirmarID;
    this.fitxerAdaptatID=fitxerAdaptatID;
    this.tipusDocumentID=tipusDocumentID;
    this.descripcioTipusDocument=descripcioTipusDocument;
    this.posicioTaulaFirmesID=posicioTaulaFirmesID;
    this.dataSolicitud=dataSolicitud;
    this.dataFinal=dataFinal;
    this.dataCaducitat=dataCaducitat;
    this.tipusFirmaID=tipusFirmaID;
    this.algorismeDeFirmaID=algorismeDeFirmaID;
    this.modeDeFirma=modeDeFirma;
    this.tipusEstatPeticioDeFirmaID=tipusEstatPeticioDeFirmaID;
    this.motiuDeRebuig=motiuDeRebuig;
    this.idiomaID=idiomaID;
    this.prioritatID=prioritatID;
    this.fluxDeFirmesID=fluxDeFirmesID;
    this.usuariAplicacioID=usuariAplicacioID;
    this.remitentNom=remitentNom;
    this.remitentDescripcio=remitentDescripcio;
    this.informacioAdicional=informacioAdicional;
    this.logoSegellID=logoSegellID;
    this.custodiaInfoID=custodiaInfoID;
    this.usuariEntitatID=usuariEntitatID;
    this.avisWeb=avisWeb;
    this.segellatDeTemps=segellatDeTemps;
}
  /** Constructor dels valors Not Null */
  public PeticioDeFirmaBean(long peticioDeFirmaID , java.lang.String titol , java.lang.String motiu , long fitxerAFirmarID , long tipusDocumentID , int posicioTaulaFirmesID , java.sql.Timestamp dataCaducitat , int tipusFirmaID , int algorismeDeFirmaID , java.lang.Boolean modeDeFirma , int tipusEstatPeticioDeFirmaID , java.lang.String idiomaID , int prioritatID , long fluxDeFirmesID , java.lang.String usuariAplicacioID , java.lang.String remitentNom , boolean avisWeb , boolean segellatDeTemps) {
    this.peticioDeFirmaID=peticioDeFirmaID;
    this.titol=titol;
    this.motiu=motiu;
    this.fitxerAFirmarID=fitxerAFirmarID;
    this.tipusDocumentID=tipusDocumentID;
    this.posicioTaulaFirmesID=posicioTaulaFirmesID;
    this.dataCaducitat=dataCaducitat;
    this.tipusFirmaID=tipusFirmaID;
    this.algorismeDeFirmaID=algorismeDeFirmaID;
    this.modeDeFirma=modeDeFirma;
    this.tipusEstatPeticioDeFirmaID=tipusEstatPeticioDeFirmaID;
    this.idiomaID=idiomaID;
    this.prioritatID=prioritatID;
    this.fluxDeFirmesID=fluxDeFirmesID;
    this.usuariAplicacioID=usuariAplicacioID;
    this.remitentNom=remitentNom;
    this.avisWeb=avisWeb;
    this.segellatDeTemps=segellatDeTemps;
}
  public PeticioDeFirmaBean(PeticioDeFirma __bean) {
    this.setPeticioDeFirmaID(__bean.getPeticioDeFirmaID());
    this.setTitol(__bean.getTitol());
    this.setDescripcio(__bean.getDescripcio());
    this.setMotiu(__bean.getMotiu());
    this.setFitxerAFirmarID(__bean.getFitxerAFirmarID());
    this.setFitxerAdaptatID(__bean.getFitxerAdaptatID());
    this.setTipusDocumentID(__bean.getTipusDocumentID());
    this.setDescripcioTipusDocument(__bean.getDescripcioTipusDocument());
    this.setPosicioTaulaFirmesID(__bean.getPosicioTaulaFirmesID());
    this.setDataSolicitud(__bean.getDataSolicitud());
    this.setDataFinal(__bean.getDataFinal());
    this.setDataCaducitat(__bean.getDataCaducitat());
    this.setTipusFirmaID(__bean.getTipusFirmaID());
    this.setAlgorismeDeFirmaID(__bean.getAlgorismeDeFirmaID());
    this.setModeDeFirma(__bean.getModeDeFirma());
    this.setTipusEstatPeticioDeFirmaID(__bean.getTipusEstatPeticioDeFirmaID());
    this.setMotiuDeRebuig(__bean.getMotiuDeRebuig());
    this.setIdiomaID(__bean.getIdiomaID());
    this.setPrioritatID(__bean.getPrioritatID());
    this.setFluxDeFirmesID(__bean.getFluxDeFirmesID());
    this.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    this.setRemitentNom(__bean.getRemitentNom());
    this.setRemitentDescripcio(__bean.getRemitentDescripcio());
    this.setInformacioAdicional(__bean.getInformacioAdicional());
    this.setLogoSegellID(__bean.getLogoSegellID());
    this.setCustodiaInfoID(__bean.getCustodiaInfoID());
    this.setUsuariEntitatID(__bean.getUsuariEntitatID());
    this.setAvisWeb(__bean.isAvisWeb());
    this.setSegellatDeTemps(__bean.isSegellatDeTemps());
    // Fitxer
    this.setFitxerAFirmar(FitxerBean.toBean(__bean.getFitxerAFirmar()));
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

	public long getFitxerAFirmarID() {
		return(fitxerAFirmarID);
	};
	public void setFitxerAFirmarID(long _fitxerAFirmarID_) {
		this.fitxerAFirmarID = _fitxerAFirmarID_;
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

	public int getPosicioTaulaFirmesID() {
		return(posicioTaulaFirmesID);
	};
	public void setPosicioTaulaFirmesID(int _posicioTaulaFirmesID_) {
		this.posicioTaulaFirmesID = _posicioTaulaFirmesID_;
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

	public java.lang.String getUsuariAplicacioID() {
		return(usuariAplicacioID);
	};
	public void setUsuariAplicacioID(java.lang.String _usuariAplicacioID_) {
		this.usuariAplicacioID = _usuariAplicacioID_;
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

	public java.lang.String getInformacioAdicional() {
		return(informacioAdicional);
	};
	public void setInformacioAdicional(java.lang.String _informacioAdicional_) {
		this.informacioAdicional = _informacioAdicional_;
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

	public java.lang.String getUsuariEntitatID() {
		return(usuariEntitatID);
	};
	public void setUsuariEntitatID(java.lang.String _usuariEntitatID_) {
		this.usuariEntitatID = _usuariEntitatID_;
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



  // ======================================

  public static PeticioDeFirmaBean toBean(PeticioDeFirma __bean) {
    if (__bean == null) { return null;}
    PeticioDeFirmaBean __tmp = new PeticioDeFirmaBean();
    __tmp.setPeticioDeFirmaID(__bean.getPeticioDeFirmaID());
    __tmp.setTitol(__bean.getTitol());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setMotiu(__bean.getMotiu());
    __tmp.setFitxerAFirmarID(__bean.getFitxerAFirmarID());
    __tmp.setFitxerAdaptatID(__bean.getFitxerAdaptatID());
    __tmp.setTipusDocumentID(__bean.getTipusDocumentID());
    __tmp.setDescripcioTipusDocument(__bean.getDescripcioTipusDocument());
    __tmp.setPosicioTaulaFirmesID(__bean.getPosicioTaulaFirmesID());
    __tmp.setDataSolicitud(__bean.getDataSolicitud());
    __tmp.setDataFinal(__bean.getDataFinal());
    __tmp.setDataCaducitat(__bean.getDataCaducitat());
    __tmp.setTipusFirmaID(__bean.getTipusFirmaID());
    __tmp.setAlgorismeDeFirmaID(__bean.getAlgorismeDeFirmaID());
    __tmp.setModeDeFirma(__bean.getModeDeFirma());
    __tmp.setTipusEstatPeticioDeFirmaID(__bean.getTipusEstatPeticioDeFirmaID());
    __tmp.setMotiuDeRebuig(__bean.getMotiuDeRebuig());
    __tmp.setIdiomaID(__bean.getIdiomaID());
    __tmp.setPrioritatID(__bean.getPrioritatID());
    __tmp.setFluxDeFirmesID(__bean.getFluxDeFirmesID());
    __tmp.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    __tmp.setRemitentNom(__bean.getRemitentNom());
    __tmp.setRemitentDescripcio(__bean.getRemitentDescripcio());
    __tmp.setInformacioAdicional(__bean.getInformacioAdicional());
    __tmp.setLogoSegellID(__bean.getLogoSegellID());
    __tmp.setCustodiaInfoID(__bean.getCustodiaInfoID());
    __tmp.setUsuariEntitatID(__bean.getUsuariEntitatID());
    __tmp.setAvisWeb(__bean.isAvisWeb());
    __tmp.setSegellatDeTemps(__bean.isSegellatDeTemps());
    // Fitxer
    __tmp.setFitxerAFirmar(FitxerBean.toBean(__bean.getFitxerAFirmar()));
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
