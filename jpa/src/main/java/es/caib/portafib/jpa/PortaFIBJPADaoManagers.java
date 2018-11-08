package es.caib.portafib.jpa;

import es.caib.portafib.model.*;
import es.caib.portafib.model.dao.*;
import javax.persistence.EntityManager;

public final class PortaFIBJPADaoManagers implements IPortaFIBDaoManagers{

   private final AnnexJPAManager pfi_annex;
   private final AnnexFirmatJPAManager pfi_annexfirmat;
   private final BitacolaJPAManager pfi_bitacola;
   private final BlocDeFirmesJPAManager pfi_blocdefirmes;
   private final CodiBarresJPAManager pfi_codibarres;
   private final ColaboracioDelegacioJPAManager pfi_colaboraciodelegacio;
   private final CustodiaInfoJPAManager pfi_custodiainfo;
   private final EntitatJPAManager pfi_entitat;
   private final EstadisticaJPAManager pfi_estadistica;
   private final EstatDeFirmaJPAManager pfi_estatdefirma;
   private final FirmaJPAManager pfi_firma;
   private final FitxerJPAManager pfi_fitxer;
   private final FluxDeFirmesJPAManager pfi_fluxdefirmes;
   private final GrupEntitatJPAManager pfi_grupentitat;
   private final GrupEntitatUsuariEntitatJPAManager pfi_grupentitatusuarientitat;
   private final IdiomaJPAManager pfi_idioma;
   private final MetadadaJPAManager pfi_metadada;
   private final ModulDeFirmaPerTipusDeDocumentJPAManager pfi_modulfirmapertipusdoc;
   private final NotificacioWSJPAManager pfi_notificacio;
   private final PermisGrupPlantillaJPAManager pfi_permisgrupplantilla;
   private final PermisUsuariPlantillaJPAManager pfi_permisusuariplantilla;
   private final PeticioDeFirmaJPAManager pfi_peticiodefirma;
   private final PlantillaFluxDeFirmesJPAManager pfi_plantillafluxdefirmes;
   private final PluginJPAManager pfi_plugin;
   private final PluginCridadaJPAManager pfi_plugincridada;
   private final PluginFirmaWebPerUsuariAplicacioJPAManager pfi_pluginfirmawebperusrapp;
   private final PluginFirmaWebPerUsuariEntitatJPAManager pfi_pluginfirmawebperusrent;
   private final PropietatGlobalJPAManager pfi_propietatglobal;
   private final RebreAvisJPAManager pfi_rebreavis;
   private final RevisorDeFirmaJPAManager pfi_revisordefirma;
   private final RoleJPAManager pfi_role;
   private final RoleUsuariAplicacioJPAManager pfi_roleusuariaplicacio;
   private final RoleUsuariEntitatJPAManager pfi_roleusuarientitat;
   private final TipusDocumentJPAManager pfi_tipusdocument;
   private final TipusDocumentColaboracioDelegacioJPAManager pfi_tipusdocumentcoladele;
   private final TipusNotificacioJPAManager pfi_tipusnotificacio;
   private final TraduccioJPAManager pfi_traduccio;
   private final UsuariAplicacioJPAManager pfi_usuariaplicacio;
   private final UsuariAplicacioConfiguracioJPAManager pfi_usuariaplicacioconfig;
   private final UsuariEntitatJPAManager pfi_usuarientitat;
   private final UsuariEntitatFavoritJPAManager pfi_usuarientitatfavorit;
   private final UsuariPersonaJPAManager pfi_usuaripersona;

