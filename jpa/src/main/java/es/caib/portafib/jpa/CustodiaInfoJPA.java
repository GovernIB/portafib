
package es.caib.portafib.jpa;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.ForeignKey;
import java.util.HashSet;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;


@Entity
@Table(name = "pfi_custodiainfo" )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq", allocationSize=1)
public class CustodiaInfoJPA implements CustodiaInfo {



private static final long serialVersionUID = 1603204493L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PORTAFIB_SEQ")
	@Index(name="pfi_custodiainfo_pk_i")
	@Column(name="custodiainfoid",nullable = false,length = 19)
	long custodiaInfoID;

  /** Identificador retornat pel sistema de custodia */
	@Column(name="custodiapluginid",length = 255)
	java.lang.String custodiaPluginID;

  /** Classe del plugin que gestiona la custòdia, es per si en algun moment canviam el sistema de custòdia. */
	@Column(name="custodiapluginclassid",nullable = false,length = 255)
	java.lang.String custodiaPluginClassID;

	@Column(name="custodiapluginparametres",length = 3000)
	java.lang.String custodiaPluginParameters;

  /** Si val diferent de null indica que és una plantilla d'entitat o d'usuari-aplicacio o usuari-entitat */
	@Column(name="nomplantilla",length = 255)
	java.lang.String nomPlantilla;

  /** Permet de forma ràpida actiav o desactivar la custòdia mantenint la configuració intacta */
	@Column(name="custodiar",nullable = false,length = 1)
	boolean custodiar;

  /** URL retornada pel sistema de custòdia per accedir al document */
	@Column(name="urlfitxercustodiat",length = 500)
	java.lang.String urlFitxerCustodiat;

  /** Indica en quines pàgines s'ha de mostrar el missatge i el codi de barres. 
   - NULL = no mostrar
   - '*' = totes les pàgines
   - 0 =primera pàgina (taula de firmes)
   - '-1' = darrera pàgina (taula de firmes)
   - '-1, 0, 1, 3, 4-5, 8-' =format Imprimir */
	@Column(name="pagines",nullable = false,length = 255)
	java.lang.String pagines;

  /** Missatge de custòdia a mostrar en el document: 
   {0} = URL validacio
   {1} = custodiaID
   {2} = custodiaPluginClassID
   {3} = data  amb hora
   {4} = valor retornat pel Plugin de Custodia */
	@Column(name="missatge",nullable = false,length = 3000)
	java.lang.String missatge;

  /** Posició del missatge en la pàgina. 0 = no mostrar */
	@Index(name="pfi_custodia_msgpospagid_fk_i")
	@Column(name="missatgeposiciopaginaid",nullable = false,length = 19)
	long missatgePosicioPaginaID;

  /** Tipus de Codi de barres a mostrar. Null no mostrar */
	@Index(name="pfi_custodia_codibarid_fk_i")
	@Column(name="codibarresid",nullable = false,length = 255)
	java.lang.String codiBarresID;

  /** Posició codi de barres en la pàgina. 0 = no mostrar */
	@Index(name="pfi_custodia_codbarrpos_fk_i")
	@Column(name="codibarresposiciopaginaid",nullable = false,length = 19)
	long codiBarresPosicioPaginaID;

  /** Texte que representarà el codi de barres: 
    {0} = URL validacio
    {1} = custodiaID
    {2} = custodiaPluginClassID
    {3} = data  amb hora
    {4} = valor retornat pel Plugin de Custodia */
	@Column(name="codibarrestext",nullable = false,length = 255)
	java.lang.String codiBarresText;

  /** UsuariEntitat que ha fet la peticio de firma. Si nomPlantilla != null llavors és un plantilla i indica a quin usuari pertany */
	@Index(name="pfi_custodia_usrentid_fk_i")
	@Column(name="usuarientitatid",length = 101)
	java.lang.String usuariEntitatID;

