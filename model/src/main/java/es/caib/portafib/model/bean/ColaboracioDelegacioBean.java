
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.ColaboracioDelegacio;


public class ColaboracioDelegacioBean implements ColaboracioDelegacio {



	long colaboracioDelegacioID;// PK
	java.lang.String destinatariID;
	java.lang.String colaboradorDelegatID;
	boolean esDelegat;
	java.lang.String motiu;
	java.lang.String descripcio;
	java.sql.Timestamp dataInici;
	java.sql.Timestamp dataFi;
	boolean activa;
	boolean revisor;
	java.lang.String motiuDeshabilitada;
	java.lang.Long fitxerAutoritzacioID;


  /** Constructor Buit */
  public ColaboracioDelegacioBean() {
  }

  /** Constructor amb tots els camps  */
  public ColaboracioDelegacioBean(long colaboracioDelegacioID , java.lang.String destinatariID , java.lang.String colaboradorDelegatID , boolean esDelegat , java.lang.String motiu , java.lang.String descripcio , java.sql.Timestamp dataInici , java.sql.Timestamp dataFi , boolean activa , boolean revisor , java.lang.String motiuDeshabilitada , java.lang.Long fitxerAutoritzacioID) {
    this.colaboracioDelegacioID=colaboracioDelegacioID;
    this.destinatariID=destinatariID;
    this.colaboradorDelegatID=colaboradorDelegatID;
    this.esDelegat=esDelegat;
    this.motiu=motiu;
    this.descripcio=descripcio;
    this.dataInici=dataInici;
    this.dataFi=dataFi;
    this.activa=activa;
    this.revisor=revisor;
    this.motiuDeshabilitada=motiuDeshabilitada;
    this.fitxerAutoritzacioID=fitxerAutoritzacioID;
}
  /** Constructor sense valors autoincrementals */
  public ColaboracioDelegacioBean(java.lang.String destinatariID , java.lang.String colaboradorDelegatID , boolean esDelegat , java.lang.String motiu , java.lang.String descripcio , java.sql.Timestamp dataInici , java.sql.Timestamp dataFi , boolean activa , boolean revisor , java.lang.String motiuDeshabilitada , java.lang.Long fitxerAutoritzacioID) {
    this.destinatariID=destinatariID;
    this.colaboradorDelegatID=colaboradorDelegatID;
    this.esDelegat=esDelegat;
    this.motiu=motiu;
    this.descripcio=descripcio;
    this.dataInici=dataInici;
    this.dataFi=dataFi;
    this.activa=activa;
    this.revisor=revisor;
    this.motiuDeshabilitada=motiuDeshabilitada;
    this.fitxerAutoritzacioID=fitxerAutoritzacioID;
}
  /** Constructor dels valors Not Null */
  public ColaboracioDelegacioBean(long colaboracioDelegacioID , java.lang.String destinatariID , java.lang.String colaboradorDelegatID , boolean esDelegat , java.lang.String motiu , java.sql.Timestamp dataInici , boolean activa , boolean revisor) {
    this.colaboracioDelegacioID=colaboracioDelegacioID;
    this.destinatariID=destinatariID;
    this.colaboradorDelegatID=colaboradorDelegatID;
    this.esDelegat=esDelegat;
    this.motiu=motiu;
    this.dataInici=dataInici;
    this.activa=activa;
    this.revisor=revisor;
}
  public ColaboracioDelegacioBean(ColaboracioDelegacio __bean) {
    this.setColaboracioDelegacioID(__bean.getColaboracioDelegacioID());
    this.setDestinatariID(__bean.getDestinatariID());
    this.setColaboradorDelegatID(__bean.getColaboradorDelegatID());
    this.setEsDelegat(__bean.isEsDelegat());
    this.setMotiu(__bean.getMotiu());
    this.setDescripcio(__bean.getDescripcio());
    this.setDataInici(__bean.getDataInici());
    this.setDataFi(__bean.getDataFi());
    this.setActiva(__bean.isActiva());
    this.setRevisor(__bean.isRevisor());
    this.setMotiuDeshabilitada(__bean.getMotiuDeshabilitada());
    this.setFitxerAutoritzacioID(__bean.getFitxerAutoritzacioID());
    // Fitxer
    this.setFitxerAutoritzacio(FitxerBean.toBean(__bean.getFitxerAutoritzacio()));
	}

