  <c:if test="${empty permisGrupPlantillaItems}">
     <%@include file="permisGrupPlantillaListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty permisGrupPlantillaItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-responsive" style="width:auto;"> 
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
  </div>
  </c:if>
  
