<%@page import="es.caib.portafib.utils.Constants"
%><%@page import="java.util.HashMap"
%><%@page import="java.util.Map"
%><%@ page contentType="text/html;charset=UTF-8" language="java"%><%@ include
  file="/WEB-INF/jsp/moduls/includes.jsp"%>
<sec:authorize access="hasRole('ROLE_DELE')">
<%!

final String[] menu = {
    //"solicituddefirma.llistat",
    /*
    "llistarsolicitudspendentsacceptadesrebutjades", //Llistar sol·licituds pendents, acceptades i rebutjades.  
    "signaturadocuments", //Signatura de documents. 
    "validarinvalidardocuments", //Validar o invalidar documents de firma   
    "rebutjarpeticiofirma", //Rebutjar petició de firma.
    */
    // "",
    "solicituddefirma.llistat.pendent.plural",
    "solicituddefirma.llistat.firmat.plural",
    "solicituddefirma.llistat.rebutjat.plural",
    "solicituddefirma.llistat.descartat.plural"
    
};

public static final Map<String, String> mapping;


static {
  //Mapping to existent path
  mapping = new HashMap<String, String>();

  
  //mapping.put("solicituddefirma.llistat", Constants.CONTEXT_DELE_ESTATFIRMA + "/list");
  
  mapping.put("solicituddefirma.llistat.pendent.plural", Constants.CONTEXT_DELE_ESTATFIRMA_PENDENT + "/list");
  mapping.put("solicituddefirma.llistat.firmat.plural", Constants.CONTEXT_DELE_ESTATFIRMA_FIRMAT + "/list");
  mapping.put("solicituddefirma.llistat.rebutjat.plural", Constants.CONTEXT_DELE_ESTATFIRMA_REBUTJAT + "/list");
  mapping.put("solicituddefirma.llistat.descartat.plural", Constants.CONTEXT_DELE_ESTATFIRMA_DESCARTAT + "/list");
  
  
  
  /*
  mapping.put("llistarsolicitudspendentsacceptadesrebutjades", list);
  mapping.put("signaturadocuments", list);
  mapping.put("validarinvalidardocuments", list);
  mapping.put("rebutjarpeticiofirma", list);
  */
}

%><%

session.setAttribute("menu", menu);

session.setAttribute("mapping", mapping);

%>

<div>
  <h5><fmt:message key="ROLE_DELE.menu" /></h5>

  
  <ul class="tree" style="margin: 3px; padding: 0px;">
    <c:forEach var="item" items="${menu}" >
    <c:if test="${empty item }">
    <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
    </c:if>
    <c:if test="${not empty item }">
      <fmt:message var="traduccio" key="${item}" />
      
      <c:if test="${empty mapping[item]}">
        <c:set var="theurl" value="/dele/${item}"/>
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