	public long getColaboracioDelegacioID() {
		return(colaboracioDelegacioID);
	};
	public void setColaboracioDelegacioID(long _colaboracioDelegacioID_) {
		this.colaboracioDelegacioID = _colaboracioDelegacioID_;
	};

	public java.lang.String getDestinatariID() {
		return(destinatariID);
	};
	public void setDestinatariID(java.lang.String _destinatariID_) {
		this.destinatariID = _destinatariID_;
	};

	public java.lang.String getColaboradorDelegatID() {
		return(colaboradorDelegatID);
	};
	public void setColaboradorDelegatID(java.lang.String _colaboradorDelegatID_) {
		this.colaboradorDelegatID = _colaboradorDelegatID_;
	};

	public boolean isEsDelegat() {
		return(esDelegat);
	};
	public void setEsDelegat(boolean _esDelegat_) {
		this.esDelegat = _esDelegat_;
	};

	public java.lang.String getMotiu() {
		return(motiu);
	};
	public void setMotiu(java.lang.String _motiu_) {
		this.motiu = _motiu_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};

	public java.sql.Timestamp getDataInici() {
		return(dataInici);
	};
	public void setDataInici(java.sql.Timestamp _dataInici_) {
		this.dataInici = _dataInici_;
	};

	public java.sql.Timestamp getDataFi() {
		return(dataFi);
	};
	public void setDataFi(java.sql.Timestamp _dataFi_) {
		this.dataFi = _dataFi_;
	};

	public boolean isActiva() {
		return(activa);
	};
	public void setActiva(boolean _activa_) {
		this.activa = _activa_;
	};

	public boolean isRevisor() {
		return(revisor);
	};
	public void setRevisor(boolean _revisor_) {
		this.revisor = _revisor_;
	};

	public java.lang.String getMotiuDeshabilitada() {
		return(motiuDeshabilitada);
	};
	public void setMotiuDeshabilitada(java.lang.String _motiuDeshabilitada_) {
		this.motiuDeshabilitada = _motiuDeshabilitada_;
	};

	public java.lang.Long getFitxerAutoritzacioID() {
		return(fitxerAutoritzacioID);
	};
	public void setFitxerAutoritzacioID(java.lang.Long _fitxerAutoritzacioID_) {
		this.fitxerAutoritzacioID = _fitxerAutoritzacioID_;
	};



  // ======================================

  public static ColaboracioDelegacioBean toBean(ColaboracioDelegacio __bean) {
    if (__bean == null) { return null;}
    ColaboracioDelegacioBean __tmp = new ColaboracioDelegacioBean();
    __tmp.setColaboracioDelegacioID(__bean.getColaboracioDelegacioID());
    __tmp.setDestinatariID(__bean.getDestinatariID());
    __tmp.setColaboradorDelegatID(__bean.getColaboradorDelegatID());
    __tmp.setEsDelegat(__bean.isEsDelegat());
    __tmp.setMotiu(__bean.getMotiu());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setDataInici(__bean.getDataInici());
    __tmp.setDataFi(__bean.getDataFi());
    __tmp.setActiva(__bean.isActiva());
    __tmp.setRevisor(__bean.isRevisor());
    __tmp.setMotiuDeshabilitada(__bean.getMotiuDeshabilitada());
    __tmp.setFitxerAutoritzacioID(__bean.getFitxerAutoritzacioID());
    // Fitxer
    __tmp.setFitxerAutoritzacio(FitxerBean.toBean(__bean.getFitxerAutoritzacio()));
		return __tmp;
	}

  protected FitxerBean fitxerAutoritzacio;
  public FitxerBean getFitxerAutoritzacio() {
    return fitxerAutoritzacio;
  }
  public void setFitxerAutoritzacio(FitxerBean __field) {
    this. fitxerAutoritzacio = __field;
  }


}
