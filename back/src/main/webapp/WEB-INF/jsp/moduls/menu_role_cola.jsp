<%@page import="es.caib.portafib.utils.Configuracio"%>
<%@page import="es.caib.portafib.utils.Constants"
%><%@page import="java.util.HashMap"
%><%@page import="java.util.Map"
%><%@ page contentType="text/html;charset=UTF-8" language="java"
%><%@ include  file="/WEB-INF/jsp/moduls/includes.jsp"
%>
<sec:authorize access="hasRole('ROLE_COLA')">
<div>
  <h5><fmt:message key="ROLE_COLA.menu" /></h5>
<%!

static final String[] menu = {
    Configuracio.isDesenvolupament()?"colaboracio.totes.plural": "    ",
    /*
    "validarinvalidardocuments", //Validar o invalidar documents de firma   
    "rebutjarpeticiofirma", //Rebutjar petició de firma.
    "llistarvalidatsinvalidatspendentsrebutjats" //Llistar documents validats, invalidats, pendents de validació i rebutjats
    */
    //"",
    "colaboracio.pendent.plural",
    "colaboracio.acceptada.plural",
    "colaboracio.noacceptada.plural",
    "colaboracio.ignorada.plural"
};

public static final Map<String, String> mapping;

static {
  //Mapping to existent path
  mapping = new HashMap<String, String>();

  mapping.put("colaboracio.totes.plural", Constants.CONTEXT_COLA_ESTATFIRMA  + "/list");
  
  mapping.put("colaboracio.pendent.plural", Constants.CONTEXT_COLA_ESTATFIRMA_PENDENT + "/list");
  mapping.put("colaboracio.acceptada.plural", Constants.CONTEXT_COLA_ESTATFIRMA_VALIDAT + "/list");
  mapping.put("colaboracio.noacceptada.plural", Constants.CONTEXT_COLA_ESTATFIRMA_INVALIDAT + "/list");
  mapping.put("colaboracio.ignorada.plural", Constants.CONTEXT_COLA_ESTATFIRMA_DESCARTAT + "/list");
  
  /*
  mapping.put("llistarvalidatsinvalidatspendentsrebutjats", list);
  mapping.put("validarinvalidardocuments", list);
  mapping.put("rebutjarpeticiofirma", list);
  */
 
}
%><%

session.setAttribute("menu", menu);

session.setAttribute("mapping", mapping);

%>
 
  <ul class="tree" style="margin: 3px; padding: 0px;">
    <c:forEach var="item" items="${menu}" >
    <c:if test="${item != '    '}">
        <c:if test="${empty item }">
        <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
        </c:if>
        <c:if test="${not empty item }">
          <fmt:message var="traduccio" key="${item}" />
          
          <c:if test="${empty mapping[item]}">
            <c:set var="theurl" value="/cola/${item}"/>
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
      </c:if>
    </c:forEach>

  </ul>
</div>
</sec:authorize>
