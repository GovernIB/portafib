
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.PluginFirmaWebPerUsuariAplicacio;


public class PluginFirmaWebPerUsuariAplicacioBean implements PluginFirmaWebPerUsuariAplicacio {



	long pluginfirmawebperusrappid;// PK
	java.lang.String usuariAplicacioID;
	long pluginFirmaWebID;
	int accio;


  /** Constructor Buit */
  public PluginFirmaWebPerUsuariAplicacioBean() {
  }

  /** Constructor amb tots els camps  */
  public PluginFirmaWebPerUsuariAplicacioBean(long pluginfirmawebperusrappid , java.lang.String usuariAplicacioID , long pluginFirmaWebID , int accio) {
    this.pluginfirmawebperusrappid=pluginfirmawebperusrappid;
    this.usuariAplicacioID=usuariAplicacioID;
    this.pluginFirmaWebID=pluginFirmaWebID;
    this.accio=accio;
}
  /** Constructor sense valors autoincrementals */
  public PluginFirmaWebPerUsuariAplicacioBean(java.lang.String usuariAplicacioID , long pluginFirmaWebID , int accio) {
    this.usuariAplicacioID=usuariAplicacioID;
    this.pluginFirmaWebID=pluginFirmaWebID;
    this.accio=accio;
}
  public PluginFirmaWebPerUsuariAplicacioBean(PluginFirmaWebPerUsuariAplicacio __bean) {
    this.setPluginfirmawebperusrappid(__bean.getPluginfirmawebperusrappid());
    this.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    this.setPluginFirmaWebID(__bean.getPluginFirmaWebID());
    this.setAccio(__bean.getAccio());
	}

	public long getPluginfirmawebperusrappid() {
		return(pluginfirmawebperusrappid);
	};
	public void setPluginfirmawebperusrappid(long _pluginfirmawebperusrappid_) {
		this.pluginfirmawebperusrappid = _pluginfirmawebperusrappid_;
	};

	public java.lang.String getUsuariAplicacioID() {
		return(usuariAplicacioID);
	};
	public void setUsuariAplicacioID(java.lang.String _usuariAplicacioID_) {
		this.usuariAplicacioID = _usuariAplicacioID_;
	};

	public long getPluginFirmaWebID() {
		return(pluginFirmaWebID);
	};
	public void setPluginFirmaWebID(long _pluginFirmaWebID_) {
		this.pluginFirmaWebID = _pluginFirmaWebID_;
	};

	public int getAccio() {
		return(accio);
	};
	public void setAccio(int _accio_) {
		this.accio = _accio_;
	};



  // ======================================

  public static PluginFirmaWebPerUsuariAplicacioBean toBean(PluginFirmaWebPerUsuariAplicacio __bean) {
    if (__bean == null) { return null;}
    PluginFirmaWebPerUsuariAplicacioBean __tmp = new PluginFirmaWebPerUsuariAplicacioBean();
    __tmp.setPluginfirmawebperusrappid(__bean.getPluginfirmawebperusrappid());
    __tmp.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    __tmp.setPluginFirmaWebID(__bean.getPluginFirmaWebID());
    __tmp.setAccio(__bean.getAccio());
		return __tmp;
	}



}
