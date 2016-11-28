

<%@page import="org.fundaciobit.plugins.signature.api.FileInfoSignature"%>
<%@ include file="/WEB-INF/views/html_header.jsp"%>

<h3 class="tabs_involved">
  &nbsp;&nbsp;&nbsp;<fmt:message key="plugins.caracteristiques.titol" />
</h3>


<table class="table table-bordered" style="margin-left:15px; margin-right: 15px ;width:auto;">

 <thead>
 <tr>
   <td style='text-align:center;vertical-align:middle'>#</td>
 <c:forEach var="row" items="${matrix[0]}"> 
   <td style='text-align:center;vertical-align:middle'> ${row} <c:if test="${empty row }">&nbsp;</c:if> </td>
  </c:forEach>
 </tr>
 </thead>
 
 <tbody>
   
 <c:forEach var="row" items="${matrix}"  varStatus="loop"> 
    <c:if test="${loop.index > 0}">
    <tr>
    <td> ${loop.index} </td>
    <c:forEach var="item" items="${matrix[loop.index]}"  varStatus="loopitems"> 
    
      <td style='text-align:center;vertical-align:middle'> <c:out value="${item}" escapeXml="false"> </c:out> </td>
    
    </c:forEach>
    </tr>
    </c:if>
   
  </c:forEach>
 
 
 </tbody>

</table>

<div style="margin-left:15px; margin-right: 15px">
<small><b>(*) Intern:</b> Significa que el propi plugin t&eacute; la capacitat de proveir la funcionalitat. Per exemple en segellat de temps significa que el propi plugin estampa i proveeix el segell de temps d'alguna autoritat certificadora. </small><br/>
<small><b>(**) Extern:</b> Permet aquesta funcionalitat si se li ofereix des de l'exterior. Per exemple en segellat de temps significa que si li passes un Segell de Temps, t&eacute; la capaciatat d'estampar-ho en la firma. </small><br/>
</div>

<%@ include file="/WEB-INF/views/html_footer.jsp"%>