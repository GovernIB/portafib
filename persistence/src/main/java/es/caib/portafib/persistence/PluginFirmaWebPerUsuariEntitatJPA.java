
package es.caib.portafib.persistence;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.UniqueConstraint;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.Index;
import javax.persistence.SequenceGenerator;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.Id;


@SuppressWarnings("deprecation")
@Entity
@Table(name = "pfi_pluginfirmawebperusrent"  , uniqueConstraints = {
            @UniqueConstraint(name="pfi_pfwpue_usuent_plug_uk", columnNames={"usuarientitatid","pluginfirmawebid"}) } )
@SequenceGenerator(name="PLUGINFIRMAWEBPERUSUARIENTITAT_SEQ", sequenceName="pfi_pluginfirmawebperusrent_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class PluginFirmaWebPerUsuariEntitatJPA implements PluginFirmaWebPerUsuariEntitat {



private static final long serialVersionUID = 1338094348L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PLUGINFIRMAWEBPERUSUARIENTITAT_SEQ")
    @Index(name="pfi_pfwpue_pk_i")
    @Column(name="pluginfirmawebperusrentid",nullable = false,length = 19)
    long pluginFirmaWebPerUsrEntID;

    @Index(name="pfi_pfwpue_usrentid_fk_i")
    @Column(name="usuarientitatid",nullable = false,length = 101)
    java.lang.String usuariEntitatID;

    @Index(name="pfi_pfwpue_plugin_fk_i")
    @Column(name="pluginfirmawebid",nullable = false,length = 19)
    long pluginFirmaWebID;

  /** Valors:  -1 eliminar, 1 afegir */
    @Column(name="accio",nullable = false,length = 10)
    int accio;



  /** Constructor Buit */
  public PluginFirmaWebPerUsuariEntitatJPA() {
  }

  /** Constructor amb tots els camps  */
  public PluginFirmaWebPerUsuariEntitatJPA(long pluginFirmaWebPerUsrEntID , java.lang.String usuariEntitatID , long pluginFirmaWebID , int accio) {
    this.pluginFirmaWebPerUsrEntID=pluginFirmaWebPerUsrEntID;
    this.usuariEntitatID=usuariEntitatID;
    this.pluginFirmaWebID=pluginFirmaWebID;
    this.accio=accio;
}
  /** Constructor sense valors autoincrementals */
  public PluginFirmaWebPerUsuariEntitatJPA(java.lang.String usuariEntitatID , long pluginFirmaWebID , int accio) {
    this.usuariEntitatID=usuariEntitatID;
    this.pluginFirmaWebID=pluginFirmaWebID;
    this.accio=accio;
}
  public PluginFirmaWebPerUsuariEntitatJPA(PluginFirmaWebPerUsuariEntitat __bean) {
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



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof PluginFirmaWebPerUsuariEntitat) {
      PluginFirmaWebPerUsuariEntitat __instance = (PluginFirmaWebPerUsuariEntitat)__obj;
      __result = true;
      __result = __result && (this.getPluginFirmaWebPerUsrEntID() == __instance.getPluginFirmaWebPerUsrEntID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:usuarientitatid | Table: pfi_usuarientitat | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @ForeignKey(name="pfi_pfwpue_usrentitat_fk")
    @JoinColumn(name = "usuarientitatid", referencedColumnName ="usuariEntitatID", nullable = false, insertable=false, updatable=false)
    private UsuariEntitatJPA usuariEntitat;

    public UsuariEntitatJPA getUsuariEntitat() {
    return this.usuariEntitat;
  }

    public  void setUsuariEntitat(UsuariEntitatJPA usuariEntitat) {
    this.usuariEntitat = usuariEntitat;
  }

// IMP Field:pluginid | Table: pfi_plugin | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @ForeignKey(name="pfi_pfwpue_plugin_fk")
    @JoinColumn(name = "pluginfirmawebid", referencedColumnName ="pluginID", nullable = false, insertable=false, updatable=false)
    private PluginJPA plugin;

    public PluginJPA getPlugin() {
    return this.plugin;
  }

    public  void setPlugin(PluginJPA plugin) {
    this.plugin = plugin;
  }


 // ---------------  STATIC METHODS ------------------
  public static PluginFirmaWebPerUsuariEntitatJPA toJPA(PluginFirmaWebPerUsuariEntitat __bean) {
    if (__bean == null) { return null;}
    PluginFirmaWebPerUsuariEntitatJPA __tmp = new PluginFirmaWebPerUsuariEntitatJPA();
    __tmp.setPluginFirmaWebPerUsrEntID(__bean.getPluginFirmaWebPerUsrEntID());
    __tmp.setUsuariEntitatID(__bean.getUsuariEntitatID());
    __tmp.setPluginFirmaWebID(__bean.getPluginFirmaWebID());
    __tmp.setAccio(__bean.getAccio());
		return __tmp;
	}


  public static PluginFirmaWebPerUsuariEntitatJPA copyJPA(PluginFirmaWebPerUsuariEntitatJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<PluginFirmaWebPerUsuariEntitatJPA> copyJPA(java.util.Set<PluginFirmaWebPerUsuariEntitatJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<PluginFirmaWebPerUsuariEntitatJPA> __tmpSet = (java.util.Set<PluginFirmaWebPerUsuariEntitatJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<PluginFirmaWebPerUsuariEntitatJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (PluginFirmaWebPerUsuariEntitatJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static PluginFirmaWebPerUsuariEntitatJPA copyJPA(PluginFirmaWebPerUsuariEntitatJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    PluginFirmaWebPerUsuariEntitatJPA __tmp = (PluginFirmaWebPerUsuariEntitatJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"UsuariEntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariEntitat) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariEntitat()) ) ) {
      __tmp.setUsuariEntitat(UsuariEntitatJPA.copyJPA(__jpa.getUsuariEntitat(), __alreadyCopied,"PluginFirmaWebPerUsuariEntitatJPA"));
    }
    if(!"PluginJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.plugin) || org.hibernate.Hibernate.isInitialized(__jpa.getPlugin()) ) ) {
      __tmp.setPlugin(PluginJPA.copyJPA(__jpa.getPlugin(), __alreadyCopied,"PluginFirmaWebPerUsuariEntitatJPA"));
    }

    return __tmp;
  }




}
