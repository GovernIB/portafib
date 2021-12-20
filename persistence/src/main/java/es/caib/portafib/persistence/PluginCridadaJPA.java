
package es.caib.portafib.persistence;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.Index;
import javax.persistence.SequenceGenerator;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import org.hibernate.annotations.Type;
import javax.persistence.Id;


@Entity
@Table(name = "pfi_plugincridada" )
@SequenceGenerator(name="PLUGINCRIDADA_SEQ", sequenceName="pfi_plugincridada_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class PluginCridadaJPA implements PluginCridada {



private static final long serialVersionUID = -1618108326L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PLUGINCRIDADA_SEQ")
    @Index(name="pfi_plugincridada_pk_i")
    @Column(name="plugincridadaid",nullable = false,length = 19)
    long pluginCridadaID;

    @Index(name="pfi_plugcrida_entitatid_fk_i")
    @Column(name="entitatid",length = 50)
    java.lang.String entitatID;

    @Column(name="data",nullable = false,length = 35,precision = 6)
    java.sql.Timestamp data;

    @Index(name="pfi_plugcrida_pluginid_fk_i")
    @Column(name="pluginid",nullable = false,length = 19)
    long pluginID;

    @Column(name="metodeplugin",nullable = false,length = 100)
    java.lang.String metodePlugin;

    @Column(name="parametrestext",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String parametresText;

    @Index(name="pfi_plugcrida_paramfitxer_fk_i")
    @Column(name="parametresfitxerid",length = 19)
    java.lang.Long parametresFitxerID;

  /** conte error si falla i dades resultat si va bé. */
    @Column(name="retorntext",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String retornText;

    @Index(name="pfi_plugcrida_retorfitxer_fk_i")
    @Column(name="retornfitxerid",length = 19)
    java.lang.Long retornFitxerID;

  /** 0 => error, 1 => ok */
    @Column(name="tipusresultat",nullable = false,length = 10)
    int tipusTesultat;

  /** milisegons execucio */
    @Column(name="tempsexecucio",nullable = false,length = 19)
    long tempsExecucio;



  /** Constructor Buit */
  public PluginCridadaJPA() {
  }

  /** Constructor amb tots els camps  */
  public PluginCridadaJPA(long pluginCridadaID , java.lang.String entitatID , java.sql.Timestamp data , long pluginID , java.lang.String metodePlugin , java.lang.String parametresText , java.lang.Long parametresFitxerID , java.lang.String retornText , java.lang.Long retornFitxerID , int tipusTesultat , long tempsExecucio) {
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
  public PluginCridadaJPA(java.lang.String entitatID , java.sql.Timestamp data , long pluginID , java.lang.String metodePlugin , java.lang.String parametresText , java.lang.Long parametresFitxerID , java.lang.String retornText , java.lang.Long retornFitxerID , int tipusTesultat , long tempsExecucio) {
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
  public PluginCridadaJPA(long pluginCridadaID , java.sql.Timestamp data , long pluginID , java.lang.String metodePlugin , int tipusTesultat , long tempsExecucio) {
    this.pluginCridadaID=pluginCridadaID;
    this.data=data;
    this.pluginID=pluginID;
    this.metodePlugin=metodePlugin;
    this.tipusTesultat=tipusTesultat;
    this.tempsExecucio=tempsExecucio;
}
  public PluginCridadaJPA(PluginCridada __bean) {
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
    this.setParametresFitxer(FitxerJPA.toJPA(__bean.getParametresFitxer()));
    // Fitxer
    this.setRetornFitxer(FitxerJPA.toJPA(__bean.getRetornFitxer()));
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



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof PluginCridada) {
      PluginCridada __instance = (PluginCridada)__obj;
      __result = true;
      __result = __result && (this.getPluginCridadaID() == __instance.getPluginCridadaID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:entitatid | Table: pfi_entitat | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @ForeignKey(name="pfi_plugcrida_entitat_fk")
    @JoinColumn(name = "entitatid", referencedColumnName ="entitatID", nullable = true, insertable=false, updatable=false)
    private EntitatJPA entitat;

    public EntitatJPA getEntitat() {
    return this.entitat;
  }

    public  void setEntitat(EntitatJPA entitat) {
    this.entitat = entitat;
  }

// IMP Field:pluginid | Table: pfi_plugin | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @ForeignKey(name="pfi_plugcrida_plugin_fk")
    @JoinColumn(name = "pluginid", referencedColumnName ="pluginID", nullable = false, insertable=false, updatable=false)
    private PluginJPA plugin;

    public PluginJPA getPlugin() {
    return this.plugin;
  }

    public  void setPlugin(PluginJPA plugin) {
    this.plugin = plugin;
  }

// IMP Field:fitxerid | Table: pfi_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @ForeignKey(name="pfi_plugcrida_fitxer_param_fk")
    @JoinColumn(name = "parametresfitxerid", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false)
    private FitxerJPA parametresFitxer;

    public FitxerJPA getParametresFitxer() {
    return this.parametresFitxer;
  }

    public  void setParametresFitxer(FitxerJPA parametresFitxer) {
    this.parametresFitxer = parametresFitxer;
  }

// IMP Field:fitxerid | Table: pfi_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @ForeignKey(name="pfi_plugcrida_fitxer_retor_fk")
    @JoinColumn(name = "retornfitxerid", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false)
    private FitxerJPA retornFitxer;

    public FitxerJPA getRetornFitxer() {
    return this.retornFitxer;
  }

    public  void setRetornFitxer(FitxerJPA retornFitxer) {
    this.retornFitxer = retornFitxer;
  }


 // ---------------  STATIC METHODS ------------------
  public static PluginCridadaJPA toJPA(PluginCridada __bean) {
    if (__bean == null) { return null;}
    PluginCridadaJPA __tmp = new PluginCridadaJPA();
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
    __tmp.setParametresFitxer(FitxerJPA.toJPA(__bean.getParametresFitxer()));
    // Fitxer
    __tmp.setRetornFitxer(FitxerJPA.toJPA(__bean.getRetornFitxer()));
		return __tmp;
	}


  public static PluginCridadaJPA copyJPA(PluginCridadaJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<PluginCridadaJPA> copyJPA(java.util.Set<PluginCridadaJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<PluginCridadaJPA> __tmpSet = (java.util.Set<PluginCridadaJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<PluginCridadaJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (PluginCridadaJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static PluginCridadaJPA copyJPA(PluginCridadaJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    PluginCridadaJPA __tmp = (PluginCridadaJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"EntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat()) ) ) {
      __tmp.setEntitat(EntitatJPA.copyJPA(__jpa.getEntitat(), __alreadyCopied,"PluginCridadaJPA"));
    }
    if(!"PluginJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.plugin) || org.hibernate.Hibernate.isInitialized(__jpa.getPlugin()) ) ) {
      __tmp.setPlugin(PluginJPA.copyJPA(__jpa.getPlugin(), __alreadyCopied,"PluginCridadaJPA"));
    }

    return __tmp;
  }




}
