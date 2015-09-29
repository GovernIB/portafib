  <c:if test="${empty posicioTaulaFirmesItems}">
     <%@include file="posicioTaulaFirmesListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty posicioTaulaFirmesItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="posicioTaulaFirmesListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="posicioTaulaFirmesListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="posicioTaulaFirmesListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="posicioTaulaFirmes" items="${posicioTaulaFirmesItems}">

        <tr>
          <%@include file="posicioTaulaFirmesListCoreMultipleSelect.jsp" %>

          <%@include file="posicioTaulaFirmesListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="posicioTaulaFirmesListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
