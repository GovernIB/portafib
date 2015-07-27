
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
@Table(name = "pfi_annexfirmat" )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq", allocationSize=1)
public class AnnexFirmatJPA implements AnnexFirmat {



private static final long serialVersionUID = -658588842L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PORTAFIB_SEQ")
	@Index(name="pfi_annexfirmat_pk_i")
	@Column(name="annexfirmatid",nullable = false,length = 19)
	long annexfirmatID;

	@Index(name="pfi_annexfirmat_fitxerid_fk_i")
	@Column(name="fitxerid",nullable = false,length = 19)
	long fitxerID;

	@Index(name="pfi_annexfirmat_annexid_fk_i")
	@Column(name="annexid",nullable = false,length = 19)
	long annexID;

	@Index(name="pfi_annexfirmat_firmaid_fk_i")
	@Column(name="firmaid",nullable = false,length = 19)
	long firmaID;



  /** Constructor Buit */
  public AnnexFirmatJPA() {
  }

  /** Constructor amb tots els camps  */
  public AnnexFirmatJPA(long annexfirmatID , long fitxerID , long annexID , long firmaID) {
    this.annexfirmatID=annexfirmatID;
    this.fitxerID=fitxerID;
    this.annexID=annexID;
    this.firmaID=firmaID;
}
  /** Constructor sense valors autoincrementals */
  public AnnexFirmatJPA(long fitxerID , long annexID , long firmaID) {
    this.fitxerID=fitxerID;
    this.annexID=annexID;
    this.firmaID=firmaID;
}
  public AnnexFirmatJPA(AnnexFirmat __bean) {
    this.setAnnexfirmatID(__bean.getAnnexfirmatID());
    this.setFitxerID(__bean.getFitxerID());
    this.setAnnexID(__bean.getAnnexID());
    this.setFirmaID(__bean.getFirmaID());
    // Fitxer
    this.setFitxer(FitxerJPA.toJPA(__bean.getFitxer()));
	}

  public static AnnexFirmatJPA toJPA(AnnexFirmat __bean) {
    if (__bean == null) { return null;}
    AnnexFirmatJPA __tmp = new AnnexFirmatJPA();
    __tmp.setAnnexfirmatID(__bean.getAnnexfirmatID());
    __tmp.setFitxerID(__bean.getFitxerID());
    __tmp.setAnnexID(__bean.getAnnexID());
    __tmp.setFirmaID(__bean.getFirmaID());
    // Fitxer
    __tmp.setFitxer(FitxerJPA.toJPA(__bean.getFitxer()));
		return __tmp;
	}

	public long getAnnexfirmatID() {
		return(annexfirmatID);
	};
	public void setAnnexfirmatID(long _annexfirmatID_) {
		this.annexfirmatID = _annexfirmatID_;
	};

	public long getFitxerID() {
		return(fitxerID);
	};
	public void setFitxerID(long _fitxerID_) {
		this.fitxerID = _fitxerID_;
	};

	public long getAnnexID() {
		return(annexID);
	};
	public void setAnnexID(long _annexID_) {
		this.annexID = _annexID_;
	};

	public long getFirmaID() {
		return(firmaID);
	};
	public void setFirmaID(long _firmaID_) {
		this.firmaID = _firmaID_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof AnnexFirmat) {
      AnnexFirmat __instance = (AnnexFirmat)__obj;
      __result = true;
      __result = __result && (this.getAnnexfirmatID() == __instance.getAnnexfirmatID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:fitxerid | Table: pfi_fitxer | Type: 1  

	@ManyToOne(fetch = FetchType.EAGER)
	@ForeignKey(name="pfi_anexfirmat_fitxer_fk")
	@JoinColumn(name = "fitxerid", referencedColumnName ="fitxerID", nullable = false, insertable=false, updatable=false)
	private FitxerJPA fitxer;

	public FitxerJPA getFitxer() {
    return this.fitxer;
  }

	public  void setFitxer(FitxerJPA fitxer) {
    this.fitxer = fitxer;
  }

// IMP Field:annexid | Table: pfi_annex | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_anexfirmat_annex_fk")
	@JoinColumn(name = "annexid", referencedColumnName ="annexID", nullable = false, insertable=false, updatable=false)
	private AnnexJPA annex;

	public AnnexJPA getAnnex() {
    return this.annex;
  }

	public  void setAnnex(AnnexJPA annex) {
    this.annex = annex;
  }

// IMP Field:firmaid | Table: pfi_firma | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_anexfirmat_firma_fk")
	@JoinColumn(name = "firmaid", referencedColumnName ="firmaID", nullable = false, insertable=false, updatable=false)
	private FirmaJPA firma;

	public FirmaJPA getFirma() {
    return this.firma;
  }

	public  void setFirma(FirmaJPA firma) {
    this.firma = firma;
  }



}
