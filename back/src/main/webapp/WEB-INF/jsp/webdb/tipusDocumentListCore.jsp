  <c:if test="${empty tipusDocumentItems}">
     <%@include file="tipusDocumentListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty tipusDocumentItems}">

  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="tipusDocumentListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="tipusDocumentListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="tipusDocumentListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="tipusDocument" items="${tipusDocumentItems}">

        <tr>
          <%@include file="tipusDocumentListCoreMultipleSelect.jsp" %>

          <%@include file="tipusDocumentListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="tipusDocumentListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </c:if>
  
