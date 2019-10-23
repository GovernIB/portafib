<%@page import="es.caib.portafib.logic.utils.PropietatGlobalUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="es.caib.portafib.back.utils.MenuItem"%>
<%@page import="es.caib.portafib.utils.ConstantsV2"%>
<%@page import="es.caib.portafib.back.controller.aden.PerfilDeFirmaAdenController"%>
<%@page import="es.caib.portafib.model.fields.PerfilDeFirmaFields"
%><%@page import="es.caib.portafib.back.controller.aden.ConfiguracioDeFirmaAdenController"
%><%@page import="es.caib.portafib.model.fields.UsuariAplicacioConfiguracioFields"
%><%@page import="es.caib.portafib.utils.Configuracio"
%><%@page import="es.caib.portafib.back.security.LoginInfo"
%><%@page import="java.util.HashMap"
%><%@page import="java.util.Map"
%><%@ page contentType="text/html;charset=UTF-8" language="java"
%><%@ include  file="/WEB-INF/jsp/moduls/includes.jsp"
%><sec:authorize access="hasRole('ROLE_ADEN')">
<div>
  <h5><fmt:message key="ROLE_ADEN.menu" /></h5>
<%!

private static final List<List<MenuItem>> menus  = new ArrayList<List<MenuItem>>();


