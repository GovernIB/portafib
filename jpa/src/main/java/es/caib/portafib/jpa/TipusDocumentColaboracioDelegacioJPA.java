
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
import javax.persistence.UniqueConstraint;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.GeneratedValue;


@Entity
@Table(name = "pfi_tipusdocumentcoladele"  , uniqueConstraints = {
            @UniqueConstraint( columnNames={"colaboraciodelegacioid","tipusdocumentid"}) } )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq")
public class TipusDocumentColaboracioDelegacioJPA implements TipusDocumentColaboracioDelegacio {



private static final long serialVersionUID = -1633225634L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PORTAFIB_SEQ")
	@Index(name="pfi_tipusdocumentcoladele_pk_i")
	@Column(name="id",nullable = false,length = 19)
	long id;

	@Index(name="pfi_tipusdoccd_coldelid_fk_i")
	@Column(name="colaboraciodelegacioid",nullable = false,length = 19)
	long colaboracioDelegacioID;

	@Index(name="pfi_tipusdoccd_tipusdocid_fk_i")
	@Column(name="tipusdocumentid",nullable = false,length = 19)
	long tipusDocumentID;



  /** Constructor Buit */
  public TipusDocumentColaboracioDelegacioJPA() {
  }

  /** Constructor amb tots els camps  */
  public TipusDocumentColaboracioDelegacioJPA(long id , long colaboracioDelegacioID , long tipusDocumentID) {
    this.id=id;
    this.colaboracioDelegacioID=colaboracioDelegacioID;
    this.tipusDocumentID=tipusDocumentID;
}
  /** Constructor sense valors autoincrementals */
  public TipusDocumentColaboracioDelegacioJPA(long colaboracioDelegacioID , long tipusDocumentID) {
    this.colaboracioDelegacioID=colaboracioDelegacioID;
    this.tipusDocumentID=tipusDocumentID;
}
  public TipusDocumentColaboracioDelegacioJPA(TipusDocumentColaboracioDelegacio __bean) {
    this.setId(__bean.getId());
    this.setColaboracioDelegacioID(__bean.getColaboracioDelegacioID());
    this.setTipusDocumentID(__bean.getTipusDocumentID());
	}

  public static TipusDocumentColaboracioDelegacioJPA toJPA(TipusDocumentColaboracioDelegacio __bean) {
    if (__bean == null) { return null;}
    TipusDocumentColaboracioDelegacioJPA __tmp = new TipusDocumentColaboracioDelegacioJPA();
    __tmp.setId(__bean.getId());
    __tmp.setColaboracioDelegacioID(__bean.getColaboracioDelegacioID());
    __tmp.setTipusDocumentID(__bean.getTipusDocumentID());
		return __tmp;
	}

	public long getId() {
		return(id);
	};
	public void setId(long _id_) {
		this.id = _id_;
	};

	public long getColaboracioDelegacioID() {
		return(colaboracioDelegacioID);
	};
	public void setColaboracioDelegacioID(long _colaboracioDelegacioID_) {
		this.colaboracioDelegacioID = _colaboracioDelegacioID_;
	};

	public long getTipusDocumentID() {
		return(tipusDocumentID);
	};
	public void setTipusDocumentID(long _tipusDocumentID_) {
		this.tipusDocumentID = _tipusDocumentID_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof TipusDocumentColaboracioDelegacio) {
      TipusDocumentColaboracioDelegacio __instance = (TipusDocumentColaboracioDelegacio)__obj;
      __result = true;
      __result = __result && (this.getId() == __instance.getId()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:colaboraciodelegacioid | Table: pfi_colaboraciodelegacio | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_tipusdoccd_colabdeleg_fk")
	@JoinColumn(name = "colaboraciodelegacioid", referencedColumnName ="colaboracioDelegacioID", nullable = false, insertable=false, updatable=false)
	private ColaboracioDelegacioJPA colaboracioDelegacio;

	public ColaboracioDelegacioJPA getColaboracioDelegacio() {
    return this.colaboracioDelegacio;
  }

	public  void setColaboracioDelegacio(ColaboracioDelegacioJPA colaboracioDelegacio) {
    this.colaboracioDelegacio = colaboracioDelegacio;
  }

// IMP Field:tipusdocumentid | Table: pfi_tipusdocument | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_tipusdoccd_tipusdoc_fk")
	@JoinColumn(name = "tipusdocumentid", referencedColumnName ="tipusDocumentID", nullable = false, insertable=false, updatable=false)
	private TipusDocumentJPA tipusDocument;

	public TipusDocumentJPA getTipusDocument() {
    return this.tipusDocument;
  }

	public  void setTipusDocument(TipusDocumentJPA tipusDocument) {
    this.tipusDocument = tipusDocument;
  }



}
