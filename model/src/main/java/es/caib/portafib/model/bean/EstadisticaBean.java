
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.Estadistica;


public class EstadisticaBean implements Estadistica {



private static final long serialVersionUID = 360955862L;

	long estadisticaID;// PK
	int tipus;
	java.sql.Timestamp data;
	java.lang.String entitatID;
	java.lang.Double valor;
	java.lang.String parametres;


  /** Constructor Buit */
  public EstadisticaBean() {
  }

  /** Constructor amb tots els camps  */
  public EstadisticaBean(long estadisticaID , int tipus , java.sql.Timestamp data , java.lang.String entitatID , java.lang.Double valor , java.lang.String parametres) {
    this.estadisticaID=estadisticaID;
    this.tipus=tipus;
    this.data=data;
    this.entitatID=entitatID;
    this.valor=valor;
    this.parametres=parametres;
}
  /** Constructor sense valors autoincrementals */
  public EstadisticaBean(int tipus , java.sql.Timestamp data , java.lang.String entitatID , java.lang.Double valor , java.lang.String parametres) {
    this.tipus=tipus;
    this.data=data;
    this.entitatID=entitatID;
    this.valor=valor;
    this.parametres=parametres;
}
  /** Constructor dels valors Not Null */
  public EstadisticaBean(long estadisticaID , int tipus , java.sql.Timestamp data) {
    this.estadisticaID=estadisticaID;
    this.tipus=tipus;
    this.data=data;
}
  public EstadisticaBean(Estadistica __bean) {
    this.setEstadisticaID(__bean.getEstadisticaID());
    this.setTipus(__bean.getTipus());
    this.setData(__bean.getData());
    this.setEntitatID(__bean.getEntitatID());
    this.setValor(__bean.getValor());
    this.setParametres(__bean.getParametres());
	}

	public long getEstadisticaID() {
		return(estadisticaID);
	};
	public void setEstadisticaID(long _estadisticaID_) {
		this.estadisticaID = _estadisticaID_;
	};

	public int getTipus() {
		return(tipus);
	};
	public void setTipus(int _tipus_) {
		this.tipus = _tipus_;
	};

	public java.sql.Timestamp getData() {
		return(data);
	};
	public void setData(java.sql.Timestamp _data_) {
		this.data = _data_;
	};

	public java.lang.String getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(java.lang.String _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public java.lang.Double getValor() {
		return(valor);
	};
	public void setValor(java.lang.Double _valor_) {
		this.valor = _valor_;
	};

	public java.lang.String getParametres() {
		return(parametres);
	};
	public void setParametres(java.lang.String _parametres_) {
		this.parametres = _parametres_;
	};



  // ======================================

  public static EstadisticaBean toBean(Estadistica __bean) {
    if (__bean == null) { return null;}
    EstadisticaBean __tmp = new EstadisticaBean();
    __tmp.setEstadisticaID(__bean.getEstadisticaID());
    __tmp.setTipus(__bean.getTipus());
    __tmp.setData(__bean.getData());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setValor(__bean.getValor());
    __tmp.setParametres(__bean.getParametres());
		return __tmp;
	}



}
