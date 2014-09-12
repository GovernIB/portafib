  <c:if test="${empty fluxDeFirmesItems}">
     <%@include file="fluxDeFirmesListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty fluxDeFirmesItems}">

  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="fluxDeFirmesListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="fluxDeFirmesListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="fluxDeFirmesListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="fluxDeFirmes" items="${fluxDeFirmesItems}">

        <tr>
          <%@include file="fluxDeFirmesListCoreMultipleSelect.jsp" %>

          <%@include file="fluxDeFirmesListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="fluxDeFirmesListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </c:if>
  
