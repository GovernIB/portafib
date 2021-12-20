
package es.caib.portafib.persistence;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Lob;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.Index;
import javax.persistence.SequenceGenerator;
import org.hibernate.annotations.Type;
import javax.persistence.Id;


@Entity
@Table(name = "pfi_bitacola" )
@SequenceGenerator(name="BITACOLA_SEQ", sequenceName="pfi_bitacola_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class BitacolaJPA implements Bitacola {



private static final long serialVersionUID = 1492894118L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="BITACOLA_SEQ")
    @Index(name="pfi_bitacola_pk_i")
    @Column(name="bitacolaid",nullable = false,length = 19)
    long bitacolaID;

    @Column(name="entitatid",nullable = false,length = 50)
    java.lang.String entitatid;

    @Column(name="usuariid",nullable = false,length = 101)
    java.lang.String usuariid;

    @Index(name="pfi_bitacola_data_i")
    @Column(name="data",nullable = false,length = 29,precision = 6)
    java.sql.Timestamp data;

    @Column(name="tipusobjecte",nullable = false,length = 10)
    int tipusObjecte;

    @Index(name="pfi_bitacola_objecteid_i")
    @Column(name="objecteid",nullable = false,length = 100)
    java.lang.String objecteid;

    @Column(name="tipusoperacio",nullable = false,length = 10)
    int tipusOperacio;

    @Column(name="descripcio",length = 255)
    java.lang.String descripcio;

    @Column(name="objecteserialitzat",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String objecteSerialitzat;



  /** Constructor Buit */
  public BitacolaJPA() {
  }

  /** Constructor amb tots els camps  */
  public BitacolaJPA(long bitacolaID , java.lang.String entitatid , java.lang.String usuariid , java.sql.Timestamp data , int tipusObjecte , java.lang.String objecteid , int tipusOperacio , java.lang.String descripcio , java.lang.String objecteSerialitzat) {
    this.bitacolaID=bitacolaID;
    this.entitatid=entitatid;
    this.usuariid=usuariid;
    this.data=data;
    this.tipusObjecte=tipusObjecte;
    this.objecteid=objecteid;
    this.tipusOperacio=tipusOperacio;
    this.descripcio=descripcio;
    this.objecteSerialitzat=objecteSerialitzat;
}
  /** Constructor sense valors autoincrementals */
  public BitacolaJPA(java.lang.String entitatid , java.lang.String usuariid , java.sql.Timestamp data , int tipusObjecte , java.lang.String objecteid , int tipusOperacio , java.lang.String descripcio , java.lang.String objecteSerialitzat) {
    this.entitatid=entitatid;
    this.usuariid=usuariid;
    this.data=data;
    this.tipusObjecte=tipusObjecte;
    this.objecteid=objecteid;
    this.tipusOperacio=tipusOperacio;
    this.descripcio=descripcio;
    this.objecteSerialitzat=objecteSerialitzat;
}
  /** Constructor dels valors Not Null */
  public BitacolaJPA(long bitacolaID , java.lang.String entitatid , java.lang.String usuariid , java.sql.Timestamp data , int tipusObjecte , java.lang.String objecteid , int tipusOperacio) {
    this.bitacolaID=bitacolaID;
    this.entitatid=entitatid;
    this.usuariid=usuariid;
    this.data=data;
    this.tipusObjecte=tipusObjecte;
    this.objecteid=objecteid;
    this.tipusOperacio=tipusOperacio;
}
  public BitacolaJPA(Bitacola __bean) {
    this.setBitacolaID(__bean.getBitacolaID());
    this.setEntitatid(__bean.getEntitatid());
    this.setUsuariid(__bean.getUsuariid());
    this.setData(__bean.getData());
    this.setTipusObjecte(__bean.getTipusObjecte());
    this.setObjecteid(__bean.getObjecteid());
    this.setTipusOperacio(__bean.getTipusOperacio());
    this.setDescripcio(__bean.getDescripcio());
    this.setObjecteSerialitzat(__bean.getObjecteSerialitzat());
	}

	public long getBitacolaID() {
		return(bitacolaID);
	};
	public void setBitacolaID(long _bitacolaID_) {
		this.bitacolaID = _bitacolaID_;
	};

	public java.lang.String getEntitatid() {
		return(entitatid);
	};
	public void setEntitatid(java.lang.String _entitatid_) {
		this.entitatid = _entitatid_;
	};

	public java.lang.String getUsuariid() {
		return(usuariid);
	};
	public void setUsuariid(java.lang.String _usuariid_) {
		this.usuariid = _usuariid_;
	};

	public java.sql.Timestamp getData() {
		return(data);
	};
	public void setData(java.sql.Timestamp _data_) {
		this.data = _data_;
	};

	public int getTipusObjecte() {
		return(tipusObjecte);
	};
	public void setTipusObjecte(int _tipusObjecte_) {
		this.tipusObjecte = _tipusObjecte_;
	};

	public java.lang.String getObjecteid() {
		return(objecteid);
	};
	public void setObjecteid(java.lang.String _objecteid_) {
		this.objecteid = _objecteid_;
	};

	public int getTipusOperacio() {
		return(tipusOperacio);
	};
	public void setTipusOperacio(int _tipusOperacio_) {
		this.tipusOperacio = _tipusOperacio_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};

	public java.lang.String getObjecteSerialitzat() {
		return(objecteSerialitzat);
	};
	public void setObjecteSerialitzat(java.lang.String _objecteSerialitzat_) {
		this.objecteSerialitzat = _objecteSerialitzat_;
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
    __tmp.setEntitatid(__bean.getEntitatid());
    __tmp.setUsuariid(__bean.getUsuariid());
    __tmp.setData(__bean.getData());
    __tmp.setTipusObjecte(__bean.getTipusObjecte());
    __tmp.setObjecteid(__bean.getObjecteid());
    __tmp.setTipusOperacio(__bean.getTipusOperacio());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setObjecteSerialitzat(__bean.getObjecteSerialitzat());
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
