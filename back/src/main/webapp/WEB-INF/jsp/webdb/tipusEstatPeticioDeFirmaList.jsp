<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<form:form name="tipusEstatPeticioDeFirma" cssClass="form-search"  modelAttribute="tipusEstatPeticioDeFirmaFilterForm" 
        method="${method}"  enctype="multipart/form-data">

  <%@include file="tipusEstatPeticioDeFirmaListCommon.jsp" %>
  <div class="filterLine lead" style="margin-bootom:10px">
    <%@include file="tipusEstatPeticioDeFirmaListHeaderButtons.jsp" %>
    <%-- ADD HERE NEW HEADER BUTTONS (Multiple Select or similar to add item)  --%>

  </div>
  <%@include file="tipusEstatPeticioDeFirmaListSubtitle.jsp" %>
  <%@include file="tipusEstatPeticioDeFirmaListFilterBy.jsp" %>
  <%-- Inici de div d'AGRUPACIO i TAULA CONTINGUTS --%>  
  <div>
  <%@include file="tipusEstatPeticioDeFirmaListGroupBy.jsp" %>
  <%-- Inici de div de TAULA CONTINGUTS --%>
  <div style="width: 100%;">
  <%@include file="tipusEstatPeticioDeFirmaListCore.jsp" %>
  <c:if test="${not empty tipusEstatPeticioDeFirmaItems}">
          <%@include file="webdbPagination.jsp" %>

  </c:if>

  </div> <%-- Final de div de TAULA CONTINGUTS --%>
  <%--  ADD HERE OTHER CONTENT --%>

  <c:if test="${__theFilterForm.attachedAdditionalJspCode}">
          <%@include file="../webdbmodificable/tipusEstatPeticioDeFirmaListModificable.jsp" %>
  </c:if>
  
  </div> <%-- Final de div d'AGRUPACIO i TAULA CONTINGUTS --%>

</form:form> 
    

