  <c:if test="${empty idiomaItems}">
     <%@include file="idiomaListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty idiomaItems}">

  <div class="row" style="margin-left: 0px;">
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  <fieldset>
  <legend class="sr-only">Selecci&oacute; d'elements de la llista</legend>
  </c:if>
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;" > 
    <thead>
      <tr>

          <%@include file="idiomaListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="idiomaListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="idiomaListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="idioma" items="${idiomaItems}">

        <tr id="idioma_rowid_${idioma.idiomaID}">
          <%@include file="idiomaListCoreMultipleSelect.jsp" %>

          <%@include file="idiomaListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="idiomaListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  </fieldset>
  </c:if>
  </div>
  </c:if>
  
