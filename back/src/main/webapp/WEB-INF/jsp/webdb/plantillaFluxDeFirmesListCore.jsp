  <c:if test="${empty plantillaFluxDeFirmesItems}">
     <%@include file="plantillaFluxDeFirmesListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty plantillaFluxDeFirmesItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="plantillaFluxDeFirmesListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="plantillaFluxDeFirmesListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="plantillaFluxDeFirmesListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="plantillaFluxDeFirmes" items="${plantillaFluxDeFirmesItems}">

        <tr>
          <%@include file="plantillaFluxDeFirmesListCoreMultipleSelect.jsp" %>

          <%@include file="plantillaFluxDeFirmesListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="plantillaFluxDeFirmesListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
