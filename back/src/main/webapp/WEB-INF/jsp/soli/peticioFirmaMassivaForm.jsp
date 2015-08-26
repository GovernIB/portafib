<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<form:form modelAttribute="peticioFirmaMassivaForm" method="${method}" name="peticioFirmaMassivaForm" enctype="multipart/form-data">

<h3 class="tabs_involved">
  <fmt:message key="peticioFirmaMassiva.titol" />
</h3>

<fmt:message key="peticioFirmaMassiva.subtitol" />

<table class="tdformlabel table-condensed table table-bordered table-striped marTop10" style="margin-bottom: 0px;">
<tbody>
  <tr>
      <td>
        <label><fmt:message key="peticioDeFirma.titol" /> &nbsp;(*)</label>
      </td>
      <td>
       <form:errors  path="titolPeticio" cssClass="errorField alert alert-error" />
       <input  name="titolPeticio" type="text" class="input-xxlarge" >
      </td>
  </tr>
  <tr>
      <td>
        <label><fmt:message key="peticioDeFirma.descripcio" /> &nbsp;(*)</label>
      </td>
      <td>
       <form:errors  path="descripcio" cssClass="errorField alert alert-error" />
       <input  name="descripcio" type="text" class="input-xxlarge" >
      </td>
  </tr>
  <tr>
      <td>
        <label><fmt:message key="peticioDeFirma.motiu" /> &nbsp;(*)</label>
      </td>
      <td>
       <form:errors  path="motiu" cssClass="errorField alert alert-error" />
       <input  name="motiu" type="text" class="input-xxlarge" >
      </td>
  </tr>
  <tr>
      <td>
        <label><fmt:message key="peticioFirmaMassiva.peticiobase" /> &nbsp;(*)</label>
      </td>
      <td>
         <form:errors path="peticioDeFirmaID" cssClass="errorField alert alert-error" />
         <form:select path="peticioDeFirmaID"  cssClass="input-xxlarge">
            <c:forEach items="${peticioFirmaMassivaForm.peticionsDeFirmesBase}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
         </form:select>
      </td>
  </tr>
  <tr>
      <td>
        <label><fmt:message key="peticioFirmaMassiva.fitxers" /> &nbsp;(*)</label>
      </td>
      <td>
       <form:errors path="files" cssClass="errorField alert alert-error" />
       <form:input path="files" type="file" multiple="multiple" class="input-xxlarge" />
      </td>
  </tr>
</tbody>
</table>

<div class="navbar-form pagination-centered" style="margin-top: 15px;">
  <button class="btn btn-primary" type="submit" class="btn"><fmt:message key="genapp.continue"/></button>
</div>

</form:form>

