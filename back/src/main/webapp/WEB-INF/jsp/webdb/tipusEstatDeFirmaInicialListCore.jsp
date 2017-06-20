  <c:if test="${empty tipusEstatDeFirmaInicialItems}">
     <%@include file="tipusEstatDeFirmaInicialListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty tipusEstatDeFirmaInicialItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="tipusEstatDeFirmaInicialListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="tipusEstatDeFirmaInicialListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="tipusEstatDeFirmaInicialListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="tipusEstatDeFirmaInicial" items="${tipusEstatDeFirmaInicialItems}">

        <tr id="tipusEstatDeFirmaInicial_rowid_${tipusEstatDeFirmaInicial.tipusEstatDeFirmaInicialID}">
          <%@include file="tipusEstatDeFirmaInicialListCoreMultipleSelect.jsp" %>

          <%@include file="tipusEstatDeFirmaInicialListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="tipusEstatDeFirmaInicialListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
