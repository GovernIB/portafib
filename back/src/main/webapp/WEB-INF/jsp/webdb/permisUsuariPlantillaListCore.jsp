  <c:if test="${empty permisUsuariPlantillaItems}">
     <%@include file="permisUsuariPlantillaListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty permisUsuariPlantillaItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="permisUsuariPlantillaListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="permisUsuariPlantillaListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="permisUsuariPlantillaListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="permisUsuariPlantilla" items="${permisUsuariPlantillaItems}">

        <tr id="permisUsuariPlantilla_rowid_${permisUsuariPlantilla.permisUsuariPlantillaID}">
          <%@include file="permisUsuariPlantillaListCoreMultipleSelect.jsp" %>

          <%@include file="permisUsuariPlantillaListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="permisUsuariPlantillaListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
