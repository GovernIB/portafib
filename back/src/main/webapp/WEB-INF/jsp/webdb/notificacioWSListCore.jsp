  <c:if test="${empty notificacioWSItems}">
     <%@include file="notificacioWSListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty notificacioWSItems}">

  <div class="row" style="margin-left: 0px;">
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  <fieldset>
  <legend class="sr-only">Selecci&oacute; d'elements de la llista</legend>
  </c:if>
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;" > 
    <thead>
      <tr>

          <%@include file="notificacioWSListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="notificacioWSListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="notificacioWSListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="notificacioWS" items="${notificacioWSItems}">

        <tr id="notificacioWS_rowid_${notificacioWS.notificacioID}">
          <%@include file="notificacioWSListCoreMultipleSelect.jsp" %>

          <%@include file="notificacioWSListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="notificacioWSListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  </fieldset>
  </c:if>
  </div>
  </c:if>
  
