  <c:if test="${empty peticioDeFirmaItems}">
     <%@include file="peticioDeFirmaListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty peticioDeFirmaItems}">

  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="peticioDeFirmaListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="peticioDeFirmaListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="peticioDeFirmaListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="peticioDeFirma" items="${peticioDeFirmaItems}">

        <tr>
          <%@include file="peticioDeFirmaListCoreMultipleSelect.jsp" %>

          <%@include file="peticioDeFirmaListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="peticioDeFirmaListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </c:if>
  