  /** UsuariAplicació que ha fet la petició de firma. Si nomPlantilla != null llavors és una plantilla i indica a quin usuari aplicacio pertany */
	@Index(name="pfi_custodia_usrappid_fk_i")
	@Column(name="usuariaplicacioid",length = 101)
	java.lang.String usuariAplicacioID;

  /** Indica si nomPlantilla != null l'entitat a la qual pertany aquesta plantilla. Pot ser compartida per un usuai ADEN */
	@Index(name="pfi_custodia_entitatid_fk_i")
	@Column(name="entitatid",length = 50)
	java.lang.String entitatID;

  /** Nom de la peticio de firma */
	@Column(name="titolpeticio",length = 255)
	java.lang.String titolPeticio;

	@Column(name="datacustodia",length = 29,precision = 6)
	java.sql.Timestamp dataCustodia;

  /** Usat en Plantilles, indica si una vegada instanciada per un usuari aquesta plantilla podrà ser modificada */
	@Column(name="editable",nullable = false,length = 1)
	boolean editable;



  /** Constructor Buit */
  public CustodiaInfoJPA() {
  }

  /** Constructor amb tots els camps  */
  public CustodiaInfoJPA(long custodiaInfoID , java.lang.String custodiaPluginID , java.lang.String custodiaPluginClassID , java.lang.String custodiaPluginParameters , java.lang.String nomPlantilla , boolean custodiar , java.lang.String urlFitxerCustodiat , java.lang.String pagines , java.lang.String missatge , long missatgePosicioPaginaID , java.lang.String codiBarresID , long codiBarresPosicioPaginaID , java.lang.String codiBarresText , java.lang.String usuariEntitatID , java.lang.String usuariAplicacioID , java.lang.String entitatID , java.lang.String titolPeticio , java.sql.Timestamp dataCustodia , boolean editable) {
    this.custodiaInfoID=custodiaInfoID;
    this.custodiaPluginID=custodiaPluginID;
    this.custodiaPluginClassID=custodiaPluginClassID;
    this.custodiaPluginParameters=custodiaPluginParameters;
    this.nomPlantilla=nomPlantilla;
    this.custodiar=custodiar;
    this.urlFitxerCustodiat=urlFitxerCustodiat;
    this.pagines=pagines;
    this.missatge=missatge;
    this.missatgePosicioPaginaID=missatgePosicioPaginaID;
    this.codiBarresID=codiBarresID;
    this.codiBarresPosicioPaginaID=codiBarresPosicioPaginaID;
    this.codiBarresText=codiBarresText;
    this.usuariEntitatID=usuariEntitatID;
    this.usuariAplicacioID=usuariAplicacioID;
    this.entitatID=entitatID;
    this.titolPeticio=titolPeticio;
    this.dataCustodia=dataCustodia;
    this.editable=editable;
}
  /** Constructor sense valors autoincrementals */
  public CustodiaInfoJPA(java.lang.String custodiaPluginID , java.lang.String custodiaPluginClassID , java.lang.String custodiaPluginParameters , java.lang.String nomPlantilla , boolean custodiar , java.lang.String urlFitxerCustodiat , java.lang.String pagines , java.lang.String missatge , long missatgePosicioPaginaID , java.lang.String codiBarresID , long codiBarresPosicioPaginaID , java.lang.String codiBarresText , java.lang.String usuariEntitatID , java.lang.String usuariAplicacioID , java.lang.String entitatID , java.lang.String titolPeticio , java.sql.Timestamp dataCustodia , boolean editable) {
    this.custodiaPluginID=custodiaPluginID;
    this.custodiaPluginClassID=custodiaPluginClassID;
    this.custodiaPluginParameters=custodiaPluginParameters;
    this.nomPlantilla=nomPlantilla;
    this.custodiar=custodiar;
    this.urlFitxerCustodiat=urlFitxerCustodiat;
    this.pagines=pagines;
    this.missatge=missatge;
    this.missatgePosicioPaginaID=missatgePosicioPaginaID;
    this.codiBarresID=codiBarresID;
    this.codiBarresPosicioPaginaID=codiBarresPosicioPaginaID;
    this.codiBarresText=codiBarresText;
    this.usuariEntitatID=usuariEntitatID;
    this.usuariAplicacioID=usuariAplicacioID;
    this.entitatID=entitatID;
    this.titolPeticio=titolPeticio;
    this.dataCustodia=dataCustodia;
    this.editable=editable;
}
  /** Constructor dels valors Not Null */
  public CustodiaInfoJPA(long custodiaInfoID , java.lang.String custodiaPluginClassID , boolean custodiar , java.lang.String pagines , java.lang.String missatge , long missatgePosicioPaginaID , java.lang.String codiBarresID , long codiBarresPosicioPaginaID , java.lang.String codiBarresText , boolean editable) {
    this.custodiaInfoID=custodiaInfoID;
    this.custodiaPluginClassID=custodiaPluginClassID;
    this.custodiar=custodiar;
    this.pagines=pagines;
    this.missatge=missatge;
    this.missatgePosicioPaginaID=missatgePosicioPaginaID;
    this.codiBarresID=codiBarresID;
    this.codiBarresPosicioPaginaID=codiBarresPosicioPaginaID;
    this.codiBarresText=codiBarresText;
    this.editable=editable;
}
  public CustodiaInfoJPA(CustodiaInfo __bean) {
    this.setCustodiaInfoID(__bean.getCustodiaInfoID());
    this.setCustodiaPluginID(__bean.getCustodiaPluginID());
    this.setCustodiaPluginClassID(__bean.getCustodiaPluginClassID());
    this.setCustodiaPluginParameters(__bean.getCustodiaPluginParameters());
    this.setNomPlantilla(__bean.getNomPlantilla());
    this.setCustodiar(__bean.isCustodiar());
    this.setUrlFitxerCustodiat(__bean.getUrlFitxerCustodiat());
    this.setPagines(__bean.getPagines());
    this.setMissatge(__bean.getMissatge());
    this.setMissatgePosicioPaginaID(__bean.getMissatgePosicioPaginaID());
    this.setCodiBarresID(__bean.getCodiBarresID());
    this.setCodiBarresPosicioPaginaID(__bean.getCodiBarresPosicioPaginaID());
    this.setCodiBarresText(__bean.getCodiBarresText());
    this.setUsuariEntitatID(__bean.getUsuariEntitatID());
    this.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    this.setEntitatID(__bean.getEntitatID());
    this.setTitolPeticio(__bean.getTitolPeticio());
    this.setDataCustodia(__bean.getDataCustodia());
    this.setEditable(__bean.isEditable());
	}

