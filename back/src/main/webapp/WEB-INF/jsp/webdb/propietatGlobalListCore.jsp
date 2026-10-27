  <c:if test="${empty propietatGlobalItems}">
     <%@include file="propietatGlobalListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty propietatGlobalItems}">

  <div class="row" style="margin-left: 0px;">
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  <fieldset>
  <legend class="sr-only">Selecci&oacute; d'elements de la llista</legend>
  </c:if>
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;" > 
    <thead>
      <tr>

          <%@include file="propietatGlobalListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="propietatGlobalListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="propietatGlobalListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="propietatGlobal" items="${propietatGlobalItems}">

        <tr id="propietatGlobal_rowid_${propietatGlobal.propietatGlobalID}">
          <%@include file="propietatGlobalListCoreMultipleSelect.jsp" %>

          <%@include file="propietatGlobalListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="propietatGlobalListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  </fieldset>
  </c:if>
  </div>
  </c:if>
  
