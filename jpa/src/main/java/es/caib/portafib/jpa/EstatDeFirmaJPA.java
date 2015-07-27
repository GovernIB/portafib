
package es.caib.portafib.jpa;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import org.hibernate.annotations.Index;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.GeneratedValue;


@Entity
@Table(name = "pfi_estatdefirma" )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq", allocationSize=1)
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

// IMP Field:tipusestatdefirmainicialid | Table: pfi_tipusestatdefirmainicial | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_estatfirma_estfirmini_fk")
	@JoinColumn(name = "tipusestatdefirmainicialid", referencedColumnName ="tipusEstatDeFirmaInicialID", nullable = false, insertable=false, updatable=false)
	private TipusEstatDeFirmaInicialJPA tipusEstatDeFirmaInicial;

	public TipusEstatDeFirmaInicialJPA getTipusEstatDeFirmaInicial() {
    return this.tipusEstatDeFirmaInicial;
  }

	public  void setTipusEstatDeFirmaInicial(TipusEstatDeFirmaInicialJPA tipusEstatDeFirmaInicial) {
    this.tipusEstatDeFirmaInicial = tipusEstatDeFirmaInicial;
  }

// IMP Field:tipusestatdefirmafinalid | Table: pfi_tipusestatdefirmafinal | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_estatfirma_estfirmafi_fk")
	@JoinColumn(name = "tipusestatdefirmafinalid", referencedColumnName ="tipusEstatDeFirmaFinalID", nullable = true, insertable=false, updatable=false)
	private TipusEstatDeFirmaFinalJPA tipusEstatDeFirmaFinal;

	public TipusEstatDeFirmaFinalJPA getTipusEstatDeFirmaFinal() {
    return this.tipusEstatDeFirmaFinal;
  }

	public  void setTipusEstatDeFirmaFinal(TipusEstatDeFirmaFinalJPA tipusEstatDeFirmaFinal) {
    this.tipusEstatDeFirmaFinal = tipusEstatDeFirmaFinal;
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



}
