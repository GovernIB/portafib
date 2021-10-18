
package es.caib.portafib.persistence;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Set;
import org.hibernate.annotations.Type;
import java.util.HashSet;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.Id;


@SuppressWarnings("deprecation")
@Entity
@Table(name = "pfi_usuariaplicacioconfig" )
@SequenceGenerator(name="USUARIAPLICACIOCONFIGURACIO_SEQ", sequenceName="pfi_usuariaplicacioconfig_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class UsuariAplicacioConfiguracioJPA implements UsuariAplicacioConfiguracio {



private static final long serialVersionUID = 2088976150L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="USUARIAPLICACIOCONFIGURACIO_SEQ")
    @Index(name="pfi_usuariaplicacioconfig_pk_i")
    @Column(name="usuariaplicacioconfigid",nullable = false,length = 19)
    long usuariAplicacioConfigID;

    @Column(name="nom",nullable = false,length = 255)
    java.lang.String nom;

    @Index(name="pfi_confapp_entitatid_fk_i")
    @Column(name="entitatid",nullable = false,length = 50)
    java.lang.String entitatID;

    @Column(name="usenfirmaapisimpleservidor",nullable = false,length = 1)
    boolean usEnFirmaApiSimpleServidor;

    @Column(name="usenfirmaapisimpleweb",nullable = false,length = 1)
    boolean usEnFirmaApiSimpleWeb;

    @Column(name="usenfirmaweb",nullable = false,length = 1)
    boolean usEnFirmaWeb;

    @Column(name="usenfirmaws1",nullable = false,length = 1)
    boolean usEnFirmaWS1;

    @Column(name="usenfirmaws2",nullable = false,length = 1)
    boolean usEnFirmaAsyncRest2;

    @Column(name="usenfirmapassarelaservidor",nullable = false,length = 1)
    boolean usEnFirmaPassarelaServidor;

    @Column(name="usenfirmapassarelaweb",nullable = false,length = 1)
    boolean usEnFirmaPassarelaWeb;

    @Column(name="filtrecertificats",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String filtreCertificats;

  /** 0 firma, 1 contrafirma 2, cofirma */
    @Column(name="tipusoperaciofirma",nullable = false,length = 10)
    int tipusOperacioFirma;

    @Index(name="pfi_confapp_tipusfirma_fk_i")
    @Column(name="tipusfirmaid",nullable = false,length = 10)
    int tipusFirmaID;

    @Index(name="pfi_confapp_algofirma_fk_i")
    @Column(name="algorismedefirmaid",length = 10)
    java.lang.Integer algorismeDeFirmaID;

    @Column(name="modedefirma",nullable = false,length = 1)
    boolean modeDeFirma;

  /** -1=> usar politica de firma de l'entitat, 0 => no usar politica de firma,  1=> usar politica d'aquesta configuracio, 2 => L'usuari web o usuari-app elegeixen la politica de firma */
    @Column(name="uspoliticadefirma",nullable = false,length = 10)
    int usPoliticaDeFirma;

  /** Identificador de la política de firma. Si es defineix aquest valors llavorses generaran 
firmes PAdES-EPES,CAdES-EPES y XAdES-EPES. */
    @Column(name="policyidentifier",length = 100)
    java.lang.String policyIdentifier;

    @Column(name="policyidentifierhash",length = 256)
    java.lang.String policyIdentifierHash;

  /** Indica el algoritmo utilizado para generar la Huella Digital definida en
el campo policyIdentifierHash.  Es obligatorio indicar este parámetro 
si se indicó también policyIdentifier. Los valores posibles son: 
SHA1, SHA-256, SHA-384 o SHA-512 */
    @Column(name="policyidentifierhashalgorithm",length = 50)
    java.lang.String policyIdentifierHashAlgorithm;

  /** URL (universalmente accesible) hacia el documento (normalmente PDF) que
contiene una descripción textual de la política de firma. Este parámetro es
opcional incluso cuando se genera una firma EPES. */
    @Column(name="policyurldocument",length = 255)
    java.lang.String policyUrlDocument;

    @Index(name="pfi_confapp_motiudele_fk_i")
    @Column(name="motiudelegacioid",length = 19)
    java.lang.Long motiuDelegacioID;

  /** -1 definit en l'entitat, 0 no es permet taules de firmes, 1  obligatori politica definida en la configuració d'usuari aplicació o entitat, 2 opcional, per defecte el definit a l'entitat */
    @Column(name="politicataulafirmes",nullable = false,length = 10)
    int politicaTaulaFirmes;

  /** Si val null s'utilitza la info de l'entitat. Valors: SENSETAULA = 0; PRIMERAPAGINA = 1; DARRERAPAGINA = -1;DEFINIT_EN_FIRMA(RUBRICA)=2 */
    @Column(name="posiciotaulafirmesid",nullable = false,length = 10)
    int posicioTaulaFirmesID;

    @Index(name="pfi_confapp_firmatper_fk_i")
    @Column(name="firmatperformatid",length = 19)
    java.lang.Long firmatPerFormatID;

    @Column(name="propietatstaulafirmes",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String propietatsTaulaFirmes;

  /** DEFINIT_EN_ENTITAT=-1;NOUSAR=0;US_OBLIGATORI=1;USUARI_ELEGEIX_PER_DEFECTE_SI=2;USUARI_ELEGEIX_PER_DEFECTE_NO=3; */
    @Column(name="politicasegellatdetemps",nullable = false,length = 10)
    int politicaSegellatDeTemps;

    @Index(name="pfi_confapp_plugsegell_fk_i")
    @Column(name="pluginsegellatid",length = 19)
    java.lang.Long pluginSegellatID;

    @Column(name="htmlperllistarpluginsfirmaweb",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String htmlPerLlistarPluginsFirmaWeb;

    @Index(name="pfi_confapp_firmaserv_fk_i")
    @Column(name="pluginfirmaservidorid",length = 19)
    java.lang.Long pluginFirmaServidorID;

    @Column(name="upgradesignformat",length = 10)
    java.lang.Integer upgradeSignFormat;

  /** Indica si validar la firma amb el Plugin de validació definit a l'entitat */
    @Column(name="validarfirma",length = 1)
    java.lang.Boolean validarFirma;

  /** -- Null => Valor definit a l'entitat */
    @Column(name="checkcanviatdocfirmat",length = 1)
    java.lang.Boolean checkCanviatDocFirmat;

  /** Null => Valor definit a l'entitat */
    @Column(name="comprovarniffirma",length = 1)
    java.lang.Boolean comprovarNifFirma;

  /** NULL => Lo que digui l'entitat */
    @Column(name="validarcertificat",length = 1)
    java.lang.Boolean validarCertificat;

    @Column(name="esdepeticio",nullable = false,length = 1)
    boolean esDePeticio;



  /** Constructor Buit */
  public UsuariAplicacioConfiguracioJPA() {
  }

  /** Constructor amb tots els camps  */
  public UsuariAplicacioConfiguracioJPA(long usuariAplicacioConfigID , java.lang.String nom , java.lang.String entitatID , boolean usEnFirmaApiSimpleServidor , boolean usEnFirmaApiSimpleWeb , boolean usEnFirmaWeb , boolean usEnFirmaWS1 , boolean usEnFirmaAsyncRest2 , boolean usEnFirmaPassarelaServidor , boolean usEnFirmaPassarelaWeb , java.lang.String filtreCertificats , int tipusOperacioFirma , int tipusFirmaID , java.lang.Integer algorismeDeFirmaID , boolean modeDeFirma , int usPoliticaDeFirma , java.lang.String policyIdentifier , java.lang.String policyIdentifierHash , java.lang.String policyIdentifierHashAlgorithm , java.lang.String policyUrlDocument , java.lang.Long motiuDelegacioID , int politicaTaulaFirmes , int posicioTaulaFirmesID , java.lang.Long firmatPerFormatID , java.lang.String propietatsTaulaFirmes , int politicaSegellatDeTemps , java.lang.Long pluginSegellatID , java.lang.String htmlPerLlistarPluginsFirmaWeb , java.lang.Long pluginFirmaServidorID , java.lang.Integer upgradeSignFormat , java.lang.Boolean validarFirma , java.lang.Boolean checkCanviatDocFirmat , java.lang.Boolean comprovarNifFirma , java.lang.Boolean validarCertificat , boolean esDePeticio) {
    this.usuariAplicacioConfigID=usuariAplicacioConfigID;
    this.nom=nom;
    this.entitatID=entitatID;
    this.usEnFirmaApiSimpleServidor=usEnFirmaApiSimpleServidor;
    this.usEnFirmaApiSimpleWeb=usEnFirmaApiSimpleWeb;
    this.usEnFirmaWeb=usEnFirmaWeb;
    this.usEnFirmaWS1=usEnFirmaWS1;
    this.usEnFirmaAsyncRest2=usEnFirmaAsyncRest2;
    this.usEnFirmaPassarelaServidor=usEnFirmaPassarelaServidor;
    this.usEnFirmaPassarelaWeb=usEnFirmaPassarelaWeb;
    this.filtreCertificats=filtreCertificats;
    this.tipusOperacioFirma=tipusOperacioFirma;
    this.tipusFirmaID=tipusFirmaID;
    this.algorismeDeFirmaID=algorismeDeFirmaID;
    this.modeDeFirma=modeDeFirma;
    this.usPoliticaDeFirma=usPoliticaDeFirma;
    this.policyIdentifier=policyIdentifier;
    this.policyIdentifierHash=policyIdentifierHash;
    this.policyIdentifierHashAlgorithm=policyIdentifierHashAlgorithm;
    this.policyUrlDocument=policyUrlDocument;
    this.motiuDelegacioID=motiuDelegacioID;
    this.politicaTaulaFirmes=politicaTaulaFirmes;
    this.posicioTaulaFirmesID=posicioTaulaFirmesID;
    this.firmatPerFormatID=firmatPerFormatID;
    this.propietatsTaulaFirmes=propietatsTaulaFirmes;
    this.politicaSegellatDeTemps=politicaSegellatDeTemps;
    this.pluginSegellatID=pluginSegellatID;
    this.htmlPerLlistarPluginsFirmaWeb=htmlPerLlistarPluginsFirmaWeb;
    this.pluginFirmaServidorID=pluginFirmaServidorID;
    this.upgradeSignFormat=upgradeSignFormat;
    this.validarFirma=validarFirma;
    this.checkCanviatDocFirmat=checkCanviatDocFirmat;
    this.comprovarNifFirma=comprovarNifFirma;
    this.validarCertificat=validarCertificat;
    this.esDePeticio=esDePeticio;
}
  /** Constructor sense valors autoincrementals */
  public UsuariAplicacioConfiguracioJPA(java.lang.String nom , java.lang.String entitatID , boolean usEnFirmaApiSimpleServidor , boolean usEnFirmaApiSimpleWeb , boolean usEnFirmaWeb , boolean usEnFirmaWS1 , boolean usEnFirmaAsyncRest2 , boolean usEnFirmaPassarelaServidor , boolean usEnFirmaPassarelaWeb , java.lang.String filtreCertificats , int tipusOperacioFirma , int tipusFirmaID , java.lang.Integer algorismeDeFirmaID , boolean modeDeFirma , int usPoliticaDeFirma , java.lang.String policyIdentifier , java.lang.String policyIdentifierHash , java.lang.String policyIdentifierHashAlgorithm , java.lang.String policyUrlDocument , java.lang.Long motiuDelegacioID , int politicaTaulaFirmes , int posicioTaulaFirmesID , java.lang.Long firmatPerFormatID , java.lang.String propietatsTaulaFirmes , int politicaSegellatDeTemps , java.lang.Long pluginSegellatID , java.lang.String htmlPerLlistarPluginsFirmaWeb , java.lang.Long pluginFirmaServidorID , java.lang.Integer upgradeSignFormat , java.lang.Boolean validarFirma , java.lang.Boolean checkCanviatDocFirmat , java.lang.Boolean comprovarNifFirma , java.lang.Boolean validarCertificat , boolean esDePeticio) {
    this.nom=nom;
    this.entitatID=entitatID;
    this.usEnFirmaApiSimpleServidor=usEnFirmaApiSimpleServidor;
    this.usEnFirmaApiSimpleWeb=usEnFirmaApiSimpleWeb;
    this.usEnFirmaWeb=usEnFirmaWeb;
    this.usEnFirmaWS1=usEnFirmaWS1;
    this.usEnFirmaAsyncRest2=usEnFirmaAsyncRest2;
    this.usEnFirmaPassarelaServidor=usEnFirmaPassarelaServidor;
    this.usEnFirmaPassarelaWeb=usEnFirmaPassarelaWeb;
    this.filtreCertificats=filtreCertificats;
    this.tipusOperacioFirma=tipusOperacioFirma;
    this.tipusFirmaID=tipusFirmaID;
    this.algorismeDeFirmaID=algorismeDeFirmaID;
    this.modeDeFirma=modeDeFirma;
    this.usPoliticaDeFirma=usPoliticaDeFirma;
    this.policyIdentifier=policyIdentifier;
    this.policyIdentifierHash=policyIdentifierHash;
    this.policyIdentifierHashAlgorithm=policyIdentifierHashAlgorithm;
    this.policyUrlDocument=policyUrlDocument;
    this.motiuDelegacioID=motiuDelegacioID;
    this.politicaTaulaFirmes=politicaTaulaFirmes;
    this.posicioTaulaFirmesID=posicioTaulaFirmesID;
    this.firmatPerFormatID=firmatPerFormatID;
    this.propietatsTaulaFirmes=propietatsTaulaFirmes;
    this.politicaSegellatDeTemps=politicaSegellatDeTemps;
    this.pluginSegellatID=pluginSegellatID;
    this.htmlPerLlistarPluginsFirmaWeb=htmlPerLlistarPluginsFirmaWeb;
    this.pluginFirmaServidorID=pluginFirmaServidorID;
    this.upgradeSignFormat=upgradeSignFormat;
    this.validarFirma=validarFirma;
    this.checkCanviatDocFirmat=checkCanviatDocFirmat;
    this.comprovarNifFirma=comprovarNifFirma;
    this.validarCertificat=validarCertificat;
    this.esDePeticio=esDePeticio;
}
  /** Constructor dels valors Not Null */
  public UsuariAplicacioConfiguracioJPA(long usuariAplicacioConfigID , java.lang.String nom , java.lang.String entitatID , boolean usEnFirmaApiSimpleServidor , boolean usEnFirmaApiSimpleWeb , boolean usEnFirmaWeb , boolean usEnFirmaWS1 , boolean usEnFirmaAsyncRest2 , boolean usEnFirmaPassarelaServidor , boolean usEnFirmaPassarelaWeb , int tipusOperacioFirma , int tipusFirmaID , boolean modeDeFirma , int usPoliticaDeFirma , int politicaTaulaFirmes , int posicioTaulaFirmesID , int politicaSegellatDeTemps , boolean esDePeticio) {
    this.usuariAplicacioConfigID=usuariAplicacioConfigID;
    this.nom=nom;
    this.entitatID=entitatID;
    this.usEnFirmaApiSimpleServidor=usEnFirmaApiSimpleServidor;
    this.usEnFirmaApiSimpleWeb=usEnFirmaApiSimpleWeb;
    this.usEnFirmaWeb=usEnFirmaWeb;
    this.usEnFirmaWS1=usEnFirmaWS1;
    this.usEnFirmaAsyncRest2=usEnFirmaAsyncRest2;
    this.usEnFirmaPassarelaServidor=usEnFirmaPassarelaServidor;
    this.usEnFirmaPassarelaWeb=usEnFirmaPassarelaWeb;
    this.tipusOperacioFirma=tipusOperacioFirma;
    this.tipusFirmaID=tipusFirmaID;
    this.modeDeFirma=modeDeFirma;
    this.usPoliticaDeFirma=usPoliticaDeFirma;
    this.politicaTaulaFirmes=politicaTaulaFirmes;
    this.posicioTaulaFirmesID=posicioTaulaFirmesID;
    this.politicaSegellatDeTemps=politicaSegellatDeTemps;
    this.esDePeticio=esDePeticio;
}
  public UsuariAplicacioConfiguracioJPA(UsuariAplicacioConfiguracio __bean) {
    this.setUsuariAplicacioConfigID(__bean.getUsuariAplicacioConfigID());
    this.setNom(__bean.getNom());
    this.setEntitatID(__bean.getEntitatID());
    this.setUsEnFirmaApiSimpleServidor(__bean.isUsEnFirmaApiSimpleServidor());
    this.setUsEnFirmaApiSimpleWeb(__bean.isUsEnFirmaApiSimpleWeb());
    this.setUsEnFirmaWeb(__bean.isUsEnFirmaWeb());
    this.setUsEnFirmaWS1(__bean.isUsEnFirmaWS1());
    this.setUsEnFirmaAsyncRest2(__bean.isUsEnFirmaAsyncRest2());
    this.setUsEnFirmaPassarelaServidor(__bean.isUsEnFirmaPassarelaServidor());
    this.setUsEnFirmaPassarelaWeb(__bean.isUsEnFirmaPassarelaWeb());
    this.setFiltreCertificats(__bean.getFiltreCertificats());
    this.setTipusOperacioFirma(__bean.getTipusOperacioFirma());
    this.setTipusFirmaID(__bean.getTipusFirmaID());
    this.setAlgorismeDeFirmaID(__bean.getAlgorismeDeFirmaID());
    this.setModeDeFirma(__bean.isModeDeFirma());
    this.setUsPoliticaDeFirma(__bean.getUsPoliticaDeFirma());
    this.setPolicyIdentifier(__bean.getPolicyIdentifier());
    this.setPolicyIdentifierHash(__bean.getPolicyIdentifierHash());
    this.setPolicyIdentifierHashAlgorithm(__bean.getPolicyIdentifierHashAlgorithm());
    this.setPolicyUrlDocument(__bean.getPolicyUrlDocument());
    this.setMotiuDelegacioID(__bean.getMotiuDelegacioID());
    this.setPoliticaTaulaFirmes(__bean.getPoliticaTaulaFirmes());
    this.setPosicioTaulaFirmesID(__bean.getPosicioTaulaFirmesID());
    this.setFirmatPerFormatID(__bean.getFirmatPerFormatID());
    this.setPropietatsTaulaFirmes(__bean.getPropietatsTaulaFirmes());
    this.setPoliticaSegellatDeTemps(__bean.getPoliticaSegellatDeTemps());
    this.setPluginSegellatID(__bean.getPluginSegellatID());
    this.setHtmlPerLlistarPluginsFirmaWeb(__bean.getHtmlPerLlistarPluginsFirmaWeb());
    this.setPluginFirmaServidorID(__bean.getPluginFirmaServidorID());
    this.setUpgradeSignFormat(__bean.getUpgradeSignFormat());
    this.setValidarFirma(__bean.getValidarFirma());
    this.setCheckCanviatDocFirmat(__bean.getCheckCanviatDocFirmat());
    this.setComprovarNifFirma(__bean.getComprovarNifFirma());
    this.setValidarCertificat(__bean.getValidarCertificat());
    this.setEsDePeticio(__bean.isEsDePeticio());
	}

	public long getUsuariAplicacioConfigID() {
		return(usuariAplicacioConfigID);
	};
	public void setUsuariAplicacioConfigID(long _usuariAplicacioConfigID_) {
		this.usuariAplicacioConfigID = _usuariAplicacioConfigID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(java.lang.String _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public boolean isUsEnFirmaApiSimpleServidor() {
		return(usEnFirmaApiSimpleServidor);
	};
	public void setUsEnFirmaApiSimpleServidor(boolean _usEnFirmaApiSimpleServidor_) {
		this.usEnFirmaApiSimpleServidor = _usEnFirmaApiSimpleServidor_;
	};

	public boolean isUsEnFirmaApiSimpleWeb() {
		return(usEnFirmaApiSimpleWeb);
	};
	public void setUsEnFirmaApiSimpleWeb(boolean _usEnFirmaApiSimpleWeb_) {
		this.usEnFirmaApiSimpleWeb = _usEnFirmaApiSimpleWeb_;
	};

	public boolean isUsEnFirmaWeb() {
		return(usEnFirmaWeb);
	};
	public void setUsEnFirmaWeb(boolean _usEnFirmaWeb_) {
		this.usEnFirmaWeb = _usEnFirmaWeb_;
	};

	public boolean isUsEnFirmaWS1() {
		return(usEnFirmaWS1);
	};
	public void setUsEnFirmaWS1(boolean _usEnFirmaWS1_) {
		this.usEnFirmaWS1 = _usEnFirmaWS1_;
	};

	public boolean isUsEnFirmaAsyncRest2() {
		return(usEnFirmaAsyncRest2);
	};
	public void setUsEnFirmaAsyncRest2(boolean _usEnFirmaAsyncRest2_) {
		this.usEnFirmaAsyncRest2 = _usEnFirmaAsyncRest2_;
	};

	public boolean isUsEnFirmaPassarelaServidor() {
		return(usEnFirmaPassarelaServidor);
	};
	public void setUsEnFirmaPassarelaServidor(boolean _usEnFirmaPassarelaServidor_) {
		this.usEnFirmaPassarelaServidor = _usEnFirmaPassarelaServidor_;
	};

	public boolean isUsEnFirmaPassarelaWeb() {
		return(usEnFirmaPassarelaWeb);
	};
	public void setUsEnFirmaPassarelaWeb(boolean _usEnFirmaPassarelaWeb_) {
		this.usEnFirmaPassarelaWeb = _usEnFirmaPassarelaWeb_;
	};

	public java.lang.String getFiltreCertificats() {
		return(filtreCertificats);
	};
	public void setFiltreCertificats(java.lang.String _filtreCertificats_) {
		this.filtreCertificats = _filtreCertificats_;
	};

	public int getTipusOperacioFirma() {
		return(tipusOperacioFirma);
	};
	public void setTipusOperacioFirma(int _tipusOperacioFirma_) {
		this.tipusOperacioFirma = _tipusOperacioFirma_;
	};

	public int getTipusFirmaID() {
		return(tipusFirmaID);
	};
	public void setTipusFirmaID(int _tipusFirmaID_) {
		this.tipusFirmaID = _tipusFirmaID_;
	};

	public java.lang.Integer getAlgorismeDeFirmaID() {
		return(algorismeDeFirmaID);
	};
	public void setAlgorismeDeFirmaID(java.lang.Integer _algorismeDeFirmaID_) {
		this.algorismeDeFirmaID = _algorismeDeFirmaID_;
	};

	public boolean isModeDeFirma() {
		return(modeDeFirma);
	};
	public void setModeDeFirma(boolean _modeDeFirma_) {
		this.modeDeFirma = _modeDeFirma_;
	};

	public int getUsPoliticaDeFirma() {
		return(usPoliticaDeFirma);
	};
	public void setUsPoliticaDeFirma(int _usPoliticaDeFirma_) {
		this.usPoliticaDeFirma = _usPoliticaDeFirma_;
	};

	public java.lang.String getPolicyIdentifier() {
		return(policyIdentifier);
	};
	public void setPolicyIdentifier(java.lang.String _policyIdentifier_) {
		this.policyIdentifier = _policyIdentifier_;
	};

	public java.lang.String getPolicyIdentifierHash() {
		return(policyIdentifierHash);
	};
	public void setPolicyIdentifierHash(java.lang.String _policyIdentifierHash_) {
		this.policyIdentifierHash = _policyIdentifierHash_;
	};

	public java.lang.String getPolicyIdentifierHashAlgorithm() {
		return(policyIdentifierHashAlgorithm);
	};
	public void setPolicyIdentifierHashAlgorithm(java.lang.String _policyIdentifierHashAlgorithm_) {
		this.policyIdentifierHashAlgorithm = _policyIdentifierHashAlgorithm_;
	};

	public java.lang.String getPolicyUrlDocument() {
		return(policyUrlDocument);
	};
	public void setPolicyUrlDocument(java.lang.String _policyUrlDocument_) {
		this.policyUrlDocument = _policyUrlDocument_;
	};

	public java.lang.Long getMotiuDelegacioID() {
		return(motiuDelegacioID);
	};
	public void setMotiuDelegacioID(java.lang.Long _motiuDelegacioID_) {
		this.motiuDelegacioID = _motiuDelegacioID_;
	};

	public int getPoliticaTaulaFirmes() {
		return(politicaTaulaFirmes);
	};
	public void setPoliticaTaulaFirmes(int _politicaTaulaFirmes_) {
		this.politicaTaulaFirmes = _politicaTaulaFirmes_;
	};

	public int getPosicioTaulaFirmesID() {
		return(posicioTaulaFirmesID);
	};
	public void setPosicioTaulaFirmesID(int _posicioTaulaFirmesID_) {
		this.posicioTaulaFirmesID = _posicioTaulaFirmesID_;
	};

	public java.lang.Long getFirmatPerFormatID() {
		return(firmatPerFormatID);
	};
	public void setFirmatPerFormatID(java.lang.Long _firmatPerFormatID_) {
		this.firmatPerFormatID = _firmatPerFormatID_;
	};

	public java.lang.String getPropietatsTaulaFirmes() {
		return(propietatsTaulaFirmes);
	};
	public void setPropietatsTaulaFirmes(java.lang.String _propietatsTaulaFirmes_) {
		this.propietatsTaulaFirmes = _propietatsTaulaFirmes_;
	};

	public int getPoliticaSegellatDeTemps() {
		return(politicaSegellatDeTemps);
	};
	public void setPoliticaSegellatDeTemps(int _politicaSegellatDeTemps_) {
		this.politicaSegellatDeTemps = _politicaSegellatDeTemps_;
	};

	public java.lang.Long getPluginSegellatID() {
		return(pluginSegellatID);
	};
	public void setPluginSegellatID(java.lang.Long _pluginSegellatID_) {
		this.pluginSegellatID = _pluginSegellatID_;
	};

	public java.lang.String getHtmlPerLlistarPluginsFirmaWeb() {
		return(htmlPerLlistarPluginsFirmaWeb);
	};
	public void setHtmlPerLlistarPluginsFirmaWeb(java.lang.String _htmlPerLlistarPluginsFirmaWeb_) {
		this.htmlPerLlistarPluginsFirmaWeb = _htmlPerLlistarPluginsFirmaWeb_;
	};

	public java.lang.Long getPluginFirmaServidorID() {
		return(pluginFirmaServidorID);
	};
	public void setPluginFirmaServidorID(java.lang.Long _pluginFirmaServidorID_) {
		this.pluginFirmaServidorID = _pluginFirmaServidorID_;
	};

	public java.lang.Integer getUpgradeSignFormat() {
		return(upgradeSignFormat);
	};
	public void setUpgradeSignFormat(java.lang.Integer _upgradeSignFormat_) {
		this.upgradeSignFormat = _upgradeSignFormat_;
	};

	public java.lang.Boolean getValidarFirma() {
		return(validarFirma);
	};
	public void setValidarFirma(java.lang.Boolean _validarFirma_) {
		this.validarFirma = _validarFirma_;
	};

	public java.lang.Boolean getCheckCanviatDocFirmat() {
		return(checkCanviatDocFirmat);
	};
	public void setCheckCanviatDocFirmat(java.lang.Boolean _checkCanviatDocFirmat_) {
		this.checkCanviatDocFirmat = _checkCanviatDocFirmat_;
	};

	public java.lang.Boolean getComprovarNifFirma() {
		return(comprovarNifFirma);
	};
	public void setComprovarNifFirma(java.lang.Boolean _comprovarNifFirma_) {
		this.comprovarNifFirma = _comprovarNifFirma_;
	};

	public java.lang.Boolean getValidarCertificat() {
		return(validarCertificat);
	};
	public void setValidarCertificat(java.lang.Boolean _validarCertificat_) {
		this.validarCertificat = _validarCertificat_;
	};

	public boolean isEsDePeticio() {
		return(esDePeticio);
	};
	public void setEsDePeticio(boolean _esDePeticio_) {
		this.esDePeticio = _esDePeticio_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof UsuariAplicacioConfiguracio) {
      UsuariAplicacioConfiguracio __instance = (UsuariAplicacioConfiguracio)__obj;
      __result = true;
      __result = __result && (this.getUsuariAplicacioConfigID() == __instance.getUsuariAplicacioConfigID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:configuraciodefirmaid | Table: pfi_peticiodefirma | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuariAplicacioConfiguracio")
    private Set<PeticioDeFirmaJPA> peticioDeFirmas = new HashSet<PeticioDeFirmaJPA>(0);
    public  Set<PeticioDeFirmaJPA> getPeticioDeFirmas() {
    return this.peticioDeFirmas;
  }

    public void setPeticioDeFirmas(Set<PeticioDeFirmaJPA> peticioDeFirmas) {
      this.peticioDeFirmas = peticioDeFirmas;
    }


// EXP  Field:usrappconfiguracio1id | Table: pfi_usuariaplicacioperfil | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "configuracioDeFirma1ID")
    private Set<PerfilDeFirmaJPA> perfilDeFirma_usrappconfiguracio1ids = new HashSet<PerfilDeFirmaJPA>(0);
    public  Set<PerfilDeFirmaJPA> getPerfilDeFirma_usrappconfiguracio1ids() {
    return this.perfilDeFirma_usrappconfiguracio1ids;
  }

    public void setPerfilDeFirma_usrappconfiguracio1ids(Set<PerfilDeFirmaJPA> perfilDeFirma_usrappconfiguracio1ids) {
      this.perfilDeFirma_usrappconfiguracio1ids = perfilDeFirma_usrappconfiguracio1ids;
    }


// EXP  Field:usrappconfiguracio2id | Table: pfi_usuariaplicacioperfil | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "configuracioDeFirma2ID")
    private Set<PerfilDeFirmaJPA> perfilDeFirma_usrappconfiguracio2ids = new HashSet<PerfilDeFirmaJPA>(0);
    public  Set<PerfilDeFirmaJPA> getPerfilDeFirma_usrappconfiguracio2ids() {
    return this.perfilDeFirma_usrappconfiguracio2ids;
  }

    public void setPerfilDeFirma_usrappconfiguracio2ids(Set<PerfilDeFirmaJPA> perfilDeFirma_usrappconfiguracio2ids) {
      this.perfilDeFirma_usrappconfiguracio2ids = perfilDeFirma_usrappconfiguracio2ids;
    }


// EXP  Field:usrappconfiguracio3id | Table: pfi_usuariaplicacioperfil | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "configuracioDeFirma3ID")
    private Set<PerfilDeFirmaJPA> perfilDeFirma_usrappconfiguracio3ids = new HashSet<PerfilDeFirmaJPA>(0);
    public  Set<PerfilDeFirmaJPA> getPerfilDeFirma_usrappconfiguracio3ids() {
    return this.perfilDeFirma_usrappconfiguracio3ids;
  }

    public void setPerfilDeFirma_usrappconfiguracio3ids(Set<PerfilDeFirmaJPA> perfilDeFirma_usrappconfiguracio3ids) {
      this.perfilDeFirma_usrappconfiguracio3ids = perfilDeFirma_usrappconfiguracio3ids;
    }


// EXP  Field:usrappconfiguracio4id | Table: pfi_usuariaplicacioperfil | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "configuracioDeFirma4ID")
    private Set<PerfilDeFirmaJPA> perfilDeFirma_usrappconfiguracio4ids = new HashSet<PerfilDeFirmaJPA>(0);
    public  Set<PerfilDeFirmaJPA> getPerfilDeFirma_usrappconfiguracio4ids() {
    return this.perfilDeFirma_usrappconfiguracio4ids;
  }

    public void setPerfilDeFirma_usrappconfiguracio4ids(Set<PerfilDeFirmaJPA> perfilDeFirma_usrappconfiguracio4ids) {
      this.perfilDeFirma_usrappconfiguracio4ids = perfilDeFirma_usrappconfiguracio4ids;
    }


// EXP  Field:usrappconfiguracio5id | Table: pfi_usuariaplicacioperfil | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "configuracioDeFirma5ID")
    private Set<PerfilDeFirmaJPA> perfilDeFirma_usrappconfiguracio5ids = new HashSet<PerfilDeFirmaJPA>(0);
    public  Set<PerfilDeFirmaJPA> getPerfilDeFirma_usrappconfiguracio5ids() {
    return this.perfilDeFirma_usrappconfiguracio5ids;
  }

    public void setPerfilDeFirma_usrappconfiguracio5ids(Set<PerfilDeFirmaJPA> perfilDeFirma_usrappconfiguracio5ids) {
      this.perfilDeFirma_usrappconfiguracio5ids = perfilDeFirma_usrappconfiguracio5ids;
    }


// IMP Field:entitatid | Table: pfi_entitat | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @ForeignKey(name="pfi_confapp_entitat_ent_fk")
    @JoinColumn(name = "entitatid", referencedColumnName ="entitatID", nullable = false, insertable=false, updatable=false)
    private EntitatJPA entitat;

    public EntitatJPA getEntitat() {
    return this.entitat;
  }

    public  void setEntitat(EntitatJPA entitat) {
    this.entitat = entitat;
  }

// IMP Field:traduccioid | Table: pfi_traduccio | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER, cascade=javax.persistence.CascadeType.ALL)
    @ForeignKey(name="pfi_confapp_traduccio_moti_fk")
    @JoinColumn(name = "motiudelegacioid", referencedColumnName ="traduccioID", nullable = true, insertable=false, updatable=false)
    private TraduccioJPA motiuDelegacio;

    public TraduccioJPA getMotiuDelegacio() {
    return this.motiuDelegacio;
  }

    public  void setMotiuDelegacio(TraduccioJPA motiuDelegacio) {
    this.motiuDelegacio = motiuDelegacio;
  }

  @javax.xml.bind.annotation.XmlTransient
  public java.util.Map<String, es.caib.portafib.persistence.TraduccioMapJPA> getMotiuDelegacioTraduccions() {
    return this.motiuDelegacio.getTraduccions();
  }

  public void setMotiuDelegacioTraduccions(java.util.Map<String, es.caib.portafib.persistence.TraduccioMapJPA> __traduccions__) {
    this.motiuDelegacio.setTraduccions(__traduccions__);
  }


// IMP Field:traduccioid | Table: pfi_traduccio | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER, cascade=javax.persistence.CascadeType.ALL)
    @ForeignKey(name="pfi_confapp_traduccio_firm_fk")
    @JoinColumn(name = "firmatperformatid", referencedColumnName ="traduccioID", nullable = true, insertable=false, updatable=false)
    private TraduccioJPA firmatPerFormat;

    public TraduccioJPA getFirmatPerFormat() {
    return this.firmatPerFormat;
  }

    public  void setFirmatPerFormat(TraduccioJPA firmatPerFormat) {
    this.firmatPerFormat = firmatPerFormat;
  }

  @javax.xml.bind.annotation.XmlTransient
  public java.util.Map<String, es.caib.portafib.persistence.TraduccioMapJPA> getFirmatPerFormatTraduccions() {
    return this.firmatPerFormat.getTraduccions();
  }

  public void setFirmatPerFormatTraduccions(java.util.Map<String, es.caib.portafib.persistence.TraduccioMapJPA> __traduccions__) {
    this.firmatPerFormat.setTraduccions(__traduccions__);
  }


// IMP Field:pluginid | Table: pfi_plugin | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @ForeignKey(name="pfi_confapp_plugin_seg_fk")
    @JoinColumn(name = "pluginsegellatid", referencedColumnName ="pluginID", nullable = true, insertable=false, updatable=false)
    private PluginJPA pluginSegellat;

    public PluginJPA getPluginSegellat() {
    return this.pluginSegellat;
  }

    public  void setPluginSegellat(PluginJPA pluginSegellat) {
    this.pluginSegellat = pluginSegellat;
  }

// IMP Field:pluginid | Table: pfi_plugin | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @ForeignKey(name="pfi_confapp_plugin_fsrv_fk")
    @JoinColumn(name = "pluginfirmaservidorid", referencedColumnName ="pluginID", nullable = true, insertable=false, updatable=false)
    private PluginJPA pluginFirmaServidor;

    public PluginJPA getPluginFirmaServidor() {
    return this.pluginFirmaServidor;
  }

    public  void setPluginFirmaServidor(PluginJPA pluginFirmaServidor) {
    this.pluginFirmaServidor = pluginFirmaServidor;
  }


 // ---------------  STATIC METHODS ------------------
  public static UsuariAplicacioConfiguracioJPA toJPA(UsuariAplicacioConfiguracio __bean) {
    if (__bean == null) { return null;}
    UsuariAplicacioConfiguracioJPA __tmp = new UsuariAplicacioConfiguracioJPA();
    __tmp.setUsuariAplicacioConfigID(__bean.getUsuariAplicacioConfigID());
    __tmp.setNom(__bean.getNom());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setUsEnFirmaApiSimpleServidor(__bean.isUsEnFirmaApiSimpleServidor());
    __tmp.setUsEnFirmaApiSimpleWeb(__bean.isUsEnFirmaApiSimpleWeb());
    __tmp.setUsEnFirmaWeb(__bean.isUsEnFirmaWeb());
    __tmp.setUsEnFirmaWS1(__bean.isUsEnFirmaWS1());
    __tmp.setUsEnFirmaAsyncRest2(__bean.isUsEnFirmaAsyncRest2());
    __tmp.setUsEnFirmaPassarelaServidor(__bean.isUsEnFirmaPassarelaServidor());
    __tmp.setUsEnFirmaPassarelaWeb(__bean.isUsEnFirmaPassarelaWeb());
    __tmp.setFiltreCertificats(__bean.getFiltreCertificats());
    __tmp.setTipusOperacioFirma(__bean.getTipusOperacioFirma());
    __tmp.setTipusFirmaID(__bean.getTipusFirmaID());
    __tmp.setAlgorismeDeFirmaID(__bean.getAlgorismeDeFirmaID());
    __tmp.setModeDeFirma(__bean.isModeDeFirma());
    __tmp.setUsPoliticaDeFirma(__bean.getUsPoliticaDeFirma());
    __tmp.setPolicyIdentifier(__bean.getPolicyIdentifier());
    __tmp.setPolicyIdentifierHash(__bean.getPolicyIdentifierHash());
    __tmp.setPolicyIdentifierHashAlgorithm(__bean.getPolicyIdentifierHashAlgorithm());
    __tmp.setPolicyUrlDocument(__bean.getPolicyUrlDocument());
    __tmp.setMotiuDelegacioID(__bean.getMotiuDelegacioID());
    __tmp.setPoliticaTaulaFirmes(__bean.getPoliticaTaulaFirmes());
    __tmp.setPosicioTaulaFirmesID(__bean.getPosicioTaulaFirmesID());
    __tmp.setFirmatPerFormatID(__bean.getFirmatPerFormatID());
    __tmp.setPropietatsTaulaFirmes(__bean.getPropietatsTaulaFirmes());
    __tmp.setPoliticaSegellatDeTemps(__bean.getPoliticaSegellatDeTemps());
    __tmp.setPluginSegellatID(__bean.getPluginSegellatID());
    __tmp.setHtmlPerLlistarPluginsFirmaWeb(__bean.getHtmlPerLlistarPluginsFirmaWeb());
    __tmp.setPluginFirmaServidorID(__bean.getPluginFirmaServidorID());
    __tmp.setUpgradeSignFormat(__bean.getUpgradeSignFormat());
    __tmp.setValidarFirma(__bean.getValidarFirma());
    __tmp.setCheckCanviatDocFirmat(__bean.getCheckCanviatDocFirmat());
    __tmp.setComprovarNifFirma(__bean.getComprovarNifFirma());
    __tmp.setValidarCertificat(__bean.getValidarCertificat());
    __tmp.setEsDePeticio(__bean.isEsDePeticio());
		return __tmp;
	}


  public static UsuariAplicacioConfiguracioJPA copyJPA(UsuariAplicacioConfiguracioJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<UsuariAplicacioConfiguracioJPA> copyJPA(java.util.Set<UsuariAplicacioConfiguracioJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    @SuppressWarnings("unchecked")
    java.util.Set<UsuariAplicacioConfiguracioJPA> __tmpSet = (java.util.Set<UsuariAplicacioConfiguracioJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<UsuariAplicacioConfiguracioJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (UsuariAplicacioConfiguracioJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static UsuariAplicacioConfiguracioJPA copyJPA(UsuariAplicacioConfiguracioJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    UsuariAplicacioConfiguracioJPA __tmp = (UsuariAplicacioConfiguracioJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"PerfilDeFirmaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.perfilDeFirma_usrappconfiguracio1ids) || org.hibernate.Hibernate.isInitialized(__jpa.getPerfilDeFirma_usrappconfiguracio1ids())) ) {
      __tmp.setPerfilDeFirma_usrappconfiguracio1ids(PerfilDeFirmaJPA.copyJPA(__jpa.getPerfilDeFirma_usrappconfiguracio1ids(), __alreadyCopied,"UsuariAplicacioConfiguracioJPA"));
    }
    if(!"PerfilDeFirmaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.perfilDeFirma_usrappconfiguracio4ids) || org.hibernate.Hibernate.isInitialized(__jpa.getPerfilDeFirma_usrappconfiguracio4ids())) ) {
      __tmp.setPerfilDeFirma_usrappconfiguracio4ids(PerfilDeFirmaJPA.copyJPA(__jpa.getPerfilDeFirma_usrappconfiguracio4ids(), __alreadyCopied,"UsuariAplicacioConfiguracioJPA"));
    }
    if(!"PerfilDeFirmaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.perfilDeFirma_usrappconfiguracio2ids) || org.hibernate.Hibernate.isInitialized(__jpa.getPerfilDeFirma_usrappconfiguracio2ids())) ) {
      __tmp.setPerfilDeFirma_usrappconfiguracio2ids(PerfilDeFirmaJPA.copyJPA(__jpa.getPerfilDeFirma_usrappconfiguracio2ids(), __alreadyCopied,"UsuariAplicacioConfiguracioJPA"));
    }
    if(!"PerfilDeFirmaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.perfilDeFirma_usrappconfiguracio5ids) || org.hibernate.Hibernate.isInitialized(__jpa.getPerfilDeFirma_usrappconfiguracio5ids())) ) {
      __tmp.setPerfilDeFirma_usrappconfiguracio5ids(PerfilDeFirmaJPA.copyJPA(__jpa.getPerfilDeFirma_usrappconfiguracio5ids(), __alreadyCopied,"UsuariAplicacioConfiguracioJPA"));
    }
    if(!"PeticioDeFirmaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.peticioDeFirmas) || org.hibernate.Hibernate.isInitialized(__jpa.getPeticioDeFirmas())) ) {
      __tmp.setPeticioDeFirmas(PeticioDeFirmaJPA.copyJPA(__jpa.getPeticioDeFirmas(), __alreadyCopied,"UsuariAplicacioConfiguracioJPA"));
    }
    if(!"PerfilDeFirmaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.perfilDeFirma_usrappconfiguracio3ids) || org.hibernate.Hibernate.isInitialized(__jpa.getPerfilDeFirma_usrappconfiguracio3ids())) ) {
      __tmp.setPerfilDeFirma_usrappconfiguracio3ids(PerfilDeFirmaJPA.copyJPA(__jpa.getPerfilDeFirma_usrappconfiguracio3ids(), __alreadyCopied,"UsuariAplicacioConfiguracioJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"TraduccioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.firmatPerFormat) || org.hibernate.Hibernate.isInitialized(__jpa.getFirmatPerFormat()) ) ) {
      __tmp.setFirmatPerFormat(TraduccioJPA.copyJPA(__jpa.getFirmatPerFormat(), __alreadyCopied,"UsuariAplicacioConfiguracioJPA"));
    }
    if(!"PluginJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.pluginSegellat) || org.hibernate.Hibernate.isInitialized(__jpa.getPluginSegellat()) ) ) {
      __tmp.setPluginSegellat(PluginJPA.copyJPA(__jpa.getPluginSegellat(), __alreadyCopied,"UsuariAplicacioConfiguracioJPA"));
    }
    if(!"TraduccioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.motiuDelegacio) || org.hibernate.Hibernate.isInitialized(__jpa.getMotiuDelegacio()) ) ) {
      __tmp.setMotiuDelegacio(TraduccioJPA.copyJPA(__jpa.getMotiuDelegacio(), __alreadyCopied,"UsuariAplicacioConfiguracioJPA"));
    }
    if(!"PluginJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.pluginFirmaServidor) || org.hibernate.Hibernate.isInitialized(__jpa.getPluginFirmaServidor()) ) ) {
      __tmp.setPluginFirmaServidor(PluginJPA.copyJPA(__jpa.getPluginFirmaServidor(), __alreadyCopied,"UsuariAplicacioConfiguracioJPA"));
    }
    if(!"EntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat()) ) ) {
      __tmp.setEntitat(EntitatJPA.copyJPA(__jpa.getEntitat(), __alreadyCopied,"UsuariAplicacioConfiguracioJPA"));
    }

    return __tmp;
  }




}
