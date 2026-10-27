  <c:if test="${empty pluginCridadaItems}">
     <%@include file="pluginCridadaListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty pluginCridadaItems}">

  <div class="row" style="margin-left: 0px;">
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  <fieldset>
  <legend class="sr-only">Selecci&oacute; d'elements de la llista</legend>
  </c:if>
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;" > 
    <thead>
      <tr>

          <%@include file="pluginCridadaListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="pluginCridadaListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="pluginCridadaListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="pluginCridada" items="${pluginCridadaItems}">

        <tr id="pluginCridada_rowid_${pluginCridada.pluginCridadaID}">
          <%@include file="pluginCridadaListCoreMultipleSelect.jsp" %>

          <%@include file="pluginCridadaListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="pluginCridadaListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  </fieldset>
  </c:if>
  </div>
  </c:if>
  
