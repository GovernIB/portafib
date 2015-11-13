<%@ page contentType="text/html;charset=UTF-8" language="java"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<sec:authorize access="hasRole('ROLE_ADMIN')">
 <c:set var="url" value="${urlActual}" />
 <div>
 <h5>WebDatabase</h5>
 <ul class="tree" style="margin:3px; padding:0px;">
 <%-- ==== GENAPP MARK START --%>


    <%-- AlgorismeDeFirma --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'algorismeDeFirma/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'algorismeDeFirma/')? "font-weight: bold;" : ""}"><fmt:message key="algorismeDeFirma.algorismeDeFirma"/></span></a>
      <ul class="${fn:contains(url, 'algorismeDeFirma/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/algorismeDeFirma/new"/>" ><span style="${(fn:contains(url, 'algorismeDeFirma/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="algorismeDeFirma.algorismeDeFirma"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/algorismeDeFirma/list/1"/>" ><span style="${(fn:contains(url, 'algorismeDeFirma/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

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

    <%-- ModulDeFirma --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'modulDeFirma/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'modulDeFirma/')? "font-weight: bold;" : ""}"><fmt:message key="modulDeFirma.modulDeFirma"/></span></a>
      <ul class="${fn:contains(url, 'modulDeFirma/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/modulDeFirma/new"/>" ><span style="${(fn:contains(url, 'modulDeFirma/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="modulDeFirma.modulDeFirma"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/modulDeFirma/list/1"/>" ><span style="${(fn:contains(url, 'modulDeFirma/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
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

    <%-- PosicioPagina --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'posicioPagina/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'posicioPagina/')? "font-weight: bold;" : ""}"><fmt:message key="posicioPagina.posicioPagina"/></span></a>
      <ul class="${fn:contains(url, 'posicioPagina/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/posicioPagina/new"/>" ><span style="${(fn:contains(url, 'posicioPagina/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="posicioPagina.posicioPagina"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/posicioPagina/list/1"/>" ><span style="${(fn:contains(url, 'posicioPagina/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- PosicioTaulaFirmes --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'posicioTaulaFirmes/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'posicioTaulaFirmes/')? "font-weight: bold;" : ""}"><fmt:message key="posicioTaulaFirmes.posicioTaulaFirmes"/></span></a>
      <ul class="${fn:contains(url, 'posicioTaulaFirmes/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/posicioTaulaFirmes/new"/>" ><span style="${(fn:contains(url, 'posicioTaulaFirmes/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="posicioTaulaFirmes.posicioTaulaFirmes"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/posicioTaulaFirmes/list/1"/>" ><span style="${(fn:contains(url, 'posicioTaulaFirmes/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- Prioritat --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'prioritat/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'prioritat/')? "font-weight: bold;" : ""}"><fmt:message key="prioritat.prioritat"/></span></a>
      <ul class="${fn:contains(url, 'prioritat/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/prioritat/new"/>" ><span style="${(fn:contains(url, 'prioritat/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="prioritat.prioritat"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/prioritat/list/1"/>" ><span style="${(fn:contains(url, 'prioritat/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
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

    <%-- TipusEstatDeFirmaFinal --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'tipusEstatDeFirmaFinal/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'tipusEstatDeFirmaFinal/')? "font-weight: bold;" : ""}"><fmt:message key="tipusEstatDeFirmaFinal.tipusEstatDeFirmaFinal"/></span></a>
      <ul class="${fn:contains(url, 'tipusEstatDeFirmaFinal/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/tipusEstatDeFirmaFinal/new"/>" ><span style="${(fn:contains(url, 'tipusEstatDeFirmaFinal/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="tipusEstatDeFirmaFinal.tipusEstatDeFirmaFinal"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/tipusEstatDeFirmaFinal/list/1"/>" ><span style="${(fn:contains(url, 'tipusEstatDeFirmaFinal/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- TipusEstatDeFirmaInicial --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'tipusEstatDeFirmaInicial/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'tipusEstatDeFirmaInicial/')? "font-weight: bold;" : ""}"><fmt:message key="tipusEstatDeFirmaInicial.tipusEstatDeFirmaInicial"/></span></a>
      <ul class="${fn:contains(url, 'tipusEstatDeFirmaInicial/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/tipusEstatDeFirmaInicial/new"/>" ><span style="${(fn:contains(url, 'tipusEstatDeFirmaInicial/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="tipusEstatDeFirmaInicial.tipusEstatDeFirmaInicial"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/tipusEstatDeFirmaInicial/list/1"/>" ><span style="${(fn:contains(url, 'tipusEstatDeFirmaInicial/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- TipusEstatPeticioDeFirma --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'tipusEstatPeticioDeFirma/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'tipusEstatPeticioDeFirma/')? "font-weight: bold;" : ""}"><fmt:message key="tipusEstatPeticioDeFirma.tipusEstatPeticioDeFirma"/></span></a>
      <ul class="${fn:contains(url, 'tipusEstatPeticioDeFirma/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/tipusEstatPeticioDeFirma/new"/>" ><span style="${(fn:contains(url, 'tipusEstatPeticioDeFirma/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="tipusEstatPeticioDeFirma.tipusEstatPeticioDeFirma"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/tipusEstatPeticioDeFirma/list/1"/>" ><span style="${(fn:contains(url, 'tipusEstatPeticioDeFirma/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- TipusFirma --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'tipusFirma/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'tipusFirma/')? "font-weight: bold;" : ""}"><fmt:message key="tipusFirma.tipusFirma"/></span></a>
      <ul class="${fn:contains(url, 'tipusFirma/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/tipusFirma/new"/>" ><span style="${(fn:contains(url, 'tipusFirma/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="tipusFirma.tipusFirma"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/tipusFirma/list/1"/>" ><span style="${(fn:contains(url, 'tipusFirma/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
        <fmt:message key="genapp.listtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
        </span></a>
        </li>
      </ul>
    </li>

    <%-- TipusMetadada --%>
    <li>
      <a href="#" role="branch" class="tree-toggle ${fn:contains(url, 'tipusMetadada/')? "" : "closed"}" data-toggle="branch" data-value="suportada"><span style="${fn:contains(url, 'tipusMetadada/')? "font-weight: bold;" : ""}"><fmt:message key="tipusMetadada.tipusMetadada"/></span></a>
      <ul class="${fn:contains(url, 'tipusMetadada/')? "branch in" : "branch"}">
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/webdb/tipusMetadada/new"/>" ><span style="${(fn:contains(url, 'tipusMetadada/') && fn:contains(url, '/new'))? "font-weight: bold;" : ""}" >
       <fmt:message var="entityname" key="tipusMetadada.tipusMetadada"/>
       <fmt:message key="genapp.createtitle" >
         <fmt:param value="${entityname}"/>
       </fmt:message>
       </span></a></li>
        <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/webdb/tipusMetadada/list/1"/>" ><span style="${(fn:contains(url, 'tipusMetadada/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
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
