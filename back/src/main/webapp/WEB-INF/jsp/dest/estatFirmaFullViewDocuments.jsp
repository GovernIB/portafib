<%@ page contentType="text/html;charset=UTF-8" language="java"%><%@ include
  file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="Constants" className="es.caib.portafib.utils.Constants" />


<!--  INICI MENU -->
<div class="mainMenu span3">
  <div class="thumbnail">

<div>
  <h5><fmt:message key="peticioDeFirma.fitxerAFirmarID" /></h5>
  <ul class="tree" style="margin: 3px; padding: 0px;">

      <li style="list-style-type: disc; list-style-position: inside;">
         <a href="javascript:mostrarDiv(0)"><c:out value="${fitxers[0].key.nom}" /></a>
      </li>
    </li>
  </ul>

    <c:if test="${fn:length(fitxers) gt 1}">

    <br/>
    <h5><fmt:message key="annex.annex.plural" /></h5>
    
      <ul class="tree" style="margin: 3px; padding: 0px;">
    
        <c:forEach var="fitxer" items="${fitxers}" begin="1" varStatus="theCount">
          <li style="list-style-type: disc; list-style-position: inside;">
            <a href="javascript:mostrarDiv(${theCount.index})"><c:out value="${fitxer.key.nom}" /></a>
          </li>
        </c:forEach>  
    
      </ul>
    
    </c:if>


</div>


  </div>
</div>

<!--  CONTINGUT  -->
<div class="span9">

  <!--  Missatges  -->
  <jsp:include page="/WEB-INF/jsp/moduls/missatges.jsp" />
  
  
  <%--  Visor del Document a firmar i dels annexes --%>
  <jsp:include page="/WEB-INF/jsp/dest/estatFirmaDocuments.jsp" />


</div>

<div class="clearfix"></div>