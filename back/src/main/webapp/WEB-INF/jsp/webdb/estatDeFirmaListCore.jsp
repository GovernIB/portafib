  <c:if test="${empty estatDeFirmaItems}">
     <%@include file="estatDeFirmaListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty estatDeFirmaItems}">

  <div class="row" style="margin-left: 0px;">
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  <fieldset>
  <legend class="sr-only">Selecci&oacute; d'elements de la llista</legend>
  </c:if>
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;" > 
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

        <tr id="estatDeFirma_rowid_${estatDeFirma.estatDeFirmaID}">
          <%@include file="estatDeFirmaListCoreMultipleSelect.jsp" %>

          <%@include file="estatDeFirmaListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="estatDeFirmaListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  </fieldset>
  </c:if>
  </div>
  </c:if>
  
