
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
@Table(name = "pfi_pluginfirmawebperusrapp"  , uniqueConstraints = {
            @UniqueConstraint(name="pfi_pfwpua_usuapp_plug_uk", columnNames={"usuariaplicacioid","pluginfirmawebid"}) } )
@SequenceGenerator(name="PLUGINFIRMAWEBPERUSUARIAPLICACIO_SEQ", sequenceName="pfi_pluginfirmawebperusrapp_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class PluginFirmaWebPerUsuariAplicacioJPA implements PluginFirmaWebPerUsuariAplicacio {



private static final long serialVersionUID = -844814954L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PLUGINFIRMAWEBPERUSUARIAPLICACIO_SEQ")
    @Index(name="pfi_pfwpua_pk_i")
    @Column(name="pluginfirmawebperusrappid",nullable = false,length = 19)
    long pluginfirmawebperusrappid;

    @Index(name="pfi_pfwpua_usrappid_fk_i")
    @Column(name="usuariaplicacioid",nullable = false,length = 101)
    java.lang.String usuariAplicacioID;

    @Index(name="pfi_pfwpua_plugin_fk_i")
    @Column(name="pluginfirmawebid",nullable = false,length = 19)
    long pluginFirmaWebID;

  /** Valors:  -1 eliminar, 1 afegir */
    @Column(name="accio",nullable = false,length = 10)
    int accio;



  /** Constructor Buit */
  public PluginFirmaWebPerUsuariAplicacioJPA() {
  }

  /** Constructor amb tots els camps  */
  public PluginFirmaWebPerUsuariAplicacioJPA(long pluginfirmawebperusrappid , java.lang.String usuariAplicacioID , long pluginFirmaWebID , int accio) {
    this.pluginfirmawebperusrappid=pluginfirmawebperusrappid;
    this.usuariAplicacioID=usuariAplicacioID;
    this.pluginFirmaWebID=pluginFirmaWebID;
    this.accio=accio;
}
  /** Constructor sense valors autoincrementals */
  public PluginFirmaWebPerUsuariAplicacioJPA(java.lang.String usuariAplicacioID , long pluginFirmaWebID , int accio) {
    this.usuariAplicacioID=usuariAplicacioID;
    this.pluginFirmaWebID=pluginFirmaWebID;
    this.accio=accio;
}
  public PluginFirmaWebPerUsuariAplicacioJPA(PluginFirmaWebPerUsuariAplicacio __bean) {
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



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof PluginFirmaWebPerUsuariAplicacio) {
      PluginFirmaWebPerUsuariAplicacio __instance = (PluginFirmaWebPerUsuariAplicacio)__obj;
      __result = true;
      __result = __result && (this.getPluginfirmawebperusrappid() == __instance.getPluginfirmawebperusrappid()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:usuariaplicacioid | Table: pfi_usuariaplicacio | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @ForeignKey(name="pfi_pfwpua_usrapp_fk")
    @JoinColumn(name = "usuariaplicacioid", referencedColumnName ="usuariAplicacioID", nullable = false, insertable=false, updatable=false)
    private UsuariAplicacioJPA usuariAplicacio;

    public UsuariAplicacioJPA getUsuariAplicacio() {
    return this.usuariAplicacio;
  }

    public  void setUsuariAplicacio(UsuariAplicacioJPA usuariAplicacio) {
    this.usuariAplicacio = usuariAplicacio;
  }

// IMP Field:pluginid | Table: pfi_plugin | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @ForeignKey(name="pfi_pfwpua_plugin_fk")
    @JoinColumn(name = "pluginfirmawebid", referencedColumnName ="pluginID", nullable = false, insertable=false, updatable=false)
    private PluginJPA plugin;

    public PluginJPA getPlugin() {
    return this.plugin;
  }

    public  void setPlugin(PluginJPA plugin) {
    this.plugin = plugin;
  }


 // ---------------  STATIC METHODS ------------------
  public static PluginFirmaWebPerUsuariAplicacioJPA toJPA(PluginFirmaWebPerUsuariAplicacio __bean) {
    if (__bean == null) { return null;}
    PluginFirmaWebPerUsuariAplicacioJPA __tmp = new PluginFirmaWebPerUsuariAplicacioJPA();
    __tmp.setPluginfirmawebperusrappid(__bean.getPluginfirmawebperusrappid());
    __tmp.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    __tmp.setPluginFirmaWebID(__bean.getPluginFirmaWebID());
    __tmp.setAccio(__bean.getAccio());
		return __tmp;
	}


  public static PluginFirmaWebPerUsuariAplicacioJPA copyJPA(PluginFirmaWebPerUsuariAplicacioJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<PluginFirmaWebPerUsuariAplicacioJPA> copyJPA(java.util.Set<PluginFirmaWebPerUsuariAplicacioJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    @SuppressWarnings("unchecked")
    java.util.Set<PluginFirmaWebPerUsuariAplicacioJPA> __tmpSet = (java.util.Set<PluginFirmaWebPerUsuariAplicacioJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<PluginFirmaWebPerUsuariAplicacioJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (PluginFirmaWebPerUsuariAplicacioJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static PluginFirmaWebPerUsuariAplicacioJPA copyJPA(PluginFirmaWebPerUsuariAplicacioJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    PluginFirmaWebPerUsuariAplicacioJPA __tmp = (PluginFirmaWebPerUsuariAplicacioJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"UsuariAplicacioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariAplicacio) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariAplicacio()) ) ) {
      __tmp.setUsuariAplicacio(UsuariAplicacioJPA.copyJPA(__jpa.getUsuariAplicacio(), __alreadyCopied,"PluginFirmaWebPerUsuariAplicacioJPA"));
    }
    if(!"PluginJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.plugin) || org.hibernate.Hibernate.isInitialized(__jpa.getPlugin()) ) ) {
      __tmp.setPlugin(PluginJPA.copyJPA(__jpa.getPlugin(), __alreadyCopied,"PluginFirmaWebPerUsuariAplicacioJPA"));
    }

    return __tmp;
  }




}
