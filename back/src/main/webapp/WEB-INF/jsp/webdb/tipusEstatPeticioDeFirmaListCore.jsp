  <c:if test="${empty tipusEstatPeticioDeFirmaItems}">
     <%@include file="tipusEstatPeticioDeFirmaListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty tipusEstatPeticioDeFirmaItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="tipusEstatPeticioDeFirmaListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="tipusEstatPeticioDeFirmaListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="tipusEstatPeticioDeFirmaListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="tipusEstatPeticioDeFirma" items="${tipusEstatPeticioDeFirmaItems}">

        <tr id="tipusEstatPeticioDeFirma_rowid_${tipusEstatPeticioDeFirma.tipusEstatPeticioDeFirmaID}">
          <%@include file="tipusEstatPeticioDeFirmaListCoreMultipleSelect.jsp" %>

          <%@include file="tipusEstatPeticioDeFirmaListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="tipusEstatPeticioDeFirmaListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
