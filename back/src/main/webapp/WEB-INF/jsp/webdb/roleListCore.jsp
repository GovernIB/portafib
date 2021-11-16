  <c:if test="${empty roleItems}">
     <%@include file="roleListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty roleItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-responsive" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="roleListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="roleListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="roleListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="role" items="${roleItems}">

        <tr id="role_rowid_${role.roleID}">
          <%@include file="roleListCoreMultipleSelect.jsp" %>

          <%@include file="roleListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="roleListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
