  <c:if test="${empty usuariPersonaItems}">
     <%@include file="usuariPersonaListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty usuariPersonaItems}">

  <div class="row" style="margin-left: 0px;">
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  <fieldset>
  <legend class="sr-only">Selecci&oacute; d'elements de la llista</legend>
  </c:if>
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;" > 
    <thead>
      <tr>

          <%@include file="usuariPersonaListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="usuariPersonaListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="usuariPersonaListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="usuariPersona" items="${usuariPersonaItems}">

        <tr id="usuariPersona_rowid_${usuariPersona.usuariPersonaID}">
          <%@include file="usuariPersonaListCoreMultipleSelect.jsp" %>

          <%@include file="usuariPersonaListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="usuariPersonaListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  </fieldset>
  </c:if>
  </div>
  </c:if>
  
