  <c:if test="${empty idiomaItems}">
     <%@include file="idiomaListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty idiomaItems}">

  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
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

        <tr>
          <%@include file="idiomaListCoreMultipleSelect.jsp" %>

          <%@include file="idiomaListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="idiomaListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </c:if>
  