  public  PortaFIBJPADaoManagers(EntityManager __em) {
    this.pfi_annex = new AnnexJPAManager(__em);
    this.pfi_annexfirmat = new AnnexFirmatJPAManager(__em);
    this.pfi_bitacola = new BitacolaJPAManager(__em);
    this.pfi_blocdefirmes = new BlocDeFirmesJPAManager(__em);
    this.pfi_codibarres = new CodiBarresJPAManager(__em);
    this.pfi_colaboraciodelegacio = new ColaboracioDelegacioJPAManager(__em);
    this.pfi_custodiainfo = new CustodiaInfoJPAManager(__em);
    this.pfi_entitat = new EntitatJPAManager(__em);
    this.pfi_estadistica = new EstadisticaJPAManager(__em);
    this.pfi_estatdefirma = new EstatDeFirmaJPAManager(__em);
    this.pfi_firma = new FirmaJPAManager(__em);
    this.pfi_fitxer = new FitxerJPAManager(__em);
    this.pfi_fluxdefirmes = new FluxDeFirmesJPAManager(__em);
    this.pfi_grupentitat = new GrupEntitatJPAManager(__em);
    this.pfi_grupentitatusuarientitat = new GrupEntitatUsuariEntitatJPAManager(__em);
    this.pfi_idioma = new IdiomaJPAManager(__em);
    this.pfi_metadada = new MetadadaJPAManager(__em);
    this.pfi_modulfirmapertipusdoc = new ModulDeFirmaPerTipusDeDocumentJPAManager(__em);
    this.pfi_notificacio = new NotificacioWSJPAManager(__em);
    this.pfi_permisgrupplantilla = new PermisGrupPlantillaJPAManager(__em);
    this.pfi_permisusuariplantilla = new PermisUsuariPlantillaJPAManager(__em);
    this.pfi_peticiodefirma = new PeticioDeFirmaJPAManager(__em);
    this.pfi_plantillafluxdefirmes = new PlantillaFluxDeFirmesJPAManager(__em);
    this.pfi_plugin = new PluginJPAManager(__em);
    this.pfi_plugincridada = new PluginCridadaJPAManager(__em);
    this.pfi_pluginfirmawebperusrapp = new PluginFirmaWebPerUsuariAplicacioJPAManager(__em);
    this.pfi_pluginfirmawebperusrent = new PluginFirmaWebPerUsuariEntitatJPAManager(__em);
    this.pfi_propietatglobal = new PropietatGlobalJPAManager(__em);
    this.pfi_rebreavis = new RebreAvisJPAManager(__em);
    this.pfi_revisordefirma = new RevisorDeFirmaJPAManager(__em);
    this.pfi_role = new RoleJPAManager(__em);
    this.pfi_roleusuariaplicacio = new RoleUsuariAplicacioJPAManager(__em);
    this.pfi_roleusuarientitat = new RoleUsuariEntitatJPAManager(__em);
    this.pfi_tipusdocument = new TipusDocumentJPAManager(__em);
    this.pfi_tipusdocumentcoladele = new TipusDocumentColaboracioDelegacioJPAManager(__em);
    this.pfi_tipusnotificacio = new TipusNotificacioJPAManager(__em);
    this.pfi_traduccio = new TraduccioJPAManager(__em);
    this.pfi_usuariaplicacio = new UsuariAplicacioJPAManager(__em);
    this.pfi_usuariaplicacioconfig = new UsuariAplicacioConfiguracioJPAManager(__em);
    this.pfi_usuarientitat = new UsuariEntitatJPAManager(__em);
    this.pfi_usuarientitatfavorit = new UsuariEntitatFavoritJPAManager(__em);
    this.pfi_usuaripersona = new UsuariPersonaJPAManager(__em);
  }

	public IAnnexManager getAnnexManager() {
	  return this.pfi_annex;
	};

	public IAnnexFirmatManager getAnnexFirmatManager() {
	  return this.pfi_annexfirmat;
	};

	public IBitacolaManager getBitacolaManager() {
	  return this.pfi_bitacola;
	};

	public IBlocDeFirmesManager getBlocDeFirmesManager() {
	  return this.pfi_blocdefirmes;
	};

	public ICodiBarresManager getCodiBarresManager() {
	  return this.pfi_codibarres;
	};

	public IColaboracioDelegacioManager getColaboracioDelegacioManager() {
	  return this.pfi_colaboraciodelegacio;
	};

	public ICustodiaInfoManager getCustodiaInfoManager() {
	  return this.pfi_custodiainfo;
	};

	public IEntitatManager getEntitatManager() {
	  return this.pfi_entitat;
	};

	public IEstadisticaManager getEstadisticaManager() {
	  return this.pfi_estadistica;
	};

	public IEstatDeFirmaManager getEstatDeFirmaManager() {
	  return this.pfi_estatdefirma;
	};

