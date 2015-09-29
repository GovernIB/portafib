  <c:if test="${empty estatDeFirmaItems}">
     <%@include file="estatDeFirmaListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty estatDeFirmaItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="estatDeFirmaListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="estatDeFirmaListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="estatDeFirmaListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="estatDeFirma" items="${estatDeFirmaItems}">

        <tr>
          <%@include file="estatDeFirmaListCoreMultipleSelect.jsp" %>

          <%@include file="estatDeFirmaListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="estatDeFirmaListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
