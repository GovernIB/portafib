  <c:if test="${empty blocDeFirmesItems}">
     <%@include file="blocDeFirmesListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty blocDeFirmesItems}">

  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="blocDeFirmesListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="blocDeFirmesListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="blocDeFirmesListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="blocDeFirmes" items="${blocDeFirmesItems}">

        <tr>
          <%@include file="blocDeFirmesListCoreMultipleSelect.jsp" %>

          <%@include file="blocDeFirmesListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="blocDeFirmesListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </c:if>
  
