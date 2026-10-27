  <c:if test="${empty peticioDeFirmaItems}">
     <%@include file="peticioDeFirmaListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty peticioDeFirmaItems}">

  <div class="row" style="margin-left: 0px;">
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  <fieldset>
  <legend class="sr-only">Selecci&oacute; d'elements de la llista</legend>
  </c:if>
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;" > 
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
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  </fieldset>
  </c:if>
  </div>
  </c:if>
  