  public static CustodiaInfoJPA toJPA(CustodiaInfo __bean) {
    if (__bean == null) { return null;}
    CustodiaInfoJPA __tmp = new CustodiaInfoJPA();
    __tmp.setCustodiaInfoID(__bean.getCustodiaInfoID());
    __tmp.setCustodiaPluginID(__bean.getCustodiaPluginID());
    __tmp.setCustodiaPluginClassID(__bean.getCustodiaPluginClassID());
    __tmp.setCustodiaPluginParameters(__bean.getCustodiaPluginParameters());
    __tmp.setNomPlantilla(__bean.getNomPlantilla());
    __tmp.setCustodiar(__bean.isCustodiar());
    __tmp.setUrlFitxerCustodiat(__bean.getUrlFitxerCustodiat());
    __tmp.setPagines(__bean.getPagines());
    __tmp.setMissatge(__bean.getMissatge());
    __tmp.setMissatgePosicioPaginaID(__bean.getMissatgePosicioPaginaID());
    __tmp.setCodiBarresID(__bean.getCodiBarresID());
    __tmp.setCodiBarresPosicioPaginaID(__bean.getCodiBarresPosicioPaginaID());
    __tmp.setCodiBarresText(__bean.getCodiBarresText());
    __tmp.setUsuariEntitatID(__bean.getUsuariEntitatID());
    __tmp.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setTitolPeticio(__bean.getTitolPeticio());
    __tmp.setDataCustodia(__bean.getDataCustodia());
    __tmp.setEditable(__bean.isEditable());
		return __tmp;
	}

