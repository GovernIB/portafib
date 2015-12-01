package es.caib.portafib.model;

import es.caib.portafib.model.dao.*;

public interface IPortaFIBDaoManagers {
	public IAlgorismeDeFirmaManager getAlgorismeDeFirmaManager();
	public IAnnexManager getAnnexManager();
	public IAnnexFirmatManager getAnnexFirmatManager();
	public IBitacolaManager getBitacolaManager();
	public IBlocDeFirmesManager getBlocDeFirmesManager();
	public ICodiBarresManager getCodiBarresManager();
	public IColaboracioDelegacioManager getColaboracioDelegacioManager();
	public ICustodiaInfoManager getCustodiaInfoManager();
	public IEntitatManager getEntitatManager();
	public IEstatDeFirmaManager getEstatDeFirmaManager();
	public IFirmaManager getFirmaManager();
	public IFitxerManager getFitxerManager();
	public IFluxDeFirmesManager getFluxDeFirmesManager();
	public IGrupEntitatManager getGrupEntitatManager();
	public IGrupEntitatUsuariEntitatManager getGrupEntitatUsuariEntitatManager();
	public IIdiomaManager getIdiomaManager();
	public IMetadadaManager getMetadadaManager();
	public IModulDeFirmaPerTipusDeDocumentManager getModulDeFirmaPerTipusDeDocumentManager();
	public INotificacioWSManager getNotificacioWSManager();
	public IPermisGrupPlantillaManager getPermisGrupPlantillaManager();
	public IPermisUsuariPlantillaManager getPermisUsuariPlantillaManager();
	public IPeticioDeFirmaManager getPeticioDeFirmaManager();
	public IPlantillaFluxDeFirmesManager getPlantillaFluxDeFirmesManager();
	public IPluginManager getPluginManager();
	public IPosicioPaginaManager getPosicioPaginaManager();
	public IPosicioTaulaFirmesManager getPosicioTaulaFirmesManager();
	public IPrioritatManager getPrioritatManager();
	public IPropietatGlobalManager getPropietatGlobalManager();
	public IRebreAvisManager getRebreAvisManager();
	public IRoleManager getRoleManager();
	public IRoleUsuariAplicacioManager getRoleUsuariAplicacioManager();
	public IRoleUsuariEntitatManager getRoleUsuariEntitatManager();
	public ITipusDocumentManager getTipusDocumentManager();
	public ITipusDocumentColaboracioDelegacioManager getTipusDocumentColaboracioDelegacioManager();
	public ITipusEstatDeFirmaFinalManager getTipusEstatDeFirmaFinalManager();
	public ITipusEstatDeFirmaInicialManager getTipusEstatDeFirmaInicialManager();
	public ITipusEstatPeticioDeFirmaManager getTipusEstatPeticioDeFirmaManager();
	public ITipusFirmaManager getTipusFirmaManager();
	public ITipusMetadadaManager getTipusMetadadaManager();
	public ITipusNotificacioManager getTipusNotificacioManager();
	public ITraduccioManager getTraduccioManager();
	public IUsuariAplicacioManager getUsuariAplicacioManager();
	public IUsuariEntitatManager getUsuariEntitatManager();
	public IUsuariEntitatFavoritManager getUsuariEntitatFavoritManager();
	public IUsuariPersonaManager getUsuariPersonaManager();

}