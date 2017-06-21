<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="Constants" className="es.caib.portafib.utils.Constants" />

<%@include file="../webdb/peticioDeFirmaFormTitle.jsp" %>


<form:form modelAttribute="peticioDeFirmaForm"  method="${method}"
  enctype="multipart/form-data">
 

  <form:hidden path="nou" />
  <c:set var="contexte" value="${peticioDeFirmaForm.contexte}"/>
 
  <%@include file="../webdb/peticioDeFirmaFormCorePre.jsp" %>
  <%@include file="../webdb/peticioDeFirmaFormCore.jsp" %>
  <%@include file="../webdb/peticioDeFirmaFormCorePost.jsp" %>
  <%@include file="../webdb/peticioDeFirmaFormButtons.jsp" %>
  
  <c:if test="${peticioDeFirmaForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/peticioDeFirmaFormModificable.jsp" %>
  </c:if>

</form:form>
  
 <%--  INICI d'ANEXES --%>
<c:if test="${not peticioDeFirmaForm.nou}">
<%--
  <c:set var="__theFilterForm" value="${annexFilterForm}" />
  <c:set var="annexItems" value="${peticioDeFirmaForm.peticioDeFirma.annexs}"/>
  <c:set var="contexte" value="${contexte}/annexos"/>  
   --%>
   

<br/>
<h4> <fmt:message key="peticiodefirma.annexos" /> </h4>
  
<table>
<tr>

<td valign="top">  

  <b><fmt:message key="peticiodefirma.annexos.actuals" /></b>

<form:form name="annex" cssClass="form-search"  modelAttribute="annexFilterForm" 
        method="${method}"  enctype="multipart/form-data">
        
  <%@include file="../webdb/annexListCommon.jsp" %>

  <c:if test="${empty annexItems}">
     <%@include file="../webdb/annexListEmpty.jsp" %>
  </c:if>

  <c:if test="${not empty annexItems}">

  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="../webdb/annexListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>
          <%@include file="../webdb/annexListButtonsHeader.jsp" %>
      </tr>
    </thead>
    <tbody>

      <c:forEach var="annex" items="${annexItems}">

        <tr>
          <%@include file="../webdb/annexListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>          
          <%@include file="../webdb/annexListButtons.jsp" %>
           
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </c:if>

 </form:form>


  <c:if test="${peticioDeFirmaForm.peticioDeFirma.tipusEstatPeticioDeFirmaID == Constants.TIPUSESTATPETICIODEFIRMA_NOINICIAT }">

  </td >
  <td style="border-right: 1px solid" >
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </td>

  <td>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </td>

  <td>

  <b><fmt:message key="peticiodefirma.annexos.afegir" /></b>
  
  <c:url var="actionNewAnnex" value="${contexte}/new" />
  
  <form:form modelAttribute="annexForm" name="annexForm" action="${actionNewAnnex}" method="${method}"
    enctype="multipart/form-data">
    
    <%@include file="../webdb/annexFormCorePre.jsp" %>
    <%@include file="../webdb/annexFormCore.jsp" %>
    <%@include file="../webdb/annexFormCorePost.jsp" %>
    <%@include file="../webdb/annexFormButtons.jsp" %>
    
  </form:form>
  
  </td>
  </c:if>
  </tr>
  </table>
  </c:if> 
  <%--  FINAL d'ANEXES --%>
  
  
  
  



