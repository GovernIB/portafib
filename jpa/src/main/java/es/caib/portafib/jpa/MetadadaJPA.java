
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
@Table(name = "pfi_metadada" )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq", allocationSize=1)
public class MetadadaJPA implements Metadada {



private static final long serialVersionUID = 171659772L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PORTAFIB_SEQ")
	@Index(name="pfi_metadada_pk_i")
	@Column(name="metadadaid",nullable = false,length = 19)
	long metadadaID;

	@Column(name="nom",nullable = false,length = 50)
	java.lang.String nom;

	@Column(name="valor",nullable = false,length = 2147483647)
  @Lob
	java.lang.String valor;

	@Column(name="descripcio",length = 1000)
	java.lang.String descripcio;

	@Index(name="pfi_metadada_peticioid_fk_i")
	@Column(name="peticiodefirmaid",nullable = false,length = 19)
	long peticioDeFirmaID;

	@Index(name="pfi_metadada_tipusmetaid_fk_i")
	@Column(name="tipusmetadadaid",nullable = false,length = 10)
	int tipusMetadadaID;



  /** Constructor Buit */
  public MetadadaJPA() {
  }

  /** Constructor amb tots els camps  */
  public MetadadaJPA(long metadadaID , java.lang.String nom , java.lang.String valor , java.lang.String descripcio , long peticioDeFirmaID , int tipusMetadadaID) {
    this.metadadaID=metadadaID;
    this.nom=nom;
    this.valor=valor;
    this.descripcio=descripcio;
    this.peticioDeFirmaID=peticioDeFirmaID;
    this.tipusMetadadaID=tipusMetadadaID;
}
  /** Constructor sense valors autoincrementals */
  public MetadadaJPA(java.lang.String nom , java.lang.String valor , java.lang.String descripcio , long peticioDeFirmaID , int tipusMetadadaID) {
    this.nom=nom;
    this.valor=valor;
    this.descripcio=descripcio;
    this.peticioDeFirmaID=peticioDeFirmaID;
    this.tipusMetadadaID=tipusMetadadaID;
}
  public MetadadaJPA(Metadada __bean) {
    this.setMetadadaID(__bean.getMetadadaID());
    this.setNom(__bean.getNom());
    this.setValor(__bean.getValor());
    this.setDescripcio(__bean.getDescripcio());
    this.setPeticioDeFirmaID(__bean.getPeticioDeFirmaID());
    this.setTipusMetadadaID(__bean.getTipusMetadadaID());
	}

  public static MetadadaJPA toJPA(Metadada __bean) {
    if (__bean == null) { return null;}
    MetadadaJPA __tmp = new MetadadaJPA();
    __tmp.setMetadadaID(__bean.getMetadadaID());
    __tmp.setNom(__bean.getNom());
    __tmp.setValor(__bean.getValor());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setPeticioDeFirmaID(__bean.getPeticioDeFirmaID());
    __tmp.setTipusMetadadaID(__bean.getTipusMetadadaID());
		return __tmp;
	}

	public long getMetadadaID() {
		return(metadadaID);
	};
	public void setMetadadaID(long _metadadaID_) {
		this.metadadaID = _metadadaID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getValor() {
		return(valor);
	};
	public void setValor(java.lang.String _valor_) {
		this.valor = _valor_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};

	public long getPeticioDeFirmaID() {
		return(peticioDeFirmaID);
	};
	public void setPeticioDeFirmaID(long _peticioDeFirmaID_) {
		this.peticioDeFirmaID = _peticioDeFirmaID_;
	};

	public int getTipusMetadadaID() {
		return(tipusMetadadaID);
	};
	public void setTipusMetadadaID(int _tipusMetadadaID_) {
		this.tipusMetadadaID = _tipusMetadadaID_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Metadada) {
      Metadada __instance = (Metadada)__obj;
      __result = true;
      __result = __result && (this.getMetadadaID() == __instance.getMetadadaID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:peticiodefirmaid | Table: pfi_peticiodefirma | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_metadada_petifirma_fk")
	@JoinColumn(name = "peticiodefirmaid", referencedColumnName ="peticioDeFirmaID", nullable = false, insertable=false, updatable=false)
	private PeticioDeFirmaJPA peticioDeFirma;

	public PeticioDeFirmaJPA getPeticioDeFirma() {
    return this.peticioDeFirma;
  }

	public  void setPeticioDeFirma(PeticioDeFirmaJPA peticioDeFirma) {
    this.peticioDeFirma = peticioDeFirma;
  }

// IMP Field:tipusmetadadaid | Table: pfi_tipusmetadada | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_metadada_tipmetada_fk")
	@JoinColumn(name = "tipusmetadadaid", referencedColumnName ="tipusMetadadaID", nullable = false, insertable=false, updatable=false)
	private TipusMetadadaJPA tipusMetadada;

	public TipusMetadadaJPA getTipusMetadada() {
    return this.tipusMetadada;
  }

	public  void setTipusMetadada(TipusMetadadaJPA tipusMetadada) {
    this.tipusMetadada = tipusMetadada;
  }



}
