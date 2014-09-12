<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="seleccioUsuariEntitatForm" method="${method}" name="seleccioUsuariEntitatForm">

<table class="table table-condensed table-bordered" style="width:auto;">
<tbody>
  <!-- Cas Usuari-Entitat Persona-->
  <tr>
    <td>
       <label>NIF:</label> <form:input cssClass="input-xlarge" path="nif"/>
       <br>
       <form:errors path="nif" cssClass="errorField alert alert-error" />
    </td>
  </tr>
</tbody>
</table>
 

<button class="btn btn-primary" type="submit" class="btn"><fmt:message key="acceptar"/></button>

</form:form>
