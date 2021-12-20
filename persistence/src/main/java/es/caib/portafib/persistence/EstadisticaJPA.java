
package es.caib.portafib.persistence;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.Index;
import javax.persistence.SequenceGenerator;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.Id;


@Entity
@Table(name = "pfi_estadistica" )
@SequenceGenerator(name="ESTADISTICA_SEQ", sequenceName="pfi_estadistica_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class EstadisticaJPA implements Estadistica {



private static final long serialVersionUID = -2066559243L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ESTADISTICA_SEQ")
    @Index(name="pfi_estadistica_pk_i")
    @Column(name="estadisticaid",nullable = false,length = 19)
    long estadisticaID;

    @Column(name="data",nullable = false,length = 29,precision = 6)
    java.sql.Timestamp data;

  /** Ha de ser combobox */
    @Column(name="tipus",nullable = false,length = 10)
    int tipus;

    @Index(name="pfi_estadistica_entitatid_fk_i")
    @Column(name="entitatid",length = 50)
    java.lang.String entitatID;

    @Column(name="valor",nullable = false,length = 17,precision = 17)
    java.lang.Double valor;

  /** No te la clau forània amb pfi_usuariaplicacio ja que si s'esborra l'usuari aplicació, haurien de quedar les estadistiques. */
    @Column(name="usuariaplicacioid",length = 101)
    java.lang.String usuariAplicacioID;

    @Column(name="usuarientitatid",length = 101)
    java.lang.String usuariEntitatID;

    @Column(name="parametres",length = 3000)
    java.lang.String parametres;



  /** Constructor Buit */
  public EstadisticaJPA() {
  }

  /** Constructor amb tots els camps  */
  public EstadisticaJPA(long estadisticaID , java.sql.Timestamp data , int tipus , java.lang.String entitatID , java.lang.Double valor , java.lang.String usuariAplicacioID , java.lang.String usuariEntitatID , java.lang.String parametres) {
    this.estadisticaID=estadisticaID;
    this.data=data;
    this.tipus=tipus;
    this.entitatID=entitatID;
    this.valor=valor;
    this.usuariAplicacioID=usuariAplicacioID;
    this.usuariEntitatID=usuariEntitatID;
    this.parametres=parametres;
}
  /** Constructor sense valors autoincrementals */
  public EstadisticaJPA(java.sql.Timestamp data , int tipus , java.lang.String entitatID , java.lang.Double valor , java.lang.String usuariAplicacioID , java.lang.String usuariEntitatID , java.lang.String parametres) {
    this.data=data;
    this.tipus=tipus;
    this.entitatID=entitatID;
    this.valor=valor;
    this.usuariAplicacioID=usuariAplicacioID;
    this.usuariEntitatID=usuariEntitatID;
    this.parametres=parametres;
}
  /** Constructor dels valors Not Null */
  public EstadisticaJPA(long estadisticaID , java.sql.Timestamp data , int tipus , java.lang.Double valor) {
    this.estadisticaID=estadisticaID;
    this.data=data;
    this.tipus=tipus;
    this.valor=valor;
}
  public EstadisticaJPA(Estadistica __bean) {
    this.setEstadisticaID(__bean.getEstadisticaID());
    this.setData(__bean.getData());
    this.setTipus(__bean.getTipus());
    this.setEntitatID(__bean.getEntitatID());
    this.setValor(__bean.getValor());
    this.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    this.setUsuariEntitatID(__bean.getUsuariEntitatID());
    this.setParametres(__bean.getParametres());
	}

	public long getEstadisticaID() {
		return(estadisticaID);
	};
	public void setEstadisticaID(long _estadisticaID_) {
		this.estadisticaID = _estadisticaID_;
	};

	public java.sql.Timestamp getData() {
		return(data);
	};
	public void setData(java.sql.Timestamp _data_) {
		this.data = _data_;
	};

	public int getTipus() {
		return(tipus);
	};
	public void setTipus(int _tipus_) {
		this.tipus = _tipus_;
	};

	public java.lang.String getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(java.lang.String _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public java.lang.Double getValor() {
		return(valor);
	};
	public void setValor(java.lang.Double _valor_) {
		this.valor = _valor_;
	};

	public java.lang.String getUsuariAplicacioID() {
		return(usuariAplicacioID);
	};
	public void setUsuariAplicacioID(java.lang.String _usuariAplicacioID_) {
		this.usuariAplicacioID = _usuariAplicacioID_;
	};

	public java.lang.String getUsuariEntitatID() {
		return(usuariEntitatID);
	};
	public void setUsuariEntitatID(java.lang.String _usuariEntitatID_) {
		this.usuariEntitatID = _usuariEntitatID_;
	};

	public java.lang.String getParametres() {
		return(parametres);
	};
	public void setParametres(java.lang.String _parametres_) {
		this.parametres = _parametres_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Estadistica) {
      Estadistica __instance = (Estadistica)__obj;
      __result = true;
      __result = __result && (this.getEstadisticaID() == __instance.getEstadisticaID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:entitatid | Table: pfi_entitat | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @ForeignKey(name="pfi_estadis_entitat_fk")
    @JoinColumn(name = "entitatid", referencedColumnName ="entitatID", nullable = true, insertable=false, updatable=false)
    private EntitatJPA entitat;

    public EntitatJPA getEntitat() {
    return this.entitat;
  }

    public  void setEntitat(EntitatJPA entitat) {
    this.entitat = entitat;
  }


 // ---------------  STATIC METHODS ------------------
  public static EstadisticaJPA toJPA(Estadistica __bean) {
    if (__bean == null) { return null;}
    EstadisticaJPA __tmp = new EstadisticaJPA();
    __tmp.setEstadisticaID(__bean.getEstadisticaID());
    __tmp.setData(__bean.getData());
    __tmp.setTipus(__bean.getTipus());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setValor(__bean.getValor());
    __tmp.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    __tmp.setUsuariEntitatID(__bean.getUsuariEntitatID());
    __tmp.setParametres(__bean.getParametres());
		return __tmp;
	}


  public static EstadisticaJPA copyJPA(EstadisticaJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<EstadisticaJPA> copyJPA(java.util.Set<EstadisticaJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<EstadisticaJPA> __tmpSet = (java.util.Set<EstadisticaJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<EstadisticaJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (EstadisticaJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static EstadisticaJPA copyJPA(EstadisticaJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    EstadisticaJPA __tmp = (EstadisticaJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"EntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat()) ) ) {
      __tmp.setEntitat(EntitatJPA.copyJPA(__jpa.getEntitat(), __alreadyCopied,"EstadisticaJPA"));
    }

    return __tmp;
  }




}
