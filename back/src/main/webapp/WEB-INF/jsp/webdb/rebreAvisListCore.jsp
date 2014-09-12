  <c:if test="${empty rebreAvisItems}">
     <%@include file="rebreAvisListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty rebreAvisItems}">

  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="rebreAvisListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="rebreAvisListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="rebreAvisListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="rebreAvis" items="${rebreAvisItems}">

        <tr>
          <%@include file="rebreAvisListCoreMultipleSelect.jsp" %>

          <%@include file="rebreAvisListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="rebreAvisListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </c:if>
  
