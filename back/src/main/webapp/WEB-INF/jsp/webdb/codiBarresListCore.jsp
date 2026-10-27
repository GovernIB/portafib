  <c:if test="${empty codiBarresItems}">
     <%@include file="codiBarresListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty codiBarresItems}">

  <div class="row" style="margin-left: 0px;">
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  <fieldset>
  <legend class="sr-only">Selecci&oacute; d'elements de la llista</legend>
  </c:if>
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;" > 
    <thead>
      <tr>

          <%@include file="codiBarresListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="codiBarresListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="codiBarresListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="codiBarres" items="${codiBarresItems}">

        <tr id="codiBarres_rowid_${codiBarres.codiBarresID}">
          <%@include file="codiBarresListCoreMultipleSelect.jsp" %>

          <%@include file="codiBarresListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="codiBarresListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  </fieldset>
  </c:if>
  </div>
  </c:if>
  
