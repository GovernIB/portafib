  <c:if test="${empty annexItems}">
     <%@include file="annexListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty annexItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
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

        <tr>
          <%@include file="annexListCoreMultipleSelect.jsp" %>

          <%@include file="annexListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="annexListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
