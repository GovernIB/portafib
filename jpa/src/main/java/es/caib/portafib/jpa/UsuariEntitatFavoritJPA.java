
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
@Table(name = "pfi_usuarientitatfavorit"  , uniqueConstraints = {
            @UniqueConstraint( columnNames={"origenid","favoritid"}) } )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq")
public class UsuariEntitatFavoritJPA implements UsuariEntitatFavorit {



private static final long serialVersionUID = -607428966L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PORTAFIB_SEQ")
	@Index(name="pfi_usuarientitatfavorit_pk_i")
	@Column(name="id",nullable = false,length = 19)
	long ID;

	@Index(name="pfi_favorit_origenid_fk_i")
	@Column(name="origenid",nullable = false,length = 101)
	java.lang.String origenID;

	@Index(name="pfi_favorit_favoritid_fk_i")
	@Column(name="favoritid",nullable = false,length = 101)
	java.lang.String favoritID;



  /** Constructor Buit */
  public UsuariEntitatFavoritJPA() {
  }

  /** Constructor amb tots els camps  */
  public UsuariEntitatFavoritJPA(long ID , java.lang.String origenID , java.lang.String favoritID) {
    this.ID=ID;
    this.origenID=origenID;
    this.favoritID=favoritID;
}
  /** Constructor sense valors autoincrementals */
  public UsuariEntitatFavoritJPA(java.lang.String origenID , java.lang.String favoritID) {
    this.origenID=origenID;
    this.favoritID=favoritID;
}
  public UsuariEntitatFavoritJPA(UsuariEntitatFavorit __bean) {
    this.setID(__bean.getID());
    this.setOrigenID(__bean.getOrigenID());
    this.setFavoritID(__bean.getFavoritID());
	}

  public static UsuariEntitatFavoritJPA toJPA(UsuariEntitatFavorit __bean) {
    if (__bean == null) { return null;}
    UsuariEntitatFavoritJPA __tmp = new UsuariEntitatFavoritJPA();
    __tmp.setID(__bean.getID());
    __tmp.setOrigenID(__bean.getOrigenID());
    __tmp.setFavoritID(__bean.getFavoritID());
		return __tmp;
	}

	public long getID() {
		return(ID);
	};
	public void setID(long _ID_) {
		this.ID = _ID_;
	};

	public java.lang.String getOrigenID() {
		return(origenID);
	};
	public void setOrigenID(java.lang.String _origenID_) {
		this.origenID = _origenID_;
	};

	public java.lang.String getFavoritID() {
		return(favoritID);
	};
	public void setFavoritID(java.lang.String _favoritID_) {
		this.favoritID = _favoritID_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof UsuariEntitatFavorit) {
      UsuariEntitatFavorit __instance = (UsuariEntitatFavorit)__obj;
      __result = true;
      __result = __result && (this.getID() == __instance.getID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:usuarientitatid | Table: pfi_usuarientitat | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_favorit_usrentitat_ori_fk")
	@JoinColumn(name = "origenid", referencedColumnName ="usuariEntitatID", nullable = false, insertable=false, updatable=false)
	private UsuariEntitatJPA origen;

	public UsuariEntitatJPA getOrigen() {
    return this.origen;
  }

	public  void setOrigen(UsuariEntitatJPA origen) {
    this.origen = origen;
  }

// IMP Field:usuarientitatid | Table: pfi_usuarientitat | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_favorit_usrentitat_fav_fk")
	@JoinColumn(name = "favoritid", referencedColumnName ="usuariEntitatID", nullable = false, insertable=false, updatable=false)
	private UsuariEntitatJPA favorit;

	public UsuariEntitatJPA getFavorit() {
    return this.favorit;
  }

	public  void setFavorit(UsuariEntitatJPA favorit) {
    this.favorit = favorit;
  }



}
