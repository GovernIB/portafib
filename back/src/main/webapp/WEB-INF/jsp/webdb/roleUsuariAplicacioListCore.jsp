  <c:if test="${empty roleUsuariAplicacioItems}">
     <%@include file="roleUsuariAplicacioListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty roleUsuariAplicacioItems}">

  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="roleUsuariAplicacioListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="roleUsuariAplicacioListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="roleUsuariAplicacioListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="roleUsuariAplicacio" items="${roleUsuariAplicacioItems}">

        <tr>
          <%@include file="roleUsuariAplicacioListCoreMultipleSelect.jsp" %>

          <%@include file="roleUsuariAplicacioListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="roleUsuariAplicacioListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </c:if>
  
