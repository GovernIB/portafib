
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.PluginFirmaWebPerUsuariEntitat;


public class PluginFirmaWebPerUsuariEntitatBean implements PluginFirmaWebPerUsuariEntitat {



	long pluginFirmaWebPerUsrEntID;// PK
	java.lang.String usuariEntitatID;
	long pluginFirmaWebID;
	int accio;


  /** Constructor Buit */
  public PluginFirmaWebPerUsuariEntitatBean() {
  }

  /** Constructor amb tots els camps  */
  public PluginFirmaWebPerUsuariEntitatBean(long pluginFirmaWebPerUsrEntID , java.lang.String usuariEntitatID , long pluginFirmaWebID , int accio) {
    this.pluginFirmaWebPerUsrEntID=pluginFirmaWebPerUsrEntID;
    this.usuariEntitatID=usuariEntitatID;
    this.pluginFirmaWebID=pluginFirmaWebID;
    this.accio=accio;
}
  /** Constructor sense valors autoincrementals */
  public PluginFirmaWebPerUsuariEntitatBean(java.lang.String usuariEntitatID , long pluginFirmaWebID , int accio) {
    this.usuariEntitatID=usuariEntitatID;
    this.pluginFirmaWebID=pluginFirmaWebID;
    this.accio=accio;
}
  public PluginFirmaWebPerUsuariEntitatBean(PluginFirmaWebPerUsuariEntitat __bean) {
    this.setPluginFirmaWebPerUsrEntID(__bean.getPluginFirmaWebPerUsrEntID());
    this.setUsuariEntitatID(__bean.getUsuariEntitatID());
    this.setPluginFirmaWebID(__bean.getPluginFirmaWebID());
    this.setAccio(__bean.getAccio());
	}

	public long getPluginFirmaWebPerUsrEntID() {
		return(pluginFirmaWebPerUsrEntID);
	};
	public void setPluginFirmaWebPerUsrEntID(long _pluginFirmaWebPerUsrEntID_) {
		this.pluginFirmaWebPerUsrEntID = _pluginFirmaWebPerUsrEntID_;
	};

	public java.lang.String getUsuariEntitatID() {
		return(usuariEntitatID);
	};
	public void setUsuariEntitatID(java.lang.String _usuariEntitatID_) {
		this.usuariEntitatID = _usuariEntitatID_;
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

  public static PluginFirmaWebPerUsuariEntitatBean toBean(PluginFirmaWebPerUsuariEntitat __bean) {
    if (__bean == null) { return null;}
    PluginFirmaWebPerUsuariEntitatBean __tmp = new PluginFirmaWebPerUsuariEntitatBean();
    __tmp.setPluginFirmaWebPerUsrEntID(__bean.getPluginFirmaWebPerUsrEntID());
    __tmp.setUsuariEntitatID(__bean.getUsuariEntitatID());
    __tmp.setPluginFirmaWebID(__bean.getPluginFirmaWebID());
    __tmp.setAccio(__bean.getAccio());
		return __tmp;
	}



}
