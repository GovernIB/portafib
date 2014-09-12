  <c:if test="${empty firmaItems}">
     <%@include file="firmaListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty firmaItems}">

  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="firmaListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="firmaListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="firmaListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="firma" items="${firmaItems}">

        <tr>
          <%@include file="firmaListCoreMultipleSelect.jsp" %>

          <%@include file="firmaListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="firmaListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </c:if>
  
