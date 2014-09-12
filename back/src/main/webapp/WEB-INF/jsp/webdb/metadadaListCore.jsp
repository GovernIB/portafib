  <c:if test="${empty metadadaItems}">
     <%@include file="metadadaListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty metadadaItems}">

  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="metadadaListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="metadadaListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="metadadaListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="metadada" items="${metadadaItems}">

        <tr>
          <%@include file="metadadaListCoreMultipleSelect.jsp" %>

          <%@include file="metadadaListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="metadadaListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </c:if>
  
