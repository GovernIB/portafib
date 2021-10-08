<%@page import="es.caib.portafib.back.utils.MenuItem"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="es.caib.portafib.utils.Configuracio"%>
<%@page import="es.caib.portafib.utils.Constants"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
%><%@ include  file="/WEB-INF/jsp/moduls/includes.jsp"%>
<sec:authorize access="hasRole('ROLE_ADMIN')">
<c:set var="url" value="${urlActual}" />


<div>
  <h5><fmt:message key="ROLE_ADMIN.menu" /></h5>
<%!

private static final List<List<MenuItem>> menus  = new ArrayList<List<MenuItem>>();
    
static {
  
  List<MenuItem> menu1;
  
  boolean compactar= false;
  
  menu1 = new ArrayList<MenuItem>();

  menu1.add(MenuItem.retallaDarrerPath("entitat.gestio", "/admin/entitat/list"));

  menu1.add(null);

  menu1.add(MenuItem.retallaDarrerPath("administradorentitat.gestio", "/admin/adminentitat/list"));

  menu1.add(null);

  menu1.add(MenuItem.retallaDarrerPath("usuaripersona.alta", "/admin/usuariPersona/alta"));
  menu1.add(MenuItem.retallaDarrerPath("usuaripersona.modificar", "/admin/usuariPersona/modificar"));
  
  menu1.add(null);
  
  menu1.add(MenuItem.retallaDarrerPath("tipusdocument.gestio", "/admin/gestiotipusdoc/list"));
  
  menu1.add(null);
  
  menu1.add(MenuItem.retallaDarrerPath("moduldefirma.plantilla.plural", "/admin/modulDeFirma/list"));
  menu1.add(MenuItem.retallaDarrerPath("moduldefirmaenservidor.plantilla.plural", "/admin/moduldefirmaenservidor/list"));
  menu1.add(MenuItem.retallaDarrerPath("segelldetemps.plantilla.plural", "/admin/segelldetemps/list"));
  menu1.add(MenuItem.retallaDarrerPath("plugincustodia.gestio", "/admin/plugincustodia/list"));
  menu1.add(MenuItem.retallaDarrerPath("validaciodefirmes.gestio", "/admin/validaciofirmes/list"));
  
  menu1.add(null);

  if (!Configuracio.isCAIB()) {
    menu1.add(MenuItem.retallaDarrerPath("usuariaplicacio.gestio", "/admin/usuariAplicacio/list"));
    menu1.add(null);
  }
  
  menu1.add(MenuItem.retallaDarrerPath("propietatglobal.gestio", "/admin/propietatglobal/list"));
  menu1.add(MenuItem.retallaDarrerPath("propietatSistema.menu", "/admin/propietatsistema/list"));
  
  menu1.add(null);
  
  menu1.add(MenuItem.retallaDarrerPath("fitxers.orfes", "/admin/fitxersorfes/list"));

  menus.add(menu1);

}


%><%


int count = 0;

for(List<MenuItem> menu : menus) {
  pageContext.setAttribute("menu", menu);
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
      <c:set var="match" value="${(fn:contains(urlActual, theurl))}"/>
      <li style="list-style-type: disc; list-style-position: inside;">
        <a href="<c:url value="${theurl}"/>">
          <span style="${match?"font-weight: bold;":""} ${(fn:endsWith(traduccio, '(*)'))? "color: red;" : ""}">${traduccio}</span>
        </a>
      </li>
    </c:if>
    </c:forEach>

  </ul>
  
  <%  
 
  count++;
  
}  // final FOR 
  %>


</div>
</sec:authorize>
