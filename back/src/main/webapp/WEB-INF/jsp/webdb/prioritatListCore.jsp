  <c:if test="${empty prioritatItems}">
     <%@include file="prioritatListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty prioritatItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="prioritatListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="prioritatListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="prioritatListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="prioritat" items="${prioritatItems}">

        <tr>
          <%@include file="prioritatListCoreMultipleSelect.jsp" %>

          <%@include file="prioritatListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="prioritatListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