	public IFirmaManager getFirmaManager() {
	  return this.pfi_firma;
	};

	public IFitxerManager getFitxerManager() {
	  return this.pfi_fitxer;
	};

	public IFluxDeFirmesManager getFluxDeFirmesManager() {
	  return this.pfi_fluxdefirmes;
	};

	public IGrupEntitatManager getGrupEntitatManager() {
	  return this.pfi_grupentitat;
	};

	public IGrupEntitatUsuariEntitatManager getGrupEntitatUsuariEntitatManager() {
	  return this.pfi_grupentitatusuarientitat;
	};

	public IIdiomaManager getIdiomaManager() {
	  return this.pfi_idioma;
	};

	public IMetadadaManager getMetadadaManager() {
	  return this.pfi_metadada;
	};

	public IModulDeFirmaPerTipusDeDocumentManager getModulDeFirmaPerTipusDeDocumentManager() {
	  return this.pfi_modulfirmapertipusdoc;
	};

	public INotificacioWSManager getNotificacioWSManager() {
	  return this.pfi_notificacio;
	};

	public IPermisGrupPlantillaManager getPermisGrupPlantillaManager() {
	  return this.pfi_permisgrupplantilla;
	};

	public IPermisUsuariPlantillaManager getPermisUsuariPlantillaManager() {
	  return this.pfi_permisusuariplantilla;
	};

	public IPeticioDeFirmaManager getPeticioDeFirmaManager() {
	  return this.pfi_peticiodefirma;
	};

	public IPlantillaFluxDeFirmesManager getPlantillaFluxDeFirmesManager() {
	  return this.pfi_plantillafluxdefirmes;
	};

	public IPluginManager getPluginManager() {
	  return this.pfi_plugin;
	};

	public IPluginCridadaManager getPluginCridadaManager() {
	  return this.pfi_plugincridada;
	};

	public IPluginFirmaWebPerUsuariAplicacioManager getPluginFirmaWebPerUsuariAplicacioManager() {
	  return this.pfi_pluginfirmawebperusrapp;
	};

	public IPluginFirmaWebPerUsuariEntitatManager getPluginFirmaWebPerUsuariEntitatManager() {
	  return this.pfi_pluginfirmawebperusrent;
	};

	public IPropietatGlobalManager getPropietatGlobalManager() {
	  return this.pfi_propietatglobal;
	};

	public IRebreAvisManager getRebreAvisManager() {
	  return this.pfi_rebreavis;
	};

	public IRevisorDeFirmaManager getRevisorDeFirmaManager() {
	  return this.pfi_revisordefirma;
	};

	public IRoleManager getRoleManager() {
	  return this.pfi_role;
	};

	public IRoleUsuariAplicacioManager getRoleUsuariAplicacioManager() {
	  return this.pfi_roleusuariaplicacio;
	};

	public IRoleUsuariEntitatManager getRoleUsuariEntitatManager() {
	  return this.pfi_roleusuarientitat;
	};

	public ITipusDocumentManager getTipusDocumentManager() {
	  return this.pfi_tipusdocument;
	};

	public ITipusDocumentColaboracioDelegacioManager getTipusDocumentColaboracioDelegacioManager() {
	  return this.pfi_tipusdocumentcoladele;
	};

	public ITipusNotificacioManager getTipusNotificacioManager() {
	  return this.pfi_tipusnotificacio;
	};

	public ITraduccioManager getTraduccioManager() {
	  return this.pfi_traduccio;
	};

	public IUsuariAplicacioManager getUsuariAplicacioManager() {
	  return this.pfi_usuariaplicacio;
	};

	public IUsuariAplicacioConfiguracioManager getUsuariAplicacioConfiguracioManager() {
	  return this.pfi_usuariaplicacioconfig;
	};

	public IUsuariEntitatManager getUsuariEntitatManager() {
	  return this.pfi_usuarientitat;
	};

	public IUsuariEntitatFavoritManager getUsuariEntitatFavoritManager() {
	  return this.pfi_usuarientitatfavorit;
	};

	public IUsuariPersonaManager getUsuariPersonaManager() {
	  return this.pfi_usuaripersona;
	};


}