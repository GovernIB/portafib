<%@ page contentType="text/html;charset=UTF-8" language="java"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<sec:authorize access="hasRole('ROLE_ADMIN')">
 <c:set var="url" value="${urlActual}" />
 <div>
 <h5>WebDatabase</h5>
 <ul class="tree" style="margin:3px; padding:0px;">
 <%-- ==== GENAPP MARK START --%>


    <%-- Annex --%>
       <fmt:message var="entityname" key="annex.annex.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/annex/list/1"/>" ><span style="${(fn:contains(url, 'annex/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- AnnexFirmat --%>
       <fmt:message var="entityname" key="annexFirmat.annexFirmat.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/annexFirmat/list/1"/>" ><span style="${(fn:contains(url, 'annexFirmat/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Bitacola --%>
       <fmt:message var="entityname" key="bitacola.bitacola.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/bitacola/list/1"/>" ><span style="${(fn:contains(url, 'bitacola/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- BlocDeFirmes --%>
       <fmt:message var="entityname" key="blocDeFirmes.blocDeFirmes.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/blocDeFirmes/list/1"/>" ><span style="${(fn:contains(url, 'blocDeFirmes/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- CodiBarres --%>
       <fmt:message var="entityname" key="codiBarres.codiBarres.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/codiBarres/list/1"/>" ><span style="${(fn:contains(url, 'codiBarres/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- ColaboracioDelegacio --%>
       <fmt:message var="entityname" key="colaboracioDelegacio.colaboracioDelegacio.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/colaboracioDelegacio/list/1"/>" ><span style="${(fn:contains(url, 'colaboracioDelegacio/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- CustodiaInfo --%>
       <fmt:message var="entityname" key="custodiaInfo.custodiaInfo.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/custodiaInfo/list/1"/>" ><span style="${(fn:contains(url, 'custodiaInfo/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Entitat --%>
       <fmt:message var="entityname" key="entitat.entitat.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/entitat/list/1"/>" ><span style="${(fn:contains(url, 'entitat/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Estadistica --%>
       <fmt:message var="entityname" key="estadistica.estadistica.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/estadistica/list/1"/>" ><span style="${(fn:contains(url, 'estadistica/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- EstatDeFirma --%>
       <fmt:message var="entityname" key="estatDeFirma.estatDeFirma.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/estatDeFirma/list/1"/>" ><span style="${(fn:contains(url, 'estatDeFirma/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Firma --%>
       <fmt:message var="entityname" key="firma.firma.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/firma/list/1"/>" ><span style="${(fn:contains(url, 'firma/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Fitxer --%>
       <fmt:message var="entityname" key="fitxer.fitxer.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/fitxer/list/1"/>" ><span style="${(fn:contains(url, 'fitxer/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- FluxDeFirmes --%>
       <fmt:message var="entityname" key="fluxDeFirmes.fluxDeFirmes.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/fluxDeFirmes/list/1"/>" ><span style="${(fn:contains(url, 'fluxDeFirmes/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- GrupEntitat --%>
       <fmt:message var="entityname" key="grupEntitat.grupEntitat.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/grupEntitat/list/1"/>" ><span style="${(fn:contains(url, 'grupEntitat/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- GrupEntitatUsuariEntitat --%>
       <fmt:message var="entityname" key="grupEntitatUsuariEntitat.grupEntitatUsuariEntitat.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/grupEntitatUsuariEntitat/list/1"/>" ><span style="${(fn:contains(url, 'grupEntitatUsuariEntitat/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Idioma --%>
       <fmt:message var="entityname" key="idioma.idioma.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/idioma/list/1"/>" ><span style="${(fn:contains(url, 'idioma/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Metadada --%>
       <fmt:message var="entityname" key="metadada.metadada.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/metadada/list/1"/>" ><span style="${(fn:contains(url, 'metadada/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- ModulDeFirmaPerTipusDeDocument --%>
       <fmt:message var="entityname" key="modulDeFirmaPerTipusDeDocument.modulDeFirmaPerTipusDeDocument.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/modulDeFirmaPerTipusDeDocument/list/1"/>" ><span style="${(fn:contains(url, 'modulDeFirmaPerTipusDeDocument/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- NotificacioWS --%>
       <fmt:message var="entityname" key="notificacioWS.notificacioWS.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/notificacioWS/list/1"/>" ><span style="${(fn:contains(url, 'notificacioWS/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- PerfilsPerUsuariAplicacio --%>
       <fmt:message var="entityname" key="perfilsPerUsuariAplicacio.perfilsPerUsuariAplicacio.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/perfilsPerUsuariAplicacio/list/1"/>" ><span style="${(fn:contains(url, 'perfilsPerUsuariAplicacio/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- PermisGrupPlantilla --%>
       <fmt:message var="entityname" key="permisGrupPlantilla.permisGrupPlantilla.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/permisGrupPlantilla/list/1"/>" ><span style="${(fn:contains(url, 'permisGrupPlantilla/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- PermisUsuariPlantilla --%>
       <fmt:message var="entityname" key="permisUsuariPlantilla.permisUsuariPlantilla.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/permisUsuariPlantilla/list/1"/>" ><span style="${(fn:contains(url, 'permisUsuariPlantilla/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- PeticioDeFirma --%>
       <fmt:message var="entityname" key="peticioDeFirma.peticioDeFirma.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/peticioDeFirma/list/1"/>" ><span style="${(fn:contains(url, 'peticioDeFirma/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- PlantillaFluxDeFirmes --%>
       <fmt:message var="entityname" key="plantillaFluxDeFirmes.plantillaFluxDeFirmes.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/plantillaFluxDeFirmes/list/1"/>" ><span style="${(fn:contains(url, 'plantillaFluxDeFirmes/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Plugin --%>
       <fmt:message var="entityname" key="plugin.plugin.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/plugin/list/1"/>" ><span style="${(fn:contains(url, 'plugin/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- PluginCridada --%>
       <fmt:message var="entityname" key="pluginCridada.pluginCridada.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/pluginCridada/list/1"/>" ><span style="${(fn:contains(url, 'pluginCridada/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- PluginFirmaWebPerUsuariAplicacio --%>
       <fmt:message var="entityname" key="pluginFirmaWebPerUsuariAplicacio.pluginFirmaWebPerUsuariAplicacio.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/pluginFirmaWebPerUsuariAplicacio/list/1"/>" ><span style="${(fn:contains(url, 'pluginFirmaWebPerUsuariAplicacio/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- PluginFirmaWebPerUsuariEntitat --%>
       <fmt:message var="entityname" key="pluginFirmaWebPerUsuariEntitat.pluginFirmaWebPerUsuariEntitat.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/pluginFirmaWebPerUsuariEntitat/list/1"/>" ><span style="${(fn:contains(url, 'pluginFirmaWebPerUsuariEntitat/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- PropietatGlobal --%>
       <fmt:message var="entityname" key="propietatGlobal.propietatGlobal.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/propietatGlobal/list/1"/>" ><span style="${(fn:contains(url, 'propietatGlobal/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- RebreAvis --%>
       <fmt:message var="entityname" key="rebreAvis.rebreAvis.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/rebreAvis/list/1"/>" ><span style="${(fn:contains(url, 'rebreAvis/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- RevisorDeFirma --%>
       <fmt:message var="entityname" key="revisorDeFirma.revisorDeFirma.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/revisorDeFirma/list/1"/>" ><span style="${(fn:contains(url, 'revisorDeFirma/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Role --%>
       <fmt:message var="entityname" key="role.role.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/role/list/1"/>" ><span style="${(fn:contains(url, 'role/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- RoleUsuariEntitat --%>
       <fmt:message var="entityname" key="roleUsuariEntitat.roleUsuariEntitat.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/roleUsuariEntitat/list/1"/>" ><span style="${(fn:contains(url, 'roleUsuariEntitat/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- TipusDocument --%>
       <fmt:message var="entityname" key="tipusDocument.tipusDocument.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/tipusDocument/list/1"/>" ><span style="${(fn:contains(url, 'tipusDocument/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- TipusDocumentColaboracioDelegacio --%>
       <fmt:message var="entityname" key="tipusDocumentColaboracioDelegacio.tipusDocumentColaboracioDelegacio.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/tipusDocumentColaboracioDelegacio/list/1"/>" ><span style="${(fn:contains(url, 'tipusDocumentColaboracioDelegacio/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- TipusNotificacio --%>
       <fmt:message var="entityname" key="tipusNotificacio.tipusNotificacio.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/tipusNotificacio/list/1"/>" ><span style="${(fn:contains(url, 'tipusNotificacio/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- Traduccio --%>
       <fmt:message var="entityname" key="traduccio.traduccio.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/traduccio/list/1"/>" ><span style="${(fn:contains(url, 'traduccio/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- UsuariAplicacio --%>
       <fmt:message var="entityname" key="usuariAplicacio.usuariAplicacio.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/usuariAplicacio/list/1"/>" ><span style="${(fn:contains(url, 'usuariAplicacio/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- UsuariAplicacioConfiguracio --%>
       <fmt:message var="entityname" key="usuariAplicacioConfiguracio.usuariAplicacioConfiguracio.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/usuariAplicacioConfiguracio/list/1"/>" ><span style="${(fn:contains(url, 'usuariAplicacioConfiguracio/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- PerfilDeFirma --%>
       <fmt:message var="entityname" key="perfilDeFirma.perfilDeFirma.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/perfilDeFirma/list/1"/>" ><span style="${(fn:contains(url, 'perfilDeFirma/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- UsuariEntitat --%>
       <fmt:message var="entityname" key="usuariEntitat.usuariEntitat.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/usuariEntitat/list/1"/>" ><span style="${(fn:contains(url, 'usuariEntitat/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- UsuariEntitatFavorit --%>
       <fmt:message var="entityname" key="usuariEntitatFavorit.usuariEntitatFavorit.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/usuariEntitatFavorit/list/1"/>" ><span style="${(fn:contains(url, 'usuariEntitatFavorit/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>

    <%-- UsuariPersona --%>
       <fmt:message var="entityname" key="usuariPersona.usuariPersona.plural"/>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/usuariPersona/list/1"/>" ><span style="${(fn:contains(url, 'usuariPersona/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
${entityname}
        </span></a>
        </li>
<%-- ==== GENAPP MARK END --%>
 </ul>
 </div>
 
</sec:authorize>
