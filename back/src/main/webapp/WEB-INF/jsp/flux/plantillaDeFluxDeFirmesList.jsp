<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PlantillaFluxDeFirmesFields" className="es.caib.portafib.model.fields.PlantillaFluxDeFirmesFields" />


<form:form name="fluxDeFirmes" cssClass="form-search"  modelAttribute="fluxDeFirmesFilterForm" 
        method="post"  enctype="multipart/form-data">

  <%@include file="../webdb/fluxDeFirmesListCommon.jsp" %>
  <div class="filterLine lead" style="margin-bootom:10px">
  <%@include file="../webdb/fluxDeFirmesListHeaderButtons.jsp" %>
  <%-- ADD HERE NEW HEADER BUTTONS (Multiple Select or similar to add item)  --%>
  <a class="btn pull-right btn-small" role="button" data-toggle="modal"
    href="<c:url value="${contexte}/new"/>"> <i class="fas fa-plus-circle"></i>
   <fmt:message key="plantilla.crear" />
  </a>

  </div>
  <%@include file="../webdb/fluxDeFirmesListSubtitle.jsp" %>
  <%@include file="../webdb/fluxDeFirmesListFilterBy.jsp" %>
  <%-- Inici de div d'AGRUPACIO i TAULA CONTINGUTS --%>  
  <div>
  <%@include file="../webdb/fluxDeFirmesListGroupBy.jsp" %>
  <%-- Inici de div de TAULA CONTINGUTS --%>
  <div style="width: 100%;">
  <c:if test="${empty fluxDeFirmesItems}">
     <%@include file="../webdb/fluxDeFirmesListEmpty.jsp" %>
  </c:if>
  
  <c:if test="${not empty fluxDeFirmesItems}">


  <%-- usuariPersona: + True indica que és un usuari-persona,           --%>
  <%--                + False indica que és un flux de usuari-aplicacio --%>
   
  <c:set var="pathUsuari" value="fluxDeFirmes.plantillaFluxDeFirmes"/>
   
  <c:if test ="${usuariPersona == true}" >
    <c:set var="fullPathUsuari" value="${pathUsuari}.usuariEntitatID"/>
  </c:if>
  <c:if test ="${usuariPersona == false}" >  
    <c:set var="fullPathUsuari" value="${pathUsuari}.usuariAplicacioID"/>
  </c:if>


  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>
          <%@include file="../webdb/fluxDeFirmesListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="../webdb/fluxDeFirmesListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>
          <c:if test ="${not empty usuariPersona == true}" >
             <%@include file="../webdb/plantillaFluxDeFirmesListCoreHeader.jsp" %>
          </c:if>
          

          <%@include file="../webdb/fluxDeFirmesListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="fluxDeFirmes" items="${fluxDeFirmesItems}">

        <tr>
          <%@include file="../webdb/fluxDeFirmesListCoreMultipleSelect.jsp" %>
        
          <%@include file="../webdb/fluxDeFirmesListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>
          
          <c:if test ="${not empty usuariPersona}" >
             <c:set var="plantillaFluxDeFirmes" value="${fluxDeFirmes.plantillaFluxDeFirmes}"/>              
             <%@include file="../webdb/plantillaFluxDeFirmesListCoreContent.jsp" %>
          </c:if>
          
          


          <%@include file="../webdb/fluxDeFirmesListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </c:if>
  
  <c:if test="${not empty fluxDeFirmesItems}">
          <%@include file="../webdb/webdbPagination.jsp" %>

  </c:if>

  </div> <%-- Final de div de TAULA CONTINGUTS --%>
  <%--  ADD HERE OTHER CONTENT --%>

  
  </div> <%-- Final de div d'AGRUPACIO i TAULA CONTINGUTS --%>

</form:form> 
    

