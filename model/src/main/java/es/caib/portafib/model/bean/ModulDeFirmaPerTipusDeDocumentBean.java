
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.ModulDeFirmaPerTipusDeDocument;


public class ModulDeFirmaPerTipusDeDocumentBean implements ModulDeFirmaPerTipusDeDocument {



	long ID;// PK
	java.lang.String nom;
	long tipusDocumentID;
	long pluginID;


  /** Constructor Buit */
  public ModulDeFirmaPerTipusDeDocumentBean() {
  }

  /** Constructor amb tots els camps  */
  public ModulDeFirmaPerTipusDeDocumentBean(long ID , java.lang.String nom , long tipusDocumentID , long pluginID) {
    this.ID=ID;
    this.nom=nom;
    this.tipusDocumentID=tipusDocumentID;
    this.pluginID=pluginID;
}
  /** Constructor sense valors autoincrementals */
  public ModulDeFirmaPerTipusDeDocumentBean(java.lang.String nom , long tipusDocumentID , long pluginID) {
    this.nom=nom;
    this.tipusDocumentID=tipusDocumentID;
    this.pluginID=pluginID;
}
  public ModulDeFirmaPerTipusDeDocumentBean(ModulDeFirmaPerTipusDeDocument __bean) {
    this.setID(__bean.getID());
    this.setNom(__bean.getNom());
    this.setTipusDocumentID(__bean.getTipusDocumentID());
    this.setPluginID(__bean.getPluginID());
	}

	public long getID() {
		return(ID);
	};
	public void setID(long _ID_) {
		this.ID = _ID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
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



  // ======================================

  public static ModulDeFirmaPerTipusDeDocumentBean toBean(ModulDeFirmaPerTipusDeDocument __bean) {
    if (__bean == null) { return null;}
    ModulDeFirmaPerTipusDeDocumentBean __tmp = new ModulDeFirmaPerTipusDeDocumentBean();
    __tmp.setID(__bean.getID());
    __tmp.setNom(__bean.getNom());
    __tmp.setTipusDocumentID(__bean.getTipusDocumentID());
    __tmp.setPluginID(__bean.getPluginID());
		return __tmp;
	}



}
