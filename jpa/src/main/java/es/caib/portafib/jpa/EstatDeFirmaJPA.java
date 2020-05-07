
package es.caib.portafib.jpa;
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
@Table(name = "pfi_estatdefirma" )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
public class EstatDeFirmaJPA implements EstatDeFirma {



private static final long serialVersionUID = 1766648722L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PORTAFIB_SEQ")
	@Index(name="pfi_estatdefirma_pk_i")
	@Column(name="estatdefirmaid",nullable = false,length = 19)
	long estatDeFirmaID;

	@Index(name="pfi_estatdefirma_firmaid_fk_i")
	@Column(name="firmaid",nullable = false,length = 19)
	long firmaID;

	@Index(name="pfi_estatfirma_usrentid_fk_i")
	@Column(name="usuarientitatid",nullable = false,length = 101)
	java.lang.String usuariEntitatID;

	@Column(name="datainici",nullable = false,length = 29,precision = 6)
	java.sql.Timestamp dataInici;

	@Column(name="datafi",length = 29,precision = 6)
	java.sql.Timestamp dataFi;

	@Index(name="pfi_estatfirma_estatinid_fk_i")
	@Column(name="tipusestatdefirmainicialid",nullable = false,length = 19)
	long tipusEstatDeFirmaInicialID;

	@Index(name="pfi_estatfirma_estatid_fk_i")
	@Column(name="tipusestatdefirmafinalid",length = 19)
	java.lang.Long tipusEstatDeFirmaFinalID;

	@Index(name="pfi_estatfirma_coladele_fk_i")
	@Column(name="colaboraciodelegacioid",length = 19)
	java.lang.Long colaboracioDelegacioID;

	@Column(name="descripcio",length = 255)
	java.lang.String descripcio;



  /** Constructor Buit */
  public EstatDeFirmaJPA() {
  }

  /** Constructor amb tots els camps  */
  public EstatDeFirmaJPA(long estatDeFirmaID , long firmaID , java.lang.String usuariEntitatID , java.sql.Timestamp dataInici , java.sql.Timestamp dataFi , long tipusEstatDeFirmaInicialID , java.lang.Long tipusEstatDeFirmaFinalID , java.lang.Long colaboracioDelegacioID , java.lang.String descripcio) {
    this.estatDeFirmaID=estatDeFirmaID;
    this.firmaID=firmaID;
    this.usuariEntitatID=usuariEntitatID;
    this.dataInici=dataInici;
    this.dataFi=dataFi;
    this.tipusEstatDeFirmaInicialID=tipusEstatDeFirmaInicialID;
    this.tipusEstatDeFirmaFinalID=tipusEstatDeFirmaFinalID;
    this.colaboracioDelegacioID=colaboracioDelegacioID;
    this.descripcio=descripcio;
}
  /** Constructor sense valors autoincrementals */
  public EstatDeFirmaJPA(long firmaID , java.lang.String usuariEntitatID , java.sql.Timestamp dataInici , java.sql.Timestamp dataFi , long tipusEstatDeFirmaInicialID , java.lang.Long tipusEstatDeFirmaFinalID , java.lang.Long colaboracioDelegacioID , java.lang.String descripcio) {
    this.firmaID=firmaID;
    this.usuariEntitatID=usuariEntitatID;
    this.dataInici=dataInici;
    this.dataFi=dataFi;
    this.tipusEstatDeFirmaInicialID=tipusEstatDeFirmaInicialID;
    this.tipusEstatDeFirmaFinalID=tipusEstatDeFirmaFinalID;
    this.colaboracioDelegacioID=colaboracioDelegacioID;
    this.descripcio=descripcio;
}
  /** Constructor dels valors Not Null */
  public EstatDeFirmaJPA(long estatDeFirmaID , long firmaID , java.lang.String usuariEntitatID , java.sql.Timestamp dataInici , long tipusEstatDeFirmaInicialID) {
    this.estatDeFirmaID=estatDeFirmaID;
    this.firmaID=firmaID;
    this.usuariEntitatID=usuariEntitatID;
    this.dataInici=dataInici;
    this.tipusEstatDeFirmaInicialID=tipusEstatDeFirmaInicialID;
}
  public EstatDeFirmaJPA(EstatDeFirma __bean) {
    this.setEstatDeFirmaID(__bean.getEstatDeFirmaID());
    this.setFirmaID(__bean.getFirmaID());
    this.setUsuariEntitatID(__bean.getUsuariEntitatID());
    this.setDataInici(__bean.getDataInici());
    this.setDataFi(__bean.getDataFi());
    this.setTipusEstatDeFirmaInicialID(__bean.getTipusEstatDeFirmaInicialID());
    this.setTipusEstatDeFirmaFinalID(__bean.getTipusEstatDeFirmaFinalID());
    this.setColaboracioDelegacioID(__bean.getColaboracioDelegacioID());
    this.setDescripcio(__bean.getDescripcio());
	}

	public long getEstatDeFirmaID() {
		return(estatDeFirmaID);
	};
	public void setEstatDeFirmaID(long _estatDeFirmaID_) {
		this.estatDeFirmaID = _estatDeFirmaID_;
	};

	public long getFirmaID() {
		return(firmaID);
	};
	public void setFirmaID(long _firmaID_) {
		this.firmaID = _firmaID_;
	};

	public java.lang.String getUsuariEntitatID() {
		return(usuariEntitatID);
	};
	public void setUsuariEntitatID(java.lang.String _usuariEntitatID_) {
		this.usuariEntitatID = _usuariEntitatID_;
	};

