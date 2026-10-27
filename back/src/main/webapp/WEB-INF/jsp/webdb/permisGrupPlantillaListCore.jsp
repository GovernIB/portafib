  <c:if test="${empty permisGrupPlantillaItems}">
     <%@include file="permisGrupPlantillaListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty permisGrupPlantillaItems}">

  <div class="row" style="margin-left: 0px;">
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  <fieldset>
  <legend class="sr-only">Selecci&oacute; d'elements de la llista</legend>
  </c:if>
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;" > 
    <thead>
      <tr>

          <%@include file="permisGrupPlantillaListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="permisGrupPlantillaListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="permisGrupPlantillaListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="permisGrupPlantilla" items="${permisGrupPlantillaItems}">

        <tr id="permisGrupPlantilla_rowid_${permisGrupPlantilla.permisGrupPlantillaID}">
          <%@include file="permisGrupPlantillaListCoreMultipleSelect.jsp" %>

          <%@include file="permisGrupPlantillaListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="permisGrupPlantillaListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  </fieldset>
  </c:if>
  </div>
  </c:if>
  
