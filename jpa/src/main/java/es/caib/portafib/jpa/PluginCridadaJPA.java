
package es.caib.portafib.jpa;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import org.hibernate.annotations.Index;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.GeneratedValue;


@Entity
@Table(name = "pfi_plugincridada" )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
public class PluginCridadaJPA implements PluginCridada {



private static final long serialVersionUID = -1618108326L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PORTAFIB_SEQ")
	@Index(name="pfi_plugincridada_pk_i")
	@Column(name="plugincridadaid",nullable = false,length = 19)
	long pluginCridadaID;

	@Index(name="pfi_plugcrida_entitatid_fk_i")
	@Column(name="entitatid",length = 50)
	java.lang.String entitatID;

	@Column(name="data",nullable = false,length = 35,precision = 6)
	java.sql.Timestamp data;

	@Column(name="tipusplugin",nullable = false,length = 10)
	int tipusPlugin;

	@Column(name="dadesplugin",length = 255)
	java.lang.String dadesPlugin;

	@Column(name="metodeplugin",nullable = false,length = 100)
	java.lang.String metodePlugin;

	@Column(name="dadescridada",nullable = false,length = 3000)
	java.lang.String dadesCridada;

  /** 0 => error, 1 => ok */
	@Column(name="tipusresultat",nullable = false,length = 10)
	int tipusTesultat;

  /** conte error si falla i dades resultat si va bé. */
	@Column(name="resultat",nullable = false,length = 2147483647)
  @Lob
	java.lang.String resultat;

  /** milisegons execucio */
	@Column(name="tempsexecucio",nullable = false,length = 19)
	long tempsExecucio;



  /** Constructor Buit */
  public PluginCridadaJPA() {
  }

  /** Constructor amb tots els camps  */
  public PluginCridadaJPA(long pluginCridadaID , java.lang.String entitatID , java.sql.Timestamp data , int tipusPlugin , java.lang.String dadesPlugin , java.lang.String metodePlugin , java.lang.String dadesCridada , int tipusTesultat , java.lang.String resultat , long tempsExecucio) {
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
  public PluginCridadaJPA(java.lang.String entitatID , java.sql.Timestamp data , int tipusPlugin , java.lang.String dadesPlugin , java.lang.String metodePlugin , java.lang.String dadesCridada , int tipusTesultat , java.lang.String resultat , long tempsExecucio) {
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
  public PluginCridadaJPA(long pluginCridadaID , java.sql.Timestamp data , int tipusPlugin , java.lang.String metodePlugin , java.lang.String dadesCridada , int tipusTesultat , java.lang.String resultat , long tempsExecucio) {
    this.pluginCridadaID=pluginCridadaID;
    this.data=data;
    this.tipusPlugin=tipusPlugin;
    this.metodePlugin=metodePlugin;
    this.dadesCridada=dadesCridada;
    this.tipusTesultat=tipusTesultat;
    this.resultat=resultat;
    this.tempsExecucio=tempsExecucio;
}
  public PluginCridadaJPA(PluginCridada __bean) {
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


 // ---------------  STATIC METHODS ------------------
  public static PluginCridadaJPA toJPA(PluginCridada __bean) {
    if (__bean == null) { return null;}
    PluginCridadaJPA __tmp = new PluginCridadaJPA();
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

    return __tmp;
  }




}