	public java.sql.Timestamp getDataInici() {
		return(dataInici);
	};
	public void setDataInici(java.sql.Timestamp _dataInici_) {
		this.dataInici = _dataInici_;
	};

	public java.sql.Timestamp getDataFi() {
		return(dataFi);
	};
	public void setDataFi(java.sql.Timestamp _dataFi_) {
		this.dataFi = _dataFi_;
	};

	public long getTipusEstatDeFirmaInicialID() {
		return(tipusEstatDeFirmaInicialID);
	};
	public void setTipusEstatDeFirmaInicialID(long _tipusEstatDeFirmaInicialID_) {
		this.tipusEstatDeFirmaInicialID = _tipusEstatDeFirmaInicialID_;
	};

	public java.lang.Long getTipusEstatDeFirmaFinalID() {
		return(tipusEstatDeFirmaFinalID);
	};
	public void setTipusEstatDeFirmaFinalID(java.lang.Long _tipusEstatDeFirmaFinalID_) {
		this.tipusEstatDeFirmaFinalID = _tipusEstatDeFirmaFinalID_;
	};

	public java.lang.Long getColaboracioDelegacioID() {
		return(colaboracioDelegacioID);
	};
	public void setColaboracioDelegacioID(java.lang.Long _colaboracioDelegacioID_) {
		this.colaboracioDelegacioID = _colaboracioDelegacioID_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof EstatDeFirma) {
      EstatDeFirma __instance = (EstatDeFirma)__obj;
      __result = true;
      __result = __result && (this.getEstatDeFirmaID() == __instance.getEstatDeFirmaID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:firmaid | Table: pfi_firma | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_estatfirma_firma_fk")
	@JoinColumn(name = "firmaid", referencedColumnName ="firmaID", nullable = false, insertable=false, updatable=false)
	private FirmaJPA firma;

	public FirmaJPA getFirma() {
    return this.firma;
  }

	public  void setFirma(FirmaJPA firma) {
    this.firma = firma;
  }

// IMP Field:usuarientitatid | Table: pfi_usuarientitat | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_estatfirma_usrentitat_fk")
	@JoinColumn(name = "usuarientitatid", referencedColumnName ="usuariEntitatID", nullable = false, insertable=false, updatable=false)
	private UsuariEntitatJPA usuariEntitat;

	public UsuariEntitatJPA getUsuariEntitat() {
    return this.usuariEntitat;
  }

	public  void setUsuariEntitat(UsuariEntitatJPA usuariEntitat) {
    this.usuariEntitat = usuariEntitat;
  }

// IMP Field:colaboraciodelegacioid | Table: pfi_colaboraciodelegacio | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_estatfirma_colabdeleg_fk")
	@JoinColumn(name = "colaboraciodelegacioid", referencedColumnName ="colaboracioDelegacioID", nullable = true, insertable=false, updatable=false)
	private ColaboracioDelegacioJPA colaboracioDelegacio;

	public ColaboracioDelegacioJPA getColaboracioDelegacio() {
    return this.colaboracioDelegacio;
  }

	public  void setColaboracioDelegacio(ColaboracioDelegacioJPA colaboracioDelegacio) {
    this.colaboracioDelegacio = colaboracioDelegacio;
  }


 // ---------------  STATIC METHODS ------------------
  public static EstatDeFirmaJPA toJPA(EstatDeFirma __bean) {
    if (__bean == null) { return null;}
    EstatDeFirmaJPA __tmp = new EstatDeFirmaJPA();
    __tmp.setEstatDeFirmaID(__bean.getEstatDeFirmaID());
    __tmp.setFirmaID(__bean.getFirmaID());
    __tmp.setUsuariEntitatID(__bean.getUsuariEntitatID());
    __tmp.setDataInici(__bean.getDataInici());
    __tmp.setDataFi(__bean.getDataFi());
    __tmp.setTipusEstatDeFirmaInicialID(__bean.getTipusEstatDeFirmaInicialID());
    __tmp.setTipusEstatDeFirmaFinalID(__bean.getTipusEstatDeFirmaFinalID());
    __tmp.setColaboracioDelegacioID(__bean.getColaboracioDelegacioID());
    __tmp.setDescripcio(__bean.getDescripcio());
		return __tmp;
	}


  public static EstatDeFirmaJPA copyJPA(EstatDeFirmaJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<EstatDeFirmaJPA> copyJPA(java.util.Set<EstatDeFirmaJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<EstatDeFirmaJPA> __tmpSet = (java.util.Set<EstatDeFirmaJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<EstatDeFirmaJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (EstatDeFirmaJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static EstatDeFirmaJPA copyJPA(EstatDeFirmaJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    EstatDeFirmaJPA __tmp = (EstatDeFirmaJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"FirmaJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.firma) || org.hibernate.Hibernate.isInitialized(__jpa.getFirma()) ) ) {
      __tmp.setFirma(FirmaJPA.copyJPA(__jpa.getFirma(), __alreadyCopied,"EstatDeFirmaJPA"));
    }
    if(!"UsuariEntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariEntitat) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariEntitat()) ) ) {
      __tmp.setUsuariEntitat(UsuariEntitatJPA.copyJPA(__jpa.getUsuariEntitat(), __alreadyCopied,"EstatDeFirmaJPA"));
    }
    if(!"ColaboracioDelegacioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.colaboracioDelegacio) || org.hibernate.Hibernate.isInitialized(__jpa.getColaboracioDelegacio()) ) ) {
      __tmp.setColaboracioDelegacio(ColaboracioDelegacioJPA.copyJPA(__jpa.getColaboracioDelegacio(), __alreadyCopied,"EstatDeFirmaJPA"));
    }

    return __tmp;
  }




}
