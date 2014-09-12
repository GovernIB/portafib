package es.caib.portafib.model.entity;

public interface UsuariPersona extends org.fundaciobit.genapp.common.IGenAppEntity {

	public java.lang.String getUsuariPersonaID();
	public void setUsuariPersonaID(java.lang.String _usuariPersonaID_);

	public java.lang.String getNom();
	public void setNom(java.lang.String _nom_);

	public java.lang.String getLlinatges();
	public void setLlinatges(java.lang.String _llinatges_);

	public java.lang.String getEmail();
	public void setEmail(java.lang.String _email_);

	public java.lang.String getNif();
	public void setNif(java.lang.String _nif_);

	public java.lang.String getIdiomaID();
	public void setIdiomaID(java.lang.String _idiomaID_);

	public java.lang.Long getRubricaID();
	public void setRubricaID(java.lang.Long _rubricaID_);

  // Fitxer
  public <F extends Fitxer> F getRubrica();


  // ======================================

}
