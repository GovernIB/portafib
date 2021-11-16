  <c:if test="${empty grupEntitatItems}">
     <%@include file="grupEntitatListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty grupEntitatItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-responsive" style="width:auto;"> 
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
  </div>
  </c:if>
  
