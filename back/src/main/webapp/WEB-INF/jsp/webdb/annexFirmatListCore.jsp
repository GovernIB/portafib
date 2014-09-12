  <c:if test="${empty annexFirmatItems}">
     <%@include file="annexFirmatListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty annexFirmatItems}">

  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="annexFirmatListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="annexFirmatListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="annexFirmatListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="annexFirmat" items="${annexFirmatItems}">

        <tr>
          <%@include file="annexFirmatListCoreMultipleSelect.jsp" %>

          <%@include file="annexFirmatListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="annexFirmatListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </c:if>
  