	public long getCustodiaInfoID() {
		return(custodiaInfoID);
	};
	public void setCustodiaInfoID(long _custodiaInfoID_) {
		this.custodiaInfoID = _custodiaInfoID_;
	};

	public java.lang.String getCustodiaPluginID() {
		return(custodiaPluginID);
	};
	public void setCustodiaPluginID(java.lang.String _custodiaPluginID_) {
		this.custodiaPluginID = _custodiaPluginID_;
	};

	public java.lang.String getCustodiaPluginClassID() {
		return(custodiaPluginClassID);
	};
	public void setCustodiaPluginClassID(java.lang.String _custodiaPluginClassID_) {
		this.custodiaPluginClassID = _custodiaPluginClassID_;
	};

	public java.lang.String getCustodiaPluginParameters() {
		return(custodiaPluginParameters);
	};
	public void setCustodiaPluginParameters(java.lang.String _custodiaPluginParameters_) {
		this.custodiaPluginParameters = _custodiaPluginParameters_;
	};

	public java.lang.String getNomPlantilla() {
		return(nomPlantilla);
	};
	public void setNomPlantilla(java.lang.String _nomPlantilla_) {
		this.nomPlantilla = _nomPlantilla_;
	};

	public boolean isCustodiar() {
		return(custodiar);
	};
	public void setCustodiar(boolean _custodiar_) {
		this.custodiar = _custodiar_;
	};

	public java.lang.String getUrlFitxerCustodiat() {
		return(urlFitxerCustodiat);
	};
	public void setUrlFitxerCustodiat(java.lang.String _urlFitxerCustodiat_) {
		this.urlFitxerCustodiat = _urlFitxerCustodiat_;
	};

	public java.lang.String getPagines() {
		return(pagines);
	};
	public void setPagines(java.lang.String _pagines_) {
		this.pagines = _pagines_;
	};

	public java.lang.String getMissatge() {
		return(missatge);
	};
	public void setMissatge(java.lang.String _missatge_) {
		this.missatge = _missatge_;
	};

	public long getMissatgePosicioPaginaID() {
		return(missatgePosicioPaginaID);
	};
	public void setMissatgePosicioPaginaID(long _missatgePosicioPaginaID_) {
		this.missatgePosicioPaginaID = _missatgePosicioPaginaID_;
	};

	public java.lang.String getCodiBarresID() {
		return(codiBarresID);
	};
	public void setCodiBarresID(java.lang.String _codiBarresID_) {
		this.codiBarresID = _codiBarresID_;
	};

	public long getCodiBarresPosicioPaginaID() {
		return(codiBarresPosicioPaginaID);
	};
	public void setCodiBarresPosicioPaginaID(long _codiBarresPosicioPaginaID_) {
		this.codiBarresPosicioPaginaID = _codiBarresPosicioPaginaID_;
	};

