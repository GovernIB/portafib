<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<form:form modelAttribute="seleccioNifForm" method="${method}" name="seleccioNifForm">

<h3 class="tabs_involved">
  <fmt:message key="${seleccioNifForm.titol}" />
</h3>

<fmt:message key="${seleccioNifForm.subtitol}" />

<table class="tdformlabel table-condensed table table-bordered table-striped marTop10">
<tbody>
  <tr>
      <td>
        <label><fmt:message key="usuariPersona.nif" /> &nbsp;(*)</label>
      </td>
      <td><form:errors path="nif" cssClass="errorField alert alert-error" />
          <form:input cssClass="input" path="nif"/></td>
  </tr>
</tbody>
</table>

<div class="navbar-form pagination-centered">
  <button class="btn btn-primary" type="submit" class="btn"><fmt:message key="genapp.continue"/></button>
  <button class="btn" type="button" onclick="goTo('<c:url value="${seleccioNifForm.cancelUrl}"/>')"><fmt:message key="genapp.cancel"/></button>
</div>
</form:form>