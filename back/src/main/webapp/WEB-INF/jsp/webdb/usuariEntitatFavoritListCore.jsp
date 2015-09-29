  <c:if test="${empty usuariEntitatFavoritItems}">
     <%@include file="usuariEntitatFavoritListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty usuariEntitatFavoritItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
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

        <tr>
          <%@include file="usuariEntitatFavoritListCoreMultipleSelect.jsp" %>

          <%@include file="usuariEntitatFavoritListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="usuariEntitatFavoritListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
