
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
@Table(name = "pfi_bitacola" )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq", allocationSize=1)
public class BitacolaJPA implements Bitacola {



private static final long serialVersionUID = 1492894118L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PORTAFIB_SEQ")
	@Index(name="pfi_bitacola_pk_i")
	@Column(name="bitacolaid",nullable = false,length = 19)
	long bitacolaID;

	@Column(name="data",nullable = false,length = 29,precision = 6)
	java.sql.Timestamp data;

	@Column(name="descripcio",nullable = false,length = 255)
	java.lang.String descripcio;

	@Index(name="pfi_bitacola_peticid_fk_i")
	@Column(name="peticiodefirmaid",nullable = false,length = 19)
	long peticioDeFirmaID;

	@Index(name="pfi_bitacola_usrentid_fk_i")
	@Column(name="usuarientitatid",nullable = false,length = 101)
	java.lang.String usuariEntitatID;



  /** Constructor Buit */
  public BitacolaJPA() {
  }

  /** Constructor amb tots els camps  */
  public BitacolaJPA(long bitacolaID , java.sql.Timestamp data , java.lang.String descripcio , long peticioDeFirmaID , java.lang.String usuariEntitatID) {
    this.bitacolaID=bitacolaID;
    this.data=data;
    this.descripcio=descripcio;
    this.peticioDeFirmaID=peticioDeFirmaID;
    this.usuariEntitatID=usuariEntitatID;
}
  /** Constructor sense valors autoincrementals */
  public BitacolaJPA(java.sql.Timestamp data , java.lang.String descripcio , long peticioDeFirmaID , java.lang.String usuariEntitatID) {
    this.data=data;
    this.descripcio=descripcio;
    this.peticioDeFirmaID=peticioDeFirmaID;
    this.usuariEntitatID=usuariEntitatID;
}
  public BitacolaJPA(Bitacola __bean) {
    this.setBitacolaID(__bean.getBitacolaID());
    this.setData(__bean.getData());
    this.setDescripcio(__bean.getDescripcio());
    this.setPeticioDeFirmaID(__bean.getPeticioDeFirmaID());
    this.setUsuariEntitatID(__bean.getUsuariEntitatID());
	}

  public static BitacolaJPA toJPA(Bitacola __bean) {
    if (__bean == null) { return null;}
    BitacolaJPA __tmp = new BitacolaJPA();
    __tmp.setBitacolaID(__bean.getBitacolaID());
    __tmp.setData(__bean.getData());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setPeticioDeFirmaID(__bean.getPeticioDeFirmaID());
    __tmp.setUsuariEntitatID(__bean.getUsuariEntitatID());
		return __tmp;
	}

	public long getBitacolaID() {
		return(bitacolaID);
	};
	public void setBitacolaID(long _bitacolaID_) {
		this.bitacolaID = _bitacolaID_;
	};

	public java.sql.Timestamp getData() {
		return(data);
	};
	public void setData(java.sql.Timestamp _data_) {
		this.data = _data_;
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

	public java.lang.String getUsuariEntitatID() {
		return(usuariEntitatID);
	};
	public void setUsuariEntitatID(java.lang.String _usuariEntitatID_) {
		this.usuariEntitatID = _usuariEntitatID_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Bitacola) {
      Bitacola __instance = (Bitacola)__obj;
      __result = true;
      __result = __result && (this.getBitacolaID() == __instance.getBitacolaID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:peticiodefirmaid | Table: pfi_peticiodefirma | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_bitacola_petifirma_fk")
	@JoinColumn(name = "peticiodefirmaid", referencedColumnName ="peticioDeFirmaID", nullable = false, insertable=false, updatable=false)
	private PeticioDeFirmaJPA peticioDeFirma;

	public PeticioDeFirmaJPA getPeticioDeFirma() {
    return this.peticioDeFirma;
  }

	public  void setPeticioDeFirma(PeticioDeFirmaJPA peticioDeFirma) {
    this.peticioDeFirma = peticioDeFirma;
  }

// IMP Field:usuarientitatid | Table: pfi_usuarientitat | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_bitacola_usrentitat_fk")
	@JoinColumn(name = "usuarientitatid", referencedColumnName ="usuariEntitatID", nullable = false, insertable=false, updatable=false)
	private UsuariEntitatJPA usuariEntitat;

	public UsuariEntitatJPA getUsuariEntitat() {
    return this.usuariEntitat;
  }

	public  void setUsuariEntitat(UsuariEntitatJPA usuariEntitat) {
    this.usuariEntitat = usuariEntitat;
  }



}
