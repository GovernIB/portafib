  <c:if test="${empty annexItems}">
     <%@include file="annexListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty annexItems}">

  <div class="row" style="margin-left: 0px;">
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  <fieldset>
  <legend class="sr-only">Selecci&oacute; d'elements de la llista</legend>
  </c:if>
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;" > 
    <thead>
      <tr>

          <%@include file="annexListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="annexListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="annexListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="annex" items="${annexItems}">

        <tr id="annex_rowid_${annex.annexID}">
          <%@include file="annexListCoreMultipleSelect.jsp" %>

          <%@include file="annexListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="annexListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  </fieldset>
  </c:if>
  </div>
  </c:if>
  
