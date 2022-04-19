
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
@Table(name = "pfi_permisgrupplantilla" , indexes = { 
        @Index(name="pfi_permisgrupplantilla_pk_i", columnList = "permisgrupplantillaid"),
        @Index(name="pfi_permisgrpl_grupentid_fk_i", columnList = "grupentitatid"),
        @Index(name="pfi_permisgrpl_fluxid_fk_i", columnList = "fluxdefirmesid")},
           uniqueConstraints = {
            @UniqueConstraint(name="pfi_permisgrpl_grupflux_uk", columnNames={"grupentitatid","fluxdefirmesid"}) } )
@SequenceGenerator(name="PERMISGRUPPLANTILLA_SEQ", sequenceName="pfi_permisgrupplantilla_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class PermisGrupPlantillaJPA implements PermisGrupPlantilla {



private static final long serialVersionUID = -1126538664L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PERMISGRUPPLANTILLA_SEQ")
    @Column(name="permisgrupplantillaid",nullable = false,length = 19)
    long permisGrupPlantillaID;

    @Column(name="grupentitatid",nullable = false,length = 19)
    long grupEntitatID;

    @Column(name="fluxdefirmesid",nullable = false,length = 19)
    long plantillaFluxDeFirmesID;



  /** Constructor Buit */
  public PermisGrupPlantillaJPA() {
  }

  /** Constructor amb tots els camps  */
  public PermisGrupPlantillaJPA(long permisGrupPlantillaID , long grupEntitatID , long plantillaFluxDeFirmesID) {
    this.permisGrupPlantillaID=permisGrupPlantillaID;
    this.grupEntitatID=grupEntitatID;
    this.plantillaFluxDeFirmesID=plantillaFluxDeFirmesID;
}
  /** Constructor sense valors autoincrementals */
  public PermisGrupPlantillaJPA(long grupEntitatID , long plantillaFluxDeFirmesID) {
    this.grupEntitatID=grupEntitatID;
    this.plantillaFluxDeFirmesID=plantillaFluxDeFirmesID;
}
  public PermisGrupPlantillaJPA(PermisGrupPlantilla __bean) {
    this.setPermisGrupPlantillaID(__bean.getPermisGrupPlantillaID());
    this.setGrupEntitatID(__bean.getGrupEntitatID());
    this.setPlantillaFluxDeFirmesID(__bean.getPlantillaFluxDeFirmesID());
	}

	public long getPermisGrupPlantillaID() {
		return(permisGrupPlantillaID);
	};
	public void setPermisGrupPlantillaID(long _permisGrupPlantillaID_) {
		this.permisGrupPlantillaID = _permisGrupPlantillaID_;
	};

	public long getGrupEntitatID() {
		return(grupEntitatID);
	};
	public void setGrupEntitatID(long _grupEntitatID_) {
		this.grupEntitatID = _grupEntitatID_;
	};

	public long getPlantillaFluxDeFirmesID() {
		return(plantillaFluxDeFirmesID);
	};
	public void setPlantillaFluxDeFirmesID(long _plantillaFluxDeFirmesID_) {
		this.plantillaFluxDeFirmesID = _plantillaFluxDeFirmesID_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof PermisGrupPlantilla) {
      PermisGrupPlantilla __instance = (PermisGrupPlantilla)__obj;
      __result = true;
      __result = __result && (this.getPermisGrupPlantillaID() == __instance.getPermisGrupPlantillaID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:grupentitatid | Table: pfi_grupentitat | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grupentitatid", referencedColumnName ="grupEntitatID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pfi_permisgrpl_grupentita_fk"))
    private GrupEntitatJPA grupEntitat;

    public GrupEntitatJPA getGrupEntitat() {
    return this.grupEntitat;
  }

    public  void setGrupEntitat(GrupEntitatJPA grupEntitat) {
    this.grupEntitat = grupEntitat;
  }

// IMP Field:fluxdefirmesid | Table: pfi_plantillafluxdefirmes | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fluxdefirmesid", referencedColumnName ="fluxDeFirmesID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pfi_permisgrpl_plantiflfi_fk"))
    private PlantillaFluxDeFirmesJPA plantillaFluxDeFirmes;

    public PlantillaFluxDeFirmesJPA getPlantillaFluxDeFirmes() {
    return this.plantillaFluxDeFirmes;
  }

    public  void setPlantillaFluxDeFirmes(PlantillaFluxDeFirmesJPA plantillaFluxDeFirmes) {
    this.plantillaFluxDeFirmes = plantillaFluxDeFirmes;
  }


 // ---------------  STATIC METHODS ------------------
  public static PermisGrupPlantillaJPA toJPA(PermisGrupPlantilla __bean) {
    if (__bean == null) { return null;}
    PermisGrupPlantillaJPA __tmp = new PermisGrupPlantillaJPA();
    __tmp.setPermisGrupPlantillaID(__bean.getPermisGrupPlantillaID());
    __tmp.setGrupEntitatID(__bean.getGrupEntitatID());
    __tmp.setPlantillaFluxDeFirmesID(__bean.getPlantillaFluxDeFirmesID());
		return __tmp;
	}


  public static PermisGrupPlantillaJPA copyJPA(PermisGrupPlantillaJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<PermisGrupPlantillaJPA> copyJPA(java.util.Set<PermisGrupPlantillaJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<PermisGrupPlantillaJPA> __tmpSet = (java.util.Set<PermisGrupPlantillaJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<PermisGrupPlantillaJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (PermisGrupPlantillaJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static PermisGrupPlantillaJPA copyJPA(PermisGrupPlantillaJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    PermisGrupPlantillaJPA __tmp = (PermisGrupPlantillaJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"GrupEntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.grupEntitat) || org.hibernate.Hibernate.isInitialized(__jpa.getGrupEntitat()) ) ) {
      __tmp.setGrupEntitat(GrupEntitatJPA.copyJPA(__jpa.getGrupEntitat(), __alreadyCopied,"PermisGrupPlantillaJPA"));
    }
    if(!"PlantillaFluxDeFirmesJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.plantillaFluxDeFirmes) || org.hibernate.Hibernate.isInitialized(__jpa.getPlantillaFluxDeFirmes()) ) ) {
      __tmp.setPlantillaFluxDeFirmes(PlantillaFluxDeFirmesJPA.copyJPA(__jpa.getPlantillaFluxDeFirmes(), __alreadyCopied,"PermisGrupPlantillaJPA"));
    }

    return __tmp;
  }




}
