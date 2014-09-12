  <c:if test="${empty custodiaInfoItems}">
     <%@include file="custodiaInfoListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty custodiaInfoItems}">

  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="custodiaInfoListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="custodiaInfoListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="custodiaInfoListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="custodiaInfo" items="${custodiaInfoItems}">

        <tr>
          <%@include file="custodiaInfoListCoreMultipleSelect.jsp" %>

          <%@include file="custodiaInfoListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="custodiaInfoListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </c:if>
  
