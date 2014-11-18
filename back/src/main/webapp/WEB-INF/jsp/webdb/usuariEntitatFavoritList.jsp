<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<form:form name="usuariEntitatFavorit" cssClass="form-search"  modelAttribute="usuariEntitatFavoritFilterForm" 
        method="${method}"  enctype="multipart/form-data">

  <%@include file="usuariEntitatFavoritListCommon.jsp" %>
  <div class="filterLine lead" style="margin-bottom:10px">
    <%@include file="usuariEntitatFavoritListHeaderButtons.jsp" %>
    <%-- ADD HERE NEW HEADER BUTTONS (Multiple Select or similar to add item)  --%>

  </div>
  <%@include file="usuariEntitatFavoritListSubtitle.jsp" %>
  <%@include file="usuariEntitatFavoritListFilterBy.jsp" %>
  <%-- Inici de div d'AGRUPACIO i TAULA CONTINGUTS --%>  
  <div>
  <%@include file="usuariEntitatFavoritListGroupBy.jsp" %>
  <%-- Inici de div de TAULA CONTINGUTS --%>
  <div style="width: 100%;">
  <%@include file="usuariEntitatFavoritListCore.jsp" %>
  <c:if test="${not empty usuariEntitatFavoritItems}">
          <%@include file="webdbPagination.jsp" %>

  </c:if>

  </div> <%-- Final de div de TAULA CONTINGUTS --%>
  <%--  ADD HERE OTHER CONTENT --%>

  <c:if test="${__theFilterForm.attachedAdditionalJspCode}">
          <%@include file="../webdbmodificable/usuariEntitatFavoritListModificable.jsp" %>
  </c:if>
  
  </div> <%-- Final de div d'AGRUPACIO i TAULA CONTINGUTS --%>

</form:form> 
    

