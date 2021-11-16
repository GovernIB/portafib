  <c:if test="${empty custodiaInfoItems}">
     <%@include file="custodiaInfoListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty custodiaInfoItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-responsive" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="custodiaInfoListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="custodiaInfoListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="custodiaInfoListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="custodiaInfo" items="${custodiaInfoItems}">

        <tr id="custodiaInfo_rowid_${custodiaInfo.custodiaInfoID}">
          <%@include file="custodiaInfoListCoreMultipleSelect.jsp" %>

          <%@include file="custodiaInfoListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="custodiaInfoListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
