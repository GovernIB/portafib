
package es.caib.portafib.jpa;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import org.hibernate.annotations.Cascade;
import javax.persistence.SequenceGenerator;
import java.util.Map;
import javax.persistence.Id;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.LazyCollection;
import javax.persistence.GenerationType;
import org.hibernate.annotations.Index;
import javax.persistence.JoinTable;
import org.hibernate.annotations.ForeignKey;
import java.util.HashMap;
import java.util.HashSet;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import org.hibernate.annotations.CollectionOfElements;
import java.util.Set;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;


@Entity
@Table(name = "pfi_traduccio" )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq")
public class TraduccioJPA implements Traduccio {



private static final long serialVersionUID = -326205279L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PORTAFIB_SEQ")
	@Index(name="pfi_traduccio_pk_i")
	@Column(name="traduccioid",nullable = false,length = 19)
	long traduccioID;



  /** Constructor Buit */
  public TraduccioJPA() {
  }

  /** Constructor amb tots els camps  */
  public TraduccioJPA(long traduccioID) {
    this.traduccioID=traduccioID;
}
  public TraduccioJPA(Traduccio __bean) {
    this.setTraduccioID(__bean.getTraduccioID());
	}

  public static TraduccioJPA toJPA(Traduccio __bean) {
    if (__bean == null) { return null;}
    TraduccioJPA __tmp = new TraduccioJPA();
    __tmp.setTraduccioID(__bean.getTraduccioID());
		return __tmp;
	}

	public long getTraduccioID() {
		return(traduccioID);
	};
	public void setTraduccioID(long _traduccioID_) {
		this.traduccioID = _traduccioID_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Traduccio) {
      Traduccio __instance = (Traduccio)__obj;
      __result = true;
      __result = __result && (this.getTraduccioID() == __instance.getTraduccioID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:nom | Table: pfi_tipusdocument | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "nomID")
	private Set<TipusDocumentJPA> tipusDocuments = new HashSet<TipusDocumentJPA>(0);
	public  Set<TipusDocumentJPA> getTipusDocuments() {
    return this.tipusDocuments;
  }

	public void setTipusDocuments(Set<TipusDocumentJPA> tipusDocuments) {
	  this.tipusDocuments = tipusDocuments;
	}


  @CollectionOfElements(fetch= FetchType.EAGER,targetElement = es.caib.portafib.jpa.TraduccioMapJPA.class)
  @Cascade(value=org.hibernate.annotations.CascadeType.ALL)
  @LazyCollection(value= LazyCollectionOption.FALSE)
  @JoinTable(name="pfi_traducciomap",joinColumns={@JoinColumn(name="traducciomapid")})
  @org.hibernate.annotations.MapKey(columns={@Column(name="idiomaid")})
  @ForeignKey(name="pfi_traducmap_traduccio_fk") 
  private Map<String, es.caib.portafib.jpa.TraduccioMapJPA> traduccions =  new HashMap<String, es.caib.portafib.jpa.TraduccioMapJPA>();

  public Map<String, es.caib.portafib.jpa.TraduccioMapJPA> getTraduccions() {
    return this.traduccions;
  }

  public void setTraduccions(Map<String, es.caib.portafib.jpa.TraduccioMapJPA> _traduccions_) {
    this.traduccions = _traduccions_;
  }

  public java.util.Set<String> getIdiomes() {
    return this.traduccions.keySet();
  }
  
  public es.caib.portafib.jpa.TraduccioMapJPA getTraduccio(String idioma) {
    return (es.caib.portafib.jpa.TraduccioMapJPA) traduccions.get(idioma);
  }
  
  public void addTraduccio(String idioma, es.caib.portafib.jpa.TraduccioMapJPA traduccio) {
    if (traduccio == null) {
      traduccions.remove(idioma);
    } else {
      traduccions.put(idioma, traduccio);
    }
  }


}
