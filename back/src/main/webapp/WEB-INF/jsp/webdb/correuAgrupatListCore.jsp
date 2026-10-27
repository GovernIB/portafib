  <c:if test="${empty correuAgrupatItems}">
     <%@include file="correuAgrupatListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty correuAgrupatItems}">

  <div class="row" style="margin-left: 0px;">
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  <fieldset>
  <legend class="sr-only">Selecci&oacute; d'elements de la llista</legend>
  </c:if>
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;" > 
    <thead>
      <tr>

          <%@include file="correuAgrupatListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="correuAgrupatListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="correuAgrupatListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="correuAgrupat" items="${correuAgrupatItems}">

        <tr id="correuAgrupat_rowid_${correuAgrupat.correuAgrupatID}">
          <%@include file="correuAgrupatListCoreMultipleSelect.jsp" %>

          <%@include file="correuAgrupatListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="correuAgrupatListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  <c:if test="${__theFilterForm.visibleMultipleSelection}">
  </fieldset>
  </c:if>
  </div>
  </c:if>
  
