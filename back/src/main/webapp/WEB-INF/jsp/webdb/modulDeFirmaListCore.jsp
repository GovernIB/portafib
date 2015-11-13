  <c:if test="${empty modulDeFirmaItems}">
     <%@include file="modulDeFirmaListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty modulDeFirmaItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="modulDeFirmaListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="modulDeFirmaListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="modulDeFirmaListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="modulDeFirma" items="${modulDeFirmaItems}">

        <tr>
          <%@include file="modulDeFirmaListCoreMultipleSelect.jsp" %>

          <%@include file="modulDeFirmaListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="modulDeFirmaListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
