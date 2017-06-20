  <c:if test="${empty fluxDeFirmesItems}">
     <%@include file="fluxDeFirmesListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty fluxDeFirmesItems}">

  <div class="row" style="margin-left: 0px;">
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

        <tr id="fluxDeFirmes_rowid_${fluxDeFirmes.fluxDeFirmesID}">
          <%@include file="fluxDeFirmesListCoreMultipleSelect.jsp" %>

          <%@include file="fluxDeFirmesListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="fluxDeFirmesListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
