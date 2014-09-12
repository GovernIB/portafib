<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<div class="lead" style="margin-bootom:10px">
  Generar Filtre CAIB 
</div>

<form:form modelAttribute="generarFiltreCaibForm" method="${method}" name="generarFiltreCaibForm">

<table class="table table-condensed table-bordered" style="width:auto;">
<tbody>
  <tr>
    <td>
       <label>URL:</label>
    </td>
    <td>
       <form:errors path="url" cssClass="errorField alert alert-error" />
       <form:input cssClass="input-xxlarge" path="url"/>
    </td>
   </tr>
   <c:if test="${not empty generarFiltreCaibForm.filtre}">
   <tr>
    <td>
       <label>Filtre:</label>
    </td>
    <td>
       <form:errors path="filtre" cssClass="errorField alert alert-error" />
       <form:textarea cssClass="input-xxlarge" path="filtre" rows="17"  wrap="hard" />
       
    </td>
   </tr>
   </c:if>
</tbody>
</table>
 

<button class="btn btn-primary" type="submit" class="btn"><fmt:message key="acceptar"/></button>

</form:form>
