  <c:if test="${empty grupEntitatItems}">
     <%@include file="grupEntitatListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty grupEntitatItems}">

  <div class="row" style="margin-left: 0px;">
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  <fieldset>
  <legend class="sr-only">Selecci&oacute; d'elements de la llista</legend>
  </c:if>
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;" > 
    <thead>
      <tr>

          <%@include file="grupEntitatListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="grupEntitatListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="grupEntitatListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="grupEntitat" items="${grupEntitatItems}">

        <tr id="grupEntitat_rowid_${grupEntitat.grupEntitatID}">
          <%@include file="grupEntitatListCoreMultipleSelect.jsp" %>

          <%@include file="grupEntitatListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="grupEntitatListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  </fieldset>
  </c:if>
  </div>
  </c:if>
  
