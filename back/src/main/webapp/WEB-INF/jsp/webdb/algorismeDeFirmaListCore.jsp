  <c:if test="${empty algorismeDeFirmaItems}">
     <%@include file="algorismeDeFirmaListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty algorismeDeFirmaItems}">

  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="algorismeDeFirmaListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="algorismeDeFirmaListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="algorismeDeFirmaListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="algorismeDeFirma" items="${algorismeDeFirmaItems}">

        <tr>
          <%@include file="algorismeDeFirmaListCoreMultipleSelect.jsp" %>

          <%@include file="algorismeDeFirmaListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="algorismeDeFirmaListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </c:if>
  