	public java.lang.String getCodiBarresText() {
		return(codiBarresText);
	};
	public void setCodiBarresText(java.lang.String _codiBarresText_) {
		this.codiBarresText = _codiBarresText_;
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

	public java.lang.String getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(java.lang.String _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public java.lang.String getTitolPeticio() {
		return(titolPeticio);
	};
	public void setTitolPeticio(java.lang.String _titolPeticio_) {
		this.titolPeticio = _titolPeticio_;
	};

	public java.sql.Timestamp getDataCustodia() {
		return(dataCustodia);
	};
	public void setDataCustodia(java.sql.Timestamp _dataCustodia_) {
		this.dataCustodia = _dataCustodia_;
	};

	public boolean isEditable() {
		return(editable);
	};
	public void setEditable(boolean _editable_) {
		this.editable = _editable_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof CustodiaInfo) {
      CustodiaInfo __instance = (CustodiaInfo)__obj;
      __result = true;
      __result = __result && (this.getCustodiaInfoID() == __instance.getCustodiaInfoID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:custodiainfoid | Table: pfi_peticiodefirma | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "custodiaInfo")
	private Set<PeticioDeFirmaJPA> peticioDeFirmas = new HashSet<PeticioDeFirmaJPA>(0);
	public  Set<PeticioDeFirmaJPA> getPeticioDeFirmas() {
    return this.peticioDeFirmas;
  }

	public void setPeticioDeFirmas(Set<PeticioDeFirmaJPA> peticioDeFirmas) {
	  this.peticioDeFirmas = peticioDeFirmas;
	}


// IMP Field:posiciopaginaid | Table: pfi_posiciopagina | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_custodia_pospagina_msg_fk")
	@JoinColumn(name = "missatgeposiciopaginaid", referencedColumnName ="posicioPaginaID", nullable = false, insertable=false, updatable=false)
	private PosicioPaginaJPA missatgePosicioPagina;

	public PosicioPaginaJPA getMissatgePosicioPagina() {
    return this.missatgePosicioPagina;
  }

	public  void setMissatgePosicioPagina(PosicioPaginaJPA missatgePosicioPagina) {
    this.missatgePosicioPagina = missatgePosicioPagina;
  }

// IMP Field:codibarresid | Table: pfi_codibarres | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_custodia_codibarres_fk")
	@JoinColumn(name = "codibarresid", referencedColumnName ="codiBarresID", nullable = false, insertable=false, updatable=false)
	private CodiBarresJPA codiBarres;

	public CodiBarresJPA getCodiBarres() {
    return this.codiBarres;
  }

	public  void setCodiBarres(CodiBarresJPA codiBarres) {
    this.codiBarres = codiBarres;
  }

// IMP Field:posiciopaginaid | Table: pfi_posiciopagina | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_custodia_pospagina_bar_fk")
	@JoinColumn(name = "codibarresposiciopaginaid", referencedColumnName ="posicioPaginaID", nullable = false, insertable=false, updatable=false)
	private PosicioPaginaJPA codiBarresPosicioPagina;

	public PosicioPaginaJPA getCodiBarresPosicioPagina() {
    return this.codiBarresPosicioPagina;
  }

	public  void setCodiBarresPosicioPagina(PosicioPaginaJPA codiBarresPosicioPagina) {
    this.codiBarresPosicioPagina = codiBarresPosicioPagina;
  }

// IMP Field:usuarientitatid | Table: pfi_usuarientitat | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_custodia_usrentitat_fk")
	@JoinColumn(name = "usuarientitatid", referencedColumnName ="usuariEntitatID", nullable = true, insertable=false, updatable=false)
	private UsuariEntitatJPA usuariEntitat;

	public UsuariEntitatJPA getUsuariEntitat() {
    return this.usuariEntitat;
  }

	public  void setUsuariEntitat(UsuariEntitatJPA usuariEntitat) {
    this.usuariEntitat = usuariEntitat;
  }

// IMP Field:usuariaplicacioid | Table: pfi_usuariaplicacio | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_custodia_usrapp_fk")
	@JoinColumn(name = "usuariaplicacioid", referencedColumnName ="usuariAplicacioID", nullable = true, insertable=false, updatable=false)
	private UsuariAplicacioJPA usuariAplicacio;

	public UsuariAplicacioJPA getUsuariAplicacio() {
    return this.usuariAplicacio;
  }

	public  void setUsuariAplicacio(UsuariAplicacioJPA usuariAplicacio) {
    this.usuariAplicacio = usuariAplicacio;
  }

// IMP Field:entitatid | Table: pfi_entitat | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_custodia_entitat_fk")
	@JoinColumn(name = "entitatid", referencedColumnName ="entitatID", nullable = true, insertable=false, updatable=false)
	private EntitatJPA entitat;

	public EntitatJPA getEntitat() {
    return this.entitat;
  }

	public  void setEntitat(EntitatJPA entitat) {
    this.entitat = entitat;
  }



}
