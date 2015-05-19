<%@page import="es.caib.portafib.utils.Configuracio"%>
<%@page import="es.caib.portafib.utils.Constants"
%><%@page import="java.util.HashMap"
%><%@page import="java.util.Map"
%><%@ page contentType="text/html;charset=UTF-8" language="java"
%><%@ include  file="/WEB-INF/jsp/moduls/includes.jsp"%>
<sec:authorize access="hasRole('ROLE_DEST')">

<div>
  <h5><fmt:message key="ROLE_DEST.menu" /></h5>
<%!

final String[] menu = {
    //"solicituddefirma.llistat",
    //"",
    "solicituddefirma.llistat.pendent.plural",
    "solicituddefirma.llistat.firmat.plural",
    "solicituddefirma.llistat.rebutjat.plural",
    "solicituddefirma.llistat.descartat.plural",
    
    /*
    "llistarsolicitudspendentsacceptadesrebutjades", //Llistar sol·licituds pendents, acceptades i rebutjades.  
    "signaturadocuments", //Signatura de documents. 
    "validarinvalidardocuments", //Validar o invalidar documents de firma   
    "rebutjarpeticiofirma", //Rebutjar petició de firma.
    */
    "",
    "delegacio.gestio",
    /*
    "delegacio.llistat", //Llistat de Delegats i Delegacions   
    "delegacio.crear", //Associar delegat   
    "deshabilitardelegacio", //Deshabilitar delegació
    */
    
    //"deshabilitardelegat", //Deshabilitar delegat   
    //"associardelegacio", //Associar delegació   
    "",
    "colaboracio.gestio"
    /*
    "colaboracio.llistat", //Llistat de Col·laboradors  
    "colaboracio.crear", //Associar col·laboració 
    "deshabilitarcolaboracio", //Deshabilitar col·laboració
    */
};


public static final Map<String, String> mapping;


static {
  //Mapping to existent path
  mapping = new HashMap<String, String>();
  

  mapping.put("solicituddefirma.llistat", Constants.CONTEXT_DEST_ESTATFIRMA + "/list");
  
  mapping.put("solicituddefirma.llistat.pendent.plural", Constants.CONTEXT_DEST_ESTATFIRMA_PENDENT + "/list");
  mapping.put("solicituddefirma.llistat.firmat.plural", Constants.CONTEXT_DEST_ESTATFIRMA_FIRMAT + "/list");
  mapping.put("solicituddefirma.llistat.rebutjat.plural", Constants.CONTEXT_DEST_ESTATFIRMA_REBUTJAT + "/list");
  
  
  mapping.put("solicituddefirma.llistat.descartat.plural", Constants.CONTEXT_DEST_ESTATFIRMA_DESCARTAT + "/list");
 
  
  /*
  String list = "/dest/estatDeFirma/list";
  mapping.put("llistarsolicitudspendentsacceptadesrebutjades", list);  
  mapping.put("signaturadocuments", list);
  mapping.put("validarinvalidardocuments", list);
  mapping.put("rebutjarpeticiofirma", list);
  */
 
  mapping.put("colaboracio.gestio", "/dest/colaborador/list"); //Llistat de Col·laboradors
  /*
  mapping.put("colaboracio.llistat", "/dest/colaborador/list"); //Llistat de Col·laboradors  
  mapping.put("colaboracio.crear", "/dest/colaborador/new");  //Associar col·laboració 
  mapping.put("deshabilitarcolaboracio", "/dest/colaborador/list");  //Deshabilitar col·laboració
  */

  mapping.put("delegacio.gestio", "/dest/delegat/list"); //Llista delegació
  /*
  mapping.put("delegacio.llistat", "/dest/delegat/list"); //Llista delegació
  mapping.put("delegacio.crear","/dest/delegat/new"); //Associar delegat
  mapping.put("deshabilitardelegacio", "/dest/delegat/list"); //Deshabilitar delegació
  */

}

%>
<ul class="tree" style="margin: 3px; padding: 0px;">
<%

session.setAttribute("menu", menu);

session.setAttribute("mapping", mapping);

  if (Configuracio.isDesenvolupament()) {  
    session.setAttribute("theurl", Constants.CONTEXT_DEST_ESTATFIRMA + "/list");
   %>
      <li style="list-style-type: disc; list-style-position: inside;">
        <a href="<c:url value="${theurl}"/>">
          <span style="${(fn:contains(urlActual, theurl))? "font-weight: bold;" : ""}"><fmt:message key="solicituddefirma.llistat" /></span>
        </a>
      </li>
      <li>
      <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
      </li>
  <% }
%>


    <c:forEach var="item" items="${menu}" >
    <c:if test="${empty item }">
      <li><hr  style="margin-top: 6px;  margin-bottom: 6px;" /></li>
    </c:if>
    <c:if test="${not empty item }">
      <fmt:message var="traduccio" key="${item}" />
      
      <c:if test="${empty mapping[item]}">
        <c:set var="theurl" value="/dest/${item}"/>
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
