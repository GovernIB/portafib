package es.caib.portafib.api.interna.secure.comanda.v1;

import java.util.Date;
import java.util.List;

import es.caib.comanda.ms.salut.model.AppInfo;
import es.caib.comanda.ms.salut.model.ContextInfo;
import es.caib.comanda.ms.salut.model.DetallSalut;
import es.caib.comanda.ms.salut.model.EstatSalut;
import es.caib.comanda.ms.salut.model.EstatSalutEnum;
import es.caib.comanda.ms.salut.model.IntegracioApp;
import es.caib.comanda.ms.salut.model.IntegracioInfo;
import es.caib.comanda.ms.salut.model.IntegracioPeticions;
import es.caib.comanda.ms.salut.model.IntegracioSalut;
import es.caib.comanda.ms.salut.model.Manual;
import es.caib.comanda.ms.salut.model.MissatgeSalut;
import es.caib.comanda.ms.salut.model.SalutInfo;
import es.caib.comanda.ms.salut.model.SalutInfo.SalutInfoBuilder;
import es.caib.comanda.ms.salut.model.SalutNivell;
import es.caib.comanda.ms.salut.model.SubsistemaInfo;
import es.caib.comanda.ms.salut.model.SubsistemaSalut;
import es.caib.portafib.commons.utils.Version;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;

import es.caib.comanda.ms.salut.model.AppInfo.AppInfoBuilder;

/**
 * 
 * @author anadal
 * 3 dic 2025 8:40:27
 */
public class SalutService implements SalutApiInterface {

    @Override
    public AppInfo appInfo(javax.servlet.http.HttpServletRequest request) throws java.io.IOException {

        List<ContextInfo> contexts = List.of(
                ContextInfo.builder().codi("BACK").nom("PortaFIB Back & Front")
                        .manuals(List.of(Manual.builder().nom("Documentació PortaFIB Back & Front")
                                .path(PropietatGlobalUtil.getAppUrl() + "/portafibBack").build()))
                        .path(PropietatGlobalUtil.getAppUrl() + "/portafibback").build(),
                ContextInfo.builder().codi("API-INTERNA").nom("PortaFIB Api Interna").manuals(null)
                        .path("/portafibapi/interna").build());

        List<IntegracioInfo> integracions = List.of(
                IntegracioInfo.builder().codi("Comanda").nom("Consultes des de COMANDA")
                        .integracioApp(IntegracioApp.CDO).build(),
                IntegracioInfo.builder().codi("FirmaEnServidor").nom("Firma en Servidor")
                        .integracioApp(IntegracioApp.PFI).build());

        List<SubsistemaInfo> subsistemes = List.of(SubsistemaInfo.builder().codi("Subsistema PFI")
                .nom("No se que és un subsistema intern de l'aplicacioó").build());

        Version versio = new Version();

        AppInfoBuilder builder = AppInfo.builder();
        builder.codi("PFI").contexts(contexts).data(new Date()).integracions(integracions)
                .jdkVersion(versio.getJdkVersion()).nom("PortaFIB").revisio(versio.getBuildTime())
                .subsistemes(subsistemes).versio(versio.getVersion());
        AppInfo appInfo = builder.build();
        return appInfo;
    }

    @Override
    public es.caib.comanda.ms.salut.model.SalutInfo health(javax.servlet.http.HttpServletRequest request)
            throws java.io.IOException {
        SalutInfoBuilder builder = SalutInfo.builder();

        List<DetallSalut> altres = SalutInfo.getInfoSistema();
/*List.of(
                DetallSalut.builder().codi("DETALL1").nom("Detall de salut 1").valor("OK").build(),
                DetallSalut.builder().codi("DETALL2").nom("Detall de salut 2").valor("OK").build()); */

        EstatSalut estat = EstatSalut.builder().estat(EstatSalutEnum.UP).latencia(2).build();

        EstatSalut estatBBDD = EstatSalut.builder().estat(EstatSalutEnum.UP).latencia(2).build();

        IntegracioPeticions ip = IntegracioPeticions.builder().endpoint("http://integracio1")
                .peticionsErrorUltimPeriode(2L).peticionsOkUltimPeriode(30L).tempsMigUltimPeriode(34)
                .peticionsPerEntorn(null).totalError(23L).totalOk(45L).totalTempsMig(1234).build();

        List<IntegracioSalut> integracions = List.of(
                IntegracioSalut.builder().codi("INTEG1").estat(EstatSalutEnum.DOWN).latencia(4).peticions(ip).build());

        List<MissatgeSalut> missatges = List.of(
                MissatgeSalut.builder().data(new Date()).missatge("Hola caracola").nivell(SalutNivell.INFO).build());

        List<SubsistemaSalut> subsistemes = List.of(SubsistemaSalut.builder().codi("SUBS1")
                .estat(EstatSalutEnum.MAINTENANCE).peticionsErrorUltimPeriode(2L).peticionsOkUltimPeriode(30L)
                .tempsMigUltimPeriode(34).totalError(23L).totalOk(45L).totalTempsMig(1234).build());

        builder.altres(altres).bd(estatBBDD).codi("CMD").data(new Date()).estat(estat).integracions(integracions)
                .missatges(missatges).subsistemes(subsistemes);

        

        return builder.build();

    }

}
