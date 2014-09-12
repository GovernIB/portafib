package es.caib.portafib.model.entity;

public interface Firma extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getFirmaID();
	public void setFirmaID(long _firmaID_);

	public java.lang.String getDestinatariID();
	public void setDestinatariID(java.lang.String _destinatariID_);

	public long getBlocDeFirmaID();
	public void setBlocDeFirmaID(long _blocDeFirmaID_);

	public boolean isObligatori();
	public void setObligatori(boolean _obligatori_);

	public java.lang.Long getFitxerFirmatID();
	public void setFitxerFirmatID(java.lang.Long _fitxerFirmatID_);

	public java.lang.Integer getNumFirmaDocument();
	public void setNumFirmaDocument(java.lang.Integer _numFirmaDocument_);

	public int getCaixaPagina();
	public void setCaixaPagina(int _caixaPagina_);

	public java.lang.Integer getCaixaX();
	public void setCaixaX(java.lang.Integer _caixaX_);

	public java.lang.Integer getCaixaY();
	public void setCaixaY(java.lang.Integer _caixaY_);

	public java.lang.Integer getCaixaAmple();
	public void setCaixaAmple(java.lang.Integer _caixaAmple_);

	public java.lang.Integer getCaixaAlt();
	public void setCaixaAlt(java.lang.Integer _caixaAlt_);

	public java.math.BigInteger getNumeroSerieCertificat();
	public void setNumeroSerieCertificat(java.math.BigInteger _numeroSerieCertificat_);

	public java.lang.String getEmissorCertificat();
	public void setEmissorCertificat(java.lang.String _emissorCertificat_);

	public java.lang.String getNomCertificat();
	public void setNomCertificat(java.lang.String _nomCertificat_);

	public java.lang.Long getTipusEstatDeFirmaFinalID();
	public void setTipusEstatDeFirmaFinalID(java.lang.Long _tipusEstatDeFirmaFinalID_);

	public boolean isMostrarRubrica();
	public void setMostrarRubrica(boolean _mostrarRubrica_);

  // Fitxer
  public <F extends Fitxer> F getFitxerFirmat();


  // ======================================

}
