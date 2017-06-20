  <c:if test="${empty peticioDeFirmaItems}">
     <%@include file="peticioDeFirmaListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty peticioDeFirmaItems}">

  <div class="row" style="margin-left: 0px;">
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

        <tr id="peticioDeFirma_rowid_${peticioDeFirma.peticioDeFirmaID}">
          <%@include file="peticioDeFirmaListCoreMultipleSelect.jsp" %>

          <%@include file="peticioDeFirmaListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="peticioDeFirmaListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
