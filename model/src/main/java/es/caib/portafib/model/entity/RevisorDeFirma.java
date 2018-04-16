package es.caib.portafib.model.entity;

public interface RevisorDeFirma extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getRevisorDeFirmaID();
	public void setRevisorDeFirmaID(long _revisorDeFirmaID_);

	public long getUsuariEntitatRevisorID();
	public void setUsuariEntitatRevisorID(long _usuariEntitatRevisorID_);

	public long getFirmaID();
	public void setFirmaID(long _firmaID_);

	public boolean isObligatori();
	public void setObligatori(boolean _obligatori_);



  // ======================================

}
