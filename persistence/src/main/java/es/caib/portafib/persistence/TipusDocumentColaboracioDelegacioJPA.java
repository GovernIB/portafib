
package es.caib.portafib.persistence;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.Index;
import javax.persistence.UniqueConstraint;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.Id;


@Entity
@Table(name = "pfi_tipusdocumentcoladele" , indexes = { 
        @Index(name="pfi_tipusdocumentcoladele_pk_i", columnList = "id"),
        @Index(name="pfi_tipusdoccd_coldelid_fk_i", columnList = "colaboraciodelegacioid"),
        @Index(name="pfi_tipusdoccd_tipusdocid_fk_i", columnList = "tipusdocumentid")},
           uniqueConstraints = {
            @UniqueConstraint(name="pfi_tipusdoccd_codetdoc_uk", columnNames={"colaboraciodelegacioid","tipusdocumentid"}) } )
@SequenceGenerator(name="TIPUSDOCUMENTCOLABORACIODELEGACIO_SEQ", sequenceName="pfi_tipusdocumentcoladele_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class TipusDocumentColaboracioDelegacioJPA implements TipusDocumentColaboracioDelegacio {



private static final long serialVersionUID = -1633225634L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TIPUSDOCUMENTCOLABORACIODELEGACIO_SEQ")
    @Column(name="id",nullable = false,length = 19)
    long id;

    @Column(name="colaboraciodelegacioid",nullable = false,length = 19)
    long colaboracioDelegacioID;

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
    @JoinColumn(name = "colaboraciodelegacioid", referencedColumnName ="colaboracioDelegacioID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pfi_tipusdoccd_colabdeleg_fk"))
    private ColaboracioDelegacioJPA colaboracioDelegacio;

    public ColaboracioDelegacioJPA getColaboracioDelegacio() {
    return this.colaboracioDelegacio;
  }

    public  void setColaboracioDelegacio(ColaboracioDelegacioJPA colaboracioDelegacio) {
    this.colaboracioDelegacio = colaboracioDelegacio;
  }

// IMP Field:tipusdocumentid | Table: pfi_tipusdocument | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipusdocumentid", referencedColumnName ="tipusDocumentID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pfi_tipusdoccd_tipusdoc_fk"))
    private TipusDocumentJPA tipusDocument;

    public TipusDocumentJPA getTipusDocument() {
    return this.tipusDocument;
  }

    public  void setTipusDocument(TipusDocumentJPA tipusDocument) {
    this.tipusDocument = tipusDocument;
  }


 // ---------------  STATIC METHODS ------------------
  public static TipusDocumentColaboracioDelegacioJPA toJPA(TipusDocumentColaboracioDelegacio __bean) {
    if (__bean == null) { return null;}
    TipusDocumentColaboracioDelegacioJPA __tmp = new TipusDocumentColaboracioDelegacioJPA();
    __tmp.setId(__bean.getId());
    __tmp.setColaboracioDelegacioID(__bean.getColaboracioDelegacioID());
    __tmp.setTipusDocumentID(__bean.getTipusDocumentID());
		return __tmp;
	}


  public static TipusDocumentColaboracioDelegacioJPA copyJPA(TipusDocumentColaboracioDelegacioJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<TipusDocumentColaboracioDelegacioJPA> copyJPA(java.util.Set<TipusDocumentColaboracioDelegacioJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<TipusDocumentColaboracioDelegacioJPA> __tmpSet = (java.util.Set<TipusDocumentColaboracioDelegacioJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<TipusDocumentColaboracioDelegacioJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (TipusDocumentColaboracioDelegacioJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static TipusDocumentColaboracioDelegacioJPA copyJPA(TipusDocumentColaboracioDelegacioJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    TipusDocumentColaboracioDelegacioJPA __tmp = (TipusDocumentColaboracioDelegacioJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"TipusDocumentJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.tipusDocument) || org.hibernate.Hibernate.isInitialized(__jpa.getTipusDocument()) ) ) {
      __tmp.setTipusDocument(TipusDocumentJPA.copyJPA(__jpa.getTipusDocument(), __alreadyCopied,"TipusDocumentColaboracioDelegacioJPA"));
    }
    if(!"ColaboracioDelegacioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.colaboracioDelegacio) || org.hibernate.Hibernate.isInitialized(__jpa.getColaboracioDelegacio()) ) ) {
      __tmp.setColaboracioDelegacio(ColaboracioDelegacioJPA.copyJPA(__jpa.getColaboracioDelegacio(), __alreadyCopied,"TipusDocumentColaboracioDelegacioJPA"));
    }

    return __tmp;
  }




}
