  <c:if test="${empty bitacolaItems}">
     <%@include file="bitacolaListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty bitacolaItems}">

  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="bitacolaListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="bitacolaListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="bitacolaListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="bitacola" items="${bitacolaItems}">

        <tr>
          <%@include file="bitacolaListCoreMultipleSelect.jsp" %>

          <%@include file="bitacolaListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="bitacolaListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </c:if>
  
