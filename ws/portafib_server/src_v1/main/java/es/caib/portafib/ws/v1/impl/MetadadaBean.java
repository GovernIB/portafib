
package es.caib.portafib.ws.v1.impl;

import es.caib.portafib.model.entity.Metadada;

/**
 * IMPORTANT: Aquesta Classe no es pot modificar ja que forma part
 * de l'API V1.0. Només es pot modificar el mètode toBean() 
 * i el constructor a partir del Objecte del Mòdel
 * 
 * @author anadal
 *
 */
public class MetadadaBean  {


	long metadadaID;// PK
	java.lang.String nom;
	java.lang.String valor;
	java.lang.String descripcio;
	long peticioDeFirmaID;
	int tipusMetadadaID;


  /** Constructor Buit */
  public MetadadaBean() {
  }

  /** Constructor amb tots els camps  */
  public MetadadaBean(long metadadaID , java.lang.String nom , java.lang.String valor , java.lang.String descripcio , long peticioDeFirmaID , int tipusMetadadaID) {
    this.metadadaID=metadadaID;
    this.nom=nom;
    this.valor=valor;
    this.descripcio=descripcio;
    this.peticioDeFirmaID=peticioDeFirmaID;
    this.tipusMetadadaID=tipusMetadadaID;
}
  /** Constructor sense valors autoincrementals */
  public MetadadaBean(java.lang.String nom , java.lang.String valor , java.lang.String descripcio , long peticioDeFirmaID , int tipusMetadadaID) {
    this.nom=nom;
    this.valor=valor;
    this.descripcio=descripcio;
    this.peticioDeFirmaID=peticioDeFirmaID;
    this.tipusMetadadaID=tipusMetadadaID;
}
  public MetadadaBean(Metadada __bean) {
    this.setMetadadaID(__bean.getMetadadaID());
    this.setNom(__bean.getNom());
    this.setValor(__bean.getValor());
    this.setDescripcio(__bean.getDescripcio());
    this.setPeticioDeFirmaID(__bean.getPeticioDeFirmaID());
    this.setTipusMetadadaID(__bean.getTipusMetadadaID());
	}

	public long getMetadadaID() {
		return(metadadaID);
	};
	public void setMetadadaID(long _metadadaID_) {
		this.metadadaID = _metadadaID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getValor() {
		return(valor);
	};
	public void setValor(java.lang.String _valor_) {
		this.valor = _valor_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};

	public long getPeticioDeFirmaID() {
		return(peticioDeFirmaID);
	};
	public void setPeticioDeFirmaID(long _peticioDeFirmaID_) {
		this.peticioDeFirmaID = _peticioDeFirmaID_;
	};

	public int getTipusMetadadaID() {
		return(tipusMetadadaID);
	};
	public void setTipusMetadadaID(int _tipusMetadadaID_) {
		this.tipusMetadadaID = _tipusMetadadaID_;
	};



  // ======================================

  public static MetadadaBean toBean(Metadada __bean) {
    if (__bean == null) { return null;}
    MetadadaBean __tmp = new MetadadaBean();
    __tmp.setMetadadaID(__bean.getMetadadaID());
    __tmp.setNom(__bean.getNom());
    __tmp.setValor(__bean.getValor());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setPeticioDeFirmaID(__bean.getPeticioDeFirmaID());
    __tmp.setTipusMetadadaID(__bean.getTipusMetadadaID());
		return __tmp;
	}



}
