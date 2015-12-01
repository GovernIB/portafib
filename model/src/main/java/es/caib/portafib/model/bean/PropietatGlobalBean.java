
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.PropietatGlobal;


public class PropietatGlobalBean implements PropietatGlobal {



private static final long serialVersionUID = 672541563L;

	long propietatGlobalID;// PK
	java.lang.String clau;
	java.lang.String valor;
	java.lang.String entitatID;
	java.lang.String descripcio;


  /** Constructor Buit */
  public PropietatGlobalBean() {
  }

  /** Constructor amb tots els camps  */
  public PropietatGlobalBean(long propietatGlobalID , java.lang.String clau , java.lang.String valor , java.lang.String entitatID , java.lang.String descripcio) {
    this.propietatGlobalID=propietatGlobalID;
    this.clau=clau;
    this.valor=valor;
    this.entitatID=entitatID;
    this.descripcio=descripcio;
}
  /** Constructor sense valors autoincrementals */
  public PropietatGlobalBean(java.lang.String clau , java.lang.String valor , java.lang.String entitatID , java.lang.String descripcio) {
    this.clau=clau;
    this.valor=valor;
    this.entitatID=entitatID;
    this.descripcio=descripcio;
}
  /** Constructor dels valors Not Null */
  public PropietatGlobalBean(long propietatGlobalID , java.lang.String clau) {
    this.propietatGlobalID=propietatGlobalID;
    this.clau=clau;
}
  public PropietatGlobalBean(PropietatGlobal __bean) {
    this.setPropietatGlobalID(__bean.getPropietatGlobalID());
    this.setClau(__bean.getClau());
    this.setValor(__bean.getValor());
    this.setEntitatID(__bean.getEntitatID());
    this.setDescripcio(__bean.getDescripcio());
	}

	public long getPropietatGlobalID() {
		return(propietatGlobalID);
	};
	public void setPropietatGlobalID(long _propietatGlobalID_) {
		this.propietatGlobalID = _propietatGlobalID_;
	};

	public java.lang.String getClau() {
		return(clau);
	};
	public void setClau(java.lang.String _clau_) {
		this.clau = _clau_;
	};

	public java.lang.String getValor() {
		return(valor);
	};
	public void setValor(java.lang.String _valor_) {
		this.valor = _valor_;
	};

	public java.lang.String getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(java.lang.String _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};



  // ======================================

  public static PropietatGlobalBean toBean(PropietatGlobal __bean) {
    if (__bean == null) { return null;}
    PropietatGlobalBean __tmp = new PropietatGlobalBean();
    __tmp.setPropietatGlobalID(__bean.getPropietatGlobalID());
    __tmp.setClau(__bean.getClau());
    __tmp.setValor(__bean.getValor());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setDescripcio(__bean.getDescripcio());
		return __tmp;
	}



}
