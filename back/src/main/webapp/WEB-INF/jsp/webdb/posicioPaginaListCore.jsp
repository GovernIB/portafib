  <c:if test="${empty posicioPaginaItems}">
     <%@include file="posicioPaginaListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty posicioPaginaItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="posicioPaginaListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="posicioPaginaListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="posicioPaginaListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="posicioPagina" items="${posicioPaginaItems}">

        <tr>
          <%@include file="posicioPaginaListCoreMultipleSelect.jsp" %>

          <%@include file="posicioPaginaListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="posicioPaginaListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
