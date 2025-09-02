
package es.caib.portafib.persistence;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import org.hibernate.annotations.Type;
import javax.persistence.Id;


@Entity(name = "CorreuAgrupatJPA")
@Table(name = "pfi_correuagrupat" , indexes = { 
        @Index(name="pfi_correuagrupat_pk_i", columnList = "correuagrupatid"),
        @Index(name="pfi_correagrup_usrentitat_fk_i", columnList = "usuarientitatid")})
@SequenceGenerator(name="CORREUAGRUPAT_SEQ", sequenceName="pfi_correuagrupat_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class CorreuAgrupatJPA implements CorreuAgrupat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CORREUAGRUPAT_SEQ")
    @Column(name="correuagrupatid",nullable = false,length = 19)
    long correuAgrupatID;

    @Column(name="email",nullable = false,length = 255)
    java.lang.String email;

    @Column(name="subject",nullable = false,length = 255)
    java.lang.String subject;

    @Column(name="message",nullable = false,length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String message;

    @Column(name="html",nullable = false,length = 1)
    boolean html;

    @Column(name="usuarientitatid",nullable = false,length = 101)
    java.lang.String usuariEntitatID;

    @Column(name="datacreacio",nullable = false,length = 29,precision = 6)
    java.sql.Timestamp dataCreacio;

    @Column(name="error",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String error;



  /** Constructor Buit */
  public CorreuAgrupatJPA() {
  }

  /** Constructor amb tots els camps  */
  public CorreuAgrupatJPA(long correuAgrupatID , java.lang.String email , java.lang.String subject , java.lang.String message , boolean html , java.lang.String usuariEntitatID , java.sql.Timestamp dataCreacio , java.lang.String error) {
    this.correuAgrupatID=correuAgrupatID;
    this.email=email;
    this.subject=subject;
    this.message=message;
    this.html=html;
    this.usuariEntitatID=usuariEntitatID;
    this.dataCreacio=dataCreacio;
    this.error=error;
}
  /** Constructor sense valors autoincrementals */
  public CorreuAgrupatJPA(java.lang.String email , java.lang.String subject , java.lang.String message , boolean html , java.lang.String usuariEntitatID , java.sql.Timestamp dataCreacio , java.lang.String error) {
    this.email=email;
    this.subject=subject;
    this.message=message;
    this.html=html;
    this.usuariEntitatID=usuariEntitatID;
    this.dataCreacio=dataCreacio;
    this.error=error;
}
  public CorreuAgrupatJPA(CorreuAgrupat __bean) {
    this.setCorreuAgrupatID(__bean.getCorreuAgrupatID());
    this.setEmail(__bean.getEmail());
    this.setSubject(__bean.getSubject());
    this.setMessage(__bean.getMessage());
    this.setHtml(__bean.isHtml());
    this.setUsuariEntitatID(__bean.getUsuariEntitatID());
    this.setDataCreacio(__bean.getDataCreacio());
    this.setError(__bean.getError());
	}

	public long getCorreuAgrupatID() {
		return(correuAgrupatID);
	};
	public void setCorreuAgrupatID(long _correuAgrupatID_) {
		this.correuAgrupatID = _correuAgrupatID_;
	};

	public java.lang.String getEmail() {
		return(email);
	};
	public void setEmail(java.lang.String _email_) {
		this.email = _email_;
	};

	public java.lang.String getSubject() {
		return(subject);
	};
	public void setSubject(java.lang.String _subject_) {
		this.subject = _subject_;
	};

	public java.lang.String getMessage() {
		return(message);
	};
	public void setMessage(java.lang.String _message_) {
		this.message = _message_;
	};

	public boolean isHtml() {
		return(html);
	};
	public void setHtml(boolean _html_) {
		this.html = _html_;
	};

	public java.lang.String getUsuariEntitatID() {
		return(usuariEntitatID);
	};
	public void setUsuariEntitatID(java.lang.String _usuariEntitatID_) {
		this.usuariEntitatID = _usuariEntitatID_;
	};

	public java.sql.Timestamp getDataCreacio() {
		return(dataCreacio);
	};
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_) {
		this.dataCreacio = _dataCreacio_;
	};

	public java.lang.String getError() {
		return(error);
	};
	public void setError(java.lang.String _error_) {
		this.error = _error_;
	};



    @Override
    public boolean equals(Object __obj) {
        boolean __result;
        if (__obj != null && __obj instanceof CorreuAgrupat) {
            CorreuAgrupat __instance = (CorreuAgrupat)__obj;
            __result = true;
            __result = __result && (this.getCorreuAgrupatID() == __instance.getCorreuAgrupatID()) ;
        } else {
            __result = false;
        }
        return __result;
    }

// IMP Field:usuarientitatid | Table: pfi_usuarientitat | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuarientitatid", referencedColumnName ="usuariEntitatID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pfi_correagrup_usrentitat_fk"))
    private UsuariEntitatJPA usuariEntitat;

    public UsuariEntitatJPA getUsuariEntitat() {
    return this.usuariEntitat;
  }

    public  void setUsuariEntitat(UsuariEntitatJPA usuariEntitat) {
    this.usuariEntitat = usuariEntitat;
  }


 // ---------------  STATIC METHODS ------------------
  public static CorreuAgrupatJPA toJPA(CorreuAgrupat __bean) {
    if (__bean == null) { return null;}
    CorreuAgrupatJPA __tmp = new CorreuAgrupatJPA();
    __tmp.setCorreuAgrupatID(__bean.getCorreuAgrupatID());
    __tmp.setEmail(__bean.getEmail());
    __tmp.setSubject(__bean.getSubject());
    __tmp.setMessage(__bean.getMessage());
    __tmp.setHtml(__bean.isHtml());
    __tmp.setUsuariEntitatID(__bean.getUsuariEntitatID());
    __tmp.setDataCreacio(__bean.getDataCreacio());
    __tmp.setError(__bean.getError());
		return __tmp;
	}


  public static CorreuAgrupatJPA copyJPA(CorreuAgrupatJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<CorreuAgrupatJPA> copyJPA(java.util.Set<CorreuAgrupatJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<CorreuAgrupatJPA> __tmpSet = (java.util.Set<CorreuAgrupatJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<CorreuAgrupatJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (CorreuAgrupatJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static CorreuAgrupatJPA copyJPA(CorreuAgrupatJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    CorreuAgrupatJPA __tmp = (CorreuAgrupatJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"UsuariEntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariEntitat) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariEntitat()) ) ) {
      __tmp.setUsuariEntitat(UsuariEntitatJPA.copyJPA(__jpa.getUsuariEntitat(), __alreadyCopied,"CorreuAgrupatJPA"));
    }

    return __tmp;
  }




}
