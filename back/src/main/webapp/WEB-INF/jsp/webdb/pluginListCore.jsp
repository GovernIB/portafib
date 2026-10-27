  <c:if test="${empty pluginItems}">
     <%@include file="pluginListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty pluginItems}">

  <div class="row" style="margin-left: 0px;">
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  <fieldset>
  <legend class="sr-only">Selecci&oacute; d'elements de la llista</legend>
  </c:if>
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;" > 
    <thead>
      <tr>

          <%@include file="pluginListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="pluginListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="pluginListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="plugin" items="${pluginItems}">

        <tr id="plugin_rowid_${plugin.pluginID}">
          <%@include file="pluginListCoreMultipleSelect.jsp" %>

          <%@include file="pluginListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="pluginListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  </fieldset>
  </c:if>
  </div>
  </c:if>
  
