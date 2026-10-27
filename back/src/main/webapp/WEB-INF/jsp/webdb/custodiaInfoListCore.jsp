  <c:if test="${empty custodiaInfoItems}">
     <%@include file="custodiaInfoListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty custodiaInfoItems}">

  <div class="row" style="margin-left: 0px;">
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  <fieldset>
  <legend class="sr-only">Selecci&oacute; d'elements de la llista</legend>
  </c:if>
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;" > 
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
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  </fieldset>
  </c:if>
  </div>
  </c:if>
  
