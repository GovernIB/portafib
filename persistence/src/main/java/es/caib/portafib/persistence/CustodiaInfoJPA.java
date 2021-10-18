
package es.caib.portafib.persistence;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.Id;


@SuppressWarnings("deprecation")
@Entity
@Table(name = "pfi_custodiainfo" )
@SequenceGenerator(name="CUSTODIAINFO_SEQ", sequenceName="pfi_custodiainfo_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class CustodiaInfoJPA implements CustodiaInfo {



private static final long serialVersionUID = 1603204493L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CUSTODIAINFO_SEQ")
    @Index(name="pfi_custodiainfo_pk_i")
    @Column(name="custodiainfoid",nullable = false,length = 19)
    long custodiaInfoID;

  /** Si val diferent de null indica que és una plantilla d'entitat o d'usuari-aplicacio o usuari-entitat */
    @Column(name="nomplantilla",length = 255)
    java.lang.String nomPlantilla;

  /** Identificador retornat pel sistema de custodia */
    @Column(name="custodiapluginid",length = 255)
    java.lang.String custodiaDocumentID;

    @Index(name="pfi_custodiainfo_pluginid_fk_i")
    @Column(name="pluginid",nullable = false,length = 19)
    long pluginID;

    @Column(name="custodiapluginparametres",length = 3000)
    java.lang.String custodiaPluginParameters;

  /** Permet de forma ràpida actiav o desactivar la custòdia mantenint la configuració intacta */
    @Column(name="custodiar",nullable = false,length = 1)
    boolean custodiar;

  /** Indica en quines pàgines s'ha de mostrar el missatge i el codi de barres. 
   - NULL = no mostrar
   - '*' = totes les pàgines
   - 0 =primera pàgina (taula de firmes)
   - '-1' = darrera pàgina (taula de firmes)
   - '-1, 0, 1, 3, 4-5, 8-' =format Imprimir */
    @Column(name="pagines",nullable = false,length = 255)
    java.lang.String pagines;

  /** Properties amb <idioma>=<missatge>
El missatge de custòdia a mostrar en el document pot contenir els següents elements variables: 
   {0} = URL validació
   {1} = Identificador del document en el Sistema de Custodia
   {2} = Identificador del Plugin de Custodia
   {3} = Data  amb hora
   {4} = Valor retornat pel Plugin de Custodia */
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

    @Column(name="csv",length = 500)
    java.lang.String csv;

    @Column(name="csvvalidationweb",length = 500)
    java.lang.String csvValidationWeb;

    @Column(name="csvgenerationdefinition",length = 500)
    java.lang.String csvGenerationDefinition;

  /** URL retornada pel sistema de custòdia per accedir al document */
    @Column(name="urlfitxercustodiat",length = 500)
    java.lang.String urlFitxerCustodiat;

    @Column(name="originalfiledirecturl",length = 500)
    java.lang.String originalFileDirectUrl;

    @Column(name="printablefiledirecturl",length = 500)
    java.lang.String printableFileDirectUrl;

    @Column(name="enifiledirecturl",length = 500)
    java.lang.String eniFileDirectUrl;

  /** futura compatibilitat amb plugin arxiu */
    @Column(name="expedientid",length = 250)
    java.lang.String expedientArxiuId;

  /** Futura compatibilitat amb Plugin Arxiu */
    @Column(name="documentid",length = 250)
    java.lang.String documentArxiuId;



  /** Constructor Buit */
  public CustodiaInfoJPA() {
  }

  /** Constructor amb tots els camps  */
  public CustodiaInfoJPA(long custodiaInfoID , java.lang.String nomPlantilla , java.lang.String custodiaDocumentID , long pluginID , java.lang.String custodiaPluginParameters , boolean custodiar , java.lang.String pagines , java.lang.String missatge , long missatgePosicioPaginaID , java.lang.String codiBarresID , long codiBarresPosicioPaginaID , java.lang.String codiBarresText , java.lang.String usuariEntitatID , java.lang.String usuariAplicacioID , java.lang.String entitatID , java.lang.String titolPeticio , java.sql.Timestamp dataCustodia , boolean editable , java.lang.String csv , java.lang.String csvValidationWeb , java.lang.String csvGenerationDefinition , java.lang.String urlFitxerCustodiat , java.lang.String originalFileDirectUrl , java.lang.String printableFileDirectUrl , java.lang.String eniFileDirectUrl , java.lang.String expedientArxiuId , java.lang.String documentArxiuId) {
    this.custodiaInfoID=custodiaInfoID;
    this.nomPlantilla=nomPlantilla;
    this.custodiaDocumentID=custodiaDocumentID;
    this.pluginID=pluginID;
    this.custodiaPluginParameters=custodiaPluginParameters;
    this.custodiar=custodiar;
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
    this.csv=csv;
    this.csvValidationWeb=csvValidationWeb;
    this.csvGenerationDefinition=csvGenerationDefinition;
    this.urlFitxerCustodiat=urlFitxerCustodiat;
    this.originalFileDirectUrl=originalFileDirectUrl;
    this.printableFileDirectUrl=printableFileDirectUrl;
    this.eniFileDirectUrl=eniFileDirectUrl;
    this.expedientArxiuId=expedientArxiuId;
    this.documentArxiuId=documentArxiuId;
}
  /** Constructor sense valors autoincrementals */
  public CustodiaInfoJPA(java.lang.String nomPlantilla , java.lang.String custodiaDocumentID , long pluginID , java.lang.String custodiaPluginParameters , boolean custodiar , java.lang.String pagines , java.lang.String missatge , long missatgePosicioPaginaID , java.lang.String codiBarresID , long codiBarresPosicioPaginaID , java.lang.String codiBarresText , java.lang.String usuariEntitatID , java.lang.String usuariAplicacioID , java.lang.String entitatID , java.lang.String titolPeticio , java.sql.Timestamp dataCustodia , boolean editable , java.lang.String csv , java.lang.String csvValidationWeb , java.lang.String csvGenerationDefinition , java.lang.String urlFitxerCustodiat , java.lang.String originalFileDirectUrl , java.lang.String printableFileDirectUrl , java.lang.String eniFileDirectUrl , java.lang.String expedientArxiuId , java.lang.String documentArxiuId) {
    this.nomPlantilla=nomPlantilla;
    this.custodiaDocumentID=custodiaDocumentID;
    this.pluginID=pluginID;
    this.custodiaPluginParameters=custodiaPluginParameters;
    this.custodiar=custodiar;
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
    this.csv=csv;
    this.csvValidationWeb=csvValidationWeb;
    this.csvGenerationDefinition=csvGenerationDefinition;
    this.urlFitxerCustodiat=urlFitxerCustodiat;
    this.originalFileDirectUrl=originalFileDirectUrl;
    this.printableFileDirectUrl=printableFileDirectUrl;
    this.eniFileDirectUrl=eniFileDirectUrl;
    this.expedientArxiuId=expedientArxiuId;
    this.documentArxiuId=documentArxiuId;
}
  /** Constructor dels valors Not Null */
  public CustodiaInfoJPA(long custodiaInfoID , long pluginID , boolean custodiar , java.lang.String pagines , java.lang.String missatge , long missatgePosicioPaginaID , java.lang.String codiBarresID , long codiBarresPosicioPaginaID , java.lang.String codiBarresText , boolean editable) {
    this.custodiaInfoID=custodiaInfoID;
    this.pluginID=pluginID;
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
    this.setNomPlantilla(__bean.getNomPlantilla());
    this.setCustodiaDocumentID(__bean.getCustodiaDocumentID());
    this.setPluginID(__bean.getPluginID());
    this.setCustodiaPluginParameters(__bean.getCustodiaPluginParameters());
    this.setCustodiar(__bean.isCustodiar());
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
    this.setCsv(__bean.getCsv());
    this.setCsvValidationWeb(__bean.getCsvValidationWeb());
    this.setCsvGenerationDefinition(__bean.getCsvGenerationDefinition());
    this.setUrlFitxerCustodiat(__bean.getUrlFitxerCustodiat());
    this.setOriginalFileDirectUrl(__bean.getOriginalFileDirectUrl());
    this.setPrintableFileDirectUrl(__bean.getPrintableFileDirectUrl());
    this.setEniFileDirectUrl(__bean.getEniFileDirectUrl());
    this.setExpedientArxiuId(__bean.getExpedientArxiuId());
    this.setDocumentArxiuId(__bean.getDocumentArxiuId());
	}

	public long getCustodiaInfoID() {
		return(custodiaInfoID);
	};
	public void setCustodiaInfoID(long _custodiaInfoID_) {
		this.custodiaInfoID = _custodiaInfoID_;
	};

	public java.lang.String getNomPlantilla() {
		return(nomPlantilla);
	};
	public void setNomPlantilla(java.lang.String _nomPlantilla_) {
		this.nomPlantilla = _nomPlantilla_;
	};

	public java.lang.String getCustodiaDocumentID() {
		return(custodiaDocumentID);
	};
	public void setCustodiaDocumentID(java.lang.String _custodiaDocumentID_) {
		this.custodiaDocumentID = _custodiaDocumentID_;
	};

	public long getPluginID() {
		return(pluginID);
	};
	public void setPluginID(long _pluginID_) {
		this.pluginID = _pluginID_;
	};

	public java.lang.String getCustodiaPluginParameters() {
		return(custodiaPluginParameters);
	};
	public void setCustodiaPluginParameters(java.lang.String _custodiaPluginParameters_) {
		this.custodiaPluginParameters = _custodiaPluginParameters_;
	};

	public boolean isCustodiar() {
		return(custodiar);
	};
	public void setCustodiar(boolean _custodiar_) {
		this.custodiar = _custodiar_;
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

	public java.lang.String getCsv() {
		return(csv);
	};
	public void setCsv(java.lang.String _csv_) {
		this.csv = _csv_;
	};

	public java.lang.String getCsvValidationWeb() {
		return(csvValidationWeb);
	};
	public void setCsvValidationWeb(java.lang.String _csvValidationWeb_) {
		this.csvValidationWeb = _csvValidationWeb_;
	};

	public java.lang.String getCsvGenerationDefinition() {
		return(csvGenerationDefinition);
	};
	public void setCsvGenerationDefinition(java.lang.String _csvGenerationDefinition_) {
		this.csvGenerationDefinition = _csvGenerationDefinition_;
	};

	public java.lang.String getUrlFitxerCustodiat() {
		return(urlFitxerCustodiat);
	};
	public void setUrlFitxerCustodiat(java.lang.String _urlFitxerCustodiat_) {
		this.urlFitxerCustodiat = _urlFitxerCustodiat_;
	};

	public java.lang.String getOriginalFileDirectUrl() {
		return(originalFileDirectUrl);
	};
	public void setOriginalFileDirectUrl(java.lang.String _originalFileDirectUrl_) {
		this.originalFileDirectUrl = _originalFileDirectUrl_;
	};

	public java.lang.String getPrintableFileDirectUrl() {
		return(printableFileDirectUrl);
	};
	public void setPrintableFileDirectUrl(java.lang.String _printableFileDirectUrl_) {
		this.printableFileDirectUrl = _printableFileDirectUrl_;
	};

	public java.lang.String getEniFileDirectUrl() {
		return(eniFileDirectUrl);
	};
	public void setEniFileDirectUrl(java.lang.String _eniFileDirectUrl_) {
		this.eniFileDirectUrl = _eniFileDirectUrl_;
	};

	public java.lang.String getExpedientArxiuId() {
		return(expedientArxiuId);
	};
	public void setExpedientArxiuId(java.lang.String _expedientArxiuId_) {
		this.expedientArxiuId = _expedientArxiuId_;
	};

	public java.lang.String getDocumentArxiuId() {
		return(documentArxiuId);
	};
	public void setDocumentArxiuId(java.lang.String _documentArxiuId_) {
		this.documentArxiuId = _documentArxiuId_;
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

// EXP  Field:custodiainfoid | Table: pfi_entitat | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "custodiaInfo")
    private Set<EntitatJPA> entitats = new HashSet<EntitatJPA>(0);
    public  Set<EntitatJPA> getEntitats() {
    return this.entitats;
  }

    public void setEntitats(Set<EntitatJPA> entitats) {
      this.entitats = entitats;
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


// EXP  Field:custodiainfoid | Table: pfi_usuariaplicacio | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "custodiaInfo")
    private Set<UsuariAplicacioJPA> usuariAplicacios = new HashSet<UsuariAplicacioJPA>(0);
    public  Set<UsuariAplicacioJPA> getUsuariAplicacios() {
    return this.usuariAplicacios;
  }

    public void setUsuariAplicacios(Set<UsuariAplicacioJPA> usuariAplicacios) {
      this.usuariAplicacios = usuariAplicacios;
    }


// EXP  Field:custodiainfoid | Table: pfi_usuarientitat | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "custodiaInfo")
    private Set<UsuariEntitatJPA> usuariEntitats = new HashSet<UsuariEntitatJPA>(0);
    public  Set<UsuariEntitatJPA> getUsuariEntitats() {
    return this.usuariEntitats;
  }

    public void setUsuariEntitats(Set<UsuariEntitatJPA> usuariEntitats) {
      this.usuariEntitats = usuariEntitats;
    }


// IMP Field:pluginid | Table: pfi_plugin | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @ForeignKey(name="pfi_custodia_plugin_fk")
    @JoinColumn(name = "pluginid", referencedColumnName ="pluginID", nullable = false, insertable=false, updatable=false)
    private PluginJPA plugin;

    public PluginJPA getPlugin() {
    return this.plugin;
  }

    public  void setPlugin(PluginJPA plugin) {
    this.plugin = plugin;
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


 // ---------------  STATIC METHODS ------------------
  public static CustodiaInfoJPA toJPA(CustodiaInfo __bean) {
    if (__bean == null) { return null;}
    CustodiaInfoJPA __tmp = new CustodiaInfoJPA();
    __tmp.setCustodiaInfoID(__bean.getCustodiaInfoID());
    __tmp.setNomPlantilla(__bean.getNomPlantilla());
    __tmp.setCustodiaDocumentID(__bean.getCustodiaDocumentID());
    __tmp.setPluginID(__bean.getPluginID());
    __tmp.setCustodiaPluginParameters(__bean.getCustodiaPluginParameters());
    __tmp.setCustodiar(__bean.isCustodiar());
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
    __tmp.setCsv(__bean.getCsv());
    __tmp.setCsvValidationWeb(__bean.getCsvValidationWeb());
    __tmp.setCsvGenerationDefinition(__bean.getCsvGenerationDefinition());
    __tmp.setUrlFitxerCustodiat(__bean.getUrlFitxerCustodiat());
    __tmp.setOriginalFileDirectUrl(__bean.getOriginalFileDirectUrl());
    __tmp.setPrintableFileDirectUrl(__bean.getPrintableFileDirectUrl());
    __tmp.setEniFileDirectUrl(__bean.getEniFileDirectUrl());
    __tmp.setExpedientArxiuId(__bean.getExpedientArxiuId());
    __tmp.setDocumentArxiuId(__bean.getDocumentArxiuId());
		return __tmp;
	}


  public static CustodiaInfoJPA copyJPA(CustodiaInfoJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<CustodiaInfoJPA> copyJPA(java.util.Set<CustodiaInfoJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    @SuppressWarnings("unchecked")
    java.util.Set<CustodiaInfoJPA> __tmpSet = (java.util.Set<CustodiaInfoJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<CustodiaInfoJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (CustodiaInfoJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static CustodiaInfoJPA copyJPA(CustodiaInfoJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    CustodiaInfoJPA __tmp = (CustodiaInfoJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"EntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitats) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitats())) ) {
      __tmp.setEntitats(EntitatJPA.copyJPA(__jpa.getEntitats(), __alreadyCopied,"CustodiaInfoJPA"));
    }
    if(!"UsuariAplicacioJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariAplicacios) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariAplicacios())) ) {
      __tmp.setUsuariAplicacios(UsuariAplicacioJPA.copyJPA(__jpa.getUsuariAplicacios(), __alreadyCopied,"CustodiaInfoJPA"));
    }
    if(!"UsuariEntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariEntitats) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariEntitats())) ) {
      __tmp.setUsuariEntitats(UsuariEntitatJPA.copyJPA(__jpa.getUsuariEntitats(), __alreadyCopied,"CustodiaInfoJPA"));
    }
    if(!"PeticioDeFirmaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.peticioDeFirmas) || org.hibernate.Hibernate.isInitialized(__jpa.getPeticioDeFirmas())) ) {
      __tmp.setPeticioDeFirmas(PeticioDeFirmaJPA.copyJPA(__jpa.getPeticioDeFirmas(), __alreadyCopied,"CustodiaInfoJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"EntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat()) ) ) {
      __tmp.setEntitat(EntitatJPA.copyJPA(__jpa.getEntitat(), __alreadyCopied,"CustodiaInfoJPA"));
    }
    if(!"CodiBarresJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.codiBarres) || org.hibernate.Hibernate.isInitialized(__jpa.getCodiBarres()) ) ) {
      __tmp.setCodiBarres(CodiBarresJPA.copyJPA(__jpa.getCodiBarres(), __alreadyCopied,"CustodiaInfoJPA"));
    }
    if(!"UsuariAplicacioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariAplicacio) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariAplicacio()) ) ) {
      __tmp.setUsuariAplicacio(UsuariAplicacioJPA.copyJPA(__jpa.getUsuariAplicacio(), __alreadyCopied,"CustodiaInfoJPA"));
    }
    if(!"UsuariEntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariEntitat) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariEntitat()) ) ) {
      __tmp.setUsuariEntitat(UsuariEntitatJPA.copyJPA(__jpa.getUsuariEntitat(), __alreadyCopied,"CustodiaInfoJPA"));
    }
    if(!"PluginJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.plugin) || org.hibernate.Hibernate.isInitialized(__jpa.getPlugin()) ) ) {
      __tmp.setPlugin(PluginJPA.copyJPA(__jpa.getPlugin(), __alreadyCopied,"CustodiaInfoJPA"));
    }

    return __tmp;
  }




}
