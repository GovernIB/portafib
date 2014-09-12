  <c:if test="${empty roleUsuariEntitatItems}">
     <%@include file="roleUsuariEntitatListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty roleUsuariEntitatItems}">

  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
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

        <tr>
          <%@include file="roleUsuariEntitatListCoreMultipleSelect.jsp" %>

          <%@include file="roleUsuariEntitatListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="roleUsuariEntitatListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </c:if>
  
