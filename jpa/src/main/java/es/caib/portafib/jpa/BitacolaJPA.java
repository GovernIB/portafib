
package es.caib.portafib.jpa;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import org.hibernate.annotations.Index;
import javax.persistence.GeneratedValue;


@Entity
@Table(name = "pfi_bitacola" )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
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
	@Column(name="usuarientitatid",length = 101)
	java.lang.String usuariEntitatID;

	@Column(name="usuariaplicacioid",length = 101)
	java.lang.String usuariAplicacioID;



  /** Constructor Buit */
  public BitacolaJPA() {
  }

  /** Constructor amb tots els camps  */
  public BitacolaJPA(long bitacolaID , java.sql.Timestamp data , java.lang.String descripcio , long peticioDeFirmaID , java.lang.String usuariEntitatID , java.lang.String usuariAplicacioID) {
    this.bitacolaID=bitacolaID;
    this.data=data;
    this.descripcio=descripcio;
    this.peticioDeFirmaID=peticioDeFirmaID;
    this.usuariEntitatID=usuariEntitatID;
    this.usuariAplicacioID=usuariAplicacioID;
}
  /** Constructor sense valors autoincrementals */
  public BitacolaJPA(java.sql.Timestamp data , java.lang.String descripcio , long peticioDeFirmaID , java.lang.String usuariEntitatID , java.lang.String usuariAplicacioID) {
    this.data=data;
    this.descripcio=descripcio;
    this.peticioDeFirmaID=peticioDeFirmaID;
    this.usuariEntitatID=usuariEntitatID;
    this.usuariAplicacioID=usuariAplicacioID;
}
  /** Constructor dels valors Not Null */
  public BitacolaJPA(long bitacolaID , java.sql.Timestamp data , java.lang.String descripcio , long peticioDeFirmaID) {
    this.bitacolaID=bitacolaID;
    this.data=data;
    this.descripcio=descripcio;
    this.peticioDeFirmaID=peticioDeFirmaID;
}
  public BitacolaJPA(Bitacola __bean) {
    this.setBitacolaID(__bean.getBitacolaID());
    this.setData(__bean.getData());
    this.setDescripcio(__bean.getDescripcio());
    this.setPeticioDeFirmaID(__bean.getPeticioDeFirmaID());
    this.setUsuariEntitatID(__bean.getUsuariEntitatID());
    this.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
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

	public java.lang.String getUsuariAplicacioID() {
		return(usuariAplicacioID);
	};
	public void setUsuariAplicacioID(java.lang.String _usuariAplicacioID_) {
		this.usuariAplicacioID = _usuariAplicacioID_;
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


 // ---------------  STATIC METHODS ------------------
  public static BitacolaJPA toJPA(Bitacola __bean) {
    if (__bean == null) { return null;}
    BitacolaJPA __tmp = new BitacolaJPA();
    __tmp.setBitacolaID(__bean.getBitacolaID());
    __tmp.setData(__bean.getData());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setPeticioDeFirmaID(__bean.getPeticioDeFirmaID());
    __tmp.setUsuariEntitatID(__bean.getUsuariEntitatID());
    __tmp.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
		return __tmp;
	}


  public static BitacolaJPA copyJPA(BitacolaJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<BitacolaJPA> copyJPA(java.util.Set<BitacolaJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<BitacolaJPA> __tmpSet = (java.util.Set<BitacolaJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<BitacolaJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (BitacolaJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static BitacolaJPA copyJPA(BitacolaJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    BitacolaJPA __tmp = (BitacolaJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
