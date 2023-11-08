<%@page import="org.fundaciobit.genapp.common.web.html.HtmlCSS"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<form:form modelAttribute="seleccioNifForm" method="post" name="seleccioNifForm">

<%=HtmlCSS.TITOL_BEGIN %><fmt:message key="${seleccioNifForm.titol}" /><%=HtmlCSS.TITOL_END %>

<%=HtmlCSS.SUBTITOL_BEGIN %><fmt:message key="${seleccioNifForm.subtitol}" /><%=HtmlCSS.SUBTITOL_END %>

<table class="tdformlabel table-sm table table-bordered table-striped marTop10">
<tbody>
  <tr>
      <td>
        <label><fmt:message key="usuariPersona.nifusername" /> &nbsp;(*)</label>
      </td>
      <td><form:errors path="nif" cssClass="errorField alert alert-danger" />
          <form:input cssClass="input" path="nif"/></td>
  </tr>
</tbody>
</table>

<div class="navbar-form pagination-centered">
  <button class="btn btn-primary" type="submit" ><fmt:message key="genapp.continue"/></button>
  <button class="btn btn-secondary" type="button" onclick="goTo('<c:url value="${seleccioNifForm.cancelUrl}"/>')"><fmt:message key="genapp.cancel"/></button>
</div>
</form:form>