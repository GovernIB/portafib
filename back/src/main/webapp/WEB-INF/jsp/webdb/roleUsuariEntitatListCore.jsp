  <c:if test="${empty roleUsuariEntitatItems}">
     <%@include file="roleUsuariEntitatListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty roleUsuariEntitatItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="roleUsuariEntitatListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="roleUsuariEntitatListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="roleUsuariEntitatListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="roleUsuariEntitat" items="${roleUsuariEntitatItems}">

        <tr id="roleUsuariEntitat_rowid_${roleUsuariEntitat.id}">
          <%@include file="roleUsuariEntitatListCoreMultipleSelect.jsp" %>

          <%@include file="roleUsuariEntitatListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="roleUsuariEntitatListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
