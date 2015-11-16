
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.ModulDeFirmaPerTipusDeDocument;


public class ModulDeFirmaPerTipusDeDocumentBean implements ModulDeFirmaPerTipusDeDocument {



private static final long serialVersionUID = 2083543313L;

	long ID;// PK
	long tipusDocumentID;
	long pluginID;
	java.lang.String nom;


  /** Constructor Buit */
  public ModulDeFirmaPerTipusDeDocumentBean() {
  }

  /** Constructor amb tots els camps  */
  public ModulDeFirmaPerTipusDeDocumentBean(long ID , long tipusDocumentID , long pluginID , java.lang.String nom) {
    this.ID=ID;
    this.tipusDocumentID=tipusDocumentID;
    this.pluginID=pluginID;
    this.nom=nom;
}
  /** Constructor sense valors autoincrementals */
  public ModulDeFirmaPerTipusDeDocumentBean(long tipusDocumentID , long pluginID , java.lang.String nom) {
    this.tipusDocumentID=tipusDocumentID;
    this.pluginID=pluginID;
    this.nom=nom;
}
  public ModulDeFirmaPerTipusDeDocumentBean(ModulDeFirmaPerTipusDeDocument __bean) {
    this.setID(__bean.getID());
    this.setTipusDocumentID(__bean.getTipusDocumentID());
    this.setPluginID(__bean.getPluginID());
    this.setNom(__bean.getNom());
	}

	public long getID() {
		return(ID);
	};
	public void setID(long _ID_) {
		this.ID = _ID_;
	};

	public long getTipusDocumentID() {
		return(tipusDocumentID);
	};
	public void setTipusDocumentID(long _tipusDocumentID_) {
		this.tipusDocumentID = _tipusDocumentID_;
	};

	public long getPluginID() {
		return(pluginID);
	};
	public void setPluginID(long _pluginID_) {
		this.pluginID = _pluginID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};



  // ======================================

  public static ModulDeFirmaPerTipusDeDocumentBean toBean(ModulDeFirmaPerTipusDeDocument __bean) {
    if (__bean == null) { return null;}
    ModulDeFirmaPerTipusDeDocumentBean __tmp = new ModulDeFirmaPerTipusDeDocumentBean();
    __tmp.setID(__bean.getID());
    __tmp.setTipusDocumentID(__bean.getTipusDocumentID());
    __tmp.setPluginID(__bean.getPluginID());
    __tmp.setNom(__bean.getNom());
		return __tmp;
	}



}
