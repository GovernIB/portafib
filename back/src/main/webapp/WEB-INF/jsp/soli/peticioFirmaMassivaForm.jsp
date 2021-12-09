<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<form:form modelAttribute="peticioFirmaMassivaForm" method="post" name="peticioFirmaMassivaForm" enctype="multipart/form-data">

<h3 class="tabs_involved">
  <fmt:message key="peticioFirmaMassiva.titol" />
</h3>

<fmt:message key="peticioFirmaMassiva.subtitol" />

<table class="tdformlabel table-condensed table table-bordered table-striped marTop10" style="margin-bottom: 0px;">
<tbody>
  <tr>
      <td>
        <label><fmt:message key="peticioDeFirma.titol" />
        &nbsp;
        <a href="#" data-toggle="tooltip" data-placement="top" title="<fmt:message key="peticioFirmaMassiva.ajudasubstitucio" />"><i class="icon-info-sign"></i></a>
        &nbsp;(*)
        </label>
      </td>
      <td>
       <form:errors  path="titolPeticio" cssClass="errorField alert alert-danger" />
       <input  name="titolPeticio" type="text" class="col-md-9-optional" placeholder="<fmt:message key="peticioFirmaMassiva.ajudasubstitucio" />" >
      </td>
  </tr>
  <tr>
      <td>
        <label><fmt:message key="peticioDeFirma.descripcio" />
        &nbsp;
        <a href="#" data-toggle="tooltip" data-placement="top" title="<fmt:message key="peticioFirmaMassiva.ajudasubstitucio" />"><i class="icon-info-sign"></i></a>
        &nbsp;(*)
        </label>
      </td>
      <td>
       <form:errors  path="descripcio" cssClass="errorField alert alert-danger" />
       <input name="descripcio" type="text" class="col-md-9-optional" placeholder="<fmt:message key="peticioFirmaMassiva.ajudasubstitucio" />" >
      </td>
  </tr>
  <tr>
      <td>
        <label><fmt:message key="peticioDeFirma.motiu" />
        &nbsp;
        <a href="#" data-toggle="tooltip" data-placement="top" title="<fmt:message key="peticioFirmaMassiva.ajudasubstitucio" />"><i class="icon-info-sign"></i></a>
        &nbsp;(*)
        </label>
      </td>
      <td>
       <form:errors  path="motiu" cssClass="errorField alert alert-danger" />
       <input  name="motiu" type="text" class="col-md-9-optional" placeholder="<fmt:message key="peticioFirmaMassiva.ajudasubstitucio" />" >
      </td>
  </tr>
  <tr>
      <td>
        <label><fmt:message key="peticioFirmaMassiva.peticiobase" /> &nbsp;(*)</label>
      </td>
      <td valign="top">
         <form:errors path="peticioDeFirmaID" cssClass="errorField alert alert-danger" />
         <form:select id="peticioDeFirmaID" path="peticioDeFirmaID"  cssClass="col-md-9-optional">
            <c:forEach items="${peticioFirmaMassivaForm.peticionsDeFirmesBase}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
         </form:select>
         
         <button onclick="mostrarFlux('peticioDeFirmaID');" type="button" class="btn"><i class="fas fa-eye"></i></button>
      </td>
  </tr>
  <tr>
      <td>
        <label><fmt:message key="peticioFirmaMassiva.fitxers" />
        &nbsp;
        <a href="#" data-toggle="tooltip" data-placement="top" title="<fmt:message key="peticioFirmaMassiva.ajudafitxers" />"><i class="icon-info-sign"></i></a>
        &nbsp;(*)</label>
      </td>
      <td>
       <form:errors path="files" cssClass="errorField alert alert-danger" />
       <form:input path="files" type="file" multiple="multiple" class="col-md-9-optional" />
      </td>
  </tr>
</tbody>
</table>

<div class="navbar-form pagination-centered" style="margin-top: 15px;">
  <button class="btn btn-primary" type="submit" class="btn"><fmt:message key="peticioFirmaMassiva.boto"/></button>
</div>

<script type="text/javascript">
      function mostrarFlux(selectID) {
          var e = document.getElementById(selectID);
          var peticio = e.options[e.selectedIndex].value;
          window.open('<c:url value="/soli/plantilla/viewfluxpeticioid"/>/' + peticio + '?readOnly=true' ,'popup','toolbar=no,directories=no,menubar=no,location=no,scrollbars=yes,width=560,height=650');
      }
</script>


</form:form>

