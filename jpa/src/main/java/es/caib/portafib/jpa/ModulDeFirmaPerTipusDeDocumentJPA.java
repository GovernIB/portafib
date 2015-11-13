
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
@Table(name = "pfi_modulfirmapertipusdoc"  , uniqueConstraints = {
            @UniqueConstraint( columnNames={"tipusdocumentid","moduldefirmaid"}) } )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
public class ModulDeFirmaPerTipusDeDocumentJPA implements ModulDeFirmaPerTipusDeDocument {



private static final long serialVersionUID = 2145428058L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PORTAFIB_SEQ")
	@Index(name="pfi_modulfirmapertipusdoc_pk_i")
	@Column(name="id",nullable = false,length = 19)
	long ID;

	@Index(name="pfi_mofitido_tipusdoc_fk_i")
	@Column(name="tipusdocumentid",nullable = false,length = 19)
	long tipusDocumentID;

	@Index(name="pfi_mofitido_modfirma_fk_i")
	@Column(name="moduldefirmaid",nullable = false,length = 19)
	long modulDeFirmaID;

	@Column(name="nom",nullable = false,length = 100)
	java.lang.String nom;



  /** Constructor Buit */
  public ModulDeFirmaPerTipusDeDocumentJPA() {
  }

  /** Constructor amb tots els camps  */
  public ModulDeFirmaPerTipusDeDocumentJPA(long ID , long tipusDocumentID , long modulDeFirmaID , java.lang.String nom) {
    this.ID=ID;
    this.tipusDocumentID=tipusDocumentID;
    this.modulDeFirmaID=modulDeFirmaID;
    this.nom=nom;
}
  /** Constructor sense valors autoincrementals */
  public ModulDeFirmaPerTipusDeDocumentJPA(long tipusDocumentID , long modulDeFirmaID , java.lang.String nom) {
    this.tipusDocumentID=tipusDocumentID;
    this.modulDeFirmaID=modulDeFirmaID;
    this.nom=nom;
}
  public ModulDeFirmaPerTipusDeDocumentJPA(ModulDeFirmaPerTipusDeDocument __bean) {
    this.setID(__bean.getID());
    this.setTipusDocumentID(__bean.getTipusDocumentID());
    this.setModulDeFirmaID(__bean.getModulDeFirmaID());
    this.setNom(__bean.getNom());
	}

	public long getID() {
		return(ID);
	};
	public void setID(long _ID_) {
		this.ID = _ID_;
	};

	public long getTipusDocumentID() {
		return(tipusDocumentID);
	};
	public void setTipusDocumentID(long _tipusDocumentID_) {
		this.tipusDocumentID = _tipusDocumentID_;
	};

	public long getModulDeFirmaID() {
		return(modulDeFirmaID);
	};
	public void setModulDeFirmaID(long _modulDeFirmaID_) {
		this.modulDeFirmaID = _modulDeFirmaID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof ModulDeFirmaPerTipusDeDocument) {
      ModulDeFirmaPerTipusDeDocument __instance = (ModulDeFirmaPerTipusDeDocument)__obj;
      __result = true;
      __result = __result && (this.getID() == __instance.getID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:tipusdocumentid | Table: pfi_tipusdocument | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_mofitido_tipusdoc_fk")
	@JoinColumn(name = "tipusdocumentid", referencedColumnName ="tipusDocumentID", nullable = false, insertable=false, updatable=false)
	private TipusDocumentJPA tipusDocument;

	public TipusDocumentJPA getTipusDocument() {
    return this.tipusDocument;
  }

	public  void setTipusDocument(TipusDocumentJPA tipusDocument) {
    this.tipusDocument = tipusDocument;
  }

// IMP Field:moduldefirmaid | Table: pfi_moduldefirma | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_mofitido_modfirm_fk")
	@JoinColumn(name = "moduldefirmaid", referencedColumnName ="modulDeFirmaID", nullable = false, insertable=false, updatable=false)
	private ModulDeFirmaJPA modulDeFirma;

	public ModulDeFirmaJPA getModulDeFirma() {
    return this.modulDeFirma;
  }

	public  void setModulDeFirma(ModulDeFirmaJPA modulDeFirma) {
    this.modulDeFirma = modulDeFirma;
  }


 // ---------------  STATIC METHODS ------------------
  public static ModulDeFirmaPerTipusDeDocumentJPA toJPA(ModulDeFirmaPerTipusDeDocument __bean) {
    if (__bean == null) { return null;}
    ModulDeFirmaPerTipusDeDocumentJPA __tmp = new ModulDeFirmaPerTipusDeDocumentJPA();
    __tmp.setID(__bean.getID());
    __tmp.setTipusDocumentID(__bean.getTipusDocumentID());
    __tmp.setModulDeFirmaID(__bean.getModulDeFirmaID());
    __tmp.setNom(__bean.getNom());
		return __tmp;
	}


  public static ModulDeFirmaPerTipusDeDocumentJPA copyJPA(ModulDeFirmaPerTipusDeDocumentJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<ModulDeFirmaPerTipusDeDocumentJPA> copyJPA(java.util.Set<ModulDeFirmaPerTipusDeDocumentJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<ModulDeFirmaPerTipusDeDocumentJPA> __tmpSet = (java.util.Set<ModulDeFirmaPerTipusDeDocumentJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<ModulDeFirmaPerTipusDeDocumentJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (ModulDeFirmaPerTipusDeDocumentJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static ModulDeFirmaPerTipusDeDocumentJPA copyJPA(ModulDeFirmaPerTipusDeDocumentJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    ModulDeFirmaPerTipusDeDocumentJPA __tmp = (ModulDeFirmaPerTipusDeDocumentJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"TipusDocumentJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.tipusDocument) || org.hibernate.Hibernate.isInitialized(__jpa.getTipusDocument()) ) ) {
      __tmp.setTipusDocument(TipusDocumentJPA.copyJPA(__jpa.getTipusDocument(), __alreadyCopied,"ModulDeFirmaPerTipusDeDocumentJPA"));
    }
    if(!"ModulDeFirmaJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.modulDeFirma) || org.hibernate.Hibernate.isInitialized(__jpa.getModulDeFirma()) ) ) {
      __tmp.setModulDeFirma(ModulDeFirmaJPA.copyJPA(__jpa.getModulDeFirma(), __alreadyCopied,"ModulDeFirmaPerTipusDeDocumentJPA"));
    }

    return __tmp;
  }




}
