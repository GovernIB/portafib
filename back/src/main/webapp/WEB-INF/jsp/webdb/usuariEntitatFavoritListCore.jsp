  <c:if test="${empty usuariEntitatFavoritItems}">
     <%@include file="usuariEntitatFavoritListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty usuariEntitatFavoritItems}">

  <div class="row" style="margin-left: 0px;">
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  <fieldset>
  <legend class="sr-only">Selecci&oacute; d'elements de la llista</legend>
  </c:if>
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;" > 
    <thead>
      <tr>

          <%@include file="usuariEntitatFavoritListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="usuariEntitatFavoritListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="usuariEntitatFavoritListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="usuariEntitatFavorit" items="${usuariEntitatFavoritItems}">

        <tr id="usuariEntitatFavorit_rowid_${usuariEntitatFavorit.ID}">
          <%@include file="usuariEntitatFavoritListCoreMultipleSelect.jsp" %>

          <%@include file="usuariEntitatFavoritListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="usuariEntitatFavoritListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  </fieldset>
  </c:if>
  </div>
  </c:if>
  
