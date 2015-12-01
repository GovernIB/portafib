  <c:if test="${empty propietatGlobalItems}">
     <%@include file="propietatGlobalListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty propietatGlobalItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="propietatGlobalListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="propietatGlobalListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="propietatGlobalListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="propietatGlobal" items="${propietatGlobalItems}">

        <tr>
          <%@include file="propietatGlobalListCoreMultipleSelect.jsp" %>

          <%@include file="propietatGlobalListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="propietatGlobalListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
