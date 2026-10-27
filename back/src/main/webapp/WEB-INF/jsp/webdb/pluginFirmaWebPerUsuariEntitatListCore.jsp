  <c:if test="${empty pluginFirmaWebPerUsuariEntitatItems}">
     <%@include file="pluginFirmaWebPerUsuariEntitatListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty pluginFirmaWebPerUsuariEntitatItems}">

  <div class="row" style="margin-left: 0px;">
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  <fieldset>
  <legend class="sr-only">Selecci&oacute; d'elements de la llista</legend>
  </c:if>
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;" > 
    <thead>
      <tr>

          <%@include file="pluginFirmaWebPerUsuariEntitatListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="pluginFirmaWebPerUsuariEntitatListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="pluginFirmaWebPerUsuariEntitatListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="pluginFirmaWebPerUsuariEntitat" items="${pluginFirmaWebPerUsuariEntitatItems}">

        <tr id="pluginFirmaWebPerUsuariEntitat_rowid_${pluginFirmaWebPerUsuariEntitat.pluginFirmaWebPerUsrEntID}">
          <%@include file="pluginFirmaWebPerUsuariEntitatListCoreMultipleSelect.jsp" %>

          <%@include file="pluginFirmaWebPerUsuariEntitatListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="pluginFirmaWebPerUsuariEntitatListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  </fieldset>
  </c:if>
  </div>
  </c:if>
  
