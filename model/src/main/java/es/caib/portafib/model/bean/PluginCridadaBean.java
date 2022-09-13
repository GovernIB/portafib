
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.PluginCridada;


public class PluginCridadaBean implements PluginCridada {



	long pluginCridadaID;// PK
	java.lang.String entitatID;
	java.sql.Timestamp data;
	long pluginID;
	java.lang.String metodePlugin;
	java.lang.String parametresText;
	java.lang.Long parametresFitxerID;
	java.lang.String retornText;
	java.lang.Long retornFitxerID;
	int tipusTesultat;
	long tempsExecucio;


  /** Constructor Buit */
  public PluginCridadaBean() {
  }

  /** Constructor amb tots els camps  */
  public PluginCridadaBean(long pluginCridadaID , java.lang.String entitatID , java.sql.Timestamp data , long pluginID , java.lang.String metodePlugin , java.lang.String parametresText , java.lang.Long parametresFitxerID , java.lang.String retornText , java.lang.Long retornFitxerID , int tipusTesultat , long tempsExecucio) {
    this.pluginCridadaID=pluginCridadaID;
    this.entitatID=entitatID;
    this.data=data;
    this.pluginID=pluginID;
    this.metodePlugin=metodePlugin;
    this.parametresText=parametresText;
    this.parametresFitxerID=parametresFitxerID;
    this.retornText=retornText;
    this.retornFitxerID=retornFitxerID;
    this.tipusTesultat=tipusTesultat;
    this.tempsExecucio=tempsExecucio;
}
  /** Constructor sense valors autoincrementals */
  public PluginCridadaBean(java.lang.String entitatID , java.sql.Timestamp data , long pluginID , java.lang.String metodePlugin , java.lang.String parametresText , java.lang.Long parametresFitxerID , java.lang.String retornText , java.lang.Long retornFitxerID , int tipusTesultat , long tempsExecucio) {
    this.entitatID=entitatID;
    this.data=data;
    this.pluginID=pluginID;
    this.metodePlugin=metodePlugin;
    this.parametresText=parametresText;
    this.parametresFitxerID=parametresFitxerID;
    this.retornText=retornText;
    this.retornFitxerID=retornFitxerID;
    this.tipusTesultat=tipusTesultat;
    this.tempsExecucio=tempsExecucio;
}
  /** Constructor dels valors Not Null */
  public PluginCridadaBean(long pluginCridadaID , java.sql.Timestamp data , long pluginID , java.lang.String metodePlugin , int tipusTesultat , long tempsExecucio) {
    this.pluginCridadaID=pluginCridadaID;
    this.data=data;
    this.pluginID=pluginID;
    this.metodePlugin=metodePlugin;
    this.tipusTesultat=tipusTesultat;
    this.tempsExecucio=tempsExecucio;
}
  public PluginCridadaBean(PluginCridada __bean) {
    this.setPluginCridadaID(__bean.getPluginCridadaID());
    this.setEntitatID(__bean.getEntitatID());
    this.setData(__bean.getData());
    this.setPluginID(__bean.getPluginID());
    this.setMetodePlugin(__bean.getMetodePlugin());
    this.setParametresText(__bean.getParametresText());
    this.setParametresFitxerID(__bean.getParametresFitxerID());
    this.setRetornText(__bean.getRetornText());
    this.setRetornFitxerID(__bean.getRetornFitxerID());
    this.setTipusTesultat(__bean.getTipusTesultat());
    this.setTempsExecucio(__bean.getTempsExecucio());
    // Fitxer
    this.setParametresFitxer(FitxerBean.toBean(__bean.getParametresFitxer()));
    // Fitxer
    this.setRetornFitxer(FitxerBean.toBean(__bean.getRetornFitxer()));
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

	public long getPluginID() {
		return(pluginID);
	};
	public void setPluginID(long _pluginID_) {
		this.pluginID = _pluginID_;
	};

	public java.lang.String getMetodePlugin() {
		return(metodePlugin);
	};
	public void setMetodePlugin(java.lang.String _metodePlugin_) {
		this.metodePlugin = _metodePlugin_;
	};

	public java.lang.String getParametresText() {
		return(parametresText);
	};
	public void setParametresText(java.lang.String _parametresText_) {
		this.parametresText = _parametresText_;
	};

	public java.lang.Long getParametresFitxerID() {
		return(parametresFitxerID);
	};
	public void setParametresFitxerID(java.lang.Long _parametresFitxerID_) {
		this.parametresFitxerID = _parametresFitxerID_;
	};

	public java.lang.String getRetornText() {
		return(retornText);
	};
	public void setRetornText(java.lang.String _retornText_) {
		this.retornText = _retornText_;
	};

	public java.lang.Long getRetornFitxerID() {
		return(retornFitxerID);
	};
	public void setRetornFitxerID(java.lang.Long _retornFitxerID_) {
		this.retornFitxerID = _retornFitxerID_;
	};

	public int getTipusTesultat() {
		return(tipusTesultat);
	};
	public void setTipusTesultat(int _tipusTesultat_) {
		this.tipusTesultat = _tipusTesultat_;
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
    __tmp.setPluginID(__bean.getPluginID());
    __tmp.setMetodePlugin(__bean.getMetodePlugin());
    __tmp.setParametresText(__bean.getParametresText());
    __tmp.setParametresFitxerID(__bean.getParametresFitxerID());
    __tmp.setRetornText(__bean.getRetornText());
    __tmp.setRetornFitxerID(__bean.getRetornFitxerID());
    __tmp.setTipusTesultat(__bean.getTipusTesultat());
    __tmp.setTempsExecucio(__bean.getTempsExecucio());
    // Fitxer
    __tmp.setParametresFitxer(FitxerBean.toBean(__bean.getParametresFitxer()));
    // Fitxer
    __tmp.setRetornFitxer(FitxerBean.toBean(__bean.getRetornFitxer()));
		return __tmp;
	}

  protected FitxerBean parametresFitxer;
  public FitxerBean getParametresFitxer() {
    return parametresFitxer;
  }
  public void setParametresFitxer(FitxerBean __field) {
    this. parametresFitxer = __field;
  }
  protected FitxerBean retornFitxer;
  public FitxerBean getRetornFitxer() {
    return retornFitxer;
  }
  public void setRetornFitxer(FitxerBean __field) {
    this. retornFitxer = __field;
  }


}
