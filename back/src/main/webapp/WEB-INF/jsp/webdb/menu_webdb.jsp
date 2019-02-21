<%@ page contentType="text/html;charset=UTF-8" language="java"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<sec:authorize access="hasRole('ROLE_ADMIN')">
 <c:set var="url" value="${urlActual}" />
 <div>
 <h5>WebDatabase</h5>
 <ul class="tree" style="margin:3px; padding:0px;">
 <%-- ==== GENAPP MARK START --%>


    <%-- Annex --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'annex/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'annex/')? "font-weight: bold;" : ""}"><fmt:message key="annex.annex"/></span></a>
      <ul class="${fn:contains(url, 'annex/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/annex/new"/>" ><span style="${(fn:contains(url, 'annex/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="annex.annex"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/annex/list/1"/>" ><span style="${(fn:contains(url, 'annex/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- AnnexFirmat --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'annexFirmat/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'annexFirmat/')? "font-weight: bold;" : ""}"><fmt:message key="annexFirmat.annexFirmat"/></span></a>
      <ul class="${fn:contains(url, 'annexFirmat/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/annexFirmat/new"/>" ><span style="${(fn:contains(url, 'annexFirmat/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="annexFirmat.annexFirmat"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/annexFirmat/list/1"/>" ><span style="${(fn:contains(url, 'annexFirmat/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- Bitacola --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'bitacola/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'bitacola/')? "font-weight: bold;" : ""}"><fmt:message key="bitacola.bitacola"/></span></a>
      <ul class="${fn:contains(url, 'bitacola/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/bitacola/new"/>" ><span style="${(fn:contains(url, 'bitacola/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="bitacola.bitacola"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/bitacola/list/1"/>" ><span style="${(fn:contains(url, 'bitacola/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- BlocDeFirmes --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'blocDeFirmes/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'blocDeFirmes/')? "font-weight: bold;" : ""}"><fmt:message key="blocDeFirmes.blocDeFirmes"/></span></a>
      <ul class="${fn:contains(url, 'blocDeFirmes/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/blocDeFirmes/new"/>" ><span style="${(fn:contains(url, 'blocDeFirmes/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="blocDeFirmes.blocDeFirmes"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/blocDeFirmes/list/1"/>" ><span style="${(fn:contains(url, 'blocDeFirmes/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- CodiBarres --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'codiBarres/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'codiBarres/')? "font-weight: bold;" : ""}"><fmt:message key="codiBarres.codiBarres"/></span></a>
      <ul class="${fn:contains(url, 'codiBarres/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/codiBarres/new"/>" ><span style="${(fn:contains(url, 'codiBarres/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="codiBarres.codiBarres"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/codiBarres/list/1"/>" ><span style="${(fn:contains(url, 'codiBarres/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- ColaboracioDelegacio --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'colaboracioDelegacio/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'colaboracioDelegacio/')? "font-weight: bold;" : ""}"><fmt:message key="colaboracioDelegacio.colaboracioDelegacio"/></span></a>
      <ul class="${fn:contains(url, 'colaboracioDelegacio/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/colaboracioDelegacio/new"/>" ><span style="${(fn:contains(url, 'colaboracioDelegacio/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="colaboracioDelegacio.colaboracioDelegacio"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/colaboracioDelegacio/list/1"/>" ><span style="${(fn:contains(url, 'colaboracioDelegacio/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- CustodiaInfo --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'custodiaInfo/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'custodiaInfo/')? "font-weight: bold;" : ""}"><fmt:message key="custodiaInfo.custodiaInfo"/></span></a>
      <ul class="${fn:contains(url, 'custodiaInfo/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/custodiaInfo/new"/>" ><span style="${(fn:contains(url, 'custodiaInfo/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="custodiaInfo.custodiaInfo"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/custodiaInfo/list/1"/>" ><span style="${(fn:contains(url, 'custodiaInfo/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- Entitat --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'entitat/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'entitat/')? "font-weight: bold;" : ""}"><fmt:message key="entitat.entitat"/></span></a>
      <ul class="${fn:contains(url, 'entitat/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/entitat/new"/>" ><span style="${(fn:contains(url, 'entitat/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="entitat.entitat"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/entitat/list/1"/>" ><span style="${(fn:contains(url, 'entitat/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- Estadistica --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'estadistica/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'estadistica/')? "font-weight: bold;" : ""}"><fmt:message key="estadistica.estadistica"/></span></a>
      <ul class="${fn:contains(url, 'estadistica/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/estadistica/new"/>" ><span style="${(fn:contains(url, 'estadistica/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="estadistica.estadistica"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/estadistica/list/1"/>" ><span style="${(fn:contains(url, 'estadistica/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- EstatDeFirma --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'estatDeFirma/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'estatDeFirma/')? "font-weight: bold;" : ""}"><fmt:message key="estatDeFirma.estatDeFirma"/></span></a>
      <ul class="${fn:contains(url, 'estatDeFirma/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/estatDeFirma/new"/>" ><span style="${(fn:contains(url, 'estatDeFirma/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="estatDeFirma.estatDeFirma"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/estatDeFirma/list/1"/>" ><span style="${(fn:contains(url, 'estatDeFirma/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- Firma --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'firma/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'firma/')? "font-weight: bold;" : ""}"><fmt:message key="firma.firma"/></span></a>
      <ul class="${fn:contains(url, 'firma/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/firma/new"/>" ><span style="${(fn:contains(url, 'firma/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="firma.firma"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/firma/list/1"/>" ><span style="${(fn:contains(url, 'firma/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- Fitxer --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'fitxer/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'fitxer/')? "font-weight: bold;" : ""}"><fmt:message key="fitxer.fitxer"/></span></a>
      <ul class="${fn:contains(url, 'fitxer/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/fitxer/new"/>" ><span style="${(fn:contains(url, 'fitxer/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="fitxer.fitxer"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/fitxer/list/1"/>" ><span style="${(fn:contains(url, 'fitxer/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- FluxDeFirmes --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'fluxDeFirmes/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'fluxDeFirmes/')? "font-weight: bold;" : ""}"><fmt:message key="fluxDeFirmes.fluxDeFirmes"/></span></a>
      <ul class="${fn:contains(url, 'fluxDeFirmes/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/fluxDeFirmes/new"/>" ><span style="${(fn:contains(url, 'fluxDeFirmes/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="fluxDeFirmes.fluxDeFirmes"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/fluxDeFirmes/list/1"/>" ><span style="${(fn:contains(url, 'fluxDeFirmes/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- GrupEntitat --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'grupEntitat/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'grupEntitat/')? "font-weight: bold;" : ""}"><fmt:message key="grupEntitat.grupEntitat"/></span></a>
      <ul class="${fn:contains(url, 'grupEntitat/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/grupEntitat/new"/>" ><span style="${(fn:contains(url, 'grupEntitat/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="grupEntitat.grupEntitat"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/grupEntitat/list/1"/>" ><span style="${(fn:contains(url, 'grupEntitat/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- GrupEntitatUsuariEntitat --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'grupEntitatUsuariEntitat/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'grupEntitatUsuariEntitat/')? "font-weight: bold;" : ""}"><fmt:message key="grupEntitatUsuariEntitat.grupEntitatUsuariEntitat"/></span></a>
      <ul class="${fn:contains(url, 'grupEntitatUsuariEntitat/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/grupEntitatUsuariEntitat/new"/>" ><span style="${(fn:contains(url, 'grupEntitatUsuariEntitat/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="grupEntitatUsuariEntitat.grupEntitatUsuariEntitat"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/grupEntitatUsuariEntitat/list/1"/>" ><span style="${(fn:contains(url, 'grupEntitatUsuariEntitat/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- Idioma --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'idioma/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'idioma/')? "font-weight: bold;" : ""}"><fmt:message key="idioma.idioma"/></span></a>
      <ul class="${fn:contains(url, 'idioma/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/idioma/new"/>" ><span style="${(fn:contains(url, 'idioma/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="idioma.idioma"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/idioma/list/1"/>" ><span style="${(fn:contains(url, 'idioma/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- Metadada --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'metadada/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'metadada/')? "font-weight: bold;" : ""}"><fmt:message key="metadada.metadada"/></span></a>
      <ul class="${fn:contains(url, 'metadada/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/metadada/new"/>" ><span style="${(fn:contains(url, 'metadada/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="metadada.metadada"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/metadada/list/1"/>" ><span style="${(fn:contains(url, 'metadada/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- ModulDeFirmaPerTipusDeDocument --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'modulDeFirmaPerTipusDeDocument/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'modulDeFirmaPerTipusDeDocument/')? "font-weight: bold;" : ""}"><fmt:message key="modulDeFirmaPerTipusDeDocument.modulDeFirmaPerTipusDeDocument"/></span></a>
      <ul class="${fn:contains(url, 'modulDeFirmaPerTipusDeDocument/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/modulDeFirmaPerTipusDeDocument/new"/>" ><span style="${(fn:contains(url, 'modulDeFirmaPerTipusDeDocument/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="modulDeFirmaPerTipusDeDocument.modulDeFirmaPerTipusDeDocument"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/modulDeFirmaPerTipusDeDocument/list/1"/>" ><span style="${(fn:contains(url, 'modulDeFirmaPerTipusDeDocument/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- NotificacioWS --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'notificacioWS/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'notificacioWS/')? "font-weight: bold;" : ""}"><fmt:message key="notificacioWS.notificacioWS"/></span></a>
      <ul class="${fn:contains(url, 'notificacioWS/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/notificacioWS/new"/>" ><span style="${(fn:contains(url, 'notificacioWS/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="notificacioWS.notificacioWS"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/notificacioWS/list/1"/>" ><span style="${(fn:contains(url, 'notificacioWS/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- PerfilsPerUsuariAplicacio --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'perfilsPerUsuariAplicacio/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'perfilsPerUsuariAplicacio/')? "font-weight: bold;" : ""}"><fmt:message key="perfilsPerUsuariAplicacio.perfilsPerUsuariAplicacio"/></span></a>
      <ul class="${fn:contains(url, 'perfilsPerUsuariAplicacio/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/perfilsPerUsuariAplicacio/new"/>" ><span style="${(fn:contains(url, 'perfilsPerUsuariAplicacio/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="perfilsPerUsuariAplicacio.perfilsPerUsuariAplicacio"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/perfilsPerUsuariAplicacio/list/1"/>" ><span style="${(fn:contains(url, 'perfilsPerUsuariAplicacio/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- PermisGrupPlantilla --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'permisGrupPlantilla/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'permisGrupPlantilla/')? "font-weight: bold;" : ""}"><fmt:message key="permisGrupPlantilla.permisGrupPlantilla"/></span></a>
      <ul class="${fn:contains(url, 'permisGrupPlantilla/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/permisGrupPlantilla/new"/>" ><span style="${(fn:contains(url, 'permisGrupPlantilla/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="permisGrupPlantilla.permisGrupPlantilla"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/permisGrupPlantilla/list/1"/>" ><span style="${(fn:contains(url, 'permisGrupPlantilla/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- PermisUsuariPlantilla --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'permisUsuariPlantilla/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'permisUsuariPlantilla/')? "font-weight: bold;" : ""}"><fmt:message key="permisUsuariPlantilla.permisUsuariPlantilla"/></span></a>
      <ul class="${fn:contains(url, 'permisUsuariPlantilla/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/permisUsuariPlantilla/new"/>" ><span style="${(fn:contains(url, 'permisUsuariPlantilla/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="permisUsuariPlantilla.permisUsuariPlantilla"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/permisUsuariPlantilla/list/1"/>" ><span style="${(fn:contains(url, 'permisUsuariPlantilla/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- PeticioDeFirma --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'peticioDeFirma/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'peticioDeFirma/')? "font-weight: bold;" : ""}"><fmt:message key="peticioDeFirma.peticioDeFirma"/></span></a>
      <ul class="${fn:contains(url, 'peticioDeFirma/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/peticioDeFirma/new"/>" ><span style="${(fn:contains(url, 'peticioDeFirma/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="peticioDeFirma.peticioDeFirma"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/peticioDeFirma/list/1"/>" ><span style="${(fn:contains(url, 'peticioDeFirma/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- PlantillaFluxDeFirmes --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'plantillaFluxDeFirmes/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'plantillaFluxDeFirmes/')? "font-weight: bold;" : ""}"><fmt:message key="plantillaFluxDeFirmes.plantillaFluxDeFirmes"/></span></a>
      <ul class="${fn:contains(url, 'plantillaFluxDeFirmes/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/plantillaFluxDeFirmes/new"/>" ><span style="${(fn:contains(url, 'plantillaFluxDeFirmes/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="plantillaFluxDeFirmes.plantillaFluxDeFirmes"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/plantillaFluxDeFirmes/list/1"/>" ><span style="${(fn:contains(url, 'plantillaFluxDeFirmes/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- Plugin --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'plugin/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'plugin/')? "font-weight: bold;" : ""}"><fmt:message key="plugin.plugin"/></span></a>
      <ul class="${fn:contains(url, 'plugin/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/plugin/new"/>" ><span style="${(fn:contains(url, 'plugin/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="plugin.plugin"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/plugin/list/1"/>" ><span style="${(fn:contains(url, 'plugin/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- PluginCridada --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'pluginCridada/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'pluginCridada/')? "font-weight: bold;" : ""}"><fmt:message key="pluginCridada.pluginCridada"/></span></a>
      <ul class="${fn:contains(url, 'pluginCridada/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/pluginCridada/new"/>" ><span style="${(fn:contains(url, 'pluginCridada/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="pluginCridada.pluginCridada"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/pluginCridada/list/1"/>" ><span style="${(fn:contains(url, 'pluginCridada/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- PluginFirmaWebPerUsuariAplicacio --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'pluginFirmaWebPerUsuariAplicacio/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'pluginFirmaWebPerUsuariAplicacio/')? "font-weight: bold;" : ""}"><fmt:message key="pluginFirmaWebPerUsuariAplicacio.pluginFirmaWebPerUsuariAplicacio"/></span></a>
      <ul class="${fn:contains(url, 'pluginFirmaWebPerUsuariAplicacio/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/pluginFirmaWebPerUsuariAplicacio/new"/>" ><span style="${(fn:contains(url, 'pluginFirmaWebPerUsuariAplicacio/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="pluginFirmaWebPerUsuariAplicacio.pluginFirmaWebPerUsuariAplicacio"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/pluginFirmaWebPerUsuariAplicacio/list/1"/>" ><span style="${(fn:contains(url, 'pluginFirmaWebPerUsuariAplicacio/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- PluginFirmaWebPerUsuariEntitat --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'pluginFirmaWebPerUsuariEntitat/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'pluginFirmaWebPerUsuariEntitat/')? "font-weight: bold;" : ""}"><fmt:message key="pluginFirmaWebPerUsuariEntitat.pluginFirmaWebPerUsuariEntitat"/></span></a>
      <ul class="${fn:contains(url, 'pluginFirmaWebPerUsuariEntitat/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/pluginFirmaWebPerUsuariEntitat/new"/>" ><span style="${(fn:contains(url, 'pluginFirmaWebPerUsuariEntitat/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="pluginFirmaWebPerUsuariEntitat.pluginFirmaWebPerUsuariEntitat"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/pluginFirmaWebPerUsuariEntitat/list/1"/>" ><span style="${(fn:contains(url, 'pluginFirmaWebPerUsuariEntitat/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- PropietatGlobal --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'propietatGlobal/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'propietatGlobal/')? "font-weight: bold;" : ""}"><fmt:message key="propietatGlobal.propietatGlobal"/></span></a>
      <ul class="${fn:contains(url, 'propietatGlobal/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/propietatGlobal/new"/>" ><span style="${(fn:contains(url, 'propietatGlobal/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="propietatGlobal.propietatGlobal"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/propietatGlobal/list/1"/>" ><span style="${(fn:contains(url, 'propietatGlobal/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- RebreAvis --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'rebreAvis/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'rebreAvis/')? "font-weight: bold;" : ""}"><fmt:message key="rebreAvis.rebreAvis"/></span></a>
      <ul class="${fn:contains(url, 'rebreAvis/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/rebreAvis/new"/>" ><span style="${(fn:contains(url, 'rebreAvis/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="rebreAvis.rebreAvis"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/rebreAvis/list/1"/>" ><span style="${(fn:contains(url, 'rebreAvis/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- RevisorDeFirma --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'revisorDeFirma/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'revisorDeFirma/')? "font-weight: bold;" : ""}"><fmt:message key="revisorDeFirma.revisorDeFirma"/></span></a>
      <ul class="${fn:contains(url, 'revisorDeFirma/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/revisorDeFirma/new"/>" ><span style="${(fn:contains(url, 'revisorDeFirma/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="revisorDeFirma.revisorDeFirma"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/revisorDeFirma/list/1"/>" ><span style="${(fn:contains(url, 'revisorDeFirma/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- Role --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'role/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'role/')? "font-weight: bold;" : ""}"><fmt:message key="role.role"/></span></a>
      <ul class="${fn:contains(url, 'role/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/role/new"/>" ><span style="${(fn:contains(url, 'role/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="role.role"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/role/list/1"/>" ><span style="${(fn:contains(url, 'role/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- RoleUsuariAplicacio --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'roleUsuariAplicacio/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'roleUsuariAplicacio/')? "font-weight: bold;" : ""}"><fmt:message key="roleUsuariAplicacio.roleUsuariAplicacio"/></span></a>
      <ul class="${fn:contains(url, 'roleUsuariAplicacio/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/roleUsuariAplicacio/new"/>" ><span style="${(fn:contains(url, 'roleUsuariAplicacio/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="roleUsuariAplicacio.roleUsuariAplicacio"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/roleUsuariAplicacio/list/1"/>" ><span style="${(fn:contains(url, 'roleUsuariAplicacio/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- RoleUsuariEntitat --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'roleUsuariEntitat/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'roleUsuariEntitat/')? "font-weight: bold;" : ""}"><fmt:message key="roleUsuariEntitat.roleUsuariEntitat"/></span></a>
      <ul class="${fn:contains(url, 'roleUsuariEntitat/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/roleUsuariEntitat/new"/>" ><span style="${(fn:contains(url, 'roleUsuariEntitat/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="roleUsuariEntitat.roleUsuariEntitat"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/roleUsuariEntitat/list/1"/>" ><span style="${(fn:contains(url, 'roleUsuariEntitat/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- TipusDocument --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'tipusDocument/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'tipusDocument/')? "font-weight: bold;" : ""}"><fmt:message key="tipusDocument.tipusDocument"/></span></a>
      <ul class="${fn:contains(url, 'tipusDocument/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/tipusDocument/new"/>" ><span style="${(fn:contains(url, 'tipusDocument/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="tipusDocument.tipusDocument"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/tipusDocument/list/1"/>" ><span style="${(fn:contains(url, 'tipusDocument/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- TipusDocumentColaboracioDelegacio --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'tipusDocumentColaboracioDelegacio/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'tipusDocumentColaboracioDelegacio/')? "font-weight: bold;" : ""}"><fmt:message key="tipusDocumentColaboracioDelegacio.tipusDocumentColaboracioDelegacio"/></span></a>
      <ul class="${fn:contains(url, 'tipusDocumentColaboracioDelegacio/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/tipusDocumentColaboracioDelegacio/new"/>" ><span style="${(fn:contains(url, 'tipusDocumentColaboracioDelegacio/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="tipusDocumentColaboracioDelegacio.tipusDocumentColaboracioDelegacio"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/tipusDocumentColaboracioDelegacio/list/1"/>" ><span style="${(fn:contains(url, 'tipusDocumentColaboracioDelegacio/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- TipusNotificacio --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'tipusNotificacio/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'tipusNotificacio/')? "font-weight: bold;" : ""}"><fmt:message key="tipusNotificacio.tipusNotificacio"/></span></a>
      <ul class="${fn:contains(url, 'tipusNotificacio/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/tipusNotificacio/new"/>" ><span style="${(fn:contains(url, 'tipusNotificacio/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="tipusNotificacio.tipusNotificacio"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/tipusNotificacio/list/1"/>" ><span style="${(fn:contains(url, 'tipusNotificacio/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- Traduccio --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'traduccio/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'traduccio/')? "font-weight: bold;" : ""}"><fmt:message key="traduccio.traduccio"/></span></a>
      <ul class="${fn:contains(url, 'traduccio/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/traduccio/new"/>" ><span style="${(fn:contains(url, 'traduccio/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="traduccio.traduccio"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/traduccio/list/1"/>" ><span style="${(fn:contains(url, 'traduccio/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- UsuariAplicacio --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'usuariAplicacio/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'usuariAplicacio/')? "font-weight: bold;" : ""}"><fmt:message key="usuariAplicacio.usuariAplicacio"/></span></a>
      <ul class="${fn:contains(url, 'usuariAplicacio/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/usuariAplicacio/new"/>" ><span style="${(fn:contains(url, 'usuariAplicacio/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="usuariAplicacio.usuariAplicacio"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/usuariAplicacio/list/1"/>" ><span style="${(fn:contains(url, 'usuariAplicacio/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- UsuariAplicacioConfiguracio --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'usuariAplicacioConfiguracio/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'usuariAplicacioConfiguracio/')? "font-weight: bold;" : ""}"><fmt:message key="usuariAplicacioConfiguracio.usuariAplicacioConfiguracio"/></span></a>
      <ul class="${fn:contains(url, 'usuariAplicacioConfiguracio/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/usuariAplicacioConfiguracio/new"/>" ><span style="${(fn:contains(url, 'usuariAplicacioConfiguracio/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="usuariAplicacioConfiguracio.usuariAplicacioConfiguracio"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/usuariAplicacioConfiguracio/list/1"/>" ><span style="${(fn:contains(url, 'usuariAplicacioConfiguracio/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- PerfilDeFirma --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'perfilDeFirma/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'perfilDeFirma/')? "font-weight: bold;" : ""}"><fmt:message key="perfilDeFirma.perfilDeFirma"/></span></a>
      <ul class="${fn:contains(url, 'perfilDeFirma/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/perfilDeFirma/new"/>" ><span style="${(fn:contains(url, 'perfilDeFirma/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="perfilDeFirma.perfilDeFirma"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/perfilDeFirma/list/1"/>" ><span style="${(fn:contains(url, 'perfilDeFirma/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- UsuariEntitat --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'usuariEntitat/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'usuariEntitat/')? "font-weight: bold;" : ""}"><fmt:message key="usuariEntitat.usuariEntitat"/></span></a>
      <ul class="${fn:contains(url, 'usuariEntitat/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/usuariEntitat/new"/>" ><span style="${(fn:contains(url, 'usuariEntitat/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="usuariEntitat.usuariEntitat"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/usuariEntitat/list/1"/>" ><span style="${(fn:contains(url, 'usuariEntitat/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- UsuariEntitatFavorit --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'usuariEntitatFavorit/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'usuariEntitatFavorit/')? "font-weight: bold;" : ""}"><fmt:message key="usuariEntitatFavorit.usuariEntitatFavorit"/></span></a>
      <ul class="${fn:contains(url, 'usuariEntitatFavorit/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/usuariEntitatFavorit/new"/>" ><span style="${(fn:contains(url, 'usuariEntitatFavorit/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="usuariEntitatFavorit.usuariEntitatFavorit"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/usuariEntitatFavorit/list/1"/>" ><span style="${(fn:contains(url, 'usuariEntitatFavorit/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- UsuariPersona --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'usuariPersona/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'usuariPersona/')? "font-weight: bold;" : ""}"><fmt:message key="usuariPersona.usuariPersona"/></span></a>
      <ul class="${fn:contains(url, 'usuariPersona/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/usuariPersona/new"/>" ><span style="${(fn:contains(url, 'usuariPersona/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="usuariPersona.usuariPersona"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/usuariPersona/list/1"/>" ><span style="${(fn:contains(url, 'usuariPersona/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>
<%-- ==== GENAPP MARK END --%>
 </ul>
 </div>
 
</sec:authorize>
