package es.caib.portafib.model.entity;

public interface AnnexFirmat extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getAnnexfirmatID();
	public void setAnnexfirmatID(long _annexfirmatID_);

	public long getFitxerID();
	public void setFitxerID(long _fitxerID_);

	public long getAnnexID();
	public void setAnnexID(long _annexID_);

	public long getFirmaID();
	public void setFirmaID(long _firmaID_);

  // Fitxer
  public <F extends Fitxer> F getFitxer();


  // ======================================

}
