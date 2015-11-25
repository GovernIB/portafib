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

final String[] menu = {

  "entitat.gestio",
  /*
  "altaentitat", // Alta d'Entitat"}
  "deshabilitarentitat", // Deshabilitar Entitat"}
  "modificaciodadesentitat", // Modificació de les dades d'una Entitat"}
  */
  "",
  "administradorentitat.gestio",
  /*
  "altaadministradorentitat", // Alta d'un Administrador d'Entitat"}
  "baixaadministradorentitat", // Baixa d'un Administrador d'Entitat"}
  */
  "",
  "usuaripersona.alta",    
  "usuaripersona.modificar",
  //"altausuariaplicacio", // Alta d'Usuari-Aplicació"}
  //"modificaciodadesusuariaplicacio", // Modificació de les dades d'un usuari-aplicació"}
  //"gestiogeneralconfiguracio", // Gestió general de configuració"}
  "",
  "tipusdocument.gestio", // Gestió Tipus de Documents"}
  "",
  "moduldefirma.plantilla.plural", // /admin/modulDeFirma
  "segelldetemps.plantilla.plural", // /admin/segelldetemps
  "",
  Configuracio.isCAIB()? "" : "usuariaplicacio.gestio",
  //"",
  //"fluxos.orfes",
  "",
  "fitxers.orfes",

};

public static final Map<String, String> mapping;


static {
  //Mapping to existent path
  mapping = new HashMap<String, String>();
  
  mapping.put("usuaripersona.alta", "/admin/usuariPersona/alta");
  mapping.put("usuaripersona.modificar", "/admin/usuariPersona/modificar");
  
  mapping.put("entitat.gestio", "/admin/entitat/list");
  
  mapping.put("administradorentitat.gestio", "/admin/adminentitat/list");

  mapping.put("usuariaplicacio.gestio", "/admin/usuariAplicacio/list");

  mapping.put("tipusdocument.gestio", "/admin/gestiotipusdoc/list");
  
  mapping.put("moduldefirma.plantilla.plural", "/admin/modulDeFirma/list");
  
  mapping.put("segelldetemps.plantilla.plural", "/admin/segelldetemps/list");
  
  //mapping.put("fluxos.orfes", "/admin/fluxosorfes/list");
  
  mapping.put("fitxers.orfes", "/admin/fitxersorfes/list");
  
  
}

%><%

session.setAttribute("menu", menu);

session.setAttribute("mapping", mapping);

%>  
  <ul class="tree" style="margin: 3px; padding: 0px;">

    <c:set var="lastItemEmpty" value="${false}"/>
    
    <c:forEach var="item" items="${menu}" >
    
    <c:if test="${empty item }">
      <c:if test="${lastItemEmpty eq false }">  
        <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
      </c:if>
      <c:set var="lastItemEmpty" value="${true}"/>
    </c:if>
    <c:if test="${not empty item }">
      <c:set var="lastItemEmpty" value="${false}"/>
      <fmt:message var="traduccio" key="${item}" />
      
      <c:if test="${empty mapping[item]}">
        <c:set var="theurl" value="/admin/${item}"/>
      </c:if>
      <c:if test="${not (empty mapping[item])}">
        <c:set var="theurl" value="${mapping[item]}"/>
      </c:if>
      
      <li style="list-style-type: disc; list-style-position: inside;">
        <a href="<c:url value="${theurl}"/>">
          <span style="${(fn:contains(urlActual, theurl))? "font-weight: bold;" : ""} ${(fn:endsWith(traduccio, '(*)'))? "color: red;" : ""}">${traduccio}</span>
        </a>
      </li>
    </c:if>
    
    </c:forEach>

  </ul>
</div>
</sec:authorize>
