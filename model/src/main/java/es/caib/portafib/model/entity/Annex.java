package es.caib.portafib.model.entity;

public interface Annex extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getAnnexID();
	public void setAnnexID(long _annexID_);

	public long getPeticioDeFirmaID();
	public void setPeticioDeFirmaID(long _peticioDeFirmaID_);

	public long getFitxerID();
	public void setFitxerID(long _fitxerID_);

	public boolean isAdjuntar();
	public void setAdjuntar(boolean _adjuntar_);

	public boolean isFirmar();
	public void setFirmar(boolean _firmar_);

  // Fitxer
  public <F extends Fitxer> F getFitxer();


  // ======================================

}
