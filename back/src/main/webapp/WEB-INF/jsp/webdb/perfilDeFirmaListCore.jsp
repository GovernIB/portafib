  <c:if test="${empty perfilDeFirmaItems}">
     <%@include file="perfilDeFirmaListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty perfilDeFirmaItems}">

  <div class="row" style="margin-left: 0px;">
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  <fieldset>
  <legend class="sr-only">Selecci&oacute; d'elements de la llista</legend>
  </c:if>
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;" > 
    <thead>
      <tr>

          <%@include file="perfilDeFirmaListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="perfilDeFirmaListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="perfilDeFirmaListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="perfilDeFirma" items="${perfilDeFirmaItems}">

        <tr id="perfilDeFirma_rowid_${perfilDeFirma.usuariAplicacioPerfilID}">
          <%@include file="perfilDeFirmaListCoreMultipleSelect.jsp" %>

          <%@include file="perfilDeFirmaListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="perfilDeFirmaListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  </fieldset>
  </c:if>
  </div>
  </c:if>
  
