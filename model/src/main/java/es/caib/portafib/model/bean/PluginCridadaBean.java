
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.PluginCridada;


public class PluginCridadaBean implements PluginCridada {



private static final long serialVersionUID = 1378032401L;

	long pluginCridadaID;// PK
	java.lang.String entitatID;
	java.sql.Timestamp data;
	int tipusPlugin;
	java.lang.String dadesPlugin;
	java.lang.String metodePlugin;
	java.lang.String dadesCridada;
	int tipusTesultat;
	java.lang.String resultat;
	long tempsExecucio;


  /** Constructor Buit */
  public PluginCridadaBean() {
  }

  /** Constructor amb tots els camps  */
  public PluginCridadaBean(long pluginCridadaID , java.lang.String entitatID , java.sql.Timestamp data , int tipusPlugin , java.lang.String dadesPlugin , java.lang.String metodePlugin , java.lang.String dadesCridada , int tipusTesultat , java.lang.String resultat , long tempsExecucio) {
    this.pluginCridadaID=pluginCridadaID;
    this.entitatID=entitatID;
    this.data=data;
    this.tipusPlugin=tipusPlugin;
    this.dadesPlugin=dadesPlugin;
    this.metodePlugin=metodePlugin;
    this.dadesCridada=dadesCridada;
    this.tipusTesultat=tipusTesultat;
    this.resultat=resultat;
    this.tempsExecucio=tempsExecucio;
}
  /** Constructor sense valors autoincrementals */
  public PluginCridadaBean(java.lang.String entitatID , java.sql.Timestamp data , int tipusPlugin , java.lang.String dadesPlugin , java.lang.String metodePlugin , java.lang.String dadesCridada , int tipusTesultat , java.lang.String resultat , long tempsExecucio) {
    this.entitatID=entitatID;
    this.data=data;
    this.tipusPlugin=tipusPlugin;
    this.dadesPlugin=dadesPlugin;
    this.metodePlugin=metodePlugin;
    this.dadesCridada=dadesCridada;
    this.tipusTesultat=tipusTesultat;
    this.resultat=resultat;
    this.tempsExecucio=tempsExecucio;
}
  /** Constructor dels valors Not Null */
  public PluginCridadaBean(long pluginCridadaID , java.sql.Timestamp data , int tipusPlugin , java.lang.String metodePlugin , java.lang.String dadesCridada , int tipusTesultat , java.lang.String resultat , long tempsExecucio) {
    this.pluginCridadaID=pluginCridadaID;
    this.data=data;
    this.tipusPlugin=tipusPlugin;
    this.metodePlugin=metodePlugin;
    this.dadesCridada=dadesCridada;
    this.tipusTesultat=tipusTesultat;
    this.resultat=resultat;
    this.tempsExecucio=tempsExecucio;
}
  public PluginCridadaBean(PluginCridada __bean) {
    this.setPluginCridadaID(__bean.getPluginCridadaID());
    this.setEntitatID(__bean.getEntitatID());
    this.setData(__bean.getData());
    this.setTipusPlugin(__bean.getTipusPlugin());
    this.setDadesPlugin(__bean.getDadesPlugin());
    this.setMetodePlugin(__bean.getMetodePlugin());
    this.setDadesCridada(__bean.getDadesCridada());
    this.setTipusTesultat(__bean.getTipusTesultat());
    this.setResultat(__bean.getResultat());
    this.setTempsExecucio(__bean.getTempsExecucio());
	}

	public long getPluginCridadaID() {
		return(pluginCridadaID);
	};
	public void setPluginCridadaID(long _pluginCridadaID_) {
		this.pluginCridadaID = _pluginCridadaID_;
	};

	public java.lang.String getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(java.lang.String _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public java.sql.Timestamp getData() {
		return(data);
	};
	public void setData(java.sql.Timestamp _data_) {
		this.data = _data_;
	};

	public int getTipusPlugin() {
		return(tipusPlugin);
	};
	public void setTipusPlugin(int _tipusPlugin_) {
		this.tipusPlugin = _tipusPlugin_;
	};

	public java.lang.String getDadesPlugin() {
		return(dadesPlugin);
	};
	public void setDadesPlugin(java.lang.String _dadesPlugin_) {
		this.dadesPlugin = _dadesPlugin_;
	};

	public java.lang.String getMetodePlugin() {
		return(metodePlugin);
	};
	public void setMetodePlugin(java.lang.String _metodePlugin_) {
		this.metodePlugin = _metodePlugin_;
	};

	public java.lang.String getDadesCridada() {
		return(dadesCridada);
	};
	public void setDadesCridada(java.lang.String _dadesCridada_) {
		this.dadesCridada = _dadesCridada_;
	};

	public int getTipusTesultat() {
		return(tipusTesultat);
	};
	public void setTipusTesultat(int _tipusTesultat_) {
		this.tipusTesultat = _tipusTesultat_;
	};

	public java.lang.String getResultat() {
		return(resultat);
	};
	public void setResultat(java.lang.String _resultat_) {
		this.resultat = _resultat_;
	};

	public long getTempsExecucio() {
		return(tempsExecucio);
	};
	public void setTempsExecucio(long _tempsExecucio_) {
		this.tempsExecucio = _tempsExecucio_;
	};



  // ======================================

  public static PluginCridadaBean toBean(PluginCridada __bean) {
    if (__bean == null) { return null;}
    PluginCridadaBean __tmp = new PluginCridadaBean();
    __tmp.setPluginCridadaID(__bean.getPluginCridadaID());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setData(__bean.getData());
    __tmp.setTipusPlugin(__bean.getTipusPlugin());
    __tmp.setDadesPlugin(__bean.getDadesPlugin());
    __tmp.setMetodePlugin(__bean.getMetodePlugin());
    __tmp.setDadesCridada(__bean.getDadesCridada());
    __tmp.setTipusTesultat(__bean.getTipusTesultat());
    __tmp.setResultat(__bean.getResultat());
    __tmp.setTempsExecucio(__bean.getTempsExecucio());
		return __tmp;
	}



}
