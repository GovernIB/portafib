package es.caib.portafib.model.entity;

public interface PeticioDeFirma extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getPeticioDeFirmaID();
	public void setPeticioDeFirmaID(long _peticioDeFirmaID_);

	public java.lang.String getTitol();
	public void setTitol(java.lang.String _titol_);

	public java.lang.String getDescripcio();
	public void setDescripcio(java.lang.String _descripcio_);

	public java.lang.String getMotiu();
	public void setMotiu(java.lang.String _motiu_);

	public java.lang.Long getFitxerAFirmarID();
	public void setFitxerAFirmarID(java.lang.Long _fitxerAFirmarID_);

	public java.lang.Long getFirmaOriginalDetachedID();
	public void setFirmaOriginalDetachedID(java.lang.Long _firmaOriginalDetachedID_);

	public java.lang.Long getFitxerAdaptatID();
	public void setFitxerAdaptatID(java.lang.Long _fitxerAdaptatID_);

	public long getTipusDocumentID();
	public void setTipusDocumentID(long _tipusDocumentID_);

	public java.lang.String getDescripcioTipusDocument();
	public void setDescripcioTipusDocument(java.lang.String _descripcioTipusDocument_);

	public java.sql.Timestamp getDataSolicitud();
	public void setDataSolicitud(java.sql.Timestamp _dataSolicitud_);

	public java.sql.Timestamp getDataFinal();
	public void setDataFinal(java.sql.Timestamp _dataFinal_);

	public java.sql.Timestamp getDataCaducitat();
	public void setDataCaducitat(java.sql.Timestamp _dataCaducitat_);

	public int getTipusOperacioFirma();
	public void setTipusOperacioFirma(int _tipusOperacioFirma_);

	public int getTipusFirmaID();
	public void setTipusFirmaID(int _tipusFirmaID_);

	public int getAlgorismeDeFirmaID();
	public void setAlgorismeDeFirmaID(int _algorismeDeFirmaID_);

	public java.lang.Boolean getModeDeFirma();
	public void setModeDeFirma(java.lang.Boolean _modeDeFirma_);

	public int getPosicioTaulaFirmesID();
	public void setPosicioTaulaFirmesID(int _posicioTaulaFirmesID_);

	public int getTipusEstatPeticioDeFirmaID();
	public void setTipusEstatPeticioDeFirmaID(int _tipusEstatPeticioDeFirmaID_);

	public java.lang.String getMotiuDeRebuig();
	public void setMotiuDeRebuig(java.lang.String _motiuDeRebuig_);

	public java.lang.String getIdiomaID();
	public void setIdiomaID(java.lang.String _idiomaID_);

	public int getPrioritatID();
	public void setPrioritatID(int _prioritatID_);

	public long getFluxDeFirmesID();
	public void setFluxDeFirmesID(long _fluxDeFirmesID_);

	public java.lang.String getSolicitantUsuariAplicacioID();
	public void setSolicitantUsuariAplicacioID(java.lang.String _solicitantUsuariAplicacioID_);

	public java.lang.String getRemitentNom();
	public void setRemitentNom(java.lang.String _remitentNom_);

	public java.lang.String getRemitentDescripcio();
	public void setRemitentDescripcio(java.lang.String _remitentDescripcio_);

	public java.lang.String getExpedientCodi();
	public void setExpedientCodi(java.lang.String _expedientCodi_);

	public java.lang.String getExpedientNom();
	public void setExpedientNom(java.lang.String _expedientNom_);

	public java.lang.String getExpedientUrl();
	public void setExpedientUrl(java.lang.String _expedientUrl_);

	public java.lang.String getProcedimentCodi();
	public void setProcedimentCodi(java.lang.String _procedimentCodi_);

	public java.lang.String getProcedimentNom();
	public void setProcedimentNom(java.lang.String _procedimentNom_);

	public java.lang.String getInformacioAddicional();
	public void setInformacioAddicional(java.lang.String _informacioAddicional_);

	public java.lang.Double getInformacioAddicionalAvaluable();
	public void setInformacioAddicionalAvaluable(java.lang.Double _informacioAddicionalAvaluable_);

	public java.lang.Long getLogoSegellID();
	public void setLogoSegellID(java.lang.Long _logoSegellID_);

	public java.lang.Long getCustodiaInfoID();
	public void setCustodiaInfoID(java.lang.Long _custodiaInfoID_);

	public java.lang.String getSolicitantUsuariEntitat1ID();
	public void setSolicitantUsuariEntitat1ID(java.lang.String _solicitantUsuariEntitat1ID_);

	public java.lang.String getSolicitantUsuariEntitat2ID();
	public void setSolicitantUsuariEntitat2ID(java.lang.String _solicitantUsuariEntitat2ID_);

	public java.lang.String getSolicitantUsuariEntitat3ID();
	public void setSolicitantUsuariEntitat3ID(java.lang.String _solicitantUsuariEntitat3ID_);

	public boolean isAvisWeb();
	public void setAvisWeb(boolean _avisWeb_);

	public boolean isSegellatDeTemps();
	public void setSegellatDeTemps(boolean _segellatDeTemps_);

	public int getOrigenPeticioDeFirma();
	public void setOrigenPeticioDeFirma(int _origenPeticioDeFirma_);

	public java.lang.Long getConfiguracioDeFirmaID();
	public void setConfiguracioDeFirmaID(java.lang.Long _configuracioDeFirmaID_);

  // Fitxer
  public <F extends Fitxer> F getFitxerAFirmar();
  // Fitxer
  public <F extends Fitxer> F getFirmaOriginalDetached();
  // Fitxer
  public <F extends Fitxer> F getFitxerAdaptat();
  // Fitxer
  public <F extends Fitxer> F getLogoSegell();


  // ======================================

}
