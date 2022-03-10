  <c:if test="${empty propietatGlobalItems}">
     <%@include file="propietatGlobalListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty propietatGlobalItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp" style="width:auto;"> 
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

        <tr id="propietatGlobal_rowid_${propietatGlobal.propietatGlobalID}">
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
  
