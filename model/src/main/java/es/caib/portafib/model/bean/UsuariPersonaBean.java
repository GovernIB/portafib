
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.UsuariPersona;


public class UsuariPersonaBean implements UsuariPersona {



private static final long serialVersionUID = 1803901243L;

	java.lang.String usuariPersonaID;// PK
	java.lang.String nom;
	java.lang.String llinatges;
	java.lang.String email;
	java.lang.String nif;
	java.lang.String idiomaID;
	java.lang.Long rubricaID;
	boolean usuariIntern;
	java.lang.String contrasenya;


  /** Constructor Buit */
  public UsuariPersonaBean() {
  }

  /** Constructor amb tots els camps  */
  public UsuariPersonaBean(java.lang.String usuariPersonaID , java.lang.String nom , java.lang.String llinatges , java.lang.String email , java.lang.String nif , java.lang.String idiomaID , java.lang.Long rubricaID , boolean usuariIntern , java.lang.String contrasenya) {
    this.usuariPersonaID=usuariPersonaID;
    this.nom=nom;
    this.llinatges=llinatges;
    this.email=email;
    this.nif=nif;
    this.idiomaID=idiomaID;
    this.rubricaID=rubricaID;
    this.usuariIntern=usuariIntern;
    this.contrasenya=contrasenya;
}
  /** Constructor dels valors Not Null */
  public UsuariPersonaBean(java.lang.String usuariPersonaID , java.lang.String nom , java.lang.String llinatges , java.lang.String email , java.lang.String nif , java.lang.String idiomaID , boolean usuariIntern) {
    this.usuariPersonaID=usuariPersonaID;
    this.nom=nom;
    this.llinatges=llinatges;
    this.email=email;
    this.nif=nif;
    this.idiomaID=idiomaID;
    this.usuariIntern=usuariIntern;
}
  public UsuariPersonaBean(UsuariPersona __bean) {
    this.setUsuariPersonaID(__bean.getUsuariPersonaID());
    this.setNom(__bean.getNom());
    this.setLlinatges(__bean.getLlinatges());
    this.setEmail(__bean.getEmail());
    this.setNif(__bean.getNif());
    this.setIdiomaID(__bean.getIdiomaID());
    this.setRubricaID(__bean.getRubricaID());
    this.setUsuariIntern(__bean.isUsuariIntern());
    this.setContrasenya(__bean.getContrasenya());
    // Fitxer
    this.setRubrica(FitxerBean.toBean(__bean.getRubrica()));
	}

	public java.lang.String getUsuariPersonaID() {
		return(usuariPersonaID);
	};
	public void setUsuariPersonaID(java.lang.String _usuariPersonaID_) {
		this.usuariPersonaID = _usuariPersonaID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getLlinatges() {
		return(llinatges);
	};
	public void setLlinatges(java.lang.String _llinatges_) {
		this.llinatges = _llinatges_;
	};

	public java.lang.String getEmail() {
		return(email);
	};
	public void setEmail(java.lang.String _email_) {
		this.email = _email_;
	};

	public java.lang.String getNif() {
		return(nif);
	};
	public void setNif(java.lang.String _nif_) {
		this.nif = _nif_;
	};

	public java.lang.String getIdiomaID() {
		return(idiomaID);
	};
	public void setIdiomaID(java.lang.String _idiomaID_) {
		this.idiomaID = _idiomaID_;
	};

	public java.lang.Long getRubricaID() {
		return(rubricaID);
	};
	public void setRubricaID(java.lang.Long _rubricaID_) {
		this.rubricaID = _rubricaID_;
	};

	public boolean isUsuariIntern() {
		return(usuariIntern);
	};
	public void setUsuariIntern(boolean _usuariIntern_) {
		this.usuariIntern = _usuariIntern_;
	};

	public java.lang.String getContrasenya() {
		return(contrasenya);
	};
	public void setContrasenya(java.lang.String _contrasenya_) {
		this.contrasenya = _contrasenya_;
	};



  // ======================================

  public static UsuariPersonaBean toBean(UsuariPersona __bean) {
    if (__bean == null) { return null;}
    UsuariPersonaBean __tmp = new UsuariPersonaBean();
    __tmp.setUsuariPersonaID(__bean.getUsuariPersonaID());
    __tmp.setNom(__bean.getNom());
    __tmp.setLlinatges(__bean.getLlinatges());
    __tmp.setEmail(__bean.getEmail());
    __tmp.setNif(__bean.getNif());
    __tmp.setIdiomaID(__bean.getIdiomaID());
    __tmp.setRubricaID(__bean.getRubricaID());
    __tmp.setUsuariIntern(__bean.isUsuariIntern());
    __tmp.setContrasenya(__bean.getContrasenya());
    // Fitxer
    __tmp.setRubrica(FitxerBean.toBean(__bean.getRubrica()));
		return __tmp;
	}

  protected FitxerBean rubrica;
  public FitxerBean getRubrica() {
    return rubrica;
  }
  public void setRubrica(FitxerBean __field) {
    this. rubrica = __field;
  }


}