static {
  
  List<MenuItem> menu1;
  List<MenuItem> menu2;
  
  boolean compactar= PropietatGlobalUtil.compactMenuOptionsOfAdEn();
  
  menu1 = new ArrayList<MenuItem>();
  menu1.add(MenuItem.retallaDarrerPath("entitat.modificar", "/aden/entitat/current"));
  menu1.add(MenuItem.retallaDarrerPath("propietatglobal.entitat.gestio", "/aden/propietatglobal/list"));
  menu1.add(MenuItem.retallaDarrerPath("tipusdocument.gestio", "/aden/gestiotipusdoc/list"));
  menu1.add(null);
  menu1.add(MenuItem.retallaDarrerPath("usuaripersona.alta", "/aden/usuariPersona/alta"));
  menu1.add(MenuItem.retallaDarrerPath("usuaripersona.modificar", "/aden/usuariPersona/modificar"));
  menu1.add(MenuItem.retallaDarrerPath("usuarientitat.gestio", "/aden/usuariEntitat/selecciousuari"));
  menu1.add(null);
  menu1.add(MenuItem.retallaDarrerPath("carrec.gestio", "/aden/carrec/list"));
  menu1.add(MenuItem.retallaDarrerPath("colaboradordecarrec.plural", "/aden/colaboradordecarrec/list"));
  menu1.add(MenuItem.retallaDarrerPath("solicitant.gestio", "/aden/solicitant/selecciousuari"));
  menu1.add(MenuItem.retallaDarrerPath("revisor.gestio", "/aden/revisor/selecciousuari"));
  menu1.add(MenuItem.retallaDarrerPath("grups.gestio", "/aden/grup/list"));
  menu1.add(null);
  menu1.add(MenuItem.retallaDarrerPath("moduldefirmaenservidor.gestio", "/aden/moduldefirmaenservidor/list"));
  menu1.add(null);
  menu1.add(MenuItem.retallaDarrerPath("moduldefirma.gestio", "/aden/modulDeFirma/list"));
  menu1.add(MenuItem.retallaDarrerPath("modulDeFirmaPerTipusDeDocument.short", "/aden/modulfirmatipusdoc/list"));
  menu1.add(null);
  menu1.add(MenuItem.retallaDarrerPath("segelldetemps.gestio", "/aden/segelldetemps/list"));
  menu1.add(MenuItem.retallaDarrerPath("plantillacustodia.gestio", "/aden/plantillacustodia/list"));
  menu1.add(null);
  menu1.add(MenuItem.retallaDarrerPath("validaciodefirmes.gestio", "/aden/validaciofirmes/list"));
  menu1.add(null);
  menu1.add(MenuItem.retallaDarrerPath("peticiodefirma.totes.consultar.llistar", "/aden/peticiofirmatotesconsultar/list"));
  menu1.add(MenuItem.retallaDarrerPath("peticiodefirma.totes.gestionar.llistar", "/aden/peticiofirmatotesgestionar/list"));
  if (compactar) {
    // No mostrar
  } else {
    menu1.add(null);
    menu1.add(MenuItem.retallaDarrerPath("peticionscaducades.llistat", "/aden/peticionscaducades/list"));
    menu1.add(MenuItem.retallaDarrerPath("peticiodefirma.netejaesborrat", "/aden/peticio/netejaesborrat/list"));
  }
  menu1.add(MenuItem.retallaDarrerPath("peticionsdefirma.destinatari", "/aden/peticionsdedestinatari/selecciousuari"));
  menu1.add(null);
  menu1.add(MenuItem.retallaDarrerPath("estadistica.estadistica.plural", "/aden/estadistica/search"));
  menu1.add(MenuItem.retallaDarrerPath("bitacola.menu", "/aden/bitacola/list"));
  menu1.add(MenuItem.retallaDarrerPath("peticiosincrona.menu", "/aden/peticiosincrona/list"));


  final String CONFIGURACIO_DE_FIRMA = UsuariAplicacioConfiguracioFields._TABLE_MODEL + "." + UsuariAplicacioConfiguracioFields._TABLE_MODEL + ".plural";
  final String PERFIL_DE_FIRMA = PerfilDeFirmaFields._TABLE_MODEL + "." + PerfilDeFirmaFields._TABLE_MODEL + ".plural";
  
  menu2 = new ArrayList<MenuItem>();
  menu2.add(MenuItem.retallaDarrerPath("usuariaplicacio.gestio", "/aden/usuariAplicacio/list"));
  menu2.add(MenuItem.retallaDarrerPath(PERFIL_DE_FIRMA, "/aden/perfildefirma/list"));
  menu2.add(MenuItem.retallaDarrerPath( CONFIGURACIO_DE_FIRMA, "/aden/configdefirma/list"));

  menu2.add(null);
  menu2.add(MenuItem.retallaDarrerPath("plantillaFluxDeFirmes.plantillaFluxDeFirmes.plural", "/aden/plantilla/list"));
  if (compactar) {
    // No mostrar 
  } else {
    menu2.add(MenuItem.retallaDarrerPath("peticiodefirma.usrapp.llistar", "/aden/peticiofirmaaplicacio/list"));
  }
  menu2.add(MenuItem.retallaDarrerPath("custodiaInfo.custodiaInfo.plural", "/aden/peticio/custodiainfo/list"));
  menu2.add(MenuItem.retallaDarrerPath("notificaciows.llistat", "/aden/notificaciows/list"));

  menus.add(menu1);
  menus.add(menu2);
}

%><%

int count = 0;

for(List<MenuItem> menu : menus) {
  session.setAttribute("menu", menu);
%>
  <ul class="tree" style="margin: 3px; padding: 0px;">
    <c:forEach var="item" items="${menu}" >

    <c:if test="${empty item }">
    <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
    </c:if>
    <c:if test="${not empty item }">
      <fmt:message var="traduccio" key="${item.label}" />
      <c:set var="theurl" value="${item.url}"/>
      <c:set var="theurlbase" value="${item.urlbase}"/>
      <li style="list-style-type: disc; list-style-position: inside;">
        <a href="<c:url value="${theurl}"/>">
          <span style="${(fn:contains(urlActual, theurlbase))? "font-weight: bold;" : ""} ${(fn:endsWith(traduccio, '(*)'))? "color: red;" : ""}">${traduccio}</span>
        </a>
      </li>
    </c:if>
    </c:forEach>

  </ul>
  
  <%
  if (count == 0) { %>
      </div>
      </div>
      <br/>
      <div class="thumbnail">
      <div>
      <h5><fmt:message key="ROLE_ADEN.menu2" /></h5>
  <%    
  }
  count++;
  
}  // final FOR 
  %>
  
</div>
</sec:authorize>
